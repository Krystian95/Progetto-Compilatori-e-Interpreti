package util;

import java.util.ArrayList;
import java.util.HashMap;

import ast.STentry;

public class Environment {
	
	//THESE VARIABLES SHOULDN'T BE PUBLIC
	//THIS CAN BE DONE MUCH BETTER
	
	public ArrayList<HashMap<String, STentry>>  symTable = new ArrayList<HashMap<String, STentry>>();
	public int nestingLevel = -1;
	public int offset = 0;
	public int parOffset = 0;
	public boolean isInsideFunction = false;
	public ArrayList<HashMap<String, STentry>>  entryToBeDeletedByFunCall = new ArrayList<HashMap<String, STentry>>();
	//livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides)
	//il "fronte" della lista di tabelle � symTable.get(nestingLevel)
		
	
}
