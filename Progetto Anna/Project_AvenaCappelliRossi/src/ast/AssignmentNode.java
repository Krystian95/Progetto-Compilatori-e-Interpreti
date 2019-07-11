package ast;
import lib.FOOLlib;
import org.antlr.v4.runtime.tree.TerminalNode;
import util.Environment;
import util.SemanticError;
import java.util.*;

/* Nodo che gestisce la regola "assignmentStm" di stm */
public class AssignmentNode implements Node {

    //"id" è la lista di id:
    // - se ha 1 elemento: si sta assegnando un'exp ad una variabile. id(0)=nome variabile
    // - se ha 2 elementi: si sta assegnano un'exp ad un campo. id(0)=nome oggetto, id(1)=nome campo
    private List<TerminalNode> id;

    private Node exp;           //espressione da assegnare
    private Node idType = null; //tipo della variabile, del campo o ObjectType
    private String entry;       //stringa da stampare nel toPrint
    private String classID;     //nome della classe del campo se si vuole assegnare un'exp ad un campo
    private ClassTypeNode classType = null; //Nodo per la classe dell'oggetto o del campo
    private ClassTypeNode expNode;     //Nodo per l'exp se è un oggetto o Nodo del ramo then se l'exp e' un ifNode di tipo oggetto
    private ArrayList<HashMap<String, STentry>> symT = new ArrayList<>(); //symbol table
    private int idOffset; //offset dell'id (variabile o oggetto)
    private int offsetField; //offset del campo

    public AssignmentNode(List<TerminalNode> var, Node e){
        id=var;
        exp=e;
    }

    public List<TerminalNode> getId(){ return id; }

    public Node getExp() { return exp; }

    public Node getIdType() { return idType; }

    public String getEntry() { return entry; }

    public String getClassID() { return classID; }

    public ClassTypeNode getClassType() { return classType; }

    public Node getExpClassType() { return expNode; }

    public ArrayList<HashMap<String, STentry>> getSymT() { return symT; }

    public int getIdOffset() { return idOffset; }

    public int getOffsetField() { return offsetField; }

    @Override
    public String toPrint(String s){
        if(idType instanceof ObjectTypeNode) {
            //caso in cui si sta assegnando un'exp ad un campo
            if (id.size() > 1)
                return s + "Stm\nAss: " + id.get(0).getText() + "." + id.get(1).getText()
                        + " of class " + classID + "\n"
                        + entry
                        + "\n=\n" + exp.toPrint(s + "");
            //caso in cui si sta assegnando un oggetto ad un altro oggetto
            else
                return s+ "Stm\nAss: " + id.get(0).getText() + "\n=\n" + exp.toPrint(s+"");
        }
        //caso in cui si sta assegnando un'exp ad una variabile
        else
            return s+ "Stm\nAss: " + id.get(0).getText() + "\n=\n" + exp.toPrint(s+"");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> res = new ArrayList<>();

        //variabili utili
        String varID = id.get(0).getText(); //nome della variabile o dell'oggetto
        String className;  //nome della classe se si sta assegnando un'exp ad un oggetto o ad un campo
        STentry varEntry = null;  //entry della variabile nella ST

        //cerco l'entry dell'id nella ST dal NL più esterno a NL = 0
        int j = env.getNestingLevel();
        while (j >= 0) {
            varEntry = (env.getSymTable().get(j--)).get(varID);
            if (varEntry != null) break;
        }

        //se la variabile (o l'oggetto) non è stata dichiarata segnalo errore
        if(varEntry == null)
            res.add(new SemanticError("Variable " + varID + " not declared"));

        //caso in cui la variabile (o l'oggetto) esiste
        else {
            idOffset = varEntry.getOffset(); //memorizzo l'offset dell'id
            idType = varEntry.getType(); //memorizzo il tipo dell'entry

            //caso "id0.id1 = exp" dove il tipo di id0 non è un oggetto
            if (!(idType instanceof ObjectTypeNode) && id.size() > 1) {
                res.add(new SemanticError("Variable " + varID + " not an Object"));
            }

            //casi "oggetto = exp" e "campo = exp"
            else if (idType instanceof ObjectTypeNode){
                //cerco l'entry della classe dell'oggetto
                className = ((ObjectTypeNode) idType).getClassName();
                STentry classEntry = env.getSymTable().get(0).get(className);

                //caso in cui l'oggetto non e' di nessuna classe essistente
                if (classEntry == null)
                    res.add(new SemanticError("Object " + varID + " not used correctly in assignment: his class not exists"));
                else {
                    //memorizzo in classType il ClassTypeNode della classe dell'oggetto e in classID il nome della classe
                    classType = (ClassTypeNode) classEntry.getType();
                    classID = className;

                    //caso "campo = exp"
                    if (id.size() != 1) {
                        //memorizzo il nome del campo e l'elenco dei campi della classe e delle sue superclassi
                        String fieldName = id.get(1).getText();
                        LinkedHashMap<String, Integer> offsetFields = classType.getOffsetFields();

                        //caso in cui il campo non appartiene alla classe dell'oggetto o alle sue superclassi
                        if (!offsetFields.containsKey(fieldName)) {
                            if (classType.getClassesExtended() == null)
                                res.add(new SemanticError("Field id " + fieldName + " not declared in class " + classID));
                            else
                                res.add(new SemanticError("Field id " + fieldName + " not declared either in class "
                                    + classID + " and in superclasses"));
                        }

                        //caso in cui il campo appartiene o alla classe dell'oggetto o ad una sua superclasse
                        else {
                            //memorizzo l'offset del campo e la lista dei campi della classe
                            offsetField = offsetFields.get(fieldName);
                            LinkedHashMap<String, Node> parlist = classType.getParlist();

                            //caso in cui il campo e' dichiarato nella classe
                            if (parlist.get(fieldName) != null) {
                                idType = parlist.get(fieldName);
                                entry = "\tSTentry: nestlev 1\n" + "\tSTentry: type\n\t" + parlist.get(fieldName).toPrint("")
                                    + "\tSTentry: offset " + offsetField;
                            }

                            //caso in cui il campo e' dichiarato in una classe estesa
                            else {
                                //scorro tutte le classi estese fino a quella in cui si trova il campo richiesto
                                int i = 0;
                                while (i < classType.getClassesExtended().size()) {
                                    //memorizzo la lista di campi della classe i-esima
                                    parlist = classType.getClassesExtended().get(i).getParlist();
                                    //se il campo e' dichiarato nell'i-esima classe
                                    if (parlist.containsKey(fieldName)) {
                                        idType = parlist.get(fieldName);
                                        //memorizzo in classID il nome della classe nella quale è definito il campo
                                        classID = classType.getClassesExtended().get(i).getClassID();
                                        entry = "STentry: nestlev 1\n"
                                            + "STentry: type\n" + parlist.get(fieldName).toPrint(" ")
                                            + "STenry: offset " + offsetField + "\n";
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

        //CS di exp
        res.addAll(exp.checkSemantics(env));

        //mi salvo la ST da NL 0 a NL corrente
        int k = 0;
        while(k<=env.getNestingLevel())
            symT.add(env.getSymTable().get(k++));

        //ricavo la classe di exp se exp si riferisce ad un oggetto
        expNode = FOOLlib.obtainNodeClass(symT, env.getNestingLevel(), exp);

        return res;
    }

    @Override
    public Node typeCheck(){
        //salvo il tipo di exp
        Node expType = exp.typeCheck();

        //verifico se a sinistra dell'uguale ci sia un oggetto
        boolean isAnObj = (idType instanceof ObjectTypeNode) && id.size() == 1;

        //caso in cui sto assegnando un'exp ad un oggetto: l'exp deve essere o un oggetto o null altrimenti segnalo errore
        if( isAnObj && !(expType instanceof ObjectTypeNode) && !(expType instanceof NullNode) ) {
            System.out.println("Incompatible type in assignment for object " + id.get(0).getText());
            System.exit(0);
        }
        //caso in cui sto assegnando un'exp ad una variabile o a un campo: l'exp non deve essere nè un oggetto nè null nè void
        else if( !isAnObj && ( (expType instanceof ObjectTypeNode) || (expType instanceof NullNode) || (expType instanceof VoidTypeNode) ) ){
            if(id.size() > 1)
                System.out.println("Incompatible type in assignment for field " + id.get(0).getText() + "." + id.get(1).getText());
            else
                System.out.println("Incompatible type in assignment for variable " + id.get(0).getText());
            System.exit(0);
        }
        //caso in cui sto assegnando un oggetto ad un oggetto, o un valore ad una variabile o un valore ad un campo
        else{
            //caso in cui assegno un oggetto ad un oggetto: verifico che le classi siano compatibili
            if ( isAnObj && (expType instanceof ObjectTypeNode)){
                if (exp instanceof IfNode){
                    if(!FOOLlib.isSubclass(((IfNode) exp).getIfreturn(),classType, symT.get(0))) {
                        System.out.println("Incompatible type in assignment for object " + id.get(0).getText());
                        System.exit(0);
                    }
                }
                else if (!FOOLlib.isSubclass(expNode,classType, symT.get(0))){
                    System.out.println("Incompatible type in assignment for object " + id.get(0).getText());
                    System.exit(0);
                }
            }
            //caso in cui assegno ad una variabile un valore
            else if( !isAnObj && id.size() == 1 ){
                if(!FOOLlib.isSubtype(expType, idType)){
                    System.out.println("Incompatible type in assignment for variable " + id.get(0).getText());
                    System.exit(0);
                }
            }
            //caso in cui assegno ad un campo un valore
            else if (!isAnObj && id.size() > 1){
                if(idType != null && !FOOLlib.isSubtype(expType, idType)){
                    System.out.println("Incompatible type in assignment for field " + id.get(0).getText() + "." + id.get(1).getText());
                    System.exit(0);
                }
            }
        }
        //restituisco void
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration(){
        boolean isAnObj = (idType instanceof ObjectTypeNode) && id.size() == 1;

        //caso in cui a sx non c'e' un oggetto
        if ( !isAnObj ) {
            //caso in cui a sx c'e' un campo
            if (id.size() > 1){
                return exp.codeGeneration()
                    + "push " + idOffset + "\n"
                    + "lf\n"
                    + "push " + offsetField + "\n"
                    + "add\n"
                    + "sw\n";
            }
            //caso in cui a sx c'e' una variabile
            else{
               return exp.codeGeneration()
                   + "lfp\n"
                   + "push " + idOffset + "\n"
                   + "add\n"
                   + "sw\n" ;
            }
        }
        //caso in cui a sx c'e' un oggetto
        else{
            if(exp instanceof NullNode) {
                return "push -1\n"
                    + "push " + idOffset + "\n"
                    + "ldp\n"
                    + "sw\n" ;
            }
            else {
                /*
                //label per i rami
                String l1 = FOOLlib.freshLabel();
                String l2 = FOOLlib.freshLabel();

                return "push " + idOffset + "\n" //carico l'objoffset
                    + "ldp\n"                 //tolgo objOffset e carico il dp dell'obj
                    + "lw\n"                  //tolgo il dp dell'obj e carico il suo contenuto
                    + "push -1\n"             //carico -1
                    + "beq " + l1 + "\n"      //se il contenuto di dp e' -1 salto a l1
                    + "push " + idOffset + "\n" //qua inizia l'else
                    + exp.codeGeneration()
                    + "sdp\n"
                    + "b " + l2 + "\n"
                    + l1 + ":\n"              //qua inizia il then
                    + "push -1\n"
                    + "push " + idOffset + "\n"
                    + "ldp\n"
                    + "sw\n"
                    + l2 + ":\n";
                */
                return "push " + idOffset + "\n"
                        + exp.codeGeneration()
                        + "sdp\n" ;
            }
        }
    }

}
