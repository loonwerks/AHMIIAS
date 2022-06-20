package agents;

/**
 * Created by mstafford on 10/3/2016.
 */

import gov.nasa.xpc.XPlaneConnect;
import main.StartAgents;
import org.jsoar.kernel.Agent;
import org.jsoar.kernel.DecisionCycle;
import org.jsoar.kernel.SoarException;
import org.jsoar.runtime.ThreadedAgent;
import org.jsoar.util.adaptables.Adaptables;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class XPlaneAgent implements AutoCloseable, Runnable
{
    private final ThreadedAgent underlyingSoarAgent = ThreadedAgent.create();
    private final XPlaneConnect xplaneConnector;
    private DecisionCycle decisionCycle;
    private Process lastSpeechProcess;

    protected XPlaneAgent()
    {
        xplaneConnector = StartAgents.getXPlaneConnector();

        decisionCycle = Adaptables.adapt(underlyingSoarAgent, DecisionCycle.class);
        underlyingSoarAgent.setName(name());
        underlyingSoarAgent.getPrinter().pushWriter(new OutputStreamWriter(System.out));
        underlyingSoarAgent.initialize();
        try {
            if(this.runOnStartup())   underlyingSoarAgent.openDebuggerAndWait();
        } catch (SoarException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //underlyingSoarAgent.runForever();
    }

    protected void speakText(String text)
    {
        String audioFile = "resources/audio/" + text.replace(" ", "_") + ".mp3";

        boolean audioFileExists = Files.exists(Paths.get(audioFile));

        try
        {
            if (!audioFileExists)
            {
                generateMP3(text, audioFile);
            }
            playAudioFile(audioFile);

        }
        catch (Exception e)
        {
            System.err.println("Couldn't say " + text + ". " + e.getMessage());
        }

    }

    private void generateMP3(String text, String audioFile) throws IOException
    {
        String pathToTTS = "src/main/python/tts/generateMP3.py".replace("/", File.separator);
        new ProcessBuilder("python", pathToTTS, text, audioFile)
                .inheritIO()
                .start();
    }


    private void playAudioFile(String filename) throws Exception
    {
        String pathToTTS = "src/main/python/tts/playMP3.py".replace("/", File.separator);
        new ProcessBuilder("python", pathToTTS, filename)
                .inheritIO()
                .start();
    }

    private void runPython(String pythonFile, String... args)
    {
        String pathToTTS = ("src/main/python/tts/" + pythonFile).replace("/", File.separator);

        LinkedList<String> processArgs = new LinkedList<>(Arrays.asList(args));
        processArgs.addFirst("python");
        processArgs.addFirst(pathToTTS);
        String[] sargs = processArgs.toArray(new String[processArgs.size()]);

        try
        {
            new ProcessBuilder(sargs)
                    .inheritIO()
                    .redirectError(ProcessBuilder.Redirect.PIPE).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close() throws Exception
    {
        underlyingSoarAgent.dispose();
    }

    protected Agent getAgent()
    {
        return underlyingSoarAgent.getAgent();
    }

    @Override
    public void run()
    {
        start();
    }

    protected abstract String name();

    public abstract boolean runOnStartup();

    public abstract void start();
}
