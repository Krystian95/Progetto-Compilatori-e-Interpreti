package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class OrBooleanOperationNode implements Node {

	private Node left;
	private Node right;

	public OrBooleanOperationNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "OrBooleanOperation\n"
				+ left.toPrint(s + "  ")   
				+ right.toPrint(s + "  ") ; 
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

		if (FOOLlib.isEqualtype(l, new BoolTypeNode()) && FOOLlib.isEqualtype(r, new BoolTypeNode())) {
			if (!(FOOLlib.isEqualtype(l, r) || FOOLlib.isEqualtype(r, l))) {
				System.err.println("You had 1 error:");
				System.err.println("\t- Incompatible types in OR boolean operation");
				System.exit(0);
			}
		} else {
			System.err.println("You had 1 error:");
			System.err.println("\t- Not integer types in OR boolean operation");
			System.exit(0);
		}

		return new BoolTypeNode();
	} 

	public String codeGeneration() {

		String l_true = FOOLlib.freshLabel();
		String lExit = FOOLlib.freshLabel();

		return 
				"push 1\n" + 
				left.codeGeneration() +
				"beq " + l_true + "\n" +
				right.codeGeneration() +
				"b " + lExit + "\n" +
				l_true + ":\n" +
				"push 1\n" +
				lExit + ":\n";

	}

}  