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
package ca.centennial.comp254.bbastias.lab4;

/**
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList. All
 * operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedQueue<E> implements Queue<E> {

	/** The primary storage for elements of the queue */
	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // an empty list

	/** Constructs an initially empty queue. */
	public LinkedQueue() {
	} // new queue relies on the initially empty list

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return number of elements in the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Tests whether the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Inserts an element at the rear of the queue.
	 * 
	 * @param element the element to be inserted
	 */
	@Override
	public void enqueue(E element) {
		list.addLast(element);
	}

	/**
	 * Returns, but does not remove, the first element of the queue.
	 * 
	 * @return the first element of the queue (or null if empty)
	 */
	@Override
	public E first() {
		return list.first();
	}

	/**
	 * Removes and returns the first element of the queue.
	 * 
	 * @return element removed (or null if empty)
	 */
	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	/**
	 * Produces a string representation of the contents of the queue. (from front to
	 * back). This exists for debugging purposes only.
	 */
	public String toString() {
		return list.toString();
	}

	public void concatenateRec(LinkedQueue<E> Q2) {
		if (Q2.isEmpty())
			return;
		enqueue(Q2.dequeue());
		concatenate(Q2);
		return;
	}

	public void concatenate(LinkedQueue<E> Q2) {
		if (Q2.isEmpty()) {// checks if the given LinkedQueue Q2 is empty by calling its "isEmpty" method.
			return;
		}
		SinglyLinkedList<E> otherList = Q2.list;// creates a new SinglyLinkedList object "otherList" and assigns it the
												// value of the internal SinglyLinkedList object "list" of Q2.
		if (isEmpty()) {// checks if the current LinkedQueue object is empty by calling its "isEmpty"
			this.list.setHead(otherList.getHead());// sets the head of the current LinkedQueue object's internal
													// SinglyLinkedList to the head of the otherList if the current
													// LinkedQueue object is empty.
		} else {
			this.list.getTail().setNext(otherList.getHead());// sets the next node of the tail of the current
																// LinkedQueue
																// object's internal SinglyLinkedList to the head of
																// otherList.
		}
		this.list.setTail(otherList.getTail());// sets the tail of the current LinkedQueue object's internal
												// SinglyLinkedList to the tail of otherList.
		this.list.setSize(this.list.size() + otherList.getSize()); // updates the size of the current LinkedQueue
																	// object's internal SinglyLinkedList by adding the
																	// size of otherList to it.
		// clean Q2
		otherList.setHead(null);
		otherList.setTail(null);
		otherList.setSize(0);
	}

}
