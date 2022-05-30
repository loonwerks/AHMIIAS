package us.hiai;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import gov.nasa.xpc.XPlaneConnect;
import org.reflections.Reflections;
import us.hiai.agents.XPlaneAgent;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 *
 * Created by mstafford on 10/3/2016.
 */
public class StartAgents
{
    static XPlaneConnect xpc = null;
    static Voice textToSpeechEngine = null;

    public static void main(String[] args)
    {
        Reflections reflect = new Reflections("us.hiai.agents");
        Set<Class<? extends XPlaneAgent>> agentClasses = reflect.getSubTypesOf(XPlaneAgent.class);

        ExecutorService agentThreadPool = Executors.newCachedThreadPool();

        for(Class<? extends XPlaneAgent> agentClass : agentClasses)
        {
            try (XPlaneAgent agent = agentClass.newInstance())
            {


                if (agent.runOnStartup())
                {
                    System.err.println(agent.getClass());
                    agentThreadPool.submit(agent);
                }
            }
            catch (Exception ignored) {
                System.err.println(ignored.getMessage());
                ignored.printStackTrace();
            }
        }
    }

    public static XPlaneConnect getXPlaneConnector()
    {
        if ( xpc == null )
        {
            try
            {
                xpc = new XPlaneConnect();
                // Ensure connection established.
                xpc.getDREF("sim/test/test_float");
                return xpc;
            }
            catch (IOException e) {
                System.err.println(e.getStackTrace()[0] + e.getMessage() + "  Is X-Plane running?");
            }
            System.exit(-1);
            return null;
        }
        else
        {
            return xpc;
        }
    }



    public static void textToSpeech(String message)
    {
        if (textToSpeechEngine == null)
        {
            VoiceManager voiceManager = VoiceManager.getInstance();
            textToSpeechEngine = voiceManager.getVoice("kevin16");
            textToSpeechEngine.allocate();
        }
        textToSpeechEngine.speak(message);
    }

    private static void terminate()
    {
        textToSpeechEngine.deallocate();
    }
}
