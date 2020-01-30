package util;

public class SemanticError {
	
	String errorDescription;
	
	public SemanticError(String errorDescription){
		this.errorDescription = errorDescription;
	}
	
	@Override
	public String toString() {
		return errorDescription;
	}
}
