package models.compiler.types;

import models.compiler.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class Bool extends TypeReferenceable {


    public Bool() {
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public Type typeCheck() {
        return null;
    }

    public String getType(){
        return "bool";
    }
}  