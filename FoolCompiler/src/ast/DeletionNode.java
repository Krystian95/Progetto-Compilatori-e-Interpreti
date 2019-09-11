package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class DeletionNode implements Node {

	private String id;

	public DeletionNode (String i) {
		id=i;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		Utils.printHashMap("BEFORE DELETION OF "+id,env.symTable);

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();


		int j = env.nestingLevel;
		STentry enry_to_delete = null;

		System.out.println("DELETION of "+id+" starting at nestingLevel = "+j);

		while (j >= 1 && enry_to_delete == null) {
			enry_to_delete = (env.symTable.get(j--)).get(id);
		}

		if (enry_to_delete == null) {
			res.add(new SemanticError("Id " + id + " not declared [DELETE]"));
		}else{
			System.out.println("DELETION of "+id+" found at nestingLevel = "+enry_to_delete.getNestinglevel());
			env.symTable.get(enry_to_delete.getNestinglevel()).remove(id, enry_to_delete);
		}

		Utils.printHashMap("AFTER DELETION OF "+id,env.symTable);

		return res;
	}

	public String toPrint(String s) {
		return s+"Deletion:" + id+"\n";
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {
		return null;
	}

	public String codeGeneration() {
		return "";
	}  

}  