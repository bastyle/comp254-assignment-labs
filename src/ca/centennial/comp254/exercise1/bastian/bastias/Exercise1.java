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
		//ok
		list.swap("BBB", "DDD");
		System.out.println("list: "+list);
		
		//validate head
		list.swapHead("AAA", "DDD");
		System.out.println("head;"+list);
		

	}

}
