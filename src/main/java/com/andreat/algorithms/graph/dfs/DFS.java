package com.andreat.algorithms.graph.dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

/**
 * The {@code DFS} class implements the Depth First Search (DFS) algorithm
 * for traversing a graph. It provides methods to build a graph and perform DFS
 * starting from a specified node. This implementation uses a recursive approach.
 */
public class DFS {

    private Map<Integer, List<Integer>> adjacencyList;
    private Set<Integer> visited;

    /**
     * Constructs a {@code DFS} object with a specified number of vertices.
     * The graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public DFS(int numVertices){
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
        visited = new HashSet<>();
    }

    /**
     * Adds an edge to the graph. For an undirected graph, this method should
     * be called twice with the order of vertices reversed.
     *
     * @param u the starting vertex of the edge.
     * @param v the ending vertex of the edge.
     */
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    /**
     * Performs Depth First Search (DFS) starting from the given node using recursion.
     * It explores as far as possible along each branch before backtracking.
     *
     * @param startNode the vertex from which to start the DFS traversal.
     */
    public void depthFirstSearchRecursive(int startNode){
        visited.clear(); // Reset visited set for each DFS traversal
        dfsRecursive(startNode);
        System.out.println();
    }

    private void dfsRecursive(int node){
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : adjacencyList.get(node)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor);
            }
        }
    }

    /**
     * Performs Depth First Search (DFS) starting from the given node using an iterative approach with a stack.
     * It explores as far as possible along each branch before backtracking.
     *
     * @param startNode the vertex from which to start the DFS traversal.
     */
    public void depthFirstSearchIterative(int startNode){
        visited.clear(); // Reset visited set for each DFS traversal
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);
        visited.add(startNode);
        System.out.print(startNode + " ");

        while (!stack.isEmpty()) {
            int currentNode= stack.pop();

            for (int neighbor : adjacencyList.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                    System.out.print(neighbor + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * The main method demonstrates the usage of the {@code DFS} class.
     * It creates a sample graph and performs both recursive and iterative DFS
     * starting from node 0.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Example usage
        DFS graph = new DFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("DFS (Recursive) starting from node 0:");
        graph.depthFirstSearchRecursive(0);

        System.out.println("\nDFS (Iterative) starting from node 0:");
        graph.depthFirstSearchIterative(0);
    }



}
