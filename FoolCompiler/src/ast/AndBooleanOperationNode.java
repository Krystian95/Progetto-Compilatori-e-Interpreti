package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class AndBooleanOperationNode implements Node {

	private Node left;
	private Node right;

	public AndBooleanOperationNode (Node l, Node r) {
		left=l;
		right=r;
	}

	public String toPrint(String s) {
		return s+"AndBooleanOperation\n" + left.toPrint(s+"  ")   
		+ right.toPrint(s+"  ") ; 
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
		if (! ( FOOLlib.isSubtype(l,r) || FOOLlib.isSubtype(r,l) ) ) {
			System.out.println("Incompatible types in AND boolean operation");
			System.exit(0);
		}
		return new BoolTypeNode();
	}  

	public String codeGeneration() {

		String lExit = FOOLlib.freshLabel();

		return 
				"push 0\n"+ 
				left.codeGeneration()+
				"beq "+ lExit +"\n"+
				//"push 1\n"+
				right.codeGeneration()+
				lExit + ":\n";

	}

}  