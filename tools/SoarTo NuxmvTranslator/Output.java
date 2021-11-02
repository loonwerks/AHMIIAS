import java.util.LinkedHashMap;
public class Output{
    SoarRules rules;
    public Output(SoarRules rules){
        this.rules = rules;
    }

    public String generateOutput(){
        String output = "MODULE main\n\n";
        rules.checkForImpasse();
        output += generateVariableDeclarations();
        output += generateAssignStatements();
        output += generateSoarAgentHeader();
        output += generateSoarRulesModuleHeader();
        output += generateRuleAssignmentStatements();
        // Debug print guard conditions
        //output += printDebugGuards();
        //output += printDebugVariableValues();
        output += generateOperatorTransStatements();
        output += generateTransStatements();
        return output;
    }
    public String generateOperatorTransStatements(){
        String output = "TRANS\n        next (state_operator_name) =\n            case\n\n";
        int numStatements = 0;
        for(Rule rule : rules.rules){
            if(!rule.valueMap.containsKey("state_operator_name")){
                continue;
            }
            numStatements++;
        }
        String statements[] = new String[numStatements];
        double priorities[] = new double[numStatements];
        for(Rule rule : rules.rules){
            if(!rule.valueMap.containsKey("state_operator_name")){
                continue;
            }
            numStatements--;
            statements[numStatements] = "";
            statements[numStatements] += "        --" + rule.ruleName + " (" + rule.listVariables() + ")\n";
            statements[numStatements] += "        (state = run & "+ rule.formatGuard() +"): " + rule.valueMap.get("state_operator_name") + ";\n";
            priorities[numStatements] = rule.priority;
        }
        // Sort based on priorities
        for(int i=0;i < priorities.length; i++){
            double max = priorities[i];
            int index = i;
            for(int j=i; j < priorities.length; j++){
                if(priorities[j] > max){
                    max = priorities[j];
                    index = j;
                }
            }
            String tempStm;
            double tempVal;
            tempStm = statements[index];
            tempVal = priorities[index];
            statements[index] = statements[i];
            priorities[index] = priorities[i];
            statements[i] = tempStm;
            priorities[i] = tempVal;

        }
        // output ordered statements
        for(int i=0;i < statements.length; i++){
            if(priorities[i] != 0.0){
                output += "        -- " + priorities[i];
            }
            output += statements[i];


        }

        output += "            TRUE : state_operator_name;\n            esac;\n";
        return output;
    }
    public String generateTransStatements(){
        String output = "";
        for(String variable : rules.variables.keySet()){
            String varTrans = "";
            for(Rule rule : rules.rules){
                for(String key : rule.valueMap.keySet()){
                    if(key.equals("state_operator_name") || !key.equals(variable)) continue;
                    varTrans += "        --" + rule.ruleName + " (" + rule.listVariables() + ")\n";
                    varTrans += "        (state = run & "+ rule.formatGuard() +"): " + rule.valueMap.get(key) + ";\n";
                }
            }
            if(varTrans.length() > 0){
                output += "TRANS\n        next (" + variable + ") =\n            case\n\n";
                output += varTrans;
                output += "            TRUE : " + variable + ";\n                esac;\n";
            }
        }
        return output;
    }


    public String generateRuleAssignmentStatements(){
        String output = "VAR state:{start, run};\nASSIGN\n";
        output += "        init (state) := start;\n";
        output += "        next (state) :=\n            case\n";
        for(Rule rule : rules.rules){
            rule.formatGuard();
            // format comment
            output += "        --" + rule.ruleName + " (" + rule.listVariables() + ")\n";
            // format assign statement with guard conditions from rule
            output += "        state = start & (" + rule.formatGuard() + ") : run;\n";
        }
        output += "        state = run : start;\n            TRUE : state;\n            esac;";
        return output+"\n";
    }
    public String generateSoarAgentHeader(){
        String output = "VAR soarAgent : soarRules(";
        // list out all variables used in the soarAgent
        boolean first = true;
        for(Variable var : rules.variables.values()){
            if(!first){
                output += ",";
            }else{
                first = false;
            }
            output += var.name;
        }
        output += ");\n\n";
        return output;
    }
    public String generateSoarRulesModuleHeader(){
        String output = "MODULE soarRules(";
        // list out all variables used in the soarAgent
        boolean first = true;
        for(Variable var : rules.variables.values()){
            if(!first){
                output += ",";
            }else{
                first = false;
            }
            output += var.name;
        }
        output += ")\n\n";
        return output;
    }

    public String printDebugGuards(){
        String output = "";
        for(Rule rule : rules.rules){
            for(String guard : rule.guards){
                output += "DEBUG: " + guard+"\n";
            }
        }
        return output;
    }

    public String printDebugVariableValues(){
        String output = "";
        for(Rule rule : rules.rules){
            for(String key : rule.valueMap.keySet()){
                output += "DEBUG: " + key + " : " + rule.valueMap.get(key) + "\n";
            }
        }
        return output;
    }

    public String generateAssignStatements(){
        String output = "ASSIGN\n";
        for(Variable var : rules.variables.values()){
            if(var.values.size() <= 1){
                continue;
            }
            output += "    init ("+var.name+") := ";
            switch(var.varType){
                case Variable.S_CONST:
                    output += "nil;\n";
                    break;
                case Variable.INT:
                    output += "0;\n";
                    break;
                case Variable.FLOAT:
                    output += "0.0;\n";
                    break;
                default:
                    output += "TYPE ERROR\n";
                    System.err.println("TYPE ERROR");
                    break;
            }
        }
        return output+"\n\n";
    }

    public String generateVariableDeclarations(){
        String output = "";
        for(Variable var : rules.variables.values()){
            var.generateType();
            output += "VAR " + var.name + " : ";
            if(var.varType == Variable.S_CONST){
                output += "{";
                boolean first = true;
                for(String varVal : var.values){
                    if(!first){
                        output += ",";
                    }else{
                        first = false;
                    }
                    output += varVal;
                }
                output += "};\n";
            }else if(var.varType == Variable.INT){
                output += "integer;\n";
            }else if(var.varType == Variable.FLOAT){
                output += "real;\n";
            }else{
                output += "TYPE ERROR";
                System.err.println("TYPE ERROR with variable "+var.name);
            }
        }
        return output+"\n\n";
    }

}
