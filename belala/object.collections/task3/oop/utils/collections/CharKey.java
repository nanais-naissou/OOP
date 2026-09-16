package oop.utils.collections;

import oop.collections.IMap;
import oop.collections.IMap.Key;

public final class CharKey implements IMap.Key {

    private char value;
    private final int hash;

    public CharKey(char c) {
        this.value = c;
        this.hash = (int) c;
    }

    @Override
    public boolean equals(IMap.Key other) {
        return (other instanceof CharKey)
                && ((CharKey) other).value == this.value;
    }

    @Override
    public int hashCode() {
        return hash;
    }


	@Override
	public boolean equals(Object o) {
		if (o instanceof Key)
			return equals((Key) o);
		return false;
	}

	@Override
	public String toString() {
	    return Character.toString(value);
	}


}
