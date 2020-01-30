package util;

public class CountVarDecNestingLevel {
	
	private int nestingLevel;
	private int countVariableInScope;
	
	public CountVarDecNestingLevel(int nestingLevel, int countVariableInScope){
		this.setNestingLevel(nestingLevel);
		this.countVariableInScope = countVariableInScope;
	}
	
	public int getCountVariableInScope() {
		return countVariableInScope;
	}
	public void setCountVariableInScope(int countVariableInScope) {
		this.countVariableInScope = countVariableInScope;
	}
	
	public int getNestingLevel() {
		return nestingLevel;
	}
	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}
	

}
