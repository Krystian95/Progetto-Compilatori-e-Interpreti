import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.ExecuteVM;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.SVMLexer;
import parser.SVMParser;
import util.Environment;
import util.SemanticError;
import ast.FoolVisitorImpl;
import ast.Node;
import ast.SVMVisitorImpl;

public class Test {
	public static void main(String[] args) throws Exception {

		String fileName = "prova.fool";

		FileInputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
		FOOLLexer lexer = new FOOLLexer(input);

		//System.out.println(lexer);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		//System.out.println("tokens.size() = "+tokens.size());

		System.out.println();

		//SIMPLISTIC BUT WRONG CHECK OF THE LEXER ERRORS
		if(lexer.lexicalErrors > 0){
			System.err.println("The program was not in the right format. Exiting the compilation process now");
		}else{

			FOOLParser parser = new FOOLParser(tokens);

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
			catch(Exception e) {
				System.err.println("\nSyntax error. Running aborted.");
				System.exit(0);
			}

			if(err.size()>0){
				System.err.println("You had " +err.size()+" errors:");
				for(SemanticError e : err)
					System.err.println("\t" + e);
			}else{

				System.out.println("\nVisualizing AST...");
				System.out.println(ast.toPrint(""));

				Node type = ast.typeCheck(); //type-checking bottom-up

				if(type == null) {
					System.out.println("Type checking ok! Type of the program is: VoidType");
				}else {
					System.out.println(type.toPrint("Type checking ok! Type of the program is: "));
				}

				// CODE GENERATION  prova.fool.asm
				String code=ast.codeGeneration(); 
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".asm")); 
				out.write(code);
				out.close(); 
				System.out.println("Code generated! Assembling and running generated code.");

				FileInputStream isASM = new FileInputStream(fileName+".asm");
				ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
				SVMLexer lexerASM = new SVMLexer(inputASM);
				CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
				SVMParser parserASM = new SVMParser(tokensASM);

				SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
				visitorSVM.visit(parserASM.assembly());
				
				if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) {
					System.err.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
					System.exit(1);
				} else {
					System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
				}

				System.out.println("Starting Virtual Machine...");
				System.out.println();

				/*for(int i=0; i<20;i++) {
			        System.out.println(visitorSVM.code[i]);
		        }*/

				ExecuteVM vm = new ExecuteVM(visitorSVM.code);
				vm.cpu();
			}
		}


	}
}
