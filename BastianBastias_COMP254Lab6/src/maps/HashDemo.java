package maps;

public class HashDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s = new Student("Bastian", 30108912);
		Student s2 = new Student("Laura", 30118912);
//		System.out.println(": " + s);
//		System.out.println(":: " + s.hashCode());
		int h1Res = h1(s);
		System.out.println("::: " + h1Res);
		int h2Res = h2(h1Res, 2);
		System.out.println(":::: " + h2Res);

		int h1Res2 = h1(s2);
		System.out.println("::: " + h1Res2);
		int h2Res2 = h2(h1Res2, 2);
		System.out.println(":::: " + h2Res2);

	}

	private static int h1(Object key) {
		int h = key.hashCode();
		return h;
	}

	public static int h2(int hashCode, int N) {
		return hashCode % N;
	}

}
