package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

public class ArrayListTests {
	private static class Factory implements IList.Factory {

		@Override
		public IList newList() {
			return  new ArrayList();
		}

		@Override
		public IList newList(Object[] elements) {
			return  new ArrayList(elements);
		}

		@Override
		public IList newList(IList list) {
			return  new ArrayList(list);
		}
	}

	public static void main(String[] args) {
		PrintStream ps = System.out;
		ListTests tests = new ListTests(new Factory());
		tests.runTests(ps);
		ps.println("Tests: PASSED");
	}
}