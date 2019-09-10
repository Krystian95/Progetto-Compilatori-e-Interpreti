package models.compiler;

import models.compiler.stentry.FunStEntry;
import models.compiler.stentry.StEntry;
import models.compiler.stentry.VarStEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Environment {

	private final LinkedList<HashMap<String, VarStEntry>> vtable = new LinkedList<>();
	private final LinkedList<HashMap<String, FunStEntry>> ftable = new LinkedList<>();
	private final LinkedList<Integer> offset = new LinkedList<>();
	private boolean insideFunction = false;
	private int nestingLevel = -1;
	private static final int BASE_OFFSET = 0;
	private String declaredVariable;
	private boolean isInsideVarDeclaration = false;

	public boolean isInsideFunctionDeclaration() {
		return insideFunction;
	}

	public void setInsideFunctionDeclaration(boolean insideFunction) {
		this.insideFunction = insideFunction;
	}

	public void addVariable(String id, VarStEntry val) {
		Objects.requireNonNull(vtable.peek()).put(id, val);
	}

	public void addFunction(String id, FunStEntry val) {
		Objects.requireNonNull(ftable.peek()).put(id, val);
	}

	public void openScope() {
		nestingLevel++;
		vtable.push(new HashMap<>());
		ftable.push(new HashMap<>());
		offset.push(BASE_OFFSET);
	}

	public void closeScope(){
		nestingLevel--;
		vtable.pop();
		ftable.pop();
		offset.pop();
	}

	public boolean containsVariable(String id){

		if(id==null) return false;

		for(HashMap<String, VarStEntry> scope: vtable){
			if(scope.containsKey(id))
				return true;
		}
		return false;
	}

	public boolean containsFunction(String id){

		for(HashMap<String, FunStEntry> scope: ftable){
			if(scope.containsKey(id))
				return true;
		}
		return false;
	}

	public boolean containsVariableLocal(String id){
		assert vtable.peek() != null;
		StEntry scope = vtable.peek().get(id);
		return scope != null;
	}

	public VarStEntry getVariableValue(String id){
		for(HashMap<String, VarStEntry> scope: vtable){
			if(scope.containsKey(id)){
				return scope.get(id);				
			}
		}
		return null;
	}

	public FunStEntry getFunctionValue(String id){
		for(HashMap<String, FunStEntry> scope: ftable){
			if(scope.containsKey(id)){
				return scope.get(id);
			}
		}
		return null;
	}

	public StEntry getVariableValueLocal(String id){
		return Objects.requireNonNull(vtable.peek()).get(id);
	}

	public int getNestingLevel() {
		return this.nestingLevel;
	}

	public void setToBeDeletedOnFunCall(StEntry entry) {
		entry.setToBeDeleted(true);
	}

	public boolean isInCurrentScope(int identifierNL){
		return identifierNL == nestingLevel;
	}


	public int getIncrementOffset() {
		int topOffSet = offset.pop();
		topOffSet  = topOffSet + 4;
		offset.push(topOffSet);
		return topOffSet;
	}

	public String getDeclaredVariable() {
		return this.declaredVariable;
	}

	public void setDeclaredVariable(String id) {
		this.declaredVariable = id;
	}

	public boolean isInsideVarDeclaration() {
		return this.isInsideVarDeclaration;
	}

	public void setIsInsideVarDeclaration(boolean value) {
		this.isInsideVarDeclaration = value;
	}
}
