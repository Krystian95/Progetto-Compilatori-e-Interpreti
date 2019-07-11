package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo che gestisce le funzioni di libreria quindi
* la regola di stm "STDLIB LPAR exp RPAR"*/
public class PrintNode implements Node {

    private Node val;

    public PrintNode(Node v) { val=v; }

    public Node getNode() { return val; }

    @Override
    public String toPrint(String s) {
        return s+"Print\n" + val.toPrint(s+"  ") ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return val.checkSemantics(env);
    }

    @Override
    public Node typeCheck() {
        Node valType = val.typeCheck();
        if (valType instanceof ObjectTypeNode || valType instanceof VoidTypeNode || valType instanceof NullNode) {
            System.out.println("Invalid type of print statement");
            System.exit(0);
            return null;
        }
        else {
            return new VoidTypeNode();
        }
    }

    public String codeGeneration() {
        return val.codeGeneration() + "print\n";
    }

}