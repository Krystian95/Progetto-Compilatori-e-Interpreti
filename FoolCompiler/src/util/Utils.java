package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.STentry;

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
	}

	public static int countVarDec(String text, ArrayList<HashMap<String,STentry>> hm, int nestLev) {

		int maxOffset = 0;

		for (HashMap<String,STentry> temp : hm) {
			for (Map.Entry<String, STentry> entry : temp.entrySet()) {
				if(entry.getValue().getNestinglevel() == nestLev && entry.getValue().getMappedEntry() == null)
					if(Math.abs(entry.getValue().getOffset()) > maxOffset) {
						maxOffset = Math.abs(entry.getValue().getOffset());
					}
			}
		}

		return maxOffset;
	}

}
