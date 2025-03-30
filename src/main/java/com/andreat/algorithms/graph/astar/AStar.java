package com.andreat.algorithms.graph.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The {@code AStar} class implements the A* search algorithm to find the shortest
 * path between a start and a goal node in a graph. It uses a heuristic function
 * to estimate the cost to the goal. This implementation is tailored for a
 * grid-based graph where nodes can be represented by their coordinates (row, col).
 */
public class AStar {

    /**
     * Represents a node in the grid.
     */
    private static class Node implements Comparable<Node> {
        int row;
        int col;
        int gScore; // Cost from start node
        int hScore; // Heuristic estimate to goal node
        int fScore; // Total cost: gScore + hScore
        Node parent;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            this.gScore = Integer.MAX_VALUE;
            this.hScore = 0;
            this.fScore = Integer.MAX_VALUE;
            this.parent = null;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node other = (Node) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(row, col);
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fScore, other.fScore);
        }
    }

    private int rows;
    private int cols;
    private int[][] grid; // 0: walkable, 1: obstacle

    /**
     * Constructs an {@code AStar} object for a given grid.
     *
     * @param grid a 2D integer array representing the grid. 0 for walkable cells,
     * 1 for obstacles.
     */
    public AStar(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    /**
     * Calculates the Manhattan distance heuristic between two nodes.
     *
     * @param node1 the first node.
     * @param node2 the second node.
     * @return the Manhattan distance between the two nodes.
     */
    private int heuristic(Node node1, Node node2) {
        return Math.abs(node1.row - node2.row) + Math.abs(node1.col - node2.col);
    }

    /**
     * Gets the neighbors of a given node in the grid.
     *
     * @param node the node to find neighbors for.
     * @return a list of valid neighbor nodes.
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] dir : directions) {
            int newRow = node.row + dir[0];
            int newCol = node.col + dir[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 0) {
                neighbors.add(new Node(newRow, newCol));
            }
        }
        return neighbors;
    }

    /**
     * Implements the A* search algorithm to find the shortest path between a
     * start and a goal node.
     *
     * @param startRow the row of the starting node.
     * @param startCol the column of the starting node.
     * @param goalRow  the row of the goal node.
     * @param goalCol  the column of the goal node.
     * @return a list of nodes representing the shortest path from start to goal,
     * or an empty list if no path is found.
     */
    public List<Node> findPath(int startRow, int startCol, int goalRow, int goalCol) {
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols ||
                goalRow < 0 || goalRow >= rows || goalCol < 0 || goalCol >= cols ||
                grid[startRow][startCol] == 1 || grid[goalRow][goalCol] == 1) {
            return new ArrayList<>(); // Invalid start or goal
        }

        Node startNode = new Node(startRow, startCol);
        Node goalNode = new Node(goalRow, goalCol);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        openSet.offer(startNode);
        startNode.gScore = 0;
        startNode.hScore = heuristic(startNode, goalNode);
        startNode.fScore = startNode.hScore;

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (closedSet.contains(current)) {
                continue; // Ignore already processed nodes
            }

            if (current.equals(goalNode)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = current.gScore + 1; // Assuming cost of 1 for each step

                if (tentativeGScore < neighbor.gScore) {
                    neighbor.parent = current;
                    neighbor.gScore = tentativeGScore;
                    neighbor.hScore = heuristic(neighbor, goalNode);
                    neighbor.fScore = neighbor.gScore + neighbor.hScore;
                    openSet.offer(neighbor); // Add or update priority in openSet
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

    /**
     * Reconstructs the path from the goal node back to the start node.
     *
     * @param current the goal node.
     * @return a list of nodes representing the path in reverse order (from goal to start).
     */
    private List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        java.util.Collections.reverse(path);
        return path;
    }

    /**
     * The main method demonstrates the usage of the {@code AStar} class.
     * It creates a sample grid and finds the shortest path from a start to a goal.
     *
     * @param args command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        AStar aStar = new AStar(grid);
        List<Node> path = aStar.findPath(0, 0, 9, 9);

        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.row + ", " + node.col + ")");
            }
        }
    }
}