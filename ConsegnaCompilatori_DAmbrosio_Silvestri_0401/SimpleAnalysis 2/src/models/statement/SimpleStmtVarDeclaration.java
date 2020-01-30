package models.statement;

import java.util.ArrayList;
import models.Environment;
import models.exp.SimpleExp;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import util.SemanticError;
import util.Strings;
import util.TypeCheck;

public class SimpleStmtVarDeclaration extends SimpleStmt{

	private SimpleType type;
	private String id;
	private SimpleExp exp;
	
	/**
	 * @param type
	 * @param id
	 * @param exp2
	 */

	public SimpleStmtVarDeclaration(SimpleType type, String id, SimpleExp exp) {
		this.type = type;
		this.id = id;
		this.exp = exp;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		//initialize result variable
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		int nestingLevel = e.getNestingLevel();
		if(e.containsVariableInVTable("declaration", id, nestingLevel) && !(e.isDeletedVtable(id))) {
			result.add(new SemanticError(Strings.ErrorVarDeclared + id));
		} else if (e.containsVariableInFTable(id, nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorVarDeclared + id));			
		} else {
			e.addVariable(id, new SimpleVarSTEntry(nestingLevel, type, e.decrementOffset(), id));
			e.setInVarDeclaration(true);
			e.incrementVarDeclaration(nestingLevel);
			e.setIdVarDeclaration(id);
		} 
		result.addAll(exp.checkSemantics(e));
		e.setInVarDeclaration(false);
		return result;
	}

	@Override
	public String toPrint(String varDec) {
		return varDec+"Var:" + id +"\n"
				+type.toPrint(varDec+"  ")  
				+exp.toPrint(varDec+"  ");
	}

	@Override
	public SimpleType typeCheck() {
		if (! (TypeCheck.isEqualtype(exp.typeCheck(),this.type)) ){  
			System.err.println("\n>> Type Check VarDeclaration: FAILED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatch + id);
			System.exit(0);
		} 
		return null;
	}

	@Override
	public String codeGeneration() {
		return exp.codeGeneration();
	}
}
