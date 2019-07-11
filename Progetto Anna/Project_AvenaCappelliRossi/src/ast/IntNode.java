package ast;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.TerminalNode;
import util.Environment;
import util.SemanticError;

/* Nodo della regola di value "(MINUS)? INTEGER" */
public class IntNode implements Node {

    private Integer val; //valore dell'intero
    private TerminalNode sign; //segno "-"

    public IntNode(Integer n) {
        val=n;
        sign=null;
    }

    public IntNode(Integer n, TerminalNode s) {
        val=n;
        sign=s;
    }

    public Integer getInt() { return val; }

    public TerminalNode getSign() { return sign; }

    @Override
    public String toPrint(String s) {
        if (sign==null)
            return s+"Int:" + Integer.toString(val) +"\n";
        else
            return s+"Neg:" + sign + Integer.toString(val) + "\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        //ritorma tipo "int"
        return new IntTypeNode();
    }

    public String codeGeneration() {
        if(sign == null)
            return "push " + val + "\n";
        else
            return "push " + -val + "\n";
    }

}  