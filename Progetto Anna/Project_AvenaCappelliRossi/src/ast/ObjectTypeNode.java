package ast;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;

/* Nodo che rappresenta il tipo oggetto */
public class ObjectTypeNode implements Node {

    private String className;

    public ObjectTypeNode(String cl) {
        className = cl;
    }

    public String getClassName(){
        return className;
    }

    @Override
    public String toPrint(String s) {
        return s + "ClassType: " + className + "\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        return new ObjectTypeNode(className);
    }

    @Override
    public String codeGeneration() {
        return "";
    }

}