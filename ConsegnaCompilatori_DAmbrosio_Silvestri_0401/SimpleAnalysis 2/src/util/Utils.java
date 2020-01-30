package util;

import models.exp.SimpleExp;
import models.value.SimpleValueId;

public class Utils {

	public static String getIdFromExp(SimpleExp exp){
        if(exp instanceof SimpleValueId) return ((SimpleValueId) exp).getValue();
        return null;
    }
}
