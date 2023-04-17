import java.util.Arrays;
import java.util.Comparator;

/*
 * **************************
 * 	QuickSort.java 
 * 
 * ************************** 
 */

/**
 * QuickSort is a divide and conquer algorithm. It picks an element as pivot and
 * partitions the given array around the picked pivot.
 * <p>
 * 
 * Reference: 12.2 Quick-Sort :Data Structures and Algorithms in Java, Sixth
 * Edition
 * <p>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see http://www.gnu.org/licenses/.
 * <p>
 * 
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * 
 */
public class QuickSort {

	// -------- support for in-place quick-sort of an array --------
	/**
	 * Quick-sort contents of a queue.
	 * 
	 * @param S    Unsorted array
	 * @param comp Comparator
	 */
	public static <K> void quickSortInPlace(K[] S, Comparator<K> comp) {
		quickSortInPlace(S, comp, 0, S.length - 1);
	}

	/**
	 * Sort the subarray S[a..b] inclusive.
	 * 
	 * @param S    Unsorted array
	 * @param comp comparator
	 * @param a    left index (too big index)
	 * @param b    right index (too small index)
	 */
	private static <K> void quickSortInPlace(K[] S, Comparator<K> comp, int a, int b) {
		if (a >= b)
			return; // subarray is trivially sorted
		int left = a;
		int right = b - 1;
		K pivot = S[b]; // right-most element is the pivot
		K temp; // temp object used for swapping
		while (left <= right) {
			// scan until reaching value equal or larger than pivot (or right
			// marker)
			while (left <= right && comp.compare(S[left], pivot) < 0)
				left++;
			// scan until reaching value equal or smaller than pivot (or left
			// marker)
			while (left <= right && comp.compare(S[right], pivot) > 0)
				right--;
			if (left <= right) { // indices did not strictly cross
				// so swap values and shrink range
				temp = S[left];
				S[left] = S[right];
				S[right] = temp;
				left++;
				right--;
			}
		}
		// put pivot into its final place (currently marked by left index)
		temp = S[left];
		S[left] = S[b];
		S[b] = temp;
		// make recursive calls
		quickSortInPlace(S, comp, a, left - 1);
		quickSortInPlace(S, comp, left + 1, b);
	}

	/**
	 * Return the Character array out of the String
	 * 
	 * @param sourceString :String as argument
	 * @return CharcterArray
	 */
	public static Character[] toCharacterArray(String sourceString) {
		char[] charArrays = new char[sourceString.length()];
		charArrays = sourceString.toCharArray();
		Character[] characterArray = new Character[charArrays.length];
		for (int i = 0; i < charArrays.length; i++) {
			characterArray[i] = charArrays[i];
		}
		return characterArray;
	}

	// test method
	public static void main(String[] args) {

		String word = "Hello, World!";

		Character[] arrCharacters = toCharacterArray(word.toLowerCase());

		System.out.println(Arrays.toString(arrCharacters)); // unsorted

		// define the comparator
		Comparator<Character> comparator = new Comparator<Character>() {

			@Override
			public int compare(Character arg0, Character arg1) {

				return arg0.compareTo(arg1);
			}
		};

		quickSortInPlace(arrCharacters, comparator); // sort the array

		System.out.println(Arrays.toString(arrCharacters)); // unsorted

	}
}
