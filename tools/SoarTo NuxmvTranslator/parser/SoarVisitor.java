// Generated from Soar.g4 by ANTLR 4.9.2

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SoarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SoarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SoarParser#soar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSoar(SoarParser.SoarContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#soar_production}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSoar_production(SoarParser.Soar_productionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlags(SoarParser.FlagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#condition_side}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_side(SoarParser.Condition_sideContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#state_imp_cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitState_imp_cond(SoarParser.State_imp_condContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(SoarParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#positive_cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositive_cond(SoarParser.Positive_condContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#conds_for_one_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConds_for_one_id(SoarParser.Conds_for_one_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#id_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_test(SoarParser.Id_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#attr_value_tests}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_value_tests(SoarParser.Attr_value_testsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#attr_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_test(SoarParser.Attr_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#value_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue_test(SoarParser.Value_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(SoarParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#conjunctive_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunctive_test(SoarParser.Conjunctive_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#simple_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_test(SoarParser.Simple_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#multi_value_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_value_test(SoarParser.Multi_value_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#disjunction_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisjunction_test(SoarParser.Disjunction_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#relational_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_test(SoarParser.Relational_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(SoarParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#single_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_test(SoarParser.Single_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SoarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(SoarParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#action_side}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction_side(SoarParser.Action_sideContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(SoarParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SoarParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#func_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(SoarParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#func_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_name(SoarParser.Func_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SoarParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#attr_value_make}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_value_make(SoarParser.Attr_value_makeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#variable_or_sym_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_or_sym_constant(SoarParser.Variable_or_sym_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#value_make}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue_make(SoarParser.Value_makeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#pref_specifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPref_specifier(SoarParser.Pref_specifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#unary_pref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_pref(SoarParser.Unary_prefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#unary_or_binary_pref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_or_binary_pref(SoarParser.Unary_or_binary_prefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SoarParser#sym_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSym_constant(SoarParser.Sym_constantContext ctx);
}