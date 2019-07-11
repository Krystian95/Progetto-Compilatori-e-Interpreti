package ast;
import java.util.ArrayList;
import java.util.HashMap;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

/* Nodo che gestisce la regola fun*/
public class FunNode implements Node {

    private String id; //nome della funzione
    private Node type; //tipo della funzione
    private ArrayList<Node> parlist = new ArrayList<Node>(); //parametri della funzione
    private Node funbody; //corpo della funzione
    private String classID; //nome della classe nella quale è definito il metodo (se si sta definendo un metodo)
    private HashMap<String, STentry> entryClassExp; //memorizzo le entry che sono definite a NL 0
    private String funLabel=""; //label del metodo attuale

    public FunNode(String i, Node t, String cl) {
        id=i;
        type=t;
        classID=cl;
    }

    public FunNode(String i, Node t) {
        id=i;
        type=t;
        classID="";
    }

    public String getId() { return id; }

    public Node getType() { return type; }

    public ArrayList<Node> getParlist() { return parlist; }

    public Node getFunbody() { return funbody; }

    public String getClassID() {return classID; }

    public HashMap<String, STentry> getEntryClassExp() { return entryClassExp; }

    public void addPar(Node p) { parlist.add(p); }

    public void addFunbody(Node f) { funbody=f; }

    public void setFunLabel(String s) { funLabel=s; }

    @Override
    public String toPrint(String s) {
        //stampo il nome della funzione, il suo tipo, i parametri e i relativi tipi (se li ha) ed il corpo della funzione
        String parlstr="";
        if(parlist.size()>0)
            for (Node par : parlist)
                parlstr += par.toPrint(s + "  ");
        return s+"Fun: " + id +"\n"
            +type.toPrint(s+"  ")
            +parlstr + funbody.toPrint(s+"");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //ricavo la ST del NL più esterno e la ST a NL=0
        HashMap<String,STentry> hm = env.getSymTable().get(env.getNestingLevel());
        entryClassExp = env.getSymTable().get(0);
        //prendo l'entry della funzione (inserita nella ST in progLetInNode)
        STentry entry = hm.get(id);

        //PRIMA PASSATA FUNZIONI: inserire tipo parametri e tipo funzione nella entry della funzione
        if(env.getCheckStep() == 1) {
            //creo un arraylist dove salvare i tipi dei parametri
            ArrayList<Node> parTypes = new ArrayList<Node>();
            //per ogni parametro salvo il suo tipo in parTypes
            if(parlist.size()>0)
                for (Node p : parlist) {
                    ParNode arg = (ParNode) p;
                    parTypes.add(arg.getType());
                }
            //inserisco nell'entry della funzione i tipi dei parametri, il tipo della funzione e, nel caso si stia
            //definendo un metodo, inseriamo anche il nome della calsse nella quale il metodo è definito
            if(classID != "")
                entry.addType(new ArrowTypeNode(parTypes, type, classID));
            else
                entry.addType(new ArrowTypeNode(parTypes, type));
        }

        //SECONDA PASSATA FUNZIONI: CS di parametri e body
        else {
            //incremento il NL e creo una ST per il nuovo NL
            env.incrementNestingLevel();
            HashMap<String, STentry> hmp = new HashMap<String, STentry>();
            env.getSymTable().add(hmp);
            env.setOffset(-2);

            //CS dei parametri della funzione
            if(parlist.size()>0) {
                //inizializzo l'offset dei parametri
                env.setParOffset(1);
                for (Node p : parlist)
                    res.addAll(p.checkSemantics(env));
            }
            //CS body
            res.addAll(funbody.checkSemantics(env));

            //memorizzo in "offset" l'offset dell'entry della funzione di cui ho terminato il CS così posso ripristinare
            //l'offset (che potrebbe essere variato nel CS del body) per le prossime dichiarazioni
            int offset = entry.getOffset()-1;
            env.setOffset(offset);

            //posso rimuovere la ST del NL del corpo della funzione e ripristinare l'offset
            env.getSymTable().remove(env.getNestingLevel());
            env.decrementNestingLevel();
        }

        return res;
    }

    @Override
    public Node typeCheck() {

        //variabili che memorizzano la classe in cui è definita la funzione e, se nel corpo della funzione
        //è stato istanziato un oggetto, memorizzo la classe dell'oggetto
        ClassTypeNode funType = null; //sx
        ClassTypeNode funbodyType = null; //dx

        //se ci sono dei parametri, eseguo il TC per ogni parametro
        if (parlist.size() > 0) {
            for (Node dec : parlist)
                dec.typeCheck();
        }

        //verifico il tipo della funzione, se questo è un ObjectTypeNode memorizzo la classe di tale oggetto
        if (type instanceof ObjectTypeNode) {
            ObjectTypeNode f = (ObjectTypeNode) type;
            String classF = f.getClassName();
            if(entryClassExp.containsKey(classF))
                funType = (ClassTypeNode) entryClassExp.get(classF).getType();
                //altrimenti restituisco null
            else {
                System.out.println("Wrong return type for function " + id + ": " + classF + " not exists");
                System.exit(0);
            }
        }

        //verifico se il tipo del corpo della funzione e' un oggetto: se si, memorizzo la classe
        //di tale oggetto
        Node bodyT = funbody.typeCheck();
        if (bodyT instanceof ObjectTypeNode){
            ObjectTypeNode t = (ObjectTypeNode) bodyT;
            String classbodyF = t.getClassName();
            if(entryClassExp.containsKey(classbodyF))
                funbodyType = (ClassTypeNode) entryClassExp.get(classbodyF).getType();
                //altrimenti restituisco null
            else {
                System.out.println("Wrong return type in function " + id + ": " + classbodyF + " not exists");
                System.exit(0);
            }
        }

        //caso in cui la funzione restituisce un oggetto e il suo tipo e' un oggetto
        if (funbodyType != null && funType != null) {
            //controllo che la classe del corpo della funzione sia <: della classe della funzione
            if (!FOOLlib.isSubclass(funbodyType, funType, entryClassExp)) {
                System.out.println("Incompatible type of function " + id);
                System.exit(0);
            }
        }

        //verifico che il tipo del corpo della funzione sia <: type
        else if ( !(FOOLlib.isSubtype(bodyT, type)) ){
            System.out.println("Wrong return type for function " + id);
            System.exit(0);
        }
        //ritorno void
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {

        //per ogni parametro aggiungo una pop in popParl
        String popParl="";
        for (Node par:parlist) {
            //System.out.println("par " + ((ParNode) par).getType());
            //if(((ParNode) par).getType() instanceof ObjectTypeNode)
            //    popParl += "pop\n";
            popParl += "pop\n";
        }

        //per ogni dichiarazione nel body (escluse le dichiarazioni di obj) aggiungo una pop in popDecFunBody
        String popDecFunBody = "";
        FunbodyNode fb = (FunbodyNode) funbody;
        if(fb.getDeclist().size() > 0 ){
            for (int i = 0; i < fb.getDeclist().size(); i++){
                if(!(fb.getDeclist().get(i) instanceof ObjectNode))
                    popDecFunBody += "pop\n";
            }
        }

        String funl="";
        //caso in cui FunNode rappresenta una funzione: generiamo una label nuova
        if(funLabel == "")
            funl = FOOLlib.freshFunLabel();
        //caso in cui FunNode rappresenta un metodo: funLabel contiene gia' la label giusta
        else
            funl = funLabel;

        //funzione void
        if (type instanceof VoidTypeNode)
            FOOLlib.putCode(
                funl + ":\n"
                    + "cfp\n"                   //move $fp a $sp
                    + "lra\n"                   //push $ra
                    + funbody.codeGeneration()  //push funbody
                    + popDecFunBody             //pop delle dichiarazioni in funbody
                    + "sra\n"                   //$ra <- top
                    + "pop\n"                   //pop $fp
                    + popParl                   //pop parlist
                    + "sfp\n"                   //$fp <- top
                    + "lra\n"                   //push $ra
                    + "js\n"                    //jump $ra
            );
        //funzione con un valore di ritorno
        else
            FOOLlib.putCode(
                funl + ":\n"
                    + "cfp\n"                   //move $fp a $sp
                    + "lra\n"                   //push $ra
                    + funbody.codeGeneration()  //push funbody e declist
                    + "srv\n"                   //pop $rv
                    + popDecFunBody             //pop delle dichiarazioni in funbody
                    + "sra\n"                   //$ra <- top
                    + "pop\n"                   //pop $fp
                    + popParl                   //pop parlist
                    + "sfp\n"                   //$fp <- top
                    + "lrv\n"                   //push $rv
                    + "lra\n"                   //push $ra
                    + "js\n"                    //jump $ra
            );

        return "push "+ funl +"\n";
    }

}  