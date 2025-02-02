// Generated from C:/Users/Laura/eclipse-workspace/CEI-FOOL/src/parser\SVM.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ALLOC=3, REMOVE=4, ADD=5, SUB=6, MULT=7, DIV=8, LOADW=9, 
		STOREW=10, LOADF=11, STOREF=12, BRANCH=13, BRANCHEQ=14, BRANCHGREATEREQ=15, 
		BRANCHLESSEQ=16, JS=17, JDT=18, LOADRA=19, STORERA=20, LOADRV=21, STORERV=22, 
		LOADFP=23, STOREFP=24, COPYFP=25, LOADHP=26, STOREHP=27, LOADDP=28, STOREDP=29, 
		LOADIO=30, STOREIO=31, LOADOP=32, STOREOP=33, PRINT=34, HALT=35, COL=36, 
		LABEL=37, NUMBER=38, WHITESP=39;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PUSH", "POP", "ALLOC", "REMOVE", "ADD", "SUB", "MULT", "DIV", "LOADW", 
		"STOREW", "LOADF", "STOREF", "BRANCH", "BRANCHEQ", "BRANCHGREATEREQ", 
		"BRANCHLESSEQ", "JS", "JDT", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "LOADDP", "STOREDP", 
		"LOADIO", "STOREIO", "LOADOP", "STOREOP", "PRINT", "HALT", "COL", "LABEL", 
		"NUMBER", "WHITESP"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'push'", "'pop'", "'alloc'", "'remove'", "'add'", "'sub'", "'mult'", 
		"'div'", "'lw'", "'sw'", "'lf'", "'sf'", "'b'", "'beq'", "'bgeq'", "'bleq'", 
		"'js'", "'jdt'", "'lra'", "'sra'", "'lrv'", "'srv'", "'lfp'", "'sfp'", 
		"'cfp'", "'lhp'", "'shp'", "'ldp'", "'sdp'", "'lio'", "'sio'", "'lop'", 
		"'sop'", "'print'", "'halt'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PUSH", "POP", "ALLOC", "REMOVE", "ADD", "SUB", "MULT", "DIV", "LOADW", 
		"STOREW", "LOADF", "STOREF", "BRANCH", "BRANCHEQ", "BRANCHGREATEREQ", 
		"BRANCHLESSEQ", "JS", "JDT", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "LOADDP", "STOREDP", 
		"LOADIO", "STOREIO", "LOADOP", "STOREOP", "PRINT", "HALT", "COL", "LABEL", 
		"NUMBER", "WHITESP"
	};
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


	    //there is a much better way to do this, check the ANTLR guide
	    //I will leave it like this for now just becasue it is quick
	    //but it doesn't work well
	    //public int lexicalErrors=0;


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u00ff\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3&\3&\7"+
		"&\u00e7\n&\f&\16&\u00ea\13&\3\'\3\'\5\'\u00ee\n\'\3\'\3\'\7\'\u00f2\n"+
		"\'\f\'\16\'\u00f5\13\'\5\'\u00f7\n\'\3(\6(\u00fa\n(\r(\16(\u00fb\3(\3"+
		"(\2\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E$G%I&K\'M(O)\3\2\5\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17"+
		"\17\"\"\2\u0103\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2"+
		"\2\5V\3\2\2\2\7Z\3\2\2\2\t`\3\2\2\2\13g\3\2\2\2\rk\3\2\2\2\17o\3\2\2\2"+
		"\21t\3\2\2\2\23x\3\2\2\2\25{\3\2\2\2\27~\3\2\2\2\31\u0081\3\2\2\2\33\u0084"+
		"\3\2\2\2\35\u0086\3\2\2\2\37\u008a\3\2\2\2!\u008f\3\2\2\2#\u0094\3\2\2"+
		"\2%\u0097\3\2\2\2\'\u009b\3\2\2\2)\u009f\3\2\2\2+\u00a3\3\2\2\2-\u00a7"+
		"\3\2\2\2/\u00ab\3\2\2\2\61\u00af\3\2\2\2\63\u00b3\3\2\2\2\65\u00b7\3\2"+
		"\2\2\67\u00bb\3\2\2\29\u00bf\3\2\2\2;\u00c3\3\2\2\2=\u00c7\3\2\2\2?\u00cb"+
		"\3\2\2\2A\u00cf\3\2\2\2C\u00d3\3\2\2\2E\u00d7\3\2\2\2G\u00dd\3\2\2\2I"+
		"\u00e2\3\2\2\2K\u00e4\3\2\2\2M\u00f6\3\2\2\2O\u00f9\3\2\2\2QR\7r\2\2R"+
		"S\7w\2\2ST\7u\2\2TU\7j\2\2U\4\3\2\2\2VW\7r\2\2WX\7q\2\2XY\7r\2\2Y\6\3"+
		"\2\2\2Z[\7c\2\2[\\\7n\2\2\\]\7n\2\2]^\7q\2\2^_\7e\2\2_\b\3\2\2\2`a\7t"+
		"\2\2ab\7g\2\2bc\7o\2\2cd\7q\2\2de\7x\2\2ef\7g\2\2f\n\3\2\2\2gh\7c\2\2"+
		"hi\7f\2\2ij\7f\2\2j\f\3\2\2\2kl\7u\2\2lm\7w\2\2mn\7d\2\2n\16\3\2\2\2o"+
		"p\7o\2\2pq\7w\2\2qr\7n\2\2rs\7v\2\2s\20\3\2\2\2tu\7f\2\2uv\7k\2\2vw\7"+
		"x\2\2w\22\3\2\2\2xy\7n\2\2yz\7y\2\2z\24\3\2\2\2{|\7u\2\2|}\7y\2\2}\26"+
		"\3\2\2\2~\177\7n\2\2\177\u0080\7h\2\2\u0080\30\3\2\2\2\u0081\u0082\7u"+
		"\2\2\u0082\u0083\7h\2\2\u0083\32\3\2\2\2\u0084\u0085\7d\2\2\u0085\34\3"+
		"\2\2\2\u0086\u0087\7d\2\2\u0087\u0088\7g\2\2\u0088\u0089\7s\2\2\u0089"+
		"\36\3\2\2\2\u008a\u008b\7d\2\2\u008b\u008c\7i\2\2\u008c\u008d\7g\2\2\u008d"+
		"\u008e\7s\2\2\u008e \3\2\2\2\u008f\u0090\7d\2\2\u0090\u0091\7n\2\2\u0091"+
		"\u0092\7g\2\2\u0092\u0093\7s\2\2\u0093\"\3\2\2\2\u0094\u0095\7l\2\2\u0095"+
		"\u0096\7u\2\2\u0096$\3\2\2\2\u0097\u0098\7l\2\2\u0098\u0099\7f\2\2\u0099"+
		"\u009a\7v\2\2\u009a&\3\2\2\2\u009b\u009c\7n\2\2\u009c\u009d\7t\2\2\u009d"+
		"\u009e\7c\2\2\u009e(\3\2\2\2\u009f\u00a0\7u\2\2\u00a0\u00a1\7t\2\2\u00a1"+
		"\u00a2\7c\2\2\u00a2*\3\2\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7t\2\2\u00a5"+
		"\u00a6\7x\2\2\u00a6,\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9\7t\2\2\u00a9"+
		"\u00aa\7x\2\2\u00aa.\3\2\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7h\2\2\u00ad"+
		"\u00ae\7r\2\2\u00ae\60\3\2\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1\7h\2\2\u00b1"+
		"\u00b2\7r\2\2\u00b2\62\3\2\2\2\u00b3\u00b4\7e\2\2\u00b4\u00b5\7h\2\2\u00b5"+
		"\u00b6\7r\2\2\u00b6\64\3\2\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7j\2\2\u00b9"+
		"\u00ba\7r\2\2\u00ba\66\3\2\2\2\u00bb\u00bc\7u\2\2\u00bc\u00bd\7j\2\2\u00bd"+
		"\u00be\7r\2\2\u00be8\3\2\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7f\2\2\u00c1"+
		"\u00c2\7r\2\2\u00c2:\3\2\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7f\2\2\u00c5"+
		"\u00c6\7r\2\2\u00c6<\3\2\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7k\2\2\u00c9"+
		"\u00ca\7q\2\2\u00ca>\3\2\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7k\2\2\u00cd"+
		"\u00ce\7q\2\2\u00ce@\3\2\2\2\u00cf\u00d0\7n\2\2\u00d0\u00d1\7q\2\2\u00d1"+
		"\u00d2\7r\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7u\2\2\u00d4\u00d5\7q\2\2\u00d5"+
		"\u00d6\7r\2\2\u00d6D\3\2\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7t\2\2\u00d9"+
		"\u00da\7k\2\2\u00da\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dcF\3\2\2\2\u00dd"+
		"\u00de\7j\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7v\2\2"+
		"\u00e1H\3\2\2\2\u00e2\u00e3\7<\2\2\u00e3J\3\2\2\2\u00e4\u00e8\t\2\2\2"+
		"\u00e5\u00e7\t\3\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9L\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00f7\7\62\2\2\u00ec\u00ee\7/\2\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f3\4\63;\2\u00f0\u00f2\4\62;\2\u00f1"+
		"\u00f0\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00eb\3\2\2\2\u00f6"+
		"\u00ed\3\2\2\2\u00f7N\3\2\2\2\u00f8\u00fa\t\4\2\2\u00f9\u00f8\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\u00fe\b(\2\2\u00feP\3\2\2\2\b\2\u00e8\u00ed\u00f3\u00f6"+
		"\u00fb\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}