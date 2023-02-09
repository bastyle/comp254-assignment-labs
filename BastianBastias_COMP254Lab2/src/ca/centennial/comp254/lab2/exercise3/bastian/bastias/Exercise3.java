package ca.centennial.comp254.lab2.exercise3.bastian.bastias;

public class Exercise3 {

	public static int findMissingNumber(int[] nums) {
		int n = nums.length; // O(1)
		int expectedSum = n * (n + 1) / 2; // This line calculates the expected sum of the numbers from 0 to n-1
											// using the formula n * (n + 1) / 2. The time complexity of this operation
											// is O(1).
		System.out.println("expectedSum: " + expectedSum);
		int actualSum = 0; // O(1)
		for (int num : nums) { // This line starts a for-each loop that iterates over each element in the input
								// array nums. The time complexity of this operation is O(n), as the loop will
								// run n times.
			actualSum += num; // O(1)
		}
		return expectedSum - actualSum; // O(1)
		// This implementation has a time complexity of O(n).
	}

	public static void main(String[] args) {
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 8, 9 };
		System.out.println("Missing number: " + findMissingNumber(nums));
	}
	// The missing number can be found by using the property that the sum of numbers
	// from 0 to n-1 is n*(n-1)/2.

}
