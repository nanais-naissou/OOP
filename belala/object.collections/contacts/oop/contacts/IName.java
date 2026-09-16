package oop.contacts;

/*
 * An interface modeling a simplified name of a person,
 * modeled as just a last name and a first name
 * (obviously a simplification of what a person's name
 *  can be in real life).
 */
public interface IName extends IField.IValue {
  /*
   * Returns the last name
   */
  String last();

  /*
   * Returns the first name, if there is one, 
   * or "" otherwise.
   */
  String first();
  
  /*
   * Returns a string composed of the last and first name,
   * in that order, separated by a single space, if this
   * name has a first name part. Otherwise, the method 
   * returns only the last name part.
   */
  @Override
  String toString();
}
