package Tries.impl;

import java.util.LinkedList;
import java.util.List;

public class MyCompressedTrie {

    private static class Edge {
        private String string;
        private Node start, end;

        private Edge(String stringIn) {
            this.string = stringIn;
            this.start = null;
            this.end = null;
        }

        @Override
        public String toString() {
            return "node ---" + this.string + "-----> node";
        }
    }

    private static class Node {

        List<Edge> edges;

        private Node() {
            this.edges = new LinkedList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            for (Edge e: this.edges) {
                sb.append(e.string + " ");
            }
            return sb.toString();
        }
    }

    private static class Leaf extends Node {
        private int val;
        private Node pred;

        private Leaf(int valIn) {
            this.val = valIn;
            this.pred = null;
        }
    }

    public Node root;
    public int size;

    public MyCompressedTrie() {
        this.root = new Node();
        this.size = 0;
    }

    public void insert(String s, int value) {
        if (this.size == 0) {
            Edge edge = new Edge(s);
            edge.start = this.root;
            
            // Node linkingNode = new Node();
            

        }
    }
}