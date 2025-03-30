package com.andreat.algorithms.graph.networkflow.fordfulkerson;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

/**
 * The {@code FordFulkerson} class implements the Edmonds-Karp variant of the
 * Ford-Fulkerson algorithm to find the maximum flow in a flow network.
 */
public class FordFulkerson {

    private int numVertices;
    private int[][] capacity;

    /**
     * Constructs a {@code FordFulkerson} object with a specified number of vertices
     * in the flow network.
     *
     * @param numVertices the number of vertices in the network.
     */
    public FordFulkerson(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
    }

    /**
     * Adds an edge to the flow network with a given capacity. This represents
     * the maximum flow that can pass from the source vertex to the sink vertex
     * along this edge.
     *
     * @param source    the source vertex of the edge.
     * @param sink      the sink vertex of the edge.
     * @param flowCapacity the capacity of the edge (must be non-negative).
     * @throws IllegalArgumentException if source or sink is out of bounds or if
     * flowCapacity is negative.
     */
    public void addEdge(int source, int sink, int flowCapacity){
        if(source < 0 || source >= numVertices || sink < 0 || sink >= numVertices) {
            throw new IllegalArgumentException("Source or sink vertex out of bounds");
        }
        if(flowCapacity < 0) {
            throw new IllegalArgumentException("Flow capacity cannot be negative");
        }
        capacity[source][sink] = flowCapacity;
    }

    /**
     * Finds the maximum flow in the flow network from a source vertex to a sink
     * vertex using the Edmonds-Karp algorithm (Ford-Fulkerson with BFS for
     * finding augmenting paths).
     *
     * @param source the source vertex of the flow.
     * @param sink   the sink vertex of the flow.
     * @return the maximum flow from the source to the sink.
     * @throws IllegalArgumentException if source or sink is out of bounds.
     */
    public int fordFulkerson(int source, int sink){
        if (source < 0 || source >= numVertices || sink < 0 || sink >= numVertices) {
            throw new IllegalArgumentException("Source or sink vertex is out of bounds.");
        }
        if (source == sink) {
            throw new IllegalArgumentException("Source and sink cannot be the same.");
        }

        int maxFlow = 0;
        int[][] residualCapacity = new int[numVertices][numVertices];
        for (int u = 0; u < numVertices; u++) {
            for (int v = 0; v < numVertices; v++) {
                residualCapacity[u][v] = capacity[u][v];
            }
        }

        int[] parent = new int[numVertices];
        int pathFlow;

        while ((pathFlow = findAugmentingPath(residualCapacity, source, sink, parent))>0){
            maxFlow += pathFlow;

            // Update residual capacities of the edges and reverse edges along the path
            int v = sink;
            while (v != source) {
                int u = parent[v];
                residualCapacity[u][v] -= pathFlow;
                residualCapacity[v][u] += pathFlow;
                v = u;
            }
        }

        return maxFlow;
    }

    /**
     * Finds an augmenting path in the residual graph using Breadth-First Search (BFS).
     *
     * @param residualCapacity the residual capacity of the flow network.
     * @param source           the source vertex.
     * @param sink             the sink vertex.
     * @param parent           an array to store the parent of each vertex in the found path.
     * @return the bottleneck capacity (minimum residual capacity) along the path,
     * or 0 if no augmenting path is found.
     */
    private int findAugmentingPath(int[][] residualCapacity, int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        parent[source] = source;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while(!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < numVertices; v++) {
                if (parent[v] == -1 && residualCapacity[u][v] > 0) {
                    parent[v] = u;
                    queue.offer(v);
                    if (v == sink) {
                        // Found a path, now find the bottleneck capacity
                        int pathFlow = Integer.MAX_VALUE;
                        for (int i = sink; i != source; i = parent[i]) {
                            int j = parent[i];
                            pathFlow = Math.min(pathFlow, residualCapacity[j][i]);
                        }
                        return pathFlow;
                    }
                }
            }
        }

        return 0; // No augmenting path found
    }

    /**
     * The main method demonstrates the usage of the {@code FordFulkerson} class.
     * It creates a sample flow network and finds the maximum flow from a source
     * to a sink.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 6;
        FordFulkerson graph = new FordFulkerson(numVertices);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 10);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 8);
        graph.addEdge(2, 4, 9);
        graph.addEdge(2, 0, 6);
        graph.addEdge(3, 5, 10);
        graph.addEdge(4, 3, 6);
        graph.addEdge(4, 5, 10);

        int source = 0;
        int sink = 5;
        int maxFlow = graph.fordFulkerson(source, sink);

        System.out.println("The maximum flow from source " + source + " to sink " + sink + " is: " + maxFlow);
    }
}