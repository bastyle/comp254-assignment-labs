package ca.centennial.comp254.exercise1.bastian.bastias;

/**
 * 
 * @author Bastian Bastias. Student id; 301242983
 *
 */
public class Exercise1 {

	public static void main(String[] args) {
		SinglyLinkedList<String> list = new SinglyLinkedList<String>();
		list.addFirst("AAA");
		list.addLast("BBB");
		list.addLast("CCC");
		//
		list.addLast("DDD");
		list.addLast("EEE");
		System.out.println(list);
		//
//		System.out.println("finding node 'BBB' > ");
//		list.swapTwoNodes("BBB", "DDD");
//		System.out.println(list);
		
		//list.swapTwoNodes2("BBB", "DDD");
		//ok
		//list.swap("BBB", "DDD");
		//System.out.println(list);
		
		//validate head
		list.swapHead("AAA", "DDD");
		System.out.println("head;"+list);

	}

}
