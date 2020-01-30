package models.term;

import java.util.ArrayList;
import models.Environment;
import models.factor.SimpleFactor;
import models.type.SimpleType;
import util.SemanticError;
import util.TypeCheck;

public class SimpleTermMult extends SimpleTerm {

	SimpleFactor left;
	SimpleTerm right;

	/**
	 * Represents a binary expression multiplication
	 * @param left
	 * @param right
	 */

	public SimpleTermMult(SimpleFactor left, SimpleTerm right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Check semantics in both side expression
	 */

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		result.addAll(left.checkSemantics(e));
		result.addAll(right.checkSemantics(e));

		return result;
	}

	@Override
	public String toPrint(String s) {
		return s+"Mult\n" + left.toPrint(s+"  ")  
		+ right.toPrint(s+"  ") ; 
	}


	@Override
	public SimpleType typeCheck() {
		String op = "*";
		return TypeCheck.typeCheckTerm(left, right, op);
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() +
				right.codeGeneration() +
				"mult\n";
	}

}
