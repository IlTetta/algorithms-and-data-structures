package com.andreat.algorithms.search.linearsearch;

/**
 * The {@code LinearSearch} class implements the linear search algorithm to find
 * the index of a target value within an array.
 */
public class LinearSearch {

    /**
     * Performs linear search on an array to find the index of the target value.
     *
     * @param arr    the array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int linearSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * The main method demonstrates the usage of the {@code LinearSearch} class.
     * It creates a sample array and searches for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {5, 2, 8, 12, 1, 9, 4};
        int target = 12;
        LinearSearch searcher = new LinearSearch();
        int index = searcher.linearSearch(array, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 7;
        int indexNotFound = searcher.linearSearch(array, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }
    }
}