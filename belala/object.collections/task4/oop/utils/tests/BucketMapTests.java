package oop.utils.tests;

import java.io.PrintStream;

import oop.collections.IMap;
import oop.utils.collections.*;

public class BucketMapTests {

    private static class Factory implements IMap.Factory {
        @Override
        public IMap newMap() {
			return new BucketMap(5); }

        @Override
        public IMap newMap(IMap map) {
			return new BucketMap(map.length()+10,map); }
    }

    public static void main(String[] args) {
        PrintStream ps = System.out;
        MapTests tests = new MapTests(new Factory());
        tests.runTests(ps);
        System.out.println("Tests: PASSED");
    }
}
