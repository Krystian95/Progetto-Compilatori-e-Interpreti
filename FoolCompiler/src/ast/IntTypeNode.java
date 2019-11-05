package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IntTypeNode implements Node {

	public IntTypeNode () {}

	public String toPrint(String s) {
		return s + "IntType\n";  
	}

	public Node typeCheck() {
		return null;
	}

	public String codeGeneration() {
		return "";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return new ArrayList<SemanticError>();
	}

}