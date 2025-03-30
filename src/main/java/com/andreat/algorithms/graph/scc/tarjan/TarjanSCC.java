package com.andreat.algorithms.graph.scc.tarjan;

import java.util.*;

/**
 * The {@code TarjanSCC} class implements Tarjan's algorithm to find the
 * strongly connected components (SCCs) of a directed graph.
 */
public class TarjanSCC {

    private int numVertices;
    private Map<Integer, List<Integer>> adjacencyList;
    private int[] discoveryTime;
    private int[] lowLink;
    private boolean[] onStack;
    private Stack<Integer> stack;
    private int time;

    /**
     * Constructs a {@code TarjanSCC} object with a specified number of vertices.
     * The directed graph is represented using an adjacency list.
     *
     * @param numVertices the total number of vertices in the graph.
     */
    public TarjanSCC(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        discoveryTime = new int[numVertices];
        lowLink = new int[numVertices];
        onStack = new boolean[numVertices];
        stack = new Stack<>();
        time = 0;
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
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    /**
     * Finds and prints the strongly connected components (SCCs) of the graph
     * using Tarjan's algorithm.
     */
    public void findSCCs() {
        for (int i = 0; i < numVertices; i++) {
            if (discoveryTime[i] == 0) {
                tarjanSCCUtil(i);
            }
        }
    }

    /**
     * Utility function for Tarjan's algorithm. Performs DFS and maintains
     * discovery times and low-link values to find SCCs.
     *
     * @param u the current vertex being visited.
     */
    private void tarjanSCCUtil(int u) {
        discoveryTime[u] = lowLink[u] = ++time;
        stack.push(u);
        onStack[u] = true;

        if (adjacencyList.containsKey(u)) {
            for (int v : adjacencyList.get(u)) {
                if (discoveryTime[v] == 0) {
                    tarjanSCCUtil(v);
                    lowLink[u] = Math.min(lowLink[u], lowLink[v]);
                } else if (onStack[v]) {
                    lowLink[u] = Math.min(lowLink[u], discoveryTime[v]);
                }
            }
        }

        // If the discovery time and low-link value of a vertex are the same,
        // it is the root of an SCC. Pop all elements from the stack until this
        // vertex is reached.
        if (lowLink[u] == discoveryTime[u]) {
            System.out.print("SCC: ");
            int w;
            do {
                w = stack.pop();
                onStack[w] = false;
                System.out.print(w + " ");
            } while (w != u);
            System.out.println();
        }
    }

    /**
     * The main method demonstrates the usage of the {@code TarjanSCC} class.
     * It creates a sample directed graph and finds its strongly connected components.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int numVertices = 5;
        TarjanSCC graph = new TarjanSCC(numVertices);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        System.out.println("Strongly Connected Components using Tarjan's Algorithm:");
        graph.findSCCs();

        System.out.println("\nAnother example:");
        int numVertices2 = 8;
        TarjanSCC graph2 = new TarjanSCC(numVertices2);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 0);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        graph2.addEdge(4, 3);
        graph2.addEdge(4, 5);
        graph2.addEdge(5, 6);
        graph2.addEdge(6, 5);
        graph2.addEdge(6, 7);
        graph2.addEdge(7, 7);

        System.out.println("Strongly Connected Components using Tarjan's Algorithm:");
        graph2.findSCCs();
    }
}
