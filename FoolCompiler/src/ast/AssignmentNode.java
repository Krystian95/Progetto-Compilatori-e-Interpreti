package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class AssignmentNode implements Node {

	private String id;
	private Node exp;
	private Node idType;
	STentry varEntry;
	private int nestinglevel;

	public AssignmentNode (String i, Node v) {
		id = i;
		exp = v;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		varEntry = null;  //entry della variabile nella ST
		nestinglevel = env.nestingLevel;
		
		// Cerco l'entry dell'id nella ST dal NL corrente fino a quello più esterno (1)
        int j = env.nestingLevel;
        while (j >= 0) {
            varEntry = (env.symTable.get(j--)).get(id);
            if (varEntry != null) break;
        }
        
        if(varEntry == null) {
            res.add(new SemanticError("Variable " + id + " not declared"));
        }else {
        	//idOffset = varEntry.getOffset(); //memorizzo l'offset dell'id
        	/*if(env.symTable.get(env.nestingLevel).containsKey(id)) {
        		res.add(new SemanticError("Variable " + id + " cannot be assigne to it self"));
        	}*/
        	idType = varEntry.getType(); //memorizzo il tipo dell'entry
        }

		res.addAll(exp.checkSemantics(env));
		
		//mi salvo la ST da NL 0 a NL corrente
        /*int k = 0;
        while(k<=env.nestingLevel)
            symT.add(env.symTable.get(k++));*/

		return res;
	}
	
	public String toPrint(String s) {
		return s+"Var:" + id +"\n"
				+exp.toPrint(s+"  "); 
	}

	//valore di ritorno non utilizzato
	public Node typeCheck() {
		
		if (! (FOOLlib.isEqualtype(exp.typeCheck(),idType)) ){
			System.err.println("You had 1 error:");
			System.err.println("\tIncompatible value for variable " + id);
			System.exit(0);
		}
		
		return null;
	}

	public String codeGeneration() {
		
		String getAR="";
		
		//System.out.println("[AssignmentNode] nestinglevel = "+nestinglevel);
		//System.out.println("[AssignmentNode] entry.getNestinglevel() = "+varEntry.getNestinglevel());
		
		for (int i=0; i<nestinglevel-varEntry.getNestinglevel(); i++) 
			getAR += "lw\n";
		
		return exp.codeGeneration()
                + "lfp\n"
                + getAR
                + "push " + varEntry.getOffset() + "\n"
                + "add\n"
                + "sw\n" ;
	}  

}  