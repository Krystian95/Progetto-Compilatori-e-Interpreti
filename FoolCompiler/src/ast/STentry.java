package ast;

import java.util.HashMap;

public class STentry {

	private int nl;
	private Node type;
	private int offset;
	private boolean deleted = false;
	private HashMap<String, STentry> parlist = new HashMap<String, STentry>();

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

	public HashMap<String, STentry> getParlist() {
		return parlist;
	}

	public void setParlist(HashMap<String, STentry> parlist) {
		this.parlist = parlist;
	}
}  