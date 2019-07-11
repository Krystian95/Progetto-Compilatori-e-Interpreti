package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* nodo che definisce l'entry di una funzione */
public class ArrowTypeNode implements Node {

    private String classID; //nome della funzione
    private ArrayList<Node> parlist; //elenco dei parametri
    private Node ret; //valore di ritorno della funzione

    public ArrowTypeNode(ArrayList<Node> p, Node r) {
        parlist=p;
        ret=r;
        classID="";
    }

    public ArrowTypeNode(ArrayList<Node> p, Node r, String id) {
        parlist=p;
        ret=r;
        classID=id;
    }

    public String getClassID() { return classID; }

    public ArrayList<Node> getParList() { return parlist; }

    public Node getReturn() { return ret; }

    @Override
    public String toPrint(String s) {
        //faccio il toPrint di tutti i parametri
        String parlstr = "";
        if(parlist != null)
            for (Node par : parlist)
                parlstr += par.toPrint(s + "  ");
        //Stampo il tipo ArrowType: Tpar1, Tpar2, ... -> Tfun
        return s + "ArrowType\n" + parlstr + ret.toPrint(s + "  ->");
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