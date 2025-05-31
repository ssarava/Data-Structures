package Stack.src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * The implementation of a stack backed by a singly linked list, which is also
 * implemented from scratch.
 *
 * @author Shashank Arava
 */
public class MyStack<T> implements Collection<T>, Cloneable {

    public static void main(String[] args) {
        MyStack<String> s = new MyStack<>();
        for (int index = 0; index < 10; index++) {
            s.push("hello" + String.valueOf(index));
        }
        ArrayList<String> list = new ArrayList<>();
        for (int index = 2; index < 6; index++) {
            list.add("hello" + String.valueOf(index));
        }
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataIn) {
            data = dataIn;
            next = null;
        }
    }

    private Node head;
    private int size;

    public MyStack() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        int ind = 0;
        Node curr = head;
        while (ind++ < index) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Adds data to the top of the stack. Same functionality as {@code add(T data)}.
     * @param data
     */
    public void push(T data) {
        Node node = new Node(data);
        if (size == 0) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T top = head.data;
        head = head.next;
        size--;
        return top;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return head.data;
    }

    public int search(T o) {
        if (size == 0) {
            return -1;
        }
        Node curr = head;
        for (int index = 1; index < size; index++, curr = curr.next) {
            if (curr.data.equals(o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        String s = "";
        Node curr = head;
        for (; curr.next != null; curr = curr.next) {
            // s += "[" + curr.data.toString() + "] --> "; // causes stack to appear like an
            // array when printed to console
            s += "[" + curr.data.toString() + "]\n"; // works better with primitives and non-primitive objects
        }
        return s + "[" + curr.data.toString() + "]";
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("List does not contain null elements.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type "
                    + head.data.getClass().getName());
        }
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.data.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Node curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }

        };
    }

    public Iterator<T> reverseIterator() {
        MyStack<T> temp = new MyStack<>();
        while (!isEmpty()) {
            temp.push(pop());
        }
        return temp.iterator();
    }

    // -------------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------- Below are methods relevant to the Java Collections interface ----------------------------- //
    // ---------------------- I implemented these for fun/practice, and used only standard stack operations ---------------------- //
    // -------------------------------------------------------------------------------------------------------------------------- //
    
    // needs testing
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            arr[index++] = curr.data;
        }
        return arr;
    }

    // needs testing
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> T[] toArray(T[] a) {
        // make 'a' larger if it's smaller than this list
        if (a.length != size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Object[] pointer = a; // needed to resolve compiler type conversion conflict
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            pointer[index++] = curr.data;
        }

        // set first element immediately following the last element to null (useful for determining size of the list since no null elements are allowed in the list)
        // if (a.length > size) {
        //     a[size] = null; 
        // }
        return a;
    }

    public boolean add(T e) {
        push(e);
        return true;
    }

    public boolean remove(Object o) {
        MyStack<T> tempStack = new MyStack<>();
        T element = pop();
        while (size != 0 && !element.equals(o)) {
            tempStack.push(element);
            element = pop();
        }

        if (size == 0) {
            while (!tempStack.isEmpty()) {
                push(tempStack.pop());
            }
            return false;
        }
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        if (this == c) {
            return true;
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        if (this == c) {
            clear();
            return true;
        }
        int initialSize = 0;
        for (Object element: c) {
            remove(element);
        }
        return initialSize == size;
    }

    public boolean retainAll(Collection<?> c) {
        int initialSize = size;
        MyStack<T> temp = new MyStack<>();
        while (size != 0) {
            T popped = pop();
            if (c.contains(popped)) {
                temp.push(popped);
            }
        }
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return size == initialSize;
    }

    
    public void clear() {
        head = null;
        size = 0;
    }

    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        MyStack<T> temp = new MyStack<>();
        for (T element: c) {
            temp.push(element);
        }
        while (!(temp.isEmpty())) {
            push(temp.pop());
        }
        return size == initialSize;
    }

    // -------------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------- Below are methods that characterize other Java collections ------------------------------- //
    // ---------------------- I implemented these for fun/practice, and used only standard stack operations ---------------------- //
    // -------------------------------------------------------------------------------------------------------------------------- //

    protected boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        MyStack<T> temp = new MyStack<>(), cTemp = new MyStack<>();
        for (T element: c) {
            cTemp.push(element);
        }
        for (int ind = 0; ind < index; ind ++) {
            temp.push(pop());
        }
        while (!cTemp.isEmpty()) {
            push(cTemp.pop());
        }
        while (temp.isEmpty()) {
            push(temp.pop());
        }
        return size == initialSize;
    }

    protected T set(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Cannot replace a value with a null reference.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot set element at index " + index + "; it's out of bounds!");
        }
        MyStack<T> temp = new MyStack<>();
        for (int ind = 0; ind < index - 1; ind ++) {
            temp.push(pop());
        }
        T popped = pop();
        push(element);
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return popped;
    }

    protected void add(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Cannot replace a value with a null reference.");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot add element at index " + index + "; it's out of bounds!");
        }
        MyStack<T> temp = new MyStack<>();
        for (int ind = 0; ind < index; ind ++) {
            temp.push(pop());
        }
        push(element);
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
    }

    /**
     * Removes the element at the 0-based index of the first occurrence of the argument, or -1 if it doesn't exist
     */
    // needs testing
    protected T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        MyStack<T> temp = new MyStack<>();
        for (int ind = 0; ind < index; ind ++) {
            temp.push(pop());
        }
        T popped = temp.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return popped;
    }

    /**
     * Returns the 0-based index of the first occurrence of the argument, or -1 if it doesn't exist
     */
    // needs testing
    protected int indexOf(Object o) {
        if (!contains(o)) {
            return -1;
        }
        MyStack<T> temp = new MyStack<>();
        int index = 0;
        T element = pop();
        while (!element.equals(o)) {
            index ++;
            temp.push(element);
            element = pop();
        }
        return index;
    }

    // needs testing
    protected int lastIndexOf(Object o) {
        if (!contains(o)) {
            return -1;
        }
        int ind = size - 1;
        MyStack<T> temp = new MyStack<>();
        while (size != 0) {
            temp.push(pop());
        }

        T element = temp.pop();
        while (!element.equals(o)) {
            ind --;
            push(element);
            element = temp.pop();
        }
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return ind;
    }
}