package models.factor;

import java.util.ArrayList;
import models.Environment;
import models.type.SimpleType;
import models.value.SimpleValue;
import static util.CodeGeneration.*;
import util.SemanticError;
import util.TypeCheck;

public class SimpleFactorBoolAnd extends SimpleFactor {

	SimpleValue left;
	SimpleValue right;

	public SimpleFactorBoolAnd(SimpleValue left, SimpleValue right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {
		//create the result
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();
		//check semantics in the left and in the right value
		result.addAll(left.checkSemantics(e));
		result.addAll(right.checkSemantics(e));
		return result;
	}

	@Override
	public String toPrint(String boolAnd) {
		return boolAnd+"AndBooleanOperation\n" + left.toPrint(boolAnd+"  ")   
		+ right.toPrint(boolAnd+"  "); 
	}

	@Override
	public SimpleType typeCheck() {
		SimpleType leftCheck = left.typeCheck();
		SimpleType rightCheck = right.typeCheck();	
		String op = "&&";
		return TypeCheck.typeCheckFactorBool(leftCheck,rightCheck, op);
	}

	@Override
	public String codeGeneration() {
		String labelExit = freshLabel();
		String labelFalse = freshLabel();
		
		return "push 0\n"+ 
				left.codeGeneration()+
				"beq "+ labelFalse +"\n"+
				right.codeGeneration()+
				"b "+labelExit+"\n"+
				labelFalse + ":\n"+
				"push 0\n"+
				labelExit+":";



	}
}
