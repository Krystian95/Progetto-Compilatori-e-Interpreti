package models.stentry;

import models.type.SimpleType;

public abstract class SimpleSTEntry {

	private int nestingLevel;
	private SimpleType type;
	private int offset;
	private String id;
	private String mode;
	private boolean deleted = false;
	private SimpleSTEntry mappedEntry;


	public SimpleSTEntry (int nestingLevel, SimpleType type, int offset, String id) {
		this.nestingLevel = nestingLevel;
		this.type = type;
		this.offset = offset;
		this.id = id;
	}

	public int getOffset () {
		return offset;
	}

	public int getNestinglevel () {
		return nestingLevel;
	}

	public SimpleType getType(){
		return type;
	}
	
	public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public SimpleSTEntry getMappedEntry() {
		return mappedEntry;
	}

	public void setMappedEntry(SimpleSTEntry mappedEntry) {
		this.mappedEntry = mappedEntry;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String toPrint(String s) {

		return	s + "\n\tSimpleSTEntry: id " + id + "\n"+
				s + "\tSimpleSTEntry: nestlev " + Integer.toString(nestingLevel) + "\n"+
				s + "\tSimpleSTEntry: deleted " + (Boolean.toString(deleted)).toUpperCase() +"\n"+
				s + "\tSimpleSTEntry: offset " + Integer.toString(offset) + "\n"+
				s + "\tSimpleSTEntry: type " + type.toPrint("") + "\n";
	}	
}
