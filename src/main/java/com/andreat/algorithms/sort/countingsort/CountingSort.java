package com.andreat.algorithms.sort.countingsort;

import java.util.Arrays;

/**
 * The {@code CountingSort} class implements the counting sort algorithm for
 * sorting an array of non-negative integers within a specific range.
 */
public class CountingSort {

    /**
     * Sorts an array of non-negative integers using the counting sort algorithm.
     * This algorithm is efficient when the range of input values is not significantly
     * larger than the number of items to be sorted.
     *
     * @param arr the array of non-negative integers to be sorted.
     * @throws IllegalArgumentException if the input array is null or contains negative numbers.
     */
    public void countingSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;
        if (n == 0) {
            return;
        }

        int maxVal = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < 0) {
                throw new IllegalArgumentException("Input array cannot contain negative numbers.");
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        int[] count = new int[maxVal + 1];

        for (int j : arr) {
            count[j]++;
        }

        for (int i = 1; i <= maxVal; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * The main method demonstrates the usage of the {@code CountingSort} class.
     * It creates a sample array of non-negative integers, sorts it using counting
     * sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {4, 2, 2, 8, 3, 3, 1};
        CountingSort sorter = new CountingSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.countingSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.countingSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] arrayWithZeros = {0, 5, 2, 8, 0, 3};
        System.out.println("Original array with zeros: " + Arrays.toString(arrayWithZeros));
        sorter.countingSort(arrayWithZeros);
        System.out.println("Sorted array with zeros: " + Arrays.toString(arrayWithZeros));

        try {
            int[] negativeArray = {1, -2, 3};
            sorter.countingSort(negativeArray);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
