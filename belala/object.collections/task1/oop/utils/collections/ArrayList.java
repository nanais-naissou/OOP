package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;
import oop.collections.IList.Listener;

public class ArrayList implements IList {
	private  Object[] tab;
	private int nb_elem;
	private int nb_listeners = 0;
	private IList.Listener[] listeners = new IList.Listener[4];

	/**
	 * Constructs an empty list.
	 */
	public ArrayList() {
		this.tab = new Object[32];
		this.nb_elem = 0;
	}

	/**
	 * Constructs a list, initialized with the elements from the given array.
	 */
	public ArrayList(Object array[]) {
		if (array == null) {
			throw new IllegalArgumentException("array null!!!!");
		}
		int length = array.length;
		this.tab = new Object[length + 32];
		this.nb_elem = length;
		System.arraycopy(array, 0, this.tab, 0, length);
	}

	/**
	 * Constructs a list, initialized with the elements from the given list.
	 */
	public ArrayList(ArrayList v) {
		if (v == null) {
			throw new IllegalArgumentException("listearray vide!!!!");
		}
		Integer length = v.nb_elem;
		this.tab = new Object[length + 32];
		this.nb_elem = length;
		System.arraycopy(v.tab, 0, this.tab, 0, nb_elem);

	}

	/**
	 * Constructs a list, initialized with the elements from the given collection.
	 */
	public ArrayList(ICollection c) {
		this.nb_elem = c.length();
		this.tab = new Object[nb_elem + 32];
		Object[] tmp = new Object[nb_elem];
		c.toArray(tmp);
		System.arraycopy(tmp, 0, this.tab, 0, nb_elem);

	}

	private class Iterator implements ICollection.Iterator {

		private int index_courant;
		private int index_avant;

		public Iterator() {
			this.index_courant = 0;
			this.index_avant = -1;
		}

		@Override
		public boolean hasNext() {
			return index_courant < nb_elem;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				index_avant = index_courant;
				return tab[index_courant++];
			} else {
				throw new IllegalStateException("n'a pas d'element next");
			}
		}

		/*
		 * Removes from the underlying collection the last element returned by this
		 * iterator. This method can be invoked only once per invocation of the method
		 * "next", throws an illegal-state exception otherwise.
		 */
		@Override
		public Object remove() {
			if (index_avant < 0) {
				throw new IllegalStateException();
			}
			Object tmp = tab[index_avant];
			ArrayList.this.removeAt(index_avant);
			this.index_courant = this.index_avant;
			this.index_avant = -1;// pour avoid remove 2 fois (un next pour avoir qu un seul dernier element avant
									// )
			return tmp;
		}

	}

	@Override
	public Iterator iterator() {
		return new Iterator();
	}

	/**
	 * @param index
	 * @return the element at the given index, if the index is valid.
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object elementAt(int index) {
		if ((index >= 0) && (index < nb_elem)) {
			return this.tab[index];
		} else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Updates the element at the given index, if the index is valid.
	 * 
	 * @param index
	 * @param niu
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object updateAt(int index, Object niu) {
		if ((index >= 0) && (index < nb_elem)) {
			Object tmp = this.tab[index];
			this.tab[index] = niu;
			for (int i = 0; i < nb_listeners; i++) {
				listeners[i].updated(this, index, tmp, niu);
			}
			return tmp;
		} else
			throw new IndexOutOfBoundsException("index out of bounds");
	}

	/**
	 * Insert the element at the given index. Nota Bene: if the index is greater
	 * than the list length, the list is grown.
	 * 
	 * @param index
	 * @param elem
	 * @throws IndexOutOfBoundsException if the index is negative.
	 */

	// PAS SUR A VERIFIERRRR!!!!!!!!!!!!!

	// cette méthode s'assure qu'il y a toujours suffisamment despace pour inserer
	// dans la lsite
	private void widenArray(int minlength) {
		if (minlength > tab.length) {
			int min = tab.length + 32;
			if (min < minlength) {
				min = minlength;
			}
			Object[] newElements = new Object[min];
			System.arraycopy(tab, 0, newElements, 0, nb_elem);
			tab = newElements;
		}
	}

	@Override
	public void insertAt(int index, Object elem) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("index<<0");
		}
		if (index > nb_elem) {
			widenArray(index + 1);
			for (int i = nb_elem; i < index; i++) {
				tab[i] = null;
			} // on remplit les "vides" inttermediaires
			tab[index] = elem;
			nb_elem = index + 1;
		} else {
			widenArray(nb_elem + 1);
			System.arraycopy(tab, index, tab, index + 1, nb_elem - index);// on décale a partir de index a droite jusqua
																			// size-index
			tab[index] = elem;
			nb_elem++;
		}
		for (int i = 0; i < nb_listeners; i++) {
			listeners[i].inserted(this, index, elem);
		}
	}

	/**
	 * Removes the element at the given index, if the index is valid.
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object removeAt(int index) {
		if (index < 0 || index >= nb_elem) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		Object removed = tab[index];
		for (int i = index; i < nb_elem - 1; i++) {
			tab[i] = tab[i + 1];
		}
		tab[nb_elem - 1] = null;
		nb_elem--;
		for (int i = 0; i < nb_listeners; i++) {
			listeners[i].removed(this, index, removed);
		}
		return removed;
	}

	/**
	 * Remove the element if found, otherwise does nothing
	 * 
	 * @param index_courant
	 * @return true if the element was found and removed
	 */
	public boolean remove(Object elem) {
		Iterator iter = iterator();
		while (iter.hasNext()) {
			if (iter.next() == (elem)) { // DEVRAIS UTILISER EQUALS ?? JCROIS PAS NAH
				iter.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * @param elem
	 * @return true if the given element is in the list, using object identity to
	 *         compare objects
	 */
	public boolean contains(Object elem) {
		Iterator iter = iterator();
		while (iter.hasNext()) {
			if (iter.next() == (elem)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Copies the list elements to the given array
	 * 
	 * @param elems
	 */
	public void toArray(Object elems[]) {
		if (elems == null || elems.length < nb_elem) {
			throw new IllegalArgumentException("tableau fourni nul ou trop petit!!!");
		}
		System.arraycopy(tab, 0, elems, 0, nb_elem);
	}

	@Override
	public int length() {
		return this.nb_elem;
	}

	@Override
	public void add(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return; }
		if (l instanceof IList.Listener) {
		    add((IList.Listener) l);
		}
		else {
			throw new IllegalArgumentException("add du listener collcetion echoué");
		}

	}

	@Override
	public void drop(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return; }
		if (l instanceof oop.collections.IList.Listener) {

		    drop((IList.Listener) l);
		} else {
			throw new IllegalArgumentException("drop du listener collcetion echoué");
		}
	}

	@Override
	public void add(IList.Listener l) {
		if (l == null)
			return;
		for (int i = 0; i < nb_listeners; i++)
			if (listeners[i].equals(l))
				return;
		if (nb_listeners == listeners.length) { // agrandir tableau
			IList.Listener[] tmp = new IList.Listener[listeners.length + 4];
			System.arraycopy(listeners, 0, tmp, 0, nb_listeners);
			listeners = tmp;
		}
		listeners[nb_listeners++] = l;
		l.added(this);
	}

	@Override
	public void drop(IList.Listener l) {
		if (l == null)
			return;
		for (int i = 0; i < nb_listeners; i++) {
			if (listeners[i].equals(l)) {
				for (int j = i; j < nb_listeners - 1; j++)
					listeners[j] = listeners[j + 1];
				listeners[--nb_listeners] = null;
				l.dropped(this);
				return;
			}
		}
	}

}
