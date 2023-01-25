package ca.centennial.comp254.exercise1.bastian.bastias;

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

	
	public static void concatenate2List(SinglyLinkedList<String>list1, SinglyLinkedList<String>list2) {
		System.out.println("list1; "+list1);
		System.out.println("list2; "+list2);
//		SinglyLinkedList<String> list3 = new SinglyLinkedList<>();
//		list3.addFirst(list1.first());
//		System.out.println("final list;"+list3);
		
		
	}
}
