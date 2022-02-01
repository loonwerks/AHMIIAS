import parser.*;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link SoarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <Object> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class Visitor<Object> extends AbstractParseTreeVisitor<Object> implements SoarVisitor<Object> {
    Rule currentRule;
    String currentContext = "";
    boolean negateCondition = false;

    public SoarRules rules;
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitSoar(SoarParser.SoarContext ctx) {


        return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
    // runs when parser encounters a new production
	@Override public Object visitSoar_production(SoarParser.Soar_productionContext ctx) {
        // create a new rule
        String ruleName = ctx.sym_constant().getText();
        boolean currentIsElaboration;
        if(ruleName.contains("elaborate")){
            currentIsElaboration = true;
        }else{
            currentIsElaboration = false;
        }
        //System.out.println(ruleName);
        rules.createNewRule(ruleName);


        // get all the statements that precede the arrow (-->)
        SoarParser.Condition_sideContext conditions = ctx.condition_side();

        currentRule = rules.getRuleByName(ruleName);
        currentRule.isElaboration = currentIsElaboration;
        // sp{ ruleName
        visit(ctx.condition_side());
        // -->
        visit(ctx.action_side());
        //}
        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFlags(SoarParser.FlagsContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitCondition_side(SoarParser.Condition_sideContext ctx) {
        // Visit the first guard statement (state <s> ...)
        visit(ctx.state_imp_cond());


        // Visit the rest of the guards (if any)
        List<SoarParser.CondContext> guards = ctx.cond();
        for(SoarParser.CondContext guard : guards){
            visit(guard);
        }



        return null; }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
     // assumes first guard starts with (state <s> ...)
	@Override public Object visitState_imp_cond(SoarParser.State_imp_condContext ctx) {
        //System.out.println(ctx.getText());
        // (state <s> ...)
        String stateKeyword = ctx.STATE().getSymbol().getText();
        currentContext = stateKeyword;
        // get '<s>'
        String var = (String)visit(ctx.id_test().test().simple_test());

        //System.out.println(var);
        currentRule.addContext(var, currentContext);

        for(SoarParser.Attr_value_testsContext attribute : ctx.attr_value_tests()){
            visit(attribute);
        }



        return null;


    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitCond(SoarParser.CondContext ctx) {
        negateCondition = ctx.Negative_pref() != null;
        visit(ctx.positive_cond());

        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitPositive_cond(SoarParser.Positive_condContext ctx) {
        visit(ctx.conds_for_one_id());
        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitConds_for_one_id(SoarParser.Conds_for_one_idContext ctx) {

        // get '<var>'
        String var = (String)visit(ctx.id_test().test().simple_test());
        currentContext = currentRule.getContext(var);
        //System.out.println(var);


        for(SoarParser.Attr_value_testsContext attribute : ctx.attr_value_tests()){
            visit(attribute);
        }

        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitId_test(SoarParser.Id_testContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAttr_value_tests(SoarParser.Attr_value_testsContext ctx) {
        //System.out.println(ctx.getText());
        String line = ctx.getText();
        //System.out.println(line);
        String variable = ctx.attr_test(0).getText();
        variable = currentContext+"_"+variable;
        variable = cleanVariableName(variable);
        //System.out.println(currentContext+"_"+variable);


        // get value check
        List<SoarParser.Value_testContext> values = ctx.value_test();
        boolean existsCheck = values.size()==0;

        if (!variable.equals("state_io") && !variable.equals("state_operator")){
            currentRule.addVariable(variable);
            rules.addVariableValue(variable, "nil");
        }

        // Attribute has no value, so guard is checking for existance
        if(existsCheck){
            if(ctx.Negative_pref() != null){ // check for not existing
                currentRule.addGuard(variable+" = nil");
            }else{ // check for presence
                currentRule.addGuard(variable+" != nil");
            }
        }else{ // Attribute has a value
            String value = (String)visit(values.get(0));

            //System.out.println(variable);


            if(!(value.startsWith("<") && value.endsWith(">"))){
                // Removes the conditional check from value
                String onlyValue = value.replaceAll("<= ", "").replaceAll(">= ", "").replaceAll("= ", "");
                onlyValue = onlyValue.replaceAll("< ", "").replaceAll("> ", "");

                // Don't add inequalities to the list of values a variable can have
                if(onlyValue.equals(value)){
                    rules.addVariableValue(variable, onlyValue);
                }else{
                    // It is an inequality, hence we can use it for setting Types
                    // Example: ^var1 <= <v2>, where <v2> is ^var2
                    // Hence, type of ^var1 is the same as ^var2
                    String leftSide = variable;
                    String rightSide = currentRule.getContext(onlyValue);
                    //System.out.println(leftSide);
                    //System.out.println(rightSide);
                    rules.addTypeNode(leftSide, rightSide);
                    rules.addTypeNode(rightSide, leftSide);

                }

                for(String key : currentRule.contextMap.keySet()){
                    if(value.contains(key)){
                        value = value.replaceAll(key, currentRule.contextMap.get(key));
                        break;
                    }
                }

                if(value.contains("<") || value.contains(">")){
                    currentRule.addGuard(variable+" "+value);
                }else{
                    if(ctx.Negative_pref() != null ^ negateCondition){
                        currentRule.addGuard(variable+" != "+value);

                        if(rules.variables.containsKey(value)){
                            String leftSide = variable;
                            String rightSide = value;
                            //System.out.println(leftSide);
                            //System.out.println(rightSide);
                            rules.addTypeNode(leftSide, rightSide);
                            rules.addTypeNode(rightSide, leftSide);
                        }


                    }else{
                        currentRule.addGuard(variable+" = "+value);

                    }
                }
            }else{

                // check for negation

                // Negate if one negation exists, if two exist, then don't
                /*if(ctx.Negative_pref() != null ^ negateCondition){

                    value = "!" + value;
                    currentRule.addContext(value, variable);
                }else{
                    currentRule.addContext(value, variable);
                }*/
                if(!currentRule.contextMap.containsKey(value)){
                    // assignment
                    currentRule.addContext(value, variable);
                }else{
                    // equality check
                    String leftSide = variable;
                    value = currentRule.getContext(value);
                    String rightSide = value;
                    //System.out.println(leftSide);
                    //System.out.println(rightSide);
                    rules.addTypeNode(leftSide, rightSide);
                    rules.addTypeNode(rightSide, leftSide);


                    if(ctx.Negative_pref() != null ^ negateCondition){
                        currentRule.addGuard(variable+" != "+value);
                    }else{
                        currentRule.addGuard(variable+" = "+value);
                    }

                }
            }
        }

        return null;
    }


    public String cleanVariableName(String s){
        s=s.replaceAll("\\_output\\-link", "");
        s=s.replaceAll("\\_input\\-link", "");
        s=s.replaceAll("\\_flightdata", "");
        return s;
    }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAttr_test(SoarParser.Attr_testContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitValue_test(SoarParser.Value_testContext ctx) {
        SoarParser.TestContext testContext = ctx.test();
        if(testContext == null){
            return null;
        }
        return visit(testContext);

     }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitTest(SoarParser.TestContext ctx) {
        // Case 1: it is a 'simple test'
        if(ctx.simple_test() != null){
            return visit(ctx.simple_test());
        }

        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
     // Not translated
	@Override public Object visitConjunctive_test(SoarParser.Conjunctive_testContext ctx) {
        System.err.println("Conjunctive test not supported in the following "+ctx.getText());
        return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitSimple_test(SoarParser.Simple_testContext ctx) {
        if(ctx.relational_test() != null){
            return visit(ctx.relational_test());
        }

        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitMulti_value_test(SoarParser.Multi_value_testContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitDisjunction_test(SoarParser.Disjunction_testContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitRelational_test(SoarParser.Relational_testContext ctx) {
        String output = "";
        if(ctx.relation() != null){
            output += (String)visit(ctx.relation());
        }


        if(ctx.single_test() != null){
            output += (String)visit(ctx.single_test());
        }


        return (Object)output;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitRelation(SoarParser.RelationContext ctx) {
        return (Object)(ctx.getText()+" ");
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitSingle_test(SoarParser.Single_testContext ctx) {

        return visitChildren(ctx);

    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitVariable(SoarParser.VariableContext ctx) {
        // return variable name
        return (Object)("<"+ctx.sym_constant().Sym_constant().getSymbol().getText()+">");

    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitConstant(SoarParser.ConstantContext ctx) {
        if (ctx.sym_constant() != null){
            return (Object)ctx.sym_constant().Sym_constant().getSymbol().getText();
        }
        if(ctx.Int_constant() != null){
            return (Object)ctx.Int_constant().getSymbol().getText();
        }
        if(ctx.Float_constant() != null){
            return (Object)ctx.Float_constant().getSymbol().getText();
        }
        if(ctx.Print_string() != null){
            return (Object)ctx.Print_string().getSymbol().getText();
        }
        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAction_side(SoarParser.Action_sideContext ctx) {
        if(ctx.action() != null){
            for(SoarParser.ActionContext action : ctx.action()){
                visit(action);
            }
        }

        return null; }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAction(SoarParser.ActionContext ctx) {
        // context
        String contextVar = (String)visit(ctx.variable());
        currentContext = currentRule.getContext(contextVar);
        List<SoarParser.Attr_value_makeContext> attributes = ctx.attr_value_make();
        for(SoarParser.Attr_value_makeContext attribute : attributes){
            visit(attribute);
        }
        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitPrint(SoarParser.PrintContext ctx) {

        return visitChildren(ctx);
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFunc_call(SoarParser.Func_callContext ctx) {

        return visitChildren(ctx);
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFunc_name(SoarParser.Func_nameContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitValue(SoarParser.ValueContext ctx) {

        return visitChildren(ctx);
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAttr_value_make(SoarParser.Attr_value_makeContext ctx) {
        String attributeName = (String)visit(ctx.variable_or_sym_constant(0).sym_constant());
        String variable = currentContext+"_"+attributeName;
        variable = cleanVariableName(variable);
        SoarParser.Value_makeContext value = ctx.value_make();
        currentContext = variable;
        String val = (String)visit(value);

        if(val == null){
            return null;
        }
        //System.out.println(val);
        if(val.startsWith("<") && val.endsWith(">")){
            //System.out.println(variable);
            //System.out.println(val);
            /* If variable value is aready in the context map and its identifier differs
                Then, treat it as an assignment statement.
                Example (<s> ^value <val>)-->(<s> ^attribute <val>) is doing ^attribute := ^value
            */
            if(currentRule.contextMap.containsKey(val) && !currentRule.getContext(val).equals(variable)){
                currentRule.addAttrValue(variable, currentRule.getContext(val));
                rules.addVariableValue(variable, "^VAR"+currentRule.getContext(val));

                String leftSide = variable;
                String rightSide = currentRule.getContext(val);
                //System.out.println(leftSide);
                //System.out.println(rightSide);
                rules.addTypeNode(leftSide, rightSide);
                rules.addTypeNode(rightSide, leftSide);

            }else{
                currentRule.addContext(val, variable);
            }

        }else{
            if(currentRule.isElaboration && currentRule.guards.size() == 1 && currentRule.guards.get(0).equals("state_superstate = nil")){
                rules.variables.get(variable).initialValue = val;
            }
            currentRule.addAttrValue(variable, val);
            rules.addVariableValue(variable, val);
        }

        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitVariable_or_sym_constant(SoarParser.Variable_or_sym_constantContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitValue_make(SoarParser.Value_makeContext ctx) {
        String value = "";
        // get the value of the attribute Example: <o> in (<s> ^operator <o> +)
        value = (String)visit(ctx.value(0));
        String pref_specifier = null;
        // visit the preference specifier Example = 0.0 in (<s> ^opetator <o> = 0.0)
        if(ctx.pref_specifier(0) != null){
            pref_specifier = (String)visit(ctx.pref_specifier(0));

            // check for second preference specifier Example = in (<s> ^opetator <o> >,=)
            if(ctx.pref_specifier(1) != null){
                pref_specifier += ","+(String)visit(ctx.pref_specifier(1));
            }

            //System.out.println(currentContext);
            //System.out.println(value +" " +pref_specifier);


        }

        if(currentContext.equals("state_operator") && pref_specifier != null){
            // Check if rule is a learning Rule (<s> ^operator <o> = 0.0)
            //System.out.println(pref_specifier);
            // check if rule is a learning rule
            if(pref_specifier.contains("=")){
                currentRule.isLearningRule = true;
            }
            // if it is a learning rule, get the priority
            if(pref_specifier.startsWith("=")){


                // set priority value
                try{
                    currentRule.priority = Double.parseDouble(pref_specifier.substring(1));
                }catch(Exception e){e.printStackTrace();}
                // Search for the name of the operator of which we are setting the preference.
                String op_name = "";
                for(String guard : currentRule.guards){

                    if(guard.startsWith("state_operator_name = ")){
                        op_name = guard.substring("state_operator_name = ".length());
                    }
                }
                currentRule.addAttrValue("state_operator_name", op_name);
            // check if rule has max priority
            }else if(pref_specifier.contains(">")){
                // set priority value
                currentRule.priority = 10_000;
                // Search for the name of the operator of which we are setting the preference.
                String op_name = "";
                for(String guard : currentRule.guards){

                    if(guard.startsWith("state_operator_name = ")){
                        op_name = guard.substring("state_operator_name = ".length());
                    }
                }
                currentRule.addAttrValue("state_operator_name", op_name);
            }
        }


        // get the value of the attribute when using a compressed notation Example: new_val in (<s> ^value old_val - new_val +)
        if(ctx.value(1) != null){
            value = (String)visit(ctx.value(1));
        }
        if(pref_specifier !=null && pref_specifier.equals("-")){
            value = "nil";
        }


        return (Object)value;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitPref_specifier(SoarParser.Pref_specifierContext ctx) {
        //System.out.println(ctx.getText());
        if (ctx.unary_pref() != null){
            return visit(ctx.unary_pref());
        }
        if (ctx.unary_or_binary_pref() != null){
            String pref = (String)visit(ctx.unary_or_binary_pref());
            if(ctx.value() != null){
                String val = (String)visit(ctx.value());
                return (Object)(pref + " " + val);
            }
            return (Object)pref;
        }

        return null;

    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitUnary_pref(SoarParser.Unary_prefContext ctx) {
        if(ctx.Positive_pref() != null){
            return (Object)(ctx.Positive_pref().getSymbol().getText());
        }
        if(ctx.Negative_pref() != null){
            return (Object)(ctx.Negative_pref().getSymbol().getText());
        }
        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitUnary_or_binary_pref(SoarParser.Unary_or_binary_prefContext ctx) {

        if(ctx.Less_than_pref() != null){
            return (Object)(ctx.Less_than_pref().getSymbol().getText());
        }
        if(ctx.Equal_pref() != null){
            return (Object)(ctx.Equal_pref().getSymbol().getText());
        }
        if(ctx.Greater_than_pref() != null){
            return (Object)(ctx.Greater_than_pref().getSymbol().getText());
        }
        if(ctx.And_pref() != null){
            return (Object)(ctx.And_pref().getSymbol().getText());
        }



        return null;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitSym_constant(SoarParser.Sym_constantContext ctx) {
        return (Object)(ctx.Sym_constant().getSymbol().getText());
    }
}
