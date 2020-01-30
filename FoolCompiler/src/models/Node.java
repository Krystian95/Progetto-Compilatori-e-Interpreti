package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import util.SemanticError;

public interface Node {

	Set<STentry> deletionsThenBranch = new HashSet<>();
	Set<STentry> deletionsElseBranch = new HashSet<>();

	Set<STentry> assignmentsThenBranch = new HashSet<>();
	Set<STentry> assignmentsElseBranch = new HashSet<>();

	String toPrint(String indent);

	Node typeCheck();

	String codeGeneration();

	ArrayList<SemanticError> checkSemantics(Environment env);

}  