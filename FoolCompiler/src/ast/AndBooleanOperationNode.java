package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class AndBooleanOperationNode implements Node {

	private Node left;
	private Node right;

	public AndBooleanOperationNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "AndBooleanOperation\n" 
				+ left.toPrint(s + "  ")   
				+ right.toPrint(s + "  ") ; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create the result
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
				System.err.println("Incompatible types in AND boolean operation");
				System.exit(0);
			}
		} else {
			System.err.println("Not integer types in AND boolean operation");
			System.exit(0);
		}

		return new BoolTypeNode();
	} 

	public String codeGeneration() {

		String lExit = FOOLlib.freshLabel();
		String lFalse = FOOLlib.freshLabel();

		return 
				"push 0\n" + 
				left.codeGeneration() + 
				"beq " + lFalse + "\n" +
				right.codeGeneration() +
				"b " + lExit + "\n" + 
				lFalse + ":\n" +
				"push 0\n" +
				lExit + ":";
	}

}  