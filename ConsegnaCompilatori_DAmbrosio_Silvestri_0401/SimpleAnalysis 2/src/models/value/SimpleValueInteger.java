package models.value;

import java.util.ArrayList;
import models.Environment;
import models.type.*;
import models.type.SimpleType;
import util.SemanticError;

public class SimpleValueInteger extends SimpleValue {
	
	int valueInt;
	
	public SimpleValueInteger(int valueInt) {
		this.valueInt = valueInt;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		return result;
	}
	
	@Override
	public String toPrint(String integer) {
		 return integer+"Int: " + valueInt +"\n";  
	}

	@Override
	public SimpleType typeCheck() {
		return new SimpleTypeInt();
	}
	
	@Override
	public String codeGeneration() {
		return "push "+valueInt+"\n";
	}
}