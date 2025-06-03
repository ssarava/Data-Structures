package Graphs.UnweightedGraph.impl;

/**
 * My implementation of an unweighted, undirected graph.
 * In accordance with standard graph theory, no duplicates are allowed.
 */
public class MyUnweightedUnirectedGraph<T> {
    
    private class Vertex {

        private static int idMarker;
        T data;
        int id;
        // List<T> adjacencies;

        private Vertex(T dataIn) {
            data = dataIn;
            id = idMarker;
            idMarker ++;
            // adjacencies = new SinglyLinkedList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            sb.append("data = " + data + "\nid: " + id + "\nAdjacencies: ");
            // for (T element: adjacencies) {
            //     sb.append(element.toString() + ", ");
            // }
            // sb.replace(sb.length() - 1, sb.length(), "");
            return sb.toString();
        }
    }

    private int size;
    private int[][] adjacencies;

    public MyUnweightedUnirectedGraph() {
        size = 0;
        adjacencies = new int[0][0];
    }

    public void add(T element) {
        Vertex toAdd = new Vertex(element);
        System.out.println(toAdd);
        int[][] temp = new int[size + 1][size + 1];
        for (int row = 0; row < adjacencies.length; row ++) {
            for (int col = 0; col < adjacencies[0].length; col ++) {
                temp[row][col] = adjacencies[row][col];
            }
        }

    }
}
