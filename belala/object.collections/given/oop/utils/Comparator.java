package oop.utils;

public interface Comparator {
  /*
   * Compare the two given objects
   * Returns true if they are considered equal.
   * Returns false otherwise.
   */
  public boolean equals(Object o1, Object o2);
  
  /*
   * Returns true if this comparator can order
   * non-equal objects. Returns false otherwise.
   */
  public boolean hasOrder();
  
  /*
   * Returns true if the given object 'o1'
   * is strictly less than the given object 'o2'.
   * Returns false otherwise, including when
   * the object 'o1' is considered equal to 
   * the object 'o2'.
   * Throws an illegal-state exception if this
   * comparator does not support ordering.
   */
  public boolean lessThan(Object o1, Object o2);
}