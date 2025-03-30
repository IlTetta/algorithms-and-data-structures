package com.andreat.algorithms.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * The {@code BFS} class implements the Breadth-First Search (BFS) algorithm
 * for traversing a graph. It provides methods to build a graph and perform BFS
 * starting from a specified node.
 */
public class BFS {

    private Map<Integer, List<Integer>> adjacencyList;

    /**
     * Constructs a {@code BFS} object with a specified number of vertices.
     * The graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public BFS(int numVertices){
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the graph. For an undirected graph, this method should
     * be called twice with the order of vertices reversed.
     *
     * @param u the starting vertex of the edge.
     * @param v the ending vertex of the edge.
     */
    public void addEdge (int u, int v){
        adjacencyList.get(u).add(v);
    }

    /**
     * Performs Breadth-First Search (BFS) starting from the given node.
     * It visits all the reachable vertices level by level.
     *
     * @param startNode the vertex from which to start the BFS traversal.
     */
    public void breadthFirstSearch(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startNode);
        queue.offer(startNode);

        while(!queue.isEmpty()){
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int neighbor : adjacencyList.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * The main method demonstrates the usage of the {@code BFS} class.
     * It creates a sample graph and performs BFS starting from node 0.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Example usage
        BFS graph = new BFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("BFS starting from node 0:");
        graph.breadthFirstSearch(0); // Output: 0 1 2 3 4 5
    }
}
