// Generated from FOOL.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FOOLParser}.
 */
public interface FOOLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIntVal(@NotNull FOOLParser.IntValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIntVal(@NotNull FOOLParser.IntValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodcallStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterMethodcallStm(@NotNull FOOLParser.MethodcallStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodcallStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitMethodcallStm(@NotNull FOOLParser.MethodcallStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(@NotNull FOOLParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(@NotNull FOOLParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExp}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterPrintExp(@NotNull FOOLParser.PrintExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExp}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitPrintExp(@NotNull FOOLParser.PrintExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull FOOLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull FOOLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(@NotNull FOOLParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(@NotNull FOOLParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 */
	void enterClassdec(@NotNull FOOLParser.ClassdecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 */
	void exitClassdec(@NotNull FOOLParser.ClassdecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterVarExp(@NotNull FOOLParser.VarExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitVarExp(@NotNull FOOLParser.VarExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void enterVardec(@NotNull FOOLParser.VardecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void exitVardec(@NotNull FOOLParser.VardecContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#letfun}.
	 * @param ctx the parse tree
	 */
	void enterLetfun(@NotNull FOOLParser.LetfunContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#letfun}.
	 * @param ctx the parse tree
	 */
	void exitLetfun(@NotNull FOOLParser.LetfunContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(@NotNull FOOLParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(@NotNull FOOLParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull FOOLParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull FOOLParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void enterVarasm(@NotNull FOOLParser.VarasmContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void exitVarasm(@NotNull FOOLParser.VarasmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull FOOLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull FOOLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull FOOLParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull FOOLParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(@NotNull FOOLParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(@NotNull FOOLParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(@NotNull FOOLParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(@NotNull FOOLParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBaseExp(@NotNull FOOLParser.BaseExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBaseExp(@NotNull FOOLParser.BaseExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStm(@NotNull FOOLParser.AssignmentStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStm(@NotNull FOOLParser.AssignmentStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterLetInExp(@NotNull FOOLParser.LetInExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitLetInExp(@NotNull FOOLParser.LetInExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNewExp(@NotNull FOOLParser.NewExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNewExp(@NotNull FOOLParser.NewExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull FOOLParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull FOOLParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(@NotNull FOOLParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(@NotNull FOOLParser.FunExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclaration(@NotNull FOOLParser.FunDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclaration(@NotNull FOOLParser.FunDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNullExp(@NotNull FOOLParser.NullExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNullExp(@NotNull FOOLParser.NullExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void enterStms(@NotNull FOOLParser.StmsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void exitStms(@NotNull FOOLParser.StmsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodcallExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterMethodcallExp(@NotNull FOOLParser.MethodcallExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodcallExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitMethodcallExp(@NotNull FOOLParser.MethodcallExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(@NotNull FOOLParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(@NotNull FOOLParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(@NotNull FOOLParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(@NotNull FOOLParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#denied}.
	 * @param ctx the parse tree
	 */
	void enterDenied(@NotNull FOOLParser.DeniedContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#denied}.
	 * @param ctx the parse tree
	 */
	void exitDenied(@NotNull FOOLParser.DeniedContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(@NotNull FOOLParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(@NotNull FOOLParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#funbody}.
	 * @param ctx the parse tree
	 */
	void enterFunbody(@NotNull FOOLParser.FunbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#funbody}.
	 * @param ctx the parse tree
	 */
	void exitFunbody(@NotNull FOOLParser.FunbodyContext ctx);
}