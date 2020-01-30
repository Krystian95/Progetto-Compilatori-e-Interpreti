package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import models.stentry.SimpleFunSTEntry;
import models.stentry.SimpleSTEntry;
import models.stentry.SimpleVarSTEntry;
import util.CountVarDecNestingLevel;

public class Environment {

	public LinkedList<HashMap<String, SimpleVarSTEntry>> vtable = new LinkedList<HashMap<String, SimpleVarSTEntry>>();
	public LinkedList<HashMap<String, SimpleFunSTEntry>> ftable = new LinkedList<HashMap<String, SimpleFunSTEntry>>();
	public ArrayList<CountVarDecNestingLevel> resultCountVar = new ArrayList<CountVarDecNestingLevel>();
	public int nestingLevel = -1;
	public int offset = 0;
	public int parOffset = 1;
	boolean isInFunction = false;
	boolean isInVarDeclaration = false;
	String idVarDeclaration;
	boolean isInFunDeclaration = false;
	public int countVarDeclaration = 0;
	boolean isInAssignment = false;

	/**
	 * Adds variable with the given id to existence	
	 * @param id
	 */
	public void addVariable(String id, SimpleVarSTEntry varSTEntry) {
		vtable.peek().put(id, varSTEntry);

	}

	/**
	 * Adds function with the given id to existence	
	 * @param id
	 */
	public void addFunction(String id, SimpleFunSTEntry funSTEntry) {
		ftable.peek().put(id, funSTEntry);
	}


	/** 
	 * Inserts a new scope into the environment.
	 * When a scope is inserted old scope is clone so previous defined
	 * variables still exist
	 */
	public void openScope(){
		nestingLevel++;
		vtable.push(new HashMap<String, SimpleVarSTEntry>());;
		ftable.push(new HashMap<String,SimpleFunSTEntry>());
	}


	/**
	 * Drops the current scope and returns to the outer scope
	 * removing all changes and additions done within this scope 
	 */

	public void closeScope(){

		for(int i = 0; i < resultCountVar.size(); i++) {
			if(resultCountVar.get(i).getNestingLevel() == nestingLevel) {
				resultCountVar.remove(i);	
			}
		}

		nestingLevel--;
		vtable.pop();
		ftable.pop();
	}

	/**
	 * Given an id determines if the variable belongs to the environment
	 * this is to check the scopes from inner to outer looking for the variable
	 * @param stmt
	 * @param id 
	 * @param nestingLevel 
	 */
	public boolean containsVariableInVTable(String stmt, String id, int nestingLevelVarLocal){

		if(id==null) 
			return false;

		if (stmt.equals("noDeclaration")) {
			for(HashMap<String, SimpleVarSTEntry> scopeVariable:vtable){
				if(scopeVariable.containsKey(id) && (nestingLevelVarLocal >= nestingLevel)) {
					return true;
				}
			}
		} else if (stmt.equals("declaration")) {
			for(HashMap<String, SimpleVarSTEntry> scopeVariable:vtable){
				if(scopeVariable.containsKey(id) && (nestingLevelVarLocal == scopeVariable.get(id).getNestinglevel())) {
					return true;
				}
			}		
		}

		return false;
	}

	/**
	 * Given an id determines if the variable belongs to the environment
	 * this is to check the scopes from inner to outer looking for the variable
	 * @param id
	 */
	public boolean containsVariableInFTable(String id, int nestingLevelFunLocal){			

		for(HashMap<String, SimpleFunSTEntry> scopeFun:ftable){
			if(scopeFun.containsKey(id))
				return true;
		}
		return false;
	}

	/**
	 * Decrement offset of a variable
	 */

	public int decrementOffset() {	
		return offset--;
	}

	/**
	 * Increment parOffset of a parameter
	 */
	
	public int incremetParOffset() {	
		return parOffset++;
	}

	public int getNestingLevel() {
		return this.nestingLevel;
	}
	
	/**
	 * Count the number of variable declared in a given nestingLevel
	 * @nestingLevel
	 */

	public int incrementVarDeclaration(int nestingLevel) {	
		countVarDeclaration++;
		int nestingLevelCurrent = -1; 
		if(resultCountVar.isEmpty()) {
			CountVarDecNestingLevel result = new CountVarDecNestingLevel(nestingLevel, countVarDeclaration);
			resultCountVar.add(result);
		} else {
			for(int i = 0; i < resultCountVar.size(); i++) {
				if(resultCountVar.get(i).getNestingLevel() == nestingLevel) {
					countVarDeclaration = resultCountVar.get(i).getCountVariableInScope();
					countVarDeclaration++;
					resultCountVar.remove(i);	
					nestingLevelCurrent = i;
					break;
				}
			}

			if(nestingLevelCurrent == -1) {
				countVarDeclaration = 1;
			} 
			CountVarDecNestingLevel result = new CountVarDecNestingLevel(nestingLevel, countVarDeclaration);
			resultCountVar.add(result);

		}
		return countVarDeclaration;
	}

	/**
	 * Check local scope for variable
	 * @param id of the variable
	 * @return variable value in current scope, null otherwise
	 */

	public SimpleSTEntry getVariableValueLocal(String id){
		return vtable.peek().get(id);
	}

	/**
	 * Check for variable
	 * @param id of the variable
	 * @return variable value, null if the variable doesnt exist
	 */

	public SimpleVarSTEntry getVariableValue(String id){
		for(HashMap<String, SimpleVarSTEntry> scope:vtable){
			if(scope.containsKey(id)){
				return scope.get(id);				
			}
		}
		return null;
	}
	
	/**
	 * Check for variable
	 * @param id of the function
	 * @return function value, null if the name function doesnt exist
	 */

	public SimpleFunSTEntry getFunctionValue(String id){
		for(HashMap<String, SimpleFunSTEntry> scope:ftable){
			if(scope.containsKey(id)){
				return scope.get(id);				
			}
		}
		return null;
	}
	
	/**
	 * Check for variable
	 * @param id of the variable
	 * @return true if the variable is deleted
	 */

	public boolean isDeletedVtable(String id){
		for(HashMap<String, SimpleVarSTEntry> scope:vtable){
			if(scope.containsKey(id)){
				if(scope.get(id).isDeleted()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check for all variables 
	 * @return list of entry deleted
	 */
	
	public ArrayList<SimpleVarSTEntry> getDeletionVtable(){
		ArrayList<SimpleVarSTEntry> entryDelete = new ArrayList<SimpleVarSTEntry>();
		for(HashMap<String, SimpleVarSTEntry> scope:vtable){
			for (Entry<String, SimpleVarSTEntry> entry : scope.entrySet()) {
				if(entry.getValue().isDeleted()) {
					entryDelete.add(entry.getValue());
				}
			}
		}
		return entryDelete;
	}

	public boolean isInVarDeclaration() {
		return isInVarDeclaration;
	}

	public void setInVarDeclaration(boolean isInVarDeclaration) {
		this.isInVarDeclaration = isInVarDeclaration;
	}

	public String getIdVarDeclaration() {
		return idVarDeclaration;
	}

	public void setIdVarDeclaration(String idVarDeclaration) {
		this.idVarDeclaration = idVarDeclaration;
	}

	public boolean isInFunDeclaration() {
		return isInFunDeclaration();
	}

	public void setInFunDeclaration(boolean isInFunDeclaration) {
		this.isInFunDeclaration = isInFunDeclaration;
	}

	public boolean isInAssignment() {
		return isInAssignment;
	}

	public void setInAssignment(boolean isInAssignment) {
		this.isInAssignment = isInAssignment;
	}

}
