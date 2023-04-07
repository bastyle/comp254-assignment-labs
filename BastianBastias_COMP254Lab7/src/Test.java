import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		exercise1();
		exercise2();
	}

	private static void exercise1() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(6, "A");
		map.put(2, "B");
		map.put(4, "C");
		map.put(1, "D");
		map.put(9, "E");
		map.put(8, "F");
		// search

		System.out.println("get 4: " + map.get(4));

	}

	private static void exercise2() {
		List<Integer> items = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			items.add(rand.nextInt(100)); // Change 100 to the max value you want
		}
		System.out.println("original list: ");
		for (Integer item : items) {
			System.out.print(item + " ");
		}
		System.out.println();
		BottomUpMergeSort<Integer> sorter = new BottomUpMergeSort<>();
		Queue<Integer> sortedItems = sorter.bottomUpMergeSort(items);
		System.out.println("merged items: ");
		while (!sortedItems.isEmpty()) {
			System.out.print(sortedItems.dequeue() + " ");
		}
	}
}
