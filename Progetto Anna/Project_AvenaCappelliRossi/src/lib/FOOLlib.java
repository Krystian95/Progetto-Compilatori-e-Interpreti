package lib;
import ast.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FOOLlib {

    //contatori per le labael e rispettivi generatori
    private static int labCount=0;
    private static int funLabCount=0;
    private static int classLabCount=0;
    private static int methodLabCount=0;
    public static String freshLabel() {
        return "label"+(labCount++);
    }
    public static String freshFunLabel() {
        return "function"+(funLabCount++);
    }
    public static String freshClassLabel() { return "class"+(classLabCount++); }
    public static String freshMethodLabel() { return "method"+(methodLabCount++); }

    //funzioni per inserire in coda al codice generato il codice passato in input
    private static String funCode="";
    public static void putCode(String c) { funCode += "\n" + c; }
    public static String getCode() {
        return funCode;
    }

    //valuta se il tipo "a" e' <= al tipo "b", dove "a" e "b" possono essere tipi base (int, bool o void), oggetti o null
    public static boolean isSubtype(Node a, Node b) {
        return ( (a instanceof NullNode) && (b instanceof ObjectTypeNode) ) ||
            ( a.getClass().equals(b.getClass()) ) ||
            ( (a instanceof BoolTypeNode) && (b instanceof IntTypeNode) );
    }

    //valuta se la classe c1 è sottoclasse della classe c2
    public static boolean isSubclass(ClassTypeNode c1, ClassTypeNode c2, HashMap<String, STentry> symT) {
        boolean isSC = true;
        //se c1 e c2 sono la stessa classe restituisco true
        if ( c1.getClassID().equals(c2.getClassID()))
            return true;
        //se c1 non estende niente restituisco false
        else if ( c1.getClassesExtended() == null)
            return false;
        //c1 e c2 non sono la stessa classe e c1 ha delle superclassi
        else{
            //cerco se tra le superclassi di c1 c'e' c2, se non c'e' restituisco false
            boolean find = false;
            for (ClassTypeNode cc : c1.getClassesExtended()){
                if (cc.getClassID().equals(c2.getClassID())){
                    find = true;
                    break;
                }
            }
            if(!find)
                return false;

            //se c1 estende c2 controllo che non ci sia ovverriding di campi
            LinkedHashMap<String, Node> fields = c1.getParlist();
            //considero ogni campo di c1 (ipotetica sottoclasse)
            for(Map.Entry<String, Node> p : fields.entrySet()) {
                //verifico se il campo di c1 ha lo stesso nome di un campo di c2
                //se ha lo stesso nome restituisco errore perchè l'overriding di campi non è consentito
                if(c2.getParlist().get(p.getKey()) != null)
                    return false;
            }

            //se c1 estende c2 controllo che i metodi siano compatibili
            LinkedHashMap<String, Node> methods = c1.getFunlist();
            //considero ogni metodo di c1 (ipotetica sottoclasse)
            for(Map.Entry<String,Node> m : methods.entrySet() ){
                //verifico se il metodo di c1 ha lo stesso nome di un metodo di c2
                if (c2.getFunlist().get(m.getKey()) != null){
                    //se il metodo m di c1 non è sottotipo del metodo m di c2 restituisco false
                    isSC = isSubfun((ArrowTypeNode) m.getValue(), (ArrowTypeNode) c2.getFunlist().get(m.getKey()), symT, c1, c2);
                    if ( !isSC )
                        break;
                }
            }

            //se arrivo in questo punto allora c1<=c2 quindi posso restutuire true
            return isSC;
        }
    }

    //valuta se la funzione f1 e' sottotipo della funzione f2
    public static boolean isSubfun(ArrowTypeNode f1, ArrowTypeNode f2, HashMap<String, STentry> symT, ClassTypeNode c1, ClassTypeNode c2){
        //verifico che: tipo di f1 <: tipo di f2
        if ( (f1.getReturn() instanceof ObjectTypeNode) && (f2.getReturn() instanceof ObjectTypeNode)){
            //recupero i tipi di ritorno di f1 e di f2
            ObjectTypeNode f1ReturnType = (ObjectTypeNode) f1.getReturn();
            ObjectTypeNode f2ReturnType = (ObjectTypeNode) f2.getReturn();
            //recupero le entry delle classi relative ai tipi di ritorno di f1 e di f2
            if (!( symT.containsKey(f1ReturnType.getClassName()) || symT.containsKey(f2ReturnType.getClassName()) ) )
                return false;
            ClassTypeNode f1ReturnClass = (ClassTypeNode) (symT.get(f1ReturnType.getClassName()).getType());
            ClassTypeNode f2ReturnClass = (ClassTypeNode) (symT.get(f2ReturnType.getClassName()).getType());
            //controllo che le classi di ritorno di f1 e f2 non coincidano con c1 e c2
            if (!(f1ReturnClass.getClassID().equals(c1.getClassID()) && f2ReturnClass.getClassID().equals(c2.getClassID()))){
                //verifico che la classe di ritorno di f1 sia <: della classe di ritorno di f2
                if( !isSubclass( f1ReturnClass, f2ReturnClass, symT) )
                    return false;
            }
        }
        else if (!isSubtype(f1.getReturn(), f2.getReturn())){
            return false;
        }
        //verifico che: n. di parametri di f1 = n. di parametri di f2
        if (f1.getParList().size() != f2.getParList().size()) {
            return false;
        }
        int i = 0;
        boolean isSF = true;
        //considero i parametri di f2 e di f1 e controllo: parametro di f2 <: parametro di f1 corrispondente
        while (i < f2.getParList().size()){
            if(f2.getParList().get(i) instanceof ObjectTypeNode && f1.getParList().get(i) instanceof ObjectTypeNode){
                //recupero il tipo del parametro di f1 e del relativo parametro di f2
                ObjectTypeNode f1ParType = (ObjectTypeNode) f1.getParList().get(i);
                ObjectTypeNode f2ParType = (ObjectTypeNode) f2.getParList().get(i);
                //recupero le entry delle classi relative ai tipi trovati sopra
                if (!( symT.containsKey(f1ParType.getClassName()) || symT.containsKey(f2ParType.getClassName()) ) )
                    return false;
                ClassTypeNode f1ParTypeClass = (ClassTypeNode) (symT.get(f1ParType.getClassName()).getType());
                ClassTypeNode f2ParTypeClass = (ClassTypeNode) (symT.get(f2ParType.getClassName()).getType());
                //controllo che le classi di ritorno di f1 e f2 non coincidano con c1 e c2
                if (!(f2ParTypeClass.getClassID().equals(c1.getClassID()) && f1ParTypeClass.getClassID().equals(c2.getClassID()))){
                    //verifico che la classe del parametro di f2 sia <: della classe del parametro di f1 corrispondente
                    if( !isSubclass( f2ParTypeClass, f1ParTypeClass, symT) )
                        return false;
                }
            }
            else
                isSF = isSubtype(f2.getParList().get(i), f1.getParList().get(i));

            i++;
            if(!isSF)
                break;
        }
        return isSF;
    }

    //Data la ST, il NL attuale e un nodo, la funzione restituisce il tipo classe del nodo se questo e' un oggetto
    public static ClassTypeNode obtainNodeClass(ArrayList<HashMap<String,STentry>> symT, int NL, Node node) {

        //dichiaro l'oggetto ClassTypeNode da restituire in output
        ClassTypeNode ctn = null;

        //caso in cui il nodo in input e' un id
        if (node instanceof IdNode) {
            STentry varEntry = null;
            String idName = ((IdNode) node).getId();
            //cerco l'entry dell'id nella ST dal NL più esterno a NL = 0
            int j = NL;
            while (j >= 0 && varEntry == null) {
                varEntry = (symT.get(j--)).get(idName);
            }
            //se l'entry dell'id esiste ed è di tipo ObjectTypeNode
            if (varEntry != null && (varEntry.getType() instanceof ObjectTypeNode)) {
                //cerco l'entry della classe dell'oggetto a NL 0
                String objClassName = ((ObjectTypeNode) varEntry.getType()).getClassName();
                HashMap<String, STentry> classEntryT = symT.get(0);
                //se trovo la classe dell'oggetto la memorizzo in ctn
                if (classEntryT.containsKey(objClassName))
                    ctn = (ClassTypeNode) classEntryT.get(objClassName).getType();
            }
        }
        //caso in cui il nodo in input e' una chiamata di funzione
        else if (node instanceof CallNode) {
            //trovo il tipo della funzione
            ArrowTypeNode cn = (ArrowTypeNode) ((CallNode) node).getFunType();
            //caso in cui il valore di ritorno della funzione e' un oggetto
            if ( cn.getReturn() instanceof ObjectTypeNode) {
                //cerco l'entry della classe dell'oggetto a NL 0
                String objClassName = ((ObjectTypeNode) cn.getReturn()).getClassName();
                HashMap<String, STentry> classEntryT = symT.get(0);
                //se trovo la classe dell'oggetto la memorizzo in ctn
                if (classEntryT.containsKey(objClassName))
                    ctn = (ClassTypeNode) classEntryT.get(objClassName).getType();
            }
        }
        //caso in cui il nodo in input e' una chiamata di metodo
        else if (node instanceof MethodcallNode) {
            //trovo il tipo del valore di ritorno del metodo
            Node t = ((MethodcallNode) node).getIdType();
            //caso in cui il metodo ritorna un oggetto
            if (t instanceof ObjectTypeNode) {
                //cerco l'entry della classe dell'oggetto a NL 0
                ObjectTypeNode tt = (ObjectTypeNode) ((MethodcallNode) node).getIdType();
                String objClassName = tt.getClassName();
                HashMap<String, STentry> classEntryT = symT.get(0);
                //se trovo la classe dell'oggetto la memorizzo in ctn
                if (classEntryT.containsKey(objClassName))
                    ctn = (ClassTypeNode) classEntryT.get(objClassName).getType();
            }
        }
        //caso in cui il nodo in input e' un newNode
        else if (node instanceof NewNode){
            //trovo il nome della classe dell'oggetto e la sua entry nella ST
            String nn = ((NewNode) node).getNewClass().getClassID();
            HashMap<String, STentry> classEntryT = symT.get(0);
            //se trovo la classe dell'oggetto la memorizzo in ctn
            if (classEntryT.containsKey(nn))
                ctn = (ClassTypeNode) classEntryT.get(nn).getType();
        }
        //caso in cui il nodo in input e' un ObjectTypeNode
        else if (node instanceof ObjectTypeNode){
            //cerco l'entry della classe dell'oggetto a NL 0
            String objClassName = ((ObjectTypeNode) node).getClassName();
            HashMap<String, STentry> classEntryT = symT.get(0);
            //se trovo la classe dell'oggetto la memorizzo in ctn
            if (classEntryT.containsKey(objClassName))
                ctn = (ClassTypeNode) classEntryT.get(objClassName).getType();
        }

        return ctn;
    }

}