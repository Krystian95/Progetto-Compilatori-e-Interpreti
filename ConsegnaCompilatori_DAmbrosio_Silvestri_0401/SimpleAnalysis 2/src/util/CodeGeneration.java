package util;

public class CodeGeneration {

	private static int labCount=0;   
	private static int funLabCount=0; 
	private static String funCode=""; 

	public static String print() {
		return "print\n";
	}

	public static String freshLabel() { 
		return "label"+(labCount++);
	} 

	public static String freshFunLabel() { 
		return "function"+(funLabCount++);
	} 

	public static void putCode(String c) { 
		//aggiunge una linea vuota di separazione prima di funzione
		funCode+="\n"+c; 
	} 

	public static String getCode() { 
		return funCode;
	} 

}
