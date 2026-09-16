package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;
import oop.collections.IMap;

public class ListMap implements IMap {

	private class Couple {
		Object key;
		Object value;

		Couple(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	private IList pairs; // list of pairs (key,value)
	private int size;
	private int nb_listeners = 0;
	private IMap.Listener[] listeners = new IMap.Listener[4];

	public ListMap() {
		this.pairs = new LinkedList();
		this.size = pairs.length();
	}

	public ListMap(IList.Factory lf) {
		this.pairs = lf.newList();
		this.size = pairs.length();

	}

	public ListMap(IMap map) {
		this.pairs = new LinkedList();
		IMap.Iterator it = map.keys();
		while (it.hasNext()) {
			Key clé = (Key) it.next();
			Object value = map.get(clé);
			Couple pair = new Couple(clé, value);
			this.pairs.insertAt(size, pair);
			size++;
		}
	}

	public ListMap(IMap map, IList.Factory lf) {
		this.pairs = lf.newList();
		IMap.Iterator it = map.keys();
		this.size = 0;
		while (it.hasNext()) {
			Key clé = (Key) it.next();
			Object value = map.get(clé);
			Couple pair = new Couple(clé, value);
			this.pairs.insertAt(size, pair);
			size++;
		}

	}

	@Override
	public int length() {
		    return pairs.length();
		
	}

	private class Iterator implements ICollection.Iterator {

		private int index_courant;
		private int index_prec;

		public Iterator() {
			this.index_courant = 0;
			this.index_prec = -1;
		}

		@Override
		public boolean hasNext() {
			return index_courant < ListMap.this.length();
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new IllegalStateException();
			Couple pair = (Couple) ListMap.this.pairs.elementAt(index_courant);
			index_prec = index_courant;
			index_courant++;
			return pair.value;
		}

		@Override
		public Object remove() {
			if (index_prec == -1) {
				throw new IllegalStateException("un seul remove par next");
			}
			Couple pair = (Couple) ListMap.this.pairs.removeAt(index_prec);
		    ListMap.this.size = ListMap.this.pairs.length();
			index_courant--;
			index_prec = -1;
			return pair.key;

		}
	}

	private static class KeysIterator implements ICollection.Iterator {

		int index_courant = 0;
		int index_prec = -1;
		private ListMap map;

		public KeysIterator(ListMap map) {
			this.map = map;
		}

		@Override
		public boolean hasNext() {
			return index_courant < this.map.length();
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new IllegalStateException();
			Couple pair = (Couple) map.pairs.elementAt(index_courant);
			Object clé = pair.key;
			index_prec = index_courant;
			index_courant++;
			return clé;
		}

		@Override
		public Object remove() {
			if (index_prec == -1) {
				throw new IllegalStateException("un seul remove par next");
			}
			Couple pair = (Couple) map.pairs.removeAt(index_prec);
		    this.map.size = this.map.pairs.length();

			index_courant--;
			index_prec = -1;
			return pair.value;

		}
	}

	private static class ValuesIterator implements ICollection.Iterator {

		int index_courant = 0;
		int index_prec = -1;
		private ListMap map;

		public ValuesIterator(ListMap map) {
			this.map = map;
		}

		@Override
		public boolean hasNext() {
			return index_courant < this.map.length();
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new IllegalStateException();
			Couple pair = (Couple) map.pairs.elementAt(index_courant);
			Object value = pair.value;
			index_prec = index_courant;
			index_courant++;
			return value;
		}

		@Override
		public Object remove() {
			if (index_prec == -1) {
				throw new IllegalStateException("un seul remove par next");
			}
			Couple pair = (Couple) map.pairs.removeAt(index_prec);
			Object value = pair.value;
            map.size = map.pairs.length();

			index_courant=index_prec;
			index_prec = -1;
			return value;

		}
	}

	@Override
	public Iterator iterator() {
		return new Iterator();
	}

	@Override
	public void toArray(Object[] values) {
		valuesToArray(values);
	}

	@Override
	public oop.collections.ICollection.Iterator keys() {
		return new KeysIterator(this);

	}

	@Override
	public oop.collections.ICollection.Iterator values() {
		return new ValuesIterator(this);

	}

	@Override
	public Object get(Key key) {
	    if (key == null)
	        throw new IllegalArgumentException("Null key not allowed");

	    for (int i = 0; i < size; i++) {
	        Couple pair = (Couple) pairs.elementAt(i);
	        if ( pair.key.equals(key)) {
	            if (pair.value == null) {
	                throw new IllegalStateException("une value dans une map ne peut etre null dans get ici");
	            }
	            return pair.value;
	        }
	    }
	    return null;
	}


	/**
	 * The put method adds or updates a pair to the associative collection. If the
	 * key is unknown, the pair is added, and the method returns null. If the key is
	 * known, the value is updated with the given value and the replaced value is
	 * returned. Keys are compared using == and Key:equals(Key)boolean
	 */
	@Override
	public Object put(Key key, Object value) {
	    if (value == null) {
	        throw new IllegalArgumentException("une value dans une map ne peut etre null");
	    }
	    if (key == null) {
	        throw new IllegalArgumentException("Null key not allowed");
	    }

	    for (int i = 0; i < size; i++) {
	        Couple pair = (Couple) pairs.elementAt(i);
	        if (pair.key.equals(key)) {
	            Object old = pair.value;
	            pair.value = value;
	            pairs.updateAt(i, pair);

	            for (int j = 0; j < nb_listeners; j++) {
	                listeners[j].updated(this, key, old, value);
	            }

	            this.size = pairs.length();
	            return old;
	        }
	    }
	    Couple pair = new Couple(key, value);
	    pairs.insertAt(pairs.length(), pair);

	    for (int j = 0; j < nb_listeners; j++) {
	        listeners[j].added(this, key, value);
	    }

	    this.size = pairs.length();
	    return null;
	}


	/**
	 * The method remove is fairly straightforward, it removes the pair identified
	 * by the given key from the collection. If the pair was unknown, null is
	 * returned. Otherwise, the value associated with the given key is returned.
	 * Keys are compared using == and Key:equals(Key)boolean
	 */
	@Override
	public Object remove(Key key) {
		for (int i = 0; i < size; i++) {
			Couple pair = (Couple) pairs.elementAt(i);

	        if (pair.key.equals(key)) {
				Couple pair2 = (Couple) pairs.removeAt(i);
                this.size = pairs.length();

				for (int j = 0; j < nb_listeners; j++) {
					listeners[j].removed(this, key, pair2.value);
				}
				return pair2.value;
			}

		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		for (int i = 0; i < size; i++) {
			Couple pair = (Couple) pairs.elementAt(i);

	        if (pair.key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keysToArray(Key[] keys) {
		oop.collections.ICollection.Iterator  it = keys();
		int i = 0;
		while (it.hasNext() && i < keys.length) {
			keys[i++] = (Key) it.next();
		}
	}

	@Override
	public void valuesToArray(Object[] values) {
		oop.collections.ICollection.Iterator it =  values();
		int i = 0;
		while (it.hasNext() && i < values.length) {
			values[i++] = it.next();
		}
	}

	@Override
	public void add(IMap.Listener l) {
		if (l == null)
			return;
		for (int i = 0; i < nb_listeners; i++)
			if (listeners[i].equals(l))
				return;
		if (nb_listeners == listeners.length) { // agrandir tableau
			IMap.Listener[] tmp = new IMap.Listener[listeners.length + 4];
			System.arraycopy(listeners, 0, tmp, 0, nb_listeners);
			listeners = tmp;
		}
		listeners[nb_listeners++] = l;
		l.added(this);
	}

	@Override
	public void drop(IMap.Listener l) {
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

	@Override
	public void add(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return;
		}
		if (l instanceof oop.collections.IMap.Listener) {
		    add((IMap.Listener) l);
		} else {
			throw new IllegalArgumentException("add du listener collcetion echoué");
		}

	}

	@Override
	public void drop(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return;
		}
		if (l instanceof oop.collections.IMap.Listener) {

		    drop((IMap.Listener) l);
		} else {
			throw new IllegalArgumentException("drop du listener collcetion echoué");
		}
	}
}