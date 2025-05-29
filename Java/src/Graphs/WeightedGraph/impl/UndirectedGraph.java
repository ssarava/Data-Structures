package Graphs.WeightedGraph.impl;

import java.util.HashMap;
import java.util.Map;

public class UndirectedGraph<T> {

    public static void main(String[] args) {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        System.out.println(graph);
        for (int index = 0; index < 10; index ++) {
            graph.addVertex("hello" + index);
        }
        for (int index = 0; index < 9; index ++) {
            graph.addEdge("hello" + index, "hello" + (index + 1), (index * 2 + 1) % 10);
        }
        System.out.println(graph);
        graph.printIDs();
    }
    
    private static class Vertex<T> {
        private T val;
        private int id;
        private static int numIDs = 0;

        private Vertex(T valIn) {
            val = valIn;
            id = numIDs;
            numIDs ++;
        }

        @Override
        public String toString() {
            return "value = " + val + "\tid = " + id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Vertex)) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Vertex<T> temp = (Vertex<T>) obj;
            return this.val.equals(temp.val) && this.id == temp.id;
        }
    }

    private static final int DEFAULT_SIZE = 0;
    private int size;
    private int[][] adjMat;
    private Map<T, Integer> database;

    public UndirectedGraph(int sizeIn) {
        size = sizeIn;
        adjMat = new int[sizeIn][sizeIn];
        this.database = new HashMap<>();
    }

    public UndirectedGraph() {
        this(DEFAULT_SIZE);
    }

    public boolean contains(T element) {
        return database.containsKey(element);
    }

    public void addEdge(T v1, T v2, int edgeWeight) {
        if (!database.containsKey(v1) || !database.containsKey(v2)) {
            throw new UnsupportedOperationException("Add the vertices first, before adding an edge!");
        }
        adjMat[database.get(v1)][database.get(v2)] = edgeWeight;
        adjMat[database.get(v2)][database.get(v1)] = edgeWeight;
    }

    public void addVertex(T valIn) {
        if (database.containsKey(valIn)) {
            throw new UnsupportedOperationException("'" + valIn + "'" + " already exists in the graph!");
        }
        
        int[][] newAdjMat = new int[adjMat.length + 1][adjMat.length + 1];
        for (int row = 0; row < adjMat.length; row ++) {
            for (int col = 0; col < adjMat[0].length; col ++) {
                newAdjMat[row][col] = adjMat[row][col];
            }
        }
        adjMat = newAdjMat;
        database.put(valIn, new Vertex<>(valIn).id);
        this.size ++;
    }

    public void printIDs() {
        for (int id: database.values()) {
            System.out.print(id + " ");
        }
        System.out.println();
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
}
