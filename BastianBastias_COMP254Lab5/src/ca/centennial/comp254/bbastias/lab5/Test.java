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


//		exercise1(lbt, root);
//		exercise1(lbt, bLeft);
//		exercise1(lbt, dLeft);
//		exercise1(lbt, hLeft);
//		exercise1(lbt, eRight);
//		exercise1(lbt, fLeft);
		exercise1B(lbt, root);
		exercise1B(lbt, eRight);
		exercise1B(lbt, bLeft);
		exercise1B(lbt, gRight);
		
//		exercise2(lbt, bLeft);
	}

	private static <E> void exercise1(LinkedBinaryTree<String> T, Position<String> position) {
		System.out.println("----- exercise1A (preorderNext) ------");
//		Position<String> aux = LinkedBinaryTree.preorderNext(T, position);
		Position<String> aux = LinkedBinaryTree.orderNext(T, position, true);
		System.out.println("Next preorder position of " + position + " is " + aux);
		aux = T.preorder2(position);
		System.out.println("Next preorder position of " + position + " is " + aux);
	}

	private static <E> void exercise1B(LinkedBinaryTree<String> T, Position<String> position) {
//		System.out.println("----- exercise1B (inorderNext) ------");
//		Position<String> aux = LinkedBinaryTree.inorderNext(T, position);
//		System.out.println("Next inorder position of " + position + " is " + aux);
//		aux = T.inorder2(position);
//		System.out.println("Next inorder position of " + position + " is " + aux);
//		System.out.println("----------------------------");
		Position<String> aux  = T.inorder3(position);
		System.out.println("Next inorder position of " + position + " is " + aux);
		
	}
	
	private static void exercise2(LinkedBinaryTree<String> T, Position<String> position) {
		System.out.println("----- exercise1B (inorderNext) ------");
		T.postorderPrintHeight();
	}
}
