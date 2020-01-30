package analyser;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import models.Environment;
import models.SimpleElementBase;
import models.SimpleVisitorImpl;
import models.type.SimpleType;
import models.vm.ExecuteVM;
import models.vm.SVMVisitorImpl;
import parser.ComplexStaticAnalysisLexer;
import parser.ComplexStaticAnalysisParser;
import parser.SVMLexer;
import parser.SVMParser;
import util.SemanticError;
import util.SyntaxError;
import util.SyntaxErrorListener;

public class Analyse {

	public static void main(String[] args) throws Exception {

		String fileName = "test.spl";

		try{ 
			FileInputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
			//create lexer
			ComplexStaticAnalysisLexer lexer = new ComplexStaticAnalysisLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SyntaxErrorListener listener = new SyntaxErrorListener();

			//create parser
			ComplexStaticAnalysisParser parser = new ComplexStaticAnalysisParser(tokens);

			parser.removeErrorListeners();
			parser.addErrorListener(listener);
			//tell the parser to build the AST
			parser.setBuildParseTree(true);
			//call method syntax error			

			//build custom visitor
			SimpleVisitorImpl visitor = new SimpleVisitorImpl();
			//visit the root, this will recursively visit the whole tree
			SimpleElementBase ast = visitor.visit(parser.block());

			List<SyntaxError> syntaxErrors = listener.getSyntaxErrors() ;
			//check lexical
			if(!syntaxErrors.isEmpty()) {
				System.err.println("Check lexical: FAILED");	
				System.err.println("You had " + syntaxErrors.size() + " errors:");
				BufferedWriter outSyntax = new BufferedWriter(new FileWriter(fileName+".syntaxError")); 
				for(SyntaxError err: syntaxErrors) {
					String syntaxErr = "line " + err.getLine() + ": " + err.getCharPositionInLine() + " " + err.getMessage() + "\n";
					outSyntax.write(syntaxErr);
					System.err.println("\tAt line " + err.getLine() + " char " + err.getCharPositionInLine() + " "
							+err.getMessage());
				}
				outSyntax.close();
				System.exit(0);
			} else {
				System.out.println("\n>> Check lexical: OK\n");

				//check semantics
				//give a fresh environment, no need to make it persist
				//this is just semantic checking
				List<SemanticError> SemanticErrors = ast.checkSemantics(new Environment());

				//this means the semantic checker found some errors
				if(!SemanticErrors.isEmpty()){
					System.err.println("Check semantics: FAILED");	
					System.err.println("You had " + SemanticErrors.size() + " errors:");				
					for(SemanticError err: SemanticErrors)
						System.err.println("\t" + err);
					System.exit(0);
				} else {
					System.out.println("\n>> Check semantics: OK\n");
					
					SimpleType type = ast.typeCheck(); //type-checking bottom-up 
					if(type == null) {
						System.out.println("\n>> Type check: OK\n");
					}
					
					System.out.println("Visualizing AST...\n");
					System.out.println(ast.toPrint(""));

				}
				// CODE GENERATION  prova.fool.asm
				String code = ast.codeGeneration(); 
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
				System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
				if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);
				System.out.println("Starting Virtual Machine...");
				ExecuteVM vm = new ExecuteVM(visitorSVM.code);
				vm.cpu();
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
