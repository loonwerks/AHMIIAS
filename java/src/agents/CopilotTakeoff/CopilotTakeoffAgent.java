package agents.CopilotTakeoff;

import agents.XPlaneAgent;
import data.FlightData;
//import jdk.internal.util.xml.impl.Input;
import org.jsoar.kernel.*;
import org.jsoar.kernel.events.OutputEvent;
import org.jsoar.kernel.io.InputBuilder;
import org.jsoar.kernel.io.InputWme;
import org.jsoar.kernel.io.quick.DefaultQMemory;
import org.jsoar.kernel.io.quick.QMemory;
import org.jsoar.kernel.io.quick.SoarQMemoryAdapter;
import org.jsoar.kernel.memory.Wme;
import org.jsoar.kernel.symbols.SymbolFactory;
import org.jsoar.util.commands.SoarCommands;
import util.VotingLogic;
import xplane.SensorChangeLearningOptionDisplay;
import xplane.WaypointController;
import xplane.XPCUserInterface;
import xplane.XPlaneConnector;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static xplane.XPlaneConnector.getFlightData;

/**
 * Created by icislab on 10/19/2016.
 */
public class CopilotTakeoffAgent extends XPlaneAgent
{
    boolean DISPLAYDETAILS = false;
    boolean isAutomated = true;
    boolean USE_LEARNING = false; //default learning mode set to false
    boolean CHANGE_SENSOR_AUTHORITY = false; //default pilot's choice for changing sensor
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
    public boolean GPSReliable = true;
    public boolean IMUReliable = true;
    public boolean LidarReliable = true;
    int timeTag = 0, timeTagClear =0;
    boolean allEnginesOK = true;
    DecisionCycle decisionCycle;
    private boolean wheelBrakesON;
    private  boolean warningResponse = false;
    private boolean airBrakesON;
    private boolean reversersON;

    private java.lang.String realTime;
    private PipedReader filterOutput;
    private XPCUserInterface UIobj;
    private WaypointController waypoints;
    private XPlaneConnector xpcobj;
    private VotingLogic votingobj;
    private int cycleCount = 0, trial_count = 0,timeOffset = 0;;
    private int responseCount =0, response_count =0, responseTimeOffset =0;
    private String pilotAlertResponse = "yes";
    private boolean trialActive = false;
    private boolean responseActive = false;
    private PrintWriter rlWriter, resultWriter;

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
        System.setProperty("jsoar.agent.interpreter","tcl");
        System.err.println("Started");
        sagt = getAgent();


        // uncomment below to see the trace of SOAR execution.
        // It is usually lots of text, but it is often useful

        // sagt.getTrace().enableAll();
        // decisionCycle = Adaptables.adapt(sagt, DecisionCycle.class);
        // decisionCycle.reset();
        PipedWriter agentWriter = new PipedWriter();
        filterOutput = new PipedReader();

        try
        {
            agentWriter.connect(filterOutput);
            //Executors.newSingleThreadExecutor().submit(() -> filterSpeech(filterOutput));
        }
        catch (IOException ignored) {}


        sagt.setName("SOAR_Agent");
//        sagt.getPrinter().pushWriter(new OutputStreamWriter(System.out));
//        sagt.getPrinter().pushWriter(agentWriter);


        //sagt.getEvents().addListener(FlightData.class, new CopilotEventListener(syms, builder, sagt, 100));
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
        try {
            //sagt.getInterpreter().eval("rl --set temporal-discount off");
            //sagt.getInterpreter().eval("rl --set hrl-discount on");
            //sagt.getInterpreter().eval("rl --set discount-rate 0.0");
            sagt.getInterpreter().eval("trace --rl");
        } catch (SoarException e) {
            e.printStackTrace();
        }

        builder = InputBuilder.create(sagt.getInputOutput());
        builder.push("flightdata").markWme("fd").
                add("airspeed", 0.0).markWme("as").
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
                add("lidar-imu-error", 0.0).markWme("lidar-imu-error").
                add("gps-error", 0.0).markWme("gps-error").
                add("imu-error", 0.0).markWme("imu-error").
                add("lidar-error", 0.0).markWme("lidar-error").
                add("pilot_decision", "none").markWme("pilot_decision").
                add("initiate-landing", UIobj.startedLandingProcedure).markWme("initiate-landing").
                add("abort-landing", UIobj.startedLandingProcedure).markWme("abort-landing").
                add("distance-to-target", UIobj.distanceToTarget).markWme("distance-to-target").
                add("sensor-alert-accepted", "nil").markWme("sensor-alert-accepted").
                add("reversersON", reversersON).markWme("reverse").
                add("learning_mode", "false").markWme("learning_mode").
                add("change_sensor_auth", "false").markWme("change_sensor_auth").
                add("gps-reliable", "true").markWme("gps-reliable").
                add("imu-reliable", "true").markWme("imu-reliable").
                add("lidar-reliable", "true").markWme("lidar-reliable");
        try {

            rlWriter = new PrintWriter("C:/soar/preference_values.txt");
            resultWriter = new PrintWriter("C:/soar/testing_results.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        syms = sagt.getSymbols();
        // read the soar file
        try
        {
            String pathToSoar = "C:\\Users\\ahmii\\learning_agent\\FIT_AHMIIAS-master\\lvca\\code\\LearningPrototype\\src\\main\\soar\\com\\soartech\\integrated-learning-agent\\load.soar".replace("/", File.separator);
            //System.out.println(pathToSoar);
            // PATH TO SOAR FILE IMPORTANT
            //pathToSoar = "C:/Users/ahmii/learning_agent/FIT_AHMIIAS-master/lvca/code/LearningPrototype/src/main/soar/com/soartech/integrated-learning-agent/load.soar";
            pathToSoar = "C:/Github Projects -Parth/Soar-Agent_Year-3/soar_agent (Year 3)/integrated-learning-agent/load.soar";
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

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(pushFlightData(), 500, 200, TimeUnit.MILLISECONDS);

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
//            System.out.println(UIobj.activeSensor + " is the selected sensor right now.");
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
                    GPSReliable = false;
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("LIDARUnreliable"))
            {

                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("yes")){
                    UIobj.SensorPossiblyUnreliable = 1;
                    LidarReliable = false;
                }

            }else if (nextWME.getAttribute().asString().getValue().equals("IMUUnreliable"))
            {

                System.out.println(nextWME.getValue());

                String txt = nextWME.getValue().toString();
                if(txt.equalsIgnoreCase("yes")){
                    UIobj.SensorPossiblyUnreliable = 2;
                    IMUReliable = false;
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
                    UIobj.activeSensor = 1;
                }else if(txt.equalsIgnoreCase("imu")){
                    UIobj.activeSensor = 2;
                }else if(txt.equalsIgnoreCase("gps")){
                    UIobj.activeSensor = 0;
                }
                UIobj.pilotDecisionToChange = "nil";
            }else if (nextWME.getAttribute().asString().getValue().equals("clear-sensor-alert-response"))
            {
                if(nextWME.getTimetag()>this.timeTagClear) {
                    this.timeTagClear = nextWME.getTimetag();
                    System.out.println("clear-sensor-alert-response");
                    System.out.println(nextWME.getValue());

                    UIobj.pilotDecision = "nil";
                    UIobj.reset();
                    QMemory memory = DefaultQMemory.create();
                    SoarQMemoryAdapter adapter = SoarQMemoryAdapter.attach(sagt, memory);
                    //memory.setString("io.output-link.clear-sensor-alert-response", "");
                    //memory.remove("io.output-link.clear-sensor-alert-response");
                    //memory.remove("io.flightdata.input-link.alert-sensor-error");
                }
            }else if (nextWME.getAttribute().asString().getValue().equals("alert-sensor-error"))
            {
                System.out.println("alert-sensor-error");
                String faultySensorName = nextWME.getValue().toString();
                UIobj.displayWarning(faultySensorName);



                //QMemory memory = DefaultQMemory.create();
                // SoarQMemoryAdapter adapter = SoarQMemoryAdapter.attach(sagt, memory);
                //memory.remove("io.output-link.alert-sensor-error");
                //memory.setString("io.output-link.clear-sensor-alert-response", "");


            }else if (nextWME.getAttribute().asString().getValue().equals("alert-sensor-error-value"))
            {
                // if(nextWME.getTimetag()>this.timeTag) {
                this.timeTag = nextWME.getTimetag();
                //memory.setString("io.output-link.clear-sensor-alert-response", "");
                double err = nextWME.getValue().asDouble().getValue();
//                System.err.println(nextWME.getValue()+" "+err+" "+trial_count);

                if (this.trial_count == 0) {
                    resultWriter.println(err+" "+trial_count);
                    resultWriter.flush();
                }
                UIobj.displayWarning("gps");
                // Accept High error
                if(isAutomated) {
                    if (err >= 9.0) {
                        UIobj.acknowledgeForError();
                        // Deny Low error
                    } else {
                        UIobj.denyForError();
                    }
                }else{
                    UIobj.ErrorSensor=true;
                }


                // QMemory memory = DefaultQMemory.create();
                //SoarQMemoryAdapter adapter = SoarQMemoryAdapter.attach(sagt, memory);
                // memory.clear("io.output-link.alert-sensor-error-value");
                // }
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
                    UIobj.finishedTakeoff=false;
                    if(xpcobj.getAutopilotState() == 164)
                        xpcobj.setAutopilot(162);
                    ///xpcobj.setAutopilot(164);


                }else if(txt.equalsIgnoreCase("horizontal")){
                    xpcobj.setVTOLModeHorizontal();
                    UIobj.finishedTakeoff=true;
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
            FlightData data = getFlightData();
            double sensorErrors[] = votingobj.checkForReliability(UIobj.positions,data.altitude);
            UIobj.GPSbarObj.error = Math.min(sensorErrors[0],sensorErrors[1]);
            try {
                if(UIobj.learningModeUpdate != -1){
                    if (UIobj.learningModeUpdate == 0){
                        USE_LEARNING = true;
                        UIobj.sensorChangeLearningObj.learningModeInfo=true;
                        CHANGE_SENSOR_AUTHORITY = false;
                        UIobj.sensorChangeLearningObj.authorityToChangeInfo=false;
                    }
                    else if(UIobj.learningModeUpdate == 1){
                        USE_LEARNING = false;
                        UIobj.sensorChangeLearningObj.learningModeInfo=false;
                    }
                }
                if(UIobj.authorityToChangeUpdate != -1){
                    if (UIobj.authorityToChangeUpdate == 0) {
                        CHANGE_SENSOR_AUTHORITY = true;
                        UIobj.sensorChangeLearningObj.authorityToChangeInfo = true;
                    }
                    else if(UIobj.authorityToChangeUpdate == 1){
                        CHANGE_SENSOR_AUTHORITY = false;
                        UIobj.sensorChangeLearningObj.authorityToChangeInfo=false;
                    }
                }
                if(this.USE_LEARNING){
                    if (!this.trialActive){
                        this.trial_count += 1;
                        if (this.trial_count % 5 == 0){
                            timeOffset = 80;
                            sagt.getInterpreter().eval("decide indifferent-selection --epsilon-greedy");
                            sagt.getInterpreter().eval("decide indifferent-selection --epsilon 0.0");
                            sagt.getInterpreter().eval("rl --set learning off");
                            //sagt.getInterpreter().eval("echo turning learning off");

                            // Testing if non incremental error fixes issue with wrong preference values
                            //UIobj.injectError();
                            UIobj.injectIncrementalError();
                        }else {
                            timeOffset = 0;
                            sagt.getInterpreter().eval("rl --set learning on");
                            //sagt.getInterpreter().eval("echo turning learning on");

                            //sagt.getInterpreter().eval("decide indifferent-selection --softmax");
                            sagt.getInterpreter().eval("decide indifferent-selection --boltzmann");
                            sagt.getInterpreter().eval("decide indifferent-selection --temperature "+(Math.max(1.0f, 25-(trial_count/4))));
                            //sagt.getInterpreter().eval("decide indifferent-selection --temperature 1.0");
                            UIobj.injectError();
                        }
                        this.trialActive = true;
                        this.cycleCount = 0;
                    }
                    if(this.trialActive) {
                        this.cycleCount++;
                        // incase previous response was missed
                        if(isAutomated) {
                            if (this.cycleCount == 18 + timeOffset) {
                                // 0: gps-lidar, 1:gps-imu, 2: imu-lidar
                                if (UIobj.GPSbarObj.error >= 9.0) {
                                    UIobj.acknowledgeForError();
                                } else {
                                    UIobj.denyForError();
                                }
                            }
                        }else{
                            UIobj.ErrorSensor = true;
                        }

                        if (this.cycleCount == 20 + timeOffset) {
                            UIobj.reset();
                        }
                        if (this.cycleCount >= 30 + timeOffset) {
                            this.trialActive = false;
                            this.cycleCount = 0;
                            System.err.println(sagt.getInterpreter().eval("print --rl"));
                            if (timeOffset == 0) {
                                rlWriter.println("new Trial");
                                for (Production p : sagt.getProductions().getProductions(null)) {
                                    if (p.rlRuleInfo != null) {
                                        rlWriter.println(p.getName());
                                        rlWriter.println(p.rlRuleInfo.rl_efr);
                                        rlWriter.println(p.rlRuleInfo.rl_ecr);
                                        String name = p.getName();
                                        double a = p.rlRuleInfo.rl_efr;
                                        double b = p.rlRuleInfo.rl_ecr;
                                        double val;
                                        if(a!=0.0 && b!=0.0){
                                            val = (a+b)/2.0;
                                        }else{
                                            val = a+b;
                                        }

                                        if(name.contains("gps")){
                                            if(name.contains("do-not-warn") && name.contains("low*gps")){
                                                UIobj.GPSdisplayObj.doNotWarnLow = val;
                                            }else if(name.contains("do-not-warn") && name.contains("high*gps")){
                                                UIobj.GPSdisplayObj.doNotWarnHigh = val;
                                            }else if(name.contains("warn") && name.contains("low*gps")){
                                                UIobj.GPSdisplayObj.warnLow = val;
                                            }else if(name.contains("warn") && name.contains("high*gps")){
                                                UIobj.GPSdisplayObj.warnHigh = val;
                                            }
                                        } else if (name.contains("imu")) {
                                            if(name.contains("do-not-warn") && name.contains("low*imu")){
                                                UIobj.IMUdisplayObj.doNotWarnLow = val;
                                            }else if(name.contains("do-not-warn") && name.contains("high*imu")){
                                                UIobj.IMUdisplayObj.doNotWarnHigh = val;
                                            }else if(name.contains("warn") && name.contains("low*imu")){
                                                UIobj.IMUdisplayObj.warnLow = val;
                                            }else if(name.contains("warn") && name.contains("high*imu")){
                                                UIobj.IMUdisplayObj.warnHigh = val;
                                            }
                                        } else if (name.contains("lidar")) {
                                            if(name.contains("do-not-warn") && name.contains("low*lidar")){
                                                UIobj.LIDARdisplayObj.doNotWarnLow = val;
                                            }else if(name.contains("do-not-warn") && name.contains("high*lidar")){
                                                UIobj.LIDARdisplayObj.doNotWarnHigh = val;
                                            }else if(name.contains("warn") && name.contains("low*lidar")){
                                                UIobj.LIDARdisplayObj.warnLow = val;
                                            }else if(name.contains("warn") && name.contains("high*lidar")){
                                                UIobj.LIDARdisplayObj.warnHigh = val;
                                            }
                                        }
                                    }
                                }
                                rlWriter.flush();
                            }
                        }
                    }
                } else{
                    if(isAutomated && UIobj.finishedTakeoff && !warningResponse) {
                        if (UIobj.GPSbarObj.error >= 9.0) {
                            UIobj.acknowledgeForError();
                            this.warningResponse = true;
                            // Deny Low error
                        } else {
                            UIobj.denyForError();
                            this.warningResponse = true;
                        }
                        //Pilot accepts/denies the warning issued.
                        this.response_count += 1;
                        if(UIobj.sensorChangeLearningObj.authorityToChangeInfo && this.response_count < 5 && UIobj.GPSbarObj.error >=10){
                            UIobj.pilotDecisionToChange = "yes";
                            InputWme pilotSensorChangeWme = builder.getWme("pilot_decision");
                            pilotSensorChangeWme.update(syms.createString(UIobj.pilotDecision));
                        }
                        if (this.response_count == 5){
                            //Pilot hasn't responded in 5 seconds.
                            this.warningResponse =false;
                            this.response_count =0;
                            UIobj.errorReset();
                        }

                    }
                }

                //FlightData data = getFlightData();
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

                if(UIobj.activeSensor == 0){
                    latitude = UIobj.getGPSLat();
                    longitude = UIobj.getGPSLon();
                }else if(UIobj.activeSensor == 1){
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

                //calculate heading to target waypoint
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

                //double sensorErrors[] = votingobj.checkForReliability(UIobj.positions,data.altitude);
                InputWme GPSvLIDAR = builder.getWme("gps-lidar-error");
                GPSvLIDAR.update(syms.createDouble(sensorErrors[0]));
                InputWme GPSvIMU = builder.getWme("gps-imu-error");
                GPSvIMU.update(syms.createDouble(sensorErrors[1]));
                InputWme LIDARvIMU = builder.getWme("lidar-imu-error");
                LIDARvIMU.update(syms.createDouble(sensorErrors[2]));

                InputWme GPSReliability = builder.getWme("gps-reliable");
                GPSReliability.update(syms.createString(GPSReliable ? "yes" : "no"));
                InputWme IMUReliability = builder.getWme("imu-reliable");
                IMUReliability.update(syms.createString(IMUReliable ? "yes" : "no"));
                InputWme LidarReliability = builder.getWme("lidar-reliable");
                LidarReliability.update(syms.createString(LidarReliable ? "yes" : "no"));

                InputWme GPSerr = builder.getWme("gps-error");
                GPSerr.update(syms.createDouble(Math.min(sensorErrors[0],sensorErrors[1])));
                InputWme IMUerr = builder.getWme("imu-error");
                IMUerr.update(syms.createDouble(Math.min(sensorErrors[1],sensorErrors[2])));
                InputWme LIDARerr = builder.getWme("lidar-error");
                LIDARerr.update(syms.createDouble(Math.min(sensorErrors[0],sensorErrors[2])));

                InputWme learningModeWme  = builder.getWme("learning_mode");
                learningModeWme.update(syms.createString(UIobj.sensorChangeLearningObj.learningModeInfo ? "on" : "off"));
                InputWme sensorChangeAuthWme  = builder.getWme("change_sensor_auth");
                sensorChangeAuthWme.update(syms.createString(UIobj.sensorChangeLearningObj.authorityToChangeInfo ? "on" : "off"));


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
                InputWme alertAccepted = builder.getWme("sensor-alert-accepted");
                alertAccepted.update(syms.createString(UIobj.pilotDecision));
                // temp
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
