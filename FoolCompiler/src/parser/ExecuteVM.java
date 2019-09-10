package parser;

public class ExecuteVM {

	public static final int CODESIZE = 10000;
	public static final int MEMSIZE = 10000;

	private int[] code;
	private int[] memory = new int[MEMSIZE];

	private int ip = 0;
	private int sp = MEMSIZE;
	private int hp = 0;       
	private int fp = MEMSIZE; 
	private int ra;           
	private int rv;

	public ExecuteVM(int[] code) {
		this.code = code;
	}

	public void cpu() {
		while ( true ) {
			if(hp+1>=sp) {
				System.out.println("\nError: Out of memory");
				return;
			}
			else {
				int bytecode = code[ip++]; // fetch
				int v1,v2;
				int address;

				//System.out.println("TESTA: "+ memory[sp]);

				switch ( bytecode ) {
				case SVMParser.PUSH:
					push( code[ip++] );
					break;
				case SVMParser.POP:
					pop();
					break;
				case SVMParser.ADD :
					v1=pop();
					v2=pop();
					push(v2 + v1);
					break;
				case SVMParser.MULT :
					v1=pop();
					v2=pop();
					push(v2 * v1);
					break;
				case SVMParser.DIV :
					v1=pop();
					v2=pop();
					push(v2 / v1);
					break;
				case SVMParser.SUB :
					v1=pop();
					v2=pop();
					push(v2 - v1);
					break;
				case SVMParser.STOREW : //
					address = pop();
					memory[address] = pop();    
					break;
				case SVMParser.LOADW : //
					// check if object address where we take the method label
					// is null value (-10000)
					if (memory[sp] == -10000) {
						System.out.println("\nError: Null pointer exception");
						return;
					}
					push(memory[pop()]);
					break;
				case SVMParser.BRANCH : 
					address = code[ip];
					ip = address;
					break;
				case SVMParser.BRANCHEQ : //
					//System.out.println("TESTA prima: "+ memory[sp]);
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 == v1) ip = address;
					//System.out.println("TESTA dopo: "+ memory[sp]);
					break;
				case SVMParser.BRANCHNOTEQ : //
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 != v1) ip = address;
					break;
				case SVMParser.BRANCHGT : //
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 > v1) ip = address;
					break;
				case SVMParser.BRANCHLT : //
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 < v1) ip = address;
					break;
				case SVMParser.BRANCHLESSEQ :
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 <= v1) ip = address;
					break;
				case SVMParser.BRANCHGREATEREQ :
					address = code[ip++];
					v1=pop();
					v2=pop();
					if (v2 >= v1) ip = address;
					break;
				case SVMParser.JS : //	
					address = pop();
					ra = ip;
					ip = address;
					break;
				case SVMParser.STORERA : //
					ra=pop();
					break;
				case SVMParser.LOADRA : //
					push(ra);
					break;
				case SVMParser.STORERV : //
					rv=pop();
					break;
				case SVMParser.LOADRV : //
					push(rv);
					break;
				case SVMParser.LOADFP : //
					push(fp/*-1*/);
					break;
				case SVMParser.STOREFP : //
					fp=pop();
					break;
				case SVMParser.COPYFP : //
					fp=sp;
					break;
				case SVMParser.STOREHP : //
					hp=pop();
					break;
				case SVMParser.LOADHP : //
					push(hp);
					break;
				case SVMParser.PRINT :
					System.out.println((sp<MEMSIZE)?memory[sp]:"Empty stack!");
					break;
				case SVMParser.DELETE :

					if (memory[sp] == -10000) {
						System.out.println("\nError: Null pointer exception");
						return;
					}

					memory[pop()] = null;

					break;
				case SVMParser.HALT :
					//to print the result 
					System.out.println("\nResult: " + memory[sp] + "\n");
					return;
				}

				//System.out.println("TESTA: "+ memory[sp]);
			} 
		}
	} 

	private int pop() {
		return memory[sp++];
	}

	private void push(int v) {
		memory[--sp] = v;
	}

}