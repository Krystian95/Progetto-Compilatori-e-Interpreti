package ast;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* Nodo che gestisce il tipo di una classe, tipo che è inserito nella ST*/
public class ClassTypeNode implements Node{

    private String classID; //nome della classe
    private LinkedHashMap<String, Node> parlist; //lista dei campi della classe
    private LinkedHashMap<String, Node> funlist; //lista dei metodi della classe
    private ArrayList<ClassTypeNode> classesExtended; //array con le superclassi della classe
    private LinkedHashMap<String, Integer> offsetMethods; //hm che contiene i nomi dei metodi della classe (propri e delle supercalssi) con i loro offset
    private LinkedHashMap<String, Integer> offsetFields; //hm che contiene i nomi dei campi della classe (propri e delle superclassi) con i loro offset

    public ClassTypeNode(String id, LinkedHashMap<String, Node> p, LinkedHashMap<String, Node> f) {
        classID=id;
        parlist=p;
        funlist=f;
        classesExtended=null;
    }

    public ClassTypeNode(String id, LinkedHashMap<String, Node> p, LinkedHashMap<String, Node> f, LinkedHashMap<String, Integer> oM, LinkedHashMap<String, Integer> oF) {
        classID=id;
        parlist=p;
        funlist=f;
        classesExtended=null;
        offsetMethods=oM;
        offsetFields=oF;
    }

    public ClassTypeNode(String id, LinkedHashMap<String, Node> p, LinkedHashMap<String, Node> f,  ArrayList<ClassTypeNode> cE) {
        classID=id;
        parlist=p;
        funlist=f;
        classesExtended=cE;
    }

    public ClassTypeNode(String id, LinkedHashMap<String, Node> p, LinkedHashMap<String, Node> f, ArrayList<ClassTypeNode> cE, LinkedHashMap<String, Integer> oM, LinkedHashMap<String, Integer> oF) {
        classID=id;
        parlist=p;
        funlist=f;
        classesExtended=cE;
        offsetMethods=oM;
        offsetFields=oF;
    }

    public String getClassID(){ return classID; }

    public LinkedHashMap<String, Node> getParlist() { return parlist; }

    public LinkedHashMap<String,Node> getFunlist() { return funlist; }

    public ArrayList<ClassTypeNode> getClassesExtended() { return classesExtended; }

    public LinkedHashMap<String, Integer> getOffsetMethods() { return offsetMethods; }

    public LinkedHashMap<String, Integer> getOffsetFields() { return offsetFields; }


    public String toPrint(String s){
        //aggiungo i campi all'output
        String parlstr = "";
        for (Map.Entry par : parlist.entrySet()) {
            Node pt = (Node) par.getValue();
            parlstr += par.getKey() + "\n" + pt.toPrint("  ");
        }
        //aggiungo i metodi all'output
        String funlstr="";
        for (Map.Entry fun : funlist.entrySet()) {
            Node at = (Node) fun.getValue();
            funlstr += fun.getKey() + "\n" + at.toPrint(" " );
        }

        //se non ci sono delle superclassi
        if(classesExtended == null)
            return s + "ClassType\n" + parlstr + funlstr + " -> " + classID + "\n";

        //se ci sono superclassi
        else {
            String tmp="";
            String parElstr;
            String funElstr;
            String classExtended;
            LinkedHashMap<String, Node> parlistE;
            LinkedHashMap<String, Node> funlistE;

            //per ogni superclasse
            for(ClassTypeNode ct : classesExtended) {
                //memorizzo il nome della classe
                classExtended = ct.getClassID();

                //memorizzo i campi della classe
                parElstr = "";
                parlistE = ct.getParlist();
                for (Map.Entry par : parlistE.entrySet()) {
                    Node pt = (Node) par.getValue();
                    parElstr += par.getKey() + "\n" + pt.toPrint("  ");
                }

                //memorizzo i metodi della classe
                funElstr = "";
                funlistE = ct.getFunlist();
                for (Map.Entry fun : funlistE.entrySet()) {
                    Node at = (Node) fun.getValue();
                    funElstr += fun.getKey() + "\n" + at.toPrint(" ");
                }

                //metto insieme tutti i pezzi della superclasse da stampare
                tmp += "ClassExtended " + classExtended + "\nClassType\n" + parElstr + funElstr + " -> " + classExtended + "\n";
            }
            //aggiungo le imnformazioni delle superclassi in coda a quelle della classe
            return s + "ClassType\n" + parlstr + funlstr + " -> " + classID + "\n" + tmp;
        }
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env){
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck(){
        return null;
    }

    @Override
    public String codeGeneration(){ return ""; }
}
