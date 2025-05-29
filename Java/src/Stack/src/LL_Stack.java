package Stack.src;


import java.util.EmptyStackException;

/**
 * The implementation of a stack backed by a singly linked list, which is also implemented from scratch.
 *
 * @author Shashank Arava
 */
public class LL_Stack<T extends Comparable<T>> {
    
    private static class Node<T> {
        private T val;
        private Node<T> next;

        private Node(T data) {
            this.val = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public LL_Stack() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void push(T data) {
        Node<T> node = new Node<>(data);
        if (this.size == 0) {
            this.head = node;
        } else  {
            node.next = this.head;
            this.head = node;
        }
        this.size ++;
    }

    public T pop() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        T top = this.head.val;
        this.head = this.head.next;
        this.size --;
        return top;
    }

    public T peek() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        return this.head.val;
    }

    public int search(T object) {
        if (this.size == 0) {
            return -1;
        }
        Node<T> curr = this.head;
        for (int index = 1; index < this.size; index ++, curr = curr.next) {
            if (object.compareTo(curr.val) == 0) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        String s = "";
        Node<T> curr = this.head;
        for (; curr.next != null; curr = curr.next) {
            // s += "[" + curr.val.toString() + "] --> ";   // causes stack to appear like an array when printed to console
            s += "[" + curr.val.toString() + "]\n" ;        // works better with primitives and non-primitive objects
        }
        return s + "[" + curr.val.toString() + "]";
    }
}
