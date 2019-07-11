package ast;
import org.antlr.v4.runtime.tree.TerminalNode;
import util.Environment;
import util.SemanticError;
import java.util.*;

/* Nodo della regola "MethodCallExp" di value, rappresenta chiamate di metodo e campi */
public class MethodcallNode implements Node {

    //"id" è la lista di id: id(0) è il nome dell'oggetto, id(1) è il nome del metodo o del campo a seconda se sono
    //presenti parentesi tonde o meno
    private List<TerminalNode> id;

    private boolean lpar; //booleano che distingue se il nodo e' chiamato per una chiamata di metodo o per un campo
    private ArrayList<Node> parlist; //parametri del metodo
    private Node idType = null; //tipo di id(0)
    private Node nameType = null; //tipo di id(1) sia se il nodo rappresenta un metodo che un campo
    private String entry; //entry da stampare
    private int nestinglevel; //NL attuale
    private String classID; //nome della classe del metodo
    private int classOffset; //offset della classe
    private int methodOffset; //offset del metodo
    private int fieldOffset; //offset del campo
    private int objectOffset; //offset dell'oggetto
    private boolean isParameter; //true se il campo e' un parametro
    private boolean isField; //true si chiamano un campo o un metodo su un oggetto che e' un campo di un altro oggetto

    private HashMap<String, STentry> sTNL0 = new HashMap<>();

    //costruttore di chiamate di metodo
    public MethodcallNode(List<TerminalNode> o, ArrayList<Node> e, boolean isF){
        id=o;
        parlist=e;
        lpar=true;
        isField =isF;
    }

    //costruttore di campi
    public MethodcallNode(List<TerminalNode> o, boolean isP, boolean isF){
        id=o;
        lpar=false;
        isParameter=isP;
        isField=isF;
    }

    public List<TerminalNode> getId() { return id; }

    public boolean getLpar() { return lpar; }

    public ArrayList<Node> getParlist() { return parlist; }

    public Node getIdType(){ return idType; }

    public Node getNameType(){ return nameType; }

    public String getEntry() { return entry; }

    public String getClassID() { return classID; }

    public int getClassOffset() { return classOffset; }

    public int getMethodOffset() { return methodOffset; }

    public int getFieldOffset() { return fieldOffset; }

    public int getObjectOffset() { return objectOffset; }

    @Override
    public String toPrint(String s){
        //caso in cui il nodo rappresenta una chiamata di metodo
        if (lpar) {
            //memorizzo i parametri
            String parlstr="";
            if (parlist != null)
                for (Node e : parlist)
                    parlstr += e.toPrint(s + "");

            return s + "MethodCall\nObject: " + id.get(0).getText() + " calls method " + id.get(1).getText()
                    + " of class " + classID + " at nestlevel " + nestinglevel + "\n"
                    + entry
                    + parlstr;
        }
        //caso in cui il nodo rappresenta un campo
        else
            return s + "Field: " + id.get(0).getText() + "." + id.get(1).getText()
                    + " of class " + classID + "\n"
                    + entry;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> res = new ArrayList<>();

        //verifico che l'id dell'oggetto sia presente nella ST
        String objectName = id.get(0).getText();
        int j = env.getNestingLevel();
        STentry tmp = null;
        while (j >= 0) {
            tmp = (env.getSymTable().get(j--)).get(objectName);
            if(tmp != null) {
                objectOffset = tmp.getOffset();
                break;
            }
        }

        //l'id dell'oggetto non si trova nella ST
        if (tmp==null)
            res.add(new SemanticError("Object id "+ objectName +" not declared"));

        //l'id dell'oggetto e' nella ST
        else {
            sTNL0 = env.getSymTable().get(0);
            nestinglevel = env.getNestingLevel();

            //controllo il tipo dell'id, se non e' un oggetto non faccio nulla, l'errore sara' segnalato nel TC
            idType = tmp.getType();
            if (idType instanceof ObjectTypeNode) {
                //String className = ((ObjectTypeNode) idType).getClassName();
                classID = ((ObjectTypeNode) idType).getClassName();
                //classID = className;

                //name può essere o un campo o un metodo
                String name = id.get(1).getText();

                //verifico che la classe del metodo/campo esista, altrimenti do' errore
                tmp = env.getSymTable().get(0).get(classID);
                if (tmp == null) {
                    if (lpar) {
                        res.add(new SemanticError("Method id " + name + " not called correctly: his class not exists"));
                    } else {
                        res.add(new SemanticError("Field id " + name + " not used correctly: his class not exists"));
                    }
                }
                //la classe del metodo o del campo esiste
                else {
                    //salvo il tipo e l'offset della classe
                    ClassTypeNode objectClassType = (ClassTypeNode) tmp.getType();
                    classOffset = tmp.getOffset();

                    //caso METODO dichiarato nella classe dell'oggetto
                    if(lpar) {
                        //verifico che il metodo appartenga alla classe o alle sue superclassi
                        LinkedHashMap<String, Integer> offsetMethods = objectClassType.getOffsetMethods();
                        if(!offsetMethods.containsKey(name)){
                            if(objectClassType.getClassesExtended() == null)
                                res.add(new SemanticError("Method id " + name + " not declared in class " + classID));
                            else
                                res.add(new SemanticError("Method id " + name + " not declared either in class "
                                    + classID + " and in superclasses"));
                        }
                        //caso in cui il metodo e' dichiarato nella classe o nelle sue superclassi
                        else{
                            //salvo l'offset del metodo e la lista di metodi della classe
                            methodOffset = offsetMethods.get(name);
                            LinkedHashMap<String, Node> funlist = objectClassType.getFunlist();

                            //caso in cui il metodo e' dichiarato nella classe dell'oggetto
                            if (funlist.containsKey(name)){
                                nameType = funlist.get(name);
                                entry = "\tSTentry: nestlev 1\n" + "\tSTentry: type\n\t" + nameType.toPrint("")
                                    + "\tSTentry: offset " + methodOffset + "\n";
                            }

                            //caso in cui il metodo e' dichiarato in una superclasse
                            else {
                                //scorro tutte le classi estese finche' non trovo quella in cui e' dichiarato il metodo
                                int i = 0;
                                while (i<objectClassType.getClassesExtended().size()) {
                                    //memorizzo la lista dei metodi della classe i-esima
                                    funlist = objectClassType.getClassesExtended().get(i).getFunlist();
                                    //se il metodo e' dichiarato nell'i-esima classe
                                    if (funlist.containsKey(name)) {
                                        nameType = funlist.get(name);
                                        classID = objectClassType.getClassID();
                                        entry = "STentry: nestlev 1\n"
                                            + "STentry: type\n" + nameType.toPrint(" ")
                                            + "STenry: offset " + methodOffset + "\n";
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                    }

                    //caso CAMPO dichiarato nella classe dell'oggetto
                    else {
                        //verifico che il campo appartenga alla classe o alle sue superclassi
                        LinkedHashMap<String, Integer> offsetFields = objectClassType.getOffsetFields();
                        if(!offsetFields.containsKey(name)){
                            if(objectClassType.getClassesExtended() == null)
                                res.add(new SemanticError("Field id " + name + " not declared in class " + classID));
                            else
                                res.add(new SemanticError("Field id " + name + " not declared either in class " + classID
                                    + " and in superclasses"));
                        }
                        //caso in cui il campo e' dichiarato nella classe o nelle sue superclassi
                        else{
                            //salvo l'offset del campo e la lista di campi della classe
                            fieldOffset = offsetFields.get(name);
                            LinkedHashMap<String, Node> parlist = objectClassType.getParlist();

                            //caso in cui il campo e' dichiarato nella classe dell'oggetto
                            if(parlist.containsKey(name)){
                                nameType = parlist.get(name);
                                entry = "\tSTentry: nestlev 1\n" + "\tSTentry: type\n\t" + nameType.toPrint("")
                                    + "\tSTentry: offset " + fieldOffset + "\n";
                            }

                            //caso in cui il campo e' dichiarato in una superclasse
                            else  {
                                //scorro tutte le classi estese finche' non trovo quella in cui e' dichiarato il metodo
                                int i = 0;
                                while (i<objectClassType.getClassesExtended().size()) {
                                    //memorizzo la lista dei campi della classe i-esima
                                    parlist = objectClassType.getClassesExtended().get(i).getParlist();
                                    //se il campo e' dichiarato nell'i-esima classe
                                    if (parlist.containsKey(name)) {
                                        //memorizzo il tipo del metodo richiamto (ArrowType)
                                        nameType = parlist.get(name);
                                        classID = objectClassType.getClassID();
                                        entry = "STentry: nestlev 1\n"
                                            + "STentry: type\n" + nameType.toPrint(" ")
                                            + "STenry: offset " + methodOffset + "\n";
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }

        //CS dei parametri
        if (parlist != null)
            for (Node p : parlist) {
                res.addAll(p.checkSemantics(env));
            }

        return res;
    }

    @Override
    public Node typeCheck(){
        //caso in cui viene chiamato un metodo su una varaibile o un campo su una variabile
        if(!(idType instanceof ObjectTypeNode)) {
            System.out.println("Variable " + id.get(0).getText() + " not an object");
            System.exit(0);
        }
        //caso della chiamata di metodo: eseguo il TC sul metodo considerandolo una funzione
        if(lpar){
            return (new CallNode(id.get(1).getText(), parlist, nameType, true, sTNL0)).typeCheck();
        }
        //caso in cui il nodo rappresenta un campo: restituisco il tipo del campo
        else{
            return nameType;
        }

    }

    @Override
    public String codeGeneration(){

        //caso della chiamata di metodo
        if(lpar){
            //codgen degli input del metodo
            String parCode="";
            for (int i=parlist.size()-1; i>=0; i--)
                parCode += parlist.get(i).codeGeneration();

            //caso in cui chiamo un metodo su un oggetto che e' il campo di una classe
            if(isField)
                return "lfp\n"
                    + parCode
                    + "lfp\n"
                    + "lop\n"                           //offset dell'oggetto su cui si chiama il metodo
                    + "ldp\n"                           //carico il punto in cui inizia l'oggetto su cui chiamo il metodo
                    + "push " + objectOffset + "\n"     //carico l'offset del campo da leggere
                    + "add\n"                           //carico l'indirizzo al quale si trova l'oggetto da leggere
                    + "lw\n"                            //carico l'inidirizzo in cui inizia l'oggetto
                    + "lio\n"                           //l'offset di obj5
                    + "sop\n"
                    + "push " + methodOffset + "\n"      //carico l'offset del campo dell'oggetto che e' il campo della classe
                    + "jdt\n";

            //caso in cui si chiama un metodo normale
            else
                return "lfp\n"
                    + parCode
                    + "lfp\n"
                    + "push " + objectOffset + "\n"
                    + "sop\n"
                    + "push " + methodOffset + "\n"
                    + "jdt\n";

        }

        //caso di utilizzo di un campo
        else {
            String getAR="";
            for (int i=0; i<nestinglevel-1; i++)
                getAR += "lw\n";

            //caso in cui si passa un campo in input ad una funzione
            if(isParameter){
                return "push " + objectOffset + "\n"    //offset parametro
                    + "lfp\n"
                    //+ getAR
                    + "add\n"
                    + "lw\n"                            //carichiamo l'offset dell'oggetto
                    + "lf\n"                            //punto in cui inizia l'oggetto
                    + "push " + fieldOffset + "\n"
                    + "add\n"
                    + "lw\n";
            }
            //caso in cui chiamo il campo di un oggetto che e' il campo di una classe
            else if(isField){
                return "lop\n"                          //offset dell'oggetto su cui si chiama il metodo
                    + "ldp\n"                           //carico il punto in cui inizia l'oggetto su cui chiamo il metodo
                    + "push " + objectOffset + "\n"     //carico l'offset del campo da leggere
                    + "add\n"                           //carico l'indirizzo al quale si trova l'oggetto da leggere
                    + "lw\n"                            //carico l'inidirizzo in cui inizia l'oggetto
                    + "lio\n"                           //offset dell'oggetto passato in input alla new
                    + "lf\n"                            //carico il punto in cui inizia l'oggetto
                    + "push " + fieldOffset + "\n"      //carico l'offset del campo dell'oggetto che e' il campo della classe
                    + "add\n"
                    + "lw\n";
            }
            //caso di normale chiamata di campo
            else {
                return "push " + objectOffset + "\n"
                    + "lf\n"
                    + "push " + fieldOffset + "\n"
                    + "add\n"
                    + "lw\n";
            }
        }

    }

}
