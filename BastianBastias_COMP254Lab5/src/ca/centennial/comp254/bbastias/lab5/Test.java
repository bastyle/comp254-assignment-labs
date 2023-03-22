package ca.centennial.comp254.bbastias.lab5;

import ca.centennial.comp254.bbastias.lab5.tree.AbstractTree;
import ca.centennial.comp254.bbastias.lab5.tree.LinkedBinaryTree;
import ca.centennial.comp254.bbastias.lab5.tree.Position;
import ca.centennial.comp254.bbastias.lab5.tree.Tree;

public class Test {

	public static void main(String[] args) {
		//parenthesize(lbt, xLeft);
		//printPreorder(lbt);
		exercise1();
	}
	
	private static <E> void exercise1() {
		System.out.println("------------");
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree();
		Position<String> root = lbt.addRoot("+");
		//
		Position<String> xLeft = lbt.addLeft(root, "X");
		Position<String> xRight = lbt.addRight(root, "X");
		//
		Position<String> twoLeft = lbt.addLeft(xLeft, "2");
		Position<String> minusRight = lbt.addRight(xLeft, "-");
		//
		Position<String> threeLeft = lbt.addLeft(xRight, "3");
		Position<String> bRight = lbt.addRight(xRight, "b");
		//
		Position<String> aLeft = lbt.addLeft(minusRight, "a");
		Position<String> oneRight = lbt.addRight(minusRight, "1");
		Position<String> aux = LinkedBinaryTree.preorderNext(lbt, aLeft);
		System.out.println(aux);
		
	}

	
	public static <E> void printPreorder(AbstractTree<E> T) {
		for (Position<E> p : T.preorder())
			System.out.println(p.getElement());
	}

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

}
