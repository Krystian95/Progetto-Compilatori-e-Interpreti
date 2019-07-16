grammar FOOL;

@lexer::members {
   //there is a much better way to do this, check the ANTLR guide
   //I will leave it like this for now just becasue it is quick
   //but it doesn't work well
   public int lexicalErrors=0;
}

// PARSER RULES


block		: '{' statement* '}';

statement	: assignment ';' #assignmentStatement
		  	| deletion ';' #deletionStatement
		  	| print ';' #printStatement
		  	| functioncall ';' #functioncallStatement
		  	| ifthenelse #ifthenelseStatement
		  	| declaration #declarationStatement
		  	| block #blockStatement
		  	;

assignment	: ID '=' exp ;

deletion	: 'delete' ID ;

print		: 'print' exp ; // OK

functioncall: ID '(' (exp (',' exp)* )? ')' ;

ifthenelse 	: 'if' '(' exp ')' 'then' block 'else' block ;   

declaration	: type ID '=' exp ';' #varasm // OK
		  	| ID '(' ( parameter ( ',' parameter)* )? ')' block #fundec
			;

type   		: 'int'  
        	| 'bool'
        	; // OK

parameter  	: ('var')? type ID ;

exp    		:  ('-')? left=term (op=('+' | '-') right=exp)? // OK
			; 
   
term   		: left=factor (op=('*' | '/') right=term)? ; // OK
   
factor 		: left=value (op=ROP right=value)? #factorForInteger
	        | left=value (op=('&&' | '||') right=value)? #factorForBoolean
	        ; 
   
value  		: INTEGER				#intVal // OK
     		| ( 'true' | 'false' )	#boolVal // OK
		  	| ID					#varExp // OK
      		| '(' exp ')' 			#baseExp // OK
		  	; 
    
// LEXER RULES

ROP     : '==' | '>' | '<' | '<=' | '>=' | '!=' ;

//Numbers
fragment DIGIT 	: '0'..'9';    
INTEGER       	: DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip ;
LINECOMENTS    	: '//' (~('\n'|'\r'))* -> skip ;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip ;
ERR     	: .  -> channel(HIDDEN) ; 
