package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class NotEqualNode implements Node {

	private Node left;
	private Node right;

	public NotEqualNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "NotEqual\n" 
				+ left.toPrint(s + "  ")   
				+ right.toPrint(s + "  "); 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//check semantics in the left and in the right exp
		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() {

		Node l = left.typeCheck();
		Node r = right.typeCheck();

		if (FOOLlib.isEqualtype(l, new IntTypeNode()) && FOOLlib.isEqualtype(r, new IntTypeNode())) {
			if (!(FOOLlib.isEqualtype(l, r) || FOOLlib.isEqualtype(r, l))) {
				System.err.println("You had 1 error:");
				System.err.println("\t- Incompatible types in not equal");
				System.exit(0);
			}
		} else {
			System.err.println("You had 1 error:");
			System.err.println("\t- Not integer types in not equal");
			System.exit(0);
		}

		return new BoolTypeNode();
	}  

	public String codeGeneration() {

		String l1 = FOOLlib.freshLabel(); 
		String l2 = FOOLlib.freshLabel();

		return left.codeGeneration() +
				right.codeGeneration() +
				"bneq " + l1 + "\n" +
				"push 0\n" +
				"b " + l2 + "\n" +
				l1 + ":\n" +
				"push 1\n" +
				l2 + ":\n";

	}

}  