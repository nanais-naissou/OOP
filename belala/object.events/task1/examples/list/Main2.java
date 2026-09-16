package examples.list;

public class Main2 {
	public static void main(String[] args) {
		
		int length = 30000;
		List2 l = new List2();
		long start, end, elapsed;
		 start = System.currentTimeMillis();
		 append(l, length);
			check(l, length);
		 end = System.currentTimeMillis();
		 elapsed = end - start;
		 System.out.printf("main(%d)=%d\n", length, elapsed);
		
		System.out.println("PASSED (Main2): " + length);
	}

	private static void append(List2 l, int length) {
		 long start, end, elapsed;
		 start = System.currentTimeMillis();
		 for (int i = 0; i < length; i++) {
				l.append(i);
			}
		 end = System.currentTimeMillis();
		 elapsed = end - start;
		 System.out.printf("append(%d)=%d\n", length, elapsed);
		
	}

	private static void check(List2 l, int length) {
		 long start, end, elapsed;
		 start = System.currentTimeMillis();
		 for (int i = 0; i < length; i++) {
				if (!l.get(i).equals(new Integer(i)))
					throw new Error("i=" + i);
			}
		 end = System.currentTimeMillis();
		 elapsed = end - start;
		 System.out.printf("check(%d)=%d\n", length, elapsed);
		
	}
}
