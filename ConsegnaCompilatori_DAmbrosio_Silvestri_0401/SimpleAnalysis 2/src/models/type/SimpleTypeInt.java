package models.type;

import java.util.ArrayList;
import models.Environment;
import util.SemanticError;

public class SimpleTypeInt extends SimpleType {
	
	public SimpleTypeInt() {		
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		return result;
	}

	@Override
	public String toPrint(String intType) {
		return intType+"intType\n";  
	}

	@Override
	public SimpleType typeCheck() {
		return null;
	}
	
	@Override
	public String codeGeneration() {
		return "";
	}

}
