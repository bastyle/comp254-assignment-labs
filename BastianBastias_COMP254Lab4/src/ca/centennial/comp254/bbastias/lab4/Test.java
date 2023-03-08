package ca.centennial.comp254.bbastias.lab4;

public class Test {

	public static void main(String[] args) {
		exercise1();
	}
	
	private static void exercise1() {
		PositionalList<Integer> list = new Exercise1<>();
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		Position<Integer> p = list.first();
		long index = list.indexOf(p);
		System.out.println("Index of first: " + index);		
		index = list.indexOf(list.last());
		System.out.println("Index of last: " + index);
	}

}
