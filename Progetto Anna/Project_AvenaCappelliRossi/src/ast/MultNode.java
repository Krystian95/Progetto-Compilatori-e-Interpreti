package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo che gestisce la regola term */
public class MultNode implements Node {

    private Node left;
    private Node right;
    private String operator;

    public MultNode(Node l, String o, Node r) {
        left=l;
        operator=o;
        right=r;
    }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public String getOperator() { return operator; }

    public String toPrint(String s) {
        if(operator.equals("*"))
            return s + "Mult\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");

        else
            return s + "Div\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //CS di right e left exp
        res.addAll(left.checkSemantics(env));
        res.addAll(right.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {
        //verifico che i tipi delle espressioni siano int o sottitipi di int
        if (! ( FOOLlib.isSubtype(left.typeCheck(),new IntTypeNode()) &&
                FOOLlib.isSubtype(right.typeCheck(),new IntTypeNode()) ) ) {
            if(operator.equals("*")) {
                System.out.println("Non integers in multiplication");
            }
            else {
                System.out.println("Non integers in division");
            }
            System.exit(0);
        }
        //se i tipi sono corretti restituisco int
        return new IntTypeNode();
    }

    @Override
    public String codeGeneration() {
        String symbol;
        if (operator.equals("*"))
            symbol = "mult\n";
        else
            symbol = "div\n";

        return left.codeGeneration()
                + right.codeGeneration()
                + symbol;

    }

}