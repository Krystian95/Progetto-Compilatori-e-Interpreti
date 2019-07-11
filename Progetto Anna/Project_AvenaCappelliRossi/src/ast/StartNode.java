package ast;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Nodo che gestisce la regola start */
public class StartNode implements Node {

    private ArrayList<Node> classdec; //lista con le dichiarazioni di classe
    private Node prog; //programma
    private HashMap<String, STentry> symTNL0; //symbol table nesting level 0

    public StartNode(ArrayList<Node> c, Node p){
        classdec=c;
        prog=p;
    }

    public Node getProg(){ return prog; }

    public ArrayList<Node> getClassdec(){ return classdec; }

    public int indexOfClassdec(String className) {
        for(int i=0; i<classdec.size(); i++) {
            ClassNode cn = (ClassNode) classdec.get(i);
            if(cn.getClassID().equals(className))
                return i;
        }

        return -1;
    }

    @Override
    public String toPrint(String s){
       String classdecstr="";
        if (classdec!=null)
            for (Node c : classdec)
                classdecstr += c.toPrint(s + "  ");
        return "Start\n" + classdecstr + prog.toPrint(s+"");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<>();

        //creo la ST a nestingLevel 0: contiene le dichiarazioni di classi e le variabili d'ambiente
        env.incrementNestingLevel();
        HashMap<String,STentry> hm = new HashMap<String,STentry> ();
        env.getSymTable().add(hm);

        //CS delle dichiarazioni delle classi
        if(classdec.size() > 0) {

            env.setOffset(0); //resetto l'offset delle entry della ST di NL=0

            //PRIMA PASSATA DELLE CLASSI:
            // - creazione dell'entry della classe con nome, NL, offset
            // - CS della classe: inserimento di nomi e tipi di campi, nomi e tipi di metodi (non è eseguito il loro CS)
            // - non è considerato se la classe ne estende un'altra
            env.setCheckStep(1);
            STentry entry; //creo l'entry della classe

            //per ogni classe dichiarata riempio l'entry e la aggiungo alla ST
            for (Node c: classdec) {
                ClassNode cn = (ClassNode) c;
                entry = new STentry(env.getNestingLevel(), env.getOffset());
                //se e' gia' stata dichiarata una classe con lo stesso nome segnalo errore
                if(hm.containsKey(cn.getClassID())) {
                    res.add(new SemanticError("Class id " + cn.getClassID() + " already declared"));
                    //System.out.println("Offset " + entry.getOffset());
                }
                //inserisco l'entry della ST, decremento l'offset ed eseguo il CS della classe:
                //inserimento di nomi e tipi di campi e metodi all'entry della classe
                else {
                    env.incrementOffset();
                    hm.put(cn.getClassID(), entry);
                    res.addAll(c.checkSemantics(env));
                }
            }
            env.setOffset(0); //resetto l'offset della ST

            //SECONDA PASSATA DELLE CLASSI: CS delle classi dove
            // - si esegue il CS dei campi della classe
            // - se la classe non ne estende un'altra: si esegue il CS dei metodi
            // - se la classe ne estende un'altra:
            //    - si controlla che l'extends sia corretto (esistenza classe estesa e ereditarietà ciclica)
            //    - si aggiungono all'entry della classe i campi e i metodi della classe estesa
            //    - si effettua il CS dei metodi della classe
            env.setCheckStep(2);
            for (Node c : classdec) {
                res.addAll(c.checkSemantics(env));
            }
            env.setOffset(0); //resetto l'offset della ST

            //TERZA PASSATA DELLE CLASSI: CS di ogni classe dove si aggiungono metodi e campi di tutte le
            //classi estese dalla classe
            env.setCheckStep(3);
            for (Node c : classdec) {
                res.addAll(c.checkSemantics(env));
            }
            env.setOffset(0); //resetto l'offset della ST

            //QUARTA PASSATA DELLE CLASSI: CS metodi e campi delle classi partendo dalla prima superclasse
            //della classe attuale
            env.setCheckStep(4);
            //in tmp si memorizzano i nomi delle classi di cui già abbiamo fatto il CS
            ArrayList<String> tmp = new ArrayList<>();
            //indice della classe estesa all'interno dell'arraylist classdec
            int index;
            String className="";

            for (Node cd : classdec) {
                ClassNode c = (ClassNode) cd;
                if(!tmp.contains(c.getClassID())) {
                    if(c.getClassesExtended() != null) {
                        for(int i=c.getClassesExtended().size()-1; i>=0; i--) {
                            className = c.getClassesExtended().get(i).getClassID();
                            if(!tmp.contains(className)) {
                                tmp.add(className);
                                index = indexOfClassdec(className);
                                res.addAll(classdec.get(index).checkSemantics(env));
                            }
                        }
                        tmp.add(c.getClassID());
                        res.addAll(c.checkSemantics(env));
                    }
                    else {
                        tmp.add(c.getClassID());
                        res.addAll(c.checkSemantics(env));
                    }
                }
            }

            symTNL0 = env.getSymTable().get(0);

            /*for(Node cd : classdec){
                ClassNode c = (ClassNode) cd;
                ClassTypeNode ctn = (ClassTypeNode) env.getSymTable().get(0).get(c.getClassID()).getType();
                System.out.println("\noffsetMethods di " + ctn.getClassID() + ": " + ctn.getOffsetFields());
            }*/
        }

        env.setOffset(-2); //resetto l'offset della ST

        //CS del prog
        res.addAll(prog.checkSemantics(env));

        //al termine del CS dell'intero programma rimuovo la ST al NL 0
        env.getSymTable().remove(env.getNestingLevel());
        env.decrementNestingLevel();

        return res;
    }

    @Override
    public Node typeCheck(){
        //se ci sono dichiarazioni di classi eseguo il TC per ogni dichiarazione
        if(classdec.size()>0) {
            for(Node c: classdec)
                c.typeCheck();
        }
        //TC del programma
        //caso in cui l'exp del programma sia "null";
        Node progType = prog.typeCheck();
        if (progType == null){
            System.out.println("null exp not admitted");
            System.exit(0);
        }
        return progType;
    }

    @Override
    public String codeGeneration() {
        String classlCode = "";

        if(classdec.size() > 0) {
            //in tmp si memorizzano i nomi delle classi di cui già abbiamo fatto la codegen
            ArrayList<String> tmp = new ArrayList<>();
            //indice della classe estesa all'interno dell'arraylist classdec
            int index;
            int indexE = - 1;
            String className;
            boolean contain;

            ClassTypeNode ct;
            String funl = "";

            //hashmap in cui ci salviamo i nomi e le label dei metodi delle superclassi
            //in modo da non incrementare la label dei metodi delle superclassi o sovrascritti
            LinkedHashMap<String, String> labelMethods = new LinkedHashMap<>();

            for (Node cd : classdec) {
                ClassNode c = (ClassNode) cd;
                contain = false;
                //System.out.println("\nCode generation di : " + c.getClassID() + "\n");
                if (!tmp.contains(c.getClassID())) {
                    if (c.getClassesExtended().size() > 0) {
                        for (int i = c.getClassesExtended().size() - 1; i >= 0; i--) {
                            className = c.getClassesExtended().get(i).getClassID();
                            if (!tmp.contains(className)) {
                                tmp.add(className);
                                index = indexOfClassdec(className);
                                ClassNode sC = (ClassNode) classdec.get(index);
                                if(sC.getClassesExtended().size() > 0) {
                                    className = sC.getClassesExtended().get(0).getClassID();
                                    if(tmp.contains(className)) {
                                        index = indexOfClassdec(className);
                                        ClassNode sCE = (ClassNode) classdec.get(index);
                                        labelMethods.putAll(sCE.getLabelMethods());
                                        //System.out.println("LabelMethods di " + sCE.getClassID() + ": " + sCE.getLabelMethods());
                                    }
                                }
                                sC.setLabelMethods(labelMethods);
                                //System.out.println("LabelMethods di " + sC.getClassID() + " prima della codegen: " + sC.getLabelMethods());
                                classlCode += sC.codeGeneration();
                                labelMethods.putAll(sC.getLabelMethods());
                                //System.out.println("LabelMethods di " + sC.getClassID() + " dopo la codegen: " + sC.getLabelMethods());
                            }
                            else {
                                if(i==0) {
                                    //System.out.println("Mi trovo nella classe estesa " + className);
                                    contain = true;
                                    indexE = indexOfClassdec(className);
                                    //System.out.println("Offset di " + className + ": " + indexE);
                                }
                            }
                        }
                        if(contain) {
                            ClassNode sC = (ClassNode) classdec.get(indexE);
                            //System.out.println("LabelMethods di " + sC.getClassID() + ": " + sC.getLabelMethods());
                            c.setLabelMethods(sC.getLabelMethods());
                            tmp.add(c.getClassID());
                            classlCode += c.codeGeneration();
                            labelMethods = new LinkedHashMap<>();
                        }
                        else {
                            c.setLabelMethods(labelMethods);
                            //System.out.println("LabelMethods di " + c.getClassID() + " prima della codegen: " + c.getLabelMethods());
                            tmp.add(c.getClassID());
                            classlCode += c.codeGeneration();
                            labelMethods = new LinkedHashMap<>();
                            //labelMethods.putAll(c.getLabelMethods());
                            //System.out.println("LabelMethods di " + c.getClassID() + " dopo la codegen: " + labelMethods);
                        }
                    }
                    else {
                        //System.out.println("LabelMethods di " + c.getClassID() + " prima della codegen: " + c.getLabelMethods());
                        tmp.add(c.getClassID());
                        classlCode += c.codeGeneration();
                        //labelMethods.putAll(c.getLabelMethods());
                        //System.out.println("LabelMethods di " + c.getClassID() + " dopo la codegen: " + c.getLabelMethods());
                    }
                }
            }

            /*for (Node c : classdec) {
                ClassNode cd = (ClassNode) c;
                //System.out.println("LabelMethods di " + cd.getClassID() + ": " + cd.getLabelMethods());
            }*/
        }

        return  classlCode
                + prog.codeGeneration()
                + "halt\n"
                + FOOLlib.getCode();
    }
}
