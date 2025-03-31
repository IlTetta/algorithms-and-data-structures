package com.andreat.algorithms.search.exponentialsearch;

/**
 * The {@code ExponentialSearch} class implements the exponential search algorithm
 * to find the index of a target value within a sorted array. Exponential search
 * is particularly useful for unbounded arrays where the size is not known.
 */
public class ExponentialSearch {

    /**
     * Performs exponential search on a sorted array to find the index of the
     * target value.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int exponentialSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        if (arr[0] == target) {
            return 0;
        }

        int i = 1;
        while (i < n && arr[i] <= target) {
            i *= 2;
        }

        // Now perform binary search in the found range
        int left = i / 2;
        int right = Math.min(i, n - 1);

        return binarySearch(arr, target, left, right);
    }

    /**
     * Helper method to perform binary search within a specified range.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @param left   the starting index of the search range.
     * @param right  the ending index of the search range.
     * @return the index of the target value if found, otherwise -1.
     */
    private int binarySearch(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * The main method demonstrates the usage of the {@code ExponentialSearch} class.
     * It creates a sample sorted array and searches for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] sortedArray = {2, 3, 4, 10, 40};
        int target = 10;
        ExponentialSearch searcher = new ExponentialSearch();
        int index = searcher.exponentialSearch(sortedArray, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 15;
        int indexNotFound = searcher.exponentialSearch(sortedArray, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }

        int[] largeArray = new int[100];
        for (int i = 0; i < 100; i++) {
            largeArray[i] = i * 2;
        }
        int targetLarge = 198;
        int indexLarge = searcher.exponentialSearch(largeArray, targetLarge);
        if (indexLarge != -1) {
            System.out.println("Target " + targetLarge + " found at index " + indexLarge);
        } else {
            System.out.println("Target " + targetLarge + " not found in the array");
        }
    }
}

