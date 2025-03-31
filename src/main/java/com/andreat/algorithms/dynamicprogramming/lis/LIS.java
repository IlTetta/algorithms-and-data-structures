package com.andreat.algorithms.dynamicprogramming.lis;

import java.util.Arrays;

/**
 * Implements the Longest Increasing Subsequence (LIS) problem using dynamic programming.
 * Given an array of integers, find the length of the longest subsequence that is strictly increasing.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 */
public class LIS {

    /**
     * Finds the length of the Longest Increasing Subsequence (LIS) of a given array of integers using dynamic programming.
     *
     * @param nums The input array of integers.
     * @return The length of the LIS.
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] nums = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println("Length of LIS is: " + longestIncreasingSubsequence(nums)); // Expected output: 6 (10, 22, 33, 50, 60, 80) or (9, 21, 41, 60, 80)

        int[] nums2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println("Length of LIS is: " + longestIncreasingSubsequence(nums2)); // Expected output: 6

        int[] nums3 = {1, 3, 2, 4, 5};
        System.out.println("Length of LIS is: " + longestIncreasingSubsequence(nums3)); // Expected output: 4 (1, 2, 4, 5) or (1, 3, 4, 5)
    }
}
