package models.type;
import java.util.ArrayList;

import models.Environment;
import models.Node;
import util.SemanticError;

public class FunctionTypeNode implements Node {

	private ArrayList<Node> parlist; 

	public FunctionTypeNode (ArrayList<Node> p) {
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