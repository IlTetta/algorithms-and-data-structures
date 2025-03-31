package com.andreat.algorithms.search.interpolationsearch;

/**
 * The {@code InterpolationSearch} class implements the interpolation search
 * algorithm to find the index of a target value within a sorted array.
 * Interpolation search is useful for uniformly distributed data.
 */
public class InterpolationSearch {

    /**
     * Performs interpolation search on a sorted array to find the index of
     * the target value.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int interpolationSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                if (arr[low] == target) return low;
                return -1;
            }

            int pos = low + ((high - low) / (arr[high] - arr[low])) * (target - arr[low]);

            if (arr[high] == arr[low]) {
                if (arr[low] == target) return low;
                return -1;
            }

            // If calculated position is out of bounds due to non-uniform distribution,
            // we can fall back to binary search approach to prevent errors.
            if (pos < low || pos > high) {
                pos = low + (high - low) / 2;
            }

            if (arr[pos] == target) {
                return pos;
            } else if (arr[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }

        return -1;
    }

    /**
     * The main method demonstrates the usage of the {@code InterpolationSearch} class.
     * It creates a sample sorted array (ideally uniformly distributed) and searches
     * for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] sortedArray = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        int target = 22;
        InterpolationSearch searcher = new InterpolationSearch();
        int index = searcher.interpolationSearch(sortedArray, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 15;
        int indexNotFound = searcher.interpolationSearch(sortedArray, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }

        int[] nonUniformArray = {2, 3, 4, 5, 6, 7, 8, 9, 10, 99};
        int targetNonUniform = 99;
        int indexNonUniform = searcher.interpolationSearch(nonUniformArray, targetNonUniform);
        if (indexNonUniform != -1) {
            System.out.println("Target " + targetNonUniform + " found at index " + indexNonUniform);
        } else {
            System.out.println("Target " + targetNonUniform + " not found in the array");
        }
    }
}
