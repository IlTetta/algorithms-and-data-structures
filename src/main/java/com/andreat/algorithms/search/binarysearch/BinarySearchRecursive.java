package com.andreat.algorithms.search.binarysearch;

/**
 * The {@code BinarySearchRecursive} class implements the recursive binary search
 * algorithm to find the index of a target value within a sorted array.
 */
public class BinarySearchRecursive {

    /**
     * Performs recursive binary search on a sorted array to find the index of
     * the target value. This method serves as the entry point and calls the
     * recursive helper method.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int binarySearchRecursive(int[] arr, int target) {
        if (arr == null){
            throw new IllegalArgumentException("Input array cannot be null");
        }
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    /**
     * Recursive helper method for binary search.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @param left   the starting index of the current search interval.
     * @param right  the ending index of the current search interval.
     * @return the index of the target value if found within the interval,
     * otherwise -1.
     */
    private int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    /**
     * The main method demonstrates the usage of the {@code BinarySearchRecursive}
     * class. It creates a sample sorted array and searches for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 23;
        BinarySearchRecursive searcher = new BinarySearchRecursive();
        int index = searcher.binarySearchRecursive(sortedArray, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 42;
        int indexNotFound = searcher.binarySearchRecursive(sortedArray, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }
    }

}
