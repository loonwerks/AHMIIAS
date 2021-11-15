// Generated from Soar.g4 by ANTLR 4.9.2

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SoarParser}.
 */
public interface SoarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SoarParser#soar}.
	 * @param ctx the parse tree
	 */
	void enterSoar(SoarParser.SoarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#soar}.
	 * @param ctx the parse tree
	 */
	void exitSoar(SoarParser.SoarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#soar_production}.
	 * @param ctx the parse tree
	 */
	void enterSoar_production(SoarParser.Soar_productionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#soar_production}.
	 * @param ctx the parse tree
	 */
	void exitSoar_production(SoarParser.Soar_productionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#flags}.
	 * @param ctx the parse tree
	 */
	void enterFlags(SoarParser.FlagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#flags}.
	 * @param ctx the parse tree
	 */
	void exitFlags(SoarParser.FlagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#condition_side}.
	 * @param ctx the parse tree
	 */
	void enterCondition_side(SoarParser.Condition_sideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#condition_side}.
	 * @param ctx the parse tree
	 */
	void exitCondition_side(SoarParser.Condition_sideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#state_imp_cond}.
	 * @param ctx the parse tree
	 */
	void enterState_imp_cond(SoarParser.State_imp_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#state_imp_cond}.
	 * @param ctx the parse tree
	 */
	void exitState_imp_cond(SoarParser.State_imp_condContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(SoarParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(SoarParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#positive_cond}.
	 * @param ctx the parse tree
	 */
	void enterPositive_cond(SoarParser.Positive_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#positive_cond}.
	 * @param ctx the parse tree
	 */
	void exitPositive_cond(SoarParser.Positive_condContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#conds_for_one_id}.
	 * @param ctx the parse tree
	 */
	void enterConds_for_one_id(SoarParser.Conds_for_one_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#conds_for_one_id}.
	 * @param ctx the parse tree
	 */
	void exitConds_for_one_id(SoarParser.Conds_for_one_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#id_test}.
	 * @param ctx the parse tree
	 */
	void enterId_test(SoarParser.Id_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#id_test}.
	 * @param ctx the parse tree
	 */
	void exitId_test(SoarParser.Id_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#attr_value_tests}.
	 * @param ctx the parse tree
	 */
	void enterAttr_value_tests(SoarParser.Attr_value_testsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#attr_value_tests}.
	 * @param ctx the parse tree
	 */
	void exitAttr_value_tests(SoarParser.Attr_value_testsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#attr_test}.
	 * @param ctx the parse tree
	 */
	void enterAttr_test(SoarParser.Attr_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#attr_test}.
	 * @param ctx the parse tree
	 */
	void exitAttr_test(SoarParser.Attr_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#value_test}.
	 * @param ctx the parse tree
	 */
	void enterValue_test(SoarParser.Value_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#value_test}.
	 * @param ctx the parse tree
	 */
	void exitValue_test(SoarParser.Value_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(SoarParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(SoarParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#conjunctive_test}.
	 * @param ctx the parse tree
	 */
	void enterConjunctive_test(SoarParser.Conjunctive_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#conjunctive_test}.
	 * @param ctx the parse tree
	 */
	void exitConjunctive_test(SoarParser.Conjunctive_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#simple_test}.
	 * @param ctx the parse tree
	 */
	void enterSimple_test(SoarParser.Simple_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#simple_test}.
	 * @param ctx the parse tree
	 */
	void exitSimple_test(SoarParser.Simple_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#multi_value_test}.
	 * @param ctx the parse tree
	 */
	void enterMulti_value_test(SoarParser.Multi_value_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#multi_value_test}.
	 * @param ctx the parse tree
	 */
	void exitMulti_value_test(SoarParser.Multi_value_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#disjunction_test}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction_test(SoarParser.Disjunction_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#disjunction_test}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction_test(SoarParser.Disjunction_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#relational_test}.
	 * @param ctx the parse tree
	 */
	void enterRelational_test(SoarParser.Relational_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#relational_test}.
	 * @param ctx the parse tree
	 */
	void exitRelational_test(SoarParser.Relational_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(SoarParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(SoarParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#single_test}.
	 * @param ctx the parse tree
	 */
	void enterSingle_test(SoarParser.Single_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#single_test}.
	 * @param ctx the parse tree
	 */
	void exitSingle_test(SoarParser.Single_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(SoarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(SoarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(SoarParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(SoarParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#action_side}.
	 * @param ctx the parse tree
	 */
	void enterAction_side(SoarParser.Action_sideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#action_side}.
	 * @param ctx the parse tree
	 */
	void exitAction_side(SoarParser.Action_sideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(SoarParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(SoarParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(SoarParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(SoarParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(SoarParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(SoarParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#func_name}.
	 * @param ctx the parse tree
	 */
	void enterFunc_name(SoarParser.Func_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#func_name}.
	 * @param ctx the parse tree
	 */
	void exitFunc_name(SoarParser.Func_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SoarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SoarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#attr_value_make}.
	 * @param ctx the parse tree
	 */
	void enterAttr_value_make(SoarParser.Attr_value_makeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#attr_value_make}.
	 * @param ctx the parse tree
	 */
	void exitAttr_value_make(SoarParser.Attr_value_makeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#variable_or_sym_constant}.
	 * @param ctx the parse tree
	 */
	void enterVariable_or_sym_constant(SoarParser.Variable_or_sym_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#variable_or_sym_constant}.
	 * @param ctx the parse tree
	 */
	void exitVariable_or_sym_constant(SoarParser.Variable_or_sym_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#value_make}.
	 * @param ctx the parse tree
	 */
	void enterValue_make(SoarParser.Value_makeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#value_make}.
	 * @param ctx the parse tree
	 */
	void exitValue_make(SoarParser.Value_makeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#pref_specifier}.
	 * @param ctx the parse tree
	 */
	void enterPref_specifier(SoarParser.Pref_specifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#pref_specifier}.
	 * @param ctx the parse tree
	 */
	void exitPref_specifier(SoarParser.Pref_specifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#unary_pref}.
	 * @param ctx the parse tree
	 */
	void enterUnary_pref(SoarParser.Unary_prefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#unary_pref}.
	 * @param ctx the parse tree
	 */
	void exitUnary_pref(SoarParser.Unary_prefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#unary_or_binary_pref}.
	 * @param ctx the parse tree
	 */
	void enterUnary_or_binary_pref(SoarParser.Unary_or_binary_prefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#unary_or_binary_pref}.
	 * @param ctx the parse tree
	 */
	void exitUnary_or_binary_pref(SoarParser.Unary_or_binary_prefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SoarParser#sym_constant}.
	 * @param ctx the parse tree
	 */
	void enterSym_constant(SoarParser.Sym_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link SoarParser#sym_constant}.
	 * @param ctx the parse tree
	 */
	void exitSym_constant(SoarParser.Sym_constantContext ctx);
}