package com.andreat.algorithms.graph.mst.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * The {@code PrimMST} class implements Prim's algorithm to find the
 * Minimum Spanning Tree (MST) of a weighted undirected graph.
 */
public class PrimMST {

    /**
     * Represents a node in the graph with its neighbor and the weight of the edge
     * connecting them.
     */
    private static class Node{
        int neighbor;
        int weight;

        public Node(int neighbor, int weight){
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    private int numVertices;
    private Map<Integer, List<Node>> adjacencyList;

    /**
     * Constructs a {@code PrimMST} object with a specified number of vertices.
     * The weighted graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public PrimMST(int numVertices){
        this.numVertices = numVertices;
        adjacencyList = new HashMap<>();
        for(int i = 0; i < numVertices; i++){
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds a weighted edge to the graph. Since the graph is undirected for MST,
     * the edge is added to the adjacency list of both vertices.
     *
     * @param u      the first vertex of the edge.
     * @param v      the second vertex of the edge.
     * @param weight the weight of the edge. Must be non-negative.
     */
    public void addEdge(int u, int v, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        adjacencyList.get(u).add(new Node(v, weight));
        adjacencyList.get(v).add(new Node(u, weight));
    }

    /**
     * Finds the Minimum Spanning Tree (MST) of the graph using Prim's algorithm.
     *
     * @return a list of edges that form the MST. Each element in the list is an
     * array of three integers: {u, v, weight}, representing an edge between
     * vertex u and vertex v with the given weight. Returns an empty list if
     * the graph is empty or not connected.
     */
    public List<int[]> findMST(){
        if(numVertices == 0){
            return new ArrayList<>();
        }

        List<int[]> mstEdges = new ArrayList<>();
        boolean[] inMST = new boolean[numVertices];
        int[] parent = new int[numVertices];
        int[] key = new int[numVertices];
        Arrays.fill(key, Integer.MAX_VALUE);

        // Start with the first vertex
        key[0] = 0;
        parent[0] = -1; // Root of MST

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((v1, v2) -> key[v1] - key[v2]);
        for(int i = 0; i < numVertices; i++){
            minHeap.offer(i);
        }

        for(int i = 0; i < numVertices - 1; i++){
            if(minHeap.isEmpty()){
                System.out.println("Warning: Graph might not be connected");
                return new ArrayList<>();
            }

            int u = minHeap.poll();
            inMST[u] = true;

            if(adjacencyList.containsKey(u)){
                for(Node neighborNode : adjacencyList.get(u)){
                    int v = neighborNode.neighbor;
                    int weight = neighborNode.weight;
                    if(!inMST[v] && weight < key[v]){
                        parent[v] = u;
                        key[v] = weight;
                        minHeap.remove(v);
                        minHeap.offer(v);
                    }
                }
            }
        }

        // Construct the MST edges from the parent array
        for(int i = 1; i < numVertices; i++){
            mstEdges.add(new int[]{parent[i],i, key[i]});
        }

        return mstEdges;
    }

    /**
     * The main method demonstrates the usage of the {@code PrimMST} class.
     * It creates a sample weighted undirected graph and finds its MST.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 7;
        PrimMST graph = new PrimMST(numVertices);
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

        List<int[]> mstEdges = graph.findMST();

        System.out.println("Edges in the MST:");
        int mstWeight = 0;
        for (int[] edge : mstEdges) {
            System.out.println("(" + edge[0] + " -- " + edge[1] + ") weight: " + edge[2]);
            mstWeight += edge[2];
        }
        System.out.println("Total weight of MST: " + mstWeight);
    }

}
