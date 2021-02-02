package translator;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SoarToNuxmvTranslator {
	public static boolean usePriority = false;
	public static void main(String[] args) {
		new SoarToNuxmvTranslator(args);
		
	}
	public SoarToNuxmvTranslator(String[] args) {
		try {
				Scanner sc;
				File outputFile;
				
				if(args.length < 1) {
				JFileChooser fileSelector = new JFileChooser();
	
		        
		        if(fileSelector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
		        
		        	System.exit(0);
		        }
				sc = new Scanner(fileSelector.getSelectedFile());
				outputFile = new File(fileSelector.getSelectedFile().getParentFile().getAbsolutePath()+"/output.smv");
			
				}else if(args.length < 2) {
					sc = new Scanner(new File(args[0]));
					outputFile = new File("output.smv");
				}else {
					sc = new Scanner(new File(args[0]));
					outputFile = new File(args[1]);
					
					
				}
			String input = "";
			String inputLine = "";
			while(sc.hasNext()) {
				inputLine = sc.nextLine();
				if(inputLine.contains("waitsnc")) {
					continue;
				}else if(inputLine.contains("#")){
					inputLine = inputLine.substring(0,inputLine.indexOf('#'));
				}
				if(inputLine.contains("write")&&inputLine.contains("(crlf)")) {
					continue;
				}
				input += inputLine;
			}
			/*Format input by:
			 * converting all input-links/output-links to io 
			 * removing new lines
			 * removing tabs
			 * removing spaces after and before brackets
			 * replacing all instances of multiple spaces with a single space
			 */
			input = input.replaceAll(".input-link.flightdata", "");
			input = input.replaceAll(".output-link", "");
			input = input.replaceAll("io\\.", "io_");
			input = input.replaceAll("\n", "");
			input = input.replaceAll("\t", "");
			input = input.replaceAll("-\\^name", "\\^name nil");
			input = input.replaceAll("\\( ", "\\(");
			input = input.replaceAll("\\) ", " \\)");
			while(input.contains("  ")) {
				input = input.replaceAll("  ", " ");
			}
			//System.out.println(input);
			
			Parser SoarParser = new Parser(input, usePriority, outputFile);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
