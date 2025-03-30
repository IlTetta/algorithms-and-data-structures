package com.andreat.algorithms.graph.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code KruskalMST} class implements Kruskal's algorithm to find the
 * Minimum Spanning Tree (MST) of a weighted undirected graph.
 */
public class KruskalMST {

    /**
     * Represents an edge in the graph with its source, destination, and weight.
     */
    private static class Edge{
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private List<Edge> edges;
    private int numVertices;

    /**
     * Constructs a {@code KruskalMST} object with a specified number of vertices.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public KruskalMST(int numVertices){
        this.numVertices = numVertices;
        edges = new ArrayList<>();
    }

    /**
     * Adds a weighted edge to the graph. Since the graph is undirected for MST,
     * the order of source and destination does not matter.
     *
     * @param source      the source vertex of the edge.
     * @param destination the destination vertex of the edge.
     * @param weight      the weight of the edge. Must be non-negative.
     */
    public void addEdge(int source, int destination, int weight){
        if(weight < 0){
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        edges.add(new Edge(source, destination, weight));
    }

    /**
     * Finds the Minimum Spanning Tree (MST) of the graph using Kruskal's algorithm.
     *
     * @return a list of edges that form the MST, or an empty list if the graph
     * is not connected.
     */
    public List<Edge> findMST(){
        List<Edge> mst = new ArrayList<>();
        // Sort edges in non-decreasing order of weight
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Initialize parent array for Disjoint Set Union (DSU)
        int[] parent = new int[numVertices];
        for(int i = 0; i < numVertices; i++){
            parent[i] = i;
        }

        int edgesInMST = 0;
        for(Edge edge : edges){
            int rootSource = find(parent, edge.source);
            int rootDestination = find(parent, edge.destination);

            // If including this edge does not create a cycle
            if(rootSource != rootDestination){
                mst.add(edge);
                union(parent, rootSource, rootDestination);
                edgesInMST++;

                // If we have included (V-1) edges, then we have found the MST
                if(edgesInMST == numVertices - 1){
                    break;
                }
            }
        }

        // Check if the graph was connected (MST should have V-1 edges)
        if(edgesInMST < numVertices - 1 && numVertices > 0){
            System.out.println("Warning: Graph might not be connected.");
            return new ArrayList<>(); // Return empty list if not connected
        }
        return mst;
    }

    /**
     * Finds the representative (root) of the set that an element belongs to
     * using path compression.
     *
     * @param parent the parent array for DSU.
     * @param i      the element to find the root of.
     * @return the root of the set containing the element.
     */
    private int find(int[] parent, int i){
        if(parent[i] == i){
            return i;
        }
        return parent[i] = find(parent, parent[i]); // Path compression
    }

    /**
     * Performs the union of two sets represented by their representatives
     * (roots) using union by rank (although rank is not explicitly tracked here,
     * a simple union by setting parent is done).
     *
     * @param parent the parent array for DSU.
     * @param x      the root of the first set.
     * @param y      the root of the second set.
     */
    private void union(int[] parent, int x, int y){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX != rootY){
            parent[rootX] = rootY;
        }
    }

    /**
     * The main method demonstrates the usage of the {@code KruskalMST} class.
     * It creates a sample weighted undirected graph and finds its MST.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 7;
        KruskalMST graph = new KruskalMST(numVertices);
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 8);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 5, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 2);
        graph.addEdge(5, 6, 6);

        List<Edge> mstEdges = graph.findMST();

        System.out.println("Edges in the MST:");
        int mstWeight = 0;
        for (Edge edge : mstEdges) {
            System.out.println("(" + edge.source + " -- " + edge.destination + ") weight: " + edge.weight);
            mstWeight += edge.weight;
        }
        System.out.println("Total weight of MST: " + mstWeight);
    }
}
