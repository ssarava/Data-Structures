package Queue.Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Queue.impl.ArrayQueue;

public class StudentTestsArray {

    @Test
    public void testConstructor() {
        ArrayQueue list1 = new ArrayQueue();
        assertTrue(list1.getSize() == 0);
        assertTrue(list1.getLength() == 10);
    }

    @Test
    public void testIsEmptyAndEnqueue() {
        ArrayQueue list1 = new ArrayQueue();
        assertTrue(list1.isEmpty());
        System.out.println(list1.toString());

        for (int index = 0; index < 5; index ++) {
            list1.enqueue(index);
        }
        assertFalse(list1.isEmpty());
        System.out.println(list1.toString());
    }

    @Test
    public void testIsFullAndEnqueue() {
        ArrayQueue list1 = new ArrayQueue();
        assertFalse(list1.isFull());
        System.out.println(list1.toString());

        for (int index = 0; index < 5; index ++) {
            list1.enqueue(index);
        }
        assertFalse(list1.isFull());
        System.out.println(list1.toString());

        for (int index = 0; index < 5; index ++) {
            list1.enqueue(index);
        }
        assertTrue(list1.isFull());
        System.out.println(list1.toString());
    }

    @Test
    public void testDequeue() {
        ArrayQueue list1 = new ArrayQueue();
        for (int index = 0; index < 10; index ++) {
            list1.enqueue(index * 2);
        }
        assertTrue(list1.isFull());
        System.out.println(list1.toString());

        list1.dequeue();
        assertTrue(list1.getSize() == 9);
        for (int index = 0; index < 20; index ++) {
            System.out.println("Removing " + list1.dequeue() + "\t" + list1.toString());
        }

    }
    @Test
    public void testPeek() {
        ArrayQueue list1 = new ArrayQueue();
        for (int index = 0; index < 10; index ++) {
            list1.enqueue(index * 2);
        }
        System.out.println(list1.toString());
        assertTrue(list1.peek() == 0);
        list1.dequeue();
        System.out.println(list1.toString());
        assertTrue(list1.peek() == 2);
    }
}
