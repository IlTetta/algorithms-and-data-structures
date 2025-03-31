package com.andreat.algorithms.dynamicprogramming.knapsack.unbounded;

/**
 * Implements the Unbounded Knapsack problem using dynamic programming.
 * Given a set of items, each with a weight and a value, determine the subset of items to include in a knapsack
 * so that the total weight is no more than a given capacity and the total value is maximized.
 * In this version, you can include multiple instances of the same item.
 */
public class UnboundedKnapsack {

    /**
     * Solves the Unbounded Knapsack problem using dynamic programming.
     *
     * @param capacity The maximum weight capacity of the knapsack.
     * @param weights  An array of the weights of the items.
     * @param values   An array of the values of the items.
     * @param n        The number of items.
     * @return The maximum total value that can be put in the knapsack.
     */
    public static int unboundedKnapsack(int capacity, int[] weights, int[] values, int n) {
        int[] dp = new int[capacity + 1];

        // Build the dp table in bottom-up manner
        for (int i = 0; i <= capacity; i++) {
            for (int j = 0; j < n; j++) {
                if (weights[j] <= i) {
                    dp[i] = Math.max(dp[i], dp[i - weights[j]] + values[j]);
                }
            }
        }

        return dp[capacity];
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] values = new int[]{10, 30, 20};
        int[] weights = new int[]{5, 10, 15};
        int capacity = 100;
        int n = values.length;

        System.out.println("Maximum value in unbounded knapsack: " + unboundedKnapsack(capacity, weights, values, n)); // Expected output: 300 (10 items of weight 5 and value 10)
    }
}
