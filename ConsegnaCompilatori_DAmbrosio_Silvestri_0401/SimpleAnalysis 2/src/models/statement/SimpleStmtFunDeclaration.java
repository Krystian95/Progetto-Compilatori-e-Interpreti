package models.statement;

import java.util.ArrayList;
import java.util.List;
import models.Environment;
import models.parameter.SimpleParameter;
import models.stentry.SimpleFunSTEntry;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import models.type.SimpleTypeFunction;
import util.CodeGeneration;
import util.SemanticError;
import util.Strings;

public class SimpleStmtFunDeclaration extends SimpleStmt{

	private String nameFun;
	private List<SimpleParameter> parListFun;
	private SimpleStmtBlock bodyFun;
	private SimpleFunSTEntry functionEntry;

	public SimpleStmtFunDeclaration (String nameFun, List<SimpleParameter> parListFun, SimpleStmtBlock bodyFun) {
		this.nameFun = nameFun;
		this.parListFun = parListFun;
		this.bodyFun = bodyFun;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<>();
		int nestingLevel = e .nestingLevel;
		if(e.containsVariableInFTable(nameFun, nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorFunDeclared + nameFun));
		} else if(e.containsVariableInVTable("declaration",nameFun, nestingLevel)) {
			result.add(new SemanticError(Strings.ErrorFunVarDeclared + nameFun));
		} else {
			SimpleTypeFunction functionType = new SimpleTypeFunction(new ArrayList<>(), bodyFun);
			e.addFunction(nameFun, new SimpleFunSTEntry(nestingLevel, functionType, e.decrementOffset(), nameFun));
			this.functionEntry = e.getFunctionValue(nameFun);
			e.openScope();
			result.addAll(checkSemanticParameter(e));
			e.incrementVarDeclaration(nestingLevel);
			e.setInFunDeclaration(true);
			result.addAll(bodyFun.checkSemantics(e));
			e.closeScope();
			e.setInFunDeclaration(false);
		}

		return result;
	}

	private ArrayList<SemanticError> checkSemanticParameter(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<>();
		SimpleTypeFunction functionType = (SimpleTypeFunction) functionEntry.getType();
		for (SimpleParameter param : parListFun) {
			result.addAll(param.checkSemantics(e));
			functionType.addParam((SimpleVarSTEntry) e.getVariableValueLocal(param.getNameParameter()));		
		}
		return result;
	}

	@Override
	public SimpleType typeCheck() {
		bodyFun.typeCheck();
		return null;
	}

	@Override
	public String toPrint(String s) {
		String parlstr="";
		for (SimpleParameter par:parListFun)
			parlstr+=par.toPrint(s+"");

		return s+"Fun:" + nameFun 
				+"\n"+parlstr+
				bodyFun.toPrint(s+"") ; 
	}

	@Override
	public String codeGeneration() {
		String popParlstr="";
		for (SimpleParameter par:parListFun) {
			popParlstr+="pop\n";
		}

		String funLabel= CodeGeneration.freshFunLabel(); 

		CodeGeneration.putCode(
				funLabel+ ":\n"
						+ "cfp\n"              		//move $fp a $sp
						+ "lra\n"               	//push $ra
						+ bodyFun.codeGeneration() 	//push funbody
						+ "sra\n"             		//$ra <- top
						+ "pop\n"           	    //pop $fp
						+ popParlstr                //pop parlist
						+ "sfp\n"            	 	//$fp <- top
						+ "lra\n"             	    //push $ra
						+ "js\n"              	    //jump $ra
				);

		return "push "+ funLabel +"\n";
	}
}
