package models.value;

import java.util.ArrayList;
import models.Environment;
import models.type.SimpleType;
import models.type.SimpleTypeBool;
import util.SemanticError;

public class SimpleValueBoolean extends SimpleValue {

	boolean valueBool;

	public SimpleValueBoolean(boolean valueBool) {
		this.valueBool = valueBool;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		return result;
	}

	@Override
	public String toPrint(String bool) {
		if (valueBool) return bool+"Bool:true\n";
		else return bool+"Bool:false\n"; 
	}

	@Override
	public SimpleType typeCheck() {
		return new SimpleTypeBool();
	}

	@Override
	public String codeGeneration() {
		int bool;

		if (valueBool) {
			bool = 1;
		} else {
			bool = 0;					
		}

		return "push "+bool+"\n";
	}
}
