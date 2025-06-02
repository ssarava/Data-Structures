package Heaps.Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Heaps.impl.MaxHeap;
import Heaps.impl.MinHeap;

public class HeapTests {

    // ---------------------- Tests for MinHeap -------------------- //
    @Test
    public void testMinConstructor() {
        MinHeap<Integer> heap1 = new MinHeap<>();
        assertTrue(heap1.getSize() == 0);

    }
    @Test
    public void testMinEmptySizeClear() {
        MinHeap<String> heap1 = new MinHeap<>();
        assertTrue(heap1.isEmpty());
        for (int index = 0; index < 10; index ++) {
            heap1.add(String.valueOf(index) + "af;dlahfh" + index);
        }
        assertFalse(heap1.getSize() < 10);
        assertTrue(heap1.getSize() >= 10);
        for (int index = 1000; index < 10000; index ++) {
            heap1.add(String.valueOf(index) + "af;dlahfh" + index);
        }
        assertTrue(heap1.getSize() == 9010);
        heap1.clear();
        assertTrue(heap1.getSize() == 0);
    }

    @Test
    public void testMinAdd() {
        MinHeap<Integer> heap1 = new MinHeap<>();
        for (int index = 0; index < 20; index ++) {
            if (index % 2 == 0) {
                heap1.add(index - 13);
            } else {
                heap1.add(2*index + 4);
            }
        }
        
        for (int root = 0; root < heap1.getSize(); root ++) {
            if (root * 2 + 1 < heap1.getSize()) {
                Integer left = heap1.getHeap().get(root * 2 + 1), curr = heap1.getHeap().get(root);
                assertTrue(curr.compareTo(left) < 0);
            }
            if (root * 2 + 2 < heap1.getSize()) {
                Integer right = heap1.getHeap().get(root * 2 + 2), curr = heap1.getHeap().get(root);
                assertTrue(curr.compareTo(right) < 0);
            }
        }
    }
    
    @Test
    public void testRemoveSmallest() {
        MinHeap<Integer> heap1 = new MinHeap<>();
        for (int index = 0; index < 20; index ++) {
            if (index % 2 == 0) {
                heap1.add(index - 13 + 2);
            } else {
                heap1.add(2*index + 4 - 3 + index);
            }
        }
        heap1.removeSmallest();
        for (int curr = 0; curr < heap1.getSize(); curr ++) {
            int left = curr * 2 + 1, right = curr * 2 + 1;

            // check if current value is less than its children (if they exist)
            if (left < heap1.getSize()) {
                assertTrue(heap1.getHeap().get(curr).compareTo(heap1.getHeap().get(right)) < 0);
            }
            if (right < heap1.getSize()) {
                assertTrue(heap1.getHeap().get(curr).compareTo(heap1.getHeap().get(right)) < 0);
            }
        }
    }
    // ------------------------------------------------------------- //

    // ---------------------- Tests for MaxHeap -------------------- //
    @Test
    public void testMaxConstructor() {
        MaxHeap<Integer> heap1 = new MaxHeap<>();
        assertTrue(heap1.getSize() == 0);

    }
    @Test
    public void testMaxEmptySizeClear() {
        MaxHeap<String> heap1 = new MaxHeap<>();
        assertTrue(heap1.isEmpty());
        for (int index = 0; index < 10; index ++) {
            heap1.add(String.valueOf(index) + "af;dlahfh" + index);
        }
        assertFalse(heap1.getSize() < 10);
        assertTrue(heap1.getSize() >= 10);
        for (int index = 1000; index < 10000; index ++) {
            heap1.add(String.valueOf(index) + "af;dlahfh" + index);
        }
        assertTrue(heap1.getSize() == 9010);
        heap1.clear();
        assertTrue(heap1.getSize() == 0);
    }

    @Test
    public void testMaxAdd() {
        MaxHeap<Integer> heap1 = new MaxHeap<>();
        for (int index = 0; index < 20; index ++) {
            if (index % 2 == 0) {
                heap1.add(index - 13);
            } else {
                heap1.add(2*index + 4);
            }
        }
        
        for (int root = 0; root < heap1.getSize(); root ++) {
            if (root * 2 + 1 < heap1.getSize()) {
                Integer left = heap1.getHeap().get(root * 2 + 1), curr = heap1.getHeap().get(root);
                assertTrue(curr.compareTo(left) > 0);
            }
            if (root * 2 + 2 < heap1.getSize()) {
                Integer right = heap1.getHeap().get(root * 2 + 2), curr = heap1.getHeap().get(root);
                assertTrue(curr.compareTo(right) > 0);
            }
        }
    }
    
    @Test
    public void testRemoveLargest() {
        MaxHeap<Integer> heap1 = new MaxHeap<>();
        for (int index = 0; index < 20; index ++) {
            if (index % 2 == 0) {
                heap1.add(index - 13 + 2);
            } else {
                heap1.add(2*index + 4 - 3 + index);
            }
        }
        heap1.removeLargest();
        for (int curr = 0; curr < heap1.getSize(); curr ++) {
            int left = curr * 2 + 1, right = curr * 2 + 1;

            // check if current value is less than its children (if they exist)
            if (left < heap1.getSize()) {
                assertTrue(heap1.getHeap().get(curr).compareTo(heap1.getHeap().get(right)) > 0);
            }
            if (right < heap1.getSize()) {
                assertTrue(heap1.getHeap().get(curr).compareTo(heap1.getHeap().get(right)) > 0);
            }
        }
    }

    // ------------------------------------------------------------- //
}
