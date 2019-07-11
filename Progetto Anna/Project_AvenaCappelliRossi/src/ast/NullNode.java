package ast;
import util.Environment;
import util.SemanticError;
import java.util.ArrayList;

/* Nodo che gestisce la regola di value "NULL" */
public class NullNode implements Node {

    private boolean isSingleNullExp;
    private boolean isInputFun;
    private int objectOffset;

    public NullNode() {}

    public NullNode(boolean isSNE, boolean isIF) {
        isSingleNullExp = isSNE;
        isInputFun = isIF;
    }

    public boolean getisSingleNullExp() { return isSingleNullExp; }

    public int getObjectOffset() { return objectOffset; }

    @Override
    public String toPrint(String s){
        return s + "null\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        //caso in cui "null" e' usato come singola espressione
        if(isSingleNullExp){
            objectOffset = env.getObjectNumber();
            env.incrementObjectNumber();
        }

        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        return new NullNode();
    }

    @Override
    public String codeGeneration(){
        //caso in cui "null" e' una espressione singola
        if (isSingleNullExp) {
            //caso in cui passo null in un CallNode
            if (isInputFun)
                return "push " + objectOffset + "\n"    //push objOffset
                    + "lhp\n"                           //push hp
                    + "alloc -1\n"                      //push sullo heap di -1
                    + "sdp\n"                           //memorizzo in dp[objOffset] l'hp (punto in cui inizia obj)
                    + "push " + objectOffset + "\n";     //push offset dell'ogggetto
            //null single exp non passata come parametro ad un CallNode
            else{
                return "push " + objectOffset + "\n"    //push objOffset
                    + "lhp\n"                           //push hp
                    + "alloc -1\n"                      //push sullo heap di -1
                    + "sdp\n"                           //memorizzo in dp[objOffset] l'hp (punto in cui inizia obj)
                    + "push " + objectOffset + "\n"     //push offset dell'ogggetto
                    + "ldp\n";                          //carico sullo stack il dp dell'obj leggendo dp[objOffset]
            }
        }
        //caso in cui null e' assegnato a qualcosa
        else
            return "lhp\n"          //push hp
                + "alloc -1\n"      //push sullo heap di -1
                + "sdp\n";          //memorizzo in dp[objOffset] l'hp (punto in cui inizia obj)

    }

}
