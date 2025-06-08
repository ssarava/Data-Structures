package Lists.impl;

import java.util.List;
import java.util.Random;

public class SkipList<T> {

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();
        System.out.println(list);
    }

    private class Node {
        private T data;
        private int numLevels;
        private List<Node> pointers;

        private Node (T dataIn, int numLevelsIn) {
            data = dataIn;
            numLevels = numLevelsIn;
            pointers = new SinglyLinkedList<>();
        }

        private Node(T dataIn) {
            this(dataIn, 0);
        }

        @Override
        public String toString() {
            return data.toString() + "\tlevels: " + numLevels;
        }
    }

    /**
     * Maximum enforced level for any given node.
     * All nodes exist at level 0
     */
    private static final int MEL = 3;
    private Node head, tail;
    private int size;
    private double prob;

    public SkipList(double probIn) {
        head = new Node(null, MEL);
        tail = new Node(null, MEL);
        size = 0;
        prob = probIn;
    }

    public SkipList() {
        this(0.5f);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {

    }

    protected int newLevel() {
        System.out.println(tail);
        System.out.println(prob);
        int newLevel = 0;
        Random rand = new Random();
        while (Math.abs(rand.nextInt()) % 2 == 0) {
            newLevel ++;
        }
        return newLevel;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        }
        String s = "";

        for (Node curr = head; curr != null; curr = curr.pointers.get(0)) {
            s += "[" + curr.data + "] --> ";
        }
        return s + " null";
    }

}