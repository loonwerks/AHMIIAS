package translator;

import java.util.ArrayList;
import java.util.HashMap;

public class Rule {
	public String name;
	public String lhs,rhs;
	public ArrayList<Variable> variables;
	public ArrayList<Variable> variablesRHS;
	public HashMap<String,String> matches;
	public String fullName;
	public String transCondition = "";
	public boolean priority = false;
	public Rule(String rule) {

		this.name = rule.substring(0,rule.indexOf("("));
		rule = rule.substring(rule.indexOf("("));
		lhs = rule.split("-->")[0];
		rhs = rule.split("-->")[1];

		matches = new HashMap<String,String>();
		variables = new ArrayList<Variable>();
		variablesRHS = new ArrayList<Variable>();
	}
	public void generateTransCondition() {
		int count = 0;
		for(int i=0;i<variables.size();i++) {
			for(int j=0;j<variables.get(i).values.size();j++) {
				if(count>0) {
					transCondition += " & ";
				}
				count++;
				if(variables.get(i).values.get(j).startsWith(">") ||variables.get(i).values.get(j).startsWith("<")) {
					transCondition += variables.get(i).name + variables.get(i).values.get(j);
				}else {
					transCondition += variables.get(i).name + "=" +variables.get(i).values.get(j);
				}
			}
		}

	}
	public void generateFullName() {
		fullName = name +"(";
		for(int i=0;i<variables.size();i++) {
			if(i>0) {
				fullName+=", ";
			}
			fullName +=variables.get(i).name;
		}
		fullName +=")";
	}
	public String parseValue(String value) {
		if(value.contains("<")&&value.contains(">")) {
			String word = value.substring(value.indexOf("<"), value.indexOf(">")+1);
			value = value.replaceAll(word, "");
			value = this.matches.get(word) + value;
		}

		return value;

	}

}
