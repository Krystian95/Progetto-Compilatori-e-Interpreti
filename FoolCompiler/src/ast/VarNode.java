package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class VarNode implements Node {

	private String id;
	private Node type;
	private Node exp;

	public VarNode (String i, Node t, Node v) {
		id = i;
		type = t;
		exp = v;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		env.isInsideDeclaration = true;
		env.idDeclaration = id;

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);

		STentry entry = new STentry(env.nestingLevel, type, env.offset);

		int j = env.nestingLevel;

		STentry enry_to_declare = null;

		enry_to_declare = (env.symTable.get(j--)).get(id);

		if (enry_to_declare != null && !enry_to_declare.isDeleted())
			res.add(new SemanticError("Var id " + id + " already declared"));
		else {
			env.offset--;
			hm.put(id, entry);
		}

		res.addAll(exp.checkSemantics(env));

		env.isInsideDeclaration = false;

		return res;
	}

	public String toPrint(String s) {
		return s + "Var:" + id + "\n"
				+ type.toPrint(s + "  ")  
				+ exp.toPrint(s + "  "); 
	}

	public Node typeCheck () {
		if (!(FOOLlib.isEqualtype(exp.typeCheck(), type))){      
			System.err.println("You had 1 error:");
			System.err.println("\tIncompatible value for variable " + id);
			System.exit(0);
		}

		return null;
	}

	public String codeGeneration() {
		return exp.codeGeneration();
	}  

}  