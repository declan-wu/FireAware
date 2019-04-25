package xb3.fire.api.algs;

import xb3.fire.api.building.Building;

public class Search {
	/**
	 * @author Jennifer Cheng
	 */
	
	/**
	 * Binary search to return a building
	 * 
	 * @param arr - the input array containing the list of buildings
	 * @param x - the ID to search for
	 * @return the Building, if found; else, the default Building (0 for all fields)
	 */
	public static Building search(Building arr[], int x) {
		int r = arr.length - 1;
		int l = 0;
		int index = binarySearch(arr, l, r, x);
		if (index >= 0 && index <= r) {
			return arr[index];
		} else {
			return null;
		}
	}

	/**
	 * Binary search implementation
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 * @return the index of the array
	 */
	private static int binarySearch(Building arr[], int l, int r, int x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element found
			if (arr[mid].getId() == x)
				return mid;

			// Left subarray
			if (arr[mid].getId() > x)
				return binarySearch(arr, l, mid - 1, x);

			// Right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// Element not found
		return -1;
	}
}

