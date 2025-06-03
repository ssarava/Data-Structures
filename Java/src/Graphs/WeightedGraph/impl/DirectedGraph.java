package Graphs.WeightedGraph.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
public class DirectedGraph {

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(10);
        for (int index = 0; index < 10; index ++) {
            graph.add(index, (index + 1) % 10, (index * 9) % 5 + 3);
        }

        // graph.add(2, 3, 9);
        System.out.println(graph.toString());
        graph.bfs(9);

    }

    public static final int DEFAULT_SIZE = 5;
    private int[][] adjMat;
    protected int size;

    public DirectedGraph(int sizeIn) {
        adjMat = new int[sizeIn][sizeIn];
    }

    public DirectedGraph() {
        this(DEFAULT_SIZE);
    }

    public void add(int from, int to, int edgeWeight) {
        if (from >= adjMat.length || to >= adjMat.length) {
            throw new UnsupportedOperationException("At least one of " + from + " and " + to + " doesn't exist in the graph! Can't add an edge betweeen them");
        }
        adjMat[from][to] = edgeWeight;
    }

    public void bfs(int vertex) {
        StringBuilder sb = new StringBuilder("");
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(vertex);
        int counter = 0;

        while (!queue.isEmpty() && counter < 20) {
            int curr = queue.remove();
            sb.append(curr + " ");
            System.out.print("curr = " + curr + "\t");
            printArr(adjMat[curr]);
            for (int neighbor = 0; neighbor < adjMat[curr].length; neighbor ++) {
                int neighb = adjMat[vertex][neighbor];
                if (neighb != 0 && !visited.contains(neighb)) {
                    System.out.println("\tadding " + neighb + "\t");
                    visited.add(neighb);
                    // System.out.println(adjMat[curr][neighbor]);
                    queue.add(neighbor);
                }
            }
            counter ++;
        }
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("   ");
        for (int index = 0; index < adjMat.length; index ++) {
            sb.append(index + " ");
        }
        sb.append("\n");
        for (int row = 0; row < adjMat.length; row ++) {
            sb.append(row + ": ");
            for (int col = 0; col < adjMat.length; col ++) {
                sb.append(adjMat[row][col] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();        
    }

    public void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i: arr) {
            sb.append(i + " ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

}
