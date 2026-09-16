package oop.collections;

public interface IList extends ICollection {

  /*
   * A simple factory to create list,
   * useful for polymorphic programming.
   */
  public interface Factory {
    IList newList();
    IList newList(IList list);
    IList newList(Object[] elements);
  }

  /**
   * @param index
   * @return the element at the given index, if the index is valid.
   * @throws IndexOutOfBoundsException otherwise.
   */
  Object elementAt(int index);

  /**
   * Updates the element at the given index, if the index is valid.
   * @param index
   * @param niu
   * @throws IndexOutOfBoundsException otherwise.
   */
  Object updateAt(int index, Object niu);

  /**
   * Insert the element at the given index.
   * Nota Bene: if the index is greater than the list length,
   *            the list is grown.
   * @param index
   * @param elem
   * @throws IndexOutOfBoundsException if the index is negative.
   */
  void insertAt(int index, Object elem);

  /**
   * Removes the element at the given index, if the index is valid.
   * @param index
   * @throws IndexOutOfBoundsException otherwise.
   */  
  Object removeAt(int index);

  /**
   * Remove the element if found, otherwise does nothing
   * @param index
   * @return true if the element was found and removed
   */
  boolean remove(Object elem);

  /**
   * @param elem
   * @return true if the given element is in the list,
   *         using object identity to compare objects
   */
  boolean contains(Object elem);

  /**
   * Copies the list elements to the given array
   * @param elems
   */
  void toArray(Object elems[]);

  /*
   * This is the interface to implement for any object
   * that wants to listen to what is happening to a list.
   */
  public interface Listener extends ICollection.Listener {
    
    /*
     * This is a notification that the given element has
     * been inserted as the given index.
     */
    void inserted(IList l, int index, Object element);
    
    /*
     * This is a notification that the given element has
     * been removed at the given index.
     */
    void removed(IList l, int index, Object element);

    /*
     * This is a notification that the element at the given
     * index has been updated, it was the given element "old",
     * it is now the given element "niu".
     */
    void updated(IList l, int index, Object old, Object niu);
  }
  
  /*
   * Add the given listener to set of the listeners of this 
   * collection. Because it is a set, a listener cannot be 
   * added twice, once added, successive attempts are ignored.
   */
  void add(Listener l);

  /*
   * Drop the given listener from the set of the listeners of this 
   * collection
   */
  void drop(Listener l);

}