package ast;
import java.util.ArrayList;
import java.util.HashMap;
import util.Environment;
import util.SemanticError;

/* Nodo per la gestione dei parametri di funzioni e metodi*/
public class ParNode implements Node {

    private String id;
    private Node type;

    public ParNode(String i, Node t) {
        id=i;
        type=t;
    }

    public String getId(){ return id; }

    public Node getType(){ return type; }

    @Override
    public String toPrint(String s) {
        return s+"Par: " + id +"\n"
                +type.toPrint(s+"  ") ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //mi salvo la ST dell'ultimo NL
        HashMap<String,STentry> hm = env.getSymTable().get(env.getNestingLevel());

        //se il nome è già stato usato nel NL corrente diamo errore
        if (hm.containsKey(id))
            res.add(new SemanticError("Parameter id " + id + " already declared"));
        //se il nome è free, inserisco l'entry nella ST e incremento l'offset
        else {
            hm.put(id, new STentry(env.getNestingLevel(), type, env.getParOffset()));
            //if (type instanceof ObjectTypeNode)
            //    env.incrementParOffset();
            env.incrementParOffset();
        }

        return res;
    }

    @Override
    public Node typeCheck() { return new VoidTypeNode(); }

    @Override
    public String codeGeneration() {
        return "";
    }

}  