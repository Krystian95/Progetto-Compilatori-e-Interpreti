package models.interpreter.instructions;

import models.interpreter.ElementBase;
import models.interpreter.CodeMemory;
import parser.CVMParser;

import static util.RegisterUtils.REGISTER_TO_INT;

public class Mult extends ElementBase {

    private final String storeRegister;
    private final String register1;
    private final String register2;

    public Mult(String storeRegister, String register1, String register2) {
        this.storeRegister = storeRegister;
        this.register1 = register1;
        this.register2 = register2;
    }

    @Override
    public void loadCode(CodeMemory env) {
        env.code[env.i++] = CVMParser.MULT;
        env.code[env.i++] = REGISTER_TO_INT.get(storeRegister);
        env.code[env.i++] = REGISTER_TO_INT.get(register1);
        env.code[env.i++] = REGISTER_TO_INT.get(register2);
    }
}
