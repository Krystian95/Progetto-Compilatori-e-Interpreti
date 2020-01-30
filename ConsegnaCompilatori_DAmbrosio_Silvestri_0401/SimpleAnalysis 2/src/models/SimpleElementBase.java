package models;

import java.util.ArrayList;
import models.type.SimpleType;
import util.SemanticError;

public abstract class SimpleElementBase {

	public abstract ArrayList<SemanticError> checkSemantics(Environment e);
	
	public abstract String toPrint(String value);
	
	public abstract String codeGeneration();
	
	public abstract SimpleType typeCheck();
}
