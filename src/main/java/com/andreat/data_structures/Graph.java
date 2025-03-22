package com.andreat.data_structures;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph<T>{
    private final Map<T, List<T>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjList.put(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination){
        if(!adjList.containsKey(source)) {
            addVertex(source);
        }
        if(!adjList.containsKey(destination)) {
            addVertex(destination);
        }
        adjList.get(source).add(destination);
    }

    public void addEdgeUndirected(T source, T destination){
        addEdge(source, destination);
        addEdge(destination, source);
    }

    public List<T> getAdjacent(T vertex){
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<T> getVertices(){
        return adjList.keySet();
    }

    public void printGraph(){
        for(T vertex : adjList.keySet()){
            System.out.print(vertex + " -> ");
            for(T neighbor : adjList.get(vertex)){
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void dfs(T startVertex){
        Set<T> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(T startVertex, Set<T> visited){
        visited.add(startVertex);
        System.out.print(startVertex + " ");

        for(T adjacent : adjList.get(startVertex)){
            if(!visited.contains(adjacent)){
                dfsRecursive(adjacent, visited);
            }
        }
    }

    public void bfs(T startVertex){
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while(!queue.isEmpty()){
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for(T adjacent : adjList.get(vertex)){
                if(!visited.contains(adjacent)){
                    visited.add(adjacent);
                    queue.add(adjacent);
                }
            }
        }
    }
}
