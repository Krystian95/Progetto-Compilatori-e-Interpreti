package ast;
import java.util.ArrayList;
import java.util.HashMap;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo che gestisce la dichiarazione di un nuovo oggetto */
public class ObjectNode implements Node {

    private Node type; //classe dell'oggetto (parte sx)
    private String id; //nome dell'oggetto
    private Node exp; //costruttore (parte dx)
    private int objectOffset; //offset dell'oggetto

    private HashMap<String, STentry> entryClassExp;

    public ObjectNode (String i, Node t, Node v) {
        id=i;
        type=t;
        exp=v;
    }

    public String getId() {
        return id;
    }

    public Node getType() {
        return type;
    }

    public Node getExp() {
        return exp;
    }

    @Override
    public String toPrint(String s) {
        //stampo il nome dell'oggetto, il suo tipo (classe) e le informazioni relative alla classe
        return s+"Obj: " + id +"\n"   +type.toPrint(s+"  ") + exp.toPrint(s+"  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //Recupero la ST del NL più esterno
        HashMap<String,STentry> hm = env.getSymTable().get(env.getNestingLevel());

        //creo un'entry per l'oggetto
        STentry entry = new STentry(env.getNestingLevel(),type, env.getObjectNumber());
        objectOffset = env.getObjectNumber();

        //System.out.println("Offset dell'oggetto " + id + ": " + env.getObjectNumber());

        //se è gia presente una variabile con lo stesso nome segnalo errore
        if ((hm.containsKey(id))) {
            res.add(new SemanticError("Variable id " + id + " already declared"));
        }
        else {
            hm.put(id,entry);
            entryClassExp = env.getSymTable().get(0);
            env.incrementObjectNumber();
        }

        //CS dell'exp
        res.addAll(exp.checkSemantics(env));

        return res;
    }

    @Override
    public Node typeCheck() {

        //variabili che memorizzano la classe dell'oggetto (parte sx) e quella del costruttore (parte dx)
        ClassTypeNode objClass = null;
        ClassTypeNode expClass = null;

        //salvo il nome della classe di cui voglio creare un'istanza
        ObjectTypeNode t = (ObjectTypeNode) type;
        String classT = t.getClassName();
        //verifico che la classe sia dichiarata nella ST
        //Prima cerco il suo id nella ST
        if(entryClassExp.containsKey(classT)) {
            //mi salvo il tipo dell'id e verifico che sia l'id di una classe
            Node tmp = entryClassExp.get(classT).getType();
            if(tmp instanceof ClassTypeNode) {
                objClass = (ClassTypeNode) entryClassExp.get(classT).getType();
            }
            //se non e' l'id di una classe segnalo errore
            else{
                System.out.println("Wrong type of variable " + id );
                System.exit(0);
            }
        }
        //se la classe non e' dichiarata segnalo errore
        else {
            System.out.println("Wrong type of variable " + id );
            System.exit(0);
        }

        //ricavo il tipo di exp (parte dx)
        Node typeE = exp.typeCheck();
        //se exp non è nè un oggeto nè un NullNode segnalo errore
        if(!(typeE instanceof ObjectTypeNode) && !(typeE instanceof NullNode)) {
            System.out.println("Incompatible value for object " + id );
            System.exit(0);
        }
        //se exp è un NullNode il TC è corretto e restituisco void
        else if(typeE instanceof NullNode){
            return new VoidTypeNode();
        }
        //se exp e' un oggeto
        else {
            //memorizzo il nome della classe dell'oggetto
            ObjectTypeNode e = (ObjectTypeNode) typeE;
            String classE = e.getClassName();
            //se la classe esiste la memorizzo in expClass (ClassTypeNode)
            if(entryClassExp.containsKey(classE))
                expClass = (ClassTypeNode) entryClassExp.get(classE).getType();
            //se la classe non esiste segnalo errore
            else {
                System.out.println("Incompatible value for object " + id);
                System.exit(0);
            }
        }

        //caso in cui objClass e expClass siano entrambi classi
        if (!(FOOLlib.isSubclass(expClass, objClass, entryClassExp))) {
            System.out.println("Incompatible value for object " + id);
            System.exit(0);
        }

        //ritorno void
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        if(exp instanceof IdNode || exp instanceof MethodcallNode) {
            return "push " + objectOffset + "\n"
                    + exp.codeGeneration()
                    + "sdp\n" ;
        }
        else {
            return "push " + objectOffset + "\n"
                    + exp.codeGeneration();
        }
    }

}