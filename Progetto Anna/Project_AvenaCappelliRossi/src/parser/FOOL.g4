grammar FOOL;

@lexer::members {
   //there is a much better way to do this, check the ANTLR guide
   //I will leave it like this for now just becasue it is quick
   //but it doesn't work well
   //public int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/


start: ( classdec SEMIC )* prog;

classdec:  CLASS classname=ID (EXTENDS classextended=ID)? LPAR ( vardec ( COMMA vardec )* )? RPAR CLPAR ( fun SEMIC )+ CRPAR
	 	;

prog   : ( exp | stms ) SEMIC            #singleExp
       | let ( exp | stms ) SEMIC        #letInExp
       ;

let    : LET (dec SEMIC)+ IN ;

letfun : LET ( varasm SEMIC )+ IN ;

vardec  : type ID ;

varasm  : vardec ASM exp ;

fun    : ( type | VOID)  ID LPAR ( vardec ( COMMA vardec)* )? RPAR CLPAR funbody CRPAR ;

funbody : ( letfun )? ( exp | stms ) SEMIC ;

dec   : varasm           #varAssignment
      | fun              #funDeclaration
      ;

type   : INT
       | BOOL
       | ID
       ;

exp    : left=factor (operator=( AND | OR) right=exp)?
       ;

factor : left=arithmetic (operator=(EQ | GE | LE) right=factor)?
       ;

arithmetic  : left=term (operator=(PLUS | MINUS) right=arithmetic)?
            ;

term  :  left=denied (operator=(TIMES | DIV) right=term)?
      ;

denied : (operator=NOT)? right=value
       ;

value  : (MINUS)? INTEGER                  #intVal
      | ( TRUE | FALSE )                   #boolVal
      | LPAR exp RPAR                      #baseExp
      | IF cond=exp THEN CLPAR thenBranch=exp SEMIC CRPAR ELSE CLPAR elseBranch=exp SEMIC CRPAR  #ifExp
      | ID                                                  #varExp
      | ID LPAR (exp (COMMA exp)* )? RPAR                   #funExp
      | NULL                                                #nullExp
      | ID DOT ID ( LPAR (exp ( COMMA exp)* )? RPAR )?      #methodcallExp
      | NEW ID LPAR (exp ( COMMA exp)* )? RPAR              #newExp
      ;

stms : stm ( SEMIC stm )* ;

stm : ID ( DOT ID )? ASM exp                                                                      #assignmentStm
	| IF cond=exp THEN CLPAR thenBranch=stms SEMIC CRPAR ELSE CLPAR elseBranch=stms SEMIC CRPAR   #ifStm
	| ID ( DOT ID )? LPAR (exp ( COMMA exp)* )? RPAR                                                   #methodcallStm
	| STDLIB  LPAR exp  RPAR                                                                      #printExp
	;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

SEMIC  : ';' ;
COLON  : ':' ;
COMMA  : ',' ;
GE     : '>=' ;
LE     : '<=' ;
NOT	   : ('not' | '!') ;
AND    : ('and' | '&&') ;
OR	   : ('or' | '||') ;
EQ     : '==' ;
ASM    : '=' ;
PLUS   : '+' ;
MINUS  : '-' ;
TIMES  : '*' ;
DIV    : '/' ;
TRUE   : 'true' ;
FALSE  : 'false' ;
LPAR   : '(' ;
RPAR   : ')' ;
CLPAR  : '{' ;
CRPAR  : '}' ;
IF        : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
LET    : 'let' ;
IN     : 'in' ;
VAR    : 'var' ;
FUN    : 'fun' ;
INT    : 'int' ;
BOOL   : 'bool' ;
CLASS  : 'class' ;
EXTENDS : 'extends' ;
DOT    : '.' ;
VOID   : 'void' ;
NULL   : 'null' ;
NEW    : 'new' ;
STDLIB : 'print' ;


//Numbers
fragment DIGIT : '0'..'9';
INTEGER       :  DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS    : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;
