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
	public Node visitInitblock(InitblockContext ctx) {

		Node statement = visit(ctx.block());

		InitBlockNode block = new InitBlockNode(statement);

		return block;

	}

	@Override
	public Node visitBlock(BlockContext ctx) {

		//list for saving children statements
		ArrayList<Node> statements = new ArrayList<Node>();

		//visit each children
		for(StatementContext stmtCtx : ctx.statement()) {
			Node tmp = visit(stmtCtx);
			statements.add(tmp);
		}

		//construct block statement expression
		BlockNode block = new BlockNode(statements);

		return block;

	}

	@Override
	public Node visitDeletion(DeletionContext ctx) {

		DeletionNode block = new DeletionNode(ctx.ID().toString());

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

	@Override
	public Node visitAssignment(AssignmentContext ctx) {

		//visit the exp
		Node expNode = visit(ctx.exp());

		return new AssignmentNode(ctx.ID().getText(), expNode);
	}


	@Override
	public Node visitVarasm(VarasmContext ctx) {

		//visit the type
		Node typeNode = visit(ctx.type());

		//visit the exp
		Node expNode = visit(ctx.exp());

		return new VarNode(ctx.ID().getText(), typeNode, expNode);
	}

	@Override
	public Node visitType(TypeContext ctx) {

		if(ctx.getText().equals("int"))
			return new IntTypeNode();
		else if(ctx.getText().equals("bool"))
			return new BoolTypeNode();

		return null;

	}

	@Override
	public Node visitPrint(PrintContext ctx) {
		return new PrintNode(visit(ctx.exp()));
	}

	@Override
	public Node visitExp(ExpContext ctx) {

		if(ctx.minus != null) {

			IntNode zero = new IntNode(0);

			if(ctx.right == null){
				return new MinusNode(zero, visit( ctx.left ));
			}else{
				if(ctx.op.getText().equals("+"))
					return new PlusNode(new MinusNode(zero, visit(ctx.left)), visit(ctx.right));
				else return new MinusNode(new MinusNode(zero, visit(ctx.left)), visit(ctx.right));
			}
		}

		if(ctx.right == null){
			return visit( ctx.left );
		}else{
			if(ctx.op.getText().equals("+"))
				return new PlusNode(visit(ctx.left), visit(ctx.right));
			else return new MinusNode(visit(ctx.left), visit(ctx.right));
		}

	}

	@Override
	public Node visitTerm(TermContext ctx) {

		if(ctx.right == null){
			return visit( ctx.left );
		}else{
			if(ctx.op.getText().equals("*"))
				return new MultNode(visit(ctx.left), visit(ctx.right));
			else return new DivNode(visit(ctx.left), visit(ctx.right));
		}
	}

	@Override
	public Node visitFactorForInteger(FactorForIntegerContext ctx) {

		if(ctx.right == null){
			return visit(ctx.left);
		}else{
			Node node_sx = visit(ctx.left);
			Node node_dx = visit(ctx.right);

			switch (ctx.op.getText()) {

			case "==":
				return new EqualNode(node_sx, node_dx);
			case ">":
				return new GreaterNode(node_sx, node_dx);
			case "<=":
				return new LessEqualNode(node_sx, node_dx);
			case "<":
				return new LesserNode(node_sx, node_dx);
			case ">=":
				return new GreaterEqualNode(node_sx, node_dx);
			case "!=":
				return new NotEqualNode(node_sx, node_dx);
			default:
				System.err.println("Operazione integer non riconosciuta");
				System.exit(0);
				return null;
			}
		}

	}

	@Override
	public Node visitFactorForBoolean(FactorForBooleanContext ctx) {

		if(ctx.right == null){
			return visit(ctx.left);
		}else{
			Node node_sx = visit(ctx.left);
			Node node_dx = visit(ctx.right);

			switch (ctx.op.getText()) {

			case "&&":
				return new AndBooleanOperationNode(node_sx, node_dx);
			case "||":
				return new OrBooleanOperationNode(node_sx, node_dx);
			default:
				System.err.println("Operazione boolean non riconosciuta");
				System.exit(0);
				return null;
			}
		}
	}

	@Override
	public Node visitIntVal(IntValContext ctx) {
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}

	@Override
	public Node visitBoolVal(BoolValContext ctx) {
		return new BoolNode(Boolean.parseBoolean(ctx.getText())); 
	}

	@Override
	public Node visitBaseExp(BaseExpContext ctx) {
		return visit(ctx.exp());

	}

	@Override
	public Node visitIfthenelse(IfthenelseContext ctx) {

		IfNode res;

		Node condExp = visit(ctx.cond);
		Node thenBlock = visit(ctx.thenBranch);
		Node elseBlock = visit(ctx.elseBranch);

		res = new IfNode(condExp, thenBlock, elseBlock);

		return res;
	}

	@Override
	public Node visitVarExp(VarExpContext ctx) {
		return new IdNode(ctx.ID().getText());

	}

	@Override
	public Node visitFundec(FundecContext ctx) {

		FunNode res = new FunNode(ctx.ID().getText());

		for(ParameterContext vc : ctx.parameter()) {
			String modePar = "val";

			if(vc.modePar != null) {
				modePar = vc.modePar.getText();
			}

			res.addPar(new ParNode(modePar, vc.ID().getText(), visit(vc.type())));
		}

		Node block = visit(ctx.block());

		res.addDecBody(null, block);

		return res;		
	}

	@Override
	public Node visitFunctioncall(FunctioncallContext ctx) {

		Node res;

		ArrayList<Node> args = new ArrayList<Node>();

		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));

		res = new CallNode(ctx.ID().getText(), args);

		return res;
	}

}
