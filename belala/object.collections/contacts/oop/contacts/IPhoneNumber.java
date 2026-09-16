package oop.contacts;

/*
 * An interface modeling a simplified phone number, 
 * which in real life is a complicated object because
 * of the many formats a phone number has around 
 * the world. 
 * 
 * A phone number is composed of two parts:
 *   - a country number (optional integer)
 *   - a phone number (as a string)  
 * 
 * The phone number is solely composed of digits '0' 
 * through '9' and single spaces.
 * 
 * When translated to a string, a phone number 
 * is formated with the country code between parenthesis
 * and then the phone number, like in the following 
 * like in the following examples:
 * 
 *   (1) 212 555 1212
 *   212 555 1212 
 *   (33) 06 34 56 78 90
 *   06 34 56 78 90
 *   3412
 *   911
 */
public interface IPhoneNumber extends IField.IValue {
  
  /*
   * Returns a string describing this phone number.
   */
  String toString();
  
  /*
   * Returns the country code, 
   * the value zero means there is no country code
   * for this phone number. 
   */
  int country();
  
  /*
   * Returns the phone number, without the country code.
   */
  String number();
  
  /*
   * Returns true if two phone numbers are equal, 
   * returns false otherwise.
   * 
   * The normal value equality between two phone numbers
   * is true if and only if:
   *   - same country codes (compared as integers)
   *   - same local numbers (compared as strings)
   * 
   * But if any of the two phone numbers does not have 
   * a country code, then only the local numbers must be 
   * compared, as strings.
   */
  boolean equals(IPhoneNumber o);
}
