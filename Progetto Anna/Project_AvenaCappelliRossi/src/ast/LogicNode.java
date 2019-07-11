package ast;
import java.util.ArrayList;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

/* Nodo della regola exp*/
public class LogicNode implements Node {

    private Node left;
    private Node right;
    private String operator;

    public LogicNode(Node l, String o, Node r) {
        left=l;
        operator=o;
        right=r;
    }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public String getOperator() { return operator; }

    @Override
    public String toPrint(String s) {
        if(operator.equals("and") || operator.equals("&&"))
            return s + "And\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
        else
            return s + "Or\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        //CS in left e right exp
        res.addAll(left.checkSemantics(env));
        res.addAll(right.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {
        //verifico che i tipi delle due espressioni siano booleani
        if (!(FOOLlib.isSubtype(left.typeCheck(), new BoolTypeNode()) &&
                FOOLlib.isSubtype(right.typeCheck(), new BoolTypeNode()))) {
            if(operator.equals("and") || operator.equals("&&")) {
                System.out.println("Non boolean in AND");
            }
            else {
                System.out.println("Non boolean in OR");
            }
            System.exit(0);
        }
        //se i tipi sono corretti restituisco bool
        return new BoolTypeNode();
    }

    @Override
    public String codeGeneration() {
        String l1 = FOOLlib.freshLabel();
        String l2 = FOOLlib.freshLabel();
        String res;
        if (operator.equals("and") || operator.equals("&&")) {
            String l3 = FOOLlib.freshLabel();
            res = left.codeGeneration()
                    + "push 1\n"
                    + "beq " + l1 + "\n"
                    + "push 0\n"
                    + "b " + l2 + "\n"
                    + l1 + ": \n"
                    + right.codeGeneration()
                    + "push 1\n"
                    + "beq " + l3 + "\n"
                    + "push 0\n"
                    + "b " + l2 + "\n"
                    + l3 + ": \n"
                    + "push 1\n"
                    + l2 + ": \n";
        } else {
            res =  left.codeGeneration()
                    + "push 1\n"
                    + "beq " + l1 + "\n"
                    + right.codeGeneration()
                    + "push 1\n"
                    + "beq " + l1 + "\n"
                    + "push 0\n"
                    + "b " + l2 + "\n"
                    + l1 + ": \n"
                    + "push 1\n"
                    + l2 + ": \n";
        }

        return res;
    }

}

