package oop.utils.tests;

import oop.collections.IList;
import oop.utils.Comparator;

public class ListUtils {
	/*
	 * Add the given elements to the given list, preserving the order of the
	 * elements in the given array.
	 */
	public static void add(IList list, Object elements[]) {
		for (Object e : elements) {
			list.insertAt(list.length(), e);
		}
	}

	/*
	 * For each element of the given array, this function removes all occurrences of
	 * that element from the given list.
	 */
	public static void remove(IList list, Object elements[]) {
		for (Object e : elements) {
			while (list.remove(e)) {
			}
		}
	}

	/*
	 * Returns a string composed of the elements of the given list, translated to
	 * strings, and separated by commas.
	 *
	 * The simple case is if the list contains strings. For example, the list
	 * ("one", "two", "three"). task1.md 2025-11-12 / The return string would be:
	 * "one, two, three".
	 *
	 * But the given list may contain any kind of objects. Fortunately, any object
	 * can be translated to a string by invoking the method
	 * "Object:toString()String".
	 */
	public static String toString(IList list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length(); i++) {
			sb.append(list.elementAt(i));
			if (i < list.length() - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	/*
	 * Reverses the source list (src) into the destination list (dst), after this
	 * function finishes, the two lists have the same elements, but in reverse
	 * order, the first one in one of the lists is the last one in the other list,
	 * and so on.
	 */
	public static void reverse(IList src, IList dst) {
		for (int i = src.length() - 1; i >= 0; i--) {
			dst.insertAt(dst.length(), src.elementAt(i));
		}
	}

	/*
	 * Orders the elements of the source list into the destination list, in
	 * increasing order, the order being given by the given comparator. Throws an
	 * illegal-argument exception if the given comparator does not support an order
	 * and only supports equality.
	 */
	public static void order(IList src, IList dst, Comparator c) {
		if (!c.hasOrder()) {
			throw new IllegalArgumentException("comparator cantt order elements");
		}
		for (int i = 0; i < src.length(); i++) {
			dst.insertAt(dst.length(), src.elementAt(i));
		}

		bubbleSort(dst, c);
	}

	/*
	 * Inserts the given new element in the given list, keeping the list ordered in
	 * increasing order, based on the order defined by the given comparator. Throws
	 * an illegal-argument exception if the given comparator does not support an
	 * order and only supports equality.
	 */
	public static void order(IList list, Comparator c, Object elem) {
		if (!c.hasOrder()) {
			throw new IllegalArgumentException("comparator cantt order elements");
		}
		for (int i = 0; i < list.length(); i++) {
			if (c.lessThan(elem, list.elementAt(i))) {
				list.insertAt(i, elem);
				return;
			}
		}
		list.insertAt(list.length(), elem);// cas dernier eleement

	}

	/*
	 * Bubble sort, as explained on Wikipedia, is a simple sorting algorithm that
	 * repeatedly steps through the given list element by element, comparing the
	 * current element with the one after it, swapping their values if needed. These
	 * passes through the list are repeated until no swaps have to be performed
	 * during a pass, meaning that the list has become fully sorted. The algorithm,
	 * which is a comparison sort, is named for the way the larger or smallest
	 * elements "bubble" up to the top of the list. Throws an illegal-argument
	 * exception if the given comparator task1.md 2025-11-12 / does not have an
	 * order.
	 */
	public static int bubbleSort(IList list, Comparator c) {
		if (!c.hasOrder()) {
			throw new IllegalArgumentException("comparator cantt order elements");
		}
		int swaps = 0;
		boolean swapped;
		for (int i = 0; i < list.length() - 1; i++) {
			swapped = false;

			for (int j = 0; j < list.length() - 1 - i; j++) {
				Object current = list.elementAt(j);
				Object next = list.elementAt(j + 1);
				if (c.lessThan(next, current)) {
					list.updateAt(j, next);
					list.updateAt(j + 1, current);
					swapped = true;
					swaps++;
				}
			}
			if (!swapped) {
				break;
			}

		}
		return swaps;

	}

	/*
	 * Forces the given list to be a set, that is, making sure that each element
	 * only appears once in that list. The notion of equality between elements is
	 * given by the comparator.
	 */
	public static void toSet(IList list, Comparator c) {//eneleve les occurences
	    for (int i = 0; i < list.length() - 1; i++) {
	        int j = i + 1;
	        while (j < list.length()) {
	            if (c.equals(list.elementAt(i), list.elementAt(j))) {
	                list.removeAt(j); 
	            } else {
	                j++; 
	            }
	        }
	    }
	}

	/*
	 * Returns the first position of the given object `o` in the given list, if
	 * found. Otherwise, returns -1. The notion of equality between elements is
	 * given by the comparator.
	 */
	public static int findFirst(IList list, Comparator c, Object o) {
		    for (int i = 0; i < list.length(); i++) {
		        if (c.equals(list.elementAt(i), o)) {
		            return i;
		        }
		    }
		    return -1;
		}


	/*
	 * Returns the last position of the given object `o` in the given list, if
	 * found. Otherwise, returns -1. The notion of equality between elements is
	 * given by the comparator.
	 */
	public static int findLast(IList list, Comparator c, Object o) {
		    for (int i = list.length()-1; i>=0 ; i--) {
		        if (c.equals(list.elementAt(i), o)) {
		            return i;
		        }
		    }
		    return -1; 
		}


	/*
	 * Returns the objects, from the given array of elements, that are found in the
	 * given list. The same object may appear multiple times in the given array, but
	 * each found object must appear only once in the return array.
	 */
	public static Object[] find(IList list, Object elements[]) {
	    if (elements == null || elements.length == 0) {
	        return null;
	    }
	    Object[] found = new Object[32]; 
	    int nbFound = 0;

	    for (Object elem : elements) {
	        boolean alreadyAdded = false;
	        for (int k = 0; k < nbFound; k++) {
	            if (found[k] == elem) { 
	                alreadyAdded = true;
	                break;
	            }
	        }
	        if (alreadyAdded) continue;
	        IList.Iterator iter = list.iterator();
	        boolean exists = false;
	        while (iter.hasNext()) {
	            Object o = iter.next();
	            if (o == elem) {
	                exists = true;
	                break;
	            }
	        }
	        if (exists) {
	            if (nbFound >= found.length) {
	                Object[] tmp = new Object[found.length + 32];
	                System.arraycopy(found, 0, tmp, 0, nbFound);
	                found = tmp;
	            }
	            found[nbFound++] = elem;
	        }
	    }
	    Object[] result = new Object[nbFound];
	    System.arraycopy(found, 0, result, 0, nbFound);
	    return result;
	}

}
