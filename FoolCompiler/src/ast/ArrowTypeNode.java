package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ArrowTypeNode implements Node {

	private ArrayList<Node> parlist; 

	public ArrowTypeNode (ArrayList<Node> p) {
		parlist = p;
	}

	public String toPrint(String s) {

		String parlstr="";

		for (Node par:parlist)
			parlstr += par.toPrint(s + "  ");

		return s + "ArrowType\n" + parlstr; 
	}

	public ArrayList<Node> getParList () {
		return parlist;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return new ArrayList<SemanticError>();
	}

	public Node typeCheck () {
		return null;
	}

	public String codeGeneration() {
		return "";
	}

}  