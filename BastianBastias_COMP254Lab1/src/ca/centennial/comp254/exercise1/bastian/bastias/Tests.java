package ca.centennial.comp254.exercise1.bastian.bastias;

import java.util.Arrays;

public class Tests {

	public static void main(String[] args) {
		// write and test a recursive java method that takes a character string s and
		// outputs its reverse. For example, the reverse of java would be avaj, use
		// charAt method of string class
		reverse("java");
		// write and test a Java method that deletes the ith node of a
		// singlylinkedlist. display an appropiate message if such a node does not
		// exist. your method will take an integer argument and should be added to
		// singlylinkedlist class

		// write and test an efficient java method for finding the ten largest elements
		// in an array of size n. What is the running time of your algorithm? use an
		// auxiliary array to store indices of largest elements and ignore previous
		// found elements. Note that 10 is a constant.

		// Let B be an array of size n ≥ 6 containing integers from 1 to n−5 inclusive,
		// five of which are repeated. Write and test a method for finding the five
		// integers in B that are repeated. What is the running time of this algorithm?
		// Hint: Sort the array B then scan it from front to end looking for the
		// repeated entries
	}

	public static int[] findRepeatedElements(int[] B) {
		int[] repeatedElements = new int[5];
		Arrays.sort(B);
		int j = 0;
		for (int i = 1; i < B.length; i++) {
			if (B[i] == B[i - 1]) {
				if (j < 5 && B[i] != repeatedElements[j - 1]) {
					repeatedElements[j++] = B[i];
				}
			}
		}
		return repeatedElements;
	}

	public static int[] findRepeatedElements2(int[] B) {
		int[] repeatedElements = new int[5];
		int n = B.length;
		for (int i = 0; i < n; i++) {
			int index = Math.abs(B[i]) - 1;
			if (B[index] > 0) {
				B[index] = -B[index];
			} else if (index < n - 5) {
				repeatedElements[Math.abs(B[i]) - (n - 5)] = Math.abs(B[i]);
			}
		}
		for (int i = 0; i < n; i++) {
			B[i] = Math.abs(B[i]);
		}
		return repeatedElements;
	}

	public static int[] findTenLargestElements(int[] arr) {
		int n = arr.length;
		int[] indices = new int[10];
		int[] largest = new int[10];
		Arrays.fill(largest, Integer.MIN_VALUE);

		for (int i = 0; i < n; i++) {
			int num = arr[i];
			if (num > largest[0]) {
				largest[0] = num;
				indices[0] = i;
				for (int j = 1; j < 10; j++) {
					if (largest[j] < largest[j - 1]) {
						int temp = largest[j];
						largest[j] = largest[j - 1];
						largest[j - 1] = temp;

						temp = indices[j];
						indices[j] = indices[j - 1];
						indices[j - 1] = temp;
					} else {
						break;
					}
				}
			}
		}
		return indices;
		// O(n) O(n)^2
	}

	public static void findLargest(int[] intArray) {
		int[] auxArray = new int[10];
		for (int i = 0; i < 10; i++) {
			int maxIndx = 0;
			for (int j = 1; j < intArray.length; j++) {
				if (intArray[j] > intArray[maxIndx]) {
					maxIndx = j;
				}
			}

		}

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

	public static int findRepeatedIntOri(int[] B) {
		boolean[] found = new boolean[B.length]; // all elements false, by default
		for (int val : B)
			if (found[val])
				return val;
			else
				found[val] = true;

		return -1; // shouldn't happen if input as expected
	}

	public static void reverse(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		char aux = word.charAt(word.length() - 1);
		System.out.print(aux);
		reverse(word.substring(0, word.length() - 1));
	}

}
