package ca.centennial.comp254.exercise1.bastian.bastias;

public class Exercise3 {

	public static void main(String[] args) {
		CircularlyLinkedList<String> circularList = new CircularlyLinkedList<String>();
		circularList.addFirst("1");
		circularList.addLast("2");
		circularList.addLast("3");
		circularList.addLast("4");
		circularList.addLast("5");
		System.out.println("original circularList: " + circularList);
		try {
			CircularlyLinkedList<String> clonedCircularList;
			clonedCircularList = circularList.clone();
			System.out.println("cloned circular list; " + clonedCircularList);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
