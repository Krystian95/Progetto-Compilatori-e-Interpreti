// Generated from FOOL.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FOOLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FOOLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FOOLParser#initblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitblock(FOOLParser.InitblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(FOOLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(FOOLParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code deletionStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletionStatement(FOOLParser.DeletionStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(FOOLParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functioncallStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncallStatement(FOOLParser.FunctioncallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifthenelseStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthenelseStatement(FOOLParser.IfthenelseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatement(FOOLParser.DeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(FOOLParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FOOLParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#deletion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletion(FOOLParser.DeletionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(FOOLParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#functioncall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncall(FOOLParser.FunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#ifthenelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthenelse(FOOLParser.IfthenelseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varasm}
	 * labeled alternative in {@link FOOLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fundec}
	 * labeled alternative in {@link FOOLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundec(FOOLParser.FundecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FOOLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(FOOLParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(FOOLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(FOOLParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorForInteger}
	 * labeled alternative in {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorForInteger(FOOLParser.FactorForIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorForBoolean}
	 * labeled alternative in {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorForBoolean(FOOLParser.FactorForBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVal(FOOLParser.IntValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(FOOLParser.BoolValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(FOOLParser.VarExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(FOOLParser.BaseExpContext ctx);
}