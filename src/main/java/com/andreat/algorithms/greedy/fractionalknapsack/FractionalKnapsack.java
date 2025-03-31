package com.andreat.algorithms.greedy.fractionalknapsack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements the Fractional Knapsack problem using a greedy approach.
 * Given a set of items, each with a weight and a value, determine the maximum value that can be obtained
 * by selecting fractions of items, assuming that a fraction of an item can be taken.
 */
public class FractionalKnapsack {

    /**
     * Represents an item with its weight and value.
     */
    private static class Item {
        int weight;
        int value;
        double ratio;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    /**
     * Solves the Fractional Knapsack problem using a greedy approach.
     *
     * @param capacity The maximum weight capacity of the knapsack.
     * @param weights  An array of the weights of the items.
     * @param values   An array of the values of the items.
     * @return The maximum total value that can be put in the knapsack.
     */
    public static double fractionalKnapsack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        if (n == 0 || capacity == 0) {
            return 0;
        }

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        Arrays.sort(items, Comparator.comparingDouble((Item a) -> a.ratio).reversed());

        double totalValue = 0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remainingCapacity = capacity - currentWeight;
                totalValue += item.ratio * remainingCapacity;
                currentWeight += remainingCapacity;
                break;
            }
        }

        return totalValue;
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

        System.out.println("Maximum value in fractional knapsack: " + fractionalKnapsack(capacity, weights, values)); // Expected output: 240.0 (take all of item 1 and 2, and 2/3 of item 3)
    }
}