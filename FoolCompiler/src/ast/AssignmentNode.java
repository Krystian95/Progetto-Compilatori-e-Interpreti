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

	public AssignmentNode (String i, Node v) {
		System.out.println("i = "+i);
		System.out.println("v = "+v.toPrint(i));
		id = i;
		exp = v;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		STentry varEntry = null;  //entry della variabile nella ST
		
		//cerco l'entry dell'id nella ST dal NL più esterno a NL = 0
        int j = env.nestingLevel;
        while (j >= 0) {
            varEntry = (env.symTable.get(j--)).get(id);
            if (varEntry != null) break;
        }
        
        if(varEntry == null) {
            res.add(new SemanticError("Variable " + id + " not declared"));
        }else {
        	//idOffset = varEntry.getOffset(); //memorizzo l'offset dell'id
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
		
		if (! (FOOLlib.isSubtype(exp.typeCheck(),idType)) ){
			System.out.println("Incompatible value for variable "+id);
			System.exit(0);
		}
		
		return null;
	}

	public String codeGeneration() {
		return exp.codeGeneration();
	}  

}  