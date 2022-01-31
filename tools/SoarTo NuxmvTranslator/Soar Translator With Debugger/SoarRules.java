import java.util.ArrayList;
import java.util.LinkedHashMap;
public class SoarRules{
    ArrayList<Rule> rules;
    LinkedHashMap<String, Variable> variables;
    LinkedHashMap<String, Integer> mapNameToType;
    LinkedHashMap<String, ArrayList<String>> typeGraph;

    public SoarRules(){
        rules = new ArrayList<Rule>();
        variables = new LinkedHashMap<String, Variable>();
        mapNameToType = new LinkedHashMap<String, Integer>();
        typeGraph = new LinkedHashMap<String, ArrayList<String>>();
    }

    public void addExistsVariables(){
        LinkedHashMap<String, Variable> newVariables = new LinkedHashMap<String, Variable>();
        for(String varName: variables.keySet()){
            newVariables.put(varName, variables.get(varName));
            if(variables.get(varName).varType == Variable.INT || variables.get(varName).varType == Variable.FLOAT){
                Variable exists = new Variable(varName+"_exists");
                exists.addValue("yes");
                exists.addValue("no");
                exists.varType = Variable.S_CONST;
                newVariables.put(varName+"_exists", exists);
            }
        }
        this.variables = newVariables;

        // Add exists variables to every rule
        ArrayList<Rule> newRules = new ArrayList<Rule>();
        for(Rule rule : rules){
            // add exists variables to list of variables
            ArrayList<String> newRuleVariables = new ArrayList<String>();
            for(String varName : rule.variables){
                newRuleVariables.add(varName);
                if(variables.get(varName).varType == Variable.INT || variables.get(varName).varType == Variable.FLOAT){
                    newRuleVariables.add(varName+"_exists");
                }
            }
            rule.variables = newRuleVariables;

            // add exists variables to Hashmap of variables
            LinkedHashMap<String, Variable> newRuleMapVariables = new LinkedHashMap<String, Variable>();
            for(String varName: rule.variableMap.keySet()){
                newRuleMapVariables.put(varName, rule.variableMap.get(varName));
                if(variables.get(varName).varType == Variable.INT || variables.get(varName).varType == Variable.FLOAT){
                    Variable exists = new Variable(varName+"_exists");
                    exists.addValue("yes");
                    exists.addValue("no");
                    exists.varType = Variable.S_CONST;
                    newRuleMapVariables.put(varName+"_exists", exists);
                }

            }
            rule.variableMap = newRuleMapVariables;

            // add exists to values being set in action side
            LinkedHashMap<String, String> ruleValueMap = new LinkedHashMap<String, String>();
            for(String varName : rule.valueMap.keySet()){

                if(variables.get(varName).varType == Variable.INT || variables.get(varName).varType == Variable.FLOAT){
                    String val = rule.valueMap.get(varName);
                    if(val.equals("nil")){
                        ruleValueMap.put(varName+"_exists","no");
                    }else{
                        ruleValueMap.put(varName+"_exists","yes");
                        ruleValueMap.put(varName,rule.valueMap.get(varName));
                    }

                }else{
                    ruleValueMap.put(varName,rule.valueMap.get(varName));
                }
            }

            rule.valueMap = ruleValueMap;
            newRules.add(rule);

        }
        rules = newRules;
    }

    public void addTypeNode(String name1, String name2){
        if (!typeGraph.containsKey(name1)){
            ArrayList<String> nodes = new ArrayList<String>();
            nodes.add(name2);
            typeGraph.put(name1,nodes);
        }else{
            ArrayList<String> nodes = typeGraph.get(name1);
            if(!nodes.contains(name2)){
                nodes.add(name2);
            }
            typeGraph.put(name1,nodes);
        }
    }


    public void parseVariableValuePass(){
        for (String varName: variables.keySet()){
            Variable var = variables.get(varName);

            for(int i=0; i<var.values.size(); i++){
                String s = var.values.get(i);
                if (s.startsWith("^VAR")){
                    var.values.remove(i);
                    i--;
                    s = s.substring(4);
                    for (String val : variables.get(s).values){
                        if(!var.values.contains(val)){
                            var.values.add(val);
                        }
                    }
                }
            }
        }

    }

    // Finds and combines rules that have an impasse.
    // ONLY COMBINES A SINGLE PAIR OF RULES
    public void checkForImpasse(){
        for(Rule rule1 : this.rules){
            String guard1 = rule1.formatGuard();
            for(Rule rule2 : this.rules){
                String guard2 = rule2.formatGuard();
                // skip check if both rules are the same one
                if(rule1.ruleName.equals(rule2.ruleName)){
                    continue;
                }
                // check if both rules have the same guard condition
                if(guard1.equals(guard2)){
                    System.out.println("Combining " + rule1.ruleName.replaceAll("\\*", "-") + " and " + rule2.ruleName.replaceAll("\\*", "-"));
                    String newRuleName = "combined-" + rule1.ruleName.replaceAll("\\*", "-") + "-" + rule2.ruleName.replaceAll("\\*", "-");
                    System.out.println("Creating new rule: "+newRuleName);
                    // Create the new rule
                    this.createNewRule(newRuleName);
                    // update the values of state_operator_name
                    this.addVariableValue("state_operator_name", newRuleName);

                    // Add the variables of the old rule
                    Rule newRule = this.getRuleByName(newRuleName);
                    rule1.populateDuplicate(newRule);

                    newRule.valueMap.put("state_operator_name", newRuleName);

                    String operator1_name = "";
                    for(String key : rule1.valueMap.keySet()){

                        if(key.equals("state_operator_name")){
                            operator1_name = rule1.valueMap.get(key);
                        }
                    }
                    String operator2_name = "";
                    for(String key : rule2.valueMap.keySet()){

                        if(key.equals("state_operator_name")){
                            operator2_name = rule2.valueMap.get(key);
                        }
                    }


                    // Update rules that depend on these.
                    for(Rule r : rules){

                        for(int i=0;i < r.guards.size(); i++){
                            String guard = r.guards.get(i);
                            if(guard.equals("state_operator_name = "+operator1_name) && r.isLearningRule){
                                r.guards.set(i, "state_operator_name = "+newRuleName);
                            }else if(guard.equals("state_operator_name = "+operator2_name) && r.isLearningRule){
                                r.guards.set(i, "state_operator_name = "+newRuleName);
                            }
                        }
                    }

                    // Remove old rules
                    removeRule(rule1.ruleName);
                    removeRule(rule2.ruleName);

                    return; // ONLY COMBINE A SINGLE PAIR OF RULES
                }


            }
        }
    }

    public void removeRule(String ruleName){

        for(int i=0; i < rules.size(); i++){
            if(rules.get(i).ruleName.equals(ruleName)){
                rules.remove(i);
                break;
            }
        }

    }

    public void addVariableValue(String varName, String varValue){
        if(variables.containsKey(varName)){
            variables.get(varName).addValue(varValue);
        }else{
            Variable v = new Variable(varName);
            v.addValue(varValue);
            variables.put(varName, v);
        }
    }


    public void createNewRule(String name){
        rules.add(new Rule(name, variables));
    }

    public Rule getRuleByName(String name){
        for(Rule rule : rules){
            if(rule.ruleName.equals(name)){
                return rule;
            }
        }
        return null;
    }


}
