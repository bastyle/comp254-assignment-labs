package maps;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exercise1();
	}

	private static void exercise1() {
		System.out.println("---------------- exercise 1 -----------------");
		SortedTableMap<String, Integer> stm = new SortedTableMap<>();
		stm.put("A", 1);
		stm.put("B", 2);
		stm.put("C", null);
		stm.put("D", 3);
		System.out.println("original sorted map:");
		for (Entry<String, Integer> e : stm.entrySet()) {
			System.out.println(e);
		}
		
		stm.containKey("A");
		stm.containKey("E");
		stm.containKey("B");
		stm.containKey("C");
		stm.containKey("F");
		System.out.println("----------------            -----------------");
	}
}
