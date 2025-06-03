package Tries.impl;

import java.util.LinkedList;
import java.util.List;

public class MyCompressedTrie {

    private static class Edge {
        private String string;
        private Node start, end;

        private Edge(String stringIn) {
            string = stringIn;
            start = null;
            end = null;
        }

        @Override
        public String toString() {
            String s = start.toString() + end.toString();
            System.out.println(s);
            return "node ---" + string + "-----> node";
        }
    }

    private static class Node {

        List<Edge> edges;

        private Node() {
            edges = new LinkedList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            for (Edge e: edges) {
                sb.append(e.string + " ");
            }
            return sb.toString();
        }
    }

    // private static class Leaf extends Node {
    //     private int val;
    //     private Node pred;

    //     private Leaf(int valIn) {
    //         val = valIn;
    //         pred = null;
    //     }
    // }

    public Node root;
    public int size;

    public MyCompressedTrie() {
        root = new Node();
        size = 0;
    }

    public void insert(String s, int value) {
        if (size == 0) {
            Edge edge = new Edge(s);
            edge.start = root;
            
            // Node linkingNode = new Node();
            

        }
    }
}