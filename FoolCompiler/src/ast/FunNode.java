package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class FunNode implements Node {

	private String id;
	private ArrayList<Node> parlist = new ArrayList<Node>();
	private Node body;

	public FunNode (String i) {
		id = i;
	}

	public void addDecBody (Node b) {
		body = b;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		HashMap<String, STentry> hm = env.symTable.get(env.nestingLevel);

		STentry entry = new STentry(env.nestingLevel, env.offset--);
		LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlistTmp = new LinkedHashMap<Integer, LinkedHashMap<String, STentry>>();

		int j = env.nestingLevel;
		STentry tmp = null; 

		tmp = (env.symTable.get(j--)).get(id);

		if (tmp != null && !tmp.isDeleted()) {
			res.add(new SemanticError("- Function id \"" + id + "\" already declared"));
			return res;
		} else{

			hm.put(id, entry);

			ArrayList<Node> parTypes = new ArrayList<Node>();

			env.parOffset=1;

			//check args
			for(Node a : parlist){
				ParNode arg = (ParNode) a;
				parTypes.add(arg.getType());
			}

			//crea una nuova hashmap per la symTable
			HashMap<String,STentry> hmn = new HashMap<String,STentry> ();
			env.symTable.add(hmn);

			int counter = 0;

			for(Node a : parlist){
				res.addAll(a.checkSemantics(env));
				ParNode arg = (ParNode) a;

				LinkedHashMap<String, STentry> parlistTmpInner = new LinkedHashMap<String, STentry>();
				parlistTmpInner.put(arg.getId(), arg.getEntry());
				parlistTmp.put(counter, parlistTmpInner);

				counter++;
			}

			entry.setDecParList(parlistTmp);

			entry.addType(new ArrowTypeNode(parTypes));

			env.isInsideFunction = true;
			res.addAll(body.checkSemantics(env));
			env.isInsideFunction = false;
		}

		return res;
	}

	public void addPar (Node p) {
		parlist.add(p);
	}  

	public String toPrint(String s) {

		String parlstr = "";

		for (Node par:parlist)
			parlstr += par.toPrint(s + "  ");

		return s + "Fun:" + id +"\n"
		+ parlstr
		+ body.toPrint(s + "  ") ; 
	}

	public Node typeCheck () {

		if(body != null) {
			body.typeCheck();
		}

		return null;
	}

	@SuppressWarnings("unused")
	public String codeGeneration() {

		String popParl="";

		for (Node dec:parlist)
			popParl += "pop\n";

		String funl = FOOLlib.freshFunLabel(); 

		FOOLlib.putCode(
				funl + ":\n"
						+ "cfp\n"
						+ "lra\n"
						+ body.codeGeneration()
						+ "sra\n"
						+ "pop\n" // [!]
						+ popParl
						+ "sfp\n"
						+ "lra\n"
						+ "js\n"
				);

		return "push " + funl + "\n";
	}

}  