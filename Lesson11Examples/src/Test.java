
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Lab 7");
		exercise1();
	}

	private static void exercise1() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(6, "A");
		map.put(2, "B");
		map.put(4, "C");
		map.put(1, "D");
		map.put(9, "E");
		map.put(8, "F");
		// search
//		for (String value : map.values()) {
//			System.out.println(value);
//		}
		System.out.println("get key 4: " + map.get(4));
		System.out.println("get key 8: " + map.get(8));
		System.out.println("get key 40: " + map.get(40));

	}

}
