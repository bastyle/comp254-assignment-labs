package ca.centennial.comp254.exercise1.bastian.bastias;

public class Exercise3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircularlyLinkedList<String> circularList = new CircularlyLinkedList<String>();
		circularList.addFirst("1");
		circularList.addLast("2");
		circularList.addLast("3");
		circularList.addLast("4");
		//
		System.out.println(circularList);
		CircularlyLinkedList<String> circularList2;
		try {
//			circularList2 = circularList.clone();
			circularList2 = circularList.clone2();
			System.out.println(circularList2);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
