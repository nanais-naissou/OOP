package oop.utils.collections;

import oop.collections.IMap;
import oop.collections.IMap.Key;

public class StringKey implements IMap.Key {
	private String s;
	private int hc;

	public StringKey(String s) {
		this.s = s;
		 int h = 0;
	        for (int i = 0; i < s.length(); i++) {
	            h = 31 * h + s.charAt(i);
	        }
		 this.hc=h ;
	}

	@Override
	public boolean equals(Key key) {
	     return (key instanceof StringKey)
	                && this.s.equals(((StringKey) key).s);	}

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
		return s;
	}

}
