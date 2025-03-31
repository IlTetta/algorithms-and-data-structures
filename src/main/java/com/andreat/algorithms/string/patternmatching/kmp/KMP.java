package com.andreat.algorithms.string.patternmatching.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Knuth-Morris-Pratt (KMP) algorithm for finding occurrences of a pattern within a text.
 * The KMP algorithm avoids redundant comparisons by utilizing information about the pattern itself.
 */
public class KMP {

    /**
     * Searches for all occurrences of a given pattern within a text.
     *
     * @param text    The text to search within.
     * @param pattern The pattern to search for.
     * @return A list of starting indices where the pattern is found in the text.
     * Returns an empty list if the pattern is not found or if the pattern is longer than the text.
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return occurrences;
        }

        if(n < m) {
            return occurrences;
        }

        int[] lps = computeLPSArray(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern

        while(i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                occurrences.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return occurrences;
    }

    /**
     * Computes the Longest Proper Prefix which is also a Suffix (LPS) array for a given pattern.
     * The LPS array is used by the KMP algorithm to efficiently shift the pattern.
     *
     * @param pattern The pattern for which to compute the LPS array.
     * @return The computed LPS array.
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0; // length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        List<Integer> result = search(text, pattern);
        System.out.println("Pattern found at indices: " + result); // Expected output: [10]
    }
}
