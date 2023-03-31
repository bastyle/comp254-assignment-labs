package maps;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exercise1();
	}

	private static void exercise1() {
		System.out.println("---------------- exercise 1 -----------------");
		SortedTableMap<String, Integer> stm = new SortedTableMap<>();
		stm.put("A", 1);
		stm.put("B", 2);
		stm.put("C", null);
		stm.put("D", 3);
		System.out.println("original sorted map:");
		for (Entry<String, Integer> e : stm.entrySet()) {
			System.out.println(e);
		}

		stm.containKey("A");
		stm.containKey("E");
		stm.containKey("B");
		stm.containKey("C");
		stm.containKey("F");

		ProbeHashMap<Integer, Integer> map = new ProbeHashMap<>(0.5f);
		Random rand = new Random();
		int numKeys = 1000000;
		int maxKey = 1000000000;
		int[] keys = new int[numKeys];
		for (int i = 0; i < numKeys; i++) {
			keys[i] = rand.nextInt(maxKey);
		}
		long startTime = System.nanoTime();
		for (int i = 0; i < numKeys; i++) {
			map.put(keys[i], i);
		}
		long endTime = System.nanoTime();
		System.out.println("Insertion time: " + (endTime - startTime) / 1000000.0 + " ms");
		startTime = System.nanoTime();
		for (int i = 0; i < numKeys; i++) {
			map.get(keys[i]);
		}
		endTime = System.nanoTime();
		System.out.println("Lookup time: " + (endTime - startTime) / 1000000.0 + " ms");
		startTime = System.nanoTime();
		for (int i = 0; i < numKeys; i++) {
			map.remove(keys[i]);
		}
		endTime = System.nanoTime();
		System.out.println("Removal time: " + (endTime - startTime) / 1000000.0 + " ms");
		
		
		//

		    
		        int[] capacities = {1000, 10000, 100000}; // different values of n to test
		        float[] maxLoadFactors = {0.5f, 0.75f, 0.9f}; // different values of m to test
		        int repetitions = 10; // number of repetitions to perform for each test
		        
		       // Random rand = new Random();
		        
		        for (int n : capacities) {
		            for (float m : maxLoadFactors) {
		                ChainHashMap<Integer, Integer> map1 = new ChainHashMap<>(n, 109345121, m);
		                long totalTime = 0;
		                
		                for (int i = 0; i < repetitions; i++) {
		                    // generate k random keys and values
		                    int k = 10 * n; // make k a multiple of n
		                    Integer[] keys1 = new Integer[k];
		                    Integer[] values = new Integer[k];
		                    for (int j = 0; j < k; j++) {
		                        keys1[j] = rand.nextInt();
		                        values[j] = rand.nextInt();
		                    }
		                    
		                    // insert the key-value pairs into the map
		                    long startTime1 = System.nanoTime();
		                    for (int j = 0; j < k; j++) {
		                        map1.put(keys1[j], values[j]);
		                    }
		                    long endTime1 = System.nanoTime();
		                    totalTime += endTime1 - startTime1;
		                }
		                
		                // compute the average time taken per repetition
		                double averageTime = (double) totalTime / repetitions;
		                
		                // output the result
		                System.out.printf("ChainHashMap (n=%d, m=%.2f): average time=%.2f ms\n", n, m, averageTime / 1000000);
		            }
		        }
		    
		

		//

		System.out.println("----------------            -----------------");
	}
}
