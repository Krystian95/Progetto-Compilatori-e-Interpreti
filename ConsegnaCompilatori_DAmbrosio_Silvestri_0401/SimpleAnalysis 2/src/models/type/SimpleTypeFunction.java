package models.type;

import java.util.ArrayList;
import java.util.List;
import models.Environment;
import models.statement.SimpleStmtBlock;
import models.stentry.SimpleVarSTEntry;
import util.SemanticError;

public class SimpleTypeFunction extends SimpleType {

	private List<SimpleVarSTEntry> param;
	private SimpleStmtBlock body; 
	
	public SimpleTypeFunction(List<SimpleVarSTEntry> param, SimpleStmtBlock body) {	
		this.param = param;
        this.setBody(body);
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		return null;
	}

	public List<SimpleVarSTEntry> getParam() {
		return param;
	}

	public void addParam(SimpleVarSTEntry paramEntry){
		param.add(paramEntry);
	}

	public SimpleStmtBlock getBody() {
		return body;
	}

	public void setBody(SimpleStmtBlock body) {
		this.body = body;
	}
	
	@Override
	public SimpleType typeCheck() {
		return null;
	}

	public String toPrint(String s) {
		String parlstr="";
		for (SimpleVarSTEntry par:param)
			parlstr+=par.toPrint(s+"  ");
		return s+"FunctionType\n" 
					+"\t"+ parlstr 
				; 
	}

	@Override
	public String codeGeneration() {
		return null;
	}

}
