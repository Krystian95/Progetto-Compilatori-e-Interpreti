// Generated from FOOL.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'"
	};
	public static final String[] ruleNames = {
		"SEMIC", "COLON", "COMMA", "GE", "LE", "NOT", "AND", "OR", "EQ", "ASM", 
		"PLUS", "MINUS", "TIMES", "DIV", "TRUE", "FALSE", "LPAR", "RPAR", "CLPAR", 
		"CRPAR", "IF", "THEN", "ELSE", "LET", "IN", "VAR", "FUN", "INT", "BOOL", 
		"CLASS", "EXTENDS", "DOT", "VOID", "NULL", "NEW", "STDLIB", "DIGIT", "INTEGER", 
		"CHAR", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS"
	};


	   //there is a much better way to do this, check the ANTLR guide
	   //I will leave it like this for now just becasue it is quick
	   //but it doesn't work well
	   //public int lexicalErrors=0;


	public FOOLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5"+
		"\7j\n\7\3\b\3\b\3\b\3\b\3\b\5\bq\n\b\3\t\3\t\3\t\3\t\5\tw\n\t\3\n\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#"+
		"\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3\'\6\'\u00e5\n\'\r\'\16\'"+
		"\u00e6\3(\3(\3)\3)\3)\7)\u00ee\n)\f)\16)\u00f1\13)\3*\3*\3*\3*\3+\3+\3"+
		"+\3+\7+\u00fb\n+\f+\16+\u00fe\13+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\7,"+
		"\u010b\n,\f,\16,\u010e\13,\3,\3,\3,\3,\3,\2\2-\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\'"+
		"O\2Q(S)U*W+\3\2\b\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\4\2,,\61\61"+
		"\3\2,,\3\2\61\61\u011c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2M\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\3Y\3\2\2\2\5[\3\2\2\2\7]\3\2\2\2\t_\3\2\2\2\13b\3"+
		"\2\2\2\ri\3\2\2\2\17p\3\2\2\2\21v\3\2\2\2\23x\3\2\2\2\25{\3\2\2\2\27}"+
		"\3\2\2\2\31\177\3\2\2\2\33\u0081\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2"+
		"\2\2!\u008a\3\2\2\2#\u0090\3\2\2\2%\u0092\3\2\2\2\'\u0094\3\2\2\2)\u0096"+
		"\3\2\2\2+\u0098\3\2\2\2-\u009b\3\2\2\2/\u00a0\3\2\2\2\61\u00a5\3\2\2\2"+
		"\63\u00a9\3\2\2\2\65\u00ac\3\2\2\2\67\u00b0\3\2\2\29\u00b4\3\2\2\2;\u00b8"+
		"\3\2\2\2=\u00bd\3\2\2\2?\u00c3\3\2\2\2A\u00cb\3\2\2\2C\u00cd\3\2\2\2E"+
		"\u00d2\3\2\2\2G\u00d7\3\2\2\2I\u00db\3\2\2\2K\u00e1\3\2\2\2M\u00e4\3\2"+
		"\2\2O\u00e8\3\2\2\2Q\u00ea\3\2\2\2S\u00f2\3\2\2\2U\u00f6\3\2\2\2W\u0101"+
		"\3\2\2\2YZ\7=\2\2Z\4\3\2\2\2[\\\7<\2\2\\\6\3\2\2\2]^\7.\2\2^\b\3\2\2\2"+
		"_`\7@\2\2`a\7?\2\2a\n\3\2\2\2bc\7>\2\2cd\7?\2\2d\f\3\2\2\2ef\7p\2\2fg"+
		"\7q\2\2gj\7v\2\2hj\7#\2\2ie\3\2\2\2ih\3\2\2\2j\16\3\2\2\2kl\7c\2\2lm\7"+
		"p\2\2mq\7f\2\2no\7(\2\2oq\7(\2\2pk\3\2\2\2pn\3\2\2\2q\20\3\2\2\2rs\7q"+
		"\2\2sw\7t\2\2tu\7~\2\2uw\7~\2\2vr\3\2\2\2vt\3\2\2\2w\22\3\2\2\2xy\7?\2"+
		"\2yz\7?\2\2z\24\3\2\2\2{|\7?\2\2|\26\3\2\2\2}~\7-\2\2~\30\3\2\2\2\177"+
		"\u0080\7/\2\2\u0080\32\3\2\2\2\u0081\u0082\7,\2\2\u0082\34\3\2\2\2\u0083"+
		"\u0084\7\61\2\2\u0084\36\3\2\2\2\u0085\u0086\7v\2\2\u0086\u0087\7t\2\2"+
		"\u0087\u0088\7w\2\2\u0088\u0089\7g\2\2\u0089 \3\2\2\2\u008a\u008b\7h\2"+
		"\2\u008b\u008c\7c\2\2\u008c\u008d\7n\2\2\u008d\u008e\7u\2\2\u008e\u008f"+
		"\7g\2\2\u008f\"\3\2\2\2\u0090\u0091\7*\2\2\u0091$\3\2\2\2\u0092\u0093"+
		"\7+\2\2\u0093&\3\2\2\2\u0094\u0095\7}\2\2\u0095(\3\2\2\2\u0096\u0097\7"+
		"\177\2\2\u0097*\3\2\2\2\u0098\u0099\7k\2\2\u0099\u009a\7h\2\2\u009a,\3"+
		"\2\2\2\u009b\u009c\7v\2\2\u009c\u009d\7j\2\2\u009d\u009e\7g\2\2\u009e"+
		"\u009f\7p\2\2\u009f.\3\2\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7n\2\2\u00a2"+
		"\u00a3\7u\2\2\u00a3\u00a4\7g\2\2\u00a4\60\3\2\2\2\u00a5\u00a6\7n\2\2\u00a6"+
		"\u00a7\7g\2\2\u00a7\u00a8\7v\2\2\u00a8\62\3\2\2\2\u00a9\u00aa\7k\2\2\u00aa"+
		"\u00ab\7p\2\2\u00ab\64\3\2\2\2\u00ac\u00ad\7x\2\2\u00ad\u00ae\7c\2\2\u00ae"+
		"\u00af\7t\2\2\u00af\66\3\2\2\2\u00b0\u00b1\7h\2\2\u00b1\u00b2\7w\2\2\u00b2"+
		"\u00b3\7p\2\2\u00b38\3\2\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7p\2\2\u00b6"+
		"\u00b7\7v\2\2\u00b7:\3\2\2\2\u00b8\u00b9\7d\2\2\u00b9\u00ba\7q\2\2\u00ba"+
		"\u00bb\7q\2\2\u00bb\u00bc\7n\2\2\u00bc<\3\2\2\2\u00bd\u00be\7e\2\2\u00be"+
		"\u00bf\7n\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7u\2\2"+
		"\u00c2>\3\2\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7z\2\2\u00c5\u00c6\7v\2"+
		"\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7f\2\2\u00c9\u00ca"+
		"\7u\2\2\u00ca@\3\2\2\2\u00cb\u00cc\7\60\2\2\u00ccB\3\2\2\2\u00cd\u00ce"+
		"\7x\2\2\u00ce\u00cf\7q\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7f\2\2\u00d1"+
		"D\3\2\2\2\u00d2\u00d3\7p\2\2\u00d3\u00d4\7w\2\2\u00d4\u00d5\7n\2\2\u00d5"+
		"\u00d6\7n\2\2\u00d6F\3\2\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\u00da\7y\2\2\u00daH\3\2\2\2\u00db\u00dc\7r\2\2\u00dc\u00dd\7t\2\2\u00dd"+
		"\u00de\7k\2\2\u00de\u00df\7p\2\2\u00df\u00e0\7v\2\2\u00e0J\3\2\2\2\u00e1"+
		"\u00e2\4\62;\2\u00e2L\3\2\2\2\u00e3\u00e5\5K&\2\u00e4\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7N\3\2\2\2"+
		"\u00e8\u00e9\t\2\2\2\u00e9P\3\2\2\2\u00ea\u00ef\5O(\2\u00eb\u00ee\5O("+
		"\2\u00ec\u00ee\5K&\2\u00ed\u00eb\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1"+
		"\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0R\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f3\t\3\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\b*"+
		"\2\2\u00f5T\3\2\2\2\u00f6\u00f7\7\61\2\2\u00f7\u00f8\7\61\2\2\u00f8\u00fc"+
		"\3\2\2\2\u00f9\u00fb\n\4\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc"+
		"\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc\3\2"+
		"\2\2\u00ff\u0100\b+\2\2\u0100V\3\2\2\2\u0101\u0102\7\61\2\2\u0102\u0103"+
		"\7,\2\2\u0103\u010c\3\2\2\2\u0104\u010b\n\5\2\2\u0105\u0106\7\61\2\2\u0106"+
		"\u010b\n\6\2\2\u0107\u0108\7,\2\2\u0108\u010b\n\7\2\2\u0109\u010b\5W,"+
		"\2\u010a\u0104\3\2\2\2\u010a\u0105\3\2\2\2\u010a\u0107\3\2\2\2\u010a\u0109"+
		"\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\7,\2\2\u0110\u0111\7\61"+
		"\2\2\u0111\u0112\3\2\2\2\u0112\u0113\b,\2\2\u0113X\3\2\2\2\f\2ipv\u00e6"+
		"\u00ed\u00ef\u00fc\u010a\u010c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}