package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo che definisce il tipo bool*/
public class BoolTypeNode implements Node {

    public BoolTypeNode() { }

    public String toPrint(String s) {
        return s+"BoolType\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return "";
    }

}  