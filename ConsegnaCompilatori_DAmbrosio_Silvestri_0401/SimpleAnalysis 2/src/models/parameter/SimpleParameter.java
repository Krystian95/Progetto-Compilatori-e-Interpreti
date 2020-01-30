package models.parameter;

import java.util.ArrayList;
import models.Environment;
import models.SimpleElementBase;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import util.SemanticError;
import util.Strings;

public class SimpleParameter extends SimpleElementBase {

	private SimpleType typeParameter;
	private String nameParameter;
	private String modeParameter;
	private SimpleVarSTEntry parEntry;

	/**
	 * @param typeParameter
	 * @param nameParameter
	 * @param modeParameter
	 */

	public SimpleParameter(SimpleType typeParameter, String modeParameter, String nameParameter) {
		this.typeParameter = typeParameter;
		this.nameParameter = nameParameter;
		this.modeParameter = modeParameter; 

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {

		ArrayList<SemanticError> result = new ArrayList<>();
		int nestingLevel = e.nestingLevel;

		if (e.containsVariableInFTable(nameParameter, nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorParameterFunction + nameParameter));
		} else if (e.containsVariableInVTable("declaration", nameParameter, nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorParameterDeclared + nameParameter));
		} else {
			e.addVariable(nameParameter, new SimpleVarSTEntry(nestingLevel, typeParameter, e.incremetParOffset(), nameParameter));
			this.parEntry = e.getVariableValue(nameParameter);
			this.parEntry.setMode(modeParameter);
		}

		return result;
	}

	public String getNameParameter(){
		return nameParameter;
	}


	@Override
	public SimpleType typeCheck() {
		return null;		
	}

	@Override
	public String toPrint(String parameter) {
		return parameter + "Par: " + nameParameter +"\n" +
				parameter + "Mode: " + modeParameter.toString() + "\n" +
				parameter + "Type: " + typeParameter.toPrint("") ;
	}

	@Override
	public String codeGeneration() {
		return "";
	}
}
