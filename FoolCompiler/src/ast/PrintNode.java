package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;

public class PrintNode implements Node {

	private Node val;

	public PrintNode (Node v) {
		val=v;
		//System.out.println("LA PRINT STA TAMPANDO: "+val.toPrint("val="));
	}

	public String toPrint(String s) {
		return s+"Print\n" + val.toPrint(s+"  ") ;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		System.out.println("LA PRINT STA TAMPANDO a nestingLevel = "+env.nestingLevel);

		Utils.printHashMap("STAMPA PRIMA DELLA PRINT:",env.symTable);

		return val.checkSemantics(env);
	}

	public Node typeCheck() {
		return val.typeCheck();
	}  

	public String codeGeneration() {
		return val.codeGeneration()+"print\n";
	}

}  