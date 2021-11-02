import java.util.ArrayList;
import java.util.LinkedHashMap;
public class SoarRules{
    ArrayList<Rule> rules;
    LinkedHashMap<String, Variable> variables;
    public SoarRules(){
        rules = new ArrayList<Rule>();
        variables = new LinkedHashMap<String, Variable>();
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
