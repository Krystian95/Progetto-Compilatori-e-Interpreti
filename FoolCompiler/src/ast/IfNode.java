package ast;

import java.util.ArrayList;
import java.util.HashSet;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class IfNode implements Node {

	private Node cond;
	private Node th;
	private Node el;

	public IfNode (Node c, Node t, Node e) {
		cond = c;
		th = t;
		el = e;
	}

	public String toPrint(String s) {
		return
				s + "If\n" + cond.toPrint(s + "  ") 
				+ th.toPrint(s + "  ")   
				+ el.toPrint(s + "  "); 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		res.addAll(cond.checkSemantics(env));

		//check semantics in the then and in the else exp
		env.setIsInsideThenBranch(true);
		res.addAll(th.checkSemantics(env));
		env.setIsInsideThenBranch(false);

		env.setIsInsideElseBranch(true);
		res.addAll(el.checkSemantics(env));
		env.setIsInsideElseBranch(false);

		return res;
	}

	public Node typeCheck() {

		if (!FOOLlib.isEqualtype(cond.typeCheck(), new BoolTypeNode())) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Non boolean condition in if");
			System.exit(0);
		} else if(!Node.deletionsThenBranch.containsAll(Node.deletionsElseBranch) || !Node.deletionsElseBranch.containsAll(Node.deletionsThenBranch)) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Mismatching behavioural between if-then-else branches");
			System.exit(0);
		}

		return null;
	}

	public String codeGeneration() {

		String l1 = FOOLlib.freshLabel(); 
		String l2 = FOOLlib.freshLabel();

		return cond.codeGeneration()+
				"push 1\n" +
				"beq " + l1 +"\n" +			  
				el.codeGeneration() +
				"b " + l2 + "\n" +
				l1 + ":\n" +
				th.codeGeneration() +
				l2 + ":\n"; 
	}

}  