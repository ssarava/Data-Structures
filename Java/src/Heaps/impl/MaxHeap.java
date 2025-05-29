package Heaps.impl;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    public static void main(String[] args) throws Exception {
        MaxHeap<Integer> heap1 = new MaxHeap<>();
        for (int index = 0; index < 20; index ++) {
            if (index % 2 == 0) {
                heap1.add(index - 18);
            } else {
                heap1.add(3*index + 8);
            }
        }
        System.out.println(heap1.toString());
    }

    public static final int DEFAULT_SIZE = 10, DEFAULT_RESIZE_FACTOR = 2;

    private ArrayList<T> heap;
    private int size, nextAvailPos, resizeFactor;

    public MaxHeap(int initialSize, int resizeFactorIn) {
        heap = new ArrayList<>();
        for (int index = 0; index < initialSize; index ++) {
            heap.add(null);
        }
        nextAvailPos = 0;
        resizeFactor = resizeFactorIn;
    }

    public MaxHeap(int resizeFactorIn) {
        this(DEFAULT_SIZE, resizeFactorIn);
    }

    public MaxHeap() {
        this(DEFAULT_SIZE, DEFAULT_RESIZE_FACTOR);
    }

    public ArrayList<T> getHeap() {
        return heap;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        for (int index = 0; index < size; index ++) {
            heap.set(index, null);
        }
        size = 0;
        nextAvailPos = 0;
    }

    public void swap(int first, int second) {
        T temp = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    }

    public void add(T elem) {
        // resize if needed
        if (nextAvailPos >= heap.size()) {
            int currSize = heap.size();
            for (int index = currSize; index < currSize * resizeFactor; index ++) {
                heap.add(null);
            }
        }
        
        int child = nextAvailPos;   
        heap.set(child, elem);         // make the newly added element the 'child'
        nextAvailPos ++;

        // while parent is less than current element;
        while (child > 0 && heap.get((child - 1) / 2).compareTo(heap.get(child)) < 0) {
            swap((child - 1) / 2, child);
            child = (child - 1) / 2;
        }
        size ++;
    }

    private Integer getLargestChild(int root) {
        int left = root * 2 + 1, right = root * 2 + 2;

        // both children are within bounds
        if (left < size && right < size) {
            return (heap.get(left).compareTo(heap.get(right)) > 0) ? left : right;

        // left and right are out of bounds
        } else if (left >= size) {
            return null;

        // left is within bounds but right is out of bounds
        } else if (left < size && right >= size) {
            return left;
        }
        return null;
    }

    public void removeLargest() {
        if (size == 0) {
            return;
        }
        // swap root with last element
        swap(0, size - 1);
        
        // remove the last element
        heap.set(size - 1, null);
        size --;

        int root = 0, left = root * 2 + 1, right = root * 2 + 2;

        // while root's value is smaller than any of its children's values, swap with the child with the largest value
        while (heap.get(root).compareTo(heap.get(left)) < 0 || heap.get(root).compareTo(heap.get(right)) < 0) {

            Integer largestChild = getLargestChild(root);
            if (largestChild == null) {
                break;
            }

            swap(root, largestChild);
            root = largestChild;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int index = 0; index < heap.size(); index ++) {
            sb.append(heap.get(index) + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}
