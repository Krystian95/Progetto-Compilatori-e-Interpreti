package ast;

import org.antlr.v4.runtime.tree.ParseTree;
import parser.SVMBaseVisitor;

import parser.SVMParser;
import parser.SVMParser.AssemblyContext;
import parser.SVMParser.PushContext;
import parser.SVMParser.PopContext;
import parser.SVMParser.AllocContext;
import parser.SVMParser.RemoveContext;
import parser.SVMParser.AddContext;
import parser.SVMParser.SubContext;
import parser.SVMParser.MultContext;
import parser.SVMParser.DivContext;
import parser.SVMParser.LoadWordContext;
import parser.SVMParser.StoreWordContext;
import parser.SVMParser.LoadFieldContext;
import parser.SVMParser.StoreFieldContext;
import parser.SVMParser.LabelContext;
import parser.SVMParser.BranchContext;
import parser.SVMParser.BranchEqualContext;
import parser.SVMParser.BranchGreaterEqualContext;
import parser.SVMParser.BranchLessEqualContext;
import parser.SVMParser.JsContext;
import parser.SVMParser.JumpDispatchTableContext;
import parser.SVMParser.LoadReturnAddressContext;
import parser.SVMParser.StoreReturnAddressContext;
import parser.SVMParser.LoadReturnValueContext;
import parser.SVMParser.StoreReturnValueContext;
import parser.SVMParser.LoadFramePointerContext;
import parser.SVMParser.StoreFramePointerContext;
import parser.SVMParser.CopyFramePointerContext;
import parser.SVMParser.LoadHeapPointerContext;
import parser.SVMParser.StoreHeapPointerContext;
import parser.SVMParser.LoadDispatchPointerContext;
import parser.SVMParser.StoreDispatchPointerContext;
import parser.SVMParser.LoadInfoObjectContext;
import parser.SVMParser.StoreInfoObjectContext;
import parser.SVMParser.LoadObjectPointerContext;
import parser.SVMParser.StoreObjectPointerContext;
import parser.SVMParser.PrintContext;
import parser.SVMParser.HaltContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SVMVisitorImpl extends SVMBaseVisitor<Void>{

    private int[] code;
    private int i=0;
    private HashMap<String, Integer> labelAdd;
    private HashMap<Integer, String> labelRef;
    private ArrayList<ArrayList<Integer>> dispatchTable;


    public SVMVisitorImpl(int[] c, HashMap<String, Integer> lA, HashMap<Integer, String> lR, ArrayList<ArrayList<Integer>> dT) {
        code=c;
        labelAdd=lA;
        labelRef=lR;
        dispatchTable=dT;
    }

    public String getLabelAddKey(int value) {
        for(String key : labelAdd.keySet()) {
            if(labelAdd.get(key) == value)
                return key;
        }

        return "";
    }

    @Override
    public Void visitAssembly(AssemblyContext ctx) {
        int cPush=0, cPop=0, cAlloc=0, cRemove=0, cAdd=0, cSub=0, cMult=0, cDiv=0, cLW=0, cSW=0, cLF=0, cSF=0, cL=0, cB=0, cBeq=0,
                cBgeq=0, cBleq=0, cJS=0, cJDT=0, cLRA=0, cSRA=0, cLRV=0, cSRV=0, cLFP=0, cSFP=0, cCopy=0,
                cLHP=0, cSHP=0, cLDP=0, cSDP=0, cLIO=0, cSIO=0, cLOP=0, cSOP=0,cP=0, cH=0;

        for (ParseTree c : ctx.children) {
            switch(c.getClass().getSimpleName()) {
                case "PushContext": {
                    visit(ctx.push(cPush++));
                    break;
                }
                case "PopContext": {
                    visit(ctx.pop(cPop++));
                    break;
                }
                case "AllocContext": {
                    visit(ctx.alloc(cAlloc++));
                    break;
                }
                case "RemoveContext": {
                    visit(ctx.remove(cRemove++));
                    break;
                }
                case "AddContext": {
                    visit(ctx.add(cAdd++));
                    break;
                }
                case "SubContext": {
                    visit(ctx.sub(cSub++));
                    break;
                }
                case "MultContext": {
                    visit(ctx.mult(cMult++));
                    break;
                }
                case "DivContext": {
                    visit(ctx.div(cDiv++));
                    break;
                }
                case "LoadWordContext": {
                    visit(ctx.loadWord(cLW++));
                    break;
                }
                case "StoreWordContext": {
                    visit(ctx.storeWord(cSW++));
                    break;
                }
                case "LoadFieldContext": {
                    visit(ctx.loadField(cLF++));
                    break;
                }
                case "StoreFieldContext": {
                    visit(ctx.storeField(cSF++));
                    break;
                }
                case "LabelContext": {
                    visit(ctx.label(cL++));
                    break;
                }
                case "BranchContext": {
                    visit(ctx.branch(cB++));
                    break;
                }
                case "BranchEqualContext": {
                    visit(ctx.branchEqual(cBeq++));
                    break;
                }
                case "BranchGreaterEqualContext": {
                    visit(ctx.branchGreaterEqual(cBgeq++));
                    break;
                }
                case "BranchLessEqualContext": {
                    visit(ctx.branchLessEqual(cBleq++));
                    break;
                }
                case "JsContext": {
                    visit(ctx.js(cJS++));
                    break;
                }
                case "JumpDispatchTableContext": {
                    visit(ctx.jumpDispatchTable(cJDT++));
                    break;
                }
                case "LoadReturnAddressContext": {
                    visit(ctx.loadReturnAddress(cLRA++));
                    break;
                }
                case "StoreReturnAddressContext": {
                    visit(ctx.storeReturnAddress(cSRA++));
                    break;
                }
                case "LoadReturnValueContext": {
                    visit(ctx.loadReturnValue(cLRV++));
                    break;
                }
                case "StoreReturnValueContext": {
                    visit(ctx.storeReturnValue(cSRV++));
                    break;
                }
                case "LoadFramePointerContext": {
                    visit(ctx.loadFramePointer(cLFP++));
                    break;
                }
                case "StoreFramePointerContext": {
                    visit(ctx.storeFramePointer(cSFP++));
                    break;
                }
                case "CopyFramePointerContext": {
                    visit(ctx.copyFramePointer(cCopy++));
                    break;
                }
                case "LoadHeapPointerContext": {
                    visit(ctx.loadHeapPointer(cLHP++));
                    break;
                }
                case "StoreHeapPointerContext": {
                    visit(ctx.storeHeapPointer(cSHP++));
                    break;
                }
                case "LoadDispatchPointerContext": {
                    visit(ctx.loadDispatchPointer(cLDP++));
                    break;
                }
                case "StoreDispatchPointerContext": {
                    visit(ctx.storeDispatchPointer(cSDP++));
                    break;
                }
                case "LoadInfoObjectContext": {
                    visit(ctx.loadInfoObject(cLIO++));
                    break;
                }
                case "StoreInfoObjectContext": {
                    visit(ctx.storeInfoObject(cSIO++));
                    break;
                }
                case "LoadObjectPointerContext": {
                    visit(ctx.loadObjectPointer(cLOP++));
                    break;
                }
                case "StoreObjectPointerContext": {
                    visit(ctx.storeObjectPointer(cSOP++));
                    break;
                }
                case "PrintContext": {
                    visit(ctx.print(cP++));
                    break;
                }
                case "HaltContext": {
                    visit(ctx.halt(cH++));
                    break;
                }
            }
        }

        //System.out.println("\nStampa di labelAdd...");
        for(Map.Entry<String, Integer> lAdd : labelAdd.entrySet()) {
            //System.out.println("Chiave: " + lAdd.getKey() + " -> Valore: " + lAdd.getValue());
            if(lAdd.getKey().contains("class"))
                dispatchTable.add(null);
        }

        //System.out.println("\nStampa di labeRef...");
        //for(Map.Entry<Integer, String> lRef : labelRef.entrySet())
        //    System.out.println("Chiave: " + lRef.getKey() + " -> Valore: " + lRef.getValue());

        //System.out.println("\nStampa di refAdd...");
        for (Integer refAdd: labelRef.keySet()) {
            code[refAdd]=labelAdd.get(labelRef.get(refAdd));
            //System.out.println("refAdd: " + refAdd + " -> code[refAdd]: " + code[refAdd]);
        }

        //arrayList d'appoggio per salvare gli indirizzi dei metodi delle classi
        ArrayList<Integer> addressesMethods;
        int i;
        int classOffset;

        System.out.println("");
        for(Integer refAdd: labelRef.keySet()) {
            if(labelRef.get(refAdd).contains("class")) {
                classOffset = code[code[refAdd]+1];
                //System.out.println("offset classe: " + classOffset);
                i = code[refAdd]+2;
                //System.out.println("Indice i: " + i);
                addressesMethods = new ArrayList<>();
                while(code[i] == SVMParser.PUSH) {
                    i++;
                    //System.out.println("Indirizzo metodo: " + code[++i]);
                    if(getLabelAddKey(code[i]).contains("method")) {
                        addressesMethods.add(code[i]);
                    }
                    i++;
                }

                //come inseriamo le classi all'offset giusto nella dispatch table?
                dispatchTable.set(classOffset, addressesMethods);
            }
        }

        /*System.out.println("\nStampa della dispatch table...");
        for(int c=0; c<dispatchTable.size(); c++) {
            System.out.println("Indirizzi dei metodi della classe a offset " + c + ":\n" + dispatchTable.get(c));
        }*/

        return null;
    }

    @Override
    public Void visitPush(PushContext ctx) {
        if(ctx.n != null) {
            code[i++] = SVMParser.PUSH;
            code[i++] = Integer.parseInt(ctx.n.getText());
        }
        else {
            code[i++] = SVMParser.PUSH;
            labelRef.put(i++, ctx.l.getText());
        }

        return null;
    }

    @Override
    public Void visitPop(PopContext ctx) {
        code[i++] = SVMParser.POP;

        return null;
    }

    @Override
    public Void visitAlloc(AllocContext ctx) {
        if(ctx.n != null) {
            code[i++] = SVMParser.ALLOC;
            code[i++] = Integer.parseInt(ctx.n.getText());
        }

        return null;
    }

    @Override
    public Void visitRemove(RemoveContext ctx) {
        code[i++] = SVMParser.REMOVE;

        return null;
    }

    @Override
    public Void visitAdd(AddContext ctx) {
        code[i++] = SVMParser.ADD;

        return null;
    }

    @Override
    public Void visitSub(SubContext ctx) {
        code[i++] = SVMParser.SUB;

        return null;
    }

    @Override
    public Void visitMult(MultContext ctx) {
        code[i++] = SVMParser.MULT;

        return null;
    }

    @Override
    public Void visitDiv(DivContext ctx) {
        code[i++] = SVMParser.DIV;

        return null;
    }

    @Override
    public Void visitLoadWord(LoadWordContext ctx) {
        code[i++] = SVMParser.LOADW;

        return null;
    }

    @Override
    public Void visitStoreWord(StoreWordContext ctx) {
        code[i++] = SVMParser.STOREW;

        return null;
    }

    @Override
    public Void visitLoadField(LoadFieldContext ctx) {
        code[i++] = SVMParser.LOADF;

        return null;
    }

    @Override
    public Void visitStoreField(StoreFieldContext ctx) {
        code[i++] = SVMParser.STOREF;

        return null;
    }

    @Override
    public Void visitLabel(LabelContext ctx) {
        labelAdd.put(ctx.l.getText(), i);

        return null;
    }

    @Override
    public Void visitBranch(BranchContext ctx) {
        code[i++] = SVMParser.BRANCH;
        labelRef.put(i++,ctx.l.getText());

        return null;
    }

    @Override
    public Void visitBranchEqual(BranchEqualContext ctx) {
        code[i++] = SVMParser.BRANCHEQ;
        labelRef.put(i++,ctx.l.getText());

        return null;
    }

    @Override
    public Void visitBranchGreaterEqual(BranchGreaterEqualContext ctx) {
        code[i++] = SVMParser.BRANCHGREATEREQ;
        labelRef.put(i++,ctx.l.getText());

        return null;
    }

    @Override
    public Void visitBranchLessEqual(BranchLessEqualContext ctx) {
        code[i++] = SVMParser.BRANCHLESSEQ;
        labelRef.put(i++,ctx.l.getText());

        return null;
    }

    @Override
    public Void visitJs(JsContext ctx) {
        code[i++] = SVMParser.JS;

        return null;
    }

    @Override
    public Void visitJumpDispatchTable(JumpDispatchTableContext ctx) {
        code[i++] = SVMParser.JDT;

        return null;
    }

    @Override
    public Void visitLoadReturnAddress(LoadReturnAddressContext ctx) {
        code[i++] = SVMParser.LOADRA;

        return null;
    }

    @Override
    public Void visitStoreReturnAddress(StoreReturnAddressContext ctx) {
        code[i++] = SVMParser.STORERA;

        return null;
    }

    @Override
    public Void visitLoadReturnValue(LoadReturnValueContext ctx) {
        code[i++] = SVMParser.LOADRV;

        return null;
    }

    @Override
    public Void visitStoreReturnValue(StoreReturnValueContext ctx) {
        code[i++] = SVMParser.STORERV;

        return null;
    }

    @Override
    public Void visitLoadFramePointer(LoadFramePointerContext ctx) {
        code[i++] = SVMParser.LOADFP;

        return null;
    }

    @Override
    public Void visitStoreFramePointer(StoreFramePointerContext ctx) {
        code[i++] = SVMParser.STOREFP;

        return null;
    }

    @Override
    public Void visitCopyFramePointer(CopyFramePointerContext ctx) {
        code[i++] = SVMParser.COPYFP;

        return null;
    }

    @Override
    public Void visitLoadHeapPointer(LoadHeapPointerContext ctx) {
        code[i++] = SVMParser.LOADHP;

        return null;
    }

    @Override
    public Void visitStoreHeapPointer(StoreHeapPointerContext ctx) {
        code[i++] = SVMParser.STOREHP;

        return null;
    }

    @Override
    public Void visitLoadDispatchPointer(LoadDispatchPointerContext ctx) {
        code[i++] = SVMParser.LOADDP;

        return null;
    }

    @Override
    public Void visitStoreDispatchPointer(StoreDispatchPointerContext ctx) {
        code[i++] = SVMParser.STOREDP;

        return null;
    }

    @Override
    public Void visitLoadInfoObject(LoadInfoObjectContext ctx) {
        code[i++] = SVMParser.LOADIO;

        return null;
    }

    @Override
    public Void visitStoreInfoObject(StoreInfoObjectContext ctx) {
        code[i++] = SVMParser.STOREIO;

        return null;
    }

    @Override
    public Void visitLoadObjectPointer(LoadObjectPointerContext ctx) {
        code[i++] = SVMParser.LOADOP;

        return null;
    }

    @Override
    public Void visitStoreObjectPointer(StoreObjectPointerContext ctx) {
        code[i++] = SVMParser.STOREOP;

        return null;
    }

    @Override
    public Void visitPrint(PrintContext ctx) {
        code[i++] = SVMParser.PRINT;

        return null;
    }

    @Override
    public Void visitHalt(HaltContext ctx) {
        code[i++] = SVMParser.HALT;

        return null;
    }
}
