package translator;

import java.util.ArrayList;

public class Variable {
	String name;
	ArrayList<String> values;
	String type = "default";
	
	public Variable(String name,String val) {
		this.name = name;
		values = new ArrayList<String>();
		val = val.trim();
		this.addValue(val);
		if(!values.contains("nil") && type.equals("default")) {
			values.add("nil");
		}
	}
	public Variable(String name,String val, boolean excludeNil) {
		this.name = name;
		values = new ArrayList<String>();
		val = val.trim();
		this.addValue(val);
		if(!excludeNil) {
		if(!values.contains("nil") && type.equals("default")) {
			values.add("nil");
		}
		}
	}
	public void addValue(String val) {
		if(val.contains("(")) {
			val = val.replaceFirst("\\(","");
		}
		
		val = val.trim();
		String temp = val.replaceAll(">", "");
		temp = temp.replaceAll("<", "");
		temp = temp.replaceAll("=", "");
		temp = temp.replaceAll("=", "");
		if(!values.contains(val)) {
			values.add(val);
		}
		
		
		
		
		try {
			Integer.parseInt(temp);
			this.type = "int";
		}catch(Exception e) {
			try {
				Double.parseDouble(temp);
				this.type = "float";
			}catch(Exception e1) {
				
			}
		}
	}
}
