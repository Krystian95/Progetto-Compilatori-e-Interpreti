package ast;
import java.util.ArrayList;
import util.Environment;
import util.SemanticError;

/* Nodo della regola di value "ID"*/
public class IdNode implements Node {

    private String id; //nome della variabile
    private STentry entry; //entry della variabile
    private int nestinglevel; //NL nel quale è utilizzata la variabile
    private boolean isField; //variabile true se l'id rappresenta un campo
    private int fieldOffset; //offset del campo
    private boolean isObject = false; //variabile true se l'id rappresenta un oggetto
    private int objectOffset; //offset dell'oggetto
    private boolean isInputFun; //true se si passa l'id in input ad una funzione
    private boolean isParameter; //true se in una funzione l'id e' un input

    public IdNode(String i, boolean isF, boolean isIF, boolean isP) {
        id=i;
        isField=isF;
        isInputFun=isIF;
        isParameter=isP;
    }

    public String getId() { return id; }

    public STentry getEntry() { return entry; }

    public int getNestinglevel() { return nestinglevel; }

    public boolean getIsField() { return isField; }

    public int getFieldOffset(){ return fieldOffset; }

    public boolean getIsObject() { return isObject; }

    public int getObjectOffset(){ return objectOffset; }

    @Override
    public String toPrint(String s) {
        //stampo il nome della variabile, il NL nel quale la sto richiamando, l'entry della variabile
        return s+"Id: " + id + " at nestlev " + nestinglevel +"\n" + entry.toPrint(s+"  ") ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        //ricavo il NL dell'ultima ST
        int j = env.getNestingLevel();

        //cerco la variabile nella ST partendo da quella di NL maggiore fino a NL 0
        STentry tmp = null;
        while (j >= 0) {
            tmp = (env.getSymTable().get(j--)).get(id);
            //caso in cui trovo la variabile
            if (tmp != null){
                //salvo entry e NL della variabile trovata
                entry = tmp;
                nestinglevel = env.getNestingLevel();
                //se l'id corrisponde ad un oggetto mi salvo il suo offset
                if(entry.getType() instanceof ObjectTypeNode){
                    isObject = true;
                    objectOffset = entry.getOffset();
                }
                //se l'id corrisponde ad un campo mi salvo il suo offset
                if(isField)
                    fieldOffset = entry.getOffset();
                break;
            }
        }

        //se non ho trovato la variabile restituisco errore
        if (tmp == null)
            res.add(new SemanticError("Id " + id + " not declared"));

        return res;
    }

    @Override
    public Node typeCheck() {
        //se l'ID si riferisce ad una funzione allora restituisco errore
        //(è un altro il nodo che gestisce questo caso)
        if (entry.getType() instanceof ArrowTypeNode) { //
            System.out.println("Wrong usage of function identifier");
            System.exit(0);
        }
        //restituisco il tipo della variabile a cui l'id si riferisce
        return entry.getType();
    }

    public String codeGeneration() {

        String getAR="";
        for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++)
            getAR += "lw\n";

        //caso in cui id e' un campo
        if(isField){
            if(isObject){
                return "lop\n"                          //offset dell'oggetto su cui si chiama il metodo
                    + "ldp\n"                           //carico il punto in cui inizia l'oggetto su cui chiamo il metodo
                    + "push " + fieldOffset + "\n"      //carico l'offset del campo da leggere
                    + "add\n"                           //carico l'indirizzo al quale si trova l'oggetto da leggere
                    + "lw\n";                           //carico l'inidirizzo in cui inizia l'oggetto
            }
            else {
                return "lop\n"                      //carico sullo stack $op (offset dell'oggetto)
                    + "ldp\n"                       //carico sullo stack $dp leggendo dp[pop()]
                    + "push " + fieldOffset + "\n"  //carico sullo stack l'offset del campo
                    + "add\n"                       //$dp + offsetCampo = indirizzo dello heap dove si trova il campo
                    + "lw\n";                       //carico sullo stack il contenuto del campo
            }
        }

        //caso in cui id e' una variabile
        else if(!isObject){
            return "push " + entry.getOffset() + "\n"   //metto l'offset della variabile sullo stack
                + "lfp\n"                               //carico $fp
                + getAR                                 //risalgo la catena statica
                + "add\n"                               //offsetVariabile + $fp = posizione della variabile nello stack
                + "lw\n";                               //carico sullo stack il valore della variabile
        }

        //caso in cui id e' un oggetto
        else{
            //caso in si sta passando l'id in input a qualcosa
            if(isInputFun)
                return "push " + objectOffset + "\n";    //carico l'offset dell'oggetto

            //caso in cui in una funzione l'id e' un input
            else if (isParameter)
                return "push " + objectOffset + "\n"    //carico l'offset del parametro
                    + "lfp\n"                           //carico il $fp
                    + "add\n"                           //carico l'indirizzo dove si trova l'offset dell'oggetto
                    + "lw\n"                            //carico l'offset dell'oggetto
                    + "ldp\n" ;                         //carico sullo stack $dp (indirizzo dello heap dove inizia l'oggetto)
                                                        // leggendo e mettendo sullo stack dp[pop()]

            //caso in cui l'id e' un oggettto dichiarato normalmente
            else
                return "push " + objectOffset + "\n"    //carico l'offset dell'oggetto
                    + "ldp\n";                          //carico sullo stack $dp (indirizzo dello heap dove inizia l'oggetto)
        }                                               // leggendo e mettendo sullo stack dp[pop()]
    }

}  