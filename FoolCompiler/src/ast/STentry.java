package ast;

import java.util.LinkedHashMap;

public class STentry {

	private int nl;
	private Node type;
	private int offset;
	private String mode;
	private boolean deleted = false;
	private boolean deletedByFunCall = false;
	private LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlist = new LinkedHashMap<Integer, LinkedHashMap<String, STentry>>();

	public STentry (int n, int os)
	{nl=n;
	offset=os;} 

	public STentry (int n, Node t, int os)
	{nl=n;
	type=t;
	offset=os;}

	public void addType (Node t)
	{type=t;}

	public Node getType ()
	{return type;}

	public int getOffset () {return offset;}

	public int getNestinglevel () {return nl;}

	public String toPrint(String s) {

		return
				s + "STentry: nestlev " + Integer.toString(nl) + "\n"+
				s + "STentry: deleted " + (Boolean.toString(deleted)).toUpperCase() +"\n"+
				s + "STentry: type" + type.toPrint("") +
				s + "STentry: offset " + Integer.toString(offset) + "\n";
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LinkedHashMap<Integer, LinkedHashMap<String, STentry>> getParlist() {
		return parlist;
	}

	public void setParlist(LinkedHashMap<Integer, LinkedHashMap<String, STentry>> parlist) {
		this.parlist = parlist;
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
}  