package ca.centennial.comp254.lab3.exercise3.bastian.bastias;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VowelsConsonants {

	private static final List<Character> VOWELS1 = Arrays.asList('a', 'e', 'i', 'o', 'u');
	private static int vowelsCount=0;

	/***
	 * Write a recursive method that takes a string as argument and determines if
	 * the string has more vowels than consonants. Test the method by asking the
	 * user to enter a string. Hint: Write your recursive method to first count
	 * vowels and consonants.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Scanner scanner = new Scanner(System.in); System.out.
		 * print("Enter a string to check if it has more vowels than consonants: ");
		 * String string = scanner.nextLine();
		 * System.out.println(hasMoreVowels(string));
		 */

		System.out.println(hasMoreVowels2("aaabaaaalllll"));
	}

	private static boolean hasMoreVowels(String word) {
		final int vowels = vowelsCounter1(word);
		final int consonants = word.length() - vowels;
		System.out.println("'" + word + "' " + (vowels > consonants ? "has more vowels (" + vowels + ")"
				: "has the same amount or more consonants than vowels  (" + consonants + ")"));
		return vowels > consonants;
	}

	private static int vowelsCounter1(String word) {
		final int wordLenght = word == null ? 0 : word.length();
		if (wordLenght == 0) {
			return 0;
		}
		return (VOWELS1.contains(word.charAt(0)) ? 1 : 0) + vowelsCounter1(word.substring(1, wordLenght));
	}

	private static boolean hasMoreVowels2(String word) {
		final int wordLenght = word == null ? 0 : word.length();
		if (wordLenght == 0) {
			return false;
		}
		vowelsCount += (VOWELS1.contains(word.charAt(0)) ? 1 : 0);
		hasMoreVowels2(word.substring(1, wordLenght));
		return (word.length() - vowelsCount) < vowelsCount;
	}

}
