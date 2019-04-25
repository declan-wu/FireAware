
public class Merge {
	/**
	 * @author Jennifer Cheng, Lab 02
	 */
	private static Comparable[] aux; // Auxiliary array for merging.

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
	 * Merge two sub-arrays together.
	 * 
	 * @param x   - the input array containing products that need to be merged
	 * @param lo  - smallest index of the sub-array to be merged
	 * @param mid - the middle index of the sub-array to be merged
	 * @param hi  - the highest index of the sub-array to be merged
	 */
	// Code is from the textbook Algorithms, 4th ed. by Sedgewick and Wayne, page
	// 271
	private static void merge(Comparable[] x, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) // Copy the entire array to the auxiliary array.
			aux[k] = x[k];
		for (int k = lo; k <= hi; k++) // Merge it back into the original array.
			if (i > mid) {
				x[k] = aux[j++];
			} else if (j > hi) {
				x[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				x[k] = aux[j++];
			} else
				x[k] = aux[i++];
	}

	/**
	 * Function to divide and search the array by indices
	 * 
	 * @param x  - the input array containing products that need to be sorted.
	 * @param lo - the smallest index of the array
	 * @param hi - the largest index of the array
	 */
	// Code is from the textbook Algorithms, 4th ed. by Sedgewick and Wayne, page
	// 273
	private static void sortMerge(Comparable[] x, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		// Sort & merge the two sides (lo-mid and mid+1-hi)
		sortMerge(x, lo, mid);
		sortMerge(x, mid + 1, hi);
		merge(x, lo, mid, hi);
	}

	/**
	 * top-down merge sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD(Comparable[] x, int n) {
		aux = new Comparable[n];
		sortMerge(x, 0, n - 1);
	}

	/**
	 * bottom-up merge sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	// Code is from the textbook Algorithms, 4th ed. by Sedgewick and Wayne, page
	// 278
	public static void sortMergeBU(Comparable[] x, int n) {
		aux = new Comparable[n];
		for (int i = 1; i < n; i += i) // i is the subarray size.
			for (int lo = 0; lo < n - i; lo += i + i) // lo is the subarray index.
				merge(x, lo, lo + i - 1, Math.min(lo + i + i - 1, n - 1));
	}
}
