package util;

import java.util.ArrayList;
import java.util.HashMap;

import ast.STentry;

public class Environment {

	private ArrayList<HashMap<String, STentry>> symTable = new ArrayList<HashMap<String, STentry>>();
	private int nestingLevel = -1;
	private int offset = 0;
	private int parOffset = 0;
	private boolean isInsideFunction = false;
	private boolean isInsideDeclaration = false;
	private String idDeclaration;
	private boolean isInsideThenBranch = false;
	private boolean isInsideElseBranch = false;
	
	public void setIsInsideElseBranch(boolean isInsideElseBranch) {
		this.isInsideElseBranch = isInsideElseBranch;
	}
	
	public boolean getIsInsideElseBranch() {
		return this.isInsideElseBranch;
	}
	
	public void setIsInsideThenBranch(boolean isInsideThenBranch) {
		this.isInsideThenBranch = isInsideThenBranch;
	}
	
	public boolean getIsInsideThenBranch() {
		return this.isInsideThenBranch;
	}

	public void setIdDeclaration(String idDeclaration) {
		this.idDeclaration = idDeclaration;
	}

	public String getIdDeclaration() {
		return this.idDeclaration;
	}

	public void setIsInsideDeclaration(boolean isInsideDeclaration) {
		this.isInsideDeclaration = isInsideDeclaration;
	}

	public boolean getIsInsideDeclaration() {
		return this.isInsideDeclaration;
	}

	public void setIsInsideFunction(boolean isInsideFunction) {
		this.isInsideFunction = isInsideFunction;
	}

	public boolean getIsInsideFunction() {
		return this.isInsideFunction;
	}

	public ArrayList<HashMap<String, STentry>> getSymbTable(){
		return this.symTable;
	}

	public void addHasMapToSymbTable(HashMap<String,STentry> hm){
		this.symTable.add(hm);
	}

	public void removeHasMapFromSymbTable(int nestingLevel){
		this.symTable.remove(nestingLevel);
	}

	public int increaseNestingLevel() {
		return this.nestingLevel++;
	}

	public int decreaseNestingLevel() {
		return this.nestingLevel--;
	}

	public int getNestingLevel() {
		return this.nestingLevel;
	}

	public int getOffset() {
		return this.offset;
	}

	public void increaseOffset() {
		this.offset++;
	}

	public int decreaseOffset() {
		return this.offset--;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getParOffset() {
		return this.parOffset;
	}

	public void setParOffset(int parOffset) {
		this.parOffset = parOffset;
	}

	public void increaseParOffset() {
		this.parOffset++;
	}
}
