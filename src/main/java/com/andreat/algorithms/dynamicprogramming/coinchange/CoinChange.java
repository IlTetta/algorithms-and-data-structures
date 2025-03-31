package com.andreat.algorithms.dynamicprogramming.coinchange;

import java.util.Arrays;

/**
 * Implements the Coin Change problem using dynamic programming to find the minimum number of coins
 * needed to make up a specific amount.
 */
public class CoinChange {

    public static int minCoins(int[] coins, int amount){
        if(amount < 0) {
            return -1;
        }
        if(amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins needed for amount " + amount + ": " + minCoins(coins, amount)); // Expected output: 3 (5 + 5 + 1)

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Minimum coins needed for amount " + amount2 + ": " + minCoins(coins2, amount2)); // Expected output: -1

        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Minimum coins needed for amount " + amount3 + ": " + minCoins(coins3, amount3)); // Expected output: 0
    }
}
