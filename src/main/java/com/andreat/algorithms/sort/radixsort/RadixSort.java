package com.andreat.algorithms.sort.radixsort;

import java.util.Arrays;

/**
 * The {@code RadixSort} class implements the radix sort algorithm for sorting
 * an array of non-negative integers. Radix sort is a non-comparative integer
 * sorting algorithm.
 */
public class RadixSort {

    /**
     * Sorts an array of non-negative integers using the radix sort algorithm.
     *
     * @param arr the array of non-negative integers to be sorted.
     * @throws IllegalArgumentException if the input array is null or contains negative numbers.
     */
    public void radixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int n = arr.length;
        if (n == 0) {
            return;
        }

        int maxVal = getMax(arr);
        if (maxVal < 0) {
            throw new IllegalArgumentException("Input array cannot contain negative numbers.");
        }

        for (int exp = 1; maxVal / exp > 0; exp *= 10) {
            countingSortForRadix(arr, n, exp);
        }
    }

    /**
     * A utility function to get the maximum value in an array.
     *
     * @param arr the input array.
     * @return the maximum value in the array.
     */
    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * A function to do counting sort of arr[] according to the digit represented
     * by exp.
     *
     * @param arr the array to be sorted.
     * @param n   the size of the array.
     * @param exp the current exponent (10^i).
     */
    private void countingSortForRadix(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * The main method demonstrates the usage of the {@code RadixSort} class.
     * It creates a sample array of non-negative integers, sorts it using radix
     * sort, and prints the sorted array.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        RadixSort sorter = new RadixSort();
        System.out.println("Original array: " + Arrays.toString(array));
        sorter.radixSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int[] emptyArray = {};
        System.out.println("Original empty array: " + Arrays.toString(emptyArray));
        sorter.radixSort(emptyArray);
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        int[] arrayWithZeros = {0, 15, 2, 8, 0, 30};
        System.out.println("Original array with zeros: " + Arrays.toString(arrayWithZeros));
        sorter.radixSort(arrayWithZeros);
        System.out.println("Sorted array with zeros: " + Arrays.toString(arrayWithZeros));

        try {
            int[] negativeArray = {1, -2, 3};
            sorter.radixSort(negativeArray);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}