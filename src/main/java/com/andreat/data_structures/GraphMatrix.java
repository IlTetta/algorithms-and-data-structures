package com.andreat.data_structures;

import java.util.Queue;
import java.util.LinkedList;

public class GraphMatrix {
    private final int vertices;
    private final int[][] adjMatrix;

    public GraphMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int source, int destination) {
        if(source >= 0 && source < vertices && destination >= 0 && destination < vertices) {
            adjMatrix[source][destination] = 1;
        }
    }

    public void addEdgeUndirected(int source, int destination) {
        addEdge(source, destination);
        addEdge(destination, source);
    }

    public void addWeitghtedEdge(int source, int destination, int weight) {
        if(source >= 0 && source < vertices && destination >= 0 && destination < vertices) {
            adjMatrix[source][destination] = weight;
        }
    }

    public boolean isAdjacent(int source, int destination){
        if(source >= 0 && source < vertices && destination >= 0 && destination < vertices) {
            return adjMatrix[source][destination] != 0;
        }
        return false;
    }

    public void printMatrix(){
        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int startVertex){
        boolean[] visited = new boolean[vertices];
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for(int i = 0; i < vertices; i++) {
            if(adjMatrix[vertex][i] != 0 && !visited[i]) {
                dfsRecursive(i, visited);
            }
        }
    }

    public void bfs(int startVertex){
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for(int i = 0; i < vertices; i++) {
                if(adjMatrix[vertex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
