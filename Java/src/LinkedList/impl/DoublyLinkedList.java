package LinkedList.impl;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * My implementation of a doubly linked list, with no reliance on structures from Java Collections.
 * Aside from the {@code toArray} method, whose implementation is identical to Java's LinkedList class's, everything was written from scratch.
 * Some methods that are rarely used by most users have yet to be written.
 */
public class DoublyLinkedList<T> implements List<T>, Deque<T>, Cloneable {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        list1.addLast(0);
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);

        // System.out.println(list1);

        Iterator<Integer> iter = list1.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Iterator<Integer> revIter = list1.descendingIterator();
        while (revIter.hasNext()) {
            System.out.println(revIter.next());
        }

    }

    public class Node implements Cloneable {

        private Node next, prev;
        private T data;

        private Node(T dataIn) {
            data = dataIn;
            next = null;
            prev = null;
        }

        @Override
        public Node clone() {
            return new Node(data);
        }
    }

    private Node head, tail;
    private int size;

    /**
     * Creates an empty DoublyLinkedList
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return head.data;
    }

    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return tail.data;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return remove(0);
    }

    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return remove(size - 1);
    }

    /**
     * The following method removes every node that contains the type parameter (of
     * generic type 'T')
     * called "targetData". This method requires no conditions to be invoked. The
     * only side effect is
     * decreasing the BasicLinkedList's size by the number of nodes removed. There
     * are no conditions
     * needed for this method to be invoked.
     */
    public void removeAllInstances(T targetData) {
        Node curr = head, tempHead = head;
        boolean nonTargetNode = false;

        while (curr != null) {
            if (curr.data.equals(targetData)) {
                if (!nonTargetNode) {
                    if (head == curr) {
                        head = curr.next;
                        curr = head;
                        tempHead = curr;
                    } else {
                        curr = curr.next;
                        tempHead = tempHead.next.next;
                    }
                } else {
                    if (tempHead == curr) {
                        tempHead = curr.next;
                        curr = tempHead;
                        tempHead = curr;
                    } else {
                        curr = curr.next;
                        tempHead.next = tempHead.next.next;
                    }
                }
                size--;
            } else {
                curr = curr.next;
                if (tempHead.next != curr) {
                    tempHead = tempHead.next;
                }
                if (!nonTargetNode) {
                    nonTargetNode = true;
                }
            }
        }
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private Node curr = head;

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");

        for (Node curr = head; curr != null; curr = curr.next) {
            sb.append("[" + curr.data + "] <--> ");
        }
        return sb.append(" null").toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DoublyLinkedList)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        DoublyLinkedList<T> temp = (DoublyLinkedList<T>) other;
        Node curr1 = head, curr2 = temp.head;
        while (curr1 != null && curr2 != null) {
            if (!curr1.data.equals(curr2.data)) {
                return false;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;

            // unequal lengths
            if ((curr1 == null && curr2 != null) || (curr1 != null && curr2 == null)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines whether this list contains the argument.
     */
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("List does not contain null elements.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        T temp = (T) o;
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(temp)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Returns the index of the first occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    @SuppressWarnings("unchecked")
    public int indexOf(Object data) {
        int index = 0;
        T temp = (T) data;
        for (Node curr = head; curr != null; curr = curr.next, index ++) {
            if (curr.data.equals(temp)) {
                return index;
            }
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    public int lastIndexOf(Object element) {
        if (element == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (element.getClass() != head.data.getClass()) {
            throw new ClassCastException(element.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        int index = size - 1;
        Node curr = tail;
        while (curr != null) {
            if (curr.data.equals(element)) {
                return index;
            }
            index--;
            curr = curr.prev;
        }
        return -1;
    }

    public T set(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot set element at index " + index);
        }
        if (element.getClass() != head.data.getClass()) {
            throw new ClassCastException(element.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        Node curr;
        int currIndex;
        T data;
        if (index < size / 2) {
            currIndex = 0;
            curr = head;
            while (curr != null && currIndex < index) {
                curr = curr.next;
                currIndex++;
            }

        } else {
            currIndex = size - 1;
            curr = tail;
            while (curr != null && currIndex > index) {
                curr = curr.prev;
                currIndex--;
            }
        }
        data = curr.data;
        curr.data = element;
        return data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot retrieve at index " + index);
        }
        if (index == 0) {
            return head.data;
        }
        int counter = 0;
        Node curr = head;
        while (counter < index) {
            curr = curr.next;
            counter++;
        }
        return curr.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot remove at index " + index);
        }
        T data;
        if (index == 0) {
            data = head.data;
            if (size != 1) {
                head.next.prev = null;
            }
            head = head.next;
        } else if (index == size - 1) {
            data = tail.data;
            if (size != 1) {
                tail.prev.next = null;
            }
            tail = tail.prev;
        } else {
            Node curr = index < size / 2 ? head : tail;
            int currIndex = index < size / 2 ? 0 : size - 1;
            if (index < size / 2) {
                System.out.println("BEFORE halfway");
                while (currIndex < index) {
                    curr = curr.next;
                    currIndex++;
                }
            } else {
                System.out.println("halfway BEYOND");
                while (currIndex > index) {
                    curr = curr.prev;
                    currIndex--;
                }
            }
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
            data = curr.data;
        }
        size--;
        return data;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size + 1) {
            throw new IndexOutOfBoundsException("Can't insert at index " + index + " because it's out of bounds!");
        }
        if (element == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        Node toAdd = new Node(element);
        if (index == 0) {
            if (size == 0) {
                tail = toAdd;
            } else {
                toAdd.next = head;
                head.prev = toAdd;
            }
            head = toAdd;
        } else if (index == size) {
            // add before the last element
            if (size == 0) {
                head = toAdd;
                tail = toAdd;
            } else {
                toAdd.prev = tail.prev;
                toAdd.next = tail;
                toAdd.prev.next = toAdd;
                toAdd.next.prev = toAdd;
            }
        } else if (index == size + 1) {
            // append to end of the list
            if (size == 0) {
                head = toAdd;
            } else {
                toAdd.prev = tail;
                tail.next = toAdd;
            }
            tail = toAdd;
        } else {
            Node curr = index < size / 2 ? head : tail;
            int currIndex = index < size / 2 ? 0 : size - 1;

            if (index < size / 2) {
                System.out.println("before halfway");
                while (curr != null && currIndex < index - 1) {
                    curr = curr.next;
                    currIndex++;
                }

            } else {
                System.out.println("halfway beyond");
                while (curr != null && currIndex > index - 1) {
                    curr = curr.prev;
                    currIndex--;
                }
            }
            toAdd.next = curr.next;
            toAdd.prev = curr.prev;
            curr.next = toAdd;
        }
        size++;
    }

    // needs testing
    @SuppressWarnings("unchecked")
    @Override
    public DoublyLinkedList<T> clone() throws CloneNotSupportedException {
        DoublyLinkedList<T> copy = (DoublyLinkedList<T>) super.clone();
        copy.head = head;
        copy.tail = tail;
        copy.size = size;
        return copy;
    }

    /**
     * Returns a deep copy of this DoublyLinkedList.
     */
    public DoublyLinkedList<T> deepClone() {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>();
        for (Node curr = head; curr != null; curr = curr.next) {
            newList.add(curr.data);
        }
        return newList;
    }

    // needs testing
    public Object[] toArray() {
        Object[] ret = new Object[size];
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            ret[index++] = curr.data;
        }
        return ret;
    }

    // needs testing
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> T[] toArray(T[] a) {
        // make 'a' larger if it's smaller than this list
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Object[] pointer = a; // needed to resolve compiler type conversion conflict
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            pointer[index++] = curr.data;
        }

        // set first element immediately following the last element to null (useful for determining size of the list since no null elements are allowed in the list)
        if (a.length > size) {
            a[size] = null; 
        }
        return a;
    }

    // needs testing
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (element.getClass() != head.data.getClass()) {
            throw new ClassCastException(element.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        add(size + 1, element);
        return true;
    }

    // needs testing
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        if (size == 0) {
            return false;
        }

        int targIndex = indexOf(o);
        if (targIndex == -1) {
            return false;
        }
        remove(targIndex);
        return true;
    }

    // needs testing
    public boolean containsAll(Collection<?> c) {
        if (this == c) {
            return true;
        }
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    // needs testing
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (T element : c) {
            add(size + 1, element);
        }
        return size == initialSize;
    }

    // needs testing
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int ind = 0, initialSize = size;
        while (ind < index) {
            index++;
        }
        for (T element : c) {
            add(ind++, element);
        }
        return size == initialSize;
    }

    // needs testing
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (Object element : c) {
            removeAllInstances((T) element);
        }
        return size == initialSize;
    }

    // needs testing
    public boolean retainAll(Collection<?> c) {
        if (this == c) {
            return false;
        }
        int initialSize = 0;
        DoublyLinkedList<T> toRemove = new DoublyLinkedList<>();
        for (T element: this) {
            if (!c.contains(element)) {
                toRemove.add(element);
            }
        }
        for (T element: toRemove) {
            removeAllInstances(element);
        }
        return size == initialSize;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // incomplete... needs testing
    public ListIterator<T> listIterator() {
        return new ListIterator<>() {

            private Node curr = head;
            private int currInd = 0;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                currInd++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                return curr != null;
            }

            @Override
            public T previous() {
                T data = curr.data;
                curr = curr.prev;
                currInd--;
                return data;
            }

            @Override
            public int nextIndex() {
                return currInd + 1;
            }

            @Override
            public int previousIndex() {
                return currInd - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
            }

            @Override
            public void set(T e) {
                throw new UnsupportedOperationException("Unimplemented method 'set'");
            }

            @Override
            public void add(T e) {
                throw new UnsupportedOperationException("Unimplemented method 'add'");
            }

        };
    }

    // incomplete... needs testing
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<>() {

            private Node curr = head;
            private int currInd = initializeIndex(index);

            private static int initializeIndex(int index) {
                int someInd = 0;
                while (someInd < index) {
                    someInd ++;
                }
                return someInd;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                currInd++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                return curr != null;
            }

            @Override
            public T previous() {
                T data = curr.data;
                curr = curr.prev;
                currInd--;
                return data;
            }

            @Override
            public int nextIndex() {
                return currInd + 1;
            }

            @Override
            public int previousIndex() {
                return currInd - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
            }

            @Override
            public void set(T e) {
                throw new UnsupportedOperationException("Unimplemented method 'set'");
            }

            @Override
            public void add(T e) {
                throw new UnsupportedOperationException("Unimplemented method 'add'");
            }

        };
    }

    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size) {
            throw new UnsupportedOperationException(
                    "At least one of 'fromtIndex' and 'toIndex' is out of bounds! Can't produce a sublist.");
        } else if (fromIndex == toIndex) {
            return new DoublyLinkedList<>();
        }
        DoublyLinkedList<T> ret = new DoublyLinkedList<>();
        for (int index = fromIndex; index < toIndex; index++) {
            ret.add(get(index));
        }
        return ret;
    }

    public void addFirst(T e) {
        if (e == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        add(0, e);
    }

    public void addLast(T e) {
        if (e == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        add(size + 1, e);
    }

    public boolean offerFirst(T e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(T e) {
        addLast(e);
        return true;
    }

    public T pollFirst() {
        return size == 0 ? null : removeFirst();
    }

    public T pollLast() {
        return size == 0 ? null : removeLast();
    }

    public T peekFirst() {
        return size == 0 ? null : head.data;
    }

    public T peekLast() {
        return size == 0 ? null : tail.data;
    }

    // needs testing
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        if (size == 0) {
            return false;
        }
        int initialSize = size;
        remove(o);
        return size == initialSize;
    }

    // needs testing
    @SuppressWarnings("unchecked")
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }
        if (size == 0) {
            return false;
        }
        T temp = (T) o;
        int targIndex = size - 1, initialSize = size;
        for (Node curr = tail; curr != null && !curr.data.equals(temp); curr = curr.prev) {
            targIndex --;
        }
        if (targIndex == -1) {
            return false;
        }
        remove(targIndex);
        return initialSize == size;
    }

    // needs testing
    public boolean offer(T e) {
        return add(e);
    }

    // needs testing
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot remove elements.");
        }
        T data = head.data;
        if (head.next != null) {
            head.next.prev = null;
        }
        head = head.next;
        return data;
    }

    // needs testing
    public T poll() {
        return pollFirst();
    }

    // needs testing
    public T element() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot remove elements.");
        }
        return head.data;
    }

    // needs testing
    public T peek() {
        return size == 0 ? null : head.data;
    }

    // needs testing
    public void push(T e) {
        addFirst(e);
    }

    // needs testing
    public T pop() {
        return removeFirst();
    }

    // needs testing
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {

            private Node curr = tail;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.prev;
                return data;
            }

        };
    }
}