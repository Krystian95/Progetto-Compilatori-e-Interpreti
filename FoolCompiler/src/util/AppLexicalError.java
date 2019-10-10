package util;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

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

	/*public static void main(String[] args) throws Exception, RecognitionException{

		FileWriter Writer = new FileWriter("output.txt", true);
		String inputFile = "test.spl";

		FileInputStream is = new FileInputStream(inputFile);
		ANTLRInputStream input = new ANTLRInputStream(is);

		SimpleLexer lexer = new SimpleLexer(input);
		System.out.println("Input: " + input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		System.out.println(tokens.getText());
		SimpleParser parser = new SimpleParser(tokens);    	

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingLexicalError.INSTANCE);

		ParseTree tree = parser.block();
		System.out.print(tree.toStringTree(parser));
		Writer.close();
	}*/
}