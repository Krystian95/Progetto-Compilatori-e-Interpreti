package parser;

import java.util.ArrayList;

public class ExecuteVM {
    
    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;
    
    private int[] code;
    private ArrayList<ArrayList<Integer>> dispatchTable;
    private int[] memory = new int[MEMSIZE];
    
    private int ip = 0; //instruction pointer
    private int sp = MEMSIZE; //stack pointer
    
    private int hp = 0; //heap pointer
    private int[] dp; //dispatch pointer: posizione assoluta nello heap
    private int op; //object pointer: offset dell'oggetto sul quale si chiama un metodo
    private int fp = MEMSIZE; //frame pointer
    private int ra; //return address
    private int rv; //return value

    //nelle slide ci sono due registri in più:
    //a: memorizza il valore di un'espressione
    //t: registro temporaneo

    public ExecuteVM(int[] code, ArrayList<ArrayList<Integer>> dT, int dimDp) {
      this.code = code;
      dispatchTable = dT;
      dp = new int[dimDp];
    }
    
    public void cpu() {
        while ( true ) {
            int bytecode = code[ip++]; // fetch
            int v1,v2;
            int address;
            switch ( bytecode ) {
                case SVMParser.PUSH:
                    push( code[ip++] );
                    break;
                case SVMParser.POP:
                    pop();
                    break;
                case SVMParser.ALLOC :
                    alloc( code[ip++] );
                    break;
                case SVMParser.REMOVE :
                    remove();
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
                    if(v1 != 0)
                        push(v2 / v1);
                    else {
                        System.out.println("Division by 0 not admitted\n");
                        System.exit(0);
                    }
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
                    push(memory[pop()]);
                    break;
                case SVMParser.LOADF :
                    v1 = dp[pop()];
                    if (memory[v1] < 0){
                        System.out.println("NullPointerException\n");
                        System.exit(0);
                    }
                    push(v1);
                    break;
                case SVMParser.STOREF :
                    alloc(pop());
                    break;
                case SVMParser.BRANCH :
                    address = code[ip];
                    ip = address;
                    break;
                case SVMParser.BRANCHEQ : //
                    address = code[ip++];
                    v1=pop();
                    v2=pop();
                    if (v2 == v1) ip = address;
                    break;
                case SVMParser.BRANCHGREATEREQ :
                    address = code[ip++];
                    v1=pop();
                    v2=pop();
                    if (v2 >= v1) ip = address;
                    break;
                case SVMParser.BRANCHLESSEQ :
                    address = code[ip++];
                    v1=pop();
                    v2=pop();
                    if (v2 <= v1) ip = address;
                    break;
                case SVMParser.JS : //
                    address = pop();
                    ra = ip;
                    ip = address;
                    //System.out.println("ra: " + ra + "\nsalta a: " + ip);
                    break;
                case SVMParser.JDT : //
                    v1 = memory[dp[op]]; //dp dell'oggetto
                    if (v1 < 0){
                        System.out.println("NullPointerException\n");
                        System.exit(0);
                    }
                    v2 = pop(); //offset del metodo
                    ra = ip;
                    ip = dispatchTable.get(v1).get(v2);
                    //System.out.println("ra: " + ra + "\nsalta a: " + ip);
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
                    push(fp);
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
                case SVMParser.LOADDP:
                    v1 = dp[pop()];
                    push(v1);
                    break;
                case SVMParser.STOREDP:
                    //se l'istruzione attuale è alloc mi salvo in op la posizione assoluta dell'oggetto nello heap
                    v1=pop(); //valore dell'hp
                    v2=pop(); //offset dell'oggetto
                    dp[v2] = v1;
                    //System.out.println("Dp: " + dp[v2] + " -> offset: " + v2);
                    break;
                case SVMParser.STOREIO:
                    break;
                case SVMParser.LOADIO:
                    v1 = pop(); //valore HP
                    for(int i=0; i<dp.length; i++){
                        if(dp[i]==v1) {
                            push(i);
                            break;
                        }
                    }
                    break;
                case SVMParser.STOREOP:
                    op=pop();
                    break;
                case SVMParser.LOADOP:
                    push(op);
                    break;
                case SVMParser.PRINT :
                    System.out.println((sp<MEMSIZE)?memory[sp]:"Empty stack!");
                    System.exit(0);
                    break;
                case SVMParser.HALT :
                    return;
            }
        }
    } 
    
    private int pop() {
        //System.out.println("Elemento " + sp + " tolto dallo stack: " + memory[sp]);
        return memory[sp++];
    }
    
    private void push(int v) {
        memory[--sp] = v;
        //System.out.println("Elemento " + sp + " inserito nello stack: " + v);
    }

    private int remove() {
        //System.out.println("Elemento " + hp + " tolto dallo heap: " + memory[hp]);
        return memory[hp--];
    }

    private void alloc(int v) {
        //System.out.println("Elemento " + hp + " inserito nello heap: " + v);
        memory[hp++] = v;
    }
}