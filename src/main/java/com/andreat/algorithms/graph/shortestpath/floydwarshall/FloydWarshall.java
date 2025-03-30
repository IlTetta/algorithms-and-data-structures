package com.andreat.algorithms.graph.shortestpath.floydwarshall;

import java.util.Arrays;

/**
 * The {@code FloydWarshall} class implements the Floyd-Warshall algorithm to find
 * the shortest paths between all pairs of vertices in a weighted directed graph.
 * It can handle graphs with negative edge weights, but not negative cycles.
 */
public class FloydWarshall {

    private int numVertices;
    private int[][] distanceMatrix;
    private static final int INF = Integer.MAX_VALUE;

    /**
     * Constructs a {@code FloydWarshall} object with a specified number of vertices.
     * The graph is initially represented with infinite distances between all
     * pairs of vertices (except for the distance from a vertex to itself, which is 0).
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public FloydWarshall(int numVertices) {
        this.numVertices = numVertices;
        this.distanceMatrix = new int[numVertices][numVertices];
        for(int i = 0; i < numVertices; i++) {
            Arrays.fill(distanceMatrix[i], INF);
            distanceMatrix[i][i] = 0;
        }
    }

    /**
     * Adds a directed edge to the graph with the given weight. If an edge
     * already exists, this method will update the weight if the new weight is smaller.
     *
     * @param source      the starting vertex of the edge.
     * @param destination the ending vertex of the edge.
     * @param weight      the weight of the edge. Can be negative.
     * @throws IllegalArgumentException if source or destination is out of bounds.
     */
    public void addEdge(int source, int destination, int weight){
        if(source < 0 || source >= numVertices || destination < 0 || destination >= numVertices) {
            throw new IllegalArgumentException("Source or destination vertex is out of bounds");
        }
        if(weight < distanceMatrix[source][destination]) {
            distanceMatrix[source][destination] = weight;
        }
    }

    /**
     * Executes the Floyd-Warshall algorithm to compute the shortest paths between
     * all pairs of vertices.
     *
     * @return a 2D array representing the shortest distances between all pairs
     * of vertices. {@code INF} indicates no path, and a negative value on the
     * main diagonal indicates a negative cycle.
     */
    public int[][] floydWarshall(){
        int[][] dist = new int[numVertices][numVertices];
        // Initialize the distance matrix with the current graph edges
        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < numVertices; j++) {
                dist[i][j] = distanceMatrix[i][j];
            }
        }

        // Floyd-Warshall algorithm
        for(int k = 0; k < numVertices; k++) {
            for(int i = 0; i < numVertices; i++) {
                for(int j = 0; j < numVertices; j++) {
                    if(dist[i][k] != INF && dist[k][j] != INF &&
                    dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Check for negative cycles on the main diagonal
        for(int i = 0; i < numVertices; i++) {
            if(dist[i][i] < 0) {
                System.out.println("Negative cycle detected involving vertex " + i);
                return null; // Negative cycle detected
            }
        }

        return dist;
    }

    /**
     * Prints the shortest distance matrix.
     *
     * @param distance the 2D array representing the shortest distances.
     */
    public void printSolution(int[][] distance) {
        System.out.println("Shortest distances between all pairs of vertices:");
        for (int i = 0; i < numVertices; ++i) {
            for (int j = 0; j < numVertices; ++j) {
                if (distance[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distance[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    /**
     * The main method demonstrates the usage of the {@code FloydWarshall} class.
     * It creates a sample weighted directed graph and finds the shortest paths
     * between all pairs of vertices.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 4;
        FloydWarshall graph = new FloydWarshall(numVertices);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(3, 1, 1);
        graph.addEdge(2, 3, 1);

        int[][] shortestDistances = graph.floydWarshall();

        if (shortestDistances != null) {
            graph.printSolution(shortestDistances);
        } else {
            System.out.println("Graph contains a negative cycle.");
        }

        System.out.println("\nExample with a negative cycle:");
        FloydWarshall graphNegativeCycle = new FloydWarshall(3);
        graphNegativeCycle.addEdge(0, 1, -1);
        graphNegativeCycle.addEdge(1, 2, -2);
        graphNegativeCycle.addEdge(2, 0, -3);

        int[][] shortestDistancesNegativeCycle = graphNegativeCycle.floydWarshall();
        if (shortestDistancesNegativeCycle != null) {
            graphNegativeCycle.printSolution(shortestDistancesNegativeCycle);
        } else {
            System.out.println("Graph contains a negative cycle.");
        }
    }
}
