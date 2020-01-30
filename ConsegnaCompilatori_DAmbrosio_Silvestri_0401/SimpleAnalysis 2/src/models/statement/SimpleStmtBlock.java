package models.statement;

import java.util.ArrayList;
import java.util.List;
import models.Environment;
import models.type.SimpleType;
import util.CodeGeneration;
import util.SemanticError;

public class SimpleStmtBlock extends SimpleStmt {

	private List<SimpleStmt> children;
	private Boolean nonInitBlock=false;
	private int countVarDeclaration;
	private int nestingLevel;

	/**
	 * Creates a new block
	 * @param children: the list of direct children (statements) elements of the block
	 */

	public SimpleStmtBlock(List<SimpleStmt> children) {
		this.children = children;
	}

	/**
	 * It checks the semantic for every child in order item by item
	 * It creates a new scope for the elements that will be found inside
	 * Each element may add new elements to the environment inside the current scope
	 * After finishing drop the newly created scope
	 */

	public ArrayList<SemanticError> checkSemantics(Environment e) {
		//create scope for inner elements
		e.openScope();	
		nestingLevel = e.nestingLevel;
		int offsetGlobal = e.offset;
		//initialize result variable		
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		//check children semantics
		if(children!=null) {
			e.offset = -1;
			for(SimpleStmt el:children) {
				result.addAll(el.checkSemantics(e));
			}
		}
		//count var declaration in scope
		for(int i = 0; i < e.resultCountVar.size(); i++) {
			if(e.resultCountVar.get(i).getNestingLevel() == nestingLevel) {
				countVarDeclaration = e.resultCountVar.get(i).getCountVariableInScope();
			}
		}

		//set offset
		e.offset = offsetGlobal;
		//close scope for this block		
		e.closeScope();

		if (e.nestingLevel>=0 ) {			
			nonInitBlock=true;
		}
		return result;
	}

	@Override
	public SimpleType typeCheck() {
		if (children != null) {
			for (SimpleStmt statement:children) {
				statement.typeCheck();
			}
		}
		return null;
	}

	@Override
	public String toPrint(String block) {
		String statementstr="";
		for (SimpleStmt statement:children)
			statementstr += statement.toPrint(block+"  ");
		return 
				block+"Block\n" + statementstr; 
	}

	public String returnCodeGeneration (String statement) {
		String pops = "";
		for (int i=0; i < countVarDeclaration; i++) {	
			pops += "pop\n";
		}
		for (SimpleStmt stm:children) {
			statement+=stm.codeGeneration();
		}
		return
				"lfp\n"+
				"cfp\n"+
				statement+
				pops+
				"sfp\n";
	}

	@Override
	public String codeGeneration() {
		String statement="";

		if (nonInitBlock) {
			//not is in the initial block
			return returnCodeGeneration(statement);
		} else {
			return "push 0\n"+
					"cfp\n"+
					"pop\n"+
					returnCodeGeneration(statement)+
					"halt\n"+
					CodeGeneration.getCode();
		}
	}
}