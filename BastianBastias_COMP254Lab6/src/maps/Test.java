package maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
//		exercise1();
		exercise2();
	}

	private static void exercise1() {
		System.out.println("---------------- exercise 1 -----------------");
		final int repetitions = 10;
		List<String> list = executeTest(true, repetitions);
		List<String> list2 = executeTest(false, repetitions);
		for (int i = 0; i < repetitions-1; i++) {
			System.out.println(list.get(i));
			System.out.println("----");
			System.out.println(list2.get(i));
			System.out.println("-----------------------------------------------------");
		}
		//
		System.out.println("----------------  end exercise 1      -----------------");
	}

	private static <T> List<String> executeTest(boolean testProbe, int repetitions) {
		List<String> list = new ArrayList<>();
		// An array of different capacity values to be tested
		int[] capacities = { 1000, 10000, 100000 };
		// An array of different load factor values to be tested
		float[] loadFactors = { 0.25f, 0.5f, 0.75f, 0.9f };
		// Number of repetitions to perform for each test
//		int repetitions = 10; 
		Random rand = new Random();
		// Loop through each capacity value
		for (int n : capacities) {
			for (float factor : loadFactors) {
				// A map object is created either ProbeHashMap or ChainHashMap based on the
				// testProbe value
				AbstractHashMap<Integer, Integer> map1 = (testProbe ? new ProbeHashMap<>(factor)
						: new ChainHashMap<>(factor));
				// A variable to keep track of total time taken for all repetitions of this test
				long totalTime = 0;
				for (int i = 0; i < repetitions; i++) {
					// Generate k random keys and values, where k is 10 times the current capacity
					// value\
					int k = 10 * n; // make k a multiple of n
					Integer[] keys1 = new Integer[k];
					Integer[] values = new Integer[k];
					for (int j = 0; j < k; j++) {
						//System.out.println(j);
						keys1[j] = rand.nextInt();
						values[j] = rand.nextInt();
					}
					// Insert the key-value pairs into the map and measure the time taken for this
					// operation
					long startTime1 = System.nanoTime();
					for (int j = 0; j < k; j++) {
						map1.put(keys1[j], values[j]);
					}
					long endTime1 = System.nanoTime();
					totalTime += endTime1 - startTime1;
				}
				// Calculate the average time taken per repetition
				double averageTime = (double) totalTime / repetitions;
				// Output the result showing which type of map was tested, the values of n and
				// m, and the average time taken

				final String msg = (testProbe ? "ProbeHashMap" : "ChainHashMap")
						+ String.format("(n=%d, m=%.2f): average time=%.2f ms", n, factor, averageTime / 1000000);
//				System.out.println(msg);
				list.add(msg);
			}
		}
		return list;
	}

	private static void exercise2() {
		System.out.println("---------------- exercise 2 -----------------");
		SortedTableMap<String, Integer> stm = new SortedTableMap<>();
		stm.put("A", 1);
		stm.put("B", 2);
		stm.put("C", null);
		stm.put("D", 3);
		System.out.println("original sorted map:");
//		for (Entry<String, Integer> e : stm.entrySet()) {
//			System.out.println(e);
//		}
		stm.containKey("A");
		stm.containKey("E");
		stm.containKey("B");
		stm.containKey("C");
		stm.containKey("F");
		System.out.println("-- get values --");
		System.out.println(stm.get("A"));
		System.out.println(stm.get("C"));
		System.out.println(stm.get("F"));
		System.out.println(stm.get(null));
	}
}
