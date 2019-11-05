package util;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AppLexicalError {

	public static int LexicalTokens = 0;
	public static int ErrorLexicalTokens = 0;

	public static class ThrowingLexicalError extends BaseErrorListener {

		public static final ThrowingLexicalError INSTANCE = new ThrowingLexicalError();

		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
				throws ParseCancellationException {
			System.err.println("You had 1 lexical error:");
			System.err.println("\tline " + line + ":" + charPositionInLine + " " + msg + "\n");
			System.exit(0);
		}
	}
}