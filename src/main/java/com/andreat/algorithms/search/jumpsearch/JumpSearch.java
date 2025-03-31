package com.andreat.algorithms.search.jumpsearch;

/**
 * The {@code JumpSearch} class implements the jump search algorithm to find
 * the index of a target value within a sorted array.
 */
public class JumpSearch {

    /**
     * Performs jump search on a sorted array to find the index of the target value.
     *
     * @param arr    the sorted array to search within.
     * @param target the value to search for.
     * @return the index of the target value in the array if found, otherwise -1.
     * @throws IllegalArgumentException if the input array is null.
     */
    public int jumpSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;
            }
        }

        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }

        if (arr[prev] == target) {
            return prev;
        }

        return -1;
    }

    /**
     * The main method demonstrates the usage of the {@code JumpSearch} class.
     * It creates a sample sorted array and searches for a target value.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] sortedArray = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        int target = 55;
        JumpSearch searcher = new JumpSearch();
        int index = searcher.jumpSearch(sortedArray, target);

        if (index != -1) {
            System.out.println("Target " + target + " found at index " + index);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }

        int notFoundTarget = 15;
        int indexNotFound = searcher.jumpSearch(sortedArray, notFoundTarget);
        if (indexNotFound != -1) {
            System.out.println("Target " + notFoundTarget + " found at index " + indexNotFound);
        } else {
            System.out.println("Target " + notFoundTarget + " not found in the array");
        }
    }
}