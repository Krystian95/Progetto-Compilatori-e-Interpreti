package models.value;

import java.util.ArrayList;

import models.Environment;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import util.SemanticError;
import util.Strings;

public class SimpleValueId extends SimpleValue {

	String value;
	private SimpleVarSTEntry StVarEntry;
	int nestingLevel;

	public SimpleValueId(String value) {
		this.value = value;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		nestingLevel = e.nestingLevel;		
		//check for the variable
		if(!e.containsVariableInVTable("noDeclaration", value, nestingLevel) || e.isDeletedVtable(value)) {
			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + value));
		} else if (e.isInVarDeclaration()) {
			System.out.println(e.isInVarDeclaration());
			if (e.getIdVarDeclaration().equals(value)) {
				System.out.println("e.getIdVarDeclaration(): " + e.getIdVarDeclaration() + " value: " + value);
				result.add(new SemanticError(Strings.ErrorVariableNotInitialized + value));
			}	
			this.StVarEntry = e.getVariableValue(this.value);
		} else {
			this.StVarEntry = e.getVariableValue(this.value);
		}
		return result;
	}

	public String getValue(){
		return value;
	}

	@Override
	public String toPrint(String s) {
		return s+"Id:" + value + "\n";  
	}

	@Override
	public SimpleType typeCheck() {
		return StVarEntry.getType();
	}

	@Override
	public String codeGeneration() {
		String getAR="";


		for (int i=0; i<nestingLevel-StVarEntry.getNestinglevel(); i++) {
			getAR+="lw\n";
		}

		return "push "+ StVarEntry.getOffset()+"\n"+ //metto offset sullo stack
				"lfp\n"+
				getAR+ 								  //risalgo la catena statica
				"add\n"+ 
				"lw\n"; 							  //carico sullo stack il valore all'indirizzo ottenuto

	}
}
