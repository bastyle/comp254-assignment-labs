package ca.centennial.comp254.bbastias.lab5;

import ca.centennial.comp254.bbastias.lab5.tree.AbstractTree;
import ca.centennial.comp254.bbastias.lab5.tree.LinkedBinaryTree;
import ca.centennial.comp254.bbastias.lab5.tree.Position;
import ca.centennial.comp254.bbastias.lab5.tree.Tree;

public class Test {

	public static void main(String[] args) {
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

		// exercise1A();
		exercise1B(lbt, root);
		exercise1B(lbt, eRight);
		exercise1B(lbt, bLeft);
	}

	private static <E> void exercise1A() {
		System.out.println("------------");
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree<>();
		Position<String> root = lbt.addRoot("A");
		//
		Position<String> bLeft = lbt.addLeft(root, "B");
		Position<String> cRight = lbt.addRight(root, "C");
		//
		Position<String> dLeft = lbt.addLeft(bLeft, "D");
		Position<String> eRight = lbt.addRight(bLeft, "E");
		//
		Position<String> fLeft = lbt.addLeft(cRight, "F");
		Position<String> gRight = lbt.addRight(cRight, "G");
		//
		Position<String> hLeft = lbt.addLeft(eRight, "H");
		Position<String> iRight = lbt.addRight(eRight, "I");
		Position<String> aux = LinkedBinaryTree.preorderNext(lbt, hLeft);
		printPreorder(lbt);
		System.out.println(aux);

	}

	private static <E> void exercise1B(LinkedBinaryTree<String> T, Position<String> position) {
		System.out.println("----- exercise1B (inorderNext) ------");
		Position<String> aux = LinkedBinaryTree.inorderNext(T, position);
		System.out.println("Next inorder position of " + position + " is " + aux);
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
