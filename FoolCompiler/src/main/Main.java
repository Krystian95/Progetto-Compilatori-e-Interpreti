package main;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import models.Environment;
import models.FoolVisitorImpl;
import models.Node;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.SVMLexer;
import parser.SVMParser;
import util.AppLexicalError.ThrowingLexicalError;
import virtual_machine.ExecuteVM;
import virtual_machine.SVMVisitorImpl;
import util.SemanticError;

public class Main {
	public static void main(String[] args) throws Exception {

		String fileName = "test.fool";

		FileInputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
		FOOLLexer lexer = new FOOLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);


		System.out.println();

		if(lexer.lexicalErrors > 0){
			System.err.println("The program was not in the right format. Exiting the compilation process now.");
		}else{

			FOOLParser parser = new FOOLParser(tokens);

			parser.removeErrorListeners();
			parser.addErrorListener(ThrowingLexicalError.INSTANCE);

			FoolVisitorImpl visitor = new FoolVisitorImpl();

			System.out.println();

			ArrayList<SemanticError> err = null;
			Node ast = null;

			try {
				System.out.println();
				ast = visitor.visit(parser.initblock()); //generazione AST
				Environment env = new Environment();
				err = ast.checkSemantics(env);
			}
			catch(Exception e) { // ParseCancellationException
				System.err.println("\nSyntax error. Running aborted.");
				System.exit(0);
			}

			if(err.size() > 0){
				System.err.println("You had " + err.size() + " error(s):");
				for(SemanticError e : err)
					System.err.println("\t" + e);
			}else{

				System.out.println("\nVisualizing AST...\n");
				System.out.println(ast.toPrint(""));

				Node type = ast.typeCheck(); //type-checking bottom-up

				if(type == null) {
					System.out.println("\nType checking ok! Type of the program is: VoidType");
				}else {
					System.out.println(type.toPrint("\nType checking ok! Type of the program is: "));
				}

				// CODE GENERATION in prova.fool.asm
				String code = ast.codeGeneration(); 
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName + ".asm")); 
				out.write(code);
				out.close(); 
				System.out.println("\nCode generated! Assembling and running generated code.");

				FileInputStream isASM = new FileInputStream(fileName + ".asm");
				ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
				SVMLexer lexerASM = new SVMLexer(inputASM);
				CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
				SVMParser parserASM = new SVMParser(tokensASM);

				SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
				visitorSVM.visit(parserASM.assembly());

				if (lexerASM.lexicalErrors > 0 || parserASM.getNumberOfSyntaxErrors() > 0) {
					System.err.println("You had: "+lexerASM.lexicalErrors+" lexical errors and " + parserASM.getNumberOfSyntaxErrors() + " syntax errors.");
					System.exit(1);
				} else {
					System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and " + parserASM.getNumberOfSyntaxErrors() + " syntax errors.");
				}

				System.out.println("\nStarting Virtual Machine...");
				System.out.println();

				ExecuteVM vm = new ExecuteVM(visitorSVM.code);
				vm.cpu();
			}
		}


	}
}
