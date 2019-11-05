package ast;
import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;

public class BlockNode implements Node {

	private ArrayList<Node> statements;
	private int nestLevel;
	private int countVarDec;

	public BlockNode (ArrayList<Node> c) {
		statements = c;
	}

	public String toPrint(String s) {

		String statementstr = "";

		for (Node statement:statements)
			statementstr += statement.toPrint(s + "  ");

		return s + "Block\n" + statementstr; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		env.nestingLevel++;
		nestLevel = env.nestingLevel;

		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		env.symTable.add(hm);

		int offsetGlobal = env.offset;

		if(statements.size() > 0){
			env.offset = -1;
			for(Node statement : statements) {
				res.addAll(statement.checkSemantics(env));
			}
		}

		env.offset = offsetGlobal ;

		countVarDec=Utils.countVarDec("", env.symTable, nestLevel);

		env.symTable.remove(env.nestingLevel--);

		return res;
	}

	public Node typeCheck () {

		if (statements != null) {
			for (Node statement:statements) {
				statement.typeCheck();
			}
		}

		return null;
	}

	public String codeGeneration() {

		String declCode = "";
		String pops = "";

		for (int i = 0; i < countVarDec; i++) {
			pops += "pop\n";
		}

		for (Node statement:statements) {
			declCode += statement.codeGeneration();
		}

		return 
				"lfp\n" +
				"cfp\n" +
				declCode +
				pops +
				"sfp\n";
	} 



}  