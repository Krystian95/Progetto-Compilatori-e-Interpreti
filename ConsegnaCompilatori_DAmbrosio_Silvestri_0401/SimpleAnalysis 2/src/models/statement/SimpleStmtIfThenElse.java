package models.statement;

import java.util.ArrayList;

import static util.CodeGeneration.*;
import models.Environment;
import models.exp.SimpleExp;
import models.stentry.SimpleVarSTEntry;
import models.type.SimpleType;
import models.type.SimpleTypeBool;
import util.SemanticError;
import util.TypeCheck;

public class SimpleStmtIfThenElse extends SimpleStmt {

	private SimpleExp condition;
	private SimpleStmtBlock thenBlock;
	private SimpleStmtBlock elseBlock;
	private ArrayList<SimpleVarSTEntry> thenDeleteEntry;
	private ArrayList<SimpleVarSTEntry> elseDeleteEntry;

	/**
	 * @param condition
	 * @param thenBlock
	 * @param elseBlock
	 */

	public SimpleStmtIfThenElse(SimpleExp condition, SimpleStmtBlock thenBlock, SimpleStmtBlock elseBlock) {

		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;		

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment e) {

		//initialize result variable
		ArrayList<SemanticError> result = new ArrayList<SemanticError>();

		//check semantics in the condition
		result.addAll(condition.checkSemantics(e));

		//check semantics in the then and in the else block
		result.addAll(thenBlock.checkSemantics(e));	

		thenDeleteEntry = e.getDeletionVtable();
		//		thenWriteEntry = e.getWriteAccessVtable(nestingLevel++);

		result.addAll(elseBlock.checkSemantics(e));
		elseDeleteEntry = e.getDeletionVtable();
		//		elseWriteEntry = e.getWriteAccessVtable(nestingLevel++);


		return result;

	}

	@Override
	public String toPrint(String ifThenElse) {
		return ifThenElse+"If\n" + condition.toPrint(ifThenElse+"  ") +
				thenBlock.toPrint(ifThenElse+"  ") +
				elseBlock.toPrint(ifThenElse+"  "); 
	}

	@Override
	public SimpleType typeCheck() {
		if (!(TypeCheck.isEqualtype(condition.typeCheck(),new SimpleTypeBool()))) {
			System.err.println("\n>> Type Check IfThenElse: FALIED");
			System.err.println("\tYou had 1 error:");
			System.err.println("\tNot boolean condition in if :\n" + condition.toPrint(""));
			System.exit(0);
		}		

		if(!thenDeleteEntry.isEmpty() || !elseDeleteEntry.isEmpty()) {
			boolean exit = false;

			for (int i = 0; i < thenDeleteEntry.size(); i++) {
				System.out.println("then delete: " + thenDeleteEntry.get(i));
			}

			if(elseDeleteEntry.size() <= thenDeleteEntry.size()) {
				System.err.println("\n>> Type Check IfThenElse: FALIED");
				System.err.println("\tYou had 1 error:");
				System.err.println("\tType mismatch in brach if then - else");
				System.exit(0);	
			}
			for (int i = 0; i < elseDeleteEntry.size(); i++) {
				System.out.println("else delete: " + elseDeleteEntry.get(i));
				for (int y = 0; y < thenDeleteEntry.size(); y++) {
					if (thenDeleteEntry.get(y) == elseDeleteEntry.get(i)) {
						exit = true;
					} 
				}

				if(exit == true) {
					System.out.println("Delete THEN = Delete ELSE");
				} else {
					System.err.println("\n>> Type Check IfThenElse: FALIED");
					System.err.println("\tYou had 1 error:");
					System.err.println("\tType mismatch in then - else");
					System.exit(0);					
				}
				exit = false;

			}
		}

		return null;

	}

	@Override
	public String codeGeneration() {
		String labelThen = freshLabel(); 
		String labelElse = freshLabel();

		return condition.codeGeneration()+
				"push 1\n"+
				"beq "+ labelThen +"\n"+			  
				elseBlock.codeGeneration()+
				"b " + labelElse + "\n" +
				labelThen + ":\n"+
				thenBlock.codeGeneration()+
				labelElse + ":\n"; 
	}
}
