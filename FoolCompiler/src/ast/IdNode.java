package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IdNode implements Node {

	private String id;
	private STentry entry;
	private int nestinglevel;

	public IdNode (String i) {
		id=i;
	}

	public String toPrint(String s) {
		return s+"Id:" + id + " at nestlev " + nestinglevel
				+"\n" + entry.toPrint(s+"  ") 
				;  
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//System.exit(0);

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		int j=env.nestingLevel;
		STentry tmp=null;

		while (j>=0 && tmp==null)
			tmp = (env.symTable.get(j--)).get(id);

		if (tmp == null) {
			res.add(new SemanticError("Id " + id + " not declared"));
		}else{
			entry = tmp;
			nestinglevel = env.nestingLevel;
		}

		return res;
	}

	public Node typeCheck () {

		if (entry.getType() instanceof ArrowTypeNode) { //
			System.out.println("Wrong usage of function identifier");
			System.exit(0);
		}	 
		return entry.getType();
	}

	public String codeGeneration() {

		String discriminator = "";

		if(entry.getOffset() > 0) {
			//discriminator = "cfp";
		}else {
			if(nestinglevel <= entry.getNestinglevel()) {
				discriminator = "lfp";
			}else {
				discriminator = "lfp";
			}
		}

		String getAR="";
		System.out.println("[IdNode] nestinglevel = "+nestinglevel);
		System.out.println("[IdNode] entry.getNestinglevel() = "+entry.getNestinglevel());
		for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++) {
			System.out.println("i = " + i);
			getAR+= "lw\n";

		}
		
		return "push "+entry.getOffset()+"\n"+ //metto offset sullo stack
		//discriminator+"\n"+
		"lfp\n"+
		getAR+ //risalgo la catena statica
		"add\n"+ 
		"lw\n"
		; //carico sullo stack il valore all'indirizzo ottenuto

	}
}  