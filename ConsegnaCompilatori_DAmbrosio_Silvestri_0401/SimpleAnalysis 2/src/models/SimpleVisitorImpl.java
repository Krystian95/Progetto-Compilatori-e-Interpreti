package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import models.exp.SimpleExp;
import models.exp.SimpleExpDiff;
import models.exp.SimpleExpSum;
import models.factor.SimpleFactor;
import models.factor.SimpleFactorBoolAnd;
import models.factor.SimpleFactorBoolOr;
import models.factor.SimpleFactorIntEquals;
import models.factor.SimpleFactorIntGreater;
import models.factor.SimpleFactorIntGreaterEquals;
import models.factor.SimpleFactorIntNotEquals;
import models.parameter.SimpleParameter;
import models.factor.SimpleFactorIntLess;
import models.factor.SimpleFactorIntLessEquals;
import models.statement.SimpleStmt;
import models.statement.SimpleStmtAssignment;
import models.statement.SimpleStmtBlock;
import models.statement.SimpleStmtDelete;
import models.statement.SimpleStmtFunDeclaration;
import models.statement.SimpleStmtFunctionCall;
import models.statement.SimpleStmtIfThenElse;
import models.statement.SimpleStmtPrint;
import models.statement.SimpleStmtVarDeclaration;
import models.term.SimpleTerm;
import models.term.SimpleTermDiv;
import models.term.SimpleTermMult;
import models.type.SimpleType;
import models.type.SimpleTypeBool;
import models.type.SimpleTypeInt;
import models.value.SimpleValue;
import models.value.SimpleValueBoolean;
import models.value.SimpleValueExp;
import models.value.SimpleValueId;
import models.value.SimpleValueInteger;
import parser.ComplexStaticAnalysisBaseVisitor;
import parser.ComplexStaticAnalysisParser.*;
import util.Strings;

public class SimpleVisitorImpl extends ComplexStaticAnalysisBaseVisitor<SimpleElementBase> {	

	@Override
	public SimpleElementBase visitStatement(StatementContext ctx) {
		if (ctx==null || ctx.children==null)
			return null;
		//visit the first child, this works for every case
		return visit(ctx.getChild(0));
	}


	@Override
	public SimpleElementBase visitBlock(BlockContext ctx) {
		if (ctx==null)
			return null;

		//list for saving children statements
		List<SimpleStmt> children = new LinkedList<SimpleStmt>();
		//visit each children
		for(StatementContext stmtCtx : ctx.statement()) 
			children.add((SimpleStmt) visitStatement(stmtCtx));
		//construct block statement expression
		SimpleStmtBlock block = new SimpleStmtBlock(children);

		return block;		
	}



	@Override
	public SimpleElementBase visitAssignment(AssignmentContext ctx) {

		//get expression
		SimpleExp exp =  (SimpleExp) visit(ctx.exp());
		//get id of variable
		String id = ctx.ID().getText();
		//construct assignment expression
		SimpleStmtAssignment assign = new SimpleStmtAssignment(exp, id);
		return assign;
	}

	@Override
	public SimpleElementBase visitDeletion(DeletionContext ctx) {

		//get id of delete variable
		String idDelete = ctx.ID().getText();
		//construct delete expression
		SimpleStmtDelete delete = new SimpleStmtDelete(idDelete);
		return delete;
	}	

	@Override
	public SimpleElementBase visitPrint(PrintContext ctx) {

		//get expression
		SimpleExp exp =  (SimpleExp) visit(ctx.exp());
		//construct print exp
		SimpleStmtPrint print = new SimpleStmtPrint(exp);
		return print;
	}


	@Override
	public SimpleElementBase visitFunctioncall(FunctioncallContext ctx) {

		String nameFunCall = ctx.ID().getText();
		
		//list parameter
		List<SimpleExp> parameterFunCall = new ArrayList<>();
		for (ExpContext parCall: ctx.exp()){
			parameterFunCall.add((SimpleExp) visitExp(parCall));
		}
		//construct funCall statement
		SimpleStmtFunctionCall functionCall = new SimpleStmtFunctionCall(nameFunCall, parameterFunCall);
		return functionCall;
	}

	@Override
	public SimpleElementBase visitIfthenelse(IfthenelseContext ctx) {
		
		//get condition expression
		SimpleExp conditionExp = (SimpleExp) visitExp (ctx.condition);
		//get then block
		SimpleStmtBlock thenBlock = (SimpleStmtBlock) visitBlock (ctx.thenBranch);
		//get else block
		SimpleStmtBlock elseBlock = (SimpleStmtBlock) visitBlock (ctx.elseBranch);
		//construct if then else statement
		SimpleStmtIfThenElse ifThenElse = new SimpleStmtIfThenElse(conditionExp, thenBlock, elseBlock);
		return ifThenElse;
	}

	@Override 
	public SimpleElementBase visitParameter(ParameterContext ctx) {

		//get type parameter
		SimpleType typePar = (SimpleType) visitType(ctx.type());
		//get parameter name
		String namePar = ctx.ID().getText();	
		//get parameter mode
		String modePar = null;
		if(ctx.modeParameter != null) {
			if(ctx.modeParameter.getText().startsWith("var")) {
				modePar = ctx.modeParameter.getText();
			} 
		} else {
			modePar = "val";
		}
		//construct parameter 
		SimpleParameter parameter = new SimpleParameter(typePar, modePar, namePar);
		return parameter;
	}

	@Override
	public SimpleElementBase visitVarDec(VarDecContext ctx) {

		if (ctx == null || ctx.ID() == null)
			return null;

		//Type of variable
		SimpleType type = (SimpleType) visitType(ctx.type());
		//Id of variable
		String id = ctx.ID().getText();
		//Exp of variable in dec
		SimpleExp exp =  (SimpleExp) visitExp(ctx.exp());
		//construct var declaration expression
		SimpleStmtVarDeclaration varDeclaration = new SimpleStmtVarDeclaration(type, id, exp);
		return varDeclaration;
	}

	@Override
	public SimpleElementBase visitFunDec(FunDecContext ctx) {

		if (ctx == null || ctx.ID() == null)
			return null;
		//get function name
		String idFun = ctx.ID().getText();
		List<SimpleParameter> parameterFun = new ArrayList<>();
		for (ParameterContext par: ctx.parameter()){
			parameterFun.add((SimpleParameter) visitParameter(par));
		}
		//get function body     
		SimpleStmtBlock body = (SimpleStmtBlock) visitBlock (ctx.block());
		//construct function declaration expression
		SimpleStmtFunDeclaration funDeclaration = new SimpleStmtFunDeclaration(idFun, parameterFun, body);
		return funDeclaration;
	}

	@Override
	public SimpleElementBase visitType(TypeContext ctx) {
		
		//get type
		String type = ctx.getText();
		if(type.equals("int")) {
			return new SimpleTypeInt();
		}
		else if(type.equals("bool")) {
			return new SimpleTypeBool();
		}
		else {
			System.err.print(Strings.ErrorTypeUnsupported);
		}
		//this will never happen thanks to the parser
		return null;
	}


	@Override
	public SimpleElementBase visitExp(ExpContext ctx) {

		if (ctx==null || ctx.left==null) {
			return null;
		}
		if(ctx.minus != null) {
			SimpleValueInteger zero = new SimpleValueInteger(0);
			if(ctx.right == null) {
				SimpleExp left = (SimpleTerm) visit(ctx.left);
				return new SimpleExpDiff(zero, left);		
			} else {
				//build left 
				SimpleTerm left = (SimpleTerm) visit(ctx.left);
				//build right
				SimpleExp right = (SimpleExp) visit(ctx.right);	
				//build op
				String op = ctx.op.getText();
				switch(op){	
					case "+": return new SimpleExpSum(new SimpleExpDiff(zero, left), right);
					case "-": return new SimpleExpDiff(new SimpleExpDiff(zero, left), right);
					default: System.err.println("Invalid operation in integer: " + op) ;
					return null;  //this should not happen
				}
			}
		}
		if(ctx.right == null){
			//it is a simple expression
			return visit(ctx.left);
		} else {
			//build left 
			SimpleExp left = (SimpleExp) visit(ctx.left);
			//build right
			SimpleExp right = (SimpleExp) visit(ctx.right);
			String op = ctx.op.getText();
			switch(op){	
				case "+": return new SimpleExpSum(left, right);
				case "-": return new SimpleExpDiff(left, right);
				default: System.err.println("Invalid operation in integer: " + op) ;
				return null;  //this should not happen
			}
		}
	}


	@Override
	public SimpleElementBase visitTerm(TermContext ctx) {

		if (ctx==null || ctx.left==null)
			return null;

		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );	
		}
		else {
			//build left 
			SimpleFactor left = (SimpleFactor) visit(ctx.left);
			//build right
			SimpleTerm right = (SimpleTerm) visit(ctx.right);
			//build op
			String op = ctx.op.getText();
			//build binary operation according operator
			switch(op){
			case "*": return new SimpleTermMult(left, right);
			case "/": return new SimpleTermDiv(left, right);
			default: System.err.println("Invalid operation in integer: " + op) ;
			return null;  //this should not happen
			}
		}
	}

	@Override
	public SimpleElementBase visitIntFactor(IntFactorContext ctx) {

		if (ctx==null || ctx.left==null)
			return null;

		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );	
		}
		else {
			
			//build left 
			SimpleValue left = (SimpleValue) visit(ctx.left);
			//build right
			SimpleValue right = (SimpleValue) visit(ctx.right);
			//build op
			String op = ctx.op.getText();
			//build binary operation according operator
			switch(op){
			case "==": return new SimpleFactorIntEquals(left, right);
			case "<" : return new SimpleFactorIntLess(left, right);
			case ">" : return new SimpleFactorIntGreater(left, right);
			case "<=": return new SimpleFactorIntLessEquals(left, right);
			case ">=": return new SimpleFactorIntGreaterEquals(left, right);
			case "!=": return new SimpleFactorIntNotEquals(left, right);
			default: System.err.println("Invalid operation in integer: " + op) ;
			return null;  //this should not happen
			}
		}
	}

	@Override
	public SimpleElementBase visitBoolFactor(BoolFactorContext ctx) {

		if (ctx==null || ctx.left==null)
			return null;

		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );	
		}
		else {
			
			//build left 
			SimpleValue left = (SimpleValue) visit(ctx.left);
			//build right
			SimpleValue right = (SimpleValue) visit(ctx.right);
			//build op
			String op = ctx.op.getText();
			//build binary operation according operator
			switch(op){
			case "&&": return new SimpleFactorBoolAnd(left, right);
			case "||" : return new SimpleFactorBoolOr(left, right);
			default: System.err.println("Invalid operation in boolean: " + op) ;
			return null; 
			}
		}
	}

	@Override
	public SimpleElementBase visitIntValue(IntValueContext ctx) {

		// notice that this method is not actually a rule but a named production #intVal
		//there is no need to perform a check here, the lexer ensures this text is an int
		int valueInteger = Integer.parseInt(ctx.INTEGER().getText());
		return new SimpleValueInteger(valueInteger);
	}

	@Override
	public SimpleElementBase visitBoolValue(BoolValueContext ctx) {

		boolean valueBool = Boolean.parseBoolean(ctx.getText());
		return new SimpleValueBoolean(valueBool);
	}

	@Override
	public SimpleElementBase visitExpValue(ExpValueContext ctx) {

		SimpleExp valueExp = (SimpleExp) visitExp(ctx.exp());
		return new SimpleValueExp(valueExp);
	}

	@Override
	public SimpleElementBase visitIdValue(IdValueContext ctx) {

		String valueId = ctx.ID().getText();
		return new SimpleValueId(valueId);
	}	
}
