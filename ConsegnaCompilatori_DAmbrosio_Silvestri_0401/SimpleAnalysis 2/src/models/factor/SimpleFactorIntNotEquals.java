package models.factor;

import static util.CodeGeneration.freshLabel;

import java.util.ArrayList;
import models.Environment;
import models.type.SimpleType;
import models.value.SimpleValue;
import util.SemanticError;
import util.TypeCheck;

public class SimpleFactorIntNotEquals extends SimpleFactor {

	SimpleValue left;
	SimpleValue right;

	public SimpleFactorIntNotEquals(SimpleValue left, SimpleValue right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		//create the result
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		//check semantics in the left and in the right exp
		result.addAll(left.checkSemantics(e));
		result.addAll(right.checkSemantics(e));
		return result;
	}

	@Override
	public String toPrint(String s) {
		return s+"NotEqual\n" + left.toPrint(s+"  ")   
		+ right.toPrint(s+"  ") ; 
	}

	@Override
	public SimpleType typeCheck() {		
		SimpleType leftCheck = left.typeCheck();
		SimpleType rightCheck = right.typeCheck();	
		String op = "!=";	    
		return TypeCheck.typeCheckFactorInt(leftCheck,rightCheck, op);
	}

	@Override
	public String codeGeneration() {
		String labelExit1 = freshLabel(); 
		String labelExit2 = freshLabel();

		return left.codeGeneration()+
				right.codeGeneration()+
				"bne "+ labelExit1 +"\n"+
				"push 0\n"+
				"b " + labelExit2 + "\n" +
				labelExit1 + ":\n"+
				"push 1\n" +
				labelExit2 + ":\n";
	}
}
