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
		list.addLast("DDD");
		list.addLast("EEE");

		list.swapNodes("BBB", "DDD");

		list.swapNodes("AAA", "BBB");
		
		list.swapNodes("DDD", "CCC");
		
		list.swapNodes("AAA", "EEE");
		
		//validate tail
		list.swapNodes("DDD", "EEE");
		
		list.swapNodes("BBB", "AAA");


	}

}
