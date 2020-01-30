package util;


import models.SimpleElementBase;
import models.exp.SimpleExp;
import models.factor.SimpleFactor;
import models.term.SimpleTerm;
import models.type.SimpleType;
import models.type.SimpleTypeBool;
import models.type.SimpleTypeInt;

public class TypeCheck {

	//evaluate the type of elements
	public static boolean isEqualtype (SimpleElementBase a, SimpleType b) {
		if((a.getClass()).equals(b.getClass())){
			return true;
		} else {
			return false;
		}
	} 

	//TypeCheck for Exp
	public static SimpleType typeCheckExp(SimpleExp left, SimpleExp right, String op) {
		if (!(TypeCheck.isEqualtype(left.typeCheck(),new SimpleTypeInt()) &&
				TypeCheck.isEqualtype(right.typeCheck(),new SimpleTypeInt()) ) ) {

			System.err.println("\n>> Type Check Exp: FAILED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatchExp + op);
			System.exit(0);
		}
		return new SimpleTypeInt();
	}

	//TypeCheck for Term
	public static SimpleType typeCheckTerm(SimpleFactor left, SimpleTerm right, String op) {
		if (!(TypeCheck.isEqualtype(left.typeCheck(),new SimpleTypeInt()) &&
				TypeCheck.isEqualtype(right.typeCheck(),new SimpleTypeInt()) ) ) {
			System.err.println("\n>> Type Check Term: FAILED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatchExp + op);
			System.exit(0);
		}
		return new SimpleTypeInt();
	}


	//TypeCheck for FactorInt
	public static SimpleType typeCheckFactorInt(SimpleType left, SimpleType right, String op) {
		if (!(TypeCheck.isEqualtype(left,new SimpleTypeInt()) && TypeCheck.isEqualtype(right,new SimpleTypeInt()))) 
		{
			if (! ( TypeCheck.isEqualtype(left,right) || TypeCheck.isEqualtype(right,left) ) ) {
				System.err.println("\n>> Type Check FactorInt: FAILED");
				System.err.println("You had 1 error:");
				System.err.println(Strings.ErrorIncompatibleType + op);
				System.exit(0);
			}
			System.err.println("\n>> Type Check FactorInt: FAILED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatchExp + op);
			System.exit(0);
		}
		return new SimpleTypeBool();
	}

	//TypeCheck for FactorBool
	public static SimpleType typeCheckFactorBool(SimpleType left, SimpleType right, String op) {
		if (!(TypeCheck.isEqualtype(left,new SimpleTypeBool()) && TypeCheck.isEqualtype(right,new SimpleTypeBool()))) {
			if (! ( TypeCheck.isEqualtype(left,right) || TypeCheck.isEqualtype(right,left) ) ) {
				System.err.println("\n>> Type Check FactorInt: FAILED");
				System.err.println("You had 1 error:");
				System.err.println(Strings.ErrorIncompatibleType + op);
				System.exit(0);
			}
			System.err.println("\n>> Type Check FactorInt: FAILED");
			System.err.println("You had 1 error:");
			System.err.println(Strings.ErrorTypeMismatchFactor+op);
			System.exit(0);
		}
		return new SimpleTypeBool();
	}
}

