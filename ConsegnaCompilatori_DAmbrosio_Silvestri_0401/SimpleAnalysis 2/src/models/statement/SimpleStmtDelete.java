package models.statement;

import java.util.ArrayList;

import models.Environment;
import models.stentry.SimpleFunSTEntry;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import util.SemanticError;
import util.Strings;


public class SimpleStmtDelete extends SimpleStmt {

	private String id;
	private SimpleVarSTEntry varEntryDelete;
	private SimpleFunSTEntry funEntryDelete;

	/**
	 * Creates a delete statement
	 * @param id the variable we want to delete
	 */
	
	public SimpleStmtDelete(String id) {
		this.id = id;
	}

	@Override
	public String toPrint(String delete) {
		return delete+"Deletion: " + id+"\n";
	}

	/*
	 * Checks if the variable in use exists. if it doesn't then add an error
	 */

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {

		//create resultult list
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		//check for the variable
		if(!e.containsVariableInVTable("noDeclaration", id, e.nestingLevel) && !e.containsVariableInFTable(id, e.nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorFunctionDoesntExist + id));
		} else {			
			this.varEntryDelete = e.getVariableValue(id);
			this.funEntryDelete = e.getFunctionValue(id);		
			if(this.varEntryDelete == null) {
				if(!this.funEntryDelete.isDeleted() ) {
					this.funEntryDelete.setDeleted(true);
				} else {
					result.add(new SemanticError(Strings.ErrorFunDoesntExist + id));
				}
			} else if (this.funEntryDelete == null){
				if(!this.varEntryDelete.isDeleted()) {
					this.varEntryDelete.setDeleted(true);
				} else {
					result.add(new SemanticError(Strings.ErrorVariableDoesntExist + id));
				}
			}
		}
		return result;
	}

	@Override
	public String codeGeneration() {
		return "";
	}

	@Override
	public SimpleType typeCheck() {
		return null;
	}
}
