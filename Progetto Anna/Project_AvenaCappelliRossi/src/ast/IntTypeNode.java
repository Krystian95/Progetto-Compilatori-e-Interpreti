package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo che definisce il tipo int*/
public class IntTypeNode implements Node {

    public IntTypeNode() { }

    public String toPrint(String s) {
        return s+"IntType\n";
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