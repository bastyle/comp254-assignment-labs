import java.util.Comparator;
import java.util.Scanner;

public class ExamPractice {

	public static void main(String[] args) {
		// practise3();
//		practiceMax();
		
		counter2();
	}

	private static void practice1() {
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree<>();
		Position<String> root = lbt.addRoot("A");
		Position<String> bLeft = lbt.addLeft(root, "B");
		Position<String> cRight = lbt.addRight(root, "C");
		Position<String> dLeft = lbt.addLeft(bLeft, "D");
		Position<String> eRight = lbt.addRight(bLeft, "E");
		Position<String> fLeft = lbt.addLeft(cRight, "F");
		Position<String> gRight = lbt.addRight(cRight, "G");
		Position<String> hLeft = lbt.addLeft(eRight, "H");
		Position<String> iRight = lbt.addRight(eRight, "I");
		lbt.computeDepths(lbt, lbt.size());
	}

	private static void practiceMax() {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();
		Position<Integer> root = lbt.addRoot(1);
		Position<Integer> bLeft = lbt.addLeft(root, 2);
		Position<Integer> cRight = lbt.addRight(root, 3);

		Position<Integer> dLeft = lbt.addLeft(bLeft, 4);
		Position<Integer> eRight = lbt.addRight(bLeft, 5);

		Position<Integer> fLeft = lbt.addLeft(cRight, 6);
		Position<Integer> gRight = lbt.addRight(cRight, 7);

		Position<Integer> hLeft = lbt.addLeft(eRight, 8);
		Position<Integer> iRight = lbt.addRight(eRight, 9);

		System.out.println(lbt.maxSum(root));
	}

	private static void practise2() {
		SortedTableMap<String, Integer> stm = new SortedTableMap<>();
		stm.put("A", 1);
		stm.put("B", 2);
		stm.put("C", null);
		stm.put("D", 3);
		for (Entry<String, Integer> e : stm.entrySet()) {
			System.out.println(e);
		}
		System.out.println(stm.get("A"));
		System.out.println(stm.get("B"));
		System.out.println(stm.get("C"));
		System.out.println(stm.get("D"));
	}

	private static void practise3() {
		Queue<Character> characterQueue = new LinkedQueue<>();
		characterQueue.enqueue('A');
		characterQueue.enqueue('z');
		characterQueue.enqueue('y');
		characterQueue.enqueue('b');
		characterQueue.enqueue('z');
		characterQueue.enqueue('i');
		characterQueue.enqueue('j');
		characterQueue.enqueue('Z');
		// create a Comparator object
		Comparator<Character> comp = new Comparator<Character>() {
			public int compare(Character c1, Character c2) {
				// return c1.compareTo(c2);
				return c1.toString().toLowerCase().compareTo(c2.toString().toLowerCase());
			}
		};
		quickSortInPlace(characterQueue, comp);
		System.out.println(characterQueue);

	}

	private static <K> void quickSortInPlace(Queue<K> queue, Comparator<K> comp) {
		if (queue.size() <= 1) {
			return;
		}
		K pivot = queue.dequeue();
		Queue<K> smallerQueue = new LinkedQueue<>();
		Queue<K> largerQueue = new LinkedQueue<>();
		while (!queue.isEmpty()) {
			K element = queue.dequeue();
			int cmp = comp.compare(element, pivot);
			if (cmp < 0) {
				smallerQueue.enqueue(element);
			} else {
				largerQueue.enqueue(element);
			}
		}
		quickSortInPlace(smallerQueue, comp);
		quickSortInPlace(largerQueue, comp);
		// queue = new LinkedQueue<>();
		while (!smallerQueue.isEmpty()) {
			queue.enqueue(smallerQueue.dequeue());
		}
		queue.enqueue(pivot);
		while (!largerQueue.isEmpty()) {
			queue.enqueue(largerQueue.dequeue());
		}
	}

	public static <T> void quickSort(Queue<T> queue, Comparator<? super T> comp) {
		if (queue.size() <= 1) {
			return;
		}
		T pivot = queue.dequeue();
		Queue<T> smallerQueue = new LinkedQueue<>();
		Queue<T> equalQueue = new LinkedQueue<>();
		Queue<T> largerQueue = new LinkedQueue<>();
		while (!queue.isEmpty()) {
			T element = queue.dequeue();
			int cmp = comp.compare(element, pivot);
			if (cmp < 0) {
				smallerQueue.enqueue(element);
			} else if (cmp == 0) {
				equalQueue.enqueue(element);
			} else {
				largerQueue.enqueue(element);
			}
		}
		quickSort(smallerQueue, comp);
		quickSort(largerQueue, comp);
		while (!smallerQueue.isEmpty()) {
			queue.enqueue(smallerQueue.dequeue());
		}
		while (!equalQueue.isEmpty()) {
			queue.enqueue(equalQueue.dequeue());
		}
		while (!largerQueue.isEmpty()) {
			queue.enqueue(largerQueue.dequeue());
		}
	}

	public <E> int[] computeDepths2(AbstractTree<E> T, int n) {
		int[] depths = new int[n];
		int i = 0;
		for (Position<E> p : T.preorder()) {
			depths[i] = T.depth(p);
			i++;
		}
		return depths;
	}

	private static void counter() {
		System.out.print("Enter word: ");
		Map<String, Integer> freq = new ChainHashMap<>(0.5f); // or any concrete map
		try (Scanner scanner = new Scanner(System.in).useDelimiter("[^a-zA-Z]+")) {
			System.out.print("Enter a text: ");
			String word = scanner.nextLine().toLowerCase();			
			String[] words = word.split(" ");
			for(String w : words) {
				System.out.print("w = [" + w + "]");
				Integer count = freq.get(w); // get the previous count for this word
				if (count == null)
					count = 0; // if not in map, previous count is zero
				freq.put(w, 1 + count); // (re)assign new count for this word
			}
		}		
		int maxCount = 0;
		String maxWord = "no word";
		for (Entry<String, Integer> ent : freq.entrySet()) // find max-count word
			if (ent.getValue() > maxCount) {
				maxWord = ent.getKey();
				maxCount = ent.getValue();
			}
		System.out.print("The most frequent word is '" + maxWord);
		System.out.println("' with " + maxCount + " occurrences.");
	}
	
	private static void counter2() {
		System.out.print("Enter word: ");
		Map<String, Integer> freq = new SortedTableMap<>(); // or any concrete map
		try (Scanner scanner = new Scanner(System.in).useDelimiter("[^a-zA-Z]+")) {
			System.out.print("Enter a text: ");
			String word = scanner.nextLine().toLowerCase();			
			String[] words = word.split(" ");
			for(String w : words) {
				System.out.print("w = [" + w + "]");
				Integer count = freq.get(w); // get the previous count for this word
				if (count == null)
					count = 0; // if not in map, previous count is zero
				freq.put(w, 1 + count); // (re)assign new count for this word
			}
		}		
		int maxCount = 0;
		String maxWord = "no word";
		for (Entry<String, Integer> ent : freq.entrySet()) // find max-count word
			if (ent.getValue() > maxCount) {
				maxWord = ent.getKey();
				maxCount = ent.getValue();
			}
		System.out.print("The most frequent word is '" + maxWord);
		System.out.println("' with " + maxCount + " occurrences.");
	}
}
