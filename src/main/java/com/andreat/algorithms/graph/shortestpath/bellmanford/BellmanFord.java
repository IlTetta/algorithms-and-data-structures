package com.andreat.algorithms.graph.shortestpath.bellmanford;

import java.util.Arrays;

/**
 * The {@code BellmanFord} class implements the Bellman-Ford algorithm to find the
 * shortest paths from a single source vertex to all other vertices in a
 * weighted directed graph. It can handle graphs with negative edge weights,
 * but not negative cycles reachable from the source.
 */
public class BellmanFord {

    private int numVertices;
    private int numEdges;
    private Edge[] edges;

    /**
     * Represents an edge in the weighted directed graph.
     */
    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Constructs a {@code BellmanFord} object with a specified number of vertices
     * and an initial capacity for edges.
     *
     * @param numVertices the total number of vertices in the graph.
     * @param numEdges    the initial capacity for the number of edges.
     */
    public BellmanFord(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        this.edges = new Edge[numEdges];
    }

    private int edgeCount = 0;

    /**
     * Adds a directed edge to the graph.
     *
     * @param source      the starting vertex of the edge.
     * @param destination the ending vertex of the edge.
     * @param weight      the weight of the edge. Can be negative.
     */
    public void addEdge(int source, int destination, int weight) {
        if (edgeCount < numEdges) {
            edges[edgeCount++] = new Edge(source, destination, weight);
        } else {
            throw new IllegalStateException("Graph is full. Cannot add more edges.");
        }
    }

    /**
     * Finds the shortest paths from the given source vertex to all other vertices
     * in the graph using the Bellman-Ford algorithm.
     *
     * @param source the source vertex from which to find the shortest paths.
     * @return an array of integers where the i-th element represents the shortest
     * distance from the source vertex to vertex i. If a vertex is not
     * reachable, its distance will be {@code Integer.MAX_VALUE}. If a negative
     * cycle is detected, returns {@code null}.
     * @throws IllegalArgumentException if the source vertex is invalid.
     */
    public int[] bellmanFord(int source) {
        if (source < 0 || source >= numVertices) {
            throw new IllegalArgumentException("Invalid source vertex.");
        }

        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Relax edges V-1 times
        for (int i = 1; i < numVertices; i++) {
            for (int j = 0; j < edgeCount; j++) {
                Edge edge = edges[j];
                if (distances[edge.source] != Integer.MAX_VALUE &&
                        distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }

        // Check for negative cycles
        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges[i];
            if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                return null; // Negative cycle detected
            }
        }

        return distances;
    }

    /**
     * The main method demonstrates the usage of the {@code BellmanFord} class.
     * It creates a sample weighted directed graph and finds the shortest paths
     * from a specified source vertex. It also demonstrates a case with a negative cycle.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 8;
        BellmanFord graph = new BellmanFord(numVertices, numEdges);

        // Example graph
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        int source = 0;
        int[] shortestDistances = graph.bellmanFord(source);

        if (shortestDistances != null) {
            System.out.println("Shortest distances from vertex " + source + ":");
            for (int i = 0; i < numVertices; i++) {
                System.out.println("To vertex " + i + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinity" : shortestDistances[i]));
            }
        } else {
            System.out.println("Negative cycle detected in the graph.");
        }

        System.out.println("\nExample with a negative cycle:");
        BellmanFord graphNegativeCycle = new BellmanFord(3, 3);
        graphNegativeCycle.addEdge(0, 1, -1);
        graphNegativeCycle.addEdge(1, 2, -2);
        graphNegativeCycle.addEdge(2, 0, -3);

        int[] shortestDistancesNegativeCycle = graphNegativeCycle.bellmanFord(0);
        if (shortestDistancesNegativeCycle != null) {
            System.out.println("Shortest distances from vertex 0:");
            for (int i = 0; i < 3; i++) {
                System.out.println("To vertex " + i + ": " + (shortestDistancesNegativeCycle[i] == Integer.MAX_VALUE ? "Infinity" : shortestDistancesNegativeCycle[i]));
            }
        } else {
            System.out.println("Negative cycle detected in the graph.");
        }
    }
}