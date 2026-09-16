package oop.utils.tests;

import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.collections.IMap;
import oop.collections.IMap.Key;
import oop.utils.Comparator;
import oop.utils.collections.ListMap;

public class MapUtils {

	/*
	 * Add the given pairs (key,value) to the given map.
	 */
	public static void add(IMap map, Key keys[], Object values[]) {
		for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) throw new IllegalArgumentException("Null key not allowed");

			if (values[i] == null)
				
			    throw new IllegalArgumentException("Null values not allowed in map 1");
			Object old = map.put(keys[i], values[i]);
			if (old != null) {
			    map.put(keys[i], old); 
			}

		}
	}

	/*
	 * Remove from the map the pairs (key,value) that have the given keys.
	 */
	public static void remove(IMap map, Key keys[]) {
		for (int i = 0; i < keys.length; i++) {
			map.remove(keys[i]);
		}
	}

	/*
	 * Updates the map with the given pairs, note that some of the given keys may
	 * already exist in the map. Returns the list of keys that were found and for
	 * which the corresponding values were updated.
	 */
	
	public static Key[] update(IMap map, Key keys[], Object values[]) {
		int j = 0;
		Key[] cles_tmp = new Key[keys.length];
		for (int i = 0; i < keys.length; i++) {
			if (values[i] == null)
			    throw new IllegalArgumentException("Null values not allowed in map 2");
			Object tmp = map.get(keys[i]); 
			if (tmp != null) {
				map.put(keys[i], values[i]);

				cles_tmp[j++] = keys[i];
			}

		}
		Key[] cles= new Key[j]; 
		for (int i=0; i<j; i++) {
			cles[i]=cles_tmp[i];
		}
		return cles;
	}

	/*
	 * }
	 * 
	 * /* Removes from the given map the pairs whose values are equal to any objects
	 * in the given array. The notion of equality between elements is given by the
	 * comparator.
	 */
	public static void remove(IMap map, Comparator c, Object values[]) {
		Iterator iter_values = map.values();
		while (iter_values.hasNext()) {
			  Object current = iter_values.next();
			for (int i = 0; i < values.length; i++) {
				if (c.equals(current, values[i])) {
					iter_values.remove();
				}
			}
		}
	}


	/*
	 * Filters the values from the given source map, keeping only those that are not
	 * equal to any of the objects in the given array. The kept pairs are put in the
	 * destination map, the source map is left unmodified. The notion of equality
	 * between elements is given by the comparator.
	 */
	public static void filter(IMap src, IMap dst, Comparator c, Object values[]) {
		//on fait une copie de src dans dst
		Key[] cles = new Key[src.length()];

		Object[] valeurs = new Object[src.length()]; 
		src.keysToArray(cles); 
		src.valuesToArray(valeurs);
		
		for (int i = 0; i < src.length(); i++) {
			if (valeurs[i] == null)
			    throw new IllegalArgumentException("Null values not allowed in map 3");
			dst.put(cles[i], valeurs[i]);
		}	
		//ensuite on applique la methode remove codé precedemment :)
		remove(dst,c,values); 
		}

	/*
	 * Add the elements from the list to the given map, as values, task3.md
	 * 2025-11-12 / using the given key factory to create a key for each one.
	 */
	public static void toSet(IMap map, Key.Factory kf, IList list) {
		for (int i = 0; i < list.length(); i++) {
			Object val = list.elementAt(i); 
			Key cle = kf.newKey(val);
			if (val == null)
			    throw new IllegalArgumentException("Null values not allowed in map 4");

			map.put(cle, val);
	}}
}
