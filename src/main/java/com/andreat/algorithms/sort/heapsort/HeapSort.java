package com.andreat.algorithms.sort.heapsort;

import java.util.Arrays;

/**
 * The {@code HeapSort} class implements the heap sort algorithm for sorting an
 * array of integers. Heap sort uses a binary heap data structure.
 */
public class HeapSort {

    /**
     * Sorts an array of integers using the heap sort algorithm.
     *
     * @param arr the array to be sorted.
     * @throws IllegalArgumentException if the input array is null.
     */
    public void heapSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);

            heapify(arr, i, 0);
        }
    }

    /**
     * To heapify a subtree rooted with node i which is an index in arr[].
     * n is size of heap.
     *
     * @param arr the array representing the heap.
     * @param n   the size of the heap.
     * @param i   the index of the root of the subtree.
     */
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);

            heapify(arr, n, largest);
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
     * The main method demonstrates the usage of the {@code HeapSort} class.
     * It creates a sample array, sorts it using heap sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        HeapSort sorter = new HeapSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.heapSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.heapSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original sorted array: " + Arrays.toString(alreadySortedArray));
        sorter.heapSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
    }
}