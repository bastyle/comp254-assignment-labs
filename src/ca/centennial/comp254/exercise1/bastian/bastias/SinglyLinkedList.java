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

	public void swapTwoNodes2(E from, E to) {
		Node<E> fromNode = findNode(from);
		Node<E> toNode = findNode(to);
		Node<E> nodeAux = new Node<>(fromNode.getElement(), fromNode.getNext());

		fromNode.setNext(new Node<>(toNode.getElement(), toNode.getNext()));
		toNode.setNext(new Node<>(nodeAux.getElement(), nodeAux.getNext()));

	}

	public void swapTwoNodes4(E from, E to) {
		Node<E> fromNode = new Node<>(findNode(from).getElement(), findNode(from).getNext());
		Node<E> toNode = new Node<>(findNode(from).getElement(), findNode(from).getNext());
		Node<E> nodeAux = new Node<>(fromNode.getElement(), fromNode.getNext());
		Node<E> walk = head;
		while (walk != null) {
			if (null != walk.getNext() && walk.getNext().getElement().equals(fromNode.getElement())) {
				
			}
		}

	}

	public void swapTwoNodes(E from, E to) {

		/*
		 * Node<E> nodeA = findNode(from); Node<E> nodeB = findNode(to);
		 * System.out.println("node a e: " + nodeA.getElement() + " next: " +
		 * nodeA.getNext().getElement()); System.out.println("node b e: " +
		 * nodeB.getElement() + " next: " + nodeB.getNext().getElement()); Node<E>
		 * auxiliarNode = nodeB; System.out.println( "auxiliarNode  e: " +
		 * auxiliarNode.getElement() + " next: " + auxiliarNode.getNext().getElement());
		 * nodeB = new Node<>(nodeA.getElement(),nodeA.getNext()); nodeA = nodeB;
		 */

		// Node found = null;
		// Node<E> nodeToFind = new Node<>(elementToFind, null);
		// System.out.println("finding node: " + nodeToFind.getElement());

		Node<E> walk = head;
		Node<E> walk2 = head;

		Node<E> fromNode = findNode(from);
		Node<E> toNode = findNode(to);
		Node<E> nodeAux = toNode;
		// walk.setNext(nodeB);

		boolean flag1 = false;
		boolean flag2 = false;
		// validar antes si es igual a head
		while (walk != null) {
			System.out.println("walk> " + walk.getElement());
			if (null != walk.getNext() && walk.getNext().getElement().equals(fromNode.getElement()) && !flag1) {
				// walk = new Node<>(from, nodeA.getNext());
				// walk.setNext(toNode.getNext());
				walk.setNext(nodeAux);
				System.out.println("toNode.getElement()>" + toNode.getElement());
				System.out.println("walk.getNext().getElement>" + walk.getNext().getElement());
				// break;
				flag1 = true;
			} else if (null != walk.getNext() && walk.getNext().getElement().equals(toNode.getElement()) && !flag2) {
				walk.setNext(fromNode);
				flag2 = true;
			}
			if (flag1 && flag2) {
				break;
			}
			walk = walk.getNext();
		}

	}

	public void swapTwoNodes3(E from, E to) {

		Node<E> walk = head;
		Node<E> walk2 = head;

		Node<E> fromNode = findNode(from);
		Node<E> toNode = findNode(to);
		Node<E> nodeAux = toNode;
		// walk.setNext(nodeB);

		boolean flag1 = false;
		boolean flag2 = false;
		while (walk != null) {
			System.out.println("walk> " + walk.getElement());
			if (null != walk.getNext() && walk.getNext().getElement().equals(fromNode.getElement()) && !flag1) {
				nodeAux = new Node<>(fromNode.getElement(), fromNode.getNext());
				nodeAux = new Node<>(walk.getNext().getElement(), walk.getNext());
				walk.setNext(toNode);
				flag1 = true;
			} else if (null != walk.getNext() && walk.getNext().getElement().equals(toNode.getElement()) && !flag2) {
				walk.setNext(fromNode);
				flag2 = true;
			}
			if (flag1 && flag2) {
				break;
			}
			walk = walk.getNext();
		}

	}

	public Node<E> findNode(E elementToFind) {
		Node walk = head;
		// Node found = null;
		Node<E> nodeToFind = new Node<>(elementToFind, null);
//		System.out.println("finding node: " + nodeToFind.getElement());
		while (walk != null) {
//			System.out.println("walk> " + walk.getElement());
			if (nodeToFind.getElement().equals(walk.getElement())) {
//				System.out.println("found");
				return walk;
			}
			walk = walk.getNext();
		}
		return null;

	}
	
	public void swap(E from, E to) {
		Node<E> prevFrom = getPrevNode(from);
		Node<E> nodeFrom = prevFrom.getNext();
		Node<E> prevTo = getPrevNode(to);
		Node<E> nodeTo = prevTo.getNext();
		Node<E> nextNodeTo = nodeTo.getNext();
		System.out.println("prevFrom; "+prevFrom.getElement());
		System.out.println("prevTo; "+prevTo.getElement());
		Node<E> nextFrom = prevFrom.getNext().getNext();
		Node<E> nextTo = prevTo.getNext().getNext();
		System.out.println("nextFrom; "+nextFrom.getElement());
		System.out.println("nextTo; "+nextTo.getElement());
		//swap
		prevFrom.setNext(nodeTo);
		nodeTo.setNext(nextFrom);		
		prevTo.setNext(nodeFrom);
		nodeFrom.setNext(nextNodeTo);		
	}
	
	public void swapHead(E from, E to) {
		if(head.getElement().equals(from)) {//firs node is equal to head
			Node<E> nodeFrom = head;
			Node<E> prevTo = getPrevNode(to);
			Node<E> nodeTo = prevTo.getNext();
			Node<E> nextNodeTo = nodeTo.getNext();
			Node<E> nextNodeFrom = head.getNext();			
			
			
			nodeFrom.setNext(nextNodeTo);
			
			prevTo.setNext(nodeFrom);
			
			
			head = nodeTo;
			head.setNext(nextNodeFrom);
			
			//nodeTo.setNext(nextNodeFrom);
						
			
		}
		//Node<E> prevFrom = getPrevNode(from);
		
		
		
				
	}
	
	public Node<E> getPrevNode(E elementToFind) {
		Node walk = head;
		Node<E> nodeToFind = new Node<>(elementToFind, null);
		while (walk != null) {
			//if (nodeToFind.getNext().getElement().equals(walk.getElement())) {
			System.out.println("walk> "+walk.getElement()+ " next; "+walk.getNext().getElement());
			System.out.println("walk.getNext().getElement().equals(elementToFind) "+walk.getNext().getElement().equals(elementToFind));
			if(null!= walk.getNext() && null!=walk.getNext().getElement() && walk.getNext().getElement().equals(elementToFind)) {
				return walk;
			}
			walk = walk.getNext();
		}
		return null;
	}

}
