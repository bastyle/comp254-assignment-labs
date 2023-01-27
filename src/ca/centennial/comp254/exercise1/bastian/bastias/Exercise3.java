package ca.centennial.comp254.exercise1.bastian.bastias;

import linkedlists.CircularlyLinkedList;
import linkedlists.CircularlyLinkedList.Node;

public class Exercise3 {

	public static void main(String[] args) {
		CircularlyLinkedList<String> circularList = new CircularlyLinkedList<String>();
		circularList.addFirst("1");
		circularList.addLast("2");
		circularList.addLast("3");
		circularList.addLast("4");
		//
		System.out.println(circularList);
		CircularlyLinkedList<String> circularList2;
		try {
//			circularList2 = circularList.clone();
			//clone disorder
			circularList2 = circularList.clone2();
//			circularList2 = circularList.clone3();
			System.out.println(circularList2);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public CircularlyLinkedList<E> cloneOnClass() throws CloneNotSupportedException {
//		// always use inherited Object.clone() to create the initial copy
//		CircularlyLinkedList<E> other = (CircularlyLinkedList<E>) super.clone(); // safe cast
//		if (size > 0) { // we need independent chain of nodes
////			other.tail.setNext(new Node<>(tail.getElement(), null));
//			other.tail.setNext(new Node<>(tail.getNext().getElement(), null));
//			
//			Node<E> walk = tail.getNext().getNext(); // walk through remainder of original list
//			Node<E> otherTail = other.tail.getNext(); // remember most recently created node
//			while (walk != null) { // make a new node storing same element
//				Node<E> newest = new Node<>(walk.getElement(), null);
//				otherTail.setNext(newest); // link previous node to this one
//				otherTail = newest;
//				walk = walk.getNext();
//			}
//			otherTail=tail;
//		}
//		return other;
//	}

}
