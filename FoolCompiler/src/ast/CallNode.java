package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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

		LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlistCalled = new LinkedHashMap<Integer, LinkedHashMap<String, STentry>>();

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

			int counter=0;

			for(Node arg : parlist) {
				res.addAll(arg.checkSemantics(env));

				LinkedHashMap<String, STentry> parlistCalledInner = new LinkedHashMap<String, STentry>();
				try {
					IdNode a = (IdNode) arg;
					//System.err.println(a.toPrint(""));
					parlistCalledInner.put(a.getId(), a.getEntry());
				}
				catch(Exception e) {
					parlistCalledInner.put("*", null);
				}
				parlistCalled.put(counter, parlistCalledInner);
				counter++;
			}

			/*env.functionDecParList = functionCalled.getDecParlist();
			env.functionCallParList = parlistCalled;*/

			System.err.println("[CallNode] dec parList: " + functionCalled.getDecParlist().toString());
			System.err.println("[CallNode] call parList: " + parlistCalled.toString());

			/*
			 * CheckSemanticsParVar
			 */
			if(functionCalled.getDecParlist().size() > 0) {
				for(Entry<Integer, LinkedHashMap<String, STentry>> item : functionCalled.getDecParlist().entrySet()) {
					for(Map.Entry<String, STentry> itemInner : item.getValue().entrySet()) {
						
						STentry entryParDec = itemInner.getValue();

						LinkedHashMap<String, STentry> parCalled = parlistCalled.get(item.getKey());

						for(Map.Entry<String, STentry> itemToCheck : parCalled.entrySet()) {

							String idEntryToCheck = itemToCheck.getKey();

							if(entryParDec.getMode().equals("var") && idEntryToCheck.toString().equals("*")) {
								System.err.println("You had 1 error:");
								System.err.println("\tParameter var " + itemInner.getKey() + " called with non ID");
								System.exit(0);
							}
						}

					}
				}
			}

			/*
			 * CheckSemanticsDeletions
			 */
			if(functionCalled.getDecParlist().size() > 0) {
				for(Entry<Integer, LinkedHashMap<String, STentry>> item : functionCalled.getDecParlist().entrySet()) {
					for(Map.Entry<String, STentry> itemInner : item.getValue().entrySet()) {

						String idParDec = itemInner.getKey();
						STentry entryParDec = itemInner.getValue();

						LinkedHashMap<String, STentry> parCalled = parlistCalled.get(item.getKey());

						for(Map.Entry<String, STentry> itemToDelete : parCalled.entrySet()) {

							String idEntry = itemToDelete.getKey();
							STentry entry = itemToDelete.getValue();

							System.err.println("\n\npar pos: " + item.getKey());

							System.err.println("\nidParDec (dec): " + idParDec);
							System.err.println("entryParDec (dec): " + entryParDec);

							System.err.println("\nidEntry (call): " + idEntry);
							System.err.println("entry (call): " + entry);

							/*
							 * Deletion
							 */
							if(entryParDec.isDeletedByFunCall()) {
								if(entry.isDeleted()) {
									res.add(new SemanticError("Id " + itemToDelete.getKey() + " not declared"));
								}

								env.symTable.get(entry.getNestinglevel()).remove(idEntry, entry);
								entry.setDeleted(true);
								env.symTable.get(entry.getNestinglevel()).put(idEntry, entry);
							}

							/*
							 * Mapping
							 */
							if(entryParDec.getMode().equals("var")) {
								env.symTable.get(entry.getNestinglevel()).remove(idParDec, entryParDec);
								entryParDec.setMappedEntry(entry);
								env.symTable.get(entry.getNestinglevel()).put(idParDec, entryParDec);
							}
						}
					}

				}
			}
		}

		return res;
	}

	private void checkSemanticsParVarType() {

	}

	private void checkSemanticsDeletions() {

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

		System.err.println("[CallNode - CodeGen]");

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