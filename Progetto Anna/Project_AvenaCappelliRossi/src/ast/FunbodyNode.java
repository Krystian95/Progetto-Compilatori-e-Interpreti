package ast;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;

/* Nodo che gestise la regola funbody */
public class FunbodyNode implements Node {

    private ArrayList<Node> declist = new ArrayList<>(); //lista di dichiarazioni
    private Node body; //exp
    private ArrayList<Node> stms;

    public FunbodyNode(ArrayList<Node> vl, Node e){ declist =vl; body=e;}

    public FunbodyNode(Node e){
        body=e;
    }

    public FunbodyNode(ArrayList<Node> vl, ArrayList<Node> s){ declist =vl; stms=s; }

    public FunbodyNode(ArrayList<Node> s){
        stms=s;
    }

    public ArrayList<Node> getDeclist () { return declist; }

    public Node getBody() { return body; }

    public ArrayList<Node> getStms() { return stms; }

    @Override
    public String toPrint(String s){
        //se ci sono delle variabili eseguo il loro toPrint
        String varasmstr="";
        if(declist != null) {
            for (Node vs : declist)
                varasmstr += vs.toPrint(s + "");
        }

        //caso in cui il body e' un exp
        if(body != null)
            return s + "Funbody\n" + varasmstr + body.toPrint(s+"");
        //caso in cui il body e' composto da stms
        else{
            String stmsStr="";
            for (Node stm : stms)
                stmsStr += stm.toPrint(s + " ");
            return s + "Funbody\n" + varasmstr + stmsStr;
        }

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> res = new ArrayList<>();

        //CS delle dichiarazioni (se ce ne sono)
        if(declist != null) {
            for (Node n : declist) {
                res.addAll(n.checkSemantics(env));
            }
        }

        //CS del corpo a seconda che sia una exp o una serie di stms
        if(body != null)
            res.addAll(body.checkSemantics(env));
        else
            for(Node stm : stms)
                res.addAll(stm.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck(){
        //se ci sono delle dichiarazioni eseguo il TC su ognuna di esse
        if (declist.size() > 0){
            for(Node dec: declist){
                dec.typeCheck();
            }
        }
        //TC del corpo della funzione a seconda che sia una exp o una serie di stms
        if(body != null)
            return body.typeCheck();
        else {
            for(Node s: stms)
                if(!(s.typeCheck() instanceof VoidTypeNode)){
                    System.out.println("not voidType in statement");
                    System.exit(0);
                }
            return new VoidTypeNode();
        }
    }

    @Override
    public String codeGeneration(){
        //codeGen delle dichiarazioni
        String declCode="";
        for (Node dec:declist)
            declCode+=dec.codeGeneration();

        //codeGen del corpo della funzione a seconda che sia una exp o una serie di stms
        if(body != null)
            return declCode + body.codeGeneration();
        else{
            String stmsCode="";
            for (Node stm : stms)
                stmsCode += stm.codeGeneration();
            return declCode + stmsCode;
        }
    }

}
