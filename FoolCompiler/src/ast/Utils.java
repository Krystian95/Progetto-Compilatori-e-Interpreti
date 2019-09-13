package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static void printHashMap(String text, ArrayList<HashMap<String,STentry>> hm) {

		System.out.println("---------------------------");
		System.out.println(text);
		System.out.println("---------------------------");
		System.out.println("HashMap = ");
		for (HashMap<String,STentry> temp : hm) {
			for (Map.Entry<String, STentry> entry : temp.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().toPrint("\t\t"));
			}
		}
		
		System.out.println("---------------------------");

		/*for (Map.Entry<String, STentry> entry : hm.) {
			for (Map.Entry<String, STentry> entry : hm.en) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().toPrint(""));
			}
		}*/
	}
	
	public static int countVarDec(String text, ArrayList<HashMap<String,STentry>> hm, int nestLev) {
		int cont=0;
		System.out.println("---------------------------");
		System.out.println(text);
		System.out.println("---------------------------");
		System.out.println("HashMap = ");
		for (HashMap<String,STentry> temp : hm) {
			for (Map.Entry<String, STentry> entry : temp.entrySet()) {
				System.out.println(entry.getKey()+" | "+entry.getValue().getNestinglevel()+" | "+nestLev);
				if(entry.getValue().getNestinglevel()==nestLev)
					cont++;
			}
		}
		
		System.out.println("---------------------------");
		return cont;

		/*for (Map.Entry<String, STentry> entry : hm.) {
			for (Map.Entry<String, STentry> entry : hm.en) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().toPrint(""));
			}
		}*/
	}

}
