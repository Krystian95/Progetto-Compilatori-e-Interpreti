package models.compiler.types;

import models.compiler.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class Int extends TypeReferenceable {

    public Int() {
    }


    @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
	}

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public Type typeCheck() {
        return null;
    }

}