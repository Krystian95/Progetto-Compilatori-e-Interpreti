package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo che gestisce la regola singleExp di prog*/
public class ProgNode implements Node {

    private Node exp;
    private ArrayList<Node> stms;

    public ProgNode(Node e) { exp=e; stms = null;}

    public ProgNode(ArrayList<Node> s) { stms=s; exp=null; }

    public Node getProg(){ return exp; }

    @Override
    public String toPrint(String s) {

        if(exp != null)
            return "Prog\n" + exp.toPrint("  ") ;
        else{
            String stmsStr="";
            for (Node stm : stms)
                stmsStr += stm.toPrint(s + " ");
            return s + "Prog\n" + stmsStr;
        }
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if(exp != null) {
            res.addAll(exp.checkSemantics(env));
        }
        else{
            for(Node stm : stms)
                res.addAll(stm.checkSemantics(env));
        }
        return res;
    }

    @Override
    public Node typeCheck() {
        //TC dell'espressione o dello statement
        if(exp != null) {
            return exp.typeCheck();
        }
        else{
            for(Node stm : stms)
                if(!(stm.typeCheck() instanceof VoidTypeNode)){
                    System.out.println("not voidType in statement");
                    System.exit(0);
                }
            return new VoidTypeNode();
        }
    }

    public String codeGeneration() {
        if(exp != null)
            return exp.codeGeneration();
        else {
            String stmsCode = "";
            for (Node stm : stms)
                stmsCode += stm.codeGeneration();
            return stmsCode;
        }
    }

}  