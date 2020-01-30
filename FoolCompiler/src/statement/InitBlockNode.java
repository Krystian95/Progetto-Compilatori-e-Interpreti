package statement;
import java.util.ArrayList;
import java.util.HashMap;

import lib.FOOLlib;
import models.Environment;
import models.Node;
import models.STentry;
import util.SemanticError;

public class InitBlockNode implements Node {

	private Node statement;

	public InitBlockNode (Node c) {
		statement = c;
	}

	public String toPrint(String s) {

		String statementstr = "";
		statementstr += statement.toPrint(s + "  ");

		return s + "InitBlock\n" + statementstr; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		env.increaseNestingLevel();
		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		env.addHasMapToSymbTable(hm);

		env.setOffset(-2);

		res.addAll(statement.checkSemantics(env));

		//clean the scope, we are leaving a let scope
		env.removeHasMapFromSymbTable(env.decreaseNestingLevel());

		return res;
	}

	public Node typeCheck () {

		if (statement != null) {
			statement.typeCheck();
		}

		return null;
	}

	public String codeGeneration() {

		String declCode = "";
		declCode += statement.codeGeneration();

		return 
				"push 0\n" +
				"cfp\n" +
				"pop\n" +
				declCode +
				"halt\n" +
				FOOLlib.getCode();
	}

}  