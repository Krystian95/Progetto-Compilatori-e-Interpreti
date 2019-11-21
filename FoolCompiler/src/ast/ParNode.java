package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;

public class ParNode implements Node {

	private String id;
	private Node type;
	private String mode;
	private STentry entry;

	public ParNode (String v, String i, Node t) {
		mode = v;
		id = i;
		type = t;
	}

	public String getId(){
		return id;
	}

	public Node getType(){
		return type;
	}

	public STentry getEntry() {
		return this.entry;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		//mi salvo la ST dell'ultimo NL
		HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);

		//se il nome è già stato usato nel NL corrente diamo errore
		if (hm.containsKey(id)) {
			res.add(new SemanticError("- Parameter id \"" + id + "\" already declared"));
			return res;
			//se il nome è free, inserisco l'entry nella ST e incremento l'offset
		}else {
			this.entry = new STentry(env.nestingLevel, type, env.parOffset);
			entry.setMode(this.mode);
			hm.put(id, this.entry);
			env.parOffset++;
		}

		return res;
	}

	public String toPrint(String s) {
		return 
				s + "Par: " + id +"\n" +
				s + "Mode: " + mode.toString() + "\n" +
				s + "Type: " + type.toPrint("") +
				s + "Entry: " + entry.toPrint("\t");
	}

	public Node typeCheck () {
		return null;
	}

	public String codeGeneration() {
		return "";
	}

}  