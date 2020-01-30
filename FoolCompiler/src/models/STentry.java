package models;

import java.util.LinkedHashMap;

public class STentry {

	private int nl;
	private Node type;
	private int offset;
	private String mode;
	private boolean deleted = false;
	private boolean deletedByFunCall = false;
	private LinkedHashMap<Integer, LinkedHashMap<String, STentry>> decParList = null;
	private STentry mappedEntry = null;

	public STentry (int n, int os){
		nl = n;
		offset = os;
	} 

	public STentry (int n, Node t, int os){
		nl = n;
		type = t;
		offset = os;
	}

	public void addType (Node t){
		type = t;
	}

	public Node getType () { return type; }

	public int getOffset () { return offset; }

	public int getNestinglevel () { return nl; }

	public String toPrint(String s) {

		return
				s + "STentry: nestlev " + Integer.toString(nl) + "\n"+
				s + "STentry: deleted " + (Boolean.toString(deleted)).toUpperCase() +"\n"+
				s + "STentry: deletedByFunCall " + (Boolean.toString(deletedByFunCall)).toUpperCase() +"\n"+
				s + "STentry: type" + type.toPrint("") +
				s + "STentry: offset " + Integer.toString(offset) + "\n";
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LinkedHashMap<Integer, LinkedHashMap<String, STentry>> getDecParlist() {
		return decParList;
	}

	public void setDecParList(LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlist) {
		this.decParList = parlist;
	}

	public boolean isDeletedByFunCall() {
		return deletedByFunCall;
	}

	public void setDeletedByFunCall(boolean deletedByFunCall) {
		this.deletedByFunCall = deletedByFunCall;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public STentry getMappedEntry() {
		return mappedEntry;
	}

	public void setMappedEntry(STentry mappedEntry) {
		this.mappedEntry = mappedEntry;
	}
}  
