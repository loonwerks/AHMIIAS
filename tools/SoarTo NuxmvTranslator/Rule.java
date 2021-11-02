import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
public class Rule{
    String ruleName;
    HashMap<String, String> contextMap; // Example: Maps <s> to state or <o> to state_operator
    ArrayList<String> variables; // Stores all variables used by this rule
    ArrayList<String> guards; // Stores all guards for this rule
    LinkedHashMap<String, Variable> variableMap;
    LinkedHashMap<String, String> valueMap; // Stores the value to apply in the right side of the rule. Maps Variable name to new value
    boolean isLearningRule = false;
    double priority = 0.0;
    public Rule(String name, LinkedHashMap<String, Variable> map){
        this.ruleName = name;
        this.contextMap = new HashMap<String, String>();
        this.variables = new ArrayList<String>();
        this.guards = new ArrayList<String>();
        this.variableMap = map;
        this.valueMap = new LinkedHashMap<String, String>();
    }

    public void populateDuplicate(Rule newRule){
        for(String key : this.contextMap.keySet()){
            newRule.addContext(key, this.contextMap.get(key));
        }
        for(String key : this.variableMap.keySet()){
            newRule.variableMap.put(key, this.variableMap.get(key));
        }
        for(String key : this.valueMap.keySet()){
            newRule.valueMap.put(key, this.valueMap.get(key));
        }
        for(String val : variables){
            newRule.variables.add(val);
        }
        for(String val : guards){
            newRule.guards.add(val);
        }
        newRule.isLearningRule = this.isLearningRule;
        newRule.priority = this.priority;
    }


    public String listVariables(){
        String output = "";
        boolean first = true;
        for(String var : variables){
            if(!first){
                output += ",";
            }else{
                first = false;
            }
            output += var;
        }
        return output;
    }

    public String formatGuard(){
        String output = "";
        boolean first = true;
        ArrayList<String> newGuards = new ArrayList<String> ();
        for(String guard : guards){
            //System.out.println(guard);
            String variableName = guard.split(" ")[0];
            //System.out.println("H" +variableName+"Q");
            int type = variableMap.get(variableName).varType;
            //System.out.println(type);
            if(type == Variable.INT){
                guard = guard.replaceAll("!= nil", ">= 0");
                guard = guard.replaceAll("= nil", "< 0");
            }else if(type == Variable.FLOAT){
                guard = guard.replaceAll("!= nil", ">= 0.0");
                guard = guard.replaceAll("= nil", "< 0.0");
            }

            if(!first){
                output += " & ";
            }else{
                first = false;
            }
            output += guard;
            newGuards.add(guard);
        }
        this.guards = newGuards;
        return output;
    }
    public void addAttrValue(String var, String val){
        valueMap.put(var, val);
    }


    public void addGuard(String guard){
        guards.add(guard);
    }

    public void addVariable(String var){
        variables.add(var);
    }
    public void addContext(String var, String context){
        this.contextMap.put(var, context);
    }

    public String getContext(String var){
        return this.contextMap.get(var);
    }


}
