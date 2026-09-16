package oop.contacts;

import oop.collections.IList;

/*
 * A contact is a set of fields, each field has a name 
 * and a value. For example:
 * 
 *   field: [name: "Gruber Olivier"]
 *   field: [phone: "+1 01 23 45 67 89"] 
 *   field: [email: "olivier.gruber@univ-grenoble-alpes.fr"]
 * 
 * At a minimum, a contact has two fields:
 * 
 *   - field "name", with the value being the name of a person.
 *   - field "phone", with the value being the phone number.
 * 
 * A contact is uniquely identified by its field "phone", 
 * but the field "name" does not. Said differently, 
 * two or more contacts may have the same names,
 * but they may not have the same phone numbers.
 */
public interface IContact {
   
  /*
   * @returns the cellular phone number of this contact
   *          corresponding to the field named "phone"
   */
  public IPhoneNumber phone();

  /*
   * @returns the name of this contact
   *          corresponding to the field named "name"
   */
  public IName name();

  /*
   * @returns an iterator on the fields, 
   */
  public IList.Iterator fields();

  /*
   * @returns the field that has the given name. 
   */
  public IField field(String name);
  
  /*
   * Applies the given updates to the fields of this contact,
   * either adding new fields or updating existing ones.
   * The two lists must be of the same length, obviously.
   * The list of names is a list of java.lang.String.
   * The list of values is a list of IField.IValue.
   */
  void update(IList names, IList values);

  /*
   * Either add a new field or updates an existing one.
   */
  void update(String name, IField.IValue value);


}
