// Generated from FOOL.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.misc.NotNull;
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
	 * Visit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVal(@NotNull FOOLParser.IntValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(@NotNull FOOLParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#functioncall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncall(@NotNull FOOLParser.FunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull FOOLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(@NotNull FOOLParser.BoolValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorForBoolean}
	 * labeled alternative in {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorForBoolean(@NotNull FOOLParser.FactorForBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fundec}
	 * labeled alternative in {@link FOOLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundec(@NotNull FOOLParser.FundecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(@NotNull FOOLParser.VarExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code deletionStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletionStatement(@NotNull FOOLParser.DeletionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#deletion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletion(@NotNull FOOLParser.DeletionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull FOOLParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull FOOLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull FOOLParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varasm}
	 * labeled alternative in {@link FOOLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarasm(@NotNull FOOLParser.VarasmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(@NotNull FOOLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorForInteger}
	 * labeled alternative in {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorForInteger(@NotNull FOOLParser.FactorForIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(@NotNull FOOLParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifthenelseStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthenelseStatement(@NotNull FOOLParser.IfthenelseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(@NotNull FOOLParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull FOOLParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatement(@NotNull FOOLParser.DeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(@NotNull FOOLParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull FOOLParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functioncallStatement}
	 * labeled alternative in {@link FOOLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncallStatement(@NotNull FOOLParser.FunctioncallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#ifthenelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthenelse(@NotNull FOOLParser.IfthenelseContext ctx);
}