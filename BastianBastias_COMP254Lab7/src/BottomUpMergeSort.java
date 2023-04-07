import java.util.*;

public class BottomUpMergeSort<E extends Comparable<E>> {

	public Queue<E> bottomUpMergeSort(List<E> items) {
		// Place each item in its own queue
		List<Queue<E>> queues = new ArrayList<>();
		for (E item : items) {
			Queue<E> queue = new LinkedQueue<>();
			queue.enqueue(item); // insert the item into a new queue
			queues.add(queue); // add the queue containing the item to the list of queues
		}

		// Repeatedly merge pairs of queues until all items are sorted in a single queue
		while (queues.size() > 1) { // while there are more than one queue
			List<Queue<E>> newQueues = new ArrayList<>(); // create a new list of queues
			for (int i = 0; i < queues.size(); i += 2) { // for each pair of queues
				Queue<E> mergedQueue;
				if (i + 1 < queues.size()) {
					// if there are two queues to merge, merge them using the mergeQueues method
					mergedQueue = mergeQueues(queues.get(i), queues.get(i + 1));
				} else {
					// if there is only one queue, add it to the new list of queues
					mergedQueue = queues.get(i);
				}
				newQueues.add(mergedQueue); // add the merged queue to the new list of queues
			}
			queues = newQueues; // replace the list of queues with the new list of queues
		}

		// Return the sorted items as a queue
		return queues.get(0); // the only queue left in the list of queues contains the sorted items
	}

	private Queue<E> mergeQueues(Queue<E> queue1, Queue<E> queue2) {
		Queue<E> mergedQueue = new LinkedQueue<>();
		while (!queue1.isEmpty() && !queue2.isEmpty()) { // while there are elements in both queues
			if (queue1.first().compareTo(queue2.first()) <= 0) {
				// if the first element of queue1 is smaller than or equal to the first element
				// of queue2,
				// dequeue and enqueue the first element of queue1 to the merged queue
				mergedQueue.enqueue(queue1.dequeue());
			} else {
				// otherwise, dequeue and enqueue the first element of queue2 to the merged
				// queue
				mergedQueue.enqueue(queue2.dequeue());
			}
		}
		// add the remaining elements of queue1 to the merged queue
		while (!queue1.isEmpty()) {
			mergedQueue.enqueue(queue1.dequeue());
		}
		// add the remaining elements of queue2 to the merged queue
		while (!queue2.isEmpty()) {
			mergedQueue.enqueue(queue2.dequeue());
		}
		return mergedQueue; // return the merged queue
	}

	public static void main(String[] args) {
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
