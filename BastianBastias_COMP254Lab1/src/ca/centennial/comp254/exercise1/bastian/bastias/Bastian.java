package ca.centennial.comp254.exercise1.bastian.bastias;

public class Bastian {

	public static void main(String[] args) {
//		reverse("pots&pans");
//		reverse2("pots&pans");
		String aux = "123";
		System.out.println(aux.substring(0, aux.length()));

//		int a[] = { 1, 2, 3, 4, 4, 5 };
//		System.out.println(findRepeatedInt(a));

	}

	private static int findRepeatedInt(int[] intArray) {
		int[] auxArray = new int[intArray.length];
		for (int i : intArray) {
			if (auxArray[i] == i) {
				return i;
			}
			auxArray[i] = i;
		}
		return -1;
	}

	public static int findRepeatedIntOri(int[] A) {
		boolean[] found = new boolean[A.length]; // all elements false, by default
		for (int val : A)
			if (found[val])
				return val;
			else
				found[val] = true;

		return -1; // shouldn't happen if input as expected
	}

	// Write a short recursive Java method that takes a character string s and
	// outputs its reverse. For example, the reverse of 'pots&pans' would be
	// 'snap&stop'
	public static void reverse2(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		char aux = word.charAt(word.length() - 1);
		System.out.print(aux);
		reverse2(word.substring(0, word.length() - 1));
	}

	public static void reverse(String s) {
		reverse(s, s.length() - 1); // reverse it
	}

	// input: a string and the index of the last character in it
	// output: reversed string
	public static void reverse(String s, int n) {
		// stopping condition
		if (n < 0)
			throw new IllegalStateException("String must have one or more chars");
		if (n >= 0) {
			System.out.println(s.charAt(n));
			reverse(s, n - 1);
		}
	}

}
