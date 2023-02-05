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
 * Demonstration of algorithm for finding the maximum element of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class ArrayMax {
   //executes a maximum 2 + 2 +2*n + 2*n + n + 1= 5 + 5*n of primitive operations
  /** Returns the maximum value of a nonempty array of numbers. */
  public static double arrayMax(double[] data) {
	// one initialization + one length call = 2 ops
    int n = data.length; 
    // one value assignment + one indexing into array = 2 ops
    double currentMax = data[0];     // assume first entry is biggest (for now)
    // one loop variable update + one comparison for each iteration = 2*n ops
    for (int j=1; j < n; j++)        // consider all other entries
      // one indexing into array + one comparison for each iteration = 2*n ops
      if (data[j] > currentMax)      // if data[j] is biggest thus far...
    	// one value assignment <= n times =   n ops maximum
        currentMax = data[j];        // record it as the current max
    // 1 ops
    return currentMax;
  }

}
