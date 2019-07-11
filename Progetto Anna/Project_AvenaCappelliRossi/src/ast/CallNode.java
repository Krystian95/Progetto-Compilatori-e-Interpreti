package ast;
import java.util.*;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

/* Nodo che gestisce la chiamata di funzione quindi la regola "funExp" di value */
public class CallNode implements Node {

    private int nestinglevel; //NL dove avviene la chiamata di funzione
    private int nestinglevelFun; //NL della ST nella quale si trova l'entry della funzione
    private String id; //nome della funzione chiamata
    private ArrayList<Node> parlist; //lista degli input della funzione
    private Node funType; //tipo di ritorno della funzione
    private String funID; //nome della funzione chiamante
    private int offsetFun = -2; //offset dell'entry della ST in cui si trova la funzione chiamata
    private String entry; //entry della funzione nella ST
    private HashMap<String, STentry> sTNL0;
    private boolean isMethodCall;
    //tipi delle classi degli oggetti passati come parametri attuali
    private ArrayList<ClassTypeNode> classesPar = new ArrayList<>();

    public CallNode(String text, ArrayList<Node> args) {
        id=text;
        parlist = args;
        funID="";
    }

    public CallNode(String text, ArrayList<Node> args, String f) {
        id=text;
        parlist = args;
        funID=f;
    }

    public CallNode(String text, ArrayList<Node> args, Node ft, boolean isMC, HashMap<String, STentry> sT) {
        id=text;
        parlist = args;
        funID="";
        funType=ft;
        isMethodCall=isMC;
        sTNL0=sT;
    }

    public int getNestingLevel() { return nestinglevel; }

    public int getNestinglevelFun() { return nestinglevelFun; }

    public String getId() { return id; }

    public ArrayList<Node> getParlist() { return parlist; }

    public Node getFunType() { return funType; }

    public String getFunID() { return funID; }

    public int getOffsetFun() { return offsetFun; }

    public String getEntry(){ return entry; }

    public ArrayList<ClassTypeNode> getClassesPar() { return classesPar; }

    @Override
    public String toPrint(String s) {
        //parametri
        String parlstr="";
        if(parlist != null)
            for (Node par : parlist)
                parlstr += par.toPrint(s + "  ");

        return s+"Call: " + id + " at nestlev " + nestinglevel +"\n"
                +entry
                +parlstr;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<>();

        //memorizzo il NL più esterno nel quale mi trovo
        nestinglevel = env.getNestingLevel();

        //cerco l'entry della funzione nella ST dal NL più esterno a NL = 0
        STentry funEntry = null;
        int j = nestinglevel;
        while (j >= 0 ) {
            funEntry = (env.getSymTable().get(j--)).get(id);
            //caso in cui ho trovato la funzione
            if(funEntry != null) {
                //memorizzo tipo dell'entry, NL della funzione, offset della funzione
                funType = funEntry.getType();
                nestinglevelFun = funEntry.getNestinglevel();
                offsetFun = funEntry.getOffset();
                //memorizzo in entry il testo da stampare nella toPrint
                entry = "STentry: nestlev " + nestinglevelFun + "\n"
                    + "STentry: type\n" + funType.toPrint("")
                    + "STentry: offset " + offsetFun + "\n";
                break;
            }
        }

        sTNL0 = env.getSymTable().get(0);

        //caso in cui non ho trovato la funzione
        if (funEntry == null) {
            //caso in cui sono a NL = 0: la funzione non è stata dichiarata all'interno di una classe
            if(env.getNestingLevel() == 0) {
                res.add(new SemanticError("Id " + id + " not declared"));
            }
            //caso in cui la funzione potrebbe essere un metodo della superclasse della classe in cui sono
            else {
                //cerco la funzione chiamante e la classe nella quale e' dichiarata tale funzione perche' ho bisogno
                //di sapere le sue superclassi
                ArrowTypeNode at = (ArrowTypeNode) env.getSymTable().get(1).get(funID).getType();
                String className = at.getClassID();
                ClassTypeNode ct = (ClassTypeNode) env.getSymTable().get(0).get(className).getType();

                //se la classe nella quale mi trovo non ha superclassi allora la funzione non e' stata dichiarata
                if(ct.getClassesExtended() == null) {
                    res.add(new SemanticError("Fun " + id + " not declared in class " + className));
                }
                //caso in cui la funzione potrebbe essere un metodo delle superclassi della classe nella quale mi trovo
                else {
                    //ricavo l'elenco di tutti i metodi della classe (compresi quelli delle superclassi)
                    LinkedHashMap<String, Integer> funlist = ct.getOffsetMethods();
                    //se la funzione non e' presente fra questi segnalo errore
                    if(!funlist.containsKey(id)){
                        res.add(new SemanticError("Id " + id + " not declared either in class " + className
                            + " and in superclasses"));
                    }
                    //caso in cui la funzione e' un metodo di una superclasse della classe nella quale mi trovo
                    else{
                        //salvo l'offset del metodo
                        offsetFun = funlist.get(id);
                        //scorro tutte le superclassi della classe in cui è dichiarata la funzione chiamante
                        //per ottenere le sue informazioni
                        ClassTypeNode tmp;
                        int i=0;
                        while (i<ct.getClassesExtended().size()) {
                            //prendo la lista dei metodi di ogni superclasse
                            tmp = ct.getClassesExtended().get(i);
                            LinkedHashMap<String, Node> funlistE = tmp.getFunlist();
                            //caso in cui il metodo e' nella classe i-esima
                            if (funlistE.containsKey(id)) {
                                nestinglevelFun = env.getNestingLevel() - 1;
                                funType = funlistE.get(id);
                                entry = "STentry: nestlev " + nestinglevelFun + "\n"
                                    + "STentry: type\n" + funlistE.get(id).toPrint("")
                                    + "STentry: offset " + offsetFun + "\n";
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
        }

        //CS DEI PARAMETRI
        String nomeParAttuale; //nome parametro attuale
        ObjectTypeNode otPar; //tipo del parametro se il parametro attuale e' un oggetto
        ClassTypeNode ctPar; //tipo della classe del parametro se il parametro attuale e' un oggetto

        //caso in cui ci sono dei parametri
        if(parlist != null) {
            //scorro tutti i parametri attuali della funzione
            //se i parametri attuali sono degli oggetti mi devo salvare i tipi delle rispettive classi
            for (Node p : parlist) {
                //caso in cui il parametro attuale e' un idNode
                if (p instanceof IdNode) {
                    j = nestinglevel;
                    nomeParAttuale = ((IdNode) p).getId();
                    STentry parEntry;
                    //cerco l'entry dell'oggetto nella ST dal NL della chiamata della funzione a NL = 0
                    while (j >= 0) {
                        parEntry = (env.getSymTable().get(j--)).get(nomeParAttuale);
                        //caso in cui ho trovato il parametro attuale nella ST
                        if (parEntry != null){
                            //caso in cui il parametro attuale e' un oggetto
                            if (parEntry.getType() instanceof ObjectTypeNode) {
                                //aggiungo in classesPar il tipo della classe del parametro attuale
                                otPar = (ObjectTypeNode) parEntry.getType();
                                ctPar = (ClassTypeNode) env.getSymTable().get(0).get(otPar.getClassName()).getType();
                                classesPar.add(ctPar);
                            }
                            break;
                        }
                    }
                }
                //caso in cui il parametro attuale e' uno NewNode
                else if(p instanceof NewNode){
                    //aggiungo in classesPar il tipo della classe del parametro attuale
                    String cn = ((NewNode) p).getClassName();
                    ctPar = (ClassTypeNode) env.getSymTable().get(0).get(cn).getType();
                    classesPar.add(ctPar);
                }

                //CS dei parametri attuali
                res.addAll(p.checkSemantics(env));
            }
        }

        return res;
    }

    @Override
    public Node typeCheck() {
        //cerco l'entry della funzione nella ST e controllo che corrisponda al tipo "ArrowType"
        ArrowTypeNode t;
        if (!(funType instanceof ArrowTypeNode)) {
            System.out.println("Invocation of a non-function " + id);
            System.exit(0);
        }
        t = (ArrowTypeNode) funType;

        //GESTIONE PARAMETRI
        //se il numero di input richiesti e' diverso dal numero di input inseriti segnalo errore
        if (!(t.getParList().size() == parlist.size())) {
            System.out.println("Wrong number of parameters in the invocation of " + id);
            System.exit(0);
        }

        ClassTypeNode ctPar; //tipo della classe del parametro attuale
        String className; //nome della classe del parametro attuale
        int countObj = 0; //contatore degli oggetti passati in input (diverso dal numero di input)

        //find=true se la classe dell'oggetto che costituisce il parametro formale è una superclasse della classe
        //dell'oggetto passato come parametro attuale
        boolean find = false;
        boolean findClass;

        //per ogni input della funzione
        for (int i = 0; i < parlist.size(); i++){
            //se il parametro attuale e' una call restituisco errore
            if(parlist.get(i) instanceof CallNode || ( (parlist.get(i) instanceof MethodcallNode) && ((MethodcallNode) parlist.get(i)).getLpar())) {
                System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of "
                    + id + ": call not admitted");
                System.exit(0);
            }

            //ricavo i tipi dei parametri attuali e formali
            Node parType = parlist.get(i).typeCheck(); //tipo parametro attuale
            Node formParType = t.getParList().get(i);  //tipo parametro formale

            //caso in cui il parametro formale non e' un oggetto
            if (!(formParType instanceof ObjectTypeNode)){
               //tipo del parametro attuale inserito <: tipo del parametro formale corrispondente memorizzato nella ST
                if(!(FOOLlib.isSubtype(parType, formParType))) {
                    System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
                    System.exit(0);
                }
            }
            //caso in cui il parametro formale e' un oggetto
            else {
                //se il parametro attuale non e' ne' un oggetto ne' null restituisco errore
                if (!(parType instanceof ObjectTypeNode) && !(parType instanceof NullNode)) {
                    System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
                    System.exit(0);
                }

                //caso in cui il parametro attuale è uno NewNode e si sta chiamando il typeCheck di CallNode da MethodcallNode
                if(isMethodCall) {
                    if(parlist.get(i) instanceof NewNode) {
                        className = ((NewNode) parlist.get(i)).getClassName();
                        ctPar = (ClassTypeNode) sTNL0.get(className).getType();
                        classesPar.add(ctPar);
                    }
                }

                //caso un cui il parametro attuale e' un oggetto
                if((parType instanceof ObjectTypeNode) && (classesPar.size() > 0)){
                    //nome della classe del parametro formale
                    className = ((ObjectTypeNode) formParType).getClassName();
                    //in ctPar memorizzo i tipi delle classi degli oggetti passati come parametri attuali
                    ctPar = classesPar.get(countObj);
                    countObj++;
                    //controllo che le classi del parametro attuale e formale non siano le stesse
                    if (!className.equals(ctPar.getClassID())) {
                        //controllo che la classe del parametro attuale non estenda nessuna altra classe, se così fosse
                        //do errore
                        if (ctPar.getClassesExtended() == null) {
                            System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
                            System.exit(0);
                        }
                        //cso in cui la classe del parametro attuale estende altre classi
                        else {
                            //controllo per ogni superclasse della classe del parametro attuale se corrisponde
                            //alla classe del parametro formale, se si mi fermo (imposto find a true) e non do errore
                            if (!className.equals(ctPar.getClassID())) {
                                for (ClassTypeNode ct : ctPar.getClassesExtended()) {
                                    if (ct.getClassID().equals(className)) {
                                        find = true;
                                        break;
                                    }
                                }
                                //se nessuna delle superclassi corrisponde alla classe del parametro formale do errore
                                if (!find) {
                                    System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }

        //restituisco il tipo della funzione memorizzato nella ST
        return t.getReturn();
    }

    @Override
    public String codeGeneration() {
        //codeGen dei parametri
        String parCode="";
        for (int i=parlist.size()-1; i>=0; i--) {
            parCode += parlist.get(i).codeGeneration();
        }

        //lw per risalire la catena statica
        String getAR="";
        for (int i=0; i<nestinglevel-nestinglevelFun; i++)
            getAR += "lw\n";

        //caso in cui si chiama un metodo da un altro metodo
        if (nestinglevelFun == 1)
            return "lfp\n"
                + parCode
                + "lfp\n"
                + "push " + offsetFun + "\n"
                + "jdt\n";

        //caso in cui si chiama una funzione
        else
            return "lfp\n"
                + parCode
                + "lfp\n"
                + getAR
                + "push " + offsetFun + "\n"
                + "lfp\n"
                + getAR
                + "add\n"
                + "lw\n"
                + "js\n";
    }

}  