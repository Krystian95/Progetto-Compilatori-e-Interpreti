package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo della regola di value "(TRUE | FALSE)"*/
public class BoolNode implements Node {

    private boolean val;

    public BoolNode(boolean n) {
        val=n;
    }

    public boolean getVal() { return val; }

    @Override
    public String toPrint(String s) {
        if (val)
            return s+"Bool:true\n";
        else
            return s+"Bool:false\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        //ritorna tipo "bool"
        return new BoolTypeNode();
    }

    public String codeGeneration() {
        return "push " + (val?1:0) + "\n";
    }

}  