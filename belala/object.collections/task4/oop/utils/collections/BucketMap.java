package oop.utils.collections;

import oop.collections.IList;
import oop.collections.IMap;
import oop.collections.IMap.Key;
import oop.utils.collections.Bucket.Pair;

public class BucketMap implements IMap {
	private int nb_listeners = 0;
	private IMap.Listener[] listeners = new IMap.Listener[4];

	private Bucket[] buckets;
	private int nb_buckets;

	public BucketMap(int nbuckets) {
		this.buckets = new Bucket[nbuckets];
		this.nb_buckets = nbuckets;
		for (int i = 0; i < nbuckets; i++) {
			Bucket bucket =  new Bucket();
			buckets[i] = bucket;
		}
	}

	public BucketMap(int nbuckets, IList.Factory lf) {
		this.nb_buckets = nbuckets;
		this.buckets = new Bucket[nbuckets];
		for (int i = 0; i < nbuckets; i++) {
			Bucket bucket = new Bucket(lf);
			buckets[i] = bucket;
		}

	}

	public BucketMap(int nbuckets, IMap map) {
		this(nbuckets);
		Iterator it = map.keys();
		while (it.hasNext()) {
			Key k = (Key) it.next();
			Object v = map.get(k);
			this.put(k, v);
		}
	}

	public BucketMap(int nbuckets, IMap map, IList.Factory lf) {
		this.nb_buckets = nbuckets;
		this.buckets = new Bucket[nbuckets];
		for (int i = 0; i < nbuckets; i++) {
			Bucket bucket = new Bucket(lf);
			buckets[i] = bucket;
		}

		Iterator it = map.keys();
		while (it.hasNext()) {
			Key k = (Key) it.next();
			Object v = map.get(k);
			this.put(k, v);
		}
	}

	private int bucketIndex(Key key) {
		int h = key.hashCode();
		if (h < 0)
			h = -h;
		return h % buckets.length;
	}

	@Override
	public Object get(Key key) { // retourne la valeur dont forget
		int i = bucketIndex(key);
		Bucket pairs = buckets[i];
		Bucket.Pair paire = pairs.get(key); 
		if (paire==null) {
			return null; 
		}
		return paire.value;

	}

	@Override
	public Object put(Key key, Object value) {// put dans pairs{pair}

		int i = bucketIndex(key);
		Bucket pairs = buckets[i];
		Object pair2 = pairs.put(key, value);
		if (pair2 == null) {
			for (int j = 0; j < nb_listeners; j++) {
				listeners[j].added(this, key, value);
			}
		} else {
			for (int j = 0; j < nb_listeners; j++) {
				listeners[j].updated(this, key, pair2, value);

			}
		}
		return pair2;
	}

	// the iterator on ICollection is an iterator
	// on the elements of the collection, so here,
	// for maps, it is an iterator on values.
	@Override
	public IMap.Iterator iterator() {
		return new MapIterator(false);
	}

	private class MapIterator implements IMap.Iterator {
        int index_courant = 0;
        Bucket.Iterator it = buckets[index_courant].iterator();
        boolean useKeys;
        Bucket.Pair index_prec =  null;

        MapIterator(boolean useKeys) {
            this.useKeys = useKeys;
        }

        @Override
        public boolean hasNext() {
            while (index_courant < nb_buckets) {
                if (it.hasNext()) return true;
                index_courant++;
                if (index_courant < nb_buckets)
                    it = buckets[index_courant].iterator();
            }
            return false;
        }

        @Override
        public Object next() {
            if (!hasNext())  throw new IllegalStateException("hasnext impossible");
;
            index_prec = it.next();
            if (useKeys) { return index_prec.key;}
            else {return index_prec.value;}
        }

        @Override
        public Object remove() {
            if (index_prec == null)
                throw new IllegalStateException("remove() sans next()");
            Bucket.Pair removed = (Pair) it.remove();
            index_prec = null;
            return removed.value;
        }
    }


	@Override
	public int length() {
		int size=0; 
		MapIterator iter = (MapIterator) this.iterator();
		while (iter.hasNext()) {
			iter.next();
			size++;
; 		}
		return size;
		
	}

	@Override
	public void toArray(Object[] values) {
		MapIterator map_iter = (MapIterator) iterator();
		int i = 0;
		while (map_iter.hasNext()) {
			Object value = map_iter.next();
			values[i++] = value;
		}

	}

	@Override
	public Iterator keys() {
		return new MapIterator(true);
	}

	@Override
	public Iterator values() {
		return new MapIterator(false);

	}

	@Override
	public Object remove(Key key) {
		int index = bucketIndex(key);
		Bucket bucket = buckets[index];
		Bucket.Iterator it = bucket.iterator();

		while (it.hasNext()) {
			Bucket.Pair pair = it.next();

			if (pair.key.equals(key)) {
				Bucket.Pair removedValue = (Pair) it.remove();
				for (int j = 0; j < nb_listeners; j++) {
					listeners[j].removed(this, key, removedValue.value);
				}

				return removedValue.value;
			}
		}

		return null;
	}

	@Override
	public boolean contains(Key key) {
		Iterator map_iter_keys = keys();
		int i = 0;
		while (map_iter_keys.hasNext()) {
			Key clé = (Key) map_iter_keys.next();
			if (key.equals(clé)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keysToArray(Key[] keys) {
		Iterator map_iter_keys = keys();
		int i = 0;
		while (map_iter_keys.hasNext()) {
			Key clé = (Key) map_iter_keys.next();
			keys[i++] = clé;
		}
	}

	@Override
	public void valuesToArray(Object[] values) {
		Iterator map_iter_values = values();
		int i = 0;
		while (map_iter_values.hasNext()) {
			Object val = map_iter_values.next();
			values[i++] = val;
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
			throw new IllegalStateException("listener null");
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
			throw new IllegalStateException("listener null");
		}
		if (l instanceof oop.collections.IMap.Listener) {

			drop((IMap.Listener) l);
		} else {
			throw new IllegalArgumentException("drop du listener collcetion echoué");
		}
	}
}
