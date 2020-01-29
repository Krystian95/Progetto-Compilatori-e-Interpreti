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
	public boolean isInsideDeclaration = false;
	public String idDeclaration;
	public boolean isInsideThenBranch = false;
	public boolean isInsideElseBranch = false;
}
