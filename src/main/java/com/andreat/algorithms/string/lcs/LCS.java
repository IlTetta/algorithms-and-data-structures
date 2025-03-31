package com.andreat.algorithms.string.lcs;

/**
 * Implements the Longest Common Subsequence (LCS) algorithm using dynamic programming.
 * The LCS of two sequences is the longest subsequence common to both sequences.
 */
public class LCS {

    /**
     * Finds the length of the Longest Common Subsequence (LCS) of two strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The length of the LCS.
     */
    public static int findLCSLength(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = 1; i<= m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Finds the Longest Common Subsequence (LCS) of two strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The LCS string.
     */
    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("Length of LCS is: " + findLCSLength(s1, s2)); // Expected output: 4

        System.out.println("LCS is: " + findLCS(s1, s2)); // Expected output: GTAB
    }
}
