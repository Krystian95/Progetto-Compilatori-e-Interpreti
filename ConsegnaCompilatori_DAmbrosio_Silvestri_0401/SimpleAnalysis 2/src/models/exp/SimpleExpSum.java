package models.exp;

import java.util.ArrayList;
import models.Environment;
import models.type.SimpleType;
import util.SemanticError;
import util.TypeCheck;

public class SimpleExpSum extends SimpleExp {


	SimpleExp left;
	SimpleExp right;

	/**
	 * Represents a binary expression sum
	 * @param left 
	 * @param right
	 */

	public SimpleExpSum(SimpleExp left, SimpleExp right) {
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
	public String toPrint(String sum) {
		return sum+"Sum\n" + left.toPrint(sum+"  ")  
		+ right.toPrint(sum+"  ") ; 
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
				"add\n";
	}

}
