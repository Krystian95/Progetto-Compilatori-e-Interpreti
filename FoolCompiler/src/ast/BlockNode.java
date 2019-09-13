package ast;
import java.util.ArrayList;
import java.util.HashMap;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class BlockNode implements Node {

	private ArrayList<Node> statements;

	public BlockNode (ArrayList<Node> c) {
		statements=c;
	}

	public String toPrint(String s) {
		String statementstr="";
		for (Node statement:statements)
			statementstr += statement.toPrint(s+"  ");
		return s+"Block\n" + statementstr; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//declare resulting list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		env.nestingLevel++;
		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		env.symTable.add(hm);
		
		int offsetGlobal = env.offset;

		//check semantics in the dec list
		if(statements.size() > 0){
			//env.offset = -2;
			env.offset = -1;
			//if there are statementren then check semantics for every statement and save the results
			for(Node statement : statements) {
				//System.out.println("statement = "+statement);
				res.addAll(statement.checkSemantics(env));
			}
		}
		
		env.offset = offsetGlobal;

		//check semantics in the exp body
		//res.addAll(exp.checkSemantics(env));

		//clean the scope, we are leaving a let scope
		env.symTable.remove(env.nestingLevel--);

		//return the result
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
		
		String declCode="";
		String pops = "";
		
		for (Node statement:statements) {
			declCode+=statement.codeGeneration();
			pops += "pop\n";
		}
		
		return 
				"lfp\n"+
				"cfp\n"+
				declCode
				//"halt\n"+
				//FOOLlib.getCode()
				;
	} 



}  