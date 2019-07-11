package ast;
import java.util.ArrayList;
import java.util.HashMap;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

/* Nodo che gestisce la regola di prog "LET decs IN (exp | stms)" */
public class ProgLetInNode implements Node {

    private ArrayList<Node> declist; //dichiarazioni di variabili o funzioni
    private Node exp; //exp
    private ArrayList<Node> stms; //stms

    public ProgLetInNode(ArrayList<Node> d, Node e) {
        declist=d;
        exp=e;
        stms = null;
    }

    public ProgLetInNode(ArrayList<Node> d, ArrayList<Node> s) {
        declist=d;
        exp=null;
        stms=s;
    }

    public ArrayList<Node> getDecList() { return declist; }

    public Node getExp(){ return exp; }

    @Override
    public String toPrint(String s) {
        String declstr="";
        String stmsStr="";
        for (Node dec : declist)
            declstr += dec.toPrint(s + "  ");
        if(exp != null)
            return s+"ProgLetIn\n" + declstr + exp.toPrint(s+"  ") ;
        else{
            for (Node stm : stms)
                stmsStr += stm.toPrint(s + " ");
            return s + "ProgLetIn\n" + declstr + stmsStr;
        }
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //recupero la ST del NL più esterno
        HashMap<String, STentry> hm = env.getSymTable().get(env.getNestingLevel());

        //prima passata funzioni: creo un'entry per ogni funzione dichiarata senza fare il CS del corpo
        env.setCheckStep(1);

        //analizzo tutte le dichiarazioni
        for(Node d : declist) {
            //caso in cui la dichiarazione è una dichiarazione di funzione
            if(d instanceof FunNode) {
                String funName = ((FunNode) d).getId();
                //creo l'entry della funzione
                STentry entryFuns = new STentry(env.getNestingLevel(), env.getOffset());
                //controllo che non sia già stata dichiarata una funzione con lo stesso nome
                if (hm.containsKey(funName))
                    res.add(new SemanticError("Fun id " + funName + " already declared"));
                //inserisco l'entry nella ST, decremento l'offset, eseguo il CS della dichiarazione:
                //alla prima passata il CS si occupa di inserire tipo parametri e tipo funzione nella entry della funzione
                else {
                    hm.put(funName, entryFuns);
                    env.decrementOffset();
                    res.addAll(d.checkSemantics(env));
                }

                //seconda passata funzioni: eseguo il CS dei parametri e del body della funzione
                env.setCheckStep(2);
                res.addAll(d.checkSemantics(env));
                env.setCheckStep(1);

            }
            //caso in cui non sto dichiarando una funzione
            else {
                res.addAll(d.checkSemantics(env));
            }
        }

        if(exp != null) {
            res.addAll(exp.checkSemantics(env));
        }
        else{
            for(Node stm : stms)
                res.addAll(stm.checkSemantics(env));
        }
        return res;
    }

    @Override
    public Node typeCheck() {
        //per ogni dichiarazione di varaibile eseguo il TC
        for (Node dec: declist)
            dec.typeCheck();
        //TC dell'espressione o dello statement
        if(exp != null)
            return exp.typeCheck();
        else{
            for(Node stm : stms) {
                //verifico che ogni stm sia di tipo void
                if(!(stm.typeCheck() instanceof VoidTypeNode)){
                    System.out.println("not voidType in statement");
                    System.exit(0);
                }
            }
            return new VoidTypeNode();
        }
    }

    @Override
    public String codeGeneration() {
        String declCode = "";
        for (Node dec:declist)
            declCode += dec.codeGeneration();

        if(exp != null)
            return  "cfp\n"
                + "push 0\n"
                + declCode
                + exp.codeGeneration();
        else{
            String stmsCode="";
            for (Node stm : stms)
                stmsCode += stm.codeGeneration();

            return "cfp\n"
                + "push 0\n"
                + declCode
                + stmsCode;
        }
    }

}