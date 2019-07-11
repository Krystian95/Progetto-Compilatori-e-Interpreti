grammar SVM;

@header {
import java.util.HashMap;
}

//@lexer::members e @parser::members definiscono delle azioni specifiche per il lexer
//e il parser rispettivamente

@lexer::members {
    //there is a much better way to do this, check the ANTLR guide
    //I will leave it like this for now just becasue it is quick
    //but it doesn't work well
    //public int lexicalErrors=0;
}

@parser::members {
      
    public int[] code = new int[ExecuteVM.CODESIZE];
    private int i = 0;
    public ArrayList<ArrayList<Integer>> dispatchTable = new ArrayList<>();
    public HashMap<String,Integer> labelAdd = new HashMap<String,Integer>();
    public HashMap<Integer,String> labelRef = new HashMap<Integer,String>();
        
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
  
assembly: 
    ( push
      | pop
      | alloc
      | remove
	  | add
	  | sub
	  | mult
	  | div
	  | loadWord
	  | storeWord
	  | loadField
	  | storeField
	  | label
	  | branch
	  | branchEqual
	  | branchGreaterEqual
	  | branchLessEqual
	  | js
	  | jumpDispatchTable
	  | loadReturnAddress
	  | storeReturnAddress
	  | loadReturnValue
	  | storeReturnValue
	  | loadFramePointer
	  | storeFramePointer
	  | copyFramePointer
	  | loadHeapPointer
	  | storeHeapPointer
	  | loadDispatchPointer
	  | storeDispatchPointer
	  | storeInfoObject
	  | loadInfoObject
	  | loadObjectPointer
	  | storeObjectPointer
	  | print
	  | halt
	  )*
	  ;

push: PUSH n=NUMBER
    | PUSH l=LABEL
    ;

pop: POP ;

alloc : ALLOC n=NUMBER
      | ALLOC l=LABEL ;

remove : REMOVE ;

add: ADD ;

sub: SUB ;

mult: MULT ;

div: DIV ;

loadWord: LOADW ;

storeWord: STOREW ;

loadField : LOADF ;

storeField : STOREF ;

label: l=LABEL COL ;

branch: BRANCH l=LABEL ;

branchEqual: BRANCHEQ l=LABEL ;

branchLessEqual: BRANCHLESSEQ l=LABEL ;

branchGreaterEqual: BRANCHGREATEREQ l=LABEL ;

js: JS ;

jumpDispatchTable: JDT;

loadReturnAddress: LOADRA ;

storeReturnAddress: STORERA ;

loadReturnValue: LOADRV ;

storeReturnValue: STORERV ;

loadFramePointer: LOADFP ;

storeFramePointer: STOREFP ;

copyFramePointer: COPYFP ;

loadHeapPointer: LOADHP ;

storeHeapPointer: STOREHP ;

loadDispatchPointer: LOADDP ;

storeDispatchPointer: STOREDP ;

loadInfoObject: LOADIO;

storeInfoObject: STOREIO;

loadObjectPointer: LOADOP;

storeObjectPointer: STOREOP;

print: PRINT ;

halt: HALT ;
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack
ALLOC : 'alloc' ; // alloc an object in the heap
REMOVE : 'remove' ; // remove an object from heap
ADD	 : 'add' ;  	// add two values from the stack
SUB	 : 'sub' ;	// sub two values from the stack
MULT : 'mult' ;  	// mult two values from the stack
DIV	 : 'div' ;	// div two values from the stack
LOADW	 : 'lw' ;	// load a value from the memory cell pointed by top
STOREW	 : 'sw' ; 	// store in the memory cell pointed by top the value next
LOADF  : 'lf' ; // load a field from the memory cell pointed by top
STOREF : 'sf' ; // store in the memory cell pointed by top the field next
BRANCH	 : 'b' ;	// jump to label
BRANCHEQ : 'beq' ;	// jump to label if top == next
BRANCHGREATEREQ: 'bgeq' ;   //jump to label if top >= next
BRANCHLESSEQ:'bleq' ;	// jump to label if top <= next
JS	 : 'js' ;	// jump to instruction pointed by top of stack and store next instruction in ra
JDT  : 'jdt';   //jump to instruction pointed by dispatch table and store next instruction in ra
LOADRA	 : 'lra' ;	// load from ra
STORERA  : 'sra' ;	// store top into ra	 
LOADRV	 : 'lrv' ;	// load from rv
STORERV  : 'srv' ;	// store top into rv	 
LOADFP	 : 'lfp' ;	// load frame pointer in the stack
STOREFP	 : 'sfp' ;	// store top into frame pointer
COPYFP   : 'cfp' ;      // copy stack pointer into frame pointer
LOADHP	 : 'lhp' ;	// load heap pointer in the stack
STOREHP	 : 'shp' ;	// store top into heap pointer
LOADDP   : 'ldp' ;  // load dispatch pointer in the stack
STOREDP  : 'sdp' ;  // store top into dispatch pointer
LOADIO   : 'lio' ;  //load object offset in the stack
STOREIO  : 'sio' ;
LOADOP   : 'lop' ; //load object pointer in the stack
STOREOP  : 'sop' ; //store top into object poiter
PRINT	 : 'print' ;	// print top of stack
HALT	 : 'halt' ;	// stop execution

COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

