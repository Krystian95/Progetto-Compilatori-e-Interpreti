package comparison;

import java.util.ArrayList;

import util.SemanticError;
import lib.FOOLlib;
import models.Environment;
import models.Node;
import models.type.BoolTypeNode;

public class EqualNode implements Node {

	private Node left;
	private Node right;

	public EqualNode (Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "Equal\n" + left.toPrint(s + "  ")   
		+ right.toPrint(s + "  ") ; 
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public Node typeCheck() {

		Node l = left.typeCheck();
		Node r = right.typeCheck();

		if (!(FOOLlib.isEqualtype(l, r) || FOOLlib.isEqualtype(r, l))) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Incompatible types in equal");
			System.exit(0);
		}

		return new BoolTypeNode();
	}  

	public String codeGeneration() {

		String l1 = FOOLlib.freshLabel(); 
		String l2 = FOOLlib.freshLabel();

		return left.codeGeneration() +
				right.codeGeneration() +
				"beq " + l1 + "\n" +
				"push 0\n" +
				"b " + l2 + "\n" +
				l1 + ":\n" +
				"push 1\n" +
				l2 + ":\n";

	}

}  