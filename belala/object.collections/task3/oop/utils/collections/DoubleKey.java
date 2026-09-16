package oop.utils.collections;

import oop.collections.IMap;
import oop.collections.IMap.Key;

public final class DoubleKey implements IMap.Key {

    private  double value;
    private  int hash;

    public DoubleKey(double v) {
        this.value = v;
        long bits = Double.doubleToLongBits(v);
        this.hash = (int)(bits ^ (bits >>> 32));
    }

    @Override
    public boolean equals(IMap.Key other) {
        return (other instanceof DoubleKey)
                && Double.compare(((DoubleKey) other).value, this.value) == 0;
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
        return Double.toString(value);
    }
}
