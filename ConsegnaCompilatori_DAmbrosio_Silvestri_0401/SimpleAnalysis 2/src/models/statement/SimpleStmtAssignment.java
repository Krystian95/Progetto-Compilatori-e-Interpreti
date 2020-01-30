package models.statement;

import java.util.ArrayList;
import models.Environment;
import models.exp.SimpleExp;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import util.SemanticError;
import util.Strings;
import util.TypeCheck;

public class SimpleStmtAssignment extends SimpleStmt{

	private SimpleExp exp;
	private String id;
	private SimpleType typeId;
	private SimpleVarSTEntry varEntry;
	private int nestingLevel;

	/**
	 * @param exp
	 * @param id
	 */

	public SimpleStmtAssignment(SimpleExp exp, String id) {
		this.exp = exp;
		this.id = id;
	}

	@Override
	public String toPrint(String assignement) {
		return assignement+ "Var:" + id +"\n"
				+exp.toPrint(assignement+"  "); 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		nestingLevel = e.nestingLevel;
		//initialize result variable
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		//check for the variable
		if(!e.containsVariableInVTable("noDeclaration", id, nestingLevel) || e.isDeletedVtable(id)) {
			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + id));
		} else {
			e.setInAssignment(true);
			this.varEntry = e.getVariableValue(id);
			this.typeId = varEntry.getType();
			e.setInAssignment(false);
		}
		result.addAll(exp.checkSemantics(e));
		return result;
	}

	@Override
	public SimpleType typeCheck() {
		if(!TypeCheck.isEqualtype(exp.typeCheck(), this.typeId)) {
			System.err.println("\n>> Type Check Assignment: FALIED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatch + id);
			System.exit(0);
		} 
		return null;
	}

	@Override
	public String codeGeneration() {
		String getAR="";
		for (int i=0; i<nestingLevel-varEntry.getNestinglevel(); i++) 
			getAR += "lw\n";
		String code = exp.codeGeneration()
				+ "lfp\n"
				+ getAR
				+ "push " + varEntry.getOffset() + "\n"
				+ "add\n"
				+ "sw\n";

		if(varEntry.getMappedEntry() != null && varEntry.getMode().equals("var")) {
			String getARMappedEntry="";
			for (int i=0; i<nestingLevel-varEntry.getMappedEntry().getNestinglevel(); i++) 
				getARMappedEntry += "lw\n";
			code	+= "\n"
					+ "push " + varEntry.getOffset() + "\n"
					+ "lfp\n"
					+ getAR
					+ "add\n"
					+ "lw\n"
					+ "\n"
					+ "lfp\n"
					+ getARMappedEntry
					+ "lw\n"
					+ "push " + varEntry.getMappedEntry().getOffset() + "\n"
					+ "add\n"
					+ "sw\n";
		}
		return code;
	} 
}
