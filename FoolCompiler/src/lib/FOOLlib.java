package lib;

import models.Node;
import node.*;

public class FOOLlib {

	private static int labCount = 0;

	private static int funLabCount = 0;

	private static String funCode = "";

	//valuta se il tipo "a" <= al tipo "b", dove "a" e "b" sono tipi di base: int o bool
	public static boolean isEqualtype (Node a, Node b) {
		return a.getClass().equals(b.getClass());
	} 

	public static String freshLabel() { 
		return "label" + (labCount++);
	} 

	public static String freshFunLabel() { 
		return "function" + (funLabCount++);
	} 

	public static void putCode(String c) { 
		funCode += "\n" + c; //aggiunge una linea vuota di separazione prima di funzione
	} 

	public static String getCode() { 
		return funCode;
	}
}