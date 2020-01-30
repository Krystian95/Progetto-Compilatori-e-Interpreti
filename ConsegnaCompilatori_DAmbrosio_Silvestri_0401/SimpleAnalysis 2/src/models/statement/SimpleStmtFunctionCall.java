package models.statement;

import static util.Utils.getIdFromExp;
import java.util.ArrayList;
import java.util.List;
import models.Environment;
import models.SimpleElementBase;
import models.exp.SimpleExp;
import models.stentry.SimpleFunSTEntry;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import models.type.SimpleTypeFunction;
import util.SemanticError;
import util.*;
import util.TypeCheck;

public class SimpleStmtFunctionCall extends SimpleStmt {

	private String nameFunCall;
	private List<SimpleExp> parameterFunCall;
	private int nestingLevel;
	private SimpleTypeFunction functionType;
	private List<SimpleVarSTEntry> funDecParameter;
	private SimpleFunSTEntry functionEntry;

	/**
	 * @param nameFunCall
	 * @param parameterFunCall
	 */

	public SimpleStmtFunctionCall (String nameFunCall, List<SimpleExp> parameterFunCall) {

		this.nameFunCall = nameFunCall;
		this.parameterFunCall = parameterFunCall;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<>();
		functionEntry = e.getFunctionValue(nameFunCall);
		if(functionEntry == null || functionEntry.isDeleted()) {
			result.add(new SemanticError(Strings.ErrorFunDoesntExist + nameFunCall));
		}
		else {
			this.nestingLevel = e.nestingLevel;
			SimpleType parameterCall = e.getFunctionValue(nameFunCall).getType();
			this.functionType = (SimpleTypeFunction) parameterCall;
			this.funDecParameter = functionType.getParam();

			if (parameterFunCall != null && parameterFunCall.size() == funDecParameter.size()) {
				for (int i = 0; i < funDecParameter.size(); i++) {			
					SimpleExp funCallParam = parameterFunCall.get(i);
					SimpleVarSTEntry funDecParam = funDecParameter.get(i);
					result.addAll(funCallParam.checkSemantics(e));

					if (funDecParam!=null) {
						if(funDecParam.getMode().equals("var") && funCallParam.getClass().toString().contains("Integer")) {
							result.add(new SemanticError(Strings.IntegerParInCallVar));						
						} else if(funDecParam.getMode().equals("var") && funCallParam.getClass().toString().contains("Boolean")) {
							result.add(new SemanticError(Strings.IntegerParInCallVar));						
						}

						String funCallParamId = getIdFromExp(funCallParam);
						SimpleVarSTEntry parameterEntry = e.getVariableValue(funCallParamId);
						if(parameterEntry != null) {
							if(funDecParam.getMode().equals("var") && funDecParam.isDeleted()) {
								parameterEntry.setDeleted(true);
							}
							if(funDecParam.getMode().equals("var")) {
								funDecParam.setMappedEntry(parameterEntry);
							}
						}
					}
				}
			} else {
				result.add(new SemanticError(Strings.WrongNumberPar + nameFunCall));
			}
		}
		return result;
	}

	@Override
	public String toPrint(String indent) {
		String parlstr="";
		for (SimpleExp par:parameterFunCall)
			parlstr+=par.toPrint(indent+"");		
		return indent+"Call:" + nameFunCall+ 
				"\n"+functionEntry.toPrint(indent+"")+
				parlstr;
	}

	@Override
	public SimpleType typeCheck() {

		if(functionType != null) {
			if (functionType.getParam().size() != parameterFunCall.size()) {
				System.err.println("\n>> Type Check FunctionCall: FALIED");
				System.err.println("\tYou had 1 error:");
				System.err.println("\tWrong number of parameters in the invocation of function");
				System.exit(0);
			}

			for (int i = 0; i < functionType.getParam().size(); i++) {
				SimpleType expectedType = functionType.getParam().get(i).getType();
				SimpleElementBase paramCallType = (parameterFunCall.get(i)).typeCheck();


				if(!(TypeCheck.isEqualtype(paramCallType, expectedType))) {
					System.err.println("\n>> Type Check FunctionCall: FALIED");
					System.err.println("\tYou had 1 error:");
					System.err.println("\tWrong type for " + (i+1) + "-th parameter in the invocation of function. "
							+ "Parameter " + paramCallType.toPrint(""));
					System.exit(0);
				}
			}
		}
		return null;
	}

	@Override
	public String codeGeneration() {
		String parCode="";
		
		for (int i= parameterFunCall.size()-1; i>=0; i--)
			parCode+= parameterFunCall.get(i).codeGeneration();
		String getAR="";
		
		for (int i=0; i<nestingLevel-functionEntry.getNestinglevel(); i++) 
			getAR+="lw\n";

		return "lfp\n"+ 				// CL
				parCode+
				"lfp\n"+getAR+ 		// setto AL risalendo la catena statica
				// ora recupero l'indirizzo a cui saltare e lo metto sullo stack
				"push "+functionEntry.getOffset()+"\n"+ // metto offset sullo stack
				"lfp\n"+getAR+ 		// risalgo la catena statica
				"add\n"+ 
				"lw\n"+ 				// carico sullo stack il valore all'indirizzo ottenuto
				"js\n"
				;
	}

}
