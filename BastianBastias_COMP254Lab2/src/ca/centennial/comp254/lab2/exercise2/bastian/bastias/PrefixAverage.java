package ca.centennial.comp254.lab2.exercise2.bastian.bastias;
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

import org.jfree.data.xy.XYSeries;

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class PrefixAverage {

	/**
	 * Returns an array a such that, for all j, a[j] equals the average of x[0],
	 * ..., x[j]. A[j] = (X[0] + X[1] + � + X[j])/(j+1)
	 * 
	 ******************************************************/
	// inner loop size will be 1, 2, 3, ..., n (based on j=0,1,2,...,n-1)
	// we know that 1+2+3+...+ n-1+n = n(n+1)/2
	// so, the running time os O(n^2)
	public static double[] prefixAverage1(double[] x) {
		int n = x.length;
		double[] a = new double[n]; // filled with zeros by default
		for (int j = 0; j < n; j++) {
			double total = 0; // begin computing x[0] + ... + x[j]
			for (int i = 0; i <= j; i++)
				total += x[i];
			a[j] = total / (j + 1); // record the average
		}
		return a;
	}

	/**
	 * Returns an array a such that, for all j, a[j] equals the average of x[0],
	 * ..., x[j].
	 */
	// the running time is O(n)
	public static double[] prefixAverage2(double[] x) {
		int n = x.length;
		double[] a = new double[n]; // filled with zeros by default
		double total = 0; // compute prefix sum as x[0] + x[1] + ...
		for (int j = 0; j < n; j++) {
			total += x[j]; // update prefix sum to include x[j]
			a[j] = total / (j + 1); // compute average based on current sum
		}
		return a;
	}

	public static void main(String[] args) {
		int n = 50000; // starting value
		double[] prefixExperiment;
		long startTime = 0;
		long endTime = 0;
		long elapsed = 0;
		int trials = 2;
		final XYSeries s1 = new XYSeries("prefixAverage1");
		final XYSeries s2 = new XYSeries("prefixAverage2");
		s1.add(1, 1);
		s2.add(1, 1);
		for (int i = 0; i < trials; i++) {
			prefixExperiment = new double[n];
			for (int j = 0; j < n; j++) {
				prefixExperiment[i] = i;
			}
			System.out.println("Testing prefixAverage1...");
			startTime = System.currentTimeMillis();
			prefixAverage1(prefixExperiment);
			endTime = System.currentTimeMillis();
			elapsed = endTime - startTime;
			System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
			s1.add(n, elapsed);
			
			System.out.println("Testing prefixAverage2...");
			startTime = System.currentTimeMillis();
			prefixAverage2(prefixExperiment);
			endTime = System.currentTimeMillis();
			elapsed = endTime - startTime;
			System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
			s2.add(n, elapsed<1?1:elapsed);
			n *= 2;
			System.out.println("---------------------------------------------------------");
		}

		final GraphicalRepresentation demo = new GraphicalRepresentation("Prefix Average Graph");
		demo.printGraph(s1,s2);
		demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}
