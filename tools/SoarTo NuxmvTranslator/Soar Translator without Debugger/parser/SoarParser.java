// Generated from Soar.g4 by ANTLR 4.9.2

    package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SoarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, Documentation=31, 
		Print_string=32, STATE=33, Positive_pref=34, Negative_pref=35, Less_than_pref=36, 
		Equal_pref=37, Greater_than_pref=38, And_pref=39, Sym_constant=40, Int_constant=41, 
		Float_constant=42, WS=43, COMMENT=44;
	public static final int
		RULE_soar = 0, RULE_soar_production = 1, RULE_flags = 2, RULE_condition_side = 3, 
		RULE_state_imp_cond = 4, RULE_cond = 5, RULE_positive_cond = 6, RULE_conds_for_one_id = 7, 
		RULE_id_test = 8, RULE_attr_value_tests = 9, RULE_attr_test = 10, RULE_value_test = 11, 
		RULE_test = 12, RULE_conjunctive_test = 13, RULE_simple_test = 14, RULE_multi_value_test = 15, 
		RULE_disjunction_test = 16, RULE_relational_test = 17, RULE_relation = 18, 
		RULE_single_test = 19, RULE_variable = 20, RULE_constant = 21, RULE_action_side = 22, 
		RULE_action = 23, RULE_print = 24, RULE_func_call = 25, RULE_func_name = 26, 
		RULE_value = 27, RULE_attr_value_make = 28, RULE_variable_or_sym_constant = 29, 
		RULE_value_make = 30, RULE_pref_specifier = 31, RULE_unary_pref = 32, 
		RULE_unary_or_binary_pref = 33, RULE_sym_constant = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"soar", "soar_production", "flags", "condition_side", "state_imp_cond", 
			"cond", "positive_cond", "conds_for_one_id", "id_test", "attr_value_tests", 
			"attr_test", "value_test", "test", "conjunctive_test", "simple_test", 
			"multi_value_test", "disjunction_test", "relational_test", "relation", 
			"single_test", "variable", "constant", "action_side", "action", "print", 
			"func_call", "func_name", "value", "attr_value_make", "variable_or_sym_constant", 
			"value_make", "pref_specifier", "unary_pref", "unary_or_binary_pref", 
			"sym_constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'sp'", "'gp'", "'{'", "'-->'", "'}'", "':'", "'o-support'", "'i-support'", 
			"'chunk'", "'default'", "'template'", "'('", "'impasse'", "')'", "'^'", 
			"'.'", "'['", "']'", "'<<'", "'>>'", "'<>'", "'<='", "'>='", "'=='", 
			"'<=>'", "'write '", "'(crlf)'", "'*'", "'/'", "','", null, null, "'state'", 
			"'+'", "'-'", "'<'", "'='", "'>'", "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "Documentation", "Print_string", 
			"STATE", "Positive_pref", "Negative_pref", "Less_than_pref", "Equal_pref", 
			"Greater_than_pref", "And_pref", "Sym_constant", "Int_constant", "Float_constant", 
			"WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Soar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SoarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SoarContext extends ParserRuleContext {
		public List<Soar_productionContext> soar_production() {
			return getRuleContexts(Soar_productionContext.class);
		}
		public Soar_productionContext soar_production(int i) {
			return getRuleContext(Soar_productionContext.class,i);
		}
		public SoarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_soar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterSoar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitSoar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitSoar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SoarContext soar() throws RecognitionException {
		SoarContext _localctx = new SoarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_soar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				soar_production();
				}
				}
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 || _la==T__1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Soar_productionContext extends ParserRuleContext {
		public Sym_constantContext sym_constant() {
			return getRuleContext(Sym_constantContext.class,0);
		}
		public Condition_sideContext condition_side() {
			return getRuleContext(Condition_sideContext.class,0);
		}
		public Action_sideContext action_side() {
			return getRuleContext(Action_sideContext.class,0);
		}
		public TerminalNode Documentation() { return getToken(SoarParser.Documentation, 0); }
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public Soar_productionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_soar_production; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterSoar_production(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitSoar_production(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitSoar_production(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Soar_productionContext soar_production() throws RecognitionException {
		Soar_productionContext _localctx = new Soar_productionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_soar_production);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(76);
			match(T__2);
			setState(77);
			sym_constant();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Documentation) {
				{
				setState(78);
				match(Documentation);
				}
			}

			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(81);
				flags();
				}
			}

			setState(84);
			condition_side();
			setState(85);
			match(T__3);
			setState(86);
			action_side();
			setState(87);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlagsContext extends ParserRuleContext {
		public FlagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterFlags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitFlags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitFlags(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlagsContext flags() throws RecognitionException {
		FlagsContext _localctx = new FlagsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__5);
			setState(90);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Condition_sideContext extends ParserRuleContext {
		public State_imp_condContext state_imp_cond() {
			return getRuleContext(State_imp_condContext.class,0);
		}
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public Condition_sideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterCondition_side(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitCondition_side(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitCondition_side(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Condition_sideContext condition_side() throws RecognitionException {
		Condition_sideContext _localctx = new Condition_sideContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_condition_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			state_imp_cond();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << Negative_pref))) != 0)) {
				{
				{
				setState(93);
				cond();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class State_imp_condContext extends ParserRuleContext {
		public TerminalNode STATE() { return getToken(SoarParser.STATE, 0); }
		public Id_testContext id_test() {
			return getRuleContext(Id_testContext.class,0);
		}
		public List<Attr_value_testsContext> attr_value_tests() {
			return getRuleContexts(Attr_value_testsContext.class);
		}
		public Attr_value_testsContext attr_value_tests(int i) {
			return getRuleContext(Attr_value_testsContext.class,i);
		}
		public State_imp_condContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state_imp_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterState_imp_cond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitState_imp_cond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitState_imp_cond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final State_imp_condContext state_imp_cond() throws RecognitionException {
		State_imp_condContext _localctx = new State_imp_condContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_state_imp_cond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__11);
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==T__12 || _la==STATE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << Print_string) | (1L << STATE) | (1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref) | (1L << Sym_constant) | (1L << Int_constant) | (1L << Float_constant))) != 0)) {
				{
				setState(101);
				id_test();
				}
			}

			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104);
				attr_value_tests();
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__14 || _la==Negative_pref );
			setState(109);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public Positive_condContext positive_cond() {
			return getRuleContext(Positive_condContext.class,0);
		}
		public TerminalNode Negative_pref() { return getToken(SoarParser.Negative_pref, 0); }
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cond);
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				positive_cond();
				}
				break;
			case Negative_pref:
				enterOuterAlt(_localctx, 2);
				{
				{
				{
				setState(112);
				match(Negative_pref);
				}
				setState(113);
				positive_cond();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Positive_condContext extends ParserRuleContext {
		public Conds_for_one_idContext conds_for_one_id() {
			return getRuleContext(Conds_for_one_idContext.class,0);
		}
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public Positive_condContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positive_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterPositive_cond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitPositive_cond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitPositive_cond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Positive_condContext positive_cond() throws RecognitionException {
		Positive_condContext _localctx = new Positive_condContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_positive_cond);
		int _la;
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				conds_for_one_id();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(117);
				match(T__2);
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(118);
					cond();
					}
					}
					setState(121); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << Negative_pref))) != 0) );
				setState(123);
				match(T__4);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conds_for_one_idContext extends ParserRuleContext {
		public Id_testContext id_test() {
			return getRuleContext(Id_testContext.class,0);
		}
		public List<Attr_value_testsContext> attr_value_tests() {
			return getRuleContexts(Attr_value_testsContext.class);
		}
		public Attr_value_testsContext attr_value_tests(int i) {
			return getRuleContext(Attr_value_testsContext.class,i);
		}
		public TerminalNode STATE() { return getToken(SoarParser.STATE, 0); }
		public Conds_for_one_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conds_for_one_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterConds_for_one_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitConds_for_one_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitConds_for_one_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conds_for_one_idContext conds_for_one_id() throws RecognitionException {
		Conds_for_one_idContext _localctx = new Conds_for_one_idContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_conds_for_one_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__11);
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(128);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==STATE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(131);
			id_test();
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				attr_value_tests();
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__14 || _la==Negative_pref );
			setState(137);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_testContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Id_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterId_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitId_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitId_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Id_testContext id_test() throws RecognitionException {
		Id_testContext _localctx = new Id_testContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_id_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attr_value_testsContext extends ParserRuleContext {
		public List<Attr_testContext> attr_test() {
			return getRuleContexts(Attr_testContext.class);
		}
		public Attr_testContext attr_test(int i) {
			return getRuleContext(Attr_testContext.class,i);
		}
		public TerminalNode Negative_pref() { return getToken(SoarParser.Negative_pref, 0); }
		public List<Value_testContext> value_test() {
			return getRuleContexts(Value_testContext.class);
		}
		public Value_testContext value_test(int i) {
			return getRuleContext(Value_testContext.class,i);
		}
		public Attr_value_testsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr_value_tests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterAttr_value_tests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitAttr_value_tests(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitAttr_value_tests(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attr_value_testsContext attr_value_tests() throws RecognitionException {
		Attr_value_testsContext _localctx = new Attr_value_testsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_attr_value_tests);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Negative_pref) {
				{
				setState(141);
				match(Negative_pref);
				}
			}

			setState(144);
			match(T__14);
			setState(145);
			attr_test();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(146);
				match(T__15);
				setState(147);
				attr_test();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__16) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << Print_string) | (1L << STATE) | (1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref) | (1L << Sym_constant) | (1L << Int_constant) | (1L << Float_constant))) != 0)) {
				{
				{
				setState(153);
				value_test();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attr_testContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Attr_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterAttr_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitAttr_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitAttr_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attr_testContext attr_test() throws RecognitionException {
		Attr_testContext _localctx = new Attr_testContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attr_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Value_testContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode Positive_pref() { return getToken(SoarParser.Positive_pref, 0); }
		public Conds_for_one_idContext conds_for_one_id() {
			return getRuleContext(Conds_for_one_idContext.class,0);
		}
		public Value_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterValue_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitValue_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitValue_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Value_testContext value_test() throws RecognitionException {
		Value_testContext _localctx = new Value_testContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_value_test);
		int _la;
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__16:
			case T__18:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case Print_string:
			case STATE:
			case Less_than_pref:
			case Equal_pref:
			case Greater_than_pref:
			case Sym_constant:
			case Int_constant:
			case Float_constant:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(161);
				test();
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Positive_pref) {
					{
					setState(162);
					match(Positive_pref);
					}
				}

				}
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(165);
				conds_for_one_id();
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Positive_pref) {
					{
					setState(166);
					match(Positive_pref);
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestContext extends ParserRuleContext {
		public Conjunctive_testContext conjunctive_test() {
			return getRuleContext(Conjunctive_testContext.class,0);
		}
		public Simple_testContext simple_test() {
			return getRuleContext(Simple_testContext.class,0);
		}
		public Multi_value_testContext multi_value_test() {
			return getRuleContext(Multi_value_testContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_test);
		try {
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				conjunctive_test();
				}
				break;
			case T__18:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case Print_string:
			case STATE:
			case Less_than_pref:
			case Equal_pref:
			case Greater_than_pref:
			case Sym_constant:
			case Int_constant:
			case Float_constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				simple_test();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				multi_value_test();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conjunctive_testContext extends ParserRuleContext {
		public List<Simple_testContext> simple_test() {
			return getRuleContexts(Simple_testContext.class);
		}
		public Simple_testContext simple_test(int i) {
			return getRuleContext(Simple_testContext.class,i);
		}
		public Conjunctive_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunctive_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterConjunctive_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitConjunctive_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitConjunctive_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conjunctive_testContext conjunctive_test() throws RecognitionException {
		Conjunctive_testContext _localctx = new Conjunctive_testContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conjunctive_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__2);
			setState(178); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(177);
				simple_test();
				}
				}
				setState(180); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << Print_string) | (1L << STATE) | (1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref) | (1L << Sym_constant) | (1L << Int_constant) | (1L << Float_constant))) != 0) );
			setState(182);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_testContext extends ParserRuleContext {
		public Disjunction_testContext disjunction_test() {
			return getRuleContext(Disjunction_testContext.class,0);
		}
		public Relational_testContext relational_test() {
			return getRuleContext(Relational_testContext.class,0);
		}
		public Simple_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterSimple_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitSimple_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitSimple_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_testContext simple_test() throws RecognitionException {
		Simple_testContext _localctx = new Simple_testContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_simple_test);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				disjunction_test();
				}
				break;
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case Print_string:
			case STATE:
			case Less_than_pref:
			case Equal_pref:
			case Greater_than_pref:
			case Sym_constant:
			case Int_constant:
			case Float_constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				relational_test();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multi_value_testContext extends ParserRuleContext {
		public List<TerminalNode> Int_constant() { return getTokens(SoarParser.Int_constant); }
		public TerminalNode Int_constant(int i) {
			return getToken(SoarParser.Int_constant, i);
		}
		public Multi_value_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_value_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterMulti_value_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitMulti_value_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitMulti_value_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_value_testContext multi_value_test() throws RecognitionException {
		Multi_value_testContext _localctx = new Multi_value_testContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_multi_value_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__16);
			setState(190); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(189);
				match(Int_constant);
				}
				}
				setState(192); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Int_constant );
			setState(194);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Disjunction_testContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public Disjunction_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterDisjunction_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitDisjunction_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitDisjunction_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Disjunction_testContext disjunction_test() throws RecognitionException {
		Disjunction_testContext _localctx = new Disjunction_testContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_disjunction_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__18);
			setState(198); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197);
				constant();
				}
				}
				setState(200); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Print_string) | (1L << STATE) | (1L << Sym_constant) | (1L << Int_constant) | (1L << Float_constant))) != 0) );
			setState(202);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relational_testContext extends ParserRuleContext {
		public Single_testContext single_test() {
			return getRuleContext(Single_testContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public Relational_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterRelational_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitRelational_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitRelational_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Relational_testContext relational_test() throws RecognitionException {
		Relational_testContext _localctx = new Relational_testContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_relational_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(204);
				relation();
				}
				break;
			}
			setState(207);
			single_test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode Less_than_pref() { return getToken(SoarParser.Less_than_pref, 0); }
		public TerminalNode Greater_than_pref() { return getToken(SoarParser.Greater_than_pref, 0); }
		public TerminalNode Equal_pref() { return getToken(SoarParser.Equal_pref, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_testContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Single_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterSingle_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitSingle_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitSingle_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_testContext single_test() throws RecognitionException {
		Single_testContext _localctx = new Single_testContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_single_test);
		try {
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Less_than_pref:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				variable();
				}
				break;
			case Print_string:
			case STATE:
			case Sym_constant:
			case Int_constant:
			case Float_constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				constant();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode Less_than_pref() { return getToken(SoarParser.Less_than_pref, 0); }
		public Sym_constantContext sym_constant() {
			return getRuleContext(Sym_constantContext.class,0);
		}
		public TerminalNode Greater_than_pref() { return getToken(SoarParser.Greater_than_pref, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(Less_than_pref);
			setState(216);
			sym_constant();
			setState(217);
			match(Greater_than_pref);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public Sym_constantContext sym_constant() {
			return getRuleContext(Sym_constantContext.class,0);
		}
		public TerminalNode Int_constant() { return getToken(SoarParser.Int_constant, 0); }
		public TerminalNode Float_constant() { return getToken(SoarParser.Float_constant, 0); }
		public TerminalNode Print_string() { return getToken(SoarParser.Print_string, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_constant);
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STATE:
			case Sym_constant:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				sym_constant();
				}
				break;
			case Int_constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(Int_constant);
				}
				break;
			case Float_constant:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				match(Float_constant);
				}
				break;
			case Print_string:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
				match(Print_string);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Action_sideContext extends ParserRuleContext {
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public List<Func_callContext> func_call() {
			return getRuleContexts(Func_callContext.class);
		}
		public Func_callContext func_call(int i) {
			return getRuleContext(Func_callContext.class,i);
		}
		public List<PrintContext> print() {
			return getRuleContexts(PrintContext.class);
		}
		public PrintContext print(int i) {
			return getRuleContext(PrintContext.class,i);
		}
		public Action_sideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterAction_side(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitAction_side(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitAction_side(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Action_sideContext action_side() throws RecognitionException {
		Action_sideContext _localctx = new Action_sideContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_action_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				setState(228);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(225);
					action();
					}
					break;
				case 2:
					{
					setState(226);
					func_call();
					}
					break;
				case 3:
					{
					setState(227);
					print();
					}
					break;
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<Attr_value_makeContext> attr_value_make() {
			return getRuleContexts(Attr_value_makeContext.class);
		}
		public Attr_value_makeContext attr_value_make(int i) {
			return getRuleContext(Attr_value_makeContext.class,i);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(233);
			match(T__11);
			setState(234);
			variable();
			setState(236); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(235);
				attr_value_make();
				}
				}
				setState(238); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__14 );
			setState(240);
			match(T__13);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public List<TerminalNode> Print_string() { return getTokens(SoarParser.Print_string); }
		public TerminalNode Print_string(int i) {
			return getToken(SoarParser.Print_string, i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_print);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(252); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(242);
					match(T__11);
					setState(243);
					match(T__25);
					setState(247); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						setState(247);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case Print_string:
							{
							setState(244);
							match(Print_string);
							}
							break;
						case Less_than_pref:
							{
							setState(245);
							variable();
							}
							break;
						case T__26:
							{
							setState(246);
							match(T__26);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						setState(249); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << Print_string) | (1L << Less_than_pref))) != 0) );
					setState(251);
					match(T__13);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(254); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_callContext extends ParserRuleContext {
		public Func_nameContext func_name() {
			return getRuleContext(Func_nameContext.class,0);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_func_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__11);
			setState(257);
			func_name();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << Print_string) | (1L << STATE) | (1L << Less_than_pref) | (1L << Sym_constant) | (1L << Int_constant) | (1L << Float_constant))) != 0)) {
				{
				{
				setState(258);
				value();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_nameContext extends ParserRuleContext {
		public Sym_constantContext sym_constant() {
			return getRuleContext(Sym_constantContext.class,0);
		}
		public TerminalNode Positive_pref() { return getToken(SoarParser.Positive_pref, 0); }
		public TerminalNode Negative_pref() { return getToken(SoarParser.Negative_pref, 0); }
		public Func_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterFunc_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitFunc_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitFunc_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_nameContext func_name() throws RecognitionException {
		Func_nameContext _localctx = new Func_nameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_func_name);
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STATE:
			case Sym_constant:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				sym_constant();
				}
				break;
			case Positive_pref:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				match(Positive_pref);
				}
				break;
			case Negative_pref:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				match(Negative_pref);
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 4);
				{
				setState(269);
				match(T__27);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 5);
				{
				setState(270);
				match(T__28);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_value);
		try {
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Print_string:
			case STATE:
			case Sym_constant:
			case Int_constant:
			case Float_constant:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				constant();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				func_call();
				}
				break;
			case Less_than_pref:
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attr_value_makeContext extends ParserRuleContext {
		public List<Variable_or_sym_constantContext> variable_or_sym_constant() {
			return getRuleContexts(Variable_or_sym_constantContext.class);
		}
		public Variable_or_sym_constantContext variable_or_sym_constant(int i) {
			return getRuleContext(Variable_or_sym_constantContext.class,i);
		}
		public Value_makeContext value_make() {
			return getRuleContext(Value_makeContext.class,0);
		}
		public Attr_value_makeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr_value_make; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterAttr_value_make(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitAttr_value_make(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitAttr_value_make(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attr_value_makeContext attr_value_make() throws RecognitionException {
		Attr_value_makeContext _localctx = new Attr_value_makeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_attr_value_make);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__14);
			setState(279);
			variable_or_sym_constant();
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(280);
				match(T__15);
				setState(281);
				variable_or_sym_constant();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287);
			value_make();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_or_sym_constantContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public Sym_constantContext sym_constant() {
			return getRuleContext(Sym_constantContext.class,0);
		}
		public Variable_or_sym_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_or_sym_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterVariable_or_sym_constant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitVariable_or_sym_constant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitVariable_or_sym_constant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_or_sym_constantContext variable_or_sym_constant() throws RecognitionException {
		Variable_or_sym_constantContext _localctx = new Variable_or_sym_constantContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_variable_or_sym_constant);
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Less_than_pref:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				variable();
				}
				break;
			case STATE:
			case Sym_constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				sym_constant();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Value_makeContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode Negative_pref() { return getToken(SoarParser.Negative_pref, 0); }
		public TerminalNode Positive_pref() { return getToken(SoarParser.Positive_pref, 0); }
		public List<Pref_specifierContext> pref_specifier() {
			return getRuleContexts(Pref_specifierContext.class);
		}
		public Pref_specifierContext pref_specifier(int i) {
			return getRuleContext(Pref_specifierContext.class,i);
		}
		public Value_makeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_make; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterValue_make(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitValue_make(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitValue_make(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Value_makeContext value_make() throws RecognitionException {
		Value_makeContext _localctx = new Value_makeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_value_make);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			value();
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Positive_pref) | (1L << Negative_pref) | (1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref) | (1L << And_pref))) != 0)) {
					{
					{
					setState(294);
					pref_specifier();
					}
					}
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				{
				setState(300);
				match(Negative_pref);
				setState(301);
				value();
				setState(302);
				match(Positive_pref);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pref_specifierContext extends ParserRuleContext {
		public Unary_prefContext unary_pref() {
			return getRuleContext(Unary_prefContext.class,0);
		}
		public Unary_or_binary_prefContext unary_or_binary_pref() {
			return getRuleContext(Unary_or_binary_prefContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Pref_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pref_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterPref_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitPref_specifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitPref_specifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pref_specifierContext pref_specifier() throws RecognitionException {
		Pref_specifierContext _localctx = new Pref_specifierContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_pref_specifier);
		int _la;
		try {
			setState(319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(306);
				unary_pref();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__29) {
					{
					setState(307);
					match(T__29);
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(310);
				unary_or_binary_pref();
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__29) {
					{
					setState(311);
					match(T__29);
					}
				}

				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(314);
				unary_or_binary_pref();
				setState(315);
				value();
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__29) {
					{
					setState(316);
					match(T__29);
					}
				}

				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_prefContext extends ParserRuleContext {
		public TerminalNode Positive_pref() { return getToken(SoarParser.Positive_pref, 0); }
		public TerminalNode Negative_pref() { return getToken(SoarParser.Negative_pref, 0); }
		public Unary_prefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_pref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterUnary_pref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitUnary_pref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitUnary_pref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_prefContext unary_pref() throws RecognitionException {
		Unary_prefContext _localctx = new Unary_prefContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_unary_pref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			_la = _input.LA(1);
			if ( !(_la==Positive_pref || _la==Negative_pref) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_or_binary_prefContext extends ParserRuleContext {
		public TerminalNode Less_than_pref() { return getToken(SoarParser.Less_than_pref, 0); }
		public TerminalNode Equal_pref() { return getToken(SoarParser.Equal_pref, 0); }
		public TerminalNode Greater_than_pref() { return getToken(SoarParser.Greater_than_pref, 0); }
		public TerminalNode And_pref() { return getToken(SoarParser.And_pref, 0); }
		public Unary_or_binary_prefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_or_binary_pref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterUnary_or_binary_pref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitUnary_or_binary_pref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitUnary_or_binary_pref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_or_binary_prefContext unary_or_binary_pref() throws RecognitionException {
		Unary_or_binary_prefContext _localctx = new Unary_or_binary_prefContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_unary_or_binary_pref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less_than_pref) | (1L << Equal_pref) | (1L << Greater_than_pref) | (1L << And_pref))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sym_constantContext extends ParserRuleContext {
		public TerminalNode Sym_constant() { return getToken(SoarParser.Sym_constant, 0); }
		public TerminalNode STATE() { return getToken(SoarParser.STATE, 0); }
		public Sym_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sym_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).enterSym_constant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SoarListener ) ((SoarListener)listener).exitSym_constant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SoarVisitor ) return ((SoarVisitor<? extends T>)visitor).visitSym_constant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sym_constantContext sym_constant() throws RecognitionException {
		Sym_constantContext _localctx = new Sym_constantContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_sym_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_la = _input.LA(1);
			if ( !(_la==STATE || _la==Sym_constant) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u014a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\6\2J\n\2\r\2\16\2K\3\3\3\3\3\3\3\3\5\3R\n"+
		"\3\3\3\5\3U\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\7\5a\n\5\f\5\16"+
		"\5d\13\5\3\6\3\6\3\6\5\6i\n\6\3\6\6\6l\n\6\r\6\16\6m\3\6\3\6\3\7\3\7\3"+
		"\7\5\7u\n\7\3\b\3\b\3\b\6\bz\n\b\r\b\16\b{\3\b\3\b\5\b\u0080\n\b\3\t\3"+
		"\t\5\t\u0084\n\t\3\t\3\t\6\t\u0088\n\t\r\t\16\t\u0089\3\t\3\t\3\n\3\n"+
		"\3\13\5\13\u0091\n\13\3\13\3\13\3\13\3\13\7\13\u0097\n\13\f\13\16\13\u009a"+
		"\13\13\3\13\7\13\u009d\n\13\f\13\16\13\u00a0\13\13\3\f\3\f\3\r\3\r\5\r"+
		"\u00a6\n\r\3\r\3\r\5\r\u00aa\n\r\5\r\u00ac\n\r\3\16\3\16\3\16\5\16\u00b1"+
		"\n\16\3\17\3\17\6\17\u00b5\n\17\r\17\16\17\u00b6\3\17\3\17\3\20\3\20\5"+
		"\20\u00bd\n\20\3\21\3\21\6\21\u00c1\n\21\r\21\16\21\u00c2\3\21\3\21\3"+
		"\22\3\22\6\22\u00c9\n\22\r\22\16\22\u00ca\3\22\3\22\3\23\5\23\u00d0\n"+
		"\23\3\23\3\23\3\24\3\24\3\25\3\25\5\25\u00d8\n\25\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\5\27\u00e2\n\27\3\30\3\30\3\30\7\30\u00e7\n\30\f"+
		"\30\16\30\u00ea\13\30\3\31\3\31\3\31\6\31\u00ef\n\31\r\31\16\31\u00f0"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\6\32\u00fa\n\32\r\32\16\32\u00fb\3"+
		"\32\6\32\u00ff\n\32\r\32\16\32\u0100\3\33\3\33\3\33\7\33\u0106\n\33\f"+
		"\33\16\33\u0109\13\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\5\34\u0112\n"+
		"\34\3\35\3\35\3\35\5\35\u0117\n\35\3\36\3\36\3\36\3\36\7\36\u011d\n\36"+
		"\f\36\16\36\u0120\13\36\3\36\3\36\3\37\3\37\5\37\u0126\n\37\3 \3 \7 \u012a"+
		"\n \f \16 \u012d\13 \3 \3 \3 \3 \5 \u0133\n \3!\3!\5!\u0137\n!\3!\3!\5"+
		"!\u013b\n!\3!\3!\3!\5!\u0140\n!\5!\u0142\n!\3\"\3\"\3#\3#\3$\3$\3$\2\2"+
		"%\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"\2\t\3\2\3\4\3\2\t\r\4\2\17\17##\4\2\27\33&(\3\2$%\3\2&)\4\2##**\2\u015a"+
		"\2I\3\2\2\2\4M\3\2\2\2\6[\3\2\2\2\b^\3\2\2\2\ne\3\2\2\2\ft\3\2\2\2\16"+
		"\177\3\2\2\2\20\u0081\3\2\2\2\22\u008d\3\2\2\2\24\u0090\3\2\2\2\26\u00a1"+
		"\3\2\2\2\30\u00ab\3\2\2\2\32\u00b0\3\2\2\2\34\u00b2\3\2\2\2\36\u00bc\3"+
		"\2\2\2 \u00be\3\2\2\2\"\u00c6\3\2\2\2$\u00cf\3\2\2\2&\u00d3\3\2\2\2(\u00d7"+
		"\3\2\2\2*\u00d9\3\2\2\2,\u00e1\3\2\2\2.\u00e8\3\2\2\2\60\u00eb\3\2\2\2"+
		"\62\u00fe\3\2\2\2\64\u0102\3\2\2\2\66\u0111\3\2\2\28\u0116\3\2\2\2:\u0118"+
		"\3\2\2\2<\u0125\3\2\2\2>\u0127\3\2\2\2@\u0141\3\2\2\2B\u0143\3\2\2\2D"+
		"\u0145\3\2\2\2F\u0147\3\2\2\2HJ\5\4\3\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2"+
		"KL\3\2\2\2L\3\3\2\2\2MN\t\2\2\2NO\7\5\2\2OQ\5F$\2PR\7!\2\2QP\3\2\2\2Q"+
		"R\3\2\2\2RT\3\2\2\2SU\5\6\4\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\5\b\5\2"+
		"WX\7\6\2\2XY\5.\30\2YZ\7\7\2\2Z\5\3\2\2\2[\\\7\b\2\2\\]\t\3\2\2]\7\3\2"+
		"\2\2^b\5\n\6\2_a\5\f\7\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\t\3"+
		"\2\2\2db\3\2\2\2ef\7\16\2\2fh\t\4\2\2gi\5\22\n\2hg\3\2\2\2hi\3\2\2\2i"+
		"k\3\2\2\2jl\5\24\13\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2"+
		"\2op\7\20\2\2p\13\3\2\2\2qu\5\16\b\2rs\7%\2\2su\5\16\b\2tq\3\2\2\2tr\3"+
		"\2\2\2u\r\3\2\2\2v\u0080\5\20\t\2wy\7\5\2\2xz\5\f\7\2yx\3\2\2\2z{\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\7\2\2~\u0080\3\2\2\2\177v\3\2\2"+
		"\2\177w\3\2\2\2\u0080\17\3\2\2\2\u0081\u0083\7\16\2\2\u0082\u0084\t\4"+
		"\2\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\5\22\n\2\u0086\u0088\5\24\13\2\u0087\u0086\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7\20\2\2\u008c\21\3\2\2\2\u008d\u008e\5\32\16\2\u008e\23\3\2\2"+
		"\2\u008f\u0091\7%\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\u0093\7\21\2\2\u0093\u0098\5\26\f\2\u0094\u0095\7\22\2"+
		"\2\u0095\u0097\5\26\f\2\u0096\u0094\3\2\2\2\u0097\u009a\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009e\3\2\2\2\u009a\u0098\3\2"+
		"\2\2\u009b\u009d\5\30\r\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\25\3\2\2\2\u00a0\u009e\3\2\2"+
		"\2\u00a1\u00a2\5\32\16\2\u00a2\27\3\2\2\2\u00a3\u00a5\5\32\16\2\u00a4"+
		"\u00a6\7$\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00ac\3\2"+
		"\2\2\u00a7\u00a9\5\20\t\2\u00a8\u00aa\7$\2\2\u00a9\u00a8\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a3\3\2\2\2\u00ab\u00a7\3\2"+
		"\2\2\u00ac\31\3\2\2\2\u00ad\u00b1\5\34\17\2\u00ae\u00b1\5\36\20\2\u00af"+
		"\u00b1\5 \21\2\u00b0\u00ad\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00af\3\2"+
		"\2\2\u00b1\33\3\2\2\2\u00b2\u00b4\7\5\2\2\u00b3\u00b5\5\36\20\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7\7\2\2\u00b9\35\3\2\2\2\u00ba\u00bd"+
		"\5\"\22\2\u00bb\u00bd\5$\23\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2"+
		"\u00bd\37\3\2\2\2\u00be\u00c0\7\23\2\2\u00bf\u00c1\7+\2\2\u00c0\u00bf"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c5\7\24\2\2\u00c5!\3\2\2\2\u00c6\u00c8\7\25\2"+
		"\2\u00c7\u00c9\5,\27\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\7\26\2\2"+
		"\u00cd#\3\2\2\2\u00ce\u00d0\5&\24\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3"+
		"\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\5(\25\2\u00d2%\3\2\2\2\u00d3\u00d4"+
		"\t\5\2\2\u00d4\'\3\2\2\2\u00d5\u00d8\5*\26\2\u00d6\u00d8\5,\27\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8)\3\2\2\2\u00d9\u00da\7&\2\2\u00da"+
		"\u00db\5F$\2\u00db\u00dc\7(\2\2\u00dc+\3\2\2\2\u00dd\u00e2\5F$\2\u00de"+
		"\u00e2\7+\2\2\u00df\u00e2\7,\2\2\u00e0\u00e2\7\"\2\2\u00e1\u00dd\3\2\2"+
		"\2\u00e1\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2-"+
		"\3\2\2\2\u00e3\u00e7\5\60\31\2\u00e4\u00e7\5\64\33\2\u00e5\u00e7\5\62"+
		"\32\2\u00e6\u00e3\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7"+
		"\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9/\3\2\2\2"+
		"\u00ea\u00e8\3\2\2\2\u00eb\u00ec\7\16\2\2\u00ec\u00ee\5*\26\2\u00ed\u00ef"+
		"\5:\36\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\7\20\2\2\u00f3\61\3\2\2"+
		"\2\u00f4\u00f5\7\16\2\2\u00f5\u00f9\7\34\2\2\u00f6\u00fa\7\"\2\2\u00f7"+
		"\u00fa\5*\26\2\u00f8\u00fa\7\35\2\2\u00f9\u00f6\3\2\2\2\u00f9\u00f7\3"+
		"\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\7\20\2\2\u00fe\u00f4\3"+
		"\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\63\3\2\2\2\u0102\u0103\7\16\2\2\u0103\u0107\5\66\34\2\u0104\u0106\58"+
		"\35\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\7\20"+
		"\2\2\u010b\65\3\2\2\2\u010c\u0112\5F$\2\u010d\u0112\7$\2\2\u010e\u0112"+
		"\7%\2\2\u010f\u0112\7\36\2\2\u0110\u0112\7\37\2\2\u0111\u010c\3\2\2\2"+
		"\u0111\u010d\3\2\2\2\u0111\u010e\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0110"+
		"\3\2\2\2\u0112\67\3\2\2\2\u0113\u0117\5,\27\2\u0114\u0117\5\64\33\2\u0115"+
		"\u0117\5*\26\2\u0116\u0113\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0115\3\2"+
		"\2\2\u01179\3\2\2\2\u0118\u0119\7\21\2\2\u0119\u011e\5<\37\2\u011a\u011b"+
		"\7\22\2\2\u011b\u011d\5<\37\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2"+
		"\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u011e"+
		"\3\2\2\2\u0121\u0122\5> \2\u0122;\3\2\2\2\u0123\u0126\5*\26\2\u0124\u0126"+
		"\5F$\2\u0125\u0123\3\2\2\2\u0125\u0124\3\2\2\2\u0126=\3\2\2\2\u0127\u0132"+
		"\58\35\2\u0128\u012a\5@!\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u0133\3\2\2\2\u012d\u012b\3\2"+
		"\2\2\u012e\u012f\7%\2\2\u012f\u0130\58\35\2\u0130\u0131\7$\2\2\u0131\u0133"+
		"\3\2\2\2\u0132\u012b\3\2\2\2\u0132\u012e\3\2\2\2\u0133?\3\2\2\2\u0134"+
		"\u0136\5B\"\2\u0135\u0137\7 \2\2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2"+
		"\2\u0137\u0142\3\2\2\2\u0138\u013a\5D#\2\u0139\u013b\7 \2\2\u013a\u0139"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0142\3\2\2\2\u013c\u013d\5D#\2\u013d"+
		"\u013f\58\35\2\u013e\u0140\7 \2\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0142\3\2\2\2\u0141\u0134\3\2\2\2\u0141\u0138\3\2\2\2\u0141"+
		"\u013c\3\2\2\2\u0142A\3\2\2\2\u0143\u0144\t\6\2\2\u0144C\3\2\2\2\u0145"+
		"\u0146\t\7\2\2\u0146E\3\2\2\2\u0147\u0148\t\b\2\2\u0148G\3\2\2\2,KQTb"+
		"hmt{\177\u0083\u0089\u0090\u0098\u009e\u00a5\u00a9\u00ab\u00b0\u00b6\u00bc"+
		"\u00c2\u00ca\u00cf\u00d7\u00e1\u00e6\u00e8\u00f0\u00f9\u00fb\u0100\u0107"+
		"\u0111\u0116\u011e\u0125\u012b\u0132\u0136\u013a\u013f\u0141";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}