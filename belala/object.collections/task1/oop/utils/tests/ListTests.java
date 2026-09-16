package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.ICollection;
import oop.collections.IList;
import oop.utils.Comparator;
import oop.utils.tests.ListUtils;

public class ListTests {
	private final IList.Factory lf;
	private PrintStream ps;

	public ListTests(IList.Factory lf) {
		this.lf = lf;
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
		test09();
		testIterator();
	}

	private TestComparator c = new TestComparator();

	class TestComparator implements Comparator {

		@Override
		public boolean hasOrder() {
			return true;
		}

		@Override
		public boolean lessThan(Object a, Object b) {
			return a.toString().compareTo(b.toString()) < 0;
		}

		@Override
		public boolean equals(Object a, Object b) {
			return a.equals(b);
		}
	}

	public void test00() {
		IList list = lf.newList();
		assert list.length() == 0;
		ps.println("Test00: passed.");
	}

	public void test01() {
		Object[] elems = { 1, 2, 3 };
		IList list = lf.newList(elems);
		assert list.length() == 3;
		ps.println("Test01: passed.");
	}

	public void test02() {// insert et remove
		IList list = lf.newList();
		list.insertAt(0, "a");
		list.insertAt(1, "b");
		list.insertAt(2, "c");
		list.insertAt(10, "d");
		assert list.length() == 11;
		assert list.elementAt(10).equals("d");

		list.remove("c");
		assert list.length() == 10;
		assert list.elementAt(0).equals("a");

		assert list.elementAt(1).equals("b");
		ps.println("Test02: passed.");
	}

	public void test03() {// tetster order
		IList list = lf.newList(new Object[] { 5, 1, 3, 2 });
		IList dst = lf.newList();

		Comparator cmp = new Comparator() {
			@Override
			public boolean hasOrder() {
				return true;
			}

			@Override
			public boolean lessThan(Object a, Object b) {
				return ((Integer) a) < ((Integer) b);
			}

			@Override
			public boolean equals(Object a, Object b) {
				return a.equals(b);
			}
		};

		ListUtils.order(list, dst, cmp);
		assert dst.length() == list.length();
		assert dst.elementAt(0).equals(1);
		assert dst.elementAt(1).equals(2);
		assert dst.elementAt(2).equals(3);
		assert dst.elementAt(3).equals(5);

		ps.println("Test03: passed.");
	}

	public void test04() {// test de set
		IList list = lf.newList(new Object[] { 1, 2, 1, 3, 2 });

		Comparator cmp = new Comparator() {
			@Override
			public boolean hasOrder() {
				return true;
			}

			@Override
			public boolean lessThan(Object a, Object b) {
				return ((Integer) a) < ((Integer) b);
			}

			@Override
			public boolean equals(Object a, Object b) {
				return a.equals(b);
			}
		};

		ListUtils.toSet(list, cmp);

		assert list.length() == 3;
		assert list.elementAt(0).equals(1);
		assert list.elementAt(1).equals(2);
		assert list.elementAt(2).equals(3);
		ps.println("Test04: passed.");
	}

	public void test05() { // test find first and last )
		IList list = lf.newList(new Object[] { 1, 2, 3, 2 });

		Comparator cmp = new Comparator() {
			@Override
			public boolean hasOrder() {
				return true;
			}

			@Override
			public boolean lessThan(Object a, Object b) {
				return ((Integer) a) < ((Integer) b);
			}

			@Override
			public boolean equals(Object a, Object b) {
				return a.equals(b);
			}
		};

		int first = ListUtils.findFirst(list, cmp, 2);
		int last = ListUtils.findLast(list, cmp, 2);

		assert first == 1;
		assert last == 3;

		ps.println("Test05: passed.");
	}
	

	public void test06() {// ici add 
		IList list = lf.newList(new Object[] { 1, 2, 3, 2 });
		ListUtils.add(list, new Object[] { "A", "B", "C" });
		assert list.elementAt(4).equals("A");
		assert list.elementAt(5).equals("B");
		assert list.elementAt(6).equals("C");
		ps.println("Test06: passed.");

	}

	public void test07() { //remove
		IList list = lf.newList(new Object[] { "A", "B", "C" });
		ListUtils.remove(list, new Object[] { "A", "C" });
		assert list.length() == 1 && list.elementAt(0).equals("B");
		ps.println("Test07: passed.");

	}

	public void test08() {//string
		IList list = lf.newList(new Object[] { "1", "2", "3" });
		assert ListUtils.toString(list).equals("1, 2, 3");
		ps.println("Test08: passed.");

	}

	public void test09() {//reverse
		IList list = lf.newList(new Object[] { "1", "2", "3" });
		IList dst = lf.newList();
		ListUtils.reverse(list, dst);
		assert dst.elementAt(0).equals("3");
		assert dst.elementAt(1).equals("2");
		assert dst.elementAt(2).equals("1");
		ps.println("Test09: passed.");

	}

	public void testIterator() {
		IList list = lf.newList();
		list.insertAt(0, "A");
		list.insertAt(1, "B");
		list.insertAt(2, "C");
		oop.collections.ICollection.Iterator it = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next());
		}
		if (!"ABC".equals(sb.toString())) {
			throw new AssertionError("test iterator échoué");
		}
		else {
			ps.println("TestIterator: passed.");

		}
	}

}
