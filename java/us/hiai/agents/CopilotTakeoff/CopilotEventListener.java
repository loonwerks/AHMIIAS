package us.hiai.agents.CopilotTakeoff;

import org.jsoar.kernel.Agent;
import org.jsoar.kernel.RunType;
import org.jsoar.kernel.io.InputBuilder;
import org.jsoar.kernel.io.InputWme;
import org.jsoar.kernel.symbols.SymbolFactory;
import org.jsoar.util.events.SoarEvent;
import org.jsoar.util.events.SoarEventListener;
import us.hiai.data.FlightData;

public class CopilotEventListener implements SoarEventListener
{

    private SymbolFactory syms;
    private InputBuilder builder;
    private Agent sagt;
    private long duration;


    CopilotEventListener(SymbolFactory syms, InputBuilder builder, Agent sagt, long duration)
    {
        this.syms = syms;
        this.builder = builder;
        this.sagt = sagt;
        this.duration = duration;
    }

    @Override
    public void onEvent(SoarEvent soarEvent)
    {
        FlightData data = (FlightData) soarEvent;
        System.out.println("Got an event:" + data);

        InputWme bl = builder.getWme("as");
        bl.update(syms.createInteger(data.airspeed));
        InputWme lat = builder.getWme("lat");
        lat.update(syms.createDouble(data.lat));
        InputWme lon = builder.getWme("lon");
        lon.update(syms.createDouble(data.lon));
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


        // uncomment if you want to see the productions that matched

//            MatchSet matchSet = sagt.getMatchSet();
//
//            if ( matchSet.getEntries().size() > 1)
//            {
//                System.out.println("Found matching productions!!");
//                for (MatchSetEntry mse : matchSet.getEntries())
//                {
//                    System.out.println("Production:" + mse.getProduction());
//                }
//            }

        sagt.runFor(1, RunType.DECISIONS);

    }
}