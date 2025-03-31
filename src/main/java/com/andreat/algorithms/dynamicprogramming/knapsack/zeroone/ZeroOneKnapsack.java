package com.andreat.algorithms.dynamicprogramming.knapsack.zeroone;

/**
 * Implements the 0/1 Knapsack problem using dynamic programming.
 * Given a set of items, each with a weight and a value, determine the subset of items to include in a knapsack
 * so that the total weight is no more than a given capacity and the total value is maximized.
 * For each item, you can either include it completely (1) or exclude it completely (0).
 */

public class ZeroOneKnapsack {

    /**
     * Solves the 0/1 Knapsack problem using dynamic programming.
     *
     * @param capacity The maximum weight capacity of the knapsack.
     * @param weights  An array of the weights of the items.
     * @param values   An array of the values of the items.
     * @param n        The number of items.
     * @return The maximum total value that can be put in the knapsack.
     */
    public static int knapsack(int capacity, int[] weights, int[] values, int n){
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                }else{
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] values = new int[]{60, 100, 120};
        int[] weights = new int[]{10, 20, 30};
        int capacity = 50;
        int n = values.length;

        System.out.println("Maximum value in knapsack: " + knapsack(capacity, weights, values, n)); // Expected output: 220 (item 2 and item 3)
    }
}
