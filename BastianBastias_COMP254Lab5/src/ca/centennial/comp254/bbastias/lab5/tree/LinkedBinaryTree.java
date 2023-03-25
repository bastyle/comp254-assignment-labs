package ca.centennial.comp254.bbastias.lab5.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.centennial.comp254.bbastias.lab5.tree.LinkedBinaryTree.Node;

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

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	// ---------------- nested Node class ----------------
	/** Nested static class for a binary tree node. */
	protected static class Node<E> implements Position<E> {
		private E element; // an element stored at this node
		private Node<E> parent; // a reference to the parent node (if any)
		private Node<E> left; // a reference to the left child (if any)
		private Node<E> right; // a reference to the right child (if any)

		/**
		 * Constructs a node with the given element and neighbors.
		 *
		 * @param e          the element to be stored
		 * @param above      reference to a parent node
		 * @param leftChild  reference to a left child node
		 * @param rightChild reference to a right child node
		 */
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}

		// get and set methods
		//
		// accessor methods
		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		// update methods
		public void setElement(E e) {
			element = e;
		}

		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}

		public void setLeft(Node<E> leftChild) {
			left = leftChild;
		}

		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}

		@Override
		public String toString() {
			return "Node [element=" + element + "]";
		}

	} // ----------- end of nested Node class -----------

	/** Factory function to create a new node storing element e. */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
	}

	// LinkedBinaryTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null; // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0; // number of nodes in the tree

	// constructor
	/** Construts an empty binary tree. */
	public LinkedBinaryTree() {
	} // constructs an empty binary tree

	// nonpublic utility
	/**
	 * Verifies that a Position belongs to the appropriate class, and is not one
	 * that has been previously removed. Note that our current implementation does
	 * not actually verify that the position belongs to this particular list
	 * instance.
	 *
	 * @param p a Position (that should belong to this tree)
	 * @return the underlying Node instance for the position
	 * @throws IllegalArgumentException if an invalid position is detected
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	// accessor methods (not already implemented in AbstractBinaryTree)
	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return number of nodes in the tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the root Position of the tree (or null if tree is empty).
	 * 
	 * @return root Position of the tree (or null if tree is empty)
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * Returns the Position of p's parent (or null if p is root).
	 *
	 * @param p A valid Position within the tree
	 * @return Position of p's parent (or null if p is root)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	/**
	 * Returns the Position of p's left child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the left child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/**
	 * Returns the Position of p's right child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the right child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	// update methods supported by this class
	/**
	 * Places element e at the root of an empty tree and returns its new Position.
	 *
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty())
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Creates a new left child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the left of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/**
	 * Creates a new right child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the right of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}

	/**
	 * Replaces the element at Position p with element e and returns the replaced
	 * element.
	 *
	 * @param p the relevant Position
	 * @param e the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/**
	 * Attaches trees t1 and t2, respectively, as the left and right subtree of the
	 * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
	 *
	 * @param p  a leaf of the tree
	 * @param t1 an independent tree whose structure becomes the left child of p
	 * @param t2 an independent tree whose structure becomes the right child of p
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p is not a leaf
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(p))
			throw new IllegalArgumentException("p must be a leaf");
		size += t1.size() + t2.size();
		if (!t1.isEmpty()) { // attach t1 as left subtree of node
			t1.root.setParent(node); // the node becomes parent of the root of left subtree
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()) { // attach t2 as right subtree of node
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * Removes the node at Position p and replaces it with its child, if any.
	 *
	 * @param p the relevant Position
	 * @return element that was removed
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p has two children.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if (child != null)
			child.setParent(node.getParent()); // child's grandparent becomes its parent
		if (node == root)
			root = child; // child becomes root
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null); // help garbage collection
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node); // our convention for defunct node
		return temp;
	}

	public static void main(String[] args) {
		// create and populate a linked binary tree
		LinkedBinaryTree lbt = new LinkedBinaryTree();
		Position<String> root = lbt.addRoot("ICET");

		//
		Position<String> softwarePosition = lbt.addLeft(root, "Software");
		Position<String> networkingPosition = lbt.addRight(root, "Networking");
		Position<String> set = lbt.addLeft(softwarePosition, "SET");
		Position<String> ig = lbt.addRight(softwarePosition, "IG");

		//
		printPreorder(lbt);
		parenthesize(lbt, root);

	}

	/** Prints parenthesized representation of subtree of T rooted at p. */
	public static <E> void parenthesize(Tree<E> T, Position<E> p) {
		System.out.print(p.getElement());
		if (T.isInternal(p)) {
			boolean firstTime = true;
			for (Position<E> c : T.children(p)) {
				System.out.print((firstTime ? " (" : ", ")); // determine proper punctuation
				firstTime = false; // any future passes will get comma
				parenthesize(T, c); // recur on child
			}
			System.out.print(")");
		}
	}

	//
	public static <E> void printPreorder(AbstractTree<E> T) {
		for (Position<E> p : T.preorder())
			System.out.println(p.getElement());
	}//

	// lab 5 //

	public static <E> Position<E> preorderNext(LinkedBinaryTree<E> T, Position<E> position) {
		for (Position<E> p : T.preorder()) {
			if (p.getElement() == position.getElement()) {
				Node<E> node = T.validate(position);
				return null != node.left ? node.left : null;
			}
		}
		return null;
	}

	public static <E> Position<E> inorderNext(LinkedBinaryTree<E> T, Position<E> position) {
		List<Position<E>> inorderList = (List<Position<E>>) T.inorder();
		int index = 0;
		for (Position<E> p : inorderList) {
			if (p.getElement() == position.getElement()) {
				break;
			}
			index++;
		}
		return (inorderList.size() > index + 1 && null != inorderList.get(index + 1)) ? inorderList.get(index + 1)
				: null;
	}

	public static <E> Position<E> orderNext(LinkedBinaryTree<E> T, Position<E> position, boolean isPreorder) {
		List<Position<E>> orderList = isPreorder ? (List<Position<E>>) T.preorder() : (List<Position<E>>) T.inorder();
		int index = 0;
		for (Position<E> p : orderList) {
			if (p.getElement() == position.getElement()) {
				break;
			}
			index++;
		}
		return (orderList.size() > index + 1 && null != orderList.get(index + 1)) ? orderList.get(index + 1) : null;
	}

	public static <E> void printElementAndHeight2(LinkedBinaryTree<E> T) {
		T.postorderPrintHeight();
	}

	public static <E> void printElementAndHeight(LinkedBinaryTree<E> T, Position<E> position) {
		Map<Position<E>, Integer> heights = new HashMap<>();
		for (Position<E> p : T.postorder()) {
			if (T.numChildren(p) == 0) {// p is leaf
				heights.put(p, 0);
			} else {
				int maxHeight = -1;
				for (Position<E> child : T.children(p)) {
					int height = heights.get(child);
					if (height > maxHeight) {
						maxHeight = height;
					}
				}
				heights.put(p, maxHeight + 1);
			}
			System.out.println(p.getElement() + " " + heights.get(p));
		}
	}

	// exe 1

//	public static <E> Position<E> preorderNext2(LinkedBinaryTree<E> T, Position<E> position) {
//		if (!T.isEmpty()) {
//			return preorderSubtree2(T,position);
//		}
//		return null;
//
//	}

	private Position<E> preorderSubtree2(Position<E> rootRef, Position<E> p) {
		// for preorder, we add position p before exploring subtrees
		if (root.getElement() == p.getElement()) {
			Node<E> node = validate(p);
			return null != node.left ? node.left : null;
		}
		Position<E> aux = null;
		for (Position<E> c : children(rootRef)) {
			if (c.getElement() == p.getElement()) {
				Node<E> node = validate(c);
				aux = null != node.left ? node.left : sibling(c);
				break;
			}
			if (null == aux)
				aux = preorderSubtree2(c, p);
		}
		return aux;
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in
	 * preorder.
	 * 
	 * @return iterable collection of the tree's positions in preorder
	 */
	public Position<E> preorder2(Position<E> position) {
		if (!isEmpty())
			return preorderSubtree2(root, position);
		return null;
	}

	///

	private Position<E> inorderSubtree2(Position<E> p, Position<E> p2Find) {
		Position<E> aux = null;

		if (p.getElement() == p2Find.getElement()) {// it's node searched (base case)
			// i need to get next one
			if (isLeaf(p)) {// validate if node is root
				Node<E> node = validate(p);
				return node.parent;
			}
			// Node<E> node = validate(p);
			// return null!= node.getLeft()? node.getLeft():sibling(p);

		}
		return aux;
	}

	protected boolean isLeaf(Position<E> p) {
		return numChildren(p) == 0;
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in inorder.
	 * 
	 * @return iterable collection of the tree's positions reported in inorder
	 */
	public Position<E> inorder2(Position<E> p2Find) {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			return inorderSubtree2(root(), p2Find); // fill the snapshot recursively
		return null;
	}

	//
	private int inorderSubtree3(Position<E> initialPosition, List<Position<E>> snapshot, Position<E> f, int index) {
//		if(root.getElement()==initialPosition.getElement())
//			index=0;
		if (left(initialPosition) != null) {
			if (initialPosition.getElement() == f.getElement()) {
				index = snapshot.size();// + 1;
//				System.out.println("node equal left side> "+f.getElement());
//				System.out.println("inx: "+index);				
			}
			index = inorderSubtree3(left(initialPosition), snapshot, f, index);
		}
		snapshot.add(initialPosition);
		if (right(initialPosition) != null) {
			if (initialPosition.getElement() == f.getElement()) {
				index = snapshot.size();// + 1;
//				System.out.println("node equal rigth "+f.getElement());
//				System.out.println("inx: "+index);
			}
			index = inorderSubtree3(right(initialPosition), snapshot, f, index);
		}
		/*
		 * if (root.getElement() == f.getElement()) { index = snapshot.size()-1;// + 1;
		 * System.out.println("root"); }
		 */

		return index;
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in inorder.
	 * 
	 * @return iterable collection of the tree's positions reported in inorder
	 */
	public Position<E> inorder3(Position<E> p) {
		List<Position<E>> snapshot = new ArrayList<>();
		Position<E> nextNode = null;
//		System.out.println("node to search>> " + p);
		if (!isEmpty()) {
			int aux = inorderSubtree3(root(), snapshot, p, -1); // fill the snapshot recursively
//			System.out.println("position>> " + aux);
			if (aux > -1) {
				nextNode = snapshot.get(aux);
			} else {//if (aux == snapshot.size()) {
				nextNode = null;// last position
			} /*else {
				System.out.println("doesn found...");
			}*/
		}
		// return snapshot;
		return nextNode;
	}

} // ----------- end of LinkedBinaryTree class -----------
