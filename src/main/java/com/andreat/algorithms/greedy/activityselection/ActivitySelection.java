package com.andreat.algorithms.greedy.activityselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Implements the Activity Selection problem using a greedy approach.
 * Given a set of activities with their start and finish times, select the maximum number of activities
 * that can be performed by a single person, assuming that a person can work on only one activity at a time.
 */
public class ActivitySelection {

    /**
     * Represents an activity with its start and finish time.
     */
    private static class Activity {
        int start;
        int finish;
        int index; // To keep track of the original activity index

        public Activity(int start, int finish, int index) {
            this.start = start;
            this.finish = finish;
            this.index = index;
        }
    }

    /**
     * Selects the maximum number of non-overlapping activities using a greedy approach.
     *
     * @param start An array of start times of the activities.
     * @param finish An array of finish times of the activities.
     * @return A list of indices of the selected activities.
     */
    public static List<Integer> selectActivities(int[] start, int[] finish) {
        int n = start.length;
        if (n == 0) {
            return new ArrayList<>();
        }

        Activity[] activities = new Activity[n];
        for (int i = 0; i < n; i++) {
            activities[i] = new Activity(start[i], finish[i], i);
        }

        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        List<Integer> selectedActivities = new ArrayList<>();
        selectedActivities.add(activities[0].index);

        int lastFinishTime = activities[0].finish;

        for (int i = 1; i < n; i++) {
            if (activities[i].start >= lastFinishTime) {
                selectedActivities.add(activities[i].index);
                lastFinishTime = activities[i].finish;
            }
        }

        return selectedActivities;
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] finish = {2, 4, 6, 7, 9, 9};
        List<Integer> result = selectActivities(start, finish);
        System.out.println("Selected activities (indices): " + result); // Expected output (one possible solution): [0, 1, 3, 4]
    }
}
