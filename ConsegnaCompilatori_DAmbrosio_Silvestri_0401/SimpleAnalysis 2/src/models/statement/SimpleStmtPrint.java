package models.statement;

import java.util.ArrayList;
import models.Environment;
import models.exp.SimpleExp;
import models.type.SimpleType;
import util.SemanticError;
import util.CodeGeneration;

public class SimpleStmtPrint extends SimpleStmt {

	private SimpleExp exp;

	public SimpleStmtPrint(SimpleExp exp) {
		this.exp = exp;
	}

	@Override
	public String toPrint(String s) {
		return s + "Print\n" +
				"\t"+ exp.toPrint(s+"  ");
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		return  exp.checkSemantics(e);
	}

	@Override
	public SimpleType typeCheck() {
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		return exp.codeGeneration()+
				CodeGeneration.print()+
				"pop\n";
	}
}
