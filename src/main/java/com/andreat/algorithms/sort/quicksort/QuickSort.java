package com.andreat.algorithms.sort.quicksort;

import java.util.Arrays;

/**
 * The {@code QuickSort} class implements the quicksort algorithm for sorting an
 * array of integers. Quick sort is a divide-and-conquer algorithm.
 */
public class QuickSort {

    /**
     * Sorts an array of integers using the quicksort algorithm.
     *
     * @param arr the array to be sorted.
     * @throws IllegalArgumentException if the input array is null.
     */
    public void quickSort(int[] arr){
        if(arr == null){
            throw new IllegalArgumentException("Input array cannot be null");
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive helper method to perform quicksort on a subarray.
     *
     * @param arr   the array being sorted.
     * @param low   the starting index of the subarray.
     * @param high  the ending index of the subarray.
     */
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the subarray from low to high around a pivot element.
     * Elements smaller than the pivot are moved to the left, and elements
     * greater than the pivot are moved to the right.
     *
     * @param arr   the array being partitioned.
     * @param low   the starting index of the subarray.
     * @param high  the ending index of the subarray.
     * @return the index of the pivot element after partitioning.
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
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
     * The main method demonstrates the usage of the {@code QuickSort} class.
     * It creates a sample array, sorts it using quicksort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5};
        QuickSort sorter = new QuickSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.quickSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.quickSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original sorted array: " + Arrays.toString(alreadySortedArray));
        sorter.quickSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
    }
}
