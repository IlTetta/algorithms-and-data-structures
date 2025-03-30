package com.andreat.algorithms.graph.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * The {@code Dijkstra} class implements Dijkstra's algorithm to find the shortest
 * path from a single node to all other nodes in a weighted graph.
 * Each weights must be non-negative.
 */
public class Dijkstra {

    private Map<Integer, List<Node>> adjacencyList;
    private int numVertices;

    /**
     * Represents a node in the graph with its neighbor and weight of the edge
     * connecting them.
     */
    private static class Node{
        int neighbor;
        int weight;

        public Node (int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    /**
     * Constructs a {@code Dijkstra} object with a specified number of vertices.
     * The weighted graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public Dijkstra(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds a weighted edge to the graph.
     *
     * @param u      the starting vertex of the edge.
     * @param v      the ending vertex of the edge.
     * @param weight the weight of the edge. Must be non-negative.
     */
    public void addEdge(int u, int v, int weight){
        if(weight < 0){
            throw new IllegalArgumentException("Edge weight cannot be negative.");
        }
        adjacencyList.get(u).add(new Node(v, weight));
    }

    /**
     * Finds the shortest paths from the given source node to all other nodes
     * in the graph using Dijkstra's algorithm.
     *
     * @param startNode the source vertex from which to find the shortest paths.
     * @return an array of integers where the i-th element represents the shortest
     * distance from the start node to vertex i. If a vertex is not
     * reachable, its distance will be {@code Integer.MAX_VALUE}.
     * @throws IllegalArgumentException if the startNode is invalid.
     */
    public int[] dijkstra(int startNode){
        if(startNode < 0 || startNode >= numVertices){
            throw new IllegalArgumentException("Invalid start node.");
        }

        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((u, v) -> distances[u] - distances[v]);
        minHeap.offer(startNode);

        while(!minHeap.isEmpty()){
            int u = minHeap.poll();

            if(distances[u] == Integer.MAX_VALUE){
                continue;
            }
             if(adjacencyList.containsKey(u)){
                 for(Node neighborNode : adjacencyList.get(u)){
                     int v = neighborNode.neighbor;
                     int weight = neighborNode.weight;
                     int newDistance = distances[u] + weight;

                     if(newDistance < distances[v]){
                            distances[v] = newDistance;
                            minHeap.offer(v);
                     }
                 }
             }
        }

        return distances;
    }

    /**
     * The main method demonstrates the usage of the {@code Dijkstra} class.
     * It creates a sample weighted graph and finds the shortest paths from node 0
     * to all other nodes.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 6;
        Dijkstra graph = new Dijkstra(numVertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 5, 3);

        int startNode = 0;
        int[] shortestDistances = graph.dijkstra(startNode);

        System.out.println("Shortest distances from node " + startNode + ":");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("To node " + i + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinity" : shortestDistances[i]));
        }
    }
}
