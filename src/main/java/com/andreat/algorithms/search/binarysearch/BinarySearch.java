package com.andreat.algorithms.search.binarysearch;

/**
 * The {@code BinarySearch} class implements the iterative binary search algorithm
 * to find the index of a target value within a sorted array.
 */
public class BinarySearch {

    /**
     * Performs iterative binary search on a sorted array to find the index of
     * the target value.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int binarySearch(int[] arr, int target){
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids overflow
            if (arr[mid] == target) {
                return mid;
            }else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * The main method demonstrates the usage of the {@code BinarySearch} class.
     * It creates a sample sorted array and searches for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 23;
        BinarySearch searcher = new BinarySearch();
        int index = searcher.binarySearch(sortedArray, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 42;
        int indexNotFound = searcher.binarySearch(sortedArray, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }
    }
}
