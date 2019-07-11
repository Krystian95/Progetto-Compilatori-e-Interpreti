package ast;

import java.util.ArrayList;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import parser.*;
import parser.FOOLParser.ClassdecContext;
import parser.FOOLParser.BaseExpContext;
import parser.FOOLParser.BoolValContext;
import parser.FOOLParser.DecContext;
import parser.FOOLParser.ExpContext;
import parser.FOOLParser.FactorContext;
import parser.FOOLParser.ArithmeticContext;
import parser.FOOLParser.DeniedContext;
import parser.FOOLParser.PrintExpContext;
import parser.FOOLParser.FunContext;
import parser.FOOLParser.FunExpContext;
import parser.FOOLParser.IfExpContext;
import parser.FOOLParser.IntValContext;
import parser.FOOLParser.LetInExpContext;
import parser.FOOLParser.SingleExpContext;
import parser.FOOLParser.TermContext;
import parser.FOOLParser.TypeContext;
import parser.FOOLParser.VarExpContext;
import parser.FOOLParser.VarasmContext;
import parser.FOOLParser.VardecContext;
import parser.FOOLParser.StartContext;
import parser.FOOLParser.NullExpContext;
import parser.FOOLParser.NewExpContext;
import parser.FOOLParser.MethodcallExpContext;
import parser.FOOLParser.FunbodyContext;
import parser.FOOLParser.AssignmentStmContext;
import parser.FOOLParser.IfStmContext;
import parser.FOOLParser.MethodcallStmContext;
import parser.FOOLParser.StmContext;

import javax.swing.*;

public class FoolVisitorImpl extends FOOLBaseVisitor<Node> {

    @Override
    public Node visitStart(StartContext ctx) {
        StartNode res;

        //dichiarazioni delle classi
        ArrayList<Node> classdeclarations = new ArrayList<Node>();
        for(ClassdecContext cc : ctx.classdec())
            classdeclarations.add(visit(cc));

        //programma
        Node prog = visit(ctx.prog());

        //dichiarazione SartNode
        res = new StartNode(classdeclarations, prog);
        return res;

    }

    @Override
    public Node visitClassdec(ClassdecContext ctx){
	    //System.out.println("visitClassdec");
        ClassNode res;

        //distinzione tra classe che non ne estende altre e sottoclasse
        if(ctx.classextended == null) {
            res = new ClassNode(ctx.classname.getText());
        } else {
           res = new ClassNode(ctx.classname.getText(), ctx.classextended.getText());
        }

        //dichiarazione campi
        for(VardecContext vc : ctx.vardec())
            res.addPar(new ParNode(vc.ID().getText(),visit(vc.type())));

        //dichiarazione metodi
        for (FunContext fun : ctx.fun()){
            res.addFun(visit(fun));
        }

        return res;
    }

	@Override
	public Node visitLetInExp(LetInExpContext ctx) {
		//System.out.println("visitLetInExp");
        ProgLetInNode res;

		//dichiarazioni di variabili
		ArrayList<Node> declarations = new ArrayList<Node>();
		for(DecContext dc : ctx.let().dec()){
			declarations.add( visit(dc) );
		}

		//il prog e' una lista di stm
		if(ctx.exp()==null){
            ArrayList<Node> stms = new ArrayList<Node>();
            for(StmContext s : ctx.stms().stm()){
                stms.add( visit(s) );
            }
            res = new ProgLetInNode(declarations, stms);
        }
        //il prog e' una exp
        else {
		    Node body = visit(ctx.exp());
            res = new ProgLetInNode(declarations, body);
        }

		return res;
	}

	@Override
	public Node visitSingleExp(SingleExpContext ctx) {
		//System.out.println("visitSingleExp");
        ProgNode res;

        //il prog e' una lista di stm
        if(ctx.exp()==null){
            ArrayList<Node> stms = new ArrayList<Node>();
            for(StmContext s : ctx.stms().stm()){
                stms.add( visit(s) );
            }
            res = new ProgNode(stms);
        }
        //il prog e' una exp
        else
            res = new ProgNode(visit(ctx.exp()));

        return res;
		
	}

	@Override
	public Node visitVarasm(VarasmContext ctx) {
        //System.out.println("visitVarasm");

		//visit type
		Node typeNode = visit(ctx.vardec().type());
		
		//visit exp
		Node expNode = visit(ctx.exp());

		//distinzione tra dichiarazione di variabile e dichiarazione di oggetto
		if(ctx.vardec().type().ID() != null)
		    return new ObjectNode(ctx.vardec().ID().getText(), typeNode, expNode);
        else
            return new VarNode(ctx.vardec().ID().getText(), typeNode, expNode);

	}
	
	@Override
	public Node visitFun(FunContext ctx) {
        //System.out.println("visitFun");
        FunNode res;

        CharStream input = ctx.getParent().start.getInputStream();
        int a = ctx.getParent().start.getStartIndex();
        int b = ctx.getParent().stop.getStopIndex();

        Interval interval = new Interval(a,b);
        int stop =  a + (input.getText(interval).indexOf("(")) -1;

        Interval interval2 = new Interval(a,stop);

        String[] className = input.getText(interval2).split(" ");
        if (className[0].equals("class")) {
            if(ctx.type()==null){
                //initialize @res with the visits to the type and its ID
                res = new FunNode(ctx.ID().getText(), new VoidTypeNode(), className[1]);
            } else {
                res = new FunNode(ctx.ID().getText(), visit(ctx.type()), className[1]);
            }
        }
        else {
            if(ctx.type()==null){
                //initialize @res with the visits to the type and its ID
                res = new FunNode(ctx.ID().getText(), new VoidTypeNode());
            } else {
                res = new FunNode(ctx.ID().getText(), visit(ctx.type()));
            }
        }

        //add argument declarations
		//we are getting a shortcut here by constructing directly the ParNode
		//this could be done differently by visiting instead the VardecContext
		for(VardecContext vc : ctx.vardec())
			res.addPar( new ParNode(vc.ID().getText(), visit( vc.type() )) );
        Node funbody =  visit(ctx.funbody());
        res.addFunbody(funbody);

		return res;
	}

    @Override
    public Node visitFunbody(FunbodyContext ctx) {
         //System.out.println("visit funbody");
        FunbodyNode res;

        ArrayList<Node> letfun = new ArrayList<Node>();
        if (ctx.children.size() > 2) {
            for (VarasmContext vc : ctx.letfun().varasm())
                letfun.add(visit(vc));
        }

        //il funbody e' una lista di stm
        if(ctx.exp()==null){
            ArrayList<Node> stms = new ArrayList<Node>();
            for(StmContext s : ctx.stms().stm()){
                stms.add( visit(s) );
            }
            if (letfun.size() > 0)
                res = new FunbodyNode(letfun, stms);
            else
                res = new FunbodyNode(stms);
        }
        //il prog e' una exp
        else {
            Node exp = visit(ctx.exp());
            if (letfun.size() > 0)
                res = new FunbodyNode(letfun, exp);
            else
                res = new FunbodyNode(exp);
        }

        return res;

    }
	
	@Override
	public Node visitType(TypeContext ctx) {
        //System.out.println("visitType");

        switch (ctx.getText()){
            case "int":
                return new IntTypeNode();
            case "bool":
                return new BoolTypeNode();
            default:
                return new ObjectTypeNode(ctx.ID().getText());
        }

	}
	
	@Override
	public Node visitExp(ExpContext ctx) {
        //System.out.println("visitExp");

		//caso espressione semplice
		if(ctx.right == null)
			return visit( ctx.left );
		//caso di espressione logica
		else
			return new LogicNode(visit(ctx.left), ctx.operator.getText(), visit(ctx.right));

	}

    @Override
    public Node visitFactor(FactorContext ctx) {
        //System.out.println("visitFactor");

        //caso espressione semplice
        if(ctx.right == null)
            return visit( ctx.left );
        //caso espressione "==", "<=" o ">="
        else
            return new EqualNode(visit(ctx.left), ctx.operator.getText(),  visit(ctx.right));

    }

    @Override
    public Node visitArithmetic(ArithmeticContext ctx) {
        //System.out.println("visitArith");

        //caso espressione semplice
        if (ctx.right == null)
            return visit(ctx.left);
        //caso espressione aritmetica con "+" o "-"
        else
            return new PlusNode(visit(ctx.left), ctx.operator.getText(), visit(ctx.right));

    }

	@Override
	public Node visitTerm(TermContext ctx) {
        //System.out.println("visitTerm");

        //caso espressione semplice
		if(ctx.right == null)
			return visit( ctx.left );
		//caso espressione aritmetica con "*" o "/"
		else
			return new MultNode(visit(ctx.left), ctx.operator.getText(), visit(ctx.right));

	}

	@Override
    public Node visitDenied(DeniedContext ctx){
	    //System.out.println("visitDenied");

        //caso espressione semplice
        if (ctx.operator == null)
	        return visit(ctx.right);
        //caso negazione di booleano
        else
	        return new DeniedNode(visit(ctx.right));

    }

	@Override
	public Node visitIntVal(IntValContext ctx) {
        //System.out.println("visitIntVal");

        //caso numero positivo
        if (ctx.MINUS()==null)
	        return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	    //caso numero negativo
        else
            return new IntNode(Integer.parseInt(ctx.INTEGER().getText()), ctx.MINUS());

	}
	
	@Override
	public Node visitBoolVal(BoolValContext ctx) {
        //System.out.println("visitBoolVal");

		return new BoolNode(Boolean.parseBoolean(ctx.getText()));
	}

	@Override
	public Node visitBaseExp(BaseExpContext ctx) {
        //System.out.println("visitBaseExp");
		
		return visit (ctx.exp());
	}
	
	@Override
	public Node visitIfExp(IfExpContext ctx) {
        //System.out.println("visitIfExp");
		IfNode res;

		Node condExp = visit (ctx.cond);
		Node thenExp = visit (ctx.thenBranch);
		Node elseExp = visit (ctx.elseBranch);
		
		res = new IfNode(condExp, thenExp, elseExp);
		
		return res;
	}
	
	@Override
	public Node visitVarExp(VarExpContext ctx) {
        //System.out.println("visitVarExp");

        ParserRuleContext rctx = ctx.getParent();
        String context = rctx.getClass().getSimpleName();
        String id = ctx.ID().getText();

        boolean find = false;
        boolean isField = false;
        boolean isInputFun = false;
        boolean isParameter = false;

        while((!context.equals("FunbodyContext")) && (!context.equals("VarasmContext")) &&
            (!context.equals("StartContext")) && (!context.equals("FunExpContext")) && (!context.equals("MethodcallExpContext"))) {
            rctx = rctx.getParent();
            context = rctx.getClass().getSimpleName();
        }

        //System.out.println("PROVA: " + context);

        //la variabile non è stata dichiarata in ProgLetIn
        if(!context.equals("VarasmContext") && !context.equals("StartContext") && (!context.equals("FunExpContext")) && (!context.equals("MethodcallExpContext"))) {

            //la variabile è stata dichiarata nel corpo di una funzione
            if(context.equals("FunbodyContext")) {
                FunbodyContext fbc = (FunbodyContext) rctx;
                if(fbc.children.size() > 2) {
                    for(VarasmContext vc : fbc.letfun().varasm()) {
                        if(id.equals(vc.vardec().ID().getText())) {
                            find = true;
                        }
                    }
                }
            }

            if(!find) {
                rctx = rctx.getParent();
                context = rctx.getClass().getSimpleName();

                //la variabile è un parametro di una funzione
                if(context.equals("FunContext")) {
                    FunContext fc = (FunContext) rctx;
                    for(VardecContext vc : fc.vardec()) {
                        if(id.equals(vc.ID().getText())) {
                            find = true;
                            isParameter = true;
                        }
                    }
                }

                //la variabile è un campo di una classe
                if (!find) {
                    if(rctx.getParent().getClass().getSimpleName().equals("ClassdecContext")){
                        ClassdecContext cc = (ClassdecContext) rctx.getParent();
                        for(VardecContext vc : cc.vardec()) {
                            if(id.equals(vc.ID().getText())) {
                                isField = true;
                            }
                        }
                    }
                }
            }
        }

        if (context.equals("VarasmContext")){

            rctx = rctx.getParent().getParent();
            context = rctx.getClass().getSimpleName();
            find = false;

            if(context.equals("FunbodyContext")) {
                FunbodyContext fbc = (FunbodyContext) rctx;
                if(fbc.children.size() > 2) {
                    for(VarasmContext vc : fbc.letfun().varasm()) {
                        if(id.equals(vc.vardec().ID().getText())) {
                            find = true;
                        }
                    }
                }
            }

            if(!find) {
                rctx = rctx.getParent();
                context = rctx.getClass().getSimpleName();

                //la variabile è un parametro di una funzione
                if (context.equals("FunContext")) {
                    FunContext fc = (FunContext) rctx;
                    for (VardecContext vc : fc.vardec()) {
                        if (id.equals(vc.ID().getText())) {
                            isParameter = true;
                        }
                    }
                }
            }
        }

        if(context.equals("FunExpContext") || context.equals("MethodcallExpContext")) {
            isInputFun=true;
        }

        return new IdNode(ctx.ID().getText(), isField, isInputFun, isParameter);

	}

	@Override
    public Node visitPrintExp(PrintExpContext ctx){
        //System.out.println("visitPrint");

        return new PrintNode(visit(ctx.exp()));
    }
	
	@Override
	public Node visitFunExp(FunExpContext ctx) {
	    //System.out.println("visitFunCallExp");
		Node res;
		
		//parametri della chiamata
		ArrayList<Node> args = new ArrayList<Node>();
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));

        String context = ctx.getClass().getSimpleName();
        ParserRuleContext rctx = ctx;
        while((!context.equals("FunContext")) && (!context.equals("StartContext"))) {
            rctx = rctx.getParent();
            context = rctx.getClass().getSimpleName();
        }

        if(!context.equals("StartContext")) {
            CharStream input = rctx.start.getInputStream();
            int a = rctx.start.getStartIndex();
            int b = rctx.stop.getStopIndex();

            Interval interval = new Interval(a, b);
            int stop = a + (input.getText(interval).indexOf("(")) - 1;

            Interval interval2 = new Interval(a, stop);

            String[] funName = input.getText(interval2).split(" ");

            res = new CallNode(ctx.ID().getText(), args, funName[1]);
        }
        else
            res = new CallNode(ctx.ID().getText(), args);
		
		return res;
	}

	@Override
	public Node visitNullExp(NullExpContext ctx){
        //System.out.println("visit Nullexp");
        NullNode res;

        //risualgo l'ast per verificare se il nullNode e' una singleExp (stop a StartContext) o viene assegato a
        //qualcosa tramite una dichiarazione o un assegnamento (stop a VarasmContext o a AssignmentContext)
        ParserRuleContext rctx = ctx.getParent();
        String context = rctx.getClass().getSimpleName();
        while((!context.equals("VarasmContext")) && (!context.equals("AssignmentStmContext")) &&
            (!context.equals("StartContext")) && (!context.equals("FunExpContext")) && (!context.equals("MethodcallExpContext"))) {
            rctx = rctx.getParent();
            context = rctx.getClass().getSimpleName();
        }

        //caso NullNode single exp
        if(context.equals("StartContext") || context.equals("FunExpContext") || context.equals("MethodcallExpContext") ) {
            if (context.equals("FunExpContext") || context.equals("MethodcallExpContext"))
                res = new NullNode(true, true);
            else
                res = new NullNode(true, false);
        }
        //caso in cui NullNode e' assegnato a qualcosa
        else
            res = new NullNode(false, false);

        return res;
	}

	@Override
    public Node visitMethodcallExp(MethodcallExpContext ctx){
        //System.out.println("visitMethodcallExp");
        MethodcallNode res;

        ParserRuleContext rctx = ctx.getParent();
        String context = rctx.getClass().getSimpleName();
        String id = ctx.ID(0).getText();

        boolean find = false;
        boolean isParameter = false;
        boolean isField = false;

        while((!context.equals("FunbodyContext")) && (!context.equals("VarasmContext")) &&
            (!context.equals("StartContext")) && (!context.equals("FunExpContext"))) {
            rctx = rctx.getParent();
            context = rctx.getClass().getSimpleName();
        }

        //System.out.println("PROVA: " + context);

        //la variabile non è stata dichiarata in ProgLetIn
        if(!context.equals("VarasmContext") && !context.equals("StartContext") && (!context.equals("FunExpContext"))) {

            //la variabile è stata dichiarata nel corpo di una funzione
            if(context.equals("FunbodyContext")) {
                FunbodyContext fbc = (FunbodyContext) rctx;
                if(fbc.children.size() > 2) {
                    for(VarasmContext vc : fbc.letfun().varasm()) {
                        if(id.equals(vc.vardec().ID().getText())) {
                            find = true;
                        }
                    }
                }
            }

            if(!find) {
                rctx = rctx.getParent();
                context = rctx.getClass().getSimpleName();

                //la variabile è un parametro di una funzione
                if (context.equals("FunContext")) {
                    FunContext fc = (FunContext) rctx;
                    for (VardecContext vc : fc.vardec()) {
                        if (id.equals(vc.ID().getText())) {
                            find = true;
                            isParameter = true;
                        }
                    }
                }

                //la variabile è un campo di una classe
                if (!find) {
                    if(rctx.getParent().getClass().getSimpleName().equals("ClassdecContext")){
                        ClassdecContext cc = (ClassdecContext) rctx.getParent();
                        for(VardecContext vc : cc.vardec()) {
                            if(id.equals(vc.ID().getText())) {
                                isField = true;
                            }
                        }
                    }
                }

            }
        }

        if (context.equals("VarasmContext")){

            rctx = rctx.getParent().getParent();
            context = rctx.getClass().getSimpleName();
            find = false;

            if(context.equals("FunbodyContext")) {
                FunbodyContext fbc = (FunbodyContext) rctx;
                if(fbc.children.size() > 2) {
                    for(VarasmContext vc : fbc.letfun().varasm()) {
                        if(id.equals(vc.vardec().ID().getText())) {
                            find = true;
                        }
                    }
                }
            }

            if(!find) {
                rctx = rctx.getParent();
                context = rctx.getClass().getSimpleName();

                //la variabile è un parametro di una funzione
                if (context.equals("FunContext")) {
                    FunContext fc = (FunContext) rctx;
                    for (VardecContext vc : fc.vardec()) {
                        if (id.equals(vc.ID().getText())) {
                            isParameter = true;
                        }
                    }
                }
            }
        }

        //caso chiamata di metodo
        if(ctx.LPAR() != null) {
            //parametri della chiamata di metodo
            ArrayList<Node> args = new ArrayList<Node>();
            for (ExpContext e : ctx.exp()) {
                args.add(visit(e));
            }
            res = new MethodcallNode(ctx.ID(), args, isField);
        }

        //caso utilizzo di un campo
        else
            res = new MethodcallNode(ctx.ID(), isParameter, isField);

        return res;
    }

    @Override
    public Node visitNewExp(NewExpContext ctx){
	    //System.out.println("visit newnexp");
        NewNode res;

        //parametri della new
        ArrayList<Node> args = new ArrayList<Node>();
        for(ExpContext e : ctx.exp()) {
            args.add(visit(e));
        }

        //risualgo l'ast per verificare se il newNode e' una singleExp (stop a StartContext) o viene assegato a
        //qualcosa tramite una dichiarazione o un assegnamento (stop a VarasmContext o a AssignmentContext)
        ParserRuleContext rctx = ctx.getParent();
        String context = rctx.getClass().getSimpleName();
        while((!context.equals("VarasmContext")) && (!context.equals("AssignmentStmContext")) &&
            (!context.equals("StartContext")) && (!context.equals("FunExpContext")) && (!context.equals("MethodcallExpContext"))) {
            rctx = rctx.getParent();
            context = rctx.getClass().getSimpleName();
        }

        //caso NewNode single exp
        if(context.equals("StartContext") || context.equals("FunExpContext") || context.equals("MethodcallExpContext")) {
            if (context.equals("FunExpContext") || context.equals("MethodcallExpContext"))
                res = new NewNode(ctx.ID().getText(), args, true, true);
            else
                res = new NewNode(ctx.ID().getText(), args, true, false);
        }
        //caso in cui NewNode e' assegnato a qualcosa
        else
            res = new NewNode(ctx.ID().getText(), args, false, false);


        return res;
    }

    @Override
    public Node visitAssignmentStm(AssignmentStmContext ctx){
	   //System.out.println("visit assignmentstm");

        //parte dx dell'uguaglianza
        Node exp = visit(ctx.exp());
        //parte sx dell'uguaglianza (può essere una variabile o un campo)
        AssignmentNode res = new AssignmentNode(ctx.ID(), exp);

        return res;
    }

    @Override
    public Node visitMethodcallStm(MethodcallStmContext ctx){
       //System.out.println("visit callmetstm");

        //input della funzione/metodo
        ArrayList<Node> args = new ArrayList<Node>();
        for(ExpContext e : ctx.exp())
            args.add(visit(e));

        //caso chiamata di funzione
        if(ctx.ID().size() < 2)
            return new CallNode(ctx.ID(0).getText(), args);

        //caso chiamata di metodo
        else{
            return new MethodcallNode(ctx.ID(), args, false);
        }

    }

    @Override
    public Node visitIfStm(IfStmContext ctx){
        //System.out.println("visit ifstm");
        IfNode res;

        Node condExp = visit (ctx.cond);
        ArrayList<Node> thenStms = new ArrayList<Node>();
        ArrayList<Node> elseStms = new ArrayList<Node>();

        //then stms
        for(StmContext s : ctx.thenBranch.stm()){
            thenStms.add( visit(s) );
        }
        //else stms
        for(StmContext s : ctx.elseBranch.stm()){
            elseStms.add( visit(s) );
        }

        res = new IfNode(condExp, thenStms, elseStms);
        return res;

    }
	
}
