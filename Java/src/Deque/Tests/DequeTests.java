package Deque.Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Deque.impl.Deque;

public class DequeTests {
    @Test
    public void testConstructor() {
        Deque<Integer> list1 = new Deque<Integer>();
        assertTrue(list1.size() == 0);
        assertTrue(list1.isEmpty());
        assertFalse(list1.isFull());
        return;
    }

    @Test
    public void testIsEmptyAndIsFullAndAddFrst() {
        Deque<Integer> list1 = new Deque<Integer>();
        assertTrue(list1.isEmpty());
        System.out.println(list1.toString());

        for (int index = 0; index < 5; index++) {
            list1.addFirst(index);
        }
        assertFalse(list1.isEmpty());
        System.out.println(list1.toString());

        for (int index = 0; index < 5; index++) {
            list1.addFirst(index);
        }
        System.out.println(list1.toString());
        assertTrue(list1.isFull());

        try {
            list1.addFirst(99);
        } catch (IllegalStateException e) {
            System.out.println("Entered catch block; can't add element");
        } finally {
            System.out.println("Finally block entered");
        }
    }

    @Test
    public void testAdd() {
        Deque<Integer> d1 = new Deque<>();
        for (int index = 0; index < 10; index ++) {
            d1.add(index);
        }
        System.out.println(d1.toString());
        assertTrue(d1.size() == 10);
        assertTrue(d1.isFull());
        try {
            d1.addFirst(99);
        } catch (IllegalStateException e) {
            System.out.println("Entered catch block; can't add element");
        } finally {
            System.out.println("Finally block entered");
        }
    }

    @Test
    public void testAddLast() {
        Deque<Integer> d1 = new Deque<>();
        for (int index = 0, factor = 2; index < 10; index ++, factor *= 2) {
            d1.addLast(factor);
        }
        System.out.println(d1.toString());
        assertTrue(d1.size() == 10);
        assertTrue(d1.isFull());
    }
}
