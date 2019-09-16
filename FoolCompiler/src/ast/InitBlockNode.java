package ast;
import java.util.ArrayList;
import java.util.HashMap;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class InitBlockNode implements Node {

	private Node statement;

	public InitBlockNode (Node c) {
		statement=c;
	}

	public String toPrint(String s) {
		String statementstr="";
		statementstr += statement.toPrint(s+"  ");
		return s+"InitBlock\n" + statementstr; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//declare resulting list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		env.nestingLevel++;
		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		env.symTable.add(hm);

		env.offset = -2;
		//if there are statementren then check semantics for every statement and save the results
		//System.out.println("statement = "+statement);
		res.addAll(statement.checkSemantics(env));

		//check semantics in the exp body
		//res.addAll(exp.checkSemantics(env));

		//clean the scope, we are leaving a let scope
		env.symTable.remove(env.nestingLevel--);

		//return the result
		return res;
	}

	public Node typeCheck () {

		if (statement != null) {
			statement.typeCheck();
		}

		return null;
	}

	public String codeGeneration() {
		String declCode="";
		declCode += statement.codeGeneration();
		return 
				// aggiunto
				/*"push 0\n"+
				"cfp\n"+
				"pop\n"+*/
				// std
				declCode+
				"halt\n"+
				FOOLlib.getCode();
	} 



}  