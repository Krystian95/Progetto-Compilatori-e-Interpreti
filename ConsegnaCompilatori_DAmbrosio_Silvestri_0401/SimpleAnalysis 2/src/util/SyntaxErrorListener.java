package util;

import java.util.ArrayList;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import util.SyntaxError;

public class SyntaxErrorListener extends BaseErrorListener {
	
	ArrayList<SyntaxError> syntaxErrors = new ArrayList<>();
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol,
			int line, int charPositionInLine,
			String msg, RecognitionException e)
	{
		syntaxErrors.add(new SyntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
	}
	
	public ArrayList<SyntaxError> getSyntaxErrors() {
		return syntaxErrors;
	}
	
}