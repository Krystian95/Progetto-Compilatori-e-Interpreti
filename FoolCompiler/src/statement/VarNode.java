package statement;

import java.util.ArrayList;
import java.util.HashMap;

import util.SemanticError;
import lib.FOOLlib;
import models.Environment;
import models.Node;
import models.STentry;

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

		env.setIsInsideDeclaration(true);
		env.setIdDeclaration(id);

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		HashMap<String,STentry> hm = env.getSymbTable().get(env.getNestingLevel());

		STentry entry = new STentry(env.getNestingLevel(), type, env.getOffset());

		int j = env.getNestingLevel();

		STentry enry_to_declare = null;

		enry_to_declare = (env.getSymbTable().get(j--)).get(id);

		if (enry_to_declare != null && !enry_to_declare.isDeleted()) {
			res.add(new SemanticError("Var id \"" + id + "\" already declared"));
			return res;
		} else {
			env.decreaseOffset();
			hm.put(id, entry);
		}

		res.addAll(exp.checkSemantics(env));

		env.setIsInsideDeclaration(false);

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
			System.err.println("\t- Incompatible value for variable \"" + id + "\"");
			System.exit(0);
		}

		return null;
	}

	public String codeGeneration() {
		return exp.codeGeneration();
	}  

}  