package com.andreat.algorithms.graph.scc.kosaraju;

import java.util.*;

/**
 * The {@code KosarajuSCC} class implements Kosaraju's algorithm to find the
 * strongly connected components (SCCs) of a directed graph.
 */
public class KosarajuSCC {

    private int numVertices;
    private Map<Integer, List<Integer>> adjacencyList;

    /**
     * Constructs a {@code KosarajuSCC} object with a specified number of vertices.
     * The directed graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public KosarajuSCC(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new java.util.HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new java.util.ArrayList<>());
        }
    }

    /**
     * Adds a directed edge to the graph.
     *
     * @param u the starting vertex of the edge.
     * @param v the ending vertex of the edge.
     */
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    /**
     * Gets the transpose of the graph. The transpose graph has the same vertices
     * as the original graph, but all the edges are reversed.
     *
     * @return a map representing the transpose graph's adjacency list.
     */
    private Map<Integer, List<Integer>> getTranspose(){
        Map<Integer, List<Integer>> transpose = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            transpose.put(i, new ArrayList<>());
        }
        for (int v = 0; v < numVertices; v++) {
            if(adjacencyList.containsKey(v)){
                for (int u : adjacencyList.get(v)) {
                    transpose.get(u).add(v);
                }
            }
        }
        return transpose;
    }

    /**
     * Performs Depth First Search (DFS) starting from a given vertex and adds
     * visited vertices to a stack in the order they are finished.
     *
     * @param v       the starting vertex for DFS.
     * @param visited an array to mark visited vertices.
     * @param stack   the stack to store vertices in finish order.
     */
    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        if(adjacencyList.containsKey(v)){
            for (int neighbor : adjacencyList.get(v)) {
                if (!visited[neighbor]) {
                    fillOrder(neighbor, visited, stack);
                }
            }
        }
        stack.push(v);
    }

    /**
     * Performs DFS starting from a given vertex and prints all reachable vertices
     * (which form a strongly connected component).
     *
     * @param v           the starting vertex for DFS.
     * @param visited     an array to mark visited vertices.
     * @param transposeGraph the adjacency list of the transpose graph.
     */
    private void DFSUtil(int v, boolean[] visited, Map<Integer, List<Integer>> transposeGraph){
        visited[v] = true;
        System.out.println(v + " ");
        if(transposeGraph.containsKey(v)){
            for (int neighbor : transposeGraph.get(v)) {
                if (!visited[neighbor]) {
                    DFSUtil(neighbor, visited, transposeGraph);
                }
            }
        }
    }

    /**
     * Finds and prints the strongly connected components (SCCs) of the graph
     * using Kosaraju's algorithm.
     */
    public void findSCCs(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numVertices];

        // Step 1: Fill vertices in stack according to their finishing times in DFS
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // Step 2: Get the transpose of the graph
        Map<Integer, List<Integer>> transposeGraph = getTranspose();

        /* Step 3: Perform DFS on the transpose graph. For every vertex
         * in the stack, if it hasn't been visited, start a DFS from it. This DFS
         * will print the SCC containing that vertex.
        */
        Arrays.fill(visited, false);
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                DFSUtil(v, visited, transposeGraph);
                System.out.println(); // Print a new line after each SCC
            }
        }
    }

    /**
     * The main method demonstrates the usage of the {@code KosarajuSCC} class.
     * It creates a sample directed graph and finds its strongly connected components.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 5;
        KosarajuSCC graph = new KosarajuSCC(numVertices);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.findSCCs();
    }
}
