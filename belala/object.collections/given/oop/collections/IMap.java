package oop.collections;

public interface IMap extends ICollection {
  
  /*
   * A simple interface for factories to create list,
   * useful for polymorphic programming.
   */
  public interface Factory {
    IMap newMap();
    IMap newMap(IMap map);
  }

  /*
   * The interface that defines the concept 
   * of keys for our (key,value) pairs.
   */
  public interface Key {
    
    /*
     * A simple interface for factories to create keys,
     * from values.
     */
    public interface Factory {
      Key newKey(Object value);
    }
    
    boolean equals(Key key);
    int     hashCode();
  }
  
  /** 
   * Returns an iterator on the values of this collection
   */
  @Override
  Iterator iterator();

  /**
   * This method copies the values to the given array,
   * not the keys, since a map is a collection of values, 
   * indexed by their keys.
   * @param elems
   */
  @Override
  void toArray(Object values[]);

  /**
   * Returns an iterator on the keys in this map.
   */
  Iterator keys();

  /**
   * Returns an iterator on the values in this map.
   */
  Iterator values();

  /**
   * The get method performs a lookup, it finds if the given key is known. 
   * If it is, the associated value is returned, otherwise null is returned.
   * Keys are compared using == and Key:equals(Key)boolean
   */
  Object get(Key key);

  /**
   * The put method adds or updates a pair to the associative collection. 
   * If the key is unknown, the pair is added, and the method returns null. 
   * If the key is known, the value is updated with the given value and 
   * the replaced value is returned.
   * Keys are compared using == and Key:equals(Key)boolean
   */
  Object put(Key key, Object value);

  /**
   * The method remove is fairly straightforward, 
   * it removes the pair identified by the given key from the collection. 
   * If the pair was unknown, null is returned. 
   * Otherwise, the value associated with the given key is returned.
   * Keys are compared using == and Key:equals(Key)boolean
   */
  Object remove(Key key);

  /**
   * The method contains returns true if the map contains a pair
   * with the given key.
   * Keys are compared using == and Key:equals(Key)boolean
   */
  boolean contains(Key key);

  /**
   * Copies the keys to the given array
   * @param elems
   */
  void keysToArray(Key keys[]);

  /**
   * Copies the values to the given array
   * @param elems
   */
  void valuesToArray(Object values[]);

  /*
   * This is the interface to implement for any object
   * that wants to listen to what is happening to a list.
   */
  public interface Listener extends ICollection.Listener {
    
    /*
     * This is a notification that the given pair has
     * been added to this map.
     */
    void added(IMap m, Key key, Object value);
    
    /*
     * This is a notification that the given pair has
     * been removed from this map.
     */
    void removed(IMap m, Key key, Object value);

    /*
     * This is a notification that the given pair has been
     * updated in this map.
     */
    void updated(IMap m, Key key, Object old, Object niu);
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