package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class DeletionNode implements Node {

	private String id;

	public DeletionNode (String i) {
		id = i;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		int j = env.getNestingLevel();

		STentry enry_to_delete = null;

		while (j >= 1 && enry_to_delete == null) {
			enry_to_delete = (env.getSymbTable().get(j--)).get(id);
		}

		if (enry_to_delete == null || enry_to_delete.isDeleted()) {
			res.add(new SemanticError("- Id \"" + id + "\" not declared"));
			return res;
		} else {
			env.getSymbTable().get(enry_to_delete.getNestinglevel()).remove(id, enry_to_delete);

			if(env.getIsInsideFunction() && enry_to_delete.getMode() != null && enry_to_delete.getMode().equals("var")) {
				enry_to_delete.setDeletedByFunCall(true);
			}

			enry_to_delete.setDeleted(true);
			env.getSymbTable().get(enry_to_delete.getNestinglevel()).put(id, enry_to_delete);
		}
		
		if(env.getIsInsideThenBranch()) {
			DeletionNode.deletionsThenBranch.add(enry_to_delete);
		} else if (env.getIsInsideElseBranch()) {
			DeletionNode.deletionsElseBranch.add(enry_to_delete);
		}
		
		return res;
	}

	public String toPrint(String s) {
		return s + "Deletion: " + id +"\n";
	}

	public Node typeCheck () {
		return null;
	}

	public String codeGeneration() {
		return "";
	}  

}  