// Generated from SVM.g4 by ANTLR 4.4
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'"
	};
	public static final String[] ruleNames = {
		"PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHNOTEQ", "BRANCHGT", "BRANCHLESSEQ", "BRANCHGREATEREQ", 
		"BRANCHLT", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", 
		"STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "DELETE", "COL", 
		"LABEL", "NUMBER", "WHITESP", "ERR"
	};


	public int lexicalErrors=0;


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 32: ERR_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  System.err.println("Invalid char: "+ getText()); lexicalErrors++;   break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00dd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\7\37\u00c0\n\37\f\37\16\37\u00c3\13\37\3 \3 \5 \u00c7"+
		"\n \3 \3 \7 \u00cb\n \f \16 \u00ce\13 \5 \u00d0\n \3!\6!\u00d3\n!\r!\16"+
		"!\u00d4\3!\3!\3\"\3\"\3\"\3\"\3\"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\5\4\2C\\c|\5\2\62;C"+
		"\\c|\5\2\13\f\17\17\"\"\u00e1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\3E\3\2\2\2\5J\3\2\2\2\7N\3\2\2\2\tR\3\2\2\2\13V\3\2\2\2\r[\3"+
		"\2\2\2\17_\3\2\2\2\21b\3\2\2\2\23e\3\2\2\2\25g\3\2\2\2\27k\3\2\2\2\31"+
		"p\3\2\2\2\33t\3\2\2\2\35y\3\2\2\2\37~\3\2\2\2!\u0082\3\2\2\2#\u0085\3"+
		"\2\2\2%\u0089\3\2\2\2\'\u008d\3\2\2\2)\u0091\3\2\2\2+\u0095\3\2\2\2-\u0099"+
		"\3\2\2\2/\u009d\3\2\2\2\61\u00a1\3\2\2\2\63\u00a5\3\2\2\2\65\u00a9\3\2"+
		"\2\2\67\u00af\3\2\2\29\u00b4\3\2\2\2;\u00bb\3\2\2\2=\u00bd\3\2\2\2?\u00cf"+
		"\3\2\2\2A\u00d2\3\2\2\2C\u00d8\3\2\2\2EF\7r\2\2FG\7w\2\2GH\7u\2\2HI\7"+
		"j\2\2I\4\3\2\2\2JK\7r\2\2KL\7q\2\2LM\7r\2\2M\6\3\2\2\2NO\7c\2\2OP\7f\2"+
		"\2PQ\7f\2\2Q\b\3\2\2\2RS\7u\2\2ST\7w\2\2TU\7d\2\2U\n\3\2\2\2VW\7o\2\2"+
		"WX\7w\2\2XY\7n\2\2YZ\7v\2\2Z\f\3\2\2\2[\\\7f\2\2\\]\7k\2\2]^\7x\2\2^\16"+
		"\3\2\2\2_`\7u\2\2`a\7y\2\2a\20\3\2\2\2bc\7n\2\2cd\7y\2\2d\22\3\2\2\2e"+
		"f\7d\2\2f\24\3\2\2\2gh\7d\2\2hi\7g\2\2ij\7s\2\2j\26\3\2\2\2kl\7d\2\2l"+
		"m\7p\2\2mn\7g\2\2no\7s\2\2o\30\3\2\2\2pq\7d\2\2qr\7i\2\2rs\7v\2\2s\32"+
		"\3\2\2\2tu\7d\2\2uv\7n\2\2vw\7g\2\2wx\7s\2\2x\34\3\2\2\2yz\7d\2\2z{\7"+
		"i\2\2{|\7g\2\2|}\7s\2\2}\36\3\2\2\2~\177\7d\2\2\177\u0080\7n\2\2\u0080"+
		"\u0081\7v\2\2\u0081 \3\2\2\2\u0082\u0083\7l\2\2\u0083\u0084\7u\2\2\u0084"+
		"\"\3\2\2\2\u0085\u0086\7n\2\2\u0086\u0087\7t\2\2\u0087\u0088\7c\2\2\u0088"+
		"$\3\2\2\2\u0089\u008a\7u\2\2\u008a\u008b\7t\2\2\u008b\u008c\7c\2\2\u008c"+
		"&\3\2\2\2\u008d\u008e\7n\2\2\u008e\u008f\7t\2\2\u008f\u0090\7x\2\2\u0090"+
		"(\3\2\2\2\u0091\u0092\7u\2\2\u0092\u0093\7t\2\2\u0093\u0094\7x\2\2\u0094"+
		"*\3\2\2\2\u0095\u0096\7n\2\2\u0096\u0097\7h\2\2\u0097\u0098\7r\2\2\u0098"+
		",\3\2\2\2\u0099\u009a\7u\2\2\u009a\u009b\7h\2\2\u009b\u009c\7r\2\2\u009c"+
		".\3\2\2\2\u009d\u009e\7e\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7r\2\2\u00a0"+
		"\60\3\2\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7j\2\2\u00a3\u00a4\7r\2\2\u00a4"+
		"\62\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7j\2\2\u00a7\u00a8\7r\2\2\u00a8"+
		"\64\3\2\2\2\u00a9\u00aa\7r\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7k\2\2\u00ac"+
		"\u00ad\7p\2\2\u00ad\u00ae\7v\2\2\u00ae\66\3\2\2\2\u00af\u00b0\7j\2\2\u00b0"+
		"\u00b1\7c\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7v\2\2\u00b38\3\2\2\2\u00b4"+
		"\u00b5\7f\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7g\2\2"+
		"\u00b8\u00b9\7v\2\2\u00b9\u00ba\7g\2\2\u00ba:\3\2\2\2\u00bb\u00bc\7<\2"+
		"\2\u00bc<\3\2\2\2\u00bd\u00c1\t\2\2\2\u00be\u00c0\t\3\2\2\u00bf\u00be"+
		"\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		">\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00d0\7\62\2\2\u00c5\u00c7\7/\2\2"+
		"\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cc"+
		"\4\63;\2\u00c9\u00cb\4\62;\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00cf\u00c4\3\2\2\2\u00cf\u00c6\3\2\2\2\u00d0@\3\2\2\2\u00d1\u00d3"+
		"\t\4\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\b!\2\2\u00d7B\3\2\2\2\u00d8"+
		"\u00d9\13\2\2\2\u00d9\u00da\b\"\3\2\u00da\u00db\3\2\2\2\u00db\u00dc\b"+
		"\"\2\2\u00dcD\3\2\2\2\b\2\u00c1\u00c6\u00cc\u00cf\u00d4\4\2\3\2\3\"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}