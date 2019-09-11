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
		id=i;
		type=t;
		exp=v;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//Utils.printHashMap(env.symTable.get(env.nestingLevel));

		//env.offset = -2;
		HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);

		STentry entry = new STentry(env.nestingLevel,type, env.offset); //separo introducendo "entry"

		if ( hm.containsKey(id) )
			res.add(new SemanticError("Var id "+id+" already declared"));
		else {
			env.offset--;
			hm.put(id, entry);
		}

		res.addAll(exp.checkSemantics(env));

		Utils.printHashMap("DOPO LA DICHIARAZIONE",env.symTable);

		return res;
	}

	public String toPrint(String s) {
		return s+"Var:" + id +"\n"
				+type.toPrint(s+"  ")  
				+exp.toPrint(s+"  "); 
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {
		if (! (FOOLlib.isSubtype(exp.typeCheck(),type)) ){      
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