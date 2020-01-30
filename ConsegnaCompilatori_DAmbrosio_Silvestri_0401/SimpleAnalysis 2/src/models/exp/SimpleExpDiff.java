package models.exp;

import java.util.ArrayList;
import models.Environment;
import models.type.SimpleType;
import util.SemanticError;
import util.TypeCheck;

public class SimpleExpDiff extends SimpleExp {

	SimpleExp left;
	SimpleExp right;

	/**
	 * Represents a binary expression subtraction
	 * @param left 
	 * @param right
	 */

	public SimpleExpDiff(SimpleExp left, SimpleExp right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Check semantics in both side expressions
	 */

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		result.addAll(left.checkSemantics(e));
		result.addAll(right.checkSemantics(e));
		return result;	
	}

	@Override
	public String toPrint(String sub) {
		return sub+"Sub\n" + left.toPrint(sub+"  ")  
		+ right.toPrint(sub+"  ") ; 
	}


	@Override
	public SimpleType typeCheck() {
		String op = "+";
		return TypeCheck.typeCheckExp(left, right, op);
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() +
				right.codeGeneration() +
				"sub\n";
	}


}
