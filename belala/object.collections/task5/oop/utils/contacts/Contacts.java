package oop.utils.contacts;

import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.collections.IMap;
import oop.collections.IMap.Key;
import oop.contacts.IContact;
import oop.contacts.IContacts;
import oop.contacts.IField.IValue;
import oop.contacts.IField;
import oop.contacts.IName;
import oop.contacts.IPhoneNumber;
import oop.utils.collections.BucketMap;
import oop.utils.collections.LinkedList;

public class Contacts implements IContacts {
	// ici on va utiliser une map avec comme valeurs pour les clés les num de
	// téléphones
	// puisque ces derniers sont uniques pour chaque contact (smart ;) )

	private IMap contacts;

	public Contacts() {
		this.contacts = new BucketMap(10);
	}

	class PhoneNumberKey implements IMap.Key {
		private IPhoneNumber number;
		private int hashcode;

		public PhoneNumberKey(IPhoneNumber nb) {
			this.number = nb;
			String num = number.number();
			if (number.country() == 0) {
				this.hashcode = num.hashCode();
			} else {
				this.hashcode = 31 * number.country() + num.hashCode();
			}

		}

		@Override
		public boolean equals(Key key) {
			PhoneNumberKey phone_key = (PhoneNumberKey) key;
			return this.number.equals(phone_key.number);
		}

		@Override
		public int hashCode() {
			return this.hashcode;
		}
	}

	/*
	 * Returns the contact that has the given phone number
	 */
	@Override
	public IContact get(IPhoneNumber phone) {
		PhoneNumberKey phone_key = new PhoneNumberKey(phone);
		return (IContact) this.contacts.get(phone_key);
	}
	/*
	 * Deletes the given contact.
	 */

	@Override
	public void delete(IContact c) {

		PhoneNumberKey phone_key = new PhoneNumberKey(c.phone());
		this.contacts.remove(phone_key);
	}

	/*
	 * Deletes the contact that has the given phone number. Returns the deleted
	 * contact if it was found, otherwise returns null.
	 */
	@Override
	public IContact delete(IPhoneNumber phone) {
		PhoneNumberKey phone_key = new PhoneNumberKey(phone);
		IContact removed = (IContact) this.contacts.remove(phone_key);
		return removed;
	}

	/*
	 * Adds a new contact with the given contact name and phone number. Returns the
	 * newly created contact. Throws an illegal-argument exception if there is
	 * already a contact with the given phone number.
	 */
	@Override
	public IContact add(IName name, IPhoneNumber phone) {
		PhoneNumberKey phone_key = new PhoneNumberKey(phone);
		if (this.contacts.contains(phone_key)) {
			throw new IllegalStateException("ajout impossible, contact déja existant");
		} else {
			IField namef = new Field("name", name);
			IField phonef = new Field("phone", phone);
			IContact contact = new Contact(namef, phonef);
			this.contacts.put(phone_key, contact);
			return contact;
		}
	}

	/*
	 * This is a method that select contacts that have a field with the given name
	 * and the fields match the given filter. See the method IField.match(String)
	 * for details on how a field matches a filter.
	 */
	@Override
	public Iterator select(String name, String filter) {
	    IList matches = new LinkedList();
	    Iterator it = contacts.values(); 
	    while (it.hasNext()) {
	        IContact c = (IContact) it.next();
	        Iterator fit = c.fields();

	        while (fit.hasNext()) {
	            IField f = (IField) fit.next();
	            if (f.name().equals(name) && f.match(filter)) {
	                matches.insertAt(matches.length(), c);
	                break; 
	            }
	        }
	    }
	    return matches.iterator();
	}



	/*
	 * Factory method to build the values for the name fields of contacts. The given
	 * strings must be trimmed before creating the name value. See
	 * java.lang.String.trim(). The argument "first" may be null.
	 */
	@Override
	public IName newName(String last, String first) {
		last= last.trim();
		first = first.trim();
		IName newname = new Name(last, first);
		return newname;
	}

	/*
	 * Factory method to build the values for the phone fields of contacts.
	 * 
	 * A phone object is built from a string, to which we apply the following simple
	 * cleaning-up rules:
	 * 
	 * - Any dash ('-') is replaced with a space (' ')
	 *  - Any dot ('.') is replaced
	 * with a space (' ') 
	 * - Any double space (' ') is replaced with a space (' ') -
	 * The resulting string is trimmed (see java.lang.String.trim()) Once the
	 * cleaning process happened, the phone number must respect the following
	 * regexp: ['0'-'9']+ (' ' ['0'-'9']+ )*
	 */
	@Override
	public IPhoneNumber newPhoneNumber(int country, String number) {
	    String numero = "";

	    for (int i = 0; i < number.length(); i++) {
	        char c = number.charAt(i);

	        if (c == '-' || c == '.') {
	            c = ' ';
	        }
	        if (c == ' ' && numero.length() > 0 && numero.charAt(numero.length() - 1) == ' ') {
	            continue;
	        }

	        numero += c;
	    }

	    numero = numero.trim();
	    return new PhoneNumber(numero, country);
	}


	/*
	 * Factory method to build the values for the regular fields of contacts, do not
	 * use for the phone or name fields.
	 */
	@Override
	public IValue newValue(String value) {
	    return new Value(value.trim());

	}
}