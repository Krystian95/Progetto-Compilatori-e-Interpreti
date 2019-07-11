package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo che gestisce la regola arithmetic*/
public class PlusNode implements Node {

    private Node left;
    private Node right;
    private String operator;

    public PlusNode(Node l, String o, Node r) {
        left=l;
        operator=o;
        right=r;
    }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public String getOperator() { return operator; }

    @Override
    public String toPrint(String s) {
        if(operator.equals("+"))
            return s + "Plus\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
        else
            return s + "Minus\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //CS di left e right exp
        res.addAll(left.checkSemantics(env));
        res.addAll(right.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {
        //verifico che i tipi delle espressioni siano int o sottotipi di int
        if (!(FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
                FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()))) {
            if(operator.equals("+")) {
                System.out.println("Non integers in sum");
            }
            else {
                System.out.println("Non integers in sub");
            }
            System.exit(0);
        }
        //se i tipi sono corretti restituisco int
        return new IntTypeNode();

    }

    @Override
    public String codeGeneration() {
        String symbol;
        if (operator.equals("+"))
            symbol = "add\n";
        else
            symbol = "sub\n";

        return left.codeGeneration()
                + right.codeGeneration()
                + symbol;
    }

}  