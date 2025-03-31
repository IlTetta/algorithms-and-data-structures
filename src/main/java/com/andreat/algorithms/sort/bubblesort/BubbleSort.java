package com.andreat.algorithms.sort.bubblesort;

import java.util.Arrays;

/**
 * The {@code BubbleSort} class implements the bubble sort algorithm for sorting
 * an array of integers. Bubble sort repeatedly steps through the list, compares
 * adjacent elements and swaps them if they are in the wrong order.
 */
public class BubbleSort {

    /**
     * Sorts an array of integers using the bubble sort algorithm.
     *
     * @param arr the array to be sorted.
     * @throws IllegalArgumentException if the input array is null.
     */
    public void bubbleSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr the array in which to swap elements.
     * @param i   the index of the first element.
     * @param j   the index of the second element.
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * The main method demonstrates the usage of the {@code BubbleSort} class.
     * It creates a sample array, sorts it using bubble sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        BubbleSort sorter = new BubbleSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.bubbleSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.bubbleSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original sorted array: " + Arrays.toString(alreadySortedArray));
        sorter.bubbleSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
    }
}