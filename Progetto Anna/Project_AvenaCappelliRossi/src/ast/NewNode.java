package ast;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Nodo che gestisce la regola di value "NewExp"*/
public class NewNode implements Node{

    private String className; //nome della classe dell'oggetto
    private ArrayList<Node> exp; //campi da passare in input
    private STentry entry; //entry della classe dell'oggetto
    private ClassTypeNode newClass; //nome della classe dell'oggetto che si sta istanziando
    private int nestinglevel; //NL corrente
    //in parlist ci sono tutti i campi partendo dalla classe attuale alle superclassi
    private LinkedHashMap<String, Node> parlist = new  LinkedHashMap<>();
    private ArrayList<HashMap<String, STentry>> symT = new ArrayList<>(); //symbol table
    private boolean isSingleNewExp; //true se il newNode e' una single exp (non e' assegnata a qualcosa)
    private boolean isInputFun;  //true se si sta passando il newNode in input ad una funzione
    private int objectOffset;  //offset dell'oggetto newNode se si tratta di una singleExp

    public String getClassName() { return className; }

    public ArrayList<Node> getExp() { return exp; }

    public STentry getEntry () { return entry; }

    public ClassTypeNode getNewClass() { return newClass; }

    public int getNestinglevel() { return nestinglevel; }

    public LinkedHashMap<String, Node> getParlist() { return parlist; }

    public boolean getisSingleNewExp() { return isSingleNewExp; }

    public boolean isInputFun() { return isInputFun; }

    public int getObjectOffset() { return  objectOffset; }

    public NewNode(String cl, ArrayList<Node> e, boolean isSNE, boolean isIF){
        className=cl;
        exp = e;
        isSingleNewExp=isSNE;
        isInputFun=isIF;
    }

    @Override
    public String toPrint(String s){

        String expstr="";
        for (Node e : exp)
            expstr += e.toPrint(s + "");

        return s+"New obj of class " + className + " at nestlev " + nestinglevel + "\nClass "
                + className + "\n"
                + entry.toPrint("")
                + expstr;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        ArrayList<SemanticError> res = new ArrayList<>();

        //memorizzo in entryClass la classe su cui è istanziato l'oggetto
        STentry entryClass = env.getSymTable().get(0).get(className);

        //controllo che la classe sia stata dichiarata
        if(entryClass == null) {
            res.add(new SemanticError("Class id " + className + " not declared"));
        }
        else {
            entry = entryClass;
            newClass  = (ClassTypeNode) entry.getType();
            if(isSingleNewExp) {
                objectOffset = env.getObjectNumber();
                env.incrementObjectNumber();
            }
        }

        //CS parametri della classe
        for (Node e : exp) {
            res.addAll(e.checkSemantics(env));
        }

        //memorizzo la ST e il NL corrente
        nestinglevel = env.getNestingLevel();
        int j = 0;
        while(j<=nestinglevel)
            symT.add(env.getSymTable().get(j++));

        return res;
    }

    @Override
    public Node typeCheck(){

        //memorizzo i campi della classe di cui voglio creare un'istanza leggendoli dalla ST
        LinkedHashMap<String, Node> pars = newClass.getParlist();

        for(Map.Entry<String,Node> p : pars.entrySet() ) {
            parlist.put(p.getKey(), p.getValue());
        }
        //se la classe ne estende altre, aggiugo ai campi della classe quelli delle sue superclassi in ordine
        if(newClass.getClassesExtended() != null) {
            for (ClassTypeNode c : newClass.getClassesExtended()) {
                parlist.putAll(c.getParlist());
            }
        }

        //creo un array dove memorizzo i tipi di tutti i campi necessari per istanziare un oggetto
        ArrayList<Node> parlistValue = new ArrayList<>(parlist.values());

        //se il numero di campi richiesti è diverso dal numero di campi inseriti segnalo errore
        if(parlist.size() != exp.size()) {
            System.out.println("Wrong number of parameters in the constructor of class " + className);
            System.exit(0);
        }
        //caso in cui il numero di campi inseriti è corretto
        else {
            int i=0; //contatore di campi
            //per ogni campo inserito in exp
            for(Node e : exp) {
                //se il campo è una chiamata di funzione segnalo errore
                if(e instanceof CallNode || ((e instanceof MethodcallNode) && (((MethodcallNode) e).getLpar()))) {
                    System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className + ": call not admitted");
                    System.exit(0);
                }
                //se il campo e' un NewNode segnalo errore
                else if(e instanceof NewNode){
                    System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className + ": new not admitted");
                    System.exit(0);
                }
                //se il campo e' un NullNode segnalo errore
                else if(e instanceof NullNode){
                    System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className + ": null not admitted");
                    System.exit(0);
                }
                //caso in cui il parametro inserito e' di un tipo concesso
                else{
                    //memorizzo il parametro attuale e fomale
                    Node attParType = e.typeCheck(); //tipo parametro attuale
                    Node formParType = parlistValue.get(i); //tipo parametro formale

                    //caso in cui il parametro formale non e' un oggetto
                    if (!(formParType instanceof ObjectTypeNode)){
                        //tipo del parametro attuale inserito <: tipo del parametro formale corrispondente memorizzato nella ST
                        if(!(FOOLlib.isSubtype(attParType, formParType))) {
                            System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className);
                            System.exit(0);
                        }
                    }
                    //caso in cui il parametro formale e' un oggetto
                    else{
                        //se il parametro attuale non e' un oggetto restituisco errore
                        if (!(attParType instanceof ObjectTypeNode)) {
                            System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className);
                            System.exit(0);
                        }
                        //ricavo i tipi delle classi del parametro attuale e del parametro formale
                        attParType = FOOLlib.obtainNodeClass(symT, nestinglevel, attParType); //tipo della classe del parametro attuale
                        formParType = FOOLlib.obtainNodeClass(symT, nestinglevel, formParType); //tipo della classe del parametro formale

                        //verifico che la classe del parametro attuale sia <: della classe del parametro formale
                        if(!FOOLlib.isSubclass((ClassTypeNode) attParType, (ClassTypeNode) formParType, symT.get(0))){
                            System.out.println("Wrong type for " + (i+1) + "-th parameter in the constructor of class " + className);
                            System.exit(0);
                        }
                    }
                }
                i++;
            }
        }

        //restituisco il tipo dell'oggetto creato
        return new ObjectTypeNode(className);
    }

    @Override
    public String codeGeneration(){

        LinkedHashMap<String, Integer> ofm = ((ClassTypeNode) symT.get(0).get(className).getType()).getOffsetFields();
        ArrayList<String> fieldsNames = new ArrayList<>(parlist.keySet());

        //codeGen dei parametri del NewNode
        String fieldCodegen="";
        if(fieldsNames.size() > 0) {
            for (String fieldName : ofm.keySet()) {
                fieldCodegen += exp.get(fieldsNames.indexOf(fieldName)).codeGeneration() + "sf\n";
            }
        }

        //espressione singola
        if (isSingleNewExp){
            //sto passando uno newNode in input ad una funzione
            if(isInputFun)
                return "push " + objectOffset + "\n"
                    + "lhp\n"
                    + "alloc " + entry.getOffset() + "\n"
                    + "sdp\n"
                    + fieldCodegen
                    + "push " + objectOffset + "\n";
            else
                return "push " + objectOffset + "\n"
                    + "lhp\n"
                    + "alloc " + entry.getOffset() + "\n"
                    + "sdp\n"
                    + fieldCodegen
                    + "push " + objectOffset + "\n"
                    + "ldp\n";
        }
        //assegnamento di newNode a qualcosa
        else{
            return "lhp\n"
                + "alloc " + entry.getOffset() + "\n"
                + "sdp\n"
                + fieldCodegen;
        }
    }

}
