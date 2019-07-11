package ast;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;

/* Nodo della regola "denied" */
public class DeniedNode implements Node{

    private Node right;

    public DeniedNode(Node r) {
        right = r;
    }

    public Node getDeniedNode() {
        return right;
    }

    public String toPrint(String s) {
        return s+"Not\n" + right.toPrint(s+"  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        //CS in right exp
        res.addAll(right.checkSemantics(env));
        return res;
    }

    @Override
    public Node typeCheck() {
        //verifico che il tipo dell'espressione da negare sia bool
        if (! FOOLlib.isSubtype(right.typeCheck(),new BoolTypeNode()) ) {
            System.out.println("Non boolean in NOT operation");
            System.exit(0);
        }
        //se il tipo è corretto restituisco bool
        return new BoolTypeNode();
    }

    @Override
    public String codeGeneration() {
        String l1 = FOOLlib.freshLabel();
        String l2 = FOOLlib.freshLabel();
        return right.codeGeneration()
                + "push 1\n"
                + "beq " + l1 + "\n"
                + "push 1\n"
                + "b " + l2 + "\n"
                + l1 + ": \n"
                + "push 0\n"
                + l2 + ": \n";
    }

}
