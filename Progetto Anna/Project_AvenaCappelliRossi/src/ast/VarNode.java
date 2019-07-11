package ast;
import java.util.ArrayList;
import java.util.HashMap;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo che gestice la dichiarazione di variabili (varasm) */
public class VarNode implements Node {

    private Node type;
    private String id;
    private Node exp;

    public VarNode (String i, Node t, Node v) {
        id=i;
        type=t;
        exp=v;
    }

    public String getId() { return id; }

    public Node getType() { return type; }

    public Node getExp() { return exp; }

    @Override
    public String toPrint(String s) {
        return s+"Var:" + id + "\n" + type.toPrint(s+"  ") + exp.toPrint(s+"  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //Recupero la ST del NL più esterno
        HashMap<String,STentry> hm = env.getSymTable().get(env.getNestingLevel());
        //creo un'entry per la variabile
        STentry entry = new STentry(env.getNestingLevel(),type, env.getOffset());

        //se è gia presente una variabile con lo stesso nome segnalo errore
        if ( hm.containsKey(id)) {
            res.add(new SemanticError("Variable id " + id + " already declared"));
        }
        //se non è presente inserisco l'entry nella ST e decremento l'offset
        else {
            env.decrementOffset();
            hm.put(id,entry);
        }

        //CS dell'exp
        res.addAll(exp.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {
        //se la dichiarazione è "T ID = EXP" verifico: "tipo di exp" <: T
        if (!(FOOLlib.isSubtype(exp.typeCheck(), type))) {
            System.out.println("Incompatible value for variable " + id);
            System.exit(0);
        }
        //restituisco void
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        return exp.codeGeneration();
    }

}  