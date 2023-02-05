package ca.centennial.comp254.lab2.exercise1.bastian.bastias;
/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Code for end-of-chapter exercises on asymptotics.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Exercises {

	public static void main(String[] args) {
		example3(new int[] { 1, 2, 3 });
	}

	// running time is O(n)
	/** Returns the sum of the integers in given array. */
	public static int example1(int[] arr) {
		int n = arr.length, total = 0; // O(1)
		for (int j = 0; j < n; j++) // The for loop runs from 0 to n-1. This means that the loop will run n times.
			total += arr[j]; // O(1)
		return total; // O(1)
		// Since the loop runs n times and each iteration takes constant time, the total
		// time taken is proportional to n, so the time complexity can be expressed as
		// O(n).
	}

	/** Returns the sum of the integers with even index in given array. */
	public static int example2(int[] arr) {
		int n = arr.length, total = 0; // O(1)
		for (int j = 0; j < n; j += 2) // The for loop runs from 0 to n-1 with an increment of 2. This means that the
										// loop will run n/2 times.
			total += arr[j]; // O(1)
		return total; // O(1)
		// The overall time complexity is O(n/2), because the dominant part of the
		// function is the loop that runs n/2 times. This is a linear time complexity,
		// but the factor of n is divided by 2, so the growth rate is slower than O(n).
	}

	/** Returns the sum of the prefix sums of given array. */
	public static int example3(int[] arr) {
		int n = arr.length, total = 0; // O(1)
		for (int j = 0; j < n; j++) // this loop runs from 0 to n-1. This means that the loop will run n times.
			for (int k = 0; k <= j; k++) // this loop runs from 0 to j, where j is the current iteration of the outer
											// loop.
											// This means that the inner loop will run 1 times on the first iteration, 2
											// time on the second iteration, 3 times on the third iteration, and so on,
											// until n-1 times on the last iteration.
				total += arr[j]; // O(1)
		return total; // O(1)
		// The overall time complexity is O(n^2), because the dominant part of the
		// function is the two nested loops.
	}

	/** Returns the sum of the prefix sums of given array. */
	public static int example4(int[] arr) {
		int n = arr.length, prefix = 0, total = 0; // O(1)
		for (int j = 0; j < n; j++) { // The for loop runs from 0 to n-1. This means that the loop will run n times.
			prefix += arr[j]; // O(1)
			total += prefix; // O(1)
		}
		return total; // O(1)
		// The overall time complexity is O(n), because the dominant part of the
		// function is the for loop, which runs n times. The other parts of the function
		// take a constant amount of time, and do not affect the overall time
		// complexity.
	}

	/**
	 * Returns the number of times second array stores sum of prefix sums from
	 * first.
	 */
	public static int example5(int[] first, int[] second) { // assume equal-length arrays
		int n = first.length, count = 0;
		for (int i = 0; i < n; i++) { // loop from 0 to n-1 => O(n)
			int total = 0;
			for (int j = 0; j < n; j++) // loop from 0 to n-1 => O(n)
				for (int k = 0; k <= j; k++) // loop from 0 to j => O(n)
					total += first[k]; // O(1)
			if (second[i] == total) // O(1)
				count++; // O(1)
		}
		return count;
	}
	// Therefore, the total running time of the function is O(n * n * n) = O(n^3).
}
