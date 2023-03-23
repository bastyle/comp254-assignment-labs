package ca.centennial.comp254.bbastias.lab5.tree;
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

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract base class providing some functionality of the BinaryTree
 * interface.
 *
 * The following five methods remain abstract, and must be implemented by a
 * concrete subclass: size, root, parent, left, right.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/**
	 * Returns the Position of p's sibling (or null if no sibling exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the sibling (or null if no sibling exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = parent(p);
		if (parent == null)
			return null; // p must be the root
		if (p == left(parent)) // p is a left child
			return right(parent); // (right child might be null)
		else // p is a right child
			return left(parent); // (left child might be null)
	}

	/**
	 * Returns the number of children of Position p.
	 *
	 * @param p A valid Position within the tree
	 * @return number of children of Position p
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public int numChildren(Position<E> p) {
		int count = 0;
		if (left(p) != null)
			count++;
		if (right(p) != null)
			count++;
		return count;
	}

	/**
	 * Returns an iterable collection of the Positions representing p's children.
	 *
	 * @param p A valid Position within the tree
	 * @return iterable collection of the Positions of p's children
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> snapshot = new ArrayList<>(2); // max capacity of 2
		if (left(p) != null)
			snapshot.add(left(p));
		if (right(p) != null)
			snapshot.add(right(p));
		return snapshot;
	}

	/**
	 * Adds positions of the subtree rooted at Position p to the given snapshot
	 * using an inorder traversal
	 * 
	 * @param p        Position serving as the root of a subtree
	 * @param snapshot a list to which results are appended
	 */
	private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		if (left(p) != null)
			inorderSubtree(left(p), snapshot);
		snapshot.add(p);
		if (right(p) != null)
			inorderSubtree(right(p), snapshot);
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in inorder.
	 * 
	 * @return iterable collection of the tree's positions reported in inorder
	 */
	public Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			inorderSubtree(root(), snapshot); // fill the snapshot recursively
		return snapshot;
	}

	/**
	 * Returns an iterable collection of the positions of the tree using inorder
	 * traversal
	 * 
	 * @return iterable collection of the tree's positions using inorder traversal
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return inorder();
	}

	public int postOrderHeight(Position<E> p) throws IllegalArgumentException {
		if (numChildren(p) == 0) {
			return 0;
		}
		int leftHeight = postOrderHeight(p);
		int rightHeight = postOrderHeight(p);
		return 1 + Math.max(leftHeight, rightHeight);
	}

	private void postorderSubtree2(Position<E> p, Map<Position<E>, Integer> heights) {
		int count = 0;
		if (numChildren(p) == 0) {
			System.out.println(p.getElement() + " is leaf");
			heights.put(p, 0);
			return;
		}
		for (Position<E> c : children(p)) {
			postorderSubtree2(c, heights);
		}
		heights.put(p, count);

		int h = 0; // base case if p is external
		for (Position<E> c : children(p))
			h = Math.max(h, 1 + height(c));

	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in
	 * postorder.
	 * 
	 * @return iterable collection of the tree's positions in postorder
	 */
	public Map<Position<E>, Integer> postorder2() {
		Map<Position<E>, Integer> heights = new HashMap<>();
		if (!isEmpty())
			postorderSubtree2(root(), heights); // fill the snapshot recursively

		return heights;
	}

	/// 3
	private int postorderSubtree3(Position<E> p, List<Position<E>> snapshot, int height) {
		int h = 0; // base case if p is external (leaf)
		for (Position<E> c : children(p)) {			
			h = Math.max(h, 1 + postorderSubtree3(c, snapshot, height));
		}
		System.out.println(p.getElement() + " height: " + h);
		snapshot.add(p); // for postorder, we add position p after exploring subtrees
		return h;
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in
	 * postorder.
	 * 
	 * @return iterable collection of the tree's positions in postorder
	 */
	public Iterable<Position<E>> postorder3() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			postorderSubtree3(root(), snapshot, 0); // fill the snapshot recursively
		return snapshot;
	}

	public int height3(Position<E> p) throws IllegalArgumentException {
		int h = 0; // base case if p is external
		for (Position<E> c : children(p))
			h = Math.max(h, 1 + height3(c));
		return h;
	}
}
