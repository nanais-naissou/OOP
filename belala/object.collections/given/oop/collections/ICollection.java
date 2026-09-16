package oop.collections;

public interface ICollection {
  
  public interface Iterator {

    /**
     * @return true if there is a next element.
     */
    public boolean hasNext();

    /**
     * @return the next element in the list.
     */
    public Object next();
    
    /*
     * Removes from the underlying collection the last element returned
     * by this iterator.  This method can be invoked only once per invocation
     * of the method "next", throws an illegal-state exception otherwise.
     */
    public Object remove();
  }

  /** 
   * Iterator on the elements of this collection
   * @return
   */
  Iterator iterator();

  /**
   * @return the length of the list
   */
  int length();

  /**
   * Copies the elements of the collection to the given array
   * @param elems
   */
  void toArray(Object elems[]);

  /*
   * This is the interface to implement for
   * anyone that wants to listen to what is
   * happening to a collection.
   */
  public interface Listener {
    /*
     * This listener has been added to the set of listeners
     * of the given collection.
     */
    void added(ICollection c);
    
    /*
     * This listener has been dropped from the set of listeners
     * of the given collection.
     */
    void dropped(ICollection c);
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
