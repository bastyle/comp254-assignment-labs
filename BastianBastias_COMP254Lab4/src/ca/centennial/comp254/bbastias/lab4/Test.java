package ca.centennial.comp254.bbastias.lab4;

public class Test {

	public static void main(String[] args) {
		exercise1();
		exercise2();
		exercise3();
	}

	private static void exercise1() {
		PositionalList<Integer> list = new LinkedPositionalList<>();
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		Position<Integer> p = list.first();
		long index = list.indexOf(p);
		System.out.println("Index of first: " + index);
		index = list.indexOf(list.last());
		System.out.println("Index of last: " + index);
	}

	private static void exercise2() {
		Stack<Integer> singlyLinkedStack = new LinkedStack<>();
		singlyLinkedStack.push(10);
		singlyLinkedStack.push(20);
		singlyLinkedStack.push(30);
		singlyLinkedStack.push(40);
		Stack<Integer> singlyLinkedStack2 = new LinkedStack<>();
		singlyLinkedStack2.push(50);
		singlyLinkedStack2.push(60);
		singlyLinkedStack2.push(70);
		singlyLinkedStack2.push(80);
		System.out.println("original singlyLinkedStack: " + singlyLinkedStack);
		System.out.println("original singlyLinkedStack2: " + singlyLinkedStack2);
		transfer(singlyLinkedStack, singlyLinkedStack2);
		System.out.println("final singlyLinkedStack: " + singlyLinkedStack);
		System.out.println("final singlyLinkedStack2: " + singlyLinkedStack2);
	}

	private static void exercise3() {
		LinkedQueue<Integer> linkedQ1 = new LinkedQueue<>();
		linkedQ1.enqueue(10);
		linkedQ1.enqueue(20);
		linkedQ1.enqueue(30);
		linkedQ1.enqueue(40);
		LinkedQueue<Integer> linkedQ2 = new LinkedQueue<>();
		linkedQ2.enqueue(50);
		linkedQ2.enqueue(60);
		linkedQ2.enqueue(70);
		linkedQ2.enqueue(80);
		System.out.println("original linkedQ1: " + linkedQ1);
		System.out.println("original linkedQ2: " + linkedQ2);
		linkedQ1.concatenate(linkedQ2);
		System.out.println("final linkedQ1: " + linkedQ1);
		System.out.println("final linkedQ2: " + linkedQ2);
	}

	private static <E> void transfer(Stack<E> S, Stack<E> T) {
		final int auxSize = S.size();// sets its value to the size of Stack S, which is the number of elements in the
										// stack.
		for (int i = 0; i < auxSize; i++) {// loop that will iterate
			T.push(S.pop());// the top element of Stack S onto the top of Stack T, while also removing the
							// element from Stack S by calling the "pop" method.
		}
	}

}
