package ast;
import java.util.ArrayList;
import java.util.HashMap;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo per le regole ifExp e ifStm*/
public class IfNode implements Node {

    private Node cond; //condizione
    private Node thExp; //ramo then se e' una exp
    private Node elExp; //ramo else se e' una exp
    private ArrayList<Node> thStms; //ramo then se sono stms
    private ArrayList<Node> elStms; //ramo else se sono stms
    private ClassTypeNode ifreturn = null; //classe dell'oggetto di ritorno dell'if se il nodo torna un oggetto
    private ArrayList<HashMap<String, STentry>> symT = new ArrayList<>(); //symbol table
    private int nl;

    public IfNode(Node c, Node t, Node e) {
        cond=c;
        thExp=t;
        elExp=e;
    }

    public IfNode(Node c, ArrayList<Node> t, ArrayList<Node> e) {
        cond=c;
        thStms=t;
        elStms=e;
    }

    public Node getCond() { return cond; }

    public Node getThen() { return thExp; }

    public Node getElse() { return elExp; }

    public ArrayList<Node> getThStms() { return thStms; }

    public ArrayList<Node> getElStms() { return elStms; }

    public ClassTypeNode getIfreturn() { return ifreturn; }

    @Override
    public String toPrint(String s) {
        //ifExp
        if(thExp != null)
            return s+"If\n" + cond.toPrint(s+"  ")
                + thExp.toPrint(s+"  ")
                + elExp.toPrint(s+"  ");
        //ifStms
        else{
            //stms ramo then
            String stmsThStr="";
            for (Node stm : thStms)
                stmsThStr += stm.toPrint(s + " ");

            //stms ramo else
            String stmsElStr="";
            for (Node stm : elStms)
                stmsElStr += stm.toPrint(s + " ");

            return s+"If\n" + cond.toPrint(s+"  ")
                + stmsThStr + stmsElStr;
        }

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //CS condizione
        res.addAll(cond.checkSemantics(env));

        //ifExp - CS rami then ed else
        if(thExp != null) {
            res.addAll(thExp.checkSemantics(env));
            res.addAll(elExp.checkSemantics(env));
        }
        //ifStms - CS rami then ed else
        else{
            for (Node stm : thStms)
                res.addAll(stm.checkSemantics(env));
            for (Node stm : elStms)
                res.addAll(stm.checkSemantics(env));
        }

        //memorizzo il NL corrente e salvo la ST da NL 0 a NL corrente
        nl = env.getNestingLevel();
        int j = 0;
        while(j<=nl)
            symT.add(env.getSymTable().get(j++));

        return res;
    }

    @Override
    public Node typeCheck() {

        //TC della condizione: deve essere BOOL
        if (!(FOOLlib.isSubtype(cond.typeCheck(),new BoolTypeNode()))) {
            System.out.println("Non boolean condition in IF");
            System.exit(0);
        }

        //caso ifStms
        if(thStms != null){
            //controllo che tutti i tipi degli stms siano void
            for(Node stm : thStms) {
                if (!(stm.typeCheck() instanceof VoidTypeNode)){
                    System.out.println("Not stms in then branch");
                    System.exit(0);
                }
            }
            for(Node stm : elStms) {
                if (!(stm.typeCheck() instanceof VoidTypeNode)){
                    System.out.println("Not stms in else branch");
                    System.exit(0);
                }
            }
            return new VoidTypeNode();
        }

        //ottengo il tipo dei due rami
        Node t = thExp.typeCheck();
        Node e = elExp.typeCheck();

        //se entrambi i rami hanno tipo void allora sono stms e ritorno void
        if (t instanceof VoidTypeNode && e instanceof VoidTypeNode )
            return new VoidTypeNode();

        //caso in cui i due rami sono entrambi oggetti: verifico che le loro classi siano compatibili
        else if ((t instanceof ObjectTypeNode) && (e instanceof ObjectTypeNode)){

            //ricavo le classi dei due oggetti dei rami
            ClassTypeNode thenClass = FOOLlib.obtainNodeClass(symT,nl,thExp);
            ClassTypeNode elseClass = FOOLlib.obtainNodeClass(symT,nl,elExp);

            //caso in cui l'oggetto del then sia <: oggetto dell'else -> restituisco la superclasse
            if(FOOLlib.isSubclass(thenClass, elseClass, symT.get(0))){
                ifreturn = elseClass;
                //return ifreturn;
                return new ObjectTypeNode(elseClass.getClassID());
            }
            //caso in cui l'oggetto dell'else sia <: oggetto del then -> restituisco la superclasse
            else if (FOOLlib.isSubclass(elseClass, thenClass,symT.get(0))) {
                ifreturn = thenClass;
                //return ifreturn;
                return new ObjectTypeNode(thenClass.getClassID());
            }
            //caso in cui non valgono ne' thenClass <: elseClass ne' elseClass <: thenClass
            else {
                //cerco se thenClass e elseClass hanno una superclasse in comune
                ArrayList<ClassTypeNode> thenSuperclasses = thenClass.getClassesExtended();
                ArrayList<ClassTypeNode> elseSuperclasses = thenClass.getClassesExtended();
                for (int i = 0; i < thenSuperclasses.size(); i++) {
                    for (int j = 0; j < elseSuperclasses.size(); j++){
                        if(thenSuperclasses.get(i).getClassID().equals(elseSuperclasses.get(i).getClassID())){
                            ifreturn = thenSuperclasses.get(i);
                            return new ObjectTypeNode(ifreturn.getClassID());
                        }
                    }
                }
            }
        }

        //caso in cui i due rami non sono entrambi oggetti o entrambi stm
        else {
            //se  vale t<:e allora restituisco e
            if (FOOLlib.isSubtype(t, e))
                return e;
            //se vale e<:t restituisco t
            if (FOOLlib.isSubtype(e, t))
                return t;
        }
        //se i tipi dei due rami sono incompatibili restituisco errore (null)
        System.out.println("Incompatible types in then else branches");
        System.exit(0);
        return null;
    }

    @Override
    public String codeGeneration() {
        //freshLabel per i rami
        String l1 = FOOLlib.freshLabel();
        String l2 = FOOLlib.freshLabel();

        //caso ifExp
        if(thExp != null)
            return cond.codeGeneration()+
                "push 1\n"+
                "beq "+ l1 +"\n"+
                elExp.codeGeneration()+
                "b " + l2 + "\n" +
                l1 + ":\n"+
                thExp.codeGeneration()+
                l2 + ":\n";

        //caso ifStms
        else{
            String stmsThCode="";
            for (Node stm : thStms)
                stmsThCode += stm.codeGeneration();
            String stmsElCode="";
            for (Node stm : elStms)
                stmsElCode += stm.codeGeneration();
            return cond.codeGeneration()+
                "push 1\n"+
                "beq "+ l1 +"\n"+
                stmsElCode+
                "b " + l2 + "\n" +
                l1 + ":\n"+
                stmsThCode+
                l2 + ":\n";
        }
    }

}  