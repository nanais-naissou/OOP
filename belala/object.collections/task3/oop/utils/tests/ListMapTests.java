package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.IMap;
import oop.utils.collections.ListMap;

public class ListMapTests {
	 private static class Factory implements IMap.Factory {
	 @Override
	 public IMap newMap() {
		 return new ListMap();
	 }
	 @Override
	 public IMap newMap(IMap map) {
		 return new ListMap(map);
	 }
	 }
	
	 public static void main(String[] args) {
	 PrintStream ps = System.out;
	 MapTests tests = new MapTests(new Factory());
	 tests.runTests(ps);
	 System.out.println("Tests: PASSED");
	 }
	}