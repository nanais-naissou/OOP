package oop.utils.collections;

import oop.collections.IMap;
import oop.collections.IMap.Key;

public class LongKey implements IMap.Key {
	private long s;
	private int hc;

	public LongKey(long s) {
		this.s = s;
		 int h= (int)(s * 31);

		 this.hc=h ;
	}

	@Override
	public boolean equals(Key key) {
	    return (key instanceof LongKey)
	            && this.s == ((LongKey) key).s;
	}

	@Override
	public int hashCode() {
		return hc;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Key)
			return equals((Key) o);
		return false;
	}

	@Override
	public String toString() {
	    return String.valueOf(s);
	}


	

}
