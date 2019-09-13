package ast;
import java.util.ArrayList;
import java.util.HashMap;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class FunNode implements Node {

	private String id;
	private Node type; 
	private ArrayList<Node> parlist = new ArrayList<Node>(); 
	private ArrayList<Node> declist; 
	private Node body;

	public FunNode (String i) {
		id=i;
	}

	public void addDecBody (ArrayList<Node> d, Node b) {
		declist=d;
		body=b;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		
		//env.offset = -2;
		HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);
		STentry entry = new STentry(env.nestingLevel,env.offset--); //separo introducendo "entry"

		if ( hm.put(id,entry) != null )
			res.add(new SemanticError("Fun id "+id+" already declared"));
		else{		

			ArrayList<Node> parTypes = new ArrayList<Node>();
			//int paroffset=1;

			//check args
			for(Node a : parlist){
				ParNode arg = (ParNode) a;
				parTypes.add(arg.getType());
				/*if ( hmn.put(arg.getId(),new STentry(env.nestingLevel,arg.getType(),paroffset++)) != null  )
					System.out.println("Parameter id "+arg.getId()+" already declared");*/
			}
			
			//creare una nuova hashmap per la symTable
			System.out.println("nestingLevel="+env.nestingLevel);
			env.nestingLevel++;
			System.out.println("nestingLevel="+env.nestingLevel);
			HashMap<String,STentry> hmn = new HashMap<String,STentry> ();
			env.symTable.add(hmn);
			//env.offset=-2;
			
			env.parOffset=1;
			
			for(Node a : parlist){
				res.addAll(a.checkSemantics(env));
			}

			//check semantics in the dec list
			if(declist!= null && declist.size() > 0){
				//env.offset = -2;
				//if there are children then check semantics for every child and save the results
				for(Node n : declist)
					res.addAll(n.checkSemantics(env));
			}

			//set func type
			entry.addType( new ArrowTypeNode(parTypes/*, type*/) );

			//check body
			res.addAll(body.checkSemantics(env));

			//close scope
			env.symTable.remove(env.nestingLevel--);

		}

		return res;
	}

	public void addPar (Node p) {
		parlist.add(p);
	}  

	public String toPrint(String s) {
		String parlstr="";
		for (Node par:parlist)
			parlstr+=par.toPrint(s+"  ");
		String declstr="";
		if (declist!=null) 
			for (Node dec:declist)
				declstr+=dec.toPrint(s+"  ");
		return s+"Fun:" + id +"\n"
				//+type.toPrint(s+"  ")
				+parlstr
				+declstr
				+body.toPrint(s+"  ") ; 
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {
		if (declist!=null) 
			for (Node dec:declist)
				dec.typeCheck();
		/*if ( !(FOOLlib.isSubtype(body.typeCheck(),type)) ){
      System.out.println("Wrong return type for function "+id);
      System.exit(0);
    } */ 
		return null;
	}

	public String codeGeneration() {

        System.out.println("declist="+declist);
        System.out.println("parlist="+parlist);

		String declCode="";
		if (declist!=null) 
			for (Node dec:declist)
				declCode+=dec.codeGeneration();

		String popDecl="";
		if (declist!=null) 
			for (Node dec:declist)
				popDecl+="pop\n";

		String popParl="";
		for (Node dec:parlist)
			popParl+="pop\n";

		String funl=FOOLlib.freshFunLabel(); 
		/*FOOLlib.putCode(
				//"[-- START FOOLlib.putCode --]\n"+
				funl+":\n"+
				"cfp\n"+ 		// setta $fp = $sp				
				"lra\n"+ 		// inserimento return address
				declCode+ 		// inserimento dichiarazioni locali
				body.codeGeneration()+
				"srv\n"+ 		// pop del return value (store top into rv)
				popDecl+
				"sra\n"+ 		// pop del return address
				"pop\n"+ 		// pop di AL
				popParl+
				"sfp\n"+  		// setto $fp a valore del CL
				"lrv\n"+ 		// risultato della funzione sullo stack
				"lra\n"+
				"js\n"  // salta a $ra
				//+"[-- END FOOLlib.putCode --]\n"
				);*/
		
		FOOLlib.putCode(
                funl + ":\n"
                    + "cfp\n"               //move $fp a $sp
                    + "lra\n"               //push $ra
                    + body.codeGeneration() //push funbody
                    + popDecl             	//pop delle dichiarazioni in funbody
                    + "sra\n"             //$ra <- top
                    + "pop\n"             //pop $fp
                    + popParl               //pop parlist
                    + "sfp\n"             //$fp <- top
                    + "lra\n"               //push $ra
                    + "js\n"                //jump $ra
            );

		return //"[-- START FUN NODE --]\n"+
				"push "+ funl +"\n"
				//"+[-- END FUN NODE --]\n"
				;
	}

}  