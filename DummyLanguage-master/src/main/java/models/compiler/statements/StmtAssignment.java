package models.compiler.statements;


import com.google.common.collect.Lists;
import models.compiler.*;
import models.compiler.expressions.Exp;
import models.compiler.stentry.StEntry;
import models.compiler.stentry.VarStEntry;
import models.compiler.types.Type;
import util.SemanticError;
import util.TypeCheckException;
import util.TypeUtils;
import java.util.*;
import java.util.function.Function;
import static util.SemanticErrorChecker.*;
import static util.Strings.*;


public class StmtAssignment extends Stmt{

    private static final List<Function<StEntry, Boolean>> CHECKS = Arrays.asList(IS_NULL, VAR_IS_DELETED, FUN_IS_DELETED);
    private final Exp exp;
    private final String id;
    private int nl;
    private VarStEntry idEntry;


    public StmtAssignment(Exp exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public Type typeCheck() throws TypeCheckException {
        TypeUtils.typeCheck(this.idEntry.getType(), exp);
        return null;
    }

    @Override
    public List<SemanticError> checkSemantics(Environment e) {

        List<SemanticError> result = new ArrayList<>();
        result.addAll(checkIdSemantics(e));
        result.addAll(exp.checkSemantics(e));
        this.addAllrwAccesses(exp.getRwAccesses());

        return result;
    }

    @Override
    public String codeGeneration() {

        StringBuilder result = new StringBuilder();

        result.append(exp.codeGeneration());
        if(nl==idEntry.getNestinglevel())
            return result.append(storeW(ACC,Integer.toString(idEntry.getOffset()),FP)).toString();
        result.append(loadW(AL, "0", FP));
        result.append(getVariableForCgen(nl, idEntry.getNestinglevel()));
        result.append(storeW(ACC,Integer.toString(idEntry.getOffset()),AL));

        return result.toString();
    }


    private List<SemanticError>  checkIdSemantics(Environment e) {

        StEntry idEntry = e.getVariableValue(id);
        for (Function<StEntry, Boolean> check: CHECKS) {
            if (check.apply(idEntry))
                return Lists.newArrayList(VALIDATION_ERRORS.get(check).apply(id));
        }

        this.idEntry = e.getVariableValue(id);
        this.nl = e.getNestingLevel();
        this.addrwAccess(e.getVariableValue(id));

        return new ArrayList<>();
    }
}
