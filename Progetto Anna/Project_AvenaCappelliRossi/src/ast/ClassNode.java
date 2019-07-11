package ast;
import util.Environment;
import util.SemanticError;
import lib.FOOLlib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Nodo che implementa la dichiarazione di una classe (regola "classdec") */
public class ClassNode implements Node {

    private String classID; //nome della classe
    private String classExtended; //nome della superclasse
    private ArrayList<Node> parlist = new ArrayList<>(); //campi della classe
    private ArrayList<Node> funlist = new ArrayList<>(); //metodi della classe
    private LinkedHashMap<String, Node> pars = new LinkedHashMap<>(); //campi della classe memoizzati per nome
    private LinkedHashMap<String, Node> funs = new LinkedHashMap<>(); //meodi della classe memorizzati per nome
    private ArrayList<ClassTypeNode> classesExtended = new ArrayList<>(); //lista di superclassi della classe
    private ClassTypeNode thisClass; //entry della classe
    private HashMap<String, STentry> symTNL0 = new HashMap<>(); //symbol table (solo NL 0)
    private int classOffset;

    //hashmap in cui ci salviamo i nomi e le label dei metodi delle superclassi
    //in modo da non incrementare la label dei metodi delle superclassi o sovrascritti
    private LinkedHashMap<String, String> labelMethods = new LinkedHashMap<>();


    public ClassNode(String className){
        classID=className;
        classExtended="";
    }

    public ClassNode(String className, String classExt){
        classID=className;
        classExtended=classExt;
    }

    public String getClassID(){ return classID; }

    public String getClassExtended() { return classExtended; }

    public ArrayList<Node> getDecs(){ return parlist; }

    public ArrayList<Node> getClassFuns(){ return funlist; }

    public ArrayList<ClassTypeNode> getClassesExtended() { return classesExtended; }

    public LinkedHashMap<String, Node> getPars(){ return pars; }

    public LinkedHashMap<String, Node> getFuns(){ return funs; }

    public ClassTypeNode getThisClass(){ return thisClass; }

    public HashMap<String, STentry> getSymTNL0() { return symTNL0; }

    public void addPar(Node p) { parlist.add(p); }

    public void addFun(Node f){ funlist.add(f); }

    public void setLabelMethods(LinkedHashMap<String, String> hm) {
        if(hm != null) {
            for (Map.Entry<String, String> lM : hm.entrySet())
                labelMethods.put(lM.getKey(), lM.getValue());
        }
    }

    public LinkedHashMap<String, String> getLabelMethods() { return labelMethods; }

    public int getMaxOffset(LinkedHashMap<String, Integer> hm) {
        int max=0;
        for(Integer offset : hm.values()) {
            if(offset > max)
                max = offset;
        }

        return max;
    }

    @Override
    public String toPrint(String s) {
        //memorizzo i campi
        String decsstr="";
        if(parlist.size()>0) {
            for (Node d : parlist){
                decsstr+=d.toPrint("");
            }
        }
        //memorizzo i metodi
        String funstr="";
        for (Node fun:funlist)
            funstr+=fun.toPrint(" ");

        //se la classe ne estende un'altra stampo anche il nome della superclasse
        if(classExtended=="")
            return "\nClass: " + classID  + "\n" + decsstr + funstr ;
        else
            return "\nClass: " + classID + " extends " + classExtended + "\n" + decsstr + funstr ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<>();

        //memorizzo la ST a NL attuale (sarà sempre NL=0)
        HashMap<String,STentry> hm = env.getSymTable().get(env.getNestingLevel());
        //cerco un'entry nella ST con lo stesso nome della classe, se non c'è viene creata
        STentry entry = hm.get(classID);
        classOffset = entry.getOffset();

        //PRIMA PASSATA CLASSI: creazione dell'entry della classe nella ST con nome, campi e meotdi
        //(non si considera se è una sottocolasse)
        if(env.getCheckStep() == 1) {
            //memorizzo la lista dei tipi dei campi della classe
            if(parlist.size()>0) {
                for (Node p : parlist) {
                    ParNode arg = (ParNode) p;
                    pars.put(arg.getId(), arg.getType());
                }
            }

            //in questo punto ho i campi in parlist, i tipi dei campi in partype e ho fatto il checksemantics dei parametri
            //ora devo fare il checksemantics dei metodi, memorizzare i nomi dei metodi e i tipi dei metodi
            ArrayList<Node> parFuns;
            for (Node f : funlist) {
                FunNode fn = (FunNode) f;
                parFuns = new ArrayList<>();
                for (Node p : fn.getParlist()) {
                    ParNode pn = (ParNode) p;
                    parFuns.add(pn.getType());
                }
                funs.put(fn.getId(), new ArrowTypeNode(parFuns, fn.getType()));
            }
            //inserisco nell'entry della classe il tipo ClassTypeNode con nome classe, campi e metodi
            thisClass = new ClassTypeNode(classID, pars, funs);
            entry.addType(thisClass);
            //System.out.println("Offset classe " + classID + ": " + entry.getOffset());
        }

        //SECONDA PASSATA CLASSI: analizzo, se presente, la superclasse ed aggiungo le sue informazioni
        // all'entry della classe
        else if(env.getCheckStep() == 2){
            //caso in cui la classe ne estende un'altra
            if (classExtended != "") {
                //se la superclasse non è presente nella ST segnalo errore
                if (!hm.containsKey(classExtended))
                    res.add(new SemanticError("Extended Class id " + classExtended + " not declared"));
                //se la classe estende sè stessa sgnalo errore di ereditarietà ciclica
                else if (classExtended.equals(classID))
                    res.add(new SemanticError("Cyclic inheritance involving " + classID));
                //la superclasse e' presente nella ST
                else {
                    //memorizzo la classe estesa dalla ST
                    ClassTypeNode extendedClass = (ClassTypeNode) hm.get(classExtended).getType();

                    //controllo se la sottoclasse non sia già stata dichiarata nella tabella dei simboli:
                    //nel caso in cui è già stata dichiarata, non è presente in nessuna entry della tabella dei simboli
                    //per cui non occorre cambiare il suo tipo
                    if (env.getOffset() == entry.getOffset()) {
                        //extendedClass è il tipo dell'entry della classe estesa
                        //classesExtended è l'array delle classi che la classe estende
                        //aggiungo extendedClass all'array classesExtended
                        classesExtended.add(extendedClass);

                        //memorizzo la lista dei tipi dei campi della classe estesa
                        //memorizzo la lista dei tipi dei metodi della classe estesa
                        //sostituisco la entry precedente della classe nella ST con la nuova in cui sono presenti anche
                        //le informazioni sulla superclasse
                        STentry newEntry = new STentry(entry.getNestinglevel(), new ClassTypeNode(classID, pars, funs, classesExtended), entry.getOffset());
                        hm.remove(classID);
                        hm.put(classID, newEntry);
                    }
                }
            }
            env.incrementOffset(); //incremento l'offset
        }

        //TERZA PASSATA CLASSI: si aggiungono (se esistono) all'array classesExtended di ogni sottoclasse le superclassi
        //della sua superclasse e si verifica che non ci siano errori di ereditarietà ciclica.
        //al termine della terza passata eseguo il CS del corpo della classe
        else if(env.getCheckStep() == 3){
            //il controllo dell'ereditarietà ciclica tra classi e superclassi avviene in questo modo:
            //1-si controlla la classe estesa dalla classe attuale
            //2-se la classe estesa (superclasse) estende una classe che ha lo stesso nome della classe attuale
            //torna l'errore di ereditarietà ciclica
            //durante la seconda passata delle classi, il controllo dell'ereditarietà ciclica avviene dalla
            //superclasse a cui si accede per ultima e si ritorna alla prima sottoclasse da cui è iniziato il controllo

            //caso in cui esiste una superclasse
            if (classExtended != "") {
                ClassTypeNode ce = (ClassTypeNode) entry.getType();
                String classNameExtended;
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(classID);
                int stop = 0;

                while (stop == 0) {
                    //controllo che esista la classe estesa
                    if (ce.getClassesExtended() == null) {
                        stop = 2;
                    } else {
                        //in ce viene memorizzata la classe estesa dalla superclasse di classID
                        //il suo nome viene memorizzato in classNameExtended
                        classNameExtended = (ce.getClassesExtended().get(0)).getClassID();
                        ce = (ClassTypeNode) hm.get(classNameExtended).getType();

                        //controllo che non ci sia ereditarietà ciclica: cioè che la classe estesa non
                        //sia già presente nell'array tmp, se già è presente vuol dire che c'è un errore
                        //di erediterietà ciclica
                        if (tmp.contains(classNameExtended))
                            stop = 1;
                        else {
                            //aggiungo la classe estesa alla lista di classi estese di classID
                            tmp.add(classNameExtended);
                            if (!classesExtended.get(0).getClassID().contains(ce.getClassID()))
                                classesExtended.add(ce);
                        }
                    }
                }

                //caso in cui ci sia un errore di ereditarietà ciclica
                if (stop == 1)
                    res.add(new SemanticError("Cyclic inheritance involving " + classID));
                    //caso in cui non ci siano errori e si può aggiungere la superclasse alla lista di superclassi
                else {
                    thisClass = new ClassTypeNode(classID, pars, funs, classesExtended);
                    STentry newEntry = new STentry(entry.getNestinglevel(), thisClass, entry.getOffset());
                    hm.remove(classID);
                    hm.put(classID, newEntry);
                }
            }
        }
        //QUARTA PASSATA
        else {
            //eseguo il CS del corpo della classe
            //creo la ST a NL=1
            env.incrementNestingLevel();
            HashMap<String, STentry> hmp = new HashMap<>();
            env.getSymTable().add(hmp);

            LinkedHashMap<String, Integer> offsetMethods = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> offsetFields = new LinkedHashMap<>();

            if(classExtended != "") {
                //System.out.println("Classe " + classID + " estende " + classExtended);
                ClassTypeNode ct = (ClassTypeNode) hm.get(classExtended).getType();
                //System.out.println("Elemento 0 di classesExtended: " + ct.getClassID()
                //        + "\noffsetMethods di " + ct.getClassID() + ": " + ct.getOffsetMethods());

                //offsetMethods = ct.getOffsetMethods();
                for (Map.Entry<String,Integer> method : ct.getOffsetMethods().entrySet()){
                    offsetMethods.put(method.getKey(), method.getValue());
                }
                //System.out.println("\noffsetMethods di " + classID + ": " + offsetMethods);
                env.setOffset(getMaxOffset(offsetMethods)+1);

                //offsetFields = ct.getOffsetFields();
                for (Map.Entry<String,Integer> field : ct.getOffsetFields().entrySet()){
                    offsetFields.put(field.getKey(), field.getValue());
                }
                env.setParOffset(getMaxOffset(offsetFields)+1);
            }
            //se la classe non estende nessuna altra classe
            else {
                //offset per i metodi
                env.setOffset(0);
                //offset per i campi
                env.setParOffset(1);
            }

            //CS campi
            if (parlist.size() > 0) {
                for (Node p : parlist) {
                    ParNode pn = (ParNode) p;
                    offsetFields.put(pn.getId(), env.getParOffset());
                    res.addAll(p.checkSemantics(env));
                    //System.out.println("OffsetFileds di " + classID + ": " + offsetFields);
                    //System.out.println("Offset campo " + pn.getId() + ": " + env.getParOffset());
                }
            }

            //CS metodi: set passata e set offset
            //PRIMA PASSATA FUNZIONI
            env.setCheckStep(1);

            //System.out.println("Classe " + classID + " -> offset di partenza " + env.getOffset());

            int offsetMethod;
            STentry entryFuns;

            for (Node f : funlist) {
                FunNode fn = (FunNode) f;
                String funName = fn.getId();
                if (hmp.containsKey(funName))
                    res.add(new SemanticError("Fun id " + funName + " already declared"));
                else{
                    if(classExtended != "" ) {
                        if(offsetMethods.containsKey(funName)) {
                            offsetMethod = offsetMethods.get(funName);
                            entryFuns = new STentry(env.getNestingLevel(), offsetMethod);
                        }
                        else {
                            entryFuns = new STentry(env.getNestingLevel(), env.getOffset());
                            offsetMethods.put(funName, entryFuns.getOffset());
                            env.incrementOffset();
                        }
                    }
                    else {
                        entryFuns = new STentry(env.getNestingLevel(), env.getOffset());
                        offsetMethods.put(funName, entryFuns.getOffset());
                        env.incrementOffset();
                    }

                    //System.out.println("offsetMethods di " + classID + ": " + offsetMethods);

                    hmp.put(funName, entryFuns);
                    res.addAll(f.checkSemantics(env));

                    //System.out.println("Offset metodo " + funName + ": " + entryFuns.getOffset());
                }
            }

            // System.out.println("offsetMethods di " + classID + ": " + offsetMethods);
            // System.out.println("OffsetFileds di " + classID + ": " + offsetFields);

            if(classExtended != "") {
                thisClass = new ClassTypeNode(classID, pars, funs, classesExtended, offsetMethods, offsetFields);
                STentry newEntry = new STentry(entry.getNestinglevel(), thisClass, entry.getOffset());
                hm.remove(classID);
                hm.put(classID, newEntry);
            }
            else {
                thisClass = new ClassTypeNode(classID, pars, funs, offsetMethods, offsetFields);
                STentry newEntry = new STentry(entry.getNestinglevel(), thisClass, entry.getOffset());
                hm.remove(classID);
                hm.put(classID, newEntry);
            }

            //SECONDA PASSATA FUNZIONI
            env.setCheckStep(2);
            for (Node f : funlist)
                res.addAll(f.checkSemantics(env));

            //close scope
            env.setOffset(entry.getOffset() - 1); //riporto l'offset all'offset dell'entry in cui è memorizzata la classe attuale
            env.getSymTable().remove(env.getNestingLevel());
            env.decrementNestingLevel();
            env.setCheckStep(4);
        }

        symTNL0 = env.getSymTable().get(0);
        return res;
    }

    @Override
    public Node typeCheck() {

        //se ci sono dei parametri, eseguo il TC per ogni parametro
        if (parlist != null)
            for (Node dec : parlist)
              dec.typeCheck();

        //TC dei metodi
        for (Node fun : funlist)
            fun.typeCheck();

        //se la classe ne estende un'altra verifico che il subtyping sia fatto correttamente
        if(classesExtended != null) {
            for(ClassTypeNode c : classesExtended) {
                if(!FOOLlib.isSubclass(thisClass, c, symTNL0)) {
                    System.out.println("Overriding in Class " + thisClass.getClassID() + " is not used correctly");
                    System.exit(0);
                }
            }
        }

        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        String classl = FOOLlib.freshClassLabel();
        String funl="";
        String labelMethodsE = "";
        String mylabelMethods = "";
        String orderedLabelMethods="";

        //arraylist in cui si memorizzano le label dei metodi della classe attuale per
        //non incrementarle nella codgen di FunNode
        ArrayList<String> funsLabelArray = new ArrayList<>();

        if(labelMethods.size() > 0) {
            for (Map.Entry<String, String> lM : labelMethods.entrySet()){
                if(!funs.containsKey(lM.getKey())) {
                    orderedLabelMethods += "push " + lM.getValue() + "\n";
                }
            }
        }

        for(Node f : funlist) {
            FunNode fn = (FunNode) f;
            funl = FOOLlib.freshMethodLabel();
            orderedLabelMethods += "push " + funl + "\n";
            funsLabelArray.add(funl);
            labelMethods.put(fn.getId(), funl);
        }

        //System.out.println("labelMethods della classe " + classID + ": " + labelMethods);
        //System.out.println("pushFunExtended " + pushFunExtended);
        //System.out.println("pushFun " + pushFun);

        //System.out.println("STRINGA: " + OrderedlabelMethods);

        FOOLlib.putCode(
                classl + ":\n"
                + "push " + classOffset + "\n"
                + orderedLabelMethods
                + "lra\n"
                + "js\n"
        );

        for(int i=0; i<funlist.size(); i++) {
            FunNode f = (FunNode) funlist.get(i);
            f.setFunLabel(funsLabelArray.get(i));
            f.codeGeneration();
        }

        return "push " + classl
            + "\n"
            + "js\n";
    }

}