/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.centennial.comp254.exercise1.bastian.bastias;

/**
 * A basic singly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SinglyLinkedList<E> implements Cloneable {
	// ---------------- nested Node class ----------------
	/**
	 * Node of a singly linked list, which stores a reference to its element and to
	 * the subsequent node in the list (or null if this is the last node).
	 */
	private static class Node<E> {

		/** The element stored at this node */
		private E element; // reference to the element stored at this node

		/** A reference to the subsequent node in the list */
		private Node<E> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e the element to be stored
		 * @param n reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// Accessor methods
		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getElement() {
			return element;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return next;
		}

		// Modifier methods
		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			next = n;
		}

	} // ----------- end of nested Node class -----------

	// instance variables of the SinglyLinkedList
	/** The head node of the list */
	private Node<E> head = null; // head node of the list (or null if empty)

	/** The last node of the list */
	private Node<E> tail = null; // last node of the list (or null if empty)

	/** Number of nodes in the list */
	private int size = 0; // number of nodes in the list

	/** Constructs an initially empty list. */
	public SinglyLinkedList() {
	} // constructs an initially empty list

	// access methods
	/**
	 * Returns the number of elements in the linked list.
	 * 
	 * @return number of elements in the linked list
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether the linked list is empty.
	 * 
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns (but does not remove) the first element of the list
	 * 
	 * @return element at the front of the list (or null if empty)
	 */
	public E first() { // returns (but does not remove) the first element
		if (isEmpty())
			return null;
		return head.getElement();
	}

	/**
	 * Returns (but does not remove) the last element of the list.
	 * 
	 * @return element at the end of the list (or null if empty)
	 */
	public E last() { // returns (but does not remove) the last element
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	// update methods
	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param e the new element to add
	 */
	public void addFirst(E e) { // adds element e to the front of the list
		head = new Node<>(e, head); // create and link a new node
		if (size == 0)
			tail = head; // special case: new node becomes tail also
		size++;
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param e the new element to add
	 */
	public void addLast(E e) { // adds element e to the end of the list
		Node<E> newest = new Node<>(e, null); // node will eventually be the tail
		if (isEmpty())
			head = newest; // special case: previously empty list
		else
			tail.setNext(newest); // new node after existing tail
		tail = newest; // new node becomes the tail
		size++;
	}

	/**
	 * Removes and returns the first element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeFirst() { // removes and returns the first element
		if (isEmpty())
			return null; // nothing to remove
		E answer = head.getElement();
		head = head.getNext(); // will become null if list had only one node
		size--;
		if (size == 0)
			tail = null; // special case as list is now empty
		return answer;
	}

	@SuppressWarnings({ "unchecked" })
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		SinglyLinkedList other = (SinglyLinkedList) o; // use nonparameterized type
		if (size != other.size)
			return false;
		Node walkA = head; // traverse the primary list
		Node walkB = other.head; // traverse the secondary list
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false; // mismatch
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true; // if we reach this, everything matched successfully
	}

	@SuppressWarnings({ "unchecked" })
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		// always use inherited Object.clone() to create the initial copy
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
		if (size > 0) { // we need independent chain of nodes
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext(); // walk through remainder of original list
			Node<E> otherTail = other.head; // remember most recently created node
			while (walk != null) { // make a new node storing same element
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest); // link previous node to this one
				otherTail = newest;
				walk = walk.getNext();
			}
			otherTail=tail;
		}
		return other;
	}

	public int hashCode() {
		int h = 0;
		for (Node walk = head; walk != null; walk = walk.getNext()) {
			h ^= walk.getElement().hashCode(); // bitwise exclusive-or with element's code
			h = (h << 5) | (h >>> 27); // 5-bit cyclic shift of composite code
		}
		return h;
	}

	/**
	 * Produces a string representation of the contents of the list. This exists for
	 * debugging purposes only.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = head;
		while (walk != null) {
			sb.append(walk.getElement());
			if (walk != tail)
				sb.append(", ");
			walk = walk.getNext();
		}
		sb.append(")");
		return sb.toString();
	}

	// main method
	public static void main(String[] args) {

		SinglyLinkedList<String> list = new SinglyLinkedList<String>();
		list.addFirst("MSP");
		list.addLast("ATL");
		list.addLast("BOS");
		//
		list.addFirst("LAX");
		System.out.println(list);
		//
	}

	public Node<E> findNode(E elementToFind) {
		Node<E> walk = head;
		Node<E> nodeToFind = new Node<>(elementToFind, null);
		while (walk != null) {
			if (nodeToFind.getElement().equals(walk.getElement())) {
				return walk;
			}
			walk = walk.getNext();
		}
		return null;
	}

	public void finalSwap(E from, E to) {
		System.out.println("--------- Swap method ----------");
		System.out.println("original list: " + this);
		System.out.println("swapping node " + from + " to: " + to + " .");
		if (null == from || null == to) {
			return;
		}
		Node<E> nodeFrom = null;
		Node<E> prevTo = null;
		Node<E> nodeTo = null;
		Node<E> nextNodeTo = null;
		Node<E> nextNodeFrom = null;
		if (head.getElement().equals(from) && !head.getNext().getElement().equals(to)) {
			// is the first 'from' node the head? and the next element is not the ´to' node.
			System.out.println("'from' node is the head and the next element is not the 'to' node.");
			nodeFrom = head;
			prevTo = getPrevNode(to);
			nodeTo = prevTo.getNext();
			nextNodeTo = nodeTo.getNext();
			nextNodeFrom = head.getNext();
			nodeFrom.setNext(nextNodeTo);
			prevTo.setNext(nodeFrom);
			head = nodeTo;
			head.setNext(nextNodeFrom);
			if (to.equals(tail.getElement())) {
				System.out.println("node 'to' is tail..");
				tail = nodeFrom;
			}
		} else if (head.getElement().equals(from) && head.getNext().getElement().equals(to)) {
			// is the first 'from' node the head? and the next element is the ´to' node.
			System.out.println("'from' node is the head and its next element is the 'to' node.");
			nodeFrom = head;
			nodeTo = head.getNext();
			nextNodeTo = head.getNext().getNext();
			head.setNext(nextNodeTo);
			nodeTo.setNext(head);
			head = nodeTo;
		} else {
//			System.out.println("changing between nodes...");
			Node<E> prevFrom = getPrevNode(from);
			nodeFrom = prevFrom.getNext();
			// validate if fromnode.next = tonode
			if (nodeFrom.getNext().getElement().equals(to) && !tail.getElement().equals(to)) {
				System.out.println("next node of 'from' node is the 'to' node.");
				prevFrom = getPrevNode(from);
				nextNodeTo = nodeFrom.getNext().getNext();
				nodeTo = nodeFrom.getNext();
				prevFrom.setNext(nodeTo);
				nodeTo.setNext(nodeFrom);
				nodeFrom.setNext(nextNodeTo);
			} else if (nodeFrom.getNext().getElement().equals(to) && tail.getElement().equals(to)) {
				System.out.println("next node of 'from' node is the 'to' node and the 'to' node is the tail.");
				prevFrom = getPrevNode(from);
				nextNodeTo = nodeFrom.getNext().getNext();
				nodeTo = nodeFrom.getNext();
				prevFrom.setNext(nodeTo);
				nodeTo.setNext(nodeFrom);
				nodeFrom.setNext(nextNodeTo);
				tail = nodeFrom;
			} else {
				System.out.println("regular swapping between nodes...");
				prevTo = getPrevNode(to);
				nodeTo = prevTo.getNext();
				nextNodeTo = nodeTo.getNext();
				Node<E> nextFrom = prevFrom.getNext().getNext();
				// swap
				prevFrom.setNext(nodeTo);
				nodeTo.setNext(nextFrom);
				prevTo.setNext(nodeFrom);
				nodeFrom.setNext(nextNodeTo);
			}

		}
		System.out.println("final list: " + this);
	}

	public Node<E> getPrevNode(E elementToFind) {
		Node<E> walk = head;
		// Node<E> nodeToFind = new Node<>(elementToFind, null);
		while (walk != null) {
			if (null != walk.getNext() && null != walk.getNext().getElement()
					&& walk.getNext().getElement().equals(elementToFind)) {
				return walk;
			}
			walk = walk.getNext();
		}
		return null;
	}

	public void concatenate2List(SinglyLinkedList<E> otherList) {
		System.out.println("original list: " + this + " size: " + size);
		System.out.println("new list to concatenate: " + otherList + " size: " + otherList.size);
		this.tail.setNext(otherList.head);
		tail = otherList.tail;
		size += otherList.size;
		System.out.println("concatenated list: " + this + " size: " + size);
	}

}