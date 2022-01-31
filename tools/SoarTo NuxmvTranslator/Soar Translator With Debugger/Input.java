import org.jsoar.kernel.Agent;
import org.jsoar.kernel.Production;
import org.jsoar.kernel.SoarException;
import org.jsoar.kernel.tracing.Printer;
import org.jsoar.util.commands.SoarCommands;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Input {

    // Function to load soar agent into JSoar and return all productions
    public static String getSoarRules(String path) throws SoarException {
        // Magic to enable TCL
        System.setProperty("jsoar.agent.interpreter","tcl");
        // Create agent
        Agent agent = new Agent();
        //agent.getPrinter().pushWriter(new OutputStreamWriter(System.out));
        agent.initialize();
        SoarCommands.source(agent.getInterpreter(), path);

        StringWriter sw = new StringWriter();
        Printer pw = new Printer(new PrintWriter(sw));

        for(Production p: agent.getProductions().getProductions(null)){
            p.print(pw, true);

        }
        pw.flush();
        return cleanProductions(sw.toString());
    }

    private static String cleanProductions(String input){
        //input = input.replaceAll("<[a-z][*][0-9]>", "");
        //input = input.replaceAll("[{]([a-zA-Z0-9><= ]*)[}]", "$1");

        return input;
    }

}
