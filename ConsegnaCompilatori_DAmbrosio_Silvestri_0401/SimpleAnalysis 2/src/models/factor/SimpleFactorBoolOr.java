package models.factor;

import java.util.ArrayList;
import static util.CodeGeneration.*;
import models.Environment;
import models.type.SimpleType;
import models.value.SimpleValue;
import util.SemanticError;
import util.TypeCheck;

public class SimpleFactorBoolOr extends SimpleFactor {

	SimpleValue left;
	SimpleValue right;

	public SimpleFactorBoolOr(SimpleValue left, SimpleValue right) {
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
	public SimpleType typeCheck() {
		SimpleType leftCheck = left.typeCheck();
		SimpleType rightCheck = right.typeCheck();	
		String op = "||";
		return TypeCheck.typeCheckFactorBool(leftCheck,rightCheck, op);
	}

	@Override
	public String toPrint(String boolOr) {
		return boolOr+"OrBooleanOperation\n" + left.toPrint(boolOr+"  ")   
		+ right.toPrint(boolOr+"  ") ; 
	}

	@Override
	public String codeGeneration() {

		String labelTrue = freshLabel();
		String labelExit = freshLabel();

		return "push 1\n"+ 
				left.codeGeneration()+
				"beq "+ labelTrue +"\n"+
				right.codeGeneration()+
				"b "+labelExit+"\n"+
				labelTrue + ":\n"+
				"push 1\n"+
				labelExit + ":\n";

	}
}
