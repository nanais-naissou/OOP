package oop.contacts;

import oop.collections.IList;
import oop.contacts.IField.IValue;

/*
 * This is the interface to define the management of contacts,
 * typical of the contact management found in our smart phones.
 * 
 * We are concerned here solely about the managed contacts, 
 * not the graphical user interface that you are used to on your phone.
 * To interact with the contact application, you have been provided
 * with a command-line shell (class oop.contacts.shell.Shell, 
 * launched by the class oop.contacts.shell.Main).
 */
public interface IContacts {

  /*
   * Returns the contact that has the given phone number
   */
  IContact get(IPhoneNumber phone);

  /*
   * Deletes the given contact. 
   */
  void delete(IContact c);

  /*
   * Deletes the contact that has the given phone number.
   * Returns the deleted contact if it was found, otherwise
   * returns null.
   */
  IContact delete(IPhoneNumber phone);

  /*
   * Adds a new contact with the given contact name 
   * and phone number. Returns the newly created contact.
   * Throws an illegal-argument exception if there is already 
   * a contact with the given phone number. 
   */
  IContact add(IName name, IPhoneNumber phone);

  /*
   * This is a method that select contacts that
   * have a field with the given name and 
   * the fields match the given filter.
   * See the method IField.match(String) for details
   * on how a field matches a filter.
   */
  IList.Iterator select(String name, String filter);

  /*
   * Factory method to build the values 
   * for the name fields of contacts.
   * The given strings must be trimmed 
   * before creating the name value.
   * See java.lang.String.trim().
   * The argument "first" may be null.
   */
  IName newName(String last, String first);

  /*
   * Factory method to build the values 
   * for the phone fields of contacts.
   * 
   * A phone object is built from a string, to which  
   * we apply the following simple cleaning-up rules:
   * 
   *   - Any dash ('-') is replaced with a space (' ')
   *   - Any dot ('.') is replaced with a space (' ')
   *   - Any double space ('  ') is replaced with a space (' ')
   *   - The resulting string is trimmed 
   *     (see java.lang.String.trim())
   * Once the cleaning process happened, the phone number
   * must respect the following regexp:
   *     ['0'-'9']+ (' ' ['0'-'9']+ )*
   */
  IPhoneNumber newPhoneNumber(int country, String number);

  /*
   * Factory method to build the values 
   * for the regular fields of contacts,
   * do not use for the phone or name fields.
   */
  IValue newValue(String value);

}