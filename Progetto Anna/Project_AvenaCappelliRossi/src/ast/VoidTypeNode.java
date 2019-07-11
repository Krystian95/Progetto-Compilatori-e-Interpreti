package ast;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;

/* Nodo che definisce il tipo void*/
public class VoidTypeNode implements Node {

    @Override
    public String toPrint(String s) {
        return s+"VoidType\n";
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
