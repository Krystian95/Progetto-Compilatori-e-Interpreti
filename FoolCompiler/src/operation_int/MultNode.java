package operation_int;

import java.util.ArrayList;

import util.SemanticError;
import lib.FOOLlib;
import models.Environment;
import models.Node;
import models.type.IntTypeNode;

public class MultNode implements Node {

	private Node left;
	private Node right;

	public MultNode (Node l, Node r) {
		left = l;
		right = r;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//check semantics in the left and in the right exp
		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public String toPrint(String s) {
		return s + "Mult\n" 
				+ left.toPrint(s + "  ")  
				+ right.toPrint(s + "  "); 
	}

	public Node typeCheck() {

		if (!(FOOLlib.isEqualtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isEqualtype(right.typeCheck(),new IntTypeNode()))) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Non integers in multiplication");
			System.exit(0);
		}

		return new IntTypeNode();
	}  

	public String codeGeneration() {
		return left.codeGeneration() +
				right.codeGeneration() +
				"mult\n";
	}

}