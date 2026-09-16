package oop.utils.contacts;

import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.contacts.IContact;
import oop.contacts.IField;
import oop.contacts.IField.IValue;
import oop.contacts.IName;
import oop.contacts.IPhoneNumber;
import oop.utils.collections.LinkedList;

public class Contact implements IContact {

	private IList fields;
	private IField phonenumber;
	private IField name;

	public Contact(IField pn, IField n) {
		this.phonenumber = pn;
		this.name = n;
		this.fields = new LinkedList();
		this.fields.insertAt(0, phonenumber);
		this.fields.insertAt(1, name);

	}

	@Override
	public IPhoneNumber phone() {
		return (IPhoneNumber) this.phonenumber.value();
	}

	@Override
	public IName name() {
		return (IName) this.name.value();
	}

	@Override
	public Iterator fields() {
		return this.fields.iterator();
	}

	@Override
	public IField field(String name) {
		Iterator iter = fields();
		while (iter.hasNext()) {
			IField cell = (IField) iter.next();

			if (cell.name().equals(name)) {
				return cell;
			}
		}
		return null;
	}

	/*
	 * Applies the given updates to the fields of this contact, either adding new
	 * fields or updating existing ones. The two lists must be of the same length,
	 * obviously. The list of names is a list of java.lang.String. The list of
	 * values is a list of IField.IValue.
	 */

	@Override
	public void update(IList names, IList values) {
	    if (names.length() != values.length()) {
	        throw new IllegalStateException("longueur des listes incompatibles");
	    }

	    for (int i = 0; i < names.length(); i++) {
	        String n = (String) names.elementAt(i);
	        IValue v = (IValue) values.elementAt(i);

	        boolean found = false;
	        Iterator it = fields();

	        while (it.hasNext()) {
	            IField f = (IField) it.next();
	            if (f.name().equals(n)) {
	                f.value(v);
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            fields.insertAt(fields.length(), new Field(n, v));
	        }
	    }
	}


	/*
	 * Either add a new field or updates an existing one.
	 */
	public void update(String name, IField.IValue value) {
		Iterator iter = fields();
		while (iter.hasNext()) {
			IField cell = (IField) iter.next();

			if (cell.name().equals(name)) {
				cell.value(value);
				return;
			}

		}
		IField new_field = new Field(name, value);
		this.fields.insertAt(fields.length(), new_field);
	}

}
