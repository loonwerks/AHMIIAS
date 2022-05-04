package us.hiai.agents.CopilotTakeoff;

import org.jsoar.kernel.*;
import org.jsoar.kernel.events.OutputEvent;
import org.jsoar.kernel.io.InputBuilder;
import org.jsoar.kernel.io.InputWme;
import org.jsoar.kernel.io.quick.DefaultQMemory;
import org.jsoar.kernel.io.quick.QMemory;
import org.jsoar.kernel.io.quick.SoarQMemoryAdapter;
import org.jsoar.kernel.memory.Wme;
import org.jsoar.kernel.symbols.SymbolFactory;
import org.jsoar.util.adaptables.Adaptables;
import org.jsoar.util.commands.SoarCommands;
import us.hiai.agents.XPlaneAgent;
import us.hiai.data.FlightData;
import us.hiai.util.VotingLogic;
import us.hiai.xplane.WaypointController;
import us.hiai.xplane.XPCUserInterface;
import us.hiai.xplane.XPlaneConnector;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static us.hiai.xplane.XPlaneConnector.getFlightData;

/**
 * Created by icislab on 10/19/2016.
 */
public class CopilotTakeoffAgent extends XPlaneAgent
{
    boolean DISPLAYDETAILS = false;
    private SymbolFactory syms;
    Agent sagt = getAgent();
    InputBuilder builder;
    double batteryLevel = 0.0;
    double heading = 0.0;
    double latitude = 0.0;
    double longitude = 0.0;
    double pitch = 0.0;
    double throttle = 0.0;
    double targetHeading = 0.0;
    double errorInSensor = 0.0;
    boolean allEnginesOK = true;
    DecisionCycle decisionCycle;
    private boolean wheelBrakesON;
    private boolean airBrakesON;
    private boolean reversersON;

    private java.lang.String realTime;
    private PipedReader filterOutput;
    private XPCUserInterface UIobj;
    private WaypointController waypoints;
    private XPlaneConnector xpcobj;
    private VotingLogic votingobj;
    @Override
    public java.lang.String name() {
        return "Copilot_Takeoff";
    }

    @Override
    public boolean runOnStartup() {
        return true;
    }

    @Override
    public void start()  {
        System.err.println("Started");
        sagt = getAgent();

        // uncomment below to see the trace of SOAR execution.
        // It is usually lots of text, but it is often useful

//        sagt.getTrace().enableAll();

        decisionCycle = Adaptables.adapt(sagt, DecisionCycle.class);
        //decisionCycle.reset();
        PipedWriter agentWriter = new PipedWriter();
        filterOutput = new PipedReader();

        /*try
        {
            agentWriter.connect(filterOutput);
            //Executors.newSingleThreadExecutor().submit(() -> filterSpeech(filterOutput));
        }
        catch (IOException ignored) {}
*/

        sagt.setName("SOAR_Agent");
        sagt.getPrinter().pushWriter(new OutputStreamWriter(System.out));
//        sagt.getPrinter().pushWriter(agentWriter);


//        sagt.getEvents().addListener(FlightData.class, new CopilotEventListener(syms, builder, sagt, 100));
        UIobj = new XPCUserInterface();
        try {
            Thread thread_UI = new Thread(UIobj);
            thread_UI.start();
        }catch(Exception e){
            e.printStackTrace();
        }
        waypoints = new WaypointController();
        votingobj = new VotingLogic();
        xpcobj = new XPlaneConnector();


        xpcobj.setAutopilot(162);
        //226

        sagt.initialize();


        builder = InputBuilder.create(sagt.getInputOutput());
        builder.push("flightdata").markWme("fd").
                add("airspeed", batteryLevel).markWme("as").
                add("lat", latitude).markWme("lat").
                add("lon", longitude).markWme("lon").
                add("altitude", pitch).markWme("alt").
                add("allEnginesOK", allEnginesOK).markWme("engOK").
                add("wheelbrakesON", wheelBrakesON).markWme("wBrakes").
                add("airbrakesON", airBrakesON).markWme("aBrakes").
                add("throttle", throttle).markWme("throttle").
                add("sensor-error", errorInSensor).markWme("sensor-error").
                add("gps-lidar-error", 0.0).markWme("gps-lidar-error").
                add("gps-imu-error", 0.0).markWme("gps-imu-error").
                add("lidar-imu-error", errorInSensor).markWme("lidar-imu-error").
                add("pilot_decision", "none").markWme("pilot_decision").
                add("initiate-landing", UIobj.startedLandingProcedure).markWme("initiate-landing").
                add("abort-landing", UIobj.startedLandingProcedure).markWme("abort-landing").
                add("distance-to-target", UIobj.distanceToTarget).markWme("distance-to-target").
                add("reversersON", reversersON).markWme("reverse");


        syms = sagt.getSymbols();
        // read the soar file
        try
        {
            //String pathToSoar = "/lvca/src/main/soar/agents/CopilotTakeoff.soar".replace("/", File.separator);
            String pathToSoar = "/home/ahmiias/Desktop/FIT_AHMIIAS/lvca/code/XPlaneSoarConnector/src/main/soar/agents/VTOL_test2.soar".replace("/", File.separator);
            SoarCommands.source(sagt.getInterpreter(), pathToSoar);
            System.out.println("There are now " + sagt.getProductions().getProductionCount() + " productions loaded");

        }
        catch (SoarException e) {
            e.printStackTrace();
        }

        sagt.getEvents().addListener(OutputEvent.class,
                soarEvent ->
                {
                    // Contains details of the OutputEvent
                    Executors.newSingleThreadExecutor().execute(() ->
                            {
                                //System.err.println("hi");
                                // Start new thread
                               // CopilotTakeoffAgent.this.speakWmes((OutputEvent) soarEvent);  // Say all WMEs with the "spoken" attribute
                                CopilotTakeoffAgent.this.throttleWmes((OutputEvent) soarEvent);  // Update throttle
                                CopilotTakeoffAgent.this.VTOLModeWmes((OutputEvent) soarEvent);  // Update the VTOL rotor position
                            });
                });

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(pushFlightData(), 500, 500, TimeUnit.MILLISECONDS);

    }
    /*
    private void speakWmes(OutputEvent soarEvent)
    {
        Iterator<Wme> wmes = soarEvent.getWmes();
        while (wmes.hasNext())
        {
            Wme nextWME = wmes.next();
            if (nextWME.getAttribute().asString().getValue().equals("spoken"))
            {
                String txt = nextWME.getValue().asString().getValue();
                speakText(txt);

                QMemory memory = DefaultQMemory.create();
                SoarQMemoryAdapter adapter = SoarQMemoryAdapter.attach(sagt, memory);
                memory.setString("io.output-link.spoken", "");
            }
        }


    }
     */
    private void throttleWmes(OutputEvent soarEvent)
    {

        Iterator<Wme> wmes = soarEvent.getWmes();
        while (wmes.hasNext())
        {
            Wme nextWME = wmes.next();
            printWme(nextWME);
            //System.err.println(nextWME.getAttribute().asString().getValue());
            if (nextWME.getAttribute().asString().getValue().equals("throttle"))
            {
                //System.err.println("HELLO");
               System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                float throttle = Float.parseFloat(txt);
                xpcobj.setEngineThrottle(throttle);
                //speakText(txt);
                try {


                }catch(Exception e){

                }
            }else if (nextWME.getAttribute().asString().getValue().equals("GPSUnreliable"))
            {

                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("yes")){
                    UIobj.SensorPossiblyUnreliable = 0;
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("LIDARUnreliable"))
            {

                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("yes")){
                    UIobj.SensorPossiblyUnreliable = 1;
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("IMUUnreliable"))
            {

                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("yes")){
                    UIobj.SensorPossiblyUnreliable = 2;
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("target-altitude"))
            {
                //System.err.println("HELLO");
                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                int altitude = Integer.parseInt(txt);
                xpcobj.setTargetAltitude(altitude);

            }else if (nextWME.getAttribute().asString().getValue().equals("target-speed"))
            {
                //System.err.println("HELLO");
                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                int airspeed = Integer.parseInt(txt);
                xpcobj.setTargetAirspeed(airspeed);
                if(airspeed == 0 && xpcobj.getAutopilotState() != 170){
                   // xpcobj.setAutopilot(170);
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("autoflaps"))
            {
                //System.err.println("HELLO");
                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("on")){
                    xpcobj.setFlapsMode(1);
                }else{
                    xpcobj.setFlapsMode(0);
                }
            }else if (nextWME.getAttribute().asString().getValue().equals("air-brake"))
            {
                //System.err.println("HELLO");
                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                float brakeDeployment = Float.parseFloat(txt);
                xpcobj.setAirBrake(brakeDeployment);

            }else if (nextWME.getAttribute().asString().getValue().equals("sensor-to-use"))
            {
                //System.err.println("HELLO");
                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("lidar")){
                    UIobj.selectedSensor = 1;
                }else if(txt.equalsIgnoreCase("imu")){
                    UIobj.selectedSensor = 2;
                }else if(txt.equalsIgnoreCase("gps")){
                    UIobj.selectedSensor = 0;
                }
                UIobj.pilotDecision = "none";
            }

        }
    }
    private void VTOLModeWmes(OutputEvent soarEvent)
    {

        Iterator<Wme> wmes = soarEvent.getWmes();
        while (wmes.hasNext())
        {
            Wme nextWME = wmes.next();
            if(DISPLAYDETAILS)
                System.out.println(nextWME.getAttribute().asString().getValue());
            if (nextWME.getAttribute().asString().getValue().equals("VTOLMode"))
            {


                String txt = nextWME.getValue().toString();
                //System.err.println(txt);
                if(txt.equalsIgnoreCase("vertical")){
                    xpcobj.setVTOLModeVertical();
                    if(xpcobj.getAutopilotState() == 164)
                        xpcobj.setAutopilot(162);
                    ///xpcobj.setAutopilot(164);


                }else if(txt.equalsIgnoreCase("horizontal")){
                    xpcobj.setVTOLModeHorizontal();
                    if(xpcobj.getAutopilotState() == 164)
                        xpcobj.setAutopilot(162);



                }else{
                    System.err.println("ERROR invalid VTOLMode");
                }

                //speakText(txt);

                // QMemory memory = DefaultQMemory.create();
                //SoarQMemoryAdapter adapter = SoarQMemoryAdapter.attach(sagt, memory);
                //memory.setString("io.output-link.throttle", "");
            }
        }
    }
    private Runnable pushFlightData()
    {
        return () ->
        {
            long bt = System.nanoTime()/1000000;
            try {
                FlightData data = getFlightData();
                //building the input structure for the soar agent

                //System.out.println("\t\t\t\tSpeed: "+data.airspeed);
                //System.out.println("\t\t\t\tThrottle: " + data.throttle);
                if(DISPLAYDETAILS)
                    System.out.println(data.lat +","+data.lon);
                UIobj.setGPSValue((float) data.lat, (float) data.lon);
                UIobj.setLidarValue((float) data.lat, (float) data.lon);
                UIobj.setIMUValue((float) data.lat, (float) data.lon);

                double latitude = 0.0;
                double longitude = 0.0;

                if(UIobj.selectedSensor == 0){
                    latitude = UIobj.getGPSLat();
                    longitude = UIobj.getGPSLon();
                }else if(UIobj.selectedSensor == 1){
                    latitude = UIobj.getLidarLat();
                    longitude = UIobj.getLidarLon();
                }else{
                    latitude = UIobj.getIMULat();
                    longitude = UIobj.getIMULon();
                }
                if(DISPLAYDETAILS)
                    System.out.println(xpcobj.getAutopilotState());

                if(UIobj.reroutedLandingZone != -1){
                    waypoints.currentWaypoint = UIobj.reroutedLandingZone + 4;
                    UIobj.reroutedLandingZone = -1;

                    UIobj.startedLandingProcedure = false;
                    UIobj.abortLanding = false;
                    xpcobj.setAutopilot(162);
                }
                if(xpcobj.getAutopilotState() != 162){
                    xpcobj.setAutopilot(162);

                }

                //calculate heading to taget waypoint
                waypoints.updateWaypoints(latitude,longitude);
                targetHeading = waypoints.calculateHeading(latitude,longitude);
                UIobj.setTargetWaypoint((float)waypoints.waypoints[waypoints.currentWaypoint].getLat(),(float)waypoints.waypoints[waypoints.currentWaypoint].getLon());
                UIobj.setDistanceToTarget(waypoints.getDistanceToWaypoint(latitude,longitude));
                UIobj.displayLandingButton = waypoints.waypoints[waypoints.currentWaypoint].isLandingPoint;
                xpcobj.setTargetHeading(targetHeading);

                UIobj.planeAirspeed = data.airspeed;
                UIobj.planeAltitude = data.altitude;


                // data.lat provides real latitude while 'latitude' provides the GPS sensor's value (possibly has an error)
                errorInSensor = waypoints.getDistanceBetweenTwoPoints(latitude, longitude, data.lat,data.lon);


                InputWme bl = builder.getWme("as");
                bl.update(syms.createInteger(data.airspeed));
                InputWme lat = builder.getWme("lat");
                lat.update(syms.createDouble(latitude));
                InputWme lon = builder.getWme("lon");
                lon.update(syms.createDouble(longitude));

                InputWme throttle = builder.getWme("throttle");
                throttle.update(syms.createDouble(data.throttle));
                InputWme errorInSensorWme = builder.getWme("sensor-error");
                errorInSensorWme.update(syms.createDouble(errorInSensor));


                double sensorErrors[] = votingobj.checkForReliability(UIobj.positions,data.altitude);
                InputWme GPSvLIDAR = builder.getWme("gps-lidar-error");
                GPSvLIDAR.update(syms.createDouble(sensorErrors[0]));
                InputWme GPSvIMU = builder.getWme("gps-imu-error");
                GPSvIMU.update(syms.createDouble(sensorErrors[1]));
                InputWme LIDARvIMU = builder.getWme("lidar-imu-error");
                LIDARvIMU.update(syms.createDouble(sensorErrors[2]));


                InputWme initiatedLandingWme = builder.getWme("initiate-landing");
                initiatedLandingWme.update(syms.createString(UIobj.startedLandingProcedure ? "yes":"no"));
                InputWme abortLandingWme = builder.getWme("abort-landing");
                abortLandingWme.update(syms.createString(UIobj.abortLanding ? "yes":"no"));
                InputWme pilotWme = builder.getWme("pilot_decision");
                pilotWme.update(syms.createString(UIobj.pilotDecision));

                InputWme d = builder.getWme("distance-to-target");
                d.update(syms.createDouble(UIobj.distanceToTarget));
                InputWme p = builder.getWme("alt");
                p.update(syms.createInteger(data.altitude));
                InputWme e = builder.getWme("engOK");
                e.update(syms.createString(Boolean.toString(data.allEningesOK)));
                InputWme wb = builder.getWme("wBrakes");
                wb.update(syms.createString(Boolean.toString(data.wheelBrakesON)));
                InputWme ab = builder.getWme("aBrakes");
                ab.update(syms.createString(Boolean.toString(data.airBrakesON)));
                InputWme re = builder.getWme("reverse");
                re.update(syms.createString(Boolean.toString(data.reversersON)));


                // uncomment if you want to see the productions that matches
                MatchSet matchSet = sagt.getMatchSet();

                if (matchSet.getEntries().size() > 1) {
                    System.out.println("Found matching productions!!");
                    for (MatchSetEntry mse : matchSet.getEntries()) {
                        System.out.println("Production:" + mse.getProduction());
                    }
                }

                sagt.runFor(1, RunType.DECISIONS);

            }catch(Exception e){
                e.printStackTrace();
            }
            long at = System.nanoTime()/1000000;
            if(DISPLAYDETAILS)
                System.out.println("[INFO]time taken: "+(at-bt)+"ms");
        } ;
    }

    private void printWme(Wme wme)
    {
        System.out.println(wme);
        Iterator<Wme> children = wme.getChildren();
        while (children.hasNext())
        {
            Wme child = children.next();
            printWme(child);
        }
    }
}