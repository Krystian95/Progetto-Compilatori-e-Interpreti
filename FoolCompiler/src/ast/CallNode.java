package ast;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class CallNode implements Node {

	private String id;
	private STentry entry; 
	private ArrayList<Node> parlist = new ArrayList<Node>(); 
	private int nestinglevel;


	public CallNode (String i, STentry e, ArrayList<Node> p, int nl) {
		id = i;
		entry = e;
		parlist = p;
		nestinglevel = nl;
	}

	public CallNode(String text, ArrayList<Node> args) {

		id = text;
		parlist = args;
	}

	public String toPrint(String s) {

		String parlstr = "";

		for (Node par:parlist)
			parlstr += par.toPrint(s + "  ");

		return
				s + "Call:" + id + " at nestlev " + nestinglevel + "\n" +
				entry.toPrint(s + "  ") +
				parlstr;        
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlistCalled = new LinkedHashMap<Integer, LinkedHashMap<String, STentry>>();

		int j = env.getNestingLevel();
		STentry functionCalled = null; 

		while (j >= 0 && functionCalled == null) {
			functionCalled = (env.getSymbTable().get(j--)).get(id);
		}

		if (functionCalled == null || functionCalled.isDeleted()) {
			res.add(new SemanticError("- Function id \"" + id + "\" not declared"));
			return res;
		} else {
			this.entry = functionCalled;
			this.nestinglevel = env.getNestingLevel();

			int counter = 0;

			for(Node arg : parlist) {
				res.addAll(arg.checkSemantics(env));
				
				if(res.size() > 0) {
					return res;
				}

				LinkedHashMap<String, STentry> parlistCalledInner = new LinkedHashMap<String, STentry>();

				try {
					IdNode a = (IdNode) arg;
					parlistCalledInner.put(a.getId(), a.getEntry());
				} catch(Exception e) {
					parlistCalledInner.put("*", null);
				}

				parlistCalled.put(counter, parlistCalledInner);
				counter++;
			}
			
			if(parlistCalled.size() != functionCalled.getDecParlist().size()) {
				res.add(new SemanticError("- Wrong number of parameters in the invocation of \"" + id + "\""));
				return res;
			}

			/*
			 * CheckSemanticsParVar
			 */
			if(functionCalled.getDecParlist() != null && functionCalled.getDecParlist().size() > 0) {
				for(Entry<Integer, LinkedHashMap<String, STentry>> item : functionCalled.getDecParlist().entrySet()) {
					for(Map.Entry<String, STentry> itemInner : item.getValue().entrySet()) {

						STentry entryParDec = itemInner.getValue();

						LinkedHashMap<String, STentry> parCalled = parlistCalled.get(item.getKey());

						for(Map.Entry<String, STentry> itemToCheck : parCalled.entrySet()) {

							String idEntryToCheck = itemToCheck.getKey();

							if(entryParDec.getMode().equals("var") && idEntryToCheck.toString().equals("*")) {
								res.add(new SemanticError("- Parameter var \"" + itemInner.getKey() + "\" called with non id"));
								return res;
							}
						}

					}
				}
			}

			/*
			 * CheckSemanticsDeletions
			 */
			if(functionCalled.getDecParlist() != null && functionCalled.getDecParlist().size() > 0) {
				for(Entry<Integer, LinkedHashMap<String, STentry>> item : functionCalled.getDecParlist().entrySet()) {
					for(Map.Entry<String, STentry> itemInner : item.getValue().entrySet()) {

						String idParDec = itemInner.getKey();
						STentry entryParDec = itemInner.getValue();

						LinkedHashMap<String, STentry> parCalled = parlistCalled.get(item.getKey());

						for(Map.Entry<String, STentry> itemToDelete : parCalled.entrySet()) {

							String idEntry = itemToDelete.getKey();
							STentry entry = itemToDelete.getValue();

							/*
							 * Deletion
							 */
							if(entryParDec.isDeletedByFunCall()) {
								env.getSymbTable().get(entry.getNestinglevel()).remove(idEntry, entry);
								entry.setDeleted(true);
								env.getSymbTable().get(entry.getNestinglevel()).put(idEntry, entry);
							}

							/*
							 * Mapping
							 */
							if(entryParDec.getMode().equals("var")) {
								env.getSymbTable().get(entry.getNestinglevel()).remove(idParDec, entryParDec);
								entryParDec.setMappedEntry(entry);
								env.getSymbTable().get(entry.getNestinglevel()).put(idParDec, entryParDec);
							}
						}
					}

				}
			}
		}

		return res;
	}

	public Node typeCheck () {

		ArrowTypeNode t = null;

		if (entry.getType() instanceof ArrowTypeNode) { 
			t = (ArrowTypeNode) entry.getType();
		} else {
			System.err.println("You had 1 error:");
			System.err.println("\t- Invocation of a non-function \"" + id + "\"");
			System.exit(0);
		}

		ArrayList<Node> p = t.getParList();

		for (int i = 0; i < parlist.size(); i++) {
			if (!(FOOLlib.isEqualtype((parlist.get(i)).typeCheck(), p.get(i)))) {
				System.err.println("You had 1 error:");
				System.err.println("\t- Wrong type for " + (i + 1) + "-th parameter in the invocation of function \"" + id + "\"");
				System.exit(0);
			} 
		}

		return null;
	}

	public String codeGeneration() {

		String parCode="";

		for (int i = parlist.size() - 1; i >= 0; i--)
			parCode += parlist.get(i).codeGeneration();

		String getAR = "";

		for (int i = 0; i<nestinglevel - entry.getNestinglevel(); i++) 
			getAR += "lw\n";

		return
				"lfp\n" +
				parCode +
				"lfp\n" + 
				getAR +
				"push " + entry.getOffset() + "\n" +
				"lfp\n" + getAR + 
				"add\n" + 
				"lw\n" +
				"js\n";
	}


}  