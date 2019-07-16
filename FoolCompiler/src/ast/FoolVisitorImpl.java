package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lib.FOOLlib;
import parser.*;
import parser.FOOLParser.*;
import util.SemanticError;

public class FoolVisitorImpl extends FOOLBaseVisitor<Node> {


	@Override
	public Node visitBlock(BlockContext ctx) {

		//list for saving children statements
		ArrayList<Node> statements = new ArrayList<Node>();

		//visit each children
		for(StatementContext stmtCtx : ctx.statement()) {
			System.out.println();
			System.out.println("stmtCtx = "+stmtCtx.getText());
			System.out.println("visitStatement("+stmtCtx.getText()+")");
			Node tmp=visit(stmtCtx);
			statements.add(tmp);
			System.out.println("ADDED visitStatement("+stmtCtx.getText()+") = "+tmp);
		}

		//System.out.println("statements = "+statements.toString());

		//construct block statement expression
		BlockNode block = new BlockNode(statements);

		return block;

	}

	@Override
	public Node visitAssignmentStatement(AssignmentStatementContext ctx) {
		return visit(ctx.assignment());
	}

	@Override
	public Node visitDeletionStatement(DeletionStatementContext ctx) {
		return visit(ctx.deletion());
	}

	@Override
	public Node visitPrintStatement(PrintStatementContext ctx) {
		return visit(ctx.print());
	}

	@Override
	public Node visitFunctioncallStatement(FunctioncallStatementContext ctx) {
		return visit(ctx.functioncall());
	}

	@Override
	public Node visitIfthenelseStatement(IfthenelseStatementContext ctx) {
		return visit(ctx.ifthenelse());
	}

	@Override
	public Node visitDeclarationStatement(DeclarationStatementContext ctx) {
		return visit(ctx.declaration());
	}

	@Override
	public Node visitBlockStatement(BlockStatementContext ctx) {
		return visit(ctx.block());
	}


	/*@Override
	public Node visitLetInExp(LetInExpContext ctx) {

		//resulting node of the right type
		ProgLetInNode res;

		//list of declarations in @res
		ArrayList<Node> declarations = new ArrayList<Node>();

		//visit all nodes corresponding to declarations inside the let context and store them in @declarations
		//notice that the ctx.let().dec() method returns a list, this is because of the use of * or + in the grammar
		//antlr detects this is a group and therefore returns a list
		for(DecContext dc : ctx.let().dec()){
			declarations.add( visit(dc) );
		}

		//visit exp context
		Node exp = visit( ctx.exp() );

		//build @res accordingly with the result of the visits to its content
		res = new ProgLetInNode(declarations,  exp);

		return res;
	}*/

	/*@Override
	public Node visitSingleExp(SingleExpContext ctx) {

		//simply return the result of the visit to the inner exp
		return new ProgNode(visit(ctx.exp()));

	}*/

	@Override
	public Node visitAssignment(AssignmentContext ctx) {


		System.out.println("visitAssignment called!");

		//visit the exp
		Node expNode = visit(ctx.exp());

		//build the varNode
		return new AssignmentNode(ctx.ID().getText(), expNode);
	}


	@Override
	public Node visitVarasm(VarasmContext ctx) {

		//declare the result node
		//VarNode result;

		//visit the type
		//Node typeNode = visit(ctx.vardec().type());
		Node typeNode = visit(ctx.type());

		//visit the exp
		//Node expNode = visit(ctx.exp());
		Node expNode = visit(ctx.exp());

		//build the varNode
		//return new VarNode(ctx.vardec().ID().getText(), typeNode, expNode);
		return new VarNode(ctx.ID().getText(), typeNode, expNode);
	}

	/*@Override
	public Node visitFun(FunContext ctx) {

		//initialize @res with the visits to the type and its ID
		FunNode res = new FunNode(ctx.ID().getText(), visit(ctx.type()));

		//add argument declarations
		//we are getting a shortcut here by constructing directly the ParNode
		//this could be done differently by visiting instead the VardecContext
		for(VardecContext vc : ctx.vardec())
			res.addPar( new ParNode(vc.ID().getText(), visit( vc.type() )) );

		//add body
		//create a list for the nested declarations
		ArrayList<Node> innerDec = new ArrayList<Node>();

		//check whether there are actually nested decs
		if(ctx.let() != null){
			//if there are visit each dec and add it to the @innerDec list
			for(DecContext dc : ctx.let().dec())
				innerDec.add(visit(dc));
		}

		//get the exp body
		Node exp = visit(ctx.exp());

		//add the body and the inner declarations to the function
		res.addDecBody(innerDec, exp);

		return res;		

	}*/

	@Override
	public Node visitType(TypeContext ctx) {
		if(ctx.getText().equals("int"))
			return new IntTypeNode();
		else if(ctx.getText().equals("bool"))
			return new BoolTypeNode();

		//this will never happen thanks to the parser
		return null;

	}

	@Override
	public Node visitPrint(PrintContext ctx) {
		return new PrintNode(visit(ctx.exp()));
	}

	@Override
	public Node visitExp(ExpContext ctx) {

		//this could be enhanced

		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			if(ctx.op.getText().equals("+"))
				return new PlusNode(visit(ctx.left), visit(ctx.right));
			else return new MinusNode(visit(ctx.left), visit(ctx.right));
		}

	}

	@Override
	public Node visitTerm(TermContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			if(ctx.op.getText().equals("*"))
				return new MultNode(visit(ctx.left), visit(ctx.right));
			else return new DivNode(visit(ctx.left), visit(ctx.right));
		}
	}

	@Override
	public Node visitFactorForInteger(FactorForIntegerContext ctx) {

		System.out.println("-----------");

		if(ctx.right == null){
			return visit(ctx.left);
		}else{
			Node node_sx = visit(ctx.left);
			Node type_sx = node_sx.typeCheck();

			Node node_dx = visit(ctx.right);
			Node type_dx = node_dx.typeCheck();

			System.out.println(type_sx.toPrint("------- type_sx = "));
			System.out.println(type_dx.toPrint("------- type_dx = "));

			if (FOOLlib.isSubtype(type_sx,new IntTypeNode()) && FOOLlib.isSubtype(type_dx,new IntTypeNode())) {
				
				int left =  Integer.parseInt(ctx.left.getText());
				int right =  Integer.parseInt(ctx.right.getText());

				boolean finalValue = false;

				switch (ctx.op.getText()) {

				case "==":
					if(left == right)
						finalValue=true;
					break;

				default:
					break;
				}

				System.out.println("finalValue = "+finalValue);

				return new BoolNode(finalValue);
			}else {
				System.out.println("finalValue FORZATO a false");
				return new BoolNode(false);
			}
		}
	}


	/*@Override
	public Node visitFactor(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			return new EqualNode(visit(ctx.left), visit(ctx.right));
		}
	}*/


	@Override
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal

		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}

	@Override
	public Node visitBoolVal(BoolValContext ctx) {

		//there is no need to perform a check here, the lexer ensures this text is a boolean
		return new BoolNode(Boolean.parseBoolean(ctx.getText())); 
	}

	@Override
	public Node visitBaseExp(BaseExpContext ctx) {

		//this is actually nothing in the sense that for the ast the parenthesis are not relevant
		//the thing is that the structure of the ast will ensure the operational order by giving
		//a larger depth (closer to the leafs) to those expressions with higher importance

		//this is actually the default implementation for this method in the FOOLBaseVisitor class
		//therefore it can be safely removed here

		return visit (ctx.exp());

	}

	/*@Override
	public Node visitIfExp(IfExpContext ctx) {

		//create the resulting node
		IfNode res;

		//visit the conditional, then the then branch, and then the else branch
		//notice once again the need of named terminals in the rule, this is because
		//we need to point to the right expression among the 3 possible ones in the rule

		Node condExp = visit (ctx.cond);

		Node thenExp = visit (ctx.thenBranch);

		Node elseExp = visit (ctx.elseBranch);

		//build the @res properly and return it
		res = new IfNode(condExp, thenExp, elseExp);

		return res;
	}*/

	@Override
	public Node visitVarExp(VarExpContext ctx) {

		//this corresponds to a variable access
		return new IdNode(ctx.ID().getText());

	}

	@Override
	public Node visitFunctioncall(FunctioncallContext ctx) {
		//this corresponds to a function invocation
		//declare the result
		Node res;

		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();

		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));

		//especial check for stdlib func
		//this is WRONG, THIS SHOULD BE DONE IN A DIFFERENT WAY
		//JUST IMAGINE THERE ARE 800 stdlib functions...
		if(ctx.ID().getText().equals("print"))
			res = new PrintNode(args.get(0));

		else
			//instantiate the invocation
			res = new CallNode(ctx.ID().getText(), args);

		return res;
	}

}
