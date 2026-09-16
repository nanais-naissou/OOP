package oop.utils.contacts;

import oop.contacts.IField.IValue;

public class Value implements IValue {
    private String value;

    public Value(String v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(IValue o) {
        if (o == null) return false;
        return this.toString().equals(o.toString());
    }

    @Override
    public boolean contains(String s) {
        return value.contains(s);
    }

    @Override
    public boolean startsWith(String s) {
        return value.startsWith(s);
    }

    @Override
    public boolean endsWith(String s) {
        return value.endsWith(s);
    }
}
