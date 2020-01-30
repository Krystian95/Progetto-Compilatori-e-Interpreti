package statement;
import java.util.ArrayList;
import java.util.HashMap;

import models.Environment;
import models.Node;
import models.STentry;
import util.SemanticError;
import util.Utils;

public class BlockNode implements Node {

	private ArrayList<Node> statements;
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

		env.increaseNestingLevel();
				
		int nestLevel = env.getNestingLevel();

		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		env.addHasMapToSymbTable(hm);

		int offsetGlobal = env.getOffset();

		if(statements.size() > 0){
			env.setOffset(-1);
			for(Node statement : statements) {
				res.addAll(statement.checkSemantics(env));
			}
		}

		env.setOffset(offsetGlobal);

		countVarDec = Utils.countVarDec("", env.getSymbTable(), nestLevel);

		env.removeHasMapFromSymbTable(env.decreaseNestingLevel());

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