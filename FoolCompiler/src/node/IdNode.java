package node;

import java.util.ArrayList;

import models.Environment;
import models.Node;
import models.STentry;
import models.type.FunctionTypeNode;
import util.SemanticError;

public class IdNode implements Node {

	private String id;
	private STentry entry;
	private int nestinglevel;

	public IdNode (String i) {
		id = i;
	}

	public String getId () {
		return this.id;
	}

	public STentry getEntry() {
		return this.entry;
	}

	public String toPrint(String s) {
		return s + "Id: " + id + " at nestlev " + nestinglevel
				+"\n" + entry.toPrint(s + "  ") ;  
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		int j = env.getNestingLevel();
		STentry tmp = null;

		while (j >= 0 && tmp == null) {
			tmp = (env.getSymbTable().get(j--)).get(id);
		}

		if (tmp == null || tmp.isDeleted()) {
			res.add(new SemanticError("- Id \"" + id + "\" not declared"));
		} else {
			if(env.getIsInsideDeclaration() && id.equals(env.getIdDeclaration())) {
				res.add(new SemanticError("- Variable \"" + id + "\" is not initializated"));
			}

			entry = tmp;
			nestinglevel = env.getNestingLevel();
		}

		return res;
	}

	public Node typeCheck () {

		if (entry.getType() instanceof FunctionTypeNode) {
			System.err.println("You had 1 error:");
			System.err.println("\t- Wrong usage of function identifier");
			System.exit(0);
		}

		return entry.getType();
	}

	public String codeGeneration() {

		String getAR = "";

		for (int i = 0; i<nestinglevel - entry.getNestinglevel(); i++) 
			getAR += "lw\n";

		return
				"push " + entry.getOffset() + "\n" +
				"lfp\n" +
				getAR +
				"add\n"+
				"lw\n";

	}
}  