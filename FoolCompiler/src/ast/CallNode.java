package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class CallNode implements Node {

	private String id;
	private STentry entry; 
	private ArrayList<Node> parlist = new ArrayList<Node>(); 
	private int nestinglevel;


	public CallNode (String i, STentry e, ArrayList<Node> p, int nl) {
		id=i;
		entry=e;
		parlist = p;
		nestinglevel=nl;
	}

	public CallNode(String text, ArrayList<Node> args) {
		id=text;
		parlist = args;
	}

	public String toPrint(String s) {  //
		String parlstr="";
		for (Node par:parlist)
			parlstr+=par.toPrint(s+"  ");		
		return s+"Call:" + id + " at nestlev " + nestinglevel +"\n" 
		+entry.toPrint(s+"  ")
		+parlstr;        
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		LinkedHashMap<String, STentry> parlistCalled = new LinkedHashMap<String, STentry>();

		int j = env.nestingLevel;
		STentry functionCalled = null; 

		while (j>=0 && functionCalled==null) {
			functionCalled = (env.symTable.get(j--)).get(id);
		}

		if (functionCalled == null || functionCalled.isDeleted()) {
			res.add(new SemanticError("Id " + id + " not declared"));
		} else {
			this.entry = functionCalled;
			this.nestinglevel = env.nestingLevel;

			for(Node arg : parlist) {
				res.addAll(arg.checkSemantics(env));
				IdNode a = (IdNode) arg;
				System.err.println(a.toPrint(""));
				parlistCalled.put(a.getId(), a.getEntry());
			}

			System.err.println("[CallNode] entry parList: " + functionCalled.getParlist().toString());
			System.err.println("[CallNode] called parList: " + parlistCalled.toString());

			if(functionCalled.getParlist().size() > 0) {
				int counterParPosition = 0;

				for(Map.Entry<String, STentry> item : functionCalled.getParlist().entrySet()) {

					//System.err.println("DELETED BY FunCall " + item.getKey());

					STentry entryParDec = item.getValue();

					if(entryParDec.isDeleted()) {
						
						System.err.println("DELETING index: " + counterParPosition);

						Map.Entry<String, STentry> parCalled =	Utils.getHashMapItemByIndex(parlistCalled, counterParPosition);

						String idEntryToDelete = parCalled.getKey();
						STentry entryToDelete = parCalled.getValue();

						env.symTable.get(entryToDelete.getNestinglevel()).remove(idEntryToDelete, entryToDelete);
						entryToDelete.setDeleted(true);
						env.symTable.get(entryToDelete.getNestinglevel()).put(idEntryToDelete, entryToDelete);
					}

					counterParPosition++;
				}
			}
		}

		//Utils.printHashMap("Dopo la FunCall", env.symTable);

		return res;
	}

	public Node typeCheck () {

		ArrowTypeNode t = null;

		if (entry.getType() instanceof ArrowTypeNode) { 
			t = (ArrowTypeNode) entry.getType();
		} else {
			System.err.println("Invocation of a non-function " + id);
			System.exit(0);
		}

		ArrayList<Node> p = t.getParList();

		if (!(p.size() == parlist.size())) {
			System.err.println("Wrong number of parameters in the invocation of " + id);
			System.exit(0);
		}

		for (int i=0; i<parlist.size(); i++) {
			if ( !(FOOLlib.isEqualtype((parlist.get(i)).typeCheck(), p.get(i)))) {
				System.err.println("Wrong type for " + (i+1) + "-th parameter in the invocation of " + id);
				System.exit(0);
			} 
		}

		return null;
	}

	public String codeGeneration() {
		String parCode="";
		for (int i=parlist.size()-1; i>=0; i--)
			parCode+=parlist.get(i).codeGeneration();

		String getAR="";
		for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++) 
			getAR+="lw\n";
		// formato AR: control_link+parameters+access_link+dich_locali
		return //"[-- START CALL NODE --]\n"+
				"lfp\n"+ 				// CL
				parCode+
				"lfp\n"+getAR+ 		// setto AL risalendo la catena statica
				// ora recupero l'indirizzo a cui saltare e lo metto sullo stack
				"push "+entry.getOffset()+"\n"+ // metto offset sullo stack
				"lfp\n"+getAR+ 		// risalgo la catena statica
				"add\n"+ 
				"lw\n"+ 				// carico sullo stack il valore all'indirizzo ottenuto
				"js\n"
				//+"[-- END CALL NODE --]\n"
				;
	}


}  