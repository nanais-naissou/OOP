package oop.utils.collections;

import oop.collections.IList;
import oop.collections.IMap.Key;

class Bucket {
	class Pair {
		Key key;
		Object value;

		private Pair(Key k, Object v) {
			key = k;
			value = v;
		}
	}

	private IList pairs;
	private int size;
	public Bucket() {
	    this.pairs = new LinkedList(); 
	}

	Bucket(IList.Factory lf) {
		this.pairs = lf.newList();
		this.size = 0;
	}

	Pair get(Key key) {

		if (key == null)
			throw new IllegalArgumentException("null key not apprécié");

		for (int i = 0; i < pairs.length(); i++) {
			Pair pair = (Pair) pairs.elementAt(i);
			if (pair.key.equals(key)) {
				if (pair.value == null) {
					throw new IllegalStateException("une value dans une map ne peut etre null dans get ici");
				}
				return pair;
			}
		}
		return null;
	}

	public Object put(Key key, Object value) {
		if (value == null) {
			throw new IllegalArgumentException("une value dans une map ne peut etre null");
		}
		if (key == null) {
			throw new IllegalArgumentException("Null key not allowed");
		}

		for (int i = 0; i < pairs.length(); i++) {
			Pair pair = (Pair) pairs.elementAt(i);
			if (pair.key == key || pair.key.equals(key)) {
				Object old = pair.value;
				pair.value = value;
				pairs.updateAt(i, pair);

				this.size = pairs.length();
				return old;
			}
		}
		Pair pair = new Pair(key, value);
		pairs.insertAt(pairs.length(), pair);

		this.size = pairs.length();
		return null;
	}

	public int length() {
		return this.size;
	}

	Iterator iterator() {
		return new Iterator();
	}

	class Iterator {
		private int index_courant;
		private int index_prec;

		Iterator() {
			this.index_courant = 0;
			this.index_prec = -1;
		}

		 boolean hasNext() {
			return index_courant < pairs.length();
		}

		Pair next() {
			if (!hasNext())
				throw new IllegalStateException();
			Pair pair = (Pair) pairs.elementAt(index_courant);
			index_prec = index_courant;
			index_courant++;
			return pair;
		}
		public Object remove() {
		    if (index_prec == -1) {
		        throw new IllegalStateException("remove doit suivre un next");
		    }
		    Pair removed = (Pair) pairs.removeAt(index_prec);
		    index_courant--;
		    index_prec = -1;
		    size = pairs.length();

		    return removed;
		}


	}
}
