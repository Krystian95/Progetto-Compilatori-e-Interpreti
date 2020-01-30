package models.type;

import java.util.ArrayList;
import models.Environment;
import util.SemanticError;

public class SimpleTypeBool extends SimpleType {
	
	public SimpleTypeBool() {	
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		return result;
	}
	
	@Override
	public SimpleType typeCheck() {
		return null;
	}

	@Override
	public String toPrint(String boolType) {
		return boolType+"BoolType\n";  
	}

	@Override
	public String codeGeneration() {
		return "";
	}

}
