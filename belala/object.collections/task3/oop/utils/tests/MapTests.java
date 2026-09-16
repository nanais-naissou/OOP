package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.IList;
import oop.collections.IMap;
import oop.collections.IMap.Key;
import oop.utils.Comparator;
import oop.utils.collections.StringKey;
import oop.utils.collections.LongKey;
import oop.utils.collections.DoubleKey;
import oop.utils.collections.CharKey;

public class MapTests {

    private IMap.Factory mf;
    private PrintStream ps;

    public MapTests(IMap.Factory mf) {
        this.mf = mf;
    }

    public void runTests(PrintStream ps) {
        this.ps = ps;

        test00();
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
        test08();
    }

    public void test00() {
        IMap m = mf.newMap();
        Key[] keys = { new StringKey("A"), new StringKey("B"), new StringKey("C") };
        Object[] values = { 1, 2, 3 };

        MapUtils.add(m, keys, values);
       // ps.println(m.length()); 
        assert m.length() == 3;
        assert m.get(keys[0]).equals(1);
        assert m.get(keys[1]).equals(2);
        assert m.get(keys[2]).equals(3);

        ps.println("Test00: passed.");
    }

    public void test01() {

        IMap m = mf.newMap();
        Key k1 = new StringKey("K1");
        Key k2 = new StringKey("K2");

        m.put(k1, 10);
        m.put(k2, 20);

        Key[] keys = { k1, k2, new StringKey("K3") };
        Object[] values = { 100, 200, 300 };

        Key[] updated = MapUtils.update(m, keys, values);

        assert m.get(k1).equals(100);
        assert m.get(k2).equals(200);
        assert updated[0] == k1 && updated[1] == k2;

        ps.println("Test01: passed.");
    }


    public void test02() {

        IMap m = mf.newMap();
        Key a = new StringKey("A");
        Key b = new StringKey("B");

        m.put(a, 1);
        m.put(b, 2);

        Key[] toRemove = { a };
        MapUtils.remove(m, toRemove);

        assert !m.contains(a);
        assert m.contains(b);

        ps.println("Test02: passed.");
    }

  
    public void test03() {

        IMap m = mf.newMap();
        m.put(new StringKey("A"), 1);
        m.put(new StringKey("B"), 2);
        m.put(new StringKey("C"), 3);

        Object[] rem = { 2 };

        Comparator cmp = new Comparator() {
            @Override public boolean hasOrder() { return false; }
            @Override public boolean lessThan(Object a, Object b) { return a.equals(b); }
            @Override public boolean equals(Object a, Object b) { return a.equals(b); }
        };

        MapUtils.remove(m, cmp, rem);
        //ps.println(m.length());
        assert m.length() == 2;
        assert !m.contains(new StringKey("B"));

        ps.println("Test03: passed.");
    }

    public void test04() {

        IMap src = mf.newMap();
        src.put(new StringKey("A"), 1);
        src.put(new StringKey("B"), 2);
        src.put(new StringKey("C"), 3);

        IMap dst = mf.newMap();

        Object[] rm = { 1, 3 };

        Comparator cmp = new Comparator() {
            @Override public boolean hasOrder() { return false; }
            @Override public boolean lessThan(Object a, Object b) { return ((char) a) < ((char) b);
 }
            @Override public boolean equals(Object a, Object b) { return a.equals(b); }
        };

        MapUtils.filter(src, dst, cmp, rm);

        assert dst.length() == 1;
        //ps.println(dst.length());
        assert dst.contains(new StringKey("B"));

        ps.println("Test04: passed.");
    }

    public void test05() {
        IMap m = mf.newMap();
        IList list = new oop.utils.collections.LinkedList();

        list.insertAt(0, "AA");
        list.insertAt(1, "BB");
        list.insertAt(2, "CC");

        Key.Factory kf = new Key.Factory() {
            @Override
            public Key newKey(Object value) {
                return new StringKey((String) value);
            }
        };

        MapUtils.toSet(m, kf, list);

        assert m.contains(new StringKey("AA"));

        assert m.contains(new StringKey("BB"));
        assert m.contains(new StringKey("CC"));

        ps.println("Test05: passed.");
    }

 
    public void test06() {

        IMap m = mf.newMap();
        IList list = new oop.utils.collections.LinkedList();

        list.insertAt(0, 'X');
        list.insertAt(1, 'Y');
        list.insertAt(2, 'Z');

        Key.Factory kf = new Key.Factory() {
            @Override
            public Key newKey(Object value) {
                return new CharKey((char) value);
            }
        };
        MapUtils.toSet(m, kf, list);

        assert m.contains(new CharKey('X'));
        assert m.contains(new CharKey('Y'));
        assert m.contains(new CharKey('Z'));

        ps.println("Test06: passed.");
    }

    public void test07() {

        IMap m = mf.newMap();
        IList list = new oop.utils.collections.LinkedList();

        list.insertAt(0, 10L);
        list.insertAt(1, 20L);
        list.insertAt(2, 30L);

        Key.Factory kf = new Key.Factory() {
            @Override
            public Key newKey(Object value) {
                return new LongKey((long) value);
            }
        };
        MapUtils.toSet(m, kf, list);

        assert m.contains(new LongKey(10L));
        assert m.contains(new LongKey(20L));
        assert m.contains(new LongKey(30L));

        ps.println("Test07: passed.");
    }

    public void test08() {

        IMap m = mf.newMap();
        IList list = new oop.utils.collections.LinkedList();

        list.insertAt(0, 1.1);
        list.insertAt(1, 2.2);
        list.insertAt(2, 3.3);

        Key.Factory kf = new Key.Factory() {
            @Override
            public Key newKey(Object value) {
                return new DoubleKey((double) value);
            }
        };
        MapUtils.toSet(m, kf, list);

        assert m.contains(new DoubleKey(1.1));
        assert m.contains(new DoubleKey(2.2));
        assert m.contains(new DoubleKey(3.3));

        ps.println("Test08: passed.");
    }
}
