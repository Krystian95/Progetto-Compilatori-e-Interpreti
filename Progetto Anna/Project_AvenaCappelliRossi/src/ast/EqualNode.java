package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class EqualNode implements Node {

    private Node left;
    private Node right;
    private String operator;

    public EqualNode(Node l, String o, Node r) {
        left=l;
        operator=o;
        right=r;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toPrint(String s) {
        if(operator.equals("=="))
            return s + "Equal\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
        else if(operator.equals("<="))
            return s + "Less Equal\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
        else
            return s + "Greater Equal\n" + left.toPrint(s + "  ")
                    + operator + "\n"
                    + right.toPrint(s + "  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //CS in left exp e in right exp
        res.addAll(left.checkSemantics(env));
        res.addAll(right.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {
        //memorizzo i tipi delle due espressioni
        Node l = left.typeCheck();
        Node r = right.typeCheck();

        //verifco che i due operandi non siano oggetti se l'operatore e' <= o >=
        if((l instanceof ObjectTypeNode) || (r instanceof ObjectTypeNode)) {
            if(operator.equals("<=")) {
                System.out.println("Incompatible types in less equal");
                System.exit(0);
            }
            else if(operator.equals(">=")){
                System.out.println("Incompatible types in grater equal");
                System.exit(0);
            }
        }

        //verifico che i tipi siano uguali o che uno sia sottotipo dell'altro
        if (! ( FOOLlib.isSubtype(l,r) || FOOLlib.isSubtype(r,l) ) ) {
            if(operator.equals("==")) {
                System.out.println("Incompatible types in equal");
            }
            else if(operator.equals("<=")) {
                System.out.println("Incompatible types in less equal");
            }
            else {
                System.out.println("Incompatible types in grater equal");
            }
            System.exit(0);
        }
        //se i tipi sono corretti restituisco bool
        return new BoolTypeNode();
    }

    @Override
    public String codeGeneration() {
        String l1=FOOLlib.freshLabel();
        String l2=FOOLlib.freshLabel();
        String symbol;

        if(operator.equals(">="))
            symbol = "bgeq " + l1 + "\n";
        else if(operator.equals("<="))
            symbol = "bleq " + l1 + "\n";
        else
            symbol = "beq " + l1 + "\n";

        return left.codeGeneration()
                + right.codeGeneration()
                + symbol
                + "push 0\n"
                + "b " + l2 + "\n"
                + l1 + ":\n"
                + "push 1\n"
                + l2 + ":\n";
    }

}  