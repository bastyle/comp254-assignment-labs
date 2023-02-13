package ca.centennial.comp254.lab3.exercise1.bastian.bastias;

public class ProductOf2Int {
	/**
	 * Create a recursive algorithm to compute the product of two positive integers,
	 * m and n, using only addition and subtraction. Implement the Java or Python
	 * code. Hint: You need subtraction to count down from m or n and addition to do
	 * the arithmetic needed to get the right answer. Check linearSum method from
	 * Week 5 examples.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(productOf(5, 52));
	}

	private static int productOf(int m, int n) {
		if (0 == n) {
			return 0;
		}
		return m + productOf(m, n - 1);
	}

}
