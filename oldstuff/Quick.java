/**
 * @author Jennifer Cheng, Lab 02
 */

import java.util.Random;

public class Quick {
	/**
	 * Shuffling an array with comparables.
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void shuffle(Comparable[] x) {
		Random rand = new Random();
		for (int i = 0; i < x.length; i++) {
			int n = rand.nextInt(i + 1);
			Comparable a = x[n]; // swaps two random products.
			x[n] = x[i];
			x[i] = a;

		}
	}

	/**
	 * Function to see if one value is less than the other.
	 * 
	 * @param a - the first object to be compared
	 * @param b - the second object to be compared
	 */
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0; // If a is less than b, then return True
	}

	/**
	 * Swaps two values.
	 * 
	 * @param x - the input array containing products to be swapped
	 * @param i - the index of the first product to be swapped
	 * @param j - the index of the second product to be swapped
	 */
	private static void swap(Comparable[] x, int i, int j) {
		Comparable y = x[i];
		x[i] = x[j];
		x[j] = y;
	}

	/**
	 * Partition for basic quicksort.
	 * 
	 * @param x  - the input array containing products to be partitioned
	 * @param lo - the index of beginning of the array to be partitioned
	 * @param hi - the index of the end of the array to be partitioned
	 */
	// Code is from the textbook Algorithms, 4th ed. by Sedgewick and Wayne, page
	// 291
	private static int partition(Comparable[] x, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable pivot = x[lo];
		while (true) {
			while (less(x[++i], pivot)) { // scan right.
				if (i == hi)
					break;
			}
			while (less(pivot, x[--j])) { // scan left.
				if (j == lo)
					break;
			}
			if (i >= j) // check if complete.
				break;
			swap(x, i, j);
		}
		swap(x, lo, j); // v is placed in the correct location.
		return j;
	}

	/**
	 * Quick sort on an array.
	 * 
	 * @param x  - the input array containing products that need to be sorted
	 * @param lo - the smallest index of the array to be sorted
	 * @param hi - the largest index of the array to be sorted
	 */
	// Code is from the textbook Algorithms, 4th ed. by Sedgewick and Wayne, page
	// 289
	private static void sortQuick(Comparable[] x, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partition(x, lo, hi);
		sortQuick(x, lo, j - 1);
		sortQuick(x, j + 1, hi);
	}

	/**
	 * Basic quick sort.
	 * 
	 * @param x - the input array containing products that need to be sorted
	 */
	public static void sortBasicQuick(Building[] x) {
		shuffle(x);
		sortQuick(x, 0, x.length - 1);
	}

	/**
	 * three partition quick sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortThreePartition(Comparable[] x, int n) {
		shuffle(x);
		sortQuick(x, 0, n - 1);
	}

}
