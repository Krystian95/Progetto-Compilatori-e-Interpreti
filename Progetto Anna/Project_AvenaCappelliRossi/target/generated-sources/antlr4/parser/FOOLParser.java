// Generated from FOOL.g4 by ANTLR 4.4
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
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, COLON=2, COMMA=3, GE=4, LE=5, NOT=6, AND=7, OR=8, EQ=9, ASM=10, 
		PLUS=11, MINUS=12, TIMES=13, DIV=14, TRUE=15, FALSE=16, LPAR=17, RPAR=18, 
		CLPAR=19, CRPAR=20, IF=21, THEN=22, ELSE=23, LET=24, IN=25, VAR=26, FUN=27, 
		INT=28, BOOL=29, CLASS=30, EXTENDS=31, DOT=32, VOID=33, NULL=34, NEW=35, 
		STDLIB=36, INTEGER=37, ID=38, WS=39, LINECOMENTS=40, BLOCKCOMENTS=41;
	public static final String[] tokenNames = {
		"<INVALID>", "';'", "':'", "','", "'>='", "'<='", "NOT", "AND", "OR", 
		"'=='", "'='", "'+'", "'-'", "'*'", "'/'", "'true'", "'false'", "'('", 
		"')'", "'{'", "'}'", "'if'", "'then'", "'else'", "'let'", "'in'", "'var'", 
		"'fun'", "'int'", "'bool'", "'class'", "'extends'", "'.'", "'void'", "'null'", 
		"'new'", "'print'", "INTEGER", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS"
	};
	public static final int
		RULE_start = 0, RULE_classdec = 1, RULE_prog = 2, RULE_let = 3, RULE_letfun = 4, 
		RULE_vardec = 5, RULE_varasm = 6, RULE_fun = 7, RULE_funbody = 8, RULE_dec = 9, 
		RULE_type = 10, RULE_exp = 11, RULE_factor = 12, RULE_arithmetic = 13, 
		RULE_term = 14, RULE_denied = 15, RULE_value = 16, RULE_stms = 17, RULE_stm = 18;
	public static final String[] ruleNames = {
		"start", "classdec", "prog", "let", "letfun", "vardec", "varasm", "fun", 
		"funbody", "dec", "type", "exp", "factor", "arithmetic", "term", "denied", 
		"value", "stms", "stm"
	};

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ClassdecContext classdec(int i) {
			return getRuleContext(ClassdecContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<ClassdecContext> classdec() {
			return getRuleContexts(ClassdecContext.class);
		}
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(38); classdec();
				setState(39); match(SEMIC);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46); prog();
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

	public static class ClassdecContext extends ParserRuleContext {
		public Token classname;
		public Token classextended;
		public TerminalNode CRPAR() { return getToken(FOOLParser.CRPAR, 0); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode CLPAR() { return getToken(FOOLParser.CLPAR, 0); }
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public TerminalNode EXTENDS() { return getToken(FOOLParser.EXTENDS, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
		public FunContext fun(int i) {
			return getRuleContext(FunContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public List<FunContext> fun() {
			return getRuleContexts(FunContext.class);
		}
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode CLASS() { return getToken(FOOLParser.CLASS, 0); }
		public ClassdecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterClassdec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitClassdec(this);
		}
	}

	public final ClassdecContext classdec() throws RecognitionException {
		ClassdecContext _localctx = new ClassdecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classdec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(CLASS);
			setState(49); ((ClassdecContext)_localctx).classname = match(ID);
			setState(52);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(50); match(EXTENDS);
				setState(51); ((ClassdecContext)_localctx).classextended = match(ID);
				}
			}

			setState(54); match(LPAR);
			setState(63);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(55); vardec();
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(56); match(COMMA);
					setState(57); vardec();
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(65); match(RPAR);
			setState(66); match(CLPAR);
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67); fun();
				setState(68); match(SEMIC);
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(74); match(CRPAR);
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

	public static class ProgContext extends ParserRuleContext {
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	 
		public ProgContext() { }
		public void copyFrom(ProgContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LetInExpContext extends ProgContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public LetInExpContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterLetInExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitLetInExp(this);
		}
	}
	public static class SingleExpContext extends ProgContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public SingleExpContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterSingleExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitSingleExp(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prog);
		try {
			setState(89);
			switch (_input.LA(1)) {
			case NOT:
			case MINUS:
			case TRUE:
			case FALSE:
			case LPAR:
			case IF:
			case NULL:
			case NEW:
			case STDLIB:
			case INTEGER:
			case ID:
				_localctx = new SingleExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(76); exp();
					}
					break;
				case 2:
					{
					setState(77); stms();
					}
					break;
				}
				setState(80); match(SEMIC);
				}
				break;
			case LET:
				_localctx = new LetInExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82); let();
				setState(85);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(83); exp();
					}
					break;
				case 2:
					{
					setState(84); stms();
					}
					break;
				}
				setState(87); match(SEMIC);
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

	public static class LetContext extends ParserRuleContext {
		public List<DecContext> dec() {
			return getRuleContexts(DecContext.class);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public DecContext dec(int i) {
			return getRuleContext(DecContext.class,i);
		}
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitLet(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_let);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(LET);
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92); dec();
				setState(93); match(SEMIC);
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(99); match(IN);
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

	public static class LetfunContext extends ParserRuleContext {
		public VarasmContext varasm(int i) {
			return getRuleContext(VarasmContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<VarasmContext> varasm() {
			return getRuleContexts(VarasmContext.class);
		}
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public LetfunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letfun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterLetfun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitLetfun(this);
		}
	}

	public final LetfunContext letfun() throws RecognitionException {
		LetfunContext _localctx = new LetfunContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_letfun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(LET);
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102); varasm();
				setState(103); match(SEMIC);
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0) );
			setState(109); match(IN);
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

	public static class VardecContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VardecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVardec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVardec(this);
		}
	}

	public final VardecContext vardec() throws RecognitionException {
		VardecContext _localctx = new VardecContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_vardec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); type();
			setState(112); match(ID);
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

	public static class VarasmContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public VardecContext vardec() {
			return getRuleContext(VardecContext.class,0);
		}
		public VarasmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varasm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarasm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarasm(this);
		}
	}

	public final VarasmContext varasm() throws RecognitionException {
		VarasmContext _localctx = new VarasmContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varasm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); vardec();
			setState(115); match(ASM);
			setState(116); exp();
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

	public static class FunContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public FunbodyContext funbody() {
			return getRuleContext(FunbodyContext.class,0);
		}
		public TerminalNode CRPAR() { return getToken(FOOLParser.CRPAR, 0); }
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode VOID() { return getToken(FOOLParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode CLPAR() { return getToken(FOOLParser.CLPAR, 0); }
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public FunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFun(this);
		}
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				{
				setState(118); type();
				}
				break;
			case VOID:
				{
				setState(119); match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(122); match(ID);
			setState(123); match(LPAR);
			setState(132);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(124); vardec();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(125); match(COMMA);
					setState(126); vardec();
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(134); match(RPAR);
			setState(135); match(CLPAR);
			setState(136); funbody();
			setState(137); match(CRPAR);
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

	public static class FunbodyContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public LetfunContext letfun() {
			return getRuleContext(LetfunContext.class,0);
		}
		public FunbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funbody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFunbody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFunbody(this);
		}
	}

	public final FunbodyContext funbody() throws RecognitionException {
		FunbodyContext _localctx = new FunbodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if (_la==LET) {
				{
				setState(139); letfun();
				}
			}

			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(142); exp();
				}
				break;
			case 2:
				{
				setState(143); stms();
				}
				break;
			}
			setState(146); match(SEMIC);
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

	public static class DecContext extends ParserRuleContext {
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec; }
	 
		public DecContext() { }
		public void copyFrom(DecContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarAssignmentContext extends DecContext {
		public VarasmContext varasm() {
			return getRuleContext(VarasmContext.class,0);
		}
		public VarAssignmentContext(DecContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarAssignment(this);
		}
	}
	public static class FunDeclarationContext extends DecContext {
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public FunDeclarationContext(DecContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFunDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFunDeclaration(this);
		}
	}

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dec);
		try {
			setState(150);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new VarAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(148); varasm();
				}
				break;
			case 2:
				_localctx = new FunDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(149); fun();
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class ExpContext extends ParserRuleContext {
		public FactorContext left;
		public Token operator;
		public ExpContext right;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode AND() { return getToken(FOOLParser.AND, 0); }
		public TerminalNode OR() { return getToken(FOOLParser.OR, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); ((ExpContext)_localctx).left = factor();
			setState(157);
			_la = _input.LA(1);
			if (_la==AND || _la==OR) {
				{
				setState(155);
				((ExpContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
					((ExpContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(156); ((ExpContext)_localctx).right = exp();
				}
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

	public static class FactorContext extends ParserRuleContext {
		public ArithmeticContext left;
		public Token operator;
		public FactorContext right;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode GE() { return getToken(FOOLParser.GE, 0); }
		public TerminalNode LE() { return getToken(FOOLParser.LE, 0); }
		public TerminalNode EQ() { return getToken(FOOLParser.EQ, 0); }
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); ((FactorContext)_localctx).left = arithmetic();
			setState(162);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(160);
				((FactorContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) ) {
					((FactorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(161); ((FactorContext)_localctx).right = factor();
				}
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

	public static class ArithmeticContext extends ParserRuleContext {
		public TermContext left;
		public Token operator;
		public ArithmeticContext right;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(FOOLParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitArithmetic(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); ((ArithmeticContext)_localctx).left = term();
			setState(167);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(165);
				((ArithmeticContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
					((ArithmeticContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(166); ((ArithmeticContext)_localctx).right = arithmetic();
				}
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

	public static class TermContext extends ParserRuleContext {
		public DeniedContext left;
		public Token operator;
		public TermContext right;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(FOOLParser.TIMES, 0); }
		public DeniedContext denied() {
			return getRuleContext(DeniedContext.class,0);
		}
		public TerminalNode DIV() { return getToken(FOOLParser.DIV, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); ((TermContext)_localctx).left = denied();
			setState(172);
			_la = _input.LA(1);
			if (_la==TIMES || _la==DIV) {
				{
				setState(170);
				((TermContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
					((TermContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(171); ((TermContext)_localctx).right = term();
				}
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

	public static class DeniedContext extends ParserRuleContext {
		public Token operator;
		public ValueContext right;
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public DeniedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_denied; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterDenied(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitDenied(this);
		}
	}

	public final DeniedContext denied() throws RecognitionException {
		DeniedContext _localctx = new DeniedContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_denied);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(174); ((DeniedContext)_localctx).operator = match(NOT);
				}
			}

			setState(177); ((DeniedContext)_localctx).right = value();
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
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseExpContext extends ValueContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public BaseExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterBaseExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitBaseExp(this);
		}
	}
	public static class VarExpContext extends ValueContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public VarExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarExp(this);
		}
	}
	public static class IntValContext extends ValueContext {
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public IntValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIntVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIntVal(this);
		}
	}
	public static class MethodcallExpContext extends ValueContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public MethodcallExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterMethodcallExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitMethodcallExp(this);
		}
	}
	public static class NewExpContext extends ValueContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public NewExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterNewExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitNewExp(this);
		}
	}
	public static class IfExpContext extends ValueContext {
		public ExpContext cond;
		public ExpContext thenBranch;
		public ExpContext elseBranch;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public IfExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIfExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIfExp(this);
		}
	}
	public static class BoolValContext extends ValueContext {
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public BoolValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterBoolVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitBoolVal(this);
		}
	}
	public static class FunExpContext extends ValueContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public FunExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFunExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFunExp(this);
		}
	}
	public static class NullExpContext extends ValueContext {
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public NullExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterNullExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitNullExp(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_value);
		int _la;
		try {
			setState(247);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new IntValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(179); match(MINUS);
					}
				}

				setState(182); match(INTEGER);
				}
				break;
			case 2:
				_localctx = new BoolValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			case 3:
				_localctx = new BaseExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(184); match(LPAR);
				setState(185); exp();
				setState(186); match(RPAR);
				}
				break;
			case 4:
				_localctx = new IfExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(188); match(IF);
				setState(189); ((IfExpContext)_localctx).cond = exp();
				setState(190); match(THEN);
				setState(191); match(CLPAR);
				setState(192); ((IfExpContext)_localctx).thenBranch = exp();
				setState(193); match(SEMIC);
				setState(194); match(CRPAR);
				setState(195); match(ELSE);
				setState(196); match(CLPAR);
				setState(197); ((IfExpContext)_localctx).elseBranch = exp();
				setState(198); match(SEMIC);
				setState(199); match(CRPAR);
				}
				break;
			case 5:
				_localctx = new VarExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(201); match(ID);
				}
				break;
			case 6:
				_localctx = new FunExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(202); match(ID);
				setState(203); match(LPAR);
				setState(212);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(204); exp();
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(205); match(COMMA);
						setState(206); exp();
						}
						}
						setState(211);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(214); match(RPAR);
				}
				break;
			case 7:
				_localctx = new NullExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(215); match(NULL);
				}
				break;
			case 8:
				_localctx = new MethodcallExpContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(216); match(ID);
				setState(217); match(DOT);
				setState(218); match(ID);
				setState(231);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(219); match(LPAR);
					setState(228);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(220); exp();
						setState(225);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(221); match(COMMA);
							setState(222); exp();
							}
							}
							setState(227);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(230); match(RPAR);
					}
				}

				}
				break;
			case 9:
				_localctx = new NewExpContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(233); match(NEW);
				setState(234); match(ID);
				setState(235); match(LPAR);
				setState(244);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(236); exp();
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(237); match(COMMA);
						setState(238); exp();
						}
						}
						setState(243);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(246); match(RPAR);
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

	public static class StmsContext extends ParserRuleContext {
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public StmContext stm(int i) {
			return getRuleContext(StmContext.class,i);
		}
		public List<StmContext> stm() {
			return getRuleContexts(StmContext.class);
		}
		public StmsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterStms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitStms(this);
		}
	}

	public final StmsContext stms() throws RecognitionException {
		StmsContext _localctx = new StmsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(249); stm();
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(250); match(SEMIC);
					setState(251); stm();
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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

	public static class StmContext extends ParserRuleContext {
		public StmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stm; }
	 
		public StmContext() { }
		public void copyFrom(StmContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignmentStmContext extends StmContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public AssignmentStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterAssignmentStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitAssignmentStm(this);
		}
	}
	public static class MethodcallStmContext extends StmContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public MethodcallStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterMethodcallStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitMethodcallStm(this);
		}
	}
	public static class IfStmContext extends StmContext {
		public ExpContext cond;
		public StmsContext thenBranch;
		public StmsContext elseBranch;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public StmsContext stms(int i) {
			return getRuleContext(StmsContext.class,i);
		}
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<StmsContext> stms() {
			return getRuleContexts(StmsContext.class);
		}
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public IfStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIfStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIfStm(this);
		}
	}
	public static class PrintExpContext extends StmContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode STDLIB() { return getToken(FOOLParser.STDLIB, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public PrintExpContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterPrintExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitPrintExp(this);
		}
	}

	public final StmContext stm() throws RecognitionException {
		StmContext _localctx = new StmContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stm);
		int _la;
		try {
			setState(299);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new AssignmentStmContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(257); match(ID);
				setState(260);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(258); match(DOT);
					setState(259); match(ID);
					}
				}

				setState(262); match(ASM);
				setState(263); exp();
				}
				break;
			case 2:
				_localctx = new IfStmContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(264); match(IF);
				setState(265); ((IfStmContext)_localctx).cond = exp();
				setState(266); match(THEN);
				setState(267); match(CLPAR);
				setState(268); ((IfStmContext)_localctx).thenBranch = stms();
				setState(269); match(SEMIC);
				setState(270); match(CRPAR);
				setState(271); match(ELSE);
				setState(272); match(CLPAR);
				setState(273); ((IfStmContext)_localctx).elseBranch = stms();
				setState(274); match(SEMIC);
				setState(275); match(CRPAR);
				}
				break;
			case 3:
				_localctx = new MethodcallStmContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(277); match(ID);
				setState(280);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(278); match(DOT);
					setState(279); match(ID);
					}
				}

				setState(282); match(LPAR);
				setState(291);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(283); exp();
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(284); match(COMMA);
						setState(285); exp();
						}
						}
						setState(290);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(293); match(RPAR);
				}
				break;
			case 4:
				_localctx = new PrintExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(294); match(STDLIB);
				setState(295); match(LPAR);
				setState(296); exp();
				setState(297); match(RPAR);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0130\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\5\3\67\n\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\5\3B\n\3"+
		"\3\3\3\3\3\3\3\3\3\3\6\3I\n\3\r\3\16\3J\3\3\3\3\3\4\3\4\5\4Q\n\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4X\n\4\3\4\3\4\5\4\\\n\4\3\5\3\5\3\5\3\5\6\5b\n\5\r\5"+
		"\16\5c\3\5\3\5\3\6\3\6\3\6\3\6\6\6l\n\6\r\6\16\6m\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\5\t{\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u0082\n\t\f"+
		"\t\16\t\u0085\13\t\5\t\u0087\n\t\3\t\3\t\3\t\3\t\3\t\3\n\5\n\u008f\n\n"+
		"\3\n\3\n\5\n\u0093\n\n\3\n\3\n\3\13\3\13\5\13\u0099\n\13\3\f\3\f\3\r\3"+
		"\r\3\r\5\r\u00a0\n\r\3\16\3\16\3\16\5\16\u00a5\n\16\3\17\3\17\3\17\5\17"+
		"\u00aa\n\17\3\20\3\20\3\20\5\20\u00af\n\20\3\21\5\21\u00b2\n\21\3\21\3"+
		"\21\3\22\5\22\u00b7\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u00d2\n\22\f\22\16\22\u00d5\13\22\5\22\u00d7\n\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00e2\n\22\f\22\16\22\u00e5"+
		"\13\22\5\22\u00e7\n\22\3\22\5\22\u00ea\n\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\7\22\u00f2\n\22\f\22\16\22\u00f5\13\22\5\22\u00f7\n\22\3\22\5\22\u00fa"+
		"\n\22\3\23\3\23\3\23\7\23\u00ff\n\23\f\23\16\23\u0102\13\23\3\24\3\24"+
		"\3\24\5\24\u0107\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u011b\n\24\3\24\3\24\3\24"+
		"\3\24\7\24\u0121\n\24\f\24\16\24\u0124\13\24\5\24\u0126\n\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u012e\n\24\3\24\2\2\25\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&\2\b\4\2\36\37((\3\2\t\n\4\2\6\7\13\13\3\2\r\16"+
		"\3\2\17\20\3\2\21\22\u0149\2-\3\2\2\2\4\62\3\2\2\2\6[\3\2\2\2\b]\3\2\2"+
		"\2\ng\3\2\2\2\fq\3\2\2\2\16t\3\2\2\2\20z\3\2\2\2\22\u008e\3\2\2\2\24\u0098"+
		"\3\2\2\2\26\u009a\3\2\2\2\30\u009c\3\2\2\2\32\u00a1\3\2\2\2\34\u00a6\3"+
		"\2\2\2\36\u00ab\3\2\2\2 \u00b1\3\2\2\2\"\u00f9\3\2\2\2$\u00fb\3\2\2\2"+
		"&\u012d\3\2\2\2()\5\4\3\2)*\7\3\2\2*,\3\2\2\2+(\3\2\2\2,/\3\2\2\2-+\3"+
		"\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\5\6\4\2\61\3\3\2\2\2\62\63"+
		"\7 \2\2\63\66\7(\2\2\64\65\7!\2\2\65\67\7(\2\2\66\64\3\2\2\2\66\67\3\2"+
		"\2\2\678\3\2\2\28A\7\23\2\29>\5\f\7\2:;\7\5\2\2;=\5\f\7\2<:\3\2\2\2=@"+
		"\3\2\2\2><\3\2\2\2>?\3\2\2\2?B\3\2\2\2@>\3\2\2\2A9\3\2\2\2AB\3\2\2\2B"+
		"C\3\2\2\2CD\7\24\2\2DH\7\25\2\2EF\5\20\t\2FG\7\3\2\2GI\3\2\2\2HE\3\2\2"+
		"\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\7\26\2\2M\5\3\2\2\2NQ\5\30"+
		"\r\2OQ\5$\23\2PN\3\2\2\2PO\3\2\2\2QR\3\2\2\2RS\7\3\2\2S\\\3\2\2\2TW\5"+
		"\b\5\2UX\5\30\r\2VX\5$\23\2WU\3\2\2\2WV\3\2\2\2XY\3\2\2\2YZ\7\3\2\2Z\\"+
		"\3\2\2\2[P\3\2\2\2[T\3\2\2\2\\\7\3\2\2\2]a\7\32\2\2^_\5\24\13\2_`\7\3"+
		"\2\2`b\3\2\2\2a^\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7\33"+
		"\2\2f\t\3\2\2\2gk\7\32\2\2hi\5\16\b\2ij\7\3\2\2jl\3\2\2\2kh\3\2\2\2lm"+
		"\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\33\2\2p\13\3\2\2\2qr\5\26\f"+
		"\2rs\7(\2\2s\r\3\2\2\2tu\5\f\7\2uv\7\f\2\2vw\5\30\r\2w\17\3\2\2\2x{\5"+
		"\26\f\2y{\7#\2\2zx\3\2\2\2zy\3\2\2\2{|\3\2\2\2|}\7(\2\2}\u0086\7\23\2"+
		"\2~\u0083\5\f\7\2\177\u0080\7\5\2\2\u0080\u0082\5\f\7\2\u0081\177\3\2"+
		"\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0086~\3\2\2\2\u0086\u0087\3\2\2\2"+
		"\u0087\u0088\3\2\2\2\u0088\u0089\7\24\2\2\u0089\u008a\7\25\2\2\u008a\u008b"+
		"\5\22\n\2\u008b\u008c\7\26\2\2\u008c\21\3\2\2\2\u008d\u008f\5\n\6\2\u008e"+
		"\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u0093\5\30"+
		"\r\2\u0091\u0093\5$\23\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\7\3\2\2\u0095\23\3\2\2\2\u0096\u0099\5\16\b"+
		"\2\u0097\u0099\5\20\t\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099"+
		"\25\3\2\2\2\u009a\u009b\t\2\2\2\u009b\27\3\2\2\2\u009c\u009f\5\32\16\2"+
		"\u009d\u009e\t\3\2\2\u009e\u00a0\5\30\r\2\u009f\u009d\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\31\3\2\2\2\u00a1\u00a4\5\34\17\2\u00a2\u00a3\t\4\2\2\u00a3"+
		"\u00a5\5\32\16\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\33\3\2"+
		"\2\2\u00a6\u00a9\5\36\20\2\u00a7\u00a8\t\5\2\2\u00a8\u00aa\5\34\17\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\35\3\2\2\2\u00ab\u00ae\5 \21"+
		"\2\u00ac\u00ad\t\6\2\2\u00ad\u00af\5\36\20\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\37\3\2\2\2\u00b0\u00b2\7\b\2\2\u00b1\u00b0\3\2\2"+
		"\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\5\"\22\2\u00b4"+
		"!\3\2\2\2\u00b5\u00b7\7\16\2\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2"+
		"\u00b7\u00b8\3\2\2\2\u00b8\u00fa\7\'\2\2\u00b9\u00fa\t\7\2\2\u00ba\u00bb"+
		"\7\23\2\2\u00bb\u00bc\5\30\r\2\u00bc\u00bd\7\24\2\2\u00bd\u00fa\3\2\2"+
		"\2\u00be\u00bf\7\27\2\2\u00bf\u00c0\5\30\r\2\u00c0\u00c1\7\30\2\2\u00c1"+
		"\u00c2\7\25\2\2\u00c2\u00c3\5\30\r\2\u00c3\u00c4\7\3\2\2\u00c4\u00c5\7"+
		"\26\2\2\u00c5\u00c6\7\31\2\2\u00c6\u00c7\7\25\2\2\u00c7\u00c8\5\30\r\2"+
		"\u00c8\u00c9\7\3\2\2\u00c9\u00ca\7\26\2\2\u00ca\u00fa\3\2\2\2\u00cb\u00fa"+
		"\7(\2\2\u00cc\u00cd\7(\2\2\u00cd\u00d6\7\23\2\2\u00ce\u00d3\5\30\r\2\u00cf"+
		"\u00d0\7\5\2\2\u00d0\u00d2\5\30\r\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5\3"+
		"\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d6\u00ce\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00fa\7\24\2\2\u00d9\u00fa\7$\2\2\u00da\u00db\7(\2\2\u00db"+
		"\u00dc\7\"\2\2\u00dc\u00e9\7(\2\2\u00dd\u00e6\7\23\2\2\u00de\u00e3\5\30"+
		"\r\2\u00df\u00e0\7\5\2\2\u00e0\u00e2\5\30\r\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00ea\7\24\2\2\u00e9\u00dd\3\2\2\2\u00e9\u00ea\3"+
		"\2\2\2\u00ea\u00fa\3\2\2\2\u00eb\u00ec\7%\2\2\u00ec\u00ed\7(\2\2\u00ed"+
		"\u00f6\7\23\2\2\u00ee\u00f3\5\30\r\2\u00ef\u00f0\7\5\2\2\u00f0\u00f2\5"+
		"\30\r\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00ee\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\7\24\2\2\u00f9"+
		"\u00b6\3\2\2\2\u00f9\u00b9\3\2\2\2\u00f9\u00ba\3\2\2\2\u00f9\u00be\3\2"+
		"\2\2\u00f9\u00cb\3\2\2\2\u00f9\u00cc\3\2\2\2\u00f9\u00d9\3\2\2\2\u00f9"+
		"\u00da\3\2\2\2\u00f9\u00eb\3\2\2\2\u00fa#\3\2\2\2\u00fb\u0100\5&\24\2"+
		"\u00fc\u00fd\7\3\2\2\u00fd\u00ff\5&\24\2\u00fe\u00fc\3\2\2\2\u00ff\u0102"+
		"\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101%\3\2\2\2\u0102"+
		"\u0100\3\2\2\2\u0103\u0106\7(\2\2\u0104\u0105\7\"\2\2\u0105\u0107\7(\2"+
		"\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109"+
		"\7\f\2\2\u0109\u012e\5\30\r\2\u010a\u010b\7\27\2\2\u010b\u010c\5\30\r"+
		"\2\u010c\u010d\7\30\2\2\u010d\u010e\7\25\2\2\u010e\u010f\5$\23\2\u010f"+
		"\u0110\7\3\2\2\u0110\u0111\7\26\2\2\u0111\u0112\7\31\2\2\u0112\u0113\7"+
		"\25\2\2\u0113\u0114\5$\23\2\u0114\u0115\7\3\2\2\u0115\u0116\7\26\2\2\u0116"+
		"\u012e\3\2\2\2\u0117\u011a\7(\2\2\u0118\u0119\7\"\2\2\u0119\u011b\7(\2"+
		"\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u0125"+
		"\7\23\2\2\u011d\u0122\5\30\r\2\u011e\u011f\7\5\2\2\u011f\u0121\5\30\r"+
		"\2\u0120\u011e\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u011d\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012e\7\24\2\2\u0128\u0129\7"+
		"&\2\2\u0129\u012a\7\23\2\2\u012a\u012b\5\30\r\2\u012b\u012c\7\24\2\2\u012c"+
		"\u012e\3\2\2\2\u012d\u0103\3\2\2\2\u012d\u010a\3\2\2\2\u012d\u0117\3\2"+
		"\2\2\u012d\u0128\3\2\2\2\u012e\'\3\2\2\2&-\66>AJPW[cmz\u0083\u0086\u008e"+
		"\u0092\u0098\u009f\u00a4\u00a9\u00ae\u00b1\u00b6\u00d3\u00d6\u00e3\u00e6"+
		"\u00e9\u00f3\u00f6\u00f9\u0100\u0106\u011a\u0122\u0125\u012d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}