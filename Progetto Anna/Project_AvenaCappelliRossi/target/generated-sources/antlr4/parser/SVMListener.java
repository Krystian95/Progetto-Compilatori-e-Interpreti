// Generated from SVM.g4 by ANTLR 4.4
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SVMParser}.
 */
public interface SVMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SVMParser#branchEqual}.
	 * @param ctx the parse tree
	 */
	void enterBranchEqual(@NotNull SVMParser.BranchEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#branchEqual}.
	 * @param ctx the parse tree
	 */
	void exitBranchEqual(@NotNull SVMParser.BranchEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#sub}.
	 * @param ctx the parse tree
	 */
	void enterSub(@NotNull SVMParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#sub}.
	 * @param ctx the parse tree
	 */
	void exitSub(@NotNull SVMParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeFramePointer}.
	 * @param ctx the parse tree
	 */
	void enterStoreFramePointer(@NotNull SVMParser.StoreFramePointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeFramePointer}.
	 * @param ctx the parse tree
	 */
	void exitStoreFramePointer(@NotNull SVMParser.StoreFramePointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeReturnAddress}.
	 * @param ctx the parse tree
	 */
	void enterStoreReturnAddress(@NotNull SVMParser.StoreReturnAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeReturnAddress}.
	 * @param ctx the parse tree
	 */
	void exitStoreReturnAddress(@NotNull SVMParser.StoreReturnAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(@NotNull SVMParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(@NotNull SVMParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadInfoObject}.
	 * @param ctx the parse tree
	 */
	void enterLoadInfoObject(@NotNull SVMParser.LoadInfoObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadInfoObject}.
	 * @param ctx the parse tree
	 */
	void exitLoadInfoObject(@NotNull SVMParser.LoadInfoObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#js}.
	 * @param ctx the parse tree
	 */
	void enterJs(@NotNull SVMParser.JsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#js}.
	 * @param ctx the parse tree
	 */
	void exitJs(@NotNull SVMParser.JsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadReturnValue}.
	 * @param ctx the parse tree
	 */
	void enterLoadReturnValue(@NotNull SVMParser.LoadReturnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadReturnValue}.
	 * @param ctx the parse tree
	 */
	void exitLoadReturnValue(@NotNull SVMParser.LoadReturnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(@NotNull SVMParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(@NotNull SVMParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(@NotNull SVMParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(@NotNull SVMParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#branchLessEqual}.
	 * @param ctx the parse tree
	 */
	void enterBranchLessEqual(@NotNull SVMParser.BranchLessEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#branchLessEqual}.
	 * @param ctx the parse tree
	 */
	void exitBranchLessEqual(@NotNull SVMParser.BranchLessEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeInfoObject}.
	 * @param ctx the parse tree
	 */
	void enterStoreInfoObject(@NotNull SVMParser.StoreInfoObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeInfoObject}.
	 * @param ctx the parse tree
	 */
	void exitStoreInfoObject(@NotNull SVMParser.StoreInfoObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#pop}.
	 * @param ctx the parse tree
	 */
	void enterPop(@NotNull SVMParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#pop}.
	 * @param ctx the parse tree
	 */
	void exitPop(@NotNull SVMParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(@NotNull SVMParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(@NotNull SVMParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeWord}.
	 * @param ctx the parse tree
	 */
	void enterStoreWord(@NotNull SVMParser.StoreWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeWord}.
	 * @param ctx the parse tree
	 */
	void exitStoreWord(@NotNull SVMParser.StoreWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadWord}.
	 * @param ctx the parse tree
	 */
	void enterLoadWord(@NotNull SVMParser.LoadWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadWord}.
	 * @param ctx the parse tree
	 */
	void exitLoadWord(@NotNull SVMParser.LoadWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeField}.
	 * @param ctx the parse tree
	 */
	void enterStoreField(@NotNull SVMParser.StoreFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeField}.
	 * @param ctx the parse tree
	 */
	void exitStoreField(@NotNull SVMParser.StoreFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(@NotNull SVMParser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(@NotNull SVMParser.AssemblyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeReturnValue}.
	 * @param ctx the parse tree
	 */
	void enterStoreReturnValue(@NotNull SVMParser.StoreReturnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeReturnValue}.
	 * @param ctx the parse tree
	 */
	void exitStoreReturnValue(@NotNull SVMParser.StoreReturnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadObjectPointer}.
	 * @param ctx the parse tree
	 */
	void enterLoadObjectPointer(@NotNull SVMParser.LoadObjectPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadObjectPointer}.
	 * @param ctx the parse tree
	 */
	void exitLoadObjectPointer(@NotNull SVMParser.LoadObjectPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadField}.
	 * @param ctx the parse tree
	 */
	void enterLoadField(@NotNull SVMParser.LoadFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadField}.
	 * @param ctx the parse tree
	 */
	void exitLoadField(@NotNull SVMParser.LoadFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadDispatchPointer}.
	 * @param ctx the parse tree
	 */
	void enterLoadDispatchPointer(@NotNull SVMParser.LoadDispatchPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadDispatchPointer}.
	 * @param ctx the parse tree
	 */
	void exitLoadDispatchPointer(@NotNull SVMParser.LoadDispatchPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(@NotNull SVMParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(@NotNull SVMParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadFramePointer}.
	 * @param ctx the parse tree
	 */
	void enterLoadFramePointer(@NotNull SVMParser.LoadFramePointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadFramePointer}.
	 * @param ctx the parse tree
	 */
	void exitLoadFramePointer(@NotNull SVMParser.LoadFramePointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeDispatchPointer}.
	 * @param ctx the parse tree
	 */
	void enterStoreDispatchPointer(@NotNull SVMParser.StoreDispatchPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeDispatchPointer}.
	 * @param ctx the parse tree
	 */
	void exitStoreDispatchPointer(@NotNull SVMParser.StoreDispatchPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#jumpDispatchTable}.
	 * @param ctx the parse tree
	 */
	void enterJumpDispatchTable(@NotNull SVMParser.JumpDispatchTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#jumpDispatchTable}.
	 * @param ctx the parse tree
	 */
	void exitJumpDispatchTable(@NotNull SVMParser.JumpDispatchTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadReturnAddress}.
	 * @param ctx the parse tree
	 */
	void enterLoadReturnAddress(@NotNull SVMParser.LoadReturnAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadReturnAddress}.
	 * @param ctx the parse tree
	 */
	void exitLoadReturnAddress(@NotNull SVMParser.LoadReturnAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#branchGreaterEqual}.
	 * @param ctx the parse tree
	 */
	void enterBranchGreaterEqual(@NotNull SVMParser.BranchGreaterEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#branchGreaterEqual}.
	 * @param ctx the parse tree
	 */
	void exitBranchGreaterEqual(@NotNull SVMParser.BranchGreaterEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(@NotNull SVMParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(@NotNull SVMParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#push}.
	 * @param ctx the parse tree
	 */
	void enterPush(@NotNull SVMParser.PushContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#push}.
	 * @param ctx the parse tree
	 */
	void exitPush(@NotNull SVMParser.PushContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#loadHeapPointer}.
	 * @param ctx the parse tree
	 */
	void enterLoadHeapPointer(@NotNull SVMParser.LoadHeapPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#loadHeapPointer}.
	 * @param ctx the parse tree
	 */
	void exitLoadHeapPointer(@NotNull SVMParser.LoadHeapPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeHeapPointer}.
	 * @param ctx the parse tree
	 */
	void enterStoreHeapPointer(@NotNull SVMParser.StoreHeapPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeHeapPointer}.
	 * @param ctx the parse tree
	 */
	void exitStoreHeapPointer(@NotNull SVMParser.StoreHeapPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#halt}.
	 * @param ctx the parse tree
	 */
	void enterHalt(@NotNull SVMParser.HaltContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#halt}.
	 * @param ctx the parse tree
	 */
	void exitHalt(@NotNull SVMParser.HaltContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull SVMParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull SVMParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#storeObjectPointer}.
	 * @param ctx the parse tree
	 */
	void enterStoreObjectPointer(@NotNull SVMParser.StoreObjectPointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#storeObjectPointer}.
	 * @param ctx the parse tree
	 */
	void exitStoreObjectPointer(@NotNull SVMParser.StoreObjectPointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#copyFramePointer}.
	 * @param ctx the parse tree
	 */
	void enterCopyFramePointer(@NotNull SVMParser.CopyFramePointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#copyFramePointer}.
	 * @param ctx the parse tree
	 */
	void exitCopyFramePointer(@NotNull SVMParser.CopyFramePointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SVMParser#alloc}.
	 * @param ctx the parse tree
	 */
	void enterAlloc(@NotNull SVMParser.AllocContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#alloc}.
	 * @param ctx the parse tree
	 */
	void exitAlloc(@NotNull SVMParser.AllocContext ctx);
}