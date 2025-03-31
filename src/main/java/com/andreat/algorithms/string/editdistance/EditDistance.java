package com.andreat.algorithms.string.editdistance;

/**
 * Implements the Edit Distance (Levenshtein Distance) algorithm using dynamic programming.
 * The edit distance between two strings is the minimum number of single-character edits
 * (insertions, deletions, or substitutions) required to change one word into the other.
 */
public class EditDistance {

    /**
     * Calculates the edit distance between two strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The minimum number of edits required to transform s1 into s2.
     */
    public static int calculateEditDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i =0; i <= m; i++) {
            dp[i][0] = i; // Deletion cost
        }
        for(int j = 0; j <= n; j++) {
            dp[0][j] = j; // Insertion cost
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, // Deletion
                                        Math.min(dp[i][j - 1] + 1, // Insertion
                                                 dp[i - 1][j - 1] + 1)); // Substitution
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String s1 = "kitten";
        String s2 = "sitting";

        System.out.println("Edit distance between \"" + s1 + "\" and \"" + s2 + "\" is: " + calculateEditDistance(s1, s2)); // Expected output: 3

        String s3 = "intention";
        String s4 = "execution";

        System.out.println("Edit distance between \"" + s3 + "\" and \"" + s4 + "\" is: " + calculateEditDistance(s3, s4)); // Expected output: 5
    }
}
