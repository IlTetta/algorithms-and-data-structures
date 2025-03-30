package com.andreat.algorithms.graph.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * The {@code TopologicalSort} class implements the topological sorting algorithm
 * for a Directed Acyclic Graph (DAG). It produces a linear ordering of vertices
 * such that for every directed edge from vertex u to vertex v, vertex u comes
 * before v in the ordering.
 */
public class TopologicalSort {

    private Map<Integer, List<Integer>> adjacencyList;
    private int numVertices;

    /**
     * Constructs a {@code TopologicalSort} object with a specified number of vertices.
     * The directed graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public TopologicalSort(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge to the graph.
     *
     * @param u the starting vertex of the edge.
     * @param v the ending vertex of the edge.
     */
    public void addEdge(int u, int v){
        adjacencyList.get(u).add(v);
    }

    /**
     * Performs topological sort on the graph.
     *
     * @return a list of vertices in topologically sorted order. Returns an empty
     * list if the graph contains a cycle (and thus cannot be topologically sorted).
     */
    public List<Integer> topologicalSort(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numVertices];

        for(int i = 0; i < numVertices; i++){
            if(!visited[i]){
                if(isCyclicUtil(i, visited, new boolean[numVertices], stack)){
                    System.out.println("Graph contains a cycle. Topological sort not possible.");
                    return new ArrayList<>(); // Return an empty list to indicate cycle
                }
            }
        }

        List<Integer> sortedList = new ArrayList<>();
        while(!stack.isEmpty()){
            sortedList.add(stack.pop());
        }

        return sortedList;
    }

    /**
     * Utility function to detect cycles in the graph using DFS. Also populates
     * the stack with vertices in reverse topological order.
     *
     * @param v       the current vertex being visited.
     * @param visited an array to mark visited vertices.
     * @param recursionStack an array to track vertices currently in the recursion stack
     * (to detect cycles).
     * @param stack   the stack to store the topologically sorted vertices.
     * @return true if a cycle is found, false otherwise.
     */
    private boolean isCyclicUtil(int v, boolean[] visited, boolean[] recursionStack, Stack<Integer> stack){
        visited[v] = true;
        recursionStack[v] = true;

        if(adjacencyList.containsKey(v)){
            for(int neighbor : adjacencyList.get(v)){
                if(!visited[neighbor]){
                    if(isCyclicUtil(neighbor, visited, recursionStack, stack)){
                        return true;
                    }
                } else if(recursionStack[neighbor]){
                    return true; // Cycle detected
                }
            }
        }

        recursionStack[v] = false; // Remove vertex from recursion stack after processing
        stack.push(v);
        return false;
    }

    /**
     * The main method demonstrates the usage of the {@code TopologicalSort} class.
     * It creates a sample DAG and performs topological sort on it.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 6;
        TopologicalSort graph = new TopologicalSort(numVertices);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Topological Sort of the given graph:");
        List<Integer> result = graph.topologicalSort();
        if (!result.isEmpty()) {
            for (int node : result) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

        System.out.println("\nExample with a cycle:");
        TopologicalSort graphWithCycle = new TopologicalSort(3);
        graphWithCycle.addEdge(0, 1);
        graphWithCycle.addEdge(1, 2);
        graphWithCycle.addEdge(2, 0);
        List<Integer> resultCycle = graphWithCycle.topologicalSort();
        if (!resultCycle.isEmpty()) {
            for (int node : resultCycle) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}


