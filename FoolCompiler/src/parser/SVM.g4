grammar SVM;

@header {
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
  
assembly: (instruction)*;

instruction:
    ( 
    	PUSH n=NUMBER 
	  | PUSH l=LABEL
	  | POP		    
	  | ADD		    
	  | SUB		    
	  | MULT	    
	  | DIV		    
	  | STOREW	  
	  | LOADW           
	  | l=LABEL COL     
	  | BRANCH l=LABEL  
	  | BRANCHEQ l=LABEL 
	  | BRANCHNOTEQ l=LABEL 
	  | BRANCHGT l=LABEL 
	  | BRANCHLT l=LABEL 
	  | BRANCHLESSEQ l=LABEL 
	  | BRANCHGREATEREQ l=LABEL 
	  | JS              
	  | LOADRA          
	  | STORERA         
	  | LOADRV          
	  | STORERV         
	  | LOADFP          
	  | STOREFP         
	  | COPYFP          
	  | LOADHP          
	  | STOREHP         
	  | PRINT           
	  | HALT
	  );
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
PUSH  	 : 'push'; 		// pushes constant in the stack
POP	 	 : 'pop'; 		// pops from stack
ADD	 	 : 'add';  		// add two values from the stack
SUB	 	 : 'sub';		// sub two values from the stack (next / top)
MULT	 : 'mult';  	// add two values from the stack
DIV	 	 : 'div';		// divide two values from the stack (next / top)
STOREW	 : 'sw'; 		// store in the memory cell pointed by top the value next
LOADW	 : 'lw';		// load a value from the memory cell pointed by top
BRANCH	 : 'b';			// jump to label
BRANCHEQ : 'beq';		// jump to label if next == top
BRANCHNOTEQ : 'bneq';	// jump to label if next != top
BRANCHGT : 'bgt';		// jump to label if next > top
BRANCHLESSEQ :'bleq';	// jump to label if next <= top
BRANCHGREATEREQ :'bgeq';// jump to label if next >= top
BRANCHLT : 'blt';		// jump to label if next < top
JS	 	 : 'js';		// jump to instruction pointed by top of stack and store next instruction in ra
LOADRA	 : 'lra';		// load from ra
STORERA  : 'sra';		// store top into ra	 
LOADRV	 : 'lrv';		// load from rv
STORERV  : 'srv';		// store top into rv
LOADFP	 : 'lfp';		// load frame pointer in the stack
STOREFP	 : 'sfp';		// store top into frame pointer
COPYFP   : 'cfp';       // copy stack pointer into frame pointer
LOADHP	 : 'lhp';		// load heap pointer in the stack
STOREHP	 : 'shp';		// store top into heap pointer
PRINT	 : 'print';		// print top of stack
HALT	 : 'halt';		// stop execution

COL	 	 : ':';
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*);

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

