package com.andreat.algorithms.sort.insertionsort;

import java.util.Arrays;

/**
 * The {@code InsertionSort} class implements the insertion sort algorithm for
 * sorting an array of integers. Insertion sort builds the final sorted array
 * one item at a time.
 */
public class InsertionSort {

    /**
     * Sorts an array of integers using the insertion sort algorithm.
     *
     * @param arr the array to be sorted.
     * @throws IllegalArgumentException if the input array is null.
     */
    public void insertionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * The main method demonstrates the usage of the {@code InsertionSort} class.
     * It creates a sample array, sorts it using insertion sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6};
        InsertionSort sorter = new InsertionSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.insertionSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.insertionSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original sorted array: " + Arrays.toString(alreadySortedArray));
        sorter.insertionSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
    }
}
