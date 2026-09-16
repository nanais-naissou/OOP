package oop.utils.contacts;

import oop.contacts.IField.IValue;
import oop.contacts.IPhoneNumber;

public class PhoneNumber implements IPhoneNumber {
	private String phonenumber; 
	private int country_code; 
	/*
	 * An interface modeling a simplified phone number, which in real life is a
	 * complicated object because of the many formats a phone number has around the
	 * world.
	 * 
	 * A phone number is composed of two parts: - a country number (optional
	 * integer) - a phone number (as a string)
	 * 
	 * The phone number is solely composed of digits '0' through '9' and single
	 * spaces.
	 * 
	 * When translated to a string, a phone number is formated with the country code
	 * between parenthesis and then the phone number, like in the following like in
	 * the following examples:
	 * 
	 * (1) 212 555 1212 212 555 1212 (33) 06 34 56 78 90 06 34 56 78 90 3412 911
	 * 
	 * 
	 */
	
	
	public PhoneNumber (String phone, int cc) {
		this.phonenumber=phone;
		this.country_code=cc; 
	}
	@Override
	public boolean equals(IValue o) {
	    if (!(o instanceof IPhoneNumber)) return false;
	    return this.equals((IPhoneNumber) o);
	}

	@Override
	public boolean contains(String s) {
		return this.toString().contains(s);
	}

	@Override
	public boolean startsWith(String s) {
		return this.toString().startsWith(s);
	}

	@Override
	public boolean endsWith(String s) {
		return this.toString().endsWith(s); 
	}

	/*
	 * Returns the country code, the value zero means there is no country code for
	 * this phone number.
	 */
	@Override// TODO Auto-generated method stub
	public int country() {
		return this.country_code; 
	}

	/*
	 * Returns a string describing this phone number.
	 */
	@Override
	public String toString() {
		if (country_code!=0) {
			return '('+country_code+')'+phonenumber; 
		}
		else return phonenumber; 
	}

	/*
	 * Returns the phone number, without the country code.
	 */
	
	@Override
	public String number() {
		return this.phonenumber; 
		}

	/*
	 * Returns true if two phone numbers are equal, returns false otherwise.
	 * 
	 * The normal value equality between two phone numbers is true if and only if: -
	 * same country codes (compared as integers) - same local numbers (compared as
	 * strings)
	 * 
	 * But if any of the two phone numbers does not have a country code, then only
	 * the local numbers must be compared, as strings.
	 */
	@Override
	public boolean equals(IPhoneNumber o) {
		if (this.country_code==0 || o.country()==0) {
			return this.phonenumber.equals(o.number());
		}
		else 
		{
			return this.country_code==o.country()&& this.phonenumber.equals(o.number()); 
		}
	}

}
