package util;

import java.util.ArrayList;
import java.util.HashMap;

import ast.STentry;

public class Environment {
	
	private ArrayList<HashMap<String,STentry>>  symTable = new ArrayList<HashMap<String,STentry>>();
	private int nestingLevel = -1;
	private int offset = 0;
	private int checkStep = 0;
	private int parOffset = 0;
	private int objectNumber = 0;
	//il "fronte" della lista di tabelle è symTable.get(nestingLevel)

	public ArrayList<HashMap<String,STentry>> getSymTable(){
	    return symTable;
    }

    public int getNestingLevel(){
	    return nestingLevel;
    }

    public void incrementNestingLevel(){
	    nestingLevel = nestingLevel+1;
    }

    public int decrementNestingLevel(){
	      nestingLevel = nestingLevel-1;
        return nestingLevel;
    }

    public int getOffset(){
	    return offset;
    }

    public void setOffset(int o){
	    offset = o;
    }

    public void decrementOffset(){
	    offset = offset-1;
    }

    public void incrementOffset() { offset = offset+1; }

    public int getCheckStep() { return checkStep; }

    public void setCheckStep(int cs) { checkStep = cs; }

    public int getParOffset() { return parOffset; }

    public void setParOffset(int po) {parOffset = po; }

    public void incrementParOffset() { parOffset = parOffset+1; }

    public int getObjectNumber() { return objectNumber; }

    public void incrementObjectNumber() { objectNumber = objectNumber + 1;}
}
