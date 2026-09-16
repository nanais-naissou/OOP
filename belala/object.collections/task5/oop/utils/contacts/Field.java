package oop.utils.contacts;


import oop.contacts.IField;

public class Field implements IField {
	private String name; 
	private IValue value; 
	
	public Field(String n, IValue v) {
		this.name=n; 
		this.value=v; 
	}
	@Override
	public String name() {
		return this.name; 
	}


	@Override
	public String name(String name) {
		String old= this.name; 
		this.name=name; 
		return old;
	}

	@Override
	public IValue value() {
		return this.value; 
	}

	@Override
	public IValue value(IValue value) {
		IValue old = this.value; 
		this.value=value; 
		return old; 
	}

	/*
	 * This method returns true if this field matches the given filter, returns
	 * false otherwise.
	 * 
	 * This field is said to match the given filter, applied to its value, in the
	 * following cases:
	 * 
	 * - If the filter is a plain value, containing no '*' this field matches the
	 * filter if the field value is value-equal to the filter.
	 * 
	 * - If the filter is a prefix filter, ending with an '*', this field matches
	 * the filter if the field value starts with the given filter, without the '*'.
	 * 
	 * - If the filter is a suffix filter, starting with an '*', this field matches
	 * the filter if the field value ends with to the given filter, without the '*'.
	 * 
	 * - If the filter is a substring filter, starting and ending with an '*', this
	 * field matches the filter if the field value contains the given filter,
	 * without the two '*'.
	 * 
	 * - If the filter is "*", the field is always a match.
	 */
	@Override
	public boolean match(String filter) {

	    if (filter.equals("*")) {
	        return true;
	    }

	    if (filter.startsWith("*") && filter.endsWith("*")) {
	        String sub = filter.substring(1, filter.length() - 1);
	        return value.contains(sub);
	    }

	    if (filter.endsWith("*")) {
	        String prefix = filter.substring(0, filter.length() - 1);
	        return value.startsWith(prefix);
	    }

	    if (filter.startsWith("*")) {
	        String suffix = filter.substring(1);
	        return value.endsWith(suffix);
	    }

	    return value.equals(new Value(filter));
	}

}
