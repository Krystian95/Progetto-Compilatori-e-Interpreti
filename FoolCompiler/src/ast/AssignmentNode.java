package ast;

import java.util.ArrayList;
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

		varEntry = null; //entry della variabile nella ST
		nestinglevel = env.nestingLevel;
		
		// Cerco l'entry dell'id nella ST dal NL corrente fino a quello più esterno (1)
		int j = env.nestingLevel;
		while(j >= 0 && varEntry == null) {
			varEntry = (env.symTable.get(j--)).get(id);
		}

		if(varEntry == null || varEntry.isDeleted()) {
			res.add(new SemanticError("- Variable \"" + id + "\" not declared"));
			return res;
		} else {
			idType = varEntry.getType(); //memorizzo il tipo dell'entry
		}

		res.addAll(exp.checkSemantics(env));

		return res;
	}

	public String toPrint(String s) {
		return s + "Var:" + id + "\n" +
				exp.toPrint(s + "  "); 
	}

	public Node typeCheck() {

		if (!(FOOLlib.isEqualtype(exp.typeCheck(), idType))){
			System.err.println("You had 1 error:");
			System.err.println("\t- Incompatible value for variable \"" + id + "\"");
			System.exit(0);
		}

		return null;
	}

	public String codeGeneration() {

		String getAR = "";

		for (int i = 0; i < (nestinglevel - varEntry.getNestinglevel()); i++) 
			getAR += "lw\n";

		String code = exp.codeGeneration()
				+ "lfp\n"
				+ getAR
				+ "push " + varEntry.getOffset() + "\n"
				+ "add\n"
				+ "sw\n";

		if(varEntry.getMappedEntry() != null && varEntry.getMode().equals("var")) {

			String getARMappedEntry = "";
			
			for (int i=0; i<nestinglevel-varEntry.getMappedEntry().getNestinglevel(); i++) 
				getARMappedEntry += "lw\n";

			code	+= "\n"
					+ "push " + varEntry.getOffset() + "\n"
					+ "lfp\n"
					+ getAR
					+ "add\n"
					+ "lw\n"
					+ "\n"
					+ "lfp\n"
					+ getARMappedEntry
					+ "lw\n"
					+ "push " + varEntry.getMappedEntry().getOffset() + "\n"
					+ "add\n"
					+ "sw\n";
		}

		return code;

	}  

}  