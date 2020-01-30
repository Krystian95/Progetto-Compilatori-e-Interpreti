package models.value;

import java.util.ArrayList;
import models.Environment;
import models.exp.SimpleExp;
import models.type.SimpleType;
import util.SemanticError;

public class SimpleValueExp extends SimpleValue {

	SimpleExp valueExp;
	
	public SimpleValueExp(SimpleExp valueExp) {
		this.valueExp = valueExp;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		return valueExp.checkSemantics(e);
	}

	@Override
	public String toPrint(String indent) {
		return valueExp.toPrint("");
	}

	@Override
	public SimpleType typeCheck() {	
		return valueExp.typeCheck();
	}
	
	@Override
	public String codeGeneration() {
		return valueExp.codeGeneration();
	}

}
