package ca.centennial.comp254.exercise1.bastian.bastias;

/**
 * 
 * @author Bastian Bastias. Student id; 301242983
 *
 */
public class Exercise2 {

	public static void main(String[] args) {
		SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
		list1.addFirst("AAA");
		list1.addLast("BBB");
		list1.addLast("CCC");
		list1.addLast("DDD");
		list1.addLast("EEE");
		
		SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
		list2.addFirst("FFF");
		list2.addLast("GGG");
		list2.addLast("HHH");
		list2.addLast("III");
		list2.addLast("JJJ");
		
		
		list1.concatenate2List(list2);
		
	}
	
}
