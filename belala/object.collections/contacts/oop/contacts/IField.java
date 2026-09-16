package oop.contacts;

public interface IField {

  public interface IValue {
    /*
     * Returns true if two values are equal, 
     * otherwise false.
     */
    boolean equals(IValue o);
   
    /*
     * Returns a string describing this value.
     */
    @Override
    String toString();
    
    /*
     * Returns true if this value contains the given string,
     * returns false otherwise.
     * 
     * A default implementation may be that this value,
     * as a string, contains the given string.
     * See java.lang.String.contains(String)boolean
     */
    boolean contains(String s);
    
    /*
     * Returns true if this value ends with the given string,
     * returns false otherwise.
     * 
     * A default implementation may be that this value,
     * as a string, starts with the given string.
     * See java.lang.String.contains(String)boolean
     */
    boolean startsWith(String s);

    /*
     * Returns true if this value ends with the given string,
     * returns false otherwise.
     * 
     * A default implementation may be that this value,
     * as a string, ends with the given string.
     * See java.lang.String.contains(String)boolean
     */
    boolean endsWith(String s);
  }

  String name();
  String name(String name);
  
  IValue value();
  IValue value(IValue value);
  
  /*
   * This method returns true if this field matches 
   * the given filter, returns false otherwise.
   * 
   * This field is said to match the given filter,
   * applied to its value, in the following cases:
   * 
   *   - If the filter is a plain value, containing no '*'
   *     this field matches the filter if 
   *     the field value is value-equal to the filter.
   *     
   *   - If the filter is a prefix filter, ending with an '*',
   *     this field matches the filter if 
   *     the field value starts with the given filter,
   *     without the '*'.
   *     
   *   - If the filter is a suffix filter, starting with an '*',
   *     this field matches the filter if 
   *     the field value ends with to the given filter,
   *     without the '*'.
   *     
   *   - If the filter is a substring filter, 
   *     starting and ending with an '*',
   *     this field matches the filter if 
   *     the field value contains the given filter,
   *     without the two '*'.
   *     
   *   - If the filter is "*", 
   *     the field is always a match.
   */
  boolean match(String filter);

}
