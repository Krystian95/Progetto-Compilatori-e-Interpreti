package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import util.SemanticError;

public class Utils {

	public static void printHashMap(String text, ArrayList<HashMap<String,STentry>> hm) {

		System.out.println("---------------------------");
		System.out.println(text);
		System.out.println("---------------------------");
		System.out.println("HashMap = ");
		for (HashMap<String,STentry> temp : hm) {
			for (Map.Entry<String, STentry> entry : temp.entrySet()) {
				System.out.println("Key: " + entry.getKey() + " Value:\n" + entry.getValue().toPrint("\t\t"));
			}
		}

		System.out.println("---------------------------");

		/*for (Map.Entry<String, STentry> entry : hm.) {
			for (Map.Entry<String, STentry> entry : hm.en) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().toPrint(""));
			}
		}*/
	}

	/*public static STentry getEntryFunCallByIndex(LinkedHashMap<Integer, LinkedHashMap<String, STentry>> functionDecParList, LinkedHashMap<Integer, LinkedHashMap<String, STentry>> functionCallParList, int index) {

		STentry foundEntry = null;

		System.err.println("\nindex to find: " + index);

		if(functionDecParList.size() > 0) {
			for(Entry<Integer, LinkedHashMap<String, STentry>> item : functionCallParList.entrySet()) {

				if(item.getKey() == index) {

					LinkedHashMap<String, STentry> parCalled = functionCallParList.get(item.getKey());

					for(Map.Entry<String, STentry> itemToCheck : parCalled.entrySet()) {
						foundEntry = itemToCheck.getValue();
						System.err.println("\nkey found: " + itemToCheck.getKey());
						System.err.println("\nentry found: " + foundEntry.toPrint(""));
					}
				}
			}
		}

		return foundEntry;

	}*/

	public static int countVarDec(String text, ArrayList<HashMap<String,STentry>> hm, int nestLev) {
		int cont=0;
		for (HashMap<String,STentry> temp : hm) {
			for (Map.Entry<String, STentry> entry : temp.entrySet()) {
				//System.out.println(entry.getKey()+" | "+entry.getValue().getNestinglevel()+" | "+nestLev);
				if(entry.getValue().getNestinglevel()==nestLev)
					cont++;
			}
		}

		return cont;
	}

}
