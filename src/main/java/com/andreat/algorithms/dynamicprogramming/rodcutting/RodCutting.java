package com.andreat.algorithms.dynamicprogramming.rodcutting;

/**
 * Implements the Rod Cutting problem using dynamic programming.
 * Given a rod of length n and a table of prices for each length i (where 1 <= i <= n),
 * determine the maximum revenue obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {

    /**
     * Solves the Rod Cutting problem using dynamic programming.
     *
     * @param prices An array of prices where prices[i] is the price of a rod of length i+1.
     * @param n      The length of the rod.
     * @return The maximum revenue obtainable.
     */
    public static int cutRod(int[] prices, int n){
        int[] dp = new int[n+1];
        dp[0] = 0; // No revenue for rod of length 0

        for(int i=1; i<=n; i++){
            int maxRevenue = Integer.MIN_VALUE;
            for(int j=1; j<=i; j++){
                maxRevenue = Math.max(maxRevenue, prices[j-1] + dp[i-j]);
            }
            dp[i] = maxRevenue;
        }
        return dp[n];
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int rodLength = 8;
        System.out.println("Maximum revenue obtainable is: " + cutRod(prices, rodLength)); // Expected output: 22 (2 + 2 + 2 + 2 -> 5+5+5+5 = 20, 3+5 -> 8+10 = 18, 2+6 -> 5+17 = 22)
    }
}
