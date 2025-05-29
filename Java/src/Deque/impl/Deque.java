package Deque.impl;

import java.util.Iterator;

// Backed by a doubly linked list
public class Deque<T> implements Iterable<T>{

    private static class Node<T> {

        private Node<T> next, prev;
        private T data;

        private Node(T element) {
            this.data = element;
            this.next = null;
        }
    }
    
    Node<T> head, tail;
    int size;
    final static int DEFAULT_SIZE = 10;

    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return DEFAULT_SIZE;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.getCapacity();
    }

    public boolean add(T element) {
        // if (this.isFull()) {
        //     throw new IllegalStateException("The MyDeque is full! Unable to add element.");
        // }
        // Node<T> node = new Node<>(element);
        // if (this.head == null) {
        //     this.head = node;
        //     this.tail = node;
        // } else {
        //     Node<T> curr = this.head;
        //     while (curr.next != null) {
        //         curr = curr.next;
        //     }
        //     curr.next = node;
        //     node.prev = curr;
        // }
        // this.size ++;
        this.addLast(element);
        return true;
    }

    public void addFirst(T element) {
        if (this.isFull()) {
            throw new IllegalStateException("The MyDeque is full! Unable to add element.");
        }
        Node<T> node = new Node<>(element);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size ++;
        return;
    }

    public void addLast(T element) {
        if (this.isFull()) {
            throw new IllegalStateException("The MyDeque is full! Unable to add element.");
        }
        Node<T> node = new Node<>(element);
        if (this.tail == null) {
            this.tail = node;
            this.head = node;
        } else {
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
        this.size ++;
        return;
    }

    public boolean contains(T element) {
        for (Node<T> curr = this.head; curr.next != null; curr = curr.next) {
            if (curr.data.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {

            Node<T> curr = tail;

            @Override
            public T next() {
                T data = curr.data;
                tail = tail.prev;
                return data;
            }

            @Override
            public boolean hasNext() {
                return tail.prev != null;
            }
        };
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> curr = head;

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }

            @Override
            public boolean hasNext() {
                return curr.next != null;
            }
        };
    }

    public T element() {
        return this.head.data;
    }

    public T getFirst() {
        return this.head.data;
    }

    public T getLast() {
        return this.tail.data;
    }

    public boolean offer(T element) {
        if (this.isFull()) {
            return false;
        }
        Node<T> node = new Node<>(element);
        if (this.tail == null) {
            this.tail = node;
            this.head = node;
        } else {
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
        this.size ++;
        return true;
    }

    public boolean offerFirst(T element) {
        if (this.isFull()) {
            return false;
        }
        Node<T> node = new Node<>(element);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size ++;
        return true;
    }

    public boolean offerLast(T element) {
        return this.offer(element);
    }

    public T peek() {
        return this.isEmpty() ? null : this.head.data;
    }

    public T peekFirst() {
        return peek();
    }

    public T peekLast() {
        return this.isEmpty() ? null : this.tail.data;
    }

    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        T data = this.head.data;
        this.head = this.head.next;
        this.size --;
        return data;
    }

    public T pollFirst() {
        return poll();
    }

    public T pollLast() {
        if (this.isEmpty()) {
            return null;
        }
        T data = this.tail.data;
        this.tail = this.tail.prev;
        this.size --;
        return data;
    }

    public T remove() {
        return poll();
    }

    public boolean remove(T element) {
        for (Node<T> curr = this.head; curr != null; curr = curr.next) {
            if (curr.data == element) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                this.size --;
                return true;
            }
        }
        return false;
    }

    public T removeFirst() {
        return poll();
    }

    public boolean removeFirstOccurrence(T element) {
        return remove(element);
    }

    public T removeLast() {
        return pollLast();
    }

    public boolean removeLastOccurrence(T element) {
        for (Node<T> curr = this.tail; curr != null; curr = curr.prev) {
            if (curr.data == element) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                this.size --;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        String s = "[ ";
        for (Node<T> curr = this.head; curr != null; curr = curr.next) {
            s += curr.data + " ";
        }
        return s + "]";
    }

    public static void main(String[] args) {
        Deque<Integer> arr1 = new Deque<>();
        System.out.println("arr1 size = " + arr1.size());
        System.out.println("arr1 capacity = " + arr1.getCapacity());
    }
}
