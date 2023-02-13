package ca.centennial.comp254.lab3.exercise2.bastian.bastias;

public class Palindrome {

	/*
	 * Write a short recursive Java method that determines if a string s is a
	 * palindrome, that is, it is equal to its reverse. Examples of palindromes
	 * include 'racecar' and 'mom'. Test the method by asking the user to provide
	 * string entries to be checked. Hint: Check the equality of the first and last
	 * characters and recur (but be careful to return the correct value for both odd
	 * and even-length strings).
	 */
	public static void main(String[] args) {
		System.out.println(isPalindrome("abcddcba"));
	}

	private static boolean isPalindrome(final String word) {
		System.out.println("word: " + word);
		final int wordLenght = word == null ? 0 : word.length();
		if (wordLenght <= 1) {
			System.out.println("it is");
			return true;
		}
		final String aux = word.substring(0, 1);
//		System.out.println("aux: " + aux);
		if (word.endsWith(aux)) {
			System.out.println("eq");
			return isPalindrome(word.substring(1, wordLenght - 1));
			//return;
		}
		System.out.println("is not");
		return false;

	}

}
