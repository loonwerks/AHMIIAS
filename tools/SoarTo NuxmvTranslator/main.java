import parser.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.*;
import java.util.Scanner;
public class main{
    public static String path = "C:/Users/ahmii/learning_agent/FIT_AHMIIAS-master/lvca/code/LearningPrototype/src/main/soar/com/soartech/integrated-learning-agent/load.soar";
    public static String debugPath = "C:/soar_translator/Learning Rules.soar";
    public static void main(String args[]){
        try{
            // read soar agent into a string
            String inputText = cleanText(readInputFile(debugPath));
            //System.out.println(inputText);

            //System.out.println(inputText);
            //System.exit(0);
            // Load Soar File
            ANTLRInputStream input = new ANTLRInputStream(inputText);
            // Create Lexer

            SoarLexer lexer = new SoarLexer(input);
            // Lex Soar file into Tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // Create Parser
            SoarParser parser = new SoarParser(tokens);
            Visitor visitor = new Visitor();
            visitor.rules = new SoarRules();
            visitor.visit(parser.soar());
            Output outputFormatter = new Output(visitor.rules);
            String outputText = outputFormatter.generateOutput();
            //System.out.println(outputText);
            PrintWriter pw = new PrintWriter(new File("output.smv"));
            pw.println(outputText);
            pw.flush();
            pw.close();

    }catch(Exception e){
        e.printStackTrace();
    }

    }

    public static String readInputFile(String path) throws FileNotFoundException{
        File soarFile = new File(path);
        Scanner sc = new Scanner(soarFile);
        String text = "";
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(s.startsWith("echo")){
                System.out.println(s.substring(5));
            }else if(s.startsWith("source")){
                String filePath = soarFile.getParent() + "/" + s.substring(7).replaceAll("\"", "");
                text += readInputFile(filePath);
            }else if(s.contains("(write (crlf)")){

            }else{
                text += s+"\n";
            }

        }
        return text;
    }

    public static String cleanText(String s){
        String keywordBlackList[] = {"waitsnc --on", "rl --set learning on", "decide indifferent-selection --softmax"};
        for(int i=0; i< keywordBlackList.length; i++){
            s = s.replaceAll(keywordBlackList[i], "");
        }
        // replace the "." in variable names with "_" but don't replace the "." in floating point numbers
        s=s.replaceAll("([a-z|A-Z])\\.","$1\\_");

        return s;
    }



}
