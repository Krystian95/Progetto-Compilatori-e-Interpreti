package operation_int;

import java.util.ArrayList;

import lib.FOOLlib;
import models.Environment;
import models.Node;
import models.type.IntTypeNode;
import util.SemanticError;

public class DivNode implements Node {

	private Node left;
	private Node right;

	public DivNode (Node l, Node r) {
		left = l;
		right = r;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		res.addAll(left.checkSemantics(env));
		res.addAll(right.checkSemantics(env));

		return res;
	}

	public String toPrint(String s) {

		return s + "Div\n" + left.toPrint(s + "  ")  
		+ right.toPrint(s + "  ") ; 
	}

	public Node typeCheck() {

		if (!(FOOLlib.isEqualtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isEqualtype(right.typeCheck(),new IntTypeNode()) ) ) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Non integers in division");
			System.exit(0);
		}

		return new IntTypeNode();
	}  

	public String codeGeneration() {
		return left.codeGeneration() +
				right.codeGeneration() +
				"div\n";
	}
	
}
