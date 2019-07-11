import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;

import ast.SVMVisitorImpl;
import org.antlr.v4.runtime.*;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import parser.ExecuteVM;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.SVMLexer;
import parser.SVMParser;
import util.Environment;
import util.SemanticError;
import ast.FoolVisitorImpl;
import ast.Node;

public class Test extends BaseErrorListener {

    public static void main(String[] args) throws Exception {

        String fileName = "prova.fool";

           FileInputStream is = new FileInputStream(fileName);
           ANTLRInputStream input = new ANTLRInputStream(is);


           FOOLLexer lexer = new FOOLLexer(input);

           lexer.removeErrorListeners();
           lexer.addErrorListener(new Test() {
               @Override
               public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                   System.out.println("Error on line: " + line + ": " + msg);
                   System.out.println("The program was not in the right format. Exiting the compilation process now");
                   System.exit(1);
                   throw new IllegalStateException();
               }
           });

           CommonTokenStream tokens = new CommonTokenStream(lexer);
           FOOLParser parser = new FOOLParser(tokens);

           parser.removeErrorListeners();
           parser.addErrorListener(new Test() {
             @Override
             public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                 System.out.println("Error on line: " + line + ": " + msg);
                 System.out.println("The program was not in the right format. Exiting the compilation process now");
                 System.exit(1);
                 throw new IllegalStateException();
             }
           });

           FoolVisitorImpl visitor = new FoolVisitorImpl();
           Node ast = visitor.visit(parser.start());
           Environment env = new Environment();
           ArrayList<SemanticError> err = ast.checkSemantics(env);


        if (err.size() > 0) { //SEMATIC ERRORS
            System.out.println("You had: " + err.size() + " errors:");
            for (SemanticError e : err)
                System.out.println("\t" + e);
            System.exit(0);
        } else {
            Node type = ast.typeCheck(); //type-checking bottom-up

            System.out.println("Visualizing AST...");
            System.out.println(ast.toPrint(""));

            System.out.println(type.toPrint("Type checking ok! Type of the program is: "));

            // CODE GENERATION  prova.fool.asm
            String code = ast.codeGeneration();
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName + ".asm"));
            out.write(code);
            out.close();
            System.out.println("Code generated! Assembling and running generated code.");

            FileInputStream isASM = new FileInputStream(fileName + ".asm");
            ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
            SVMLexer lexerASM = new SVMLexer(inputASM);

            lexerASM.removeErrorListeners();
            lexerASM.addErrorListener(new Test() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    System.out.println("You had a lexical error on line: " + line + ": " + msg);
                    System.exit(1);
                    throw new IllegalStateException();
                }
            });

            CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);

            SVMParser parserASM = new SVMParser(tokensASM);

            parserASM.removeErrorListeners();
            parserASM.addErrorListener(new Test() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    System.out.println("You had a syntax error on line: " + line + ": " + msg);
                    System.exit(1);
                    throw new IllegalStateException();
                }
            });

            SVMVisitorImpl visitorASM = new SVMVisitorImpl(parserASM.code, parserASM.labelAdd, parserASM.labelRef, parserASM.dispatchTable);

            visitorASM.visit(parserASM.assembly());

            /*System.out.println("\nStampa di code...");
            for (int i = 0; i < parserASM.code.length; i++) {
                if (parserASM.code[i] != 0 || parserASM.code[i + 1] != 0)
                    System.out.println("Elemento " + i + ": " + parserASM.code[i]);
                else
                    break;
            }*/

            //System.out.println("\nYou had: " + lexerASM.lexicalErrors + " lexical errors and " + parserASM.getNumberOfSyntaxErrors() + " syntax errors.");
            //if (lexerASM.lexicalErrors > 0 || parserASM.getNumberOfSyntaxErrors() > 0) System.exit(1);

            System.out.println("Starting Virtual Machine...");
            ExecuteVM vm = new ExecuteVM(parserASM.code, parserASM.dispatchTable, env.getObjectNumber());
            vm.cpu();

        }
    }
}

