// Generated from ComplexStaticAnalysis.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ComplexStaticAnalysisParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ComplexStaticAnalysisVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ComplexStaticAnalysisParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ComplexStaticAnalysisParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ComplexStaticAnalysisParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#deletion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletion(ComplexStaticAnalysisParser.DeletionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(ComplexStaticAnalysisParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#functioncall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncall(ComplexStaticAnalysisParser.FunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#ifthenelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthenelse(ComplexStaticAnalysisParser.IfthenelseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(ComplexStaticAnalysisParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(ComplexStaticAnalysisParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ComplexStaticAnalysisParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(ComplexStaticAnalysisParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(ComplexStaticAnalysisParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexStaticAnalysisParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ComplexStaticAnalysisParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intFactor}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntFactor(ComplexStaticAnalysisParser.IntFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolFactor}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolFactor(ComplexStaticAnalysisParser.BoolFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intValue}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntValue(ComplexStaticAnalysisParser.IntValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolValue}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolValue(ComplexStaticAnalysisParser.BoolValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expValue}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpValue(ComplexStaticAnalysisParser.ExpValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idValue}
	 * labeled alternative in {@link ComplexStaticAnalysisParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdValue(ComplexStaticAnalysisParser.IdValueContext ctx);
}