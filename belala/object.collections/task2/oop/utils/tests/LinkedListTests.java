package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.IList;
import oop.utils.collections.LinkedList;

public class LinkedListTests {
	private static class Factory implements IList.Factory {
		@Override
		public IList newList() {
			return new LinkedList();
		}

		@Override
		public IList newList(Object[] elements) {
			return new LinkedList(elements);
		}

		@Override
		public IList newList(IList list) {
			return new LinkedList(list);
		}
	}

	public static void main(String[] args) {
		PrintStream ps = System.out;
		ListTests tests = new ListTests(new Factory());
		tests.runTests(ps);
		System.out.println("Tests: PASSED");
	}
}