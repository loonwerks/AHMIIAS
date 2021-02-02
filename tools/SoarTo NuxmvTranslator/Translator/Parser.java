package translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parser {
	boolean priority;
	public Parser(String input, boolean priority,File outputFile) throws FileNotFoundException {
		this.priority = priority;
		
		String output = "MODULE main\n\n";
		String ruleStrings[] = input.split("}");
		Rule rules[] = new Rule[ruleStrings.length];
		for(int i=0;i<ruleStrings.length;i++) {
			ruleStrings[i] = ruleStrings[i].replaceFirst("sp","");
			ruleStrings[i] = ruleStrings[i].replaceFirst("\\{","");
			ruleStrings[i] = ruleStrings[i].trim();
			rules[i] = new Rule(ruleStrings[i]);
		}
		
		ArrayList<Variable> allVariables = new ArrayList<Variable>();
		
		//Find all variables
		findVariables(rules,allVariables);
		
		//output Variable Declarations
		output+= outputVariableDeclarations(allVariables);
		
		
		output += "\n\n";
		
		//output Assign statements
		output += outputAssignStatements(allVariables);
		output += "\n\n";
		
		//output soarAgent declaration
		output += outputModuleDeclarations(allVariables);
		
		//output soarAgent Module
		output += outputSoarModule(allVariables);
		
		
		//generate conditions used for ASSIGN and TRANS blocks
		for(int i=0;i<rules.length;i++) {
			rules[i].generateTransCondition();
			rules[i].generateFullName();
		}
		
		//output ASSIGN statements in soar module
		
		output += outputTransStatements(allVariables, rules);
		
		if(priority) {
		
			//output priority module
			output += outputPriorityModule(allVariables, rules);
			
		}
		
		PrintWriter pw = new PrintWriter(outputFile);
		pw.println(output);
		pw.flush();
		System.out.println("Saved output to "+outputFile.getAbsolutePath());

	}
	public boolean isBlank(String s) {
		s=s.replaceAll(" ", "");
		s=s.replaceAll("\n", "");
		s=s.replaceAll("\t", "");
		s=s.replaceAll("\r", "");

		return s.isEmpty();
	}
	
	public void findVariables(Rule rules[],ArrayList<Variable> allVariables) {
		final int READY = 0,READING_MATCH=1,READING_ATTRIBUTE=2,READING_VALUE=3;
		
		int state = READY;
		
		String lhs;
		String currentPath = "state";
		String attributeName = "";
		//handle LHS of rules
		for(int i=0;i<rules.length;i++) {
			lhs = rules[i].lhs;
			String[] guards = lhs.trim().split("\\)");
			for(int j=0;j<guards.length;j++) {
				guards[j] = guards[j].replaceFirst("\\(", "");
				guards[j] = guards[j].trim();
				if(isBlank(guards[j])) {
					continue;
				}
								
				attributeName = "";
				String words[] = guards[j].split(" ");
				
				for(int k=0;k<words.length;k++) {
					if(words[k].equals("state")) {
						currentPath = "state";
						continue;
					}
					if(words[k].contains("<")&&words[k].contains(">")) {
						if(words[k].contains("<s>")) {
							currentPath = "state";
						}else {
							if(attributeName == "") {
								currentPath = rules[i].matches.get(words[k]);
							}else {
								if(!rules[i].matches.containsKey(words[k])) {
									rules[i].matches.put(words[k], currentPath+"_"+attributeName);								
								}
							}
						}
						continue;
					}
					if(words[k].startsWith("^")) {
						attributeName = words[k].replaceFirst("\\^", "");
						continue;
					}
					String attributeValue = "";
					while(k < words.length && !words[k].contains("^")) {
						attributeValue += words[k];
						k++;
					}
					k--;
					//add value to variable
					boolean present = false;
					for(int x=0;x<rules[i].variables.size();x++) {
						if(rules[i].variables.get(x).name.equals(currentPath+"_"+attributeName)) {
							rules[i].variables.get(x).addValue(attributeValue==""?"nil":attributeValue);
							present = true;
							break;
						}
					}
					if(!present) {
						rules[i].variables.add(new Variable(currentPath+"_"+attributeName,attributeValue==""?"nil":attributeValue,true));
					}
					
					
					//add value to variable in allVariables
					present = false;
					for(int x=0;x<allVariables.size();x++) {
						if(allVariables.get(x).name.equals(currentPath+"_"+attributeName)) {
							allVariables.get(x).addValue(attributeValue==""?"nil":attributeValue);
							present = true;
							break;
						}
					}
					if(!present) {
						allVariables.add(new Variable(currentPath+"_"+attributeName,attributeValue==""?"nil":attributeValue));
					}				
				}		
			}	
		}
		attributeName = "";
		currentPath = "";
		String rhs;
		//handle RHS of rules
		for(int i=0;i<rules.length;i++) {
			rhs = rules[i].rhs;
			String[] guards = rhs.trim().split("\\)");
			for(int j=0;j<guards.length;j++) {
				guards[j] = guards[j].replaceFirst("\\(", "");
				guards[j] = guards[j].trim();
				if(isBlank(guards[j])) {
					continue;
				}
								
				attributeName = "";
				String words[] = guards[j].split(" ");
				
				for(int k=0;k<words.length;k++) {
					
					if(words[k].contains("<")&&words[k].contains(">")) {
						if(words[k].contains("<s>")) {
							currentPath = "state";
						}else {
							if(attributeName == "") {
								currentPath = rules[i].matches.get(words[k]);
							}else {
								if(!rules[i].matches.containsKey(words[k])) {
									rules[i].matches.put(words[k], currentPath+"_"+attributeName);								
								}
							}
						}
						continue;
					}
					if(words[k].startsWith("^")) {
						attributeName = words[k].replaceFirst("\\^", "");
						continue;
					}
					//ignore the "+" in (<s> ^operator <o> +)
					if(words[k].equals("+")) {
						while(k < words.length) {
							if(words[k].equals(">")) {
								rules[i].priority = true;
								break;
							}
							k++;
						}
						break;
					}
					if(words[k].equals("-")) {
						break;
					}
					
					
					String attributeValue = "";
					while(k < words.length && !words[k].contains("^") && !words[k].equals("+")){
						attributeValue += words[k];
						k++;
					}
					k--;
					//add value to variable
					boolean present = false;
					for(int x=0;x<rules[i].variablesRHS.size();x++) {
						if(rules[i].variablesRHS.get(x).name.equals(currentPath+"_"+attributeName)) {
							rules[i].variablesRHS.get(x).addValue(attributeValue==""?"nil":attributeValue);
							present = true;
							break;
						}
					}
					if(!present) {
						rules[i].variablesRHS.add(new Variable(currentPath+"_"+attributeName,attributeValue==""?"nil":attributeValue,true));
					}
					
					
					//add value to variable in allVariables
					present = false;
					for(int x=0;x<allVariables.size();x++) {
						if(allVariables.get(x).name.equals(currentPath+"_"+attributeName)) {
							allVariables.get(x).addValue(attributeValue==""?"nil":attributeValue);
							present = true;
							break;
						}
					}
					if(!present) {
						allVariables.add(new Variable(currentPath+"_"+attributeName,attributeValue==""?"nil":attributeValue));
					}			
				}		
			}	
		}
		
		
	}
	
	public String outputVariableDeclarations(ArrayList<Variable> allVariables) {
		String output = "";
		for(int i=0;i<allVariables.size();i++) {
			output+= "VAR "+allVariables.get(i).name+" : ";
			if(allVariables.get(i).type.equals("default")) {
				output += "{";
				for(int j=0;j<allVariables.get(i).values.size();j++) {
					if(j>0) {
						output+=",";
					}
					output+=allVariables.get(i).values.get(j);
				}
				output +="};\n";
			}else if(allVariables.get(i).type.equals("int")) {
				output += "integer;\n";
			}else if(allVariables.get(i).type.equals("float")) {
				output += "real;\n";
			}
		}
		if(priority) {
			output += "VAR priority : {false, true};";	
		}
		return output;
	}

	public String outputAssignStatements(ArrayList<Variable> allVariables) {
		String output = "";
		output +="ASSIGN\n";
		for(int i=0;i<allVariables.size();i++) {
			if(allVariables.get(i).values.size() == 1 && allVariables.get(i).type.equals("default")) {
				continue;
			}
			output += "    ";
			output+= "init ("+allVariables.get(i).name+") := ";
			if(allVariables.get(i).type.equals("default")) {
				output +="nil;\n";
			}else if(allVariables.get(i).type.equals("int")) {
				output += "0;\n";
			}else if(allVariables.get(i).type.equals("float")) {
				output += "0.0;\n";
			}
		}
		if(priority)
			output += "    init (priority) := false;";
		
		return output;
	}
	public String outputModuleDeclarations(ArrayList<Variable> allVariables) {
		String output = "";
		
		output += "\nVAR soarAgent : soarRules(";
		for(int i=0;i<allVariables.size();i++) {
			
			output +=allVariables.get(i).name+",";
		}
		output += "priority);\n\n";
		if(!priority) {
			output = output.substring(0, output.length()-11);
			output += ");\n\n";
		}
		if(priority) {
		//output priorityModule declaration
		output += "\nVAR priorityModuleRule : priorityModule(";
		for(int i=0;i<allVariables.size();i++) {
			output +=allVariables.get(i).name+",";
		}
		output += "priority";
		output += ");\n\n";
		if(!priority) {
			output = output.substring(0, output.length()-11);
			output += ");\n\n";
		}
		}
		return output;
	}
	public String outputSoarModule(ArrayList<Variable> allVariables) {
		String output = "";
		output += "\nMODULE soarRules(";
		for(int i=0;i<allVariables.size();i++) {
			

			output +=allVariables.get(i).name+", ";
		}
		output += "priority)\n\n";
		if(!priority) {
			output = output.substring(0, output.length()-11);
			output += ");\n\n";
		}
		output += "VAR state:{start, run};\n";
		return output;
	}
	public String outputTransStatements(ArrayList<Variable> allVariables,Rule rules[]) {
		String output = "";
		output += "ASSIGN\n";
		output += "        init (state) := start;\n";
		output += "        next (state) := \n";
		output += "            case\n        ";
		
		for(int i=0;i<rules.length;i++) {
			output += "--";
			output += rules[i].fullName+"\n        ";
			output += "state = start & ("+rules[i].transCondition+") : run;\n        ";
		}
		output += "state = run : start;\n";
		output += "            TRUE : state;\n"
				+ "            esac;\n";
		
		String priority_operator = "";
		String non_priority_operator = "";
		//output TRANS statements for State_operator_name
		for(int i=0;i<allVariables.size();i++) {
			boolean foundVariable = false;
			if(!allVariables.get(i).name.equals("state_operator_name")){
				continue;
			}
			for(int j=0;j<rules.length;j++) {
				for(int k=0;k<rules[j].variablesRHS.size();k++) {
					
					
					if(rules[j].variablesRHS.get(k).name.equals(allVariables.get(i).name)) {
						if(!foundVariable) {
							foundVariable = true;
							
							output += "TRANS\n";
							output += "        next ("+allVariables.get(i).name+") =\n";
							output += "            case\n";
						}
						
						if(rules[j].priority) {
							priority_operator += "        --"+rules[j].fullName;
													
							priority_operator += "\n        (state = run & ";
	
							priority_operator += rules[j].transCondition+"): ";
							priority_operator += rules[j].parseValue(rules[j].variablesRHS.get(k).values.get(0));
							priority_operator += ";\n";
						}else {
							non_priority_operator += "        --"+rules[j].fullName;
							
							non_priority_operator += "\n        (state = run & ";
	
							non_priority_operator += rules[j].transCondition+"): ";
							non_priority_operator += rules[j].parseValue(rules[j].variablesRHS.get(k).values.get(0));
							non_priority_operator += ";\n";
							
							
						}
					}
				}
			}
			if(foundVariable) {
				output += "\n--Priorioty Rules:\n";
				output += priority_operator;
				output += "\n--Non Priorioty Rules:\n";
				output += non_priority_operator;
				output += "            TRUE : nil;\n";
				output += "            esac;\n\n\n";
			}
			
		}
		
		//output TRANS statements for each variable found in the RHS of a rule
		for(int i=0;i<allVariables.size();i++) {
			boolean foundVariable = false;
			if(allVariables.get(i).name.equals("state_operator_name")){
				continue;
			}
			for(int j=0;j<rules.length;j++) {
				for(int k=0;k<rules[j].variablesRHS.size();k++) {
					
					
					if(rules[j].variablesRHS.get(k).name.equals(allVariables.get(i).name)) {
						if(!foundVariable) {
							foundVariable = true;
							
							output += "TRANS\n";
							output += "        next ("+allVariables.get(i).name+") =\n";
							output += "            case\n";
						}
						output += "        --"+rules[j].fullName;
						
						
							output += "\n        (state = run & ";

						
						
						output += rules[j].transCondition+"): ";
						output += rules[j].parseValue(rules[j].variablesRHS.get(k).values.get(0));
						output += ";\n";
					}
				}
			}
			if(foundVariable) {
				output += "            TRUE : "+allVariables.get(i).name+";\n";
				output += "            esac;\n\n\n";
			}
			
		}
		return output;
		
	}
	public String outputPriorityModule(ArrayList<Variable> allVariables,Rule rules[]) {
		String output = "";
		output += "MODULE priorityModule(";
		for(int i=0;i<allVariables.size();i++) {
			
			output +=allVariables.get(i).name+",";
		}
		
		
		output += "priority)\n"
				+ "\n"
				+ "ASSIGN\n"
				+ "          next (priority) :=\n"
				+ "             case\n";
		output += "                ";
		output += "priority = false & (";
		int count = 0;
		for(int i=0;i<rules.length;i++) {
			if(rules[i].priority) {
				
				if(count>0) {
					output += " | ";
				}
				output += rules[i].transCondition;
				count++;
			}
		}
		output += "): true;\n";
		output += "                ";
		output += "(priority = true | priority = false) & (";
		count = 0;
		for(int i=0;i<rules.length;i++) {
			if(!rules[i].priority) {
				
				if(count>0) {
					output += " | ";
				}
				output += rules[i].transCondition;
				count++;
			}
		}
		output += "):false;\n";
		output += "            TRUE:priority;\n";
		output += "            esac;";
		
		return output;
	}
	
	
}
