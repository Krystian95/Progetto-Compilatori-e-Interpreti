package models.interpreter.instructions;

import models.interpreter.ElementBase;
import models.interpreter.CodeMemory;
import parser.CVMParser;

import static util.RegisterUtils.REGISTER_TO_INT;

public class Move extends ElementBase {

    private final String srcRegister;
    private final String destRegister;

    public Move(String srcRegister, String destRegister) {
        this.srcRegister = srcRegister;
        this.destRegister = destRegister;
    }

    @Override
    public void loadCode(CodeMemory env) {
        env.code[env.i++] = CVMParser.MOVE;
        env.code[env.i++] = REGISTER_TO_INT.get(srcRegister);
        env.code[env.i++] = REGISTER_TO_INT.get(destRegister);
    }

    public String getSrcRegister() {
        return srcRegister;
    }

    public String getDestRegister() {
        return destRegister;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName()+" "+ srcRegister +" " + destRegister + "\n";
    }
}
