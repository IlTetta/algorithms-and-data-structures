package com.andreat.algorithms.sort.mergesort;

import java.util.Arrays;

import java.util.Arrays;

/**
 * The {@code MergeSort} class implements the merge sort algorithm for sorting an
 * array of integers. Merge sort is a divide-and-conquer algorithm.
 */
public class MergeSort {

    /**
     * Sorts an array of integers using the merge sort algorithm.
     *
     * @param arr the array to be sorted.
     * @throws IllegalArgumentException if the input array is null.
     */
    public void mergeSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive helper method to divide the array into subarrays.
     *
     * @param arr   the array being sorted.
     * @param left  the starting index of the current subarray.
     * @param right the ending index of the current subarray.
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays of arr[].
     * The first subarray is arr[left..mid].
     * The second subarray is arr[mid+1..right].
     *
     * @param arr   the array containing the subarrays to be merged.
     * @param left  the starting index of the first subarray.
     * @param mid   the ending index of the first subarray.
     * @param right the ending index of the second subarray.
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

    /**
     * The main method demonstrates the usage of the {@code MergeSort} class.
     * It creates a sample array, sorts it using merge sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        MergeSort sorter = new MergeSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.mergeSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.mergeSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original sorted array: " + Arrays.toString(alreadySortedArray));
        sorter.mergeSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
    }
}
