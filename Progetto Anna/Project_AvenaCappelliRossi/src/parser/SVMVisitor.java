// Generated from C:/Users/Laura/eclipse-workspace/CEI-FOOL/src/parser\SVM.g4 by ANTLR 4.7
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssembly(SVMParser.AssemblyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(SVMParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(SVMParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#alloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlloc(SVMParser.AllocContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove(SVMParser.RemoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(SVMParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(SVMParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#mult}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(SVMParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(SVMParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadWord(SVMParser.LoadWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreWord(SVMParser.StoreWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadField(SVMParser.LoadFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreField(SVMParser.StoreFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(SVMParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(SVMParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#branchEqual}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchEqual(SVMParser.BranchEqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#branchLessEqual}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchLessEqual(SVMParser.BranchLessEqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#branchGreaterEqual}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchGreaterEqual(SVMParser.BranchGreaterEqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#js}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJs(SVMParser.JsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#jumpDispatchTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpDispatchTable(SVMParser.JumpDispatchTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadReturnAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadReturnAddress(SVMParser.LoadReturnAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeReturnAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreReturnAddress(SVMParser.StoreReturnAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadReturnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadReturnValue(SVMParser.LoadReturnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeReturnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreReturnValue(SVMParser.StoreReturnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadFramePointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadFramePointer(SVMParser.LoadFramePointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeFramePointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreFramePointer(SVMParser.StoreFramePointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#copyFramePointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopyFramePointer(SVMParser.CopyFramePointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadHeapPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadHeapPointer(SVMParser.LoadHeapPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeHeapPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreHeapPointer(SVMParser.StoreHeapPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadDispatchPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadDispatchPointer(SVMParser.LoadDispatchPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeDispatchPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreDispatchPointer(SVMParser.StoreDispatchPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadInfoObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadInfoObject(SVMParser.LoadInfoObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeInfoObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreInfoObject(SVMParser.StoreInfoObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#loadObjectPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadObjectPointer(SVMParser.LoadObjectPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#storeObjectPointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreObjectPointer(SVMParser.StoreObjectPointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SVMParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#halt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(SVMParser.HaltContext ctx);
}