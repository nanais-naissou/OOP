package oop.utils.contacts;

import oop.contacts.IField.IValue;
import oop.contacts.IName;

/*
 * An interface modeling a simplified name of a person,
 * modeled as just a last name and a first name
 * (obviously a simplification of what a person's name
 *  can be in real life).
 */
public class Name implements IName {
	private String fi;
	private String la;

	public Name(String first, String last) {
		this.fi = first;
		this.la = last;
	}

	/*
	 * Returns the last name
	 */
	@Override
	public String last() {
		return la;
	}

	/*
	 * Returns the first name, if there is one, or "" otherwise.
	 */
	@Override
	public String first() {
		if(fi!=null) {return this.fi; }
		else return ""; 
	}

	/*
	 * Returns a string composed of the last and first name, in that order,
	 * separated by a single space, if this name has a first name part. Otherwise,
	 * the method returns only the last name part.
	 */
	@Override
	public String toString() {
	    if (fi == null || fi.isEmpty()) {
	        return la;
	    }
	    return la + " " + fi;
	}


	/*
	 * Returns true if two values are equal, otherwise false.
	 */
	@Override
	public boolean equals(IValue o) {
		if (o==null) {return false; }
		if (o instanceof IName ) {
		return this.toString().equals(o.toString());  }
		else { return false;}
	}

	/*
	 * Returns true if this value contains the given string, returns false
	 * otherwise.
	 * 
	 * A default implementation may be that this value, as a string, contains the
	 * given string. See java.lang.String.contains(String)boolean
	 */
	@Override
	public boolean contains(String s) {
		return this.toString().contains(s); 
	}

	/*
	 * Returns true if this value ends with the given string, returns false
	 * otherwise.
	 * 
	 * A default implementation may be that this value, as a string, starts with the
	 * given string. See java.lang.String.contains(String)boolean
	 */
	@Override
	public boolean startsWith(String s) {
		return this.toString().startsWith(s);
	}

	/*
	 * Returns true if this value ends with the given string, returns false
	 * otherwise.
	 * 
	 * A default implementation may be that this value, as a string, ends with the
	 * given string. See java.lang.String.contains(String)boolean
	 */
	@Override
	public boolean endsWith(String s) {
		return this.toString().endsWith(s); 
	}

}
