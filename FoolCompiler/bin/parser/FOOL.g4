grammar FOOL;

@lexer::members {
   //there is a much better way to do this, check the ANTLR guide
   //I will leave it like this for now just becasue it is quick
   //but it doesn't work well
   public int lexicalErrors=0;
}

// PARSER RULES

initblock	: block;

block		: '{' statement* '}'; // OK

statement	: assignment ';' #assignmentStatement // OK
		  	| deletion ';' #deletionStatement // OK
		  	| print ';' #printStatement // OK
		  	| functioncall ';' #functioncallStatement // OK
		  	| ifthenelse #ifthenelseStatement // OK
		  	| declaration #declarationStatement // OK
		  	| block #blockStatement // OK
		  	;

assignment	: ID '=' exp ; // OK

deletion	: 'delete' ID ;

print		: 'print' exp ; // OK

functioncall: ID '(' (exp (',' exp)* )? ')' ; // MANCA gestione funzioni di sistema (stdlib, es. "print (...)")

ifthenelse 	: 'if' '(' cond=exp ')' 'then' thenBranch=block 'else' elseBranch=block ; // MANCA nesting level

declaration	: type ID '=' exp ';' #varasm // OK
		  	| ID '(' ( parameter ( ',' parameter)* )? ')' block #fundec // MANCA nesting declarations + var nesting level
			;

type   		: 'int'  
        	| 'bool'
        	; // OK

parameter  	: (modePar='var')? type ID ; // OK

exp    		:  ('-')? left=term (op=('+' | '-') right=exp)? // OK
			; 
   
term   		: left=factor (op=('*' | '/') right=term)? ; // OK
   
factor 		: left=value (op=ROP right=value)? #factorForInteger // OK
	        | left=value (op=('&&' | '||') right=value)? #factorForBoolean // OK
	        ; 
   
value  		: INTEGER				#intVal // OK
     		| ( 'true' | 'false' )	#boolVal // OK
		  	| ID					#varExp // OK
      		| '(' exp ')' 			#baseExp // OK
		  	; 
    
// LEXER RULES

ROP     : '==' | '>' | '<' | '<=' | '>=' | '!=' ; // OK

//Numbers
fragment DIGIT 	: '0'..'9';    
INTEGER       	: DIGIT+;

//IDs
fragment CHAR  	: 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip ;
LINECOMENTS    	: '//' (~('\n'|'\r'))* -> skip ;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip ;
ERR     		: .  -> channel(HIDDEN) ; 
