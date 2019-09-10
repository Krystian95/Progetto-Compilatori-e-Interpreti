// Generated from SVM.g4 by ANTLR 4.4
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, STOREW=7, LOADW=8, BRANCH=9, 
		BRANCHEQ=10, BRANCHNOTEQ=11, BRANCHGT=12, BRANCHLESSEQ=13, BRANCHGREATEREQ=14, 
		BRANCHLT=15, JS=16, LOADRA=17, STORERA=18, LOADRV=19, STORERV=20, LOADFP=21, 
		STOREFP=22, COPYFP=23, LOADHP=24, STOREHP=25, PRINT=26, HALT=27, DELETE=28, 
		COL=29, LABEL=30, NUMBER=31, WHITESP=32, ERR=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'sw'", 
		"'lw'", "'b'", "'beq'", "'bneq'", "'bgt'", "'bleq'", "'bgeq'", "'blt'", 
		"'js'", "'lra'", "'sra'", "'lrv'", "'srv'", "'lfp'", "'sfp'", "'cfp'", 
		"'lhp'", "'shp'", "'print'", "'halt'", "'delete'", "':'", "LABEL", "NUMBER", 
		"WHITESP", "ERR"
	};
	public static final int
		RULE_assembly = 0, RULE_instruction = 1;
	public static final String[] ruleNames = {
		"assembly", "instruction"
	};

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AssemblyContext extends ParserRuleContext {
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAssembly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << ADD) | (1L << SUB) | (1L << MULT) | (1L << DIV) | (1L << STOREW) | (1L << LOADW) | (1L << BRANCH) | (1L << BRANCHEQ) | (1L << BRANCHNOTEQ) | (1L << BRANCHGT) | (1L << BRANCHLESSEQ) | (1L << BRANCHGREATEREQ) | (1L << BRANCHLT) | (1L << JS) | (1L << LOADRA) | (1L << STORERA) | (1L << LOADRV) | (1L << STORERV) | (1L << LOADFP) | (1L << STOREFP) | (1L << COPYFP) | (1L << LOADHP) | (1L << STOREHP) | (1L << PRINT) | (1L << HALT) | (1L << DELETE) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4); instruction();
				}
				}
				setState(9);
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

	public static class InstructionContext extends ParserRuleContext {
		public Token n;
		public Token l;
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode DELETE() { return getToken(SVMParser.DELETE, 0); }
		public TerminalNode BRANCHLT() { return getToken(SVMParser.BRANCHLT, 0); }
		public TerminalNode STOREHP() { return getToken(SVMParser.STOREHP, 0); }
		public TerminalNode LOADRA() { return getToken(SVMParser.LOADRA, 0); }
		public TerminalNode BRANCHLESSEQ() { return getToken(SVMParser.BRANCHLESSEQ, 0); }
		public TerminalNode LOADRV() { return getToken(SVMParser.LOADRV, 0); }
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode BRANCHNOTEQ() { return getToken(SVMParser.BRANCHNOTEQ, 0); }
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public TerminalNode LOADW() { return getToken(SVMParser.LOADW, 0); }
		public TerminalNode COL() { return getToken(SVMParser.COL, 0); }
		public TerminalNode STOREFP() { return getToken(SVMParser.STOREFP, 0); }
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public TerminalNode BRANCHGT() { return getToken(SVMParser.BRANCHGT, 0); }
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public TerminalNode MULT() { return getToken(SVMParser.MULT, 0); }
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public TerminalNode COPYFP() { return getToken(SVMParser.COPYFP, 0); }
		public TerminalNode STORERA() { return getToken(SVMParser.STORERA, 0); }
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public TerminalNode STORERV() { return getToken(SVMParser.STORERV, 0); }
		public TerminalNode BRANCHGREATEREQ() { return getToken(SVMParser.BRANCHGREATEREQ, 0); }
		public TerminalNode JS() { return getToken(SVMParser.JS, 0); }
		public TerminalNode LOADHP() { return getToken(SVMParser.LOADHP, 0); }
		public TerminalNode STOREW() { return getToken(SVMParser.STOREW, 0); }
		public TerminalNode LOADFP() { return getToken(SVMParser.LOADFP, 0); }
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(10); match(DELETE);
				}
				break;
			case 2:
				{
				setState(11); match(PUSH);
				setState(12); ((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 3:
				{
				setState(13); match(PUSH);
				setState(14); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 4:
				{
				setState(15); match(POP);
				}
				break;
			case 5:
				{
				setState(16); match(ADD);
				}
				break;
			case 6:
				{
				setState(17); match(SUB);
				}
				break;
			case 7:
				{
				setState(18); match(MULT);
				}
				break;
			case 8:
				{
				setState(19); match(DIV);
				}
				break;
			case 9:
				{
				setState(20); match(STOREW);
				}
				break;
			case 10:
				{
				setState(21); match(LOADW);
				}
				break;
			case 11:
				{
				setState(22); ((InstructionContext)_localctx).l = match(LABEL);
				setState(23); match(COL);
				}
				break;
			case 12:
				{
				setState(24); match(BRANCH);
				setState(25); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 13:
				{
				setState(26); match(BRANCHEQ);
				setState(27); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 14:
				{
				setState(28); match(BRANCHNOTEQ);
				setState(29); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 15:
				{
				setState(30); match(BRANCHGT);
				setState(31); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 16:
				{
				setState(32); match(BRANCHLT);
				setState(33); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 17:
				{
				setState(34); match(BRANCHLESSEQ);
				setState(35); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 18:
				{
				setState(36); match(BRANCHGREATEREQ);
				setState(37); ((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 19:
				{
				setState(38); match(JS);
				}
				break;
			case 20:
				{
				setState(39); match(LOADRA);
				}
				break;
			case 21:
				{
				setState(40); match(STORERA);
				}
				break;
			case 22:
				{
				setState(41); match(LOADRV);
				}
				break;
			case 23:
				{
				setState(42); match(STORERV);
				}
				break;
			case 24:
				{
				setState(43); match(LOADFP);
				}
				break;
			case 25:
				{
				setState(44); match(STOREFP);
				}
				break;
			case 26:
				{
				setState(45); match(COPYFP);
				}
				break;
			case 27:
				{
				setState(46); match(LOADHP);
				}
				break;
			case 28:
				{
				setState(47); match(STOREHP);
				}
				break;
			case 29:
				{
				setState(48); match(PRINT);
				}
				break;
			case 30:
				{
				setState(49); match(HALT);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\67\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\65\n\3\3\3"+
		"\2\2\4\2\4\2\2R\2\t\3\2\2\2\4\64\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13"+
		"\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n\3\3\2\2\2\13\t\3\2\2\2\f\65\7\36\2"+
		"\2\r\16\7\3\2\2\16\65\7!\2\2\17\20\7\3\2\2\20\65\7 \2\2\21\65\7\4\2\2"+
		"\22\65\7\5\2\2\23\65\7\6\2\2\24\65\7\7\2\2\25\65\7\b\2\2\26\65\7\t\2\2"+
		"\27\65\7\n\2\2\30\31\7 \2\2\31\65\7\37\2\2\32\33\7\13\2\2\33\65\7 \2\2"+
		"\34\35\7\f\2\2\35\65\7 \2\2\36\37\7\r\2\2\37\65\7 \2\2 !\7\16\2\2!\65"+
		"\7 \2\2\"#\7\21\2\2#\65\7 \2\2$%\7\17\2\2%\65\7 \2\2&\'\7\20\2\2\'\65"+
		"\7 \2\2(\65\7\22\2\2)\65\7\23\2\2*\65\7\24\2\2+\65\7\25\2\2,\65\7\26\2"+
		"\2-\65\7\27\2\2.\65\7\30\2\2/\65\7\31\2\2\60\65\7\32\2\2\61\65\7\33\2"+
		"\2\62\65\7\34\2\2\63\65\7\35\2\2\64\f\3\2\2\2\64\r\3\2\2\2\64\17\3\2\2"+
		"\2\64\21\3\2\2\2\64\22\3\2\2\2\64\23\3\2\2\2\64\24\3\2\2\2\64\25\3\2\2"+
		"\2\64\26\3\2\2\2\64\27\3\2\2\2\64\30\3\2\2\2\64\32\3\2\2\2\64\34\3\2\2"+
		"\2\64\36\3\2\2\2\64 \3\2\2\2\64\"\3\2\2\2\64$\3\2\2\2\64&\3\2\2\2\64("+
		"\3\2\2\2\64)\3\2\2\2\64*\3\2\2\2\64+\3\2\2\2\64,\3\2\2\2\64-\3\2\2\2\64"+
		".\3\2\2\2\64/\3\2\2\2\64\60\3\2\2\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63"+
		"\3\2\2\2\65\5\3\2\2\2\4\t\64";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}