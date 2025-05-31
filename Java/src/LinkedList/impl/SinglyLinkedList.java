package LinkedList.impl;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

// This class represents an implementation of a Singly Linked List, as was done in CMSC132 with Dr. Emad. There will NOT be a 'tail' reference
public class SinglyLinkedList<T> implements List<T>, Queue<T>, Cloneable {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        // for (int index = 1; index < 6; index ++) {
        // list1.offer(index);
        // }
        System.out.println(list1);
        list1.reverse();
        System.out.println(list1);
    }

    private class Node implements Cloneable {

        private T data;
        private Node next;

        private Node(T dataIn) {
            data = dataIn;
            next = null;
        }

        @Override
        public Node clone() {
            return new Node(data);
        }
    }

    private Node head, tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SinglyLinkedList)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        SinglyLinkedList<T> temp = (SinglyLinkedList<T>) other;
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

    /*
     * The following method removes the BasicLinkedList's head and returns its data.
     * The only side effect is decreasing the BasicLinkedList's size by one.
     * This method has no parameters nor conditions needed to be invoked.
     */
    public T retrieveFirstElement() {
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /*
     * The following method removes the BasicLinkedList's tail and returns its data.
     * The only side effect is decreasing the BasicLinkedList's size by one.
     * This method has no parameters nor conditions needed to be invoked.
     */
    public T retrieveLastElement() {
        Node curr = head;
        if (isEmpty()) {
            return null;
        } else if (head.next == null) {
            size--;
            return head.data;
        }
        while (curr.next.next != null) {
            curr = curr.next;
        }
        T tailData = curr.next.data;
        curr.next = null;
        size--;
        return tailData;
    }

    /*
     * The following method removes every node that contains the type parameter (of
     * generic type 'T')
     * called "targetData". This method requires no conditions to be invoked. The
     * only side effect is
     * decreasing the BasicLinkedList's size by the number of nodes removed. There
     * are no conditions
     * needed for this method to be invoked.
     */
    public void removeAllInstances(Object targetData) {
        if (targetData == null) {
            return;
        }
        if (targetData.getClass() != head.data.getClass()) {
            throw new ClassCastException(targetData.getClass().getName() + " is not compatible with a deque of type " + head.data.getClass().getName());
        }

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

    /*
     * The following overridden method returns an generic type 'T' iterator for the
     * BasicLinkedList.
     * This method has no parameters, side effects, nor conditions needed for this
     * method to be invoked.
     * The iterator is returned as an anonymous inner class.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node curr = head;

            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }

            public boolean hasNext() {
                return curr != null;
            }

        };
    }

    // returns a string representation of a MySinglyLinkedList object for debugging
    // purposes
    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        String s = "";

        for (Node curr = head; curr != null; curr = curr.next) {
            s += "[" + curr.data + "] --> ";
        }
        return s + " null";
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(o)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Returns the index of the first occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    public int indexOf(Object data) {
        int index = 0;
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object data) {
        int index = 0, lastIndex = -1;
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                lastIndex = index;
            }
            curr = curr.next;
            index++;
        }
        return lastIndex;
    }

    public T set(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int currIndex = 0;
        Node curr = head;
        while (curr != null) {
            curr = curr.next;
            if (currIndex + 1 == index) {
                break;
            }
            currIndex++;
        }
        T prevElement = curr.data;
        curr.data = element;
        return prevElement;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot remove at index " + index);
        }
        T data;
        if (index == 0) {
            data = head.data;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
            }

        } else {
            Node curr = head;
            int currIndex = 0;
            while (curr != null && currIndex < index - 1) {
                curr = curr.next;
                currIndex++;
            }
            data = curr.next.data;
            curr.next = curr.next.next;
        }
        size--;
        return data;
    }

    // probably needs work and testing
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Can't insert at index " + index + " because it's out of bounds!");
        }
        if (element == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        Node node = new Node(element);
        if (index == 0) {
            node.next = head;
            head = node;
        } else if (index == size) {
            tail.next = node;
            tail = node;
        } else {
            Node curr = head;
            int currIndex = 0;
            while (curr != null && currIndex < index - 1) {
                curr = curr.next;
                currIndex++;
            }
            node.next = curr.next;
            curr.next = node;
        }
        size++;
    }

    public void reverse() {
        Node node = null;
        while (head != null) {
            Node temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }
        head = node;
    }

    public SinglyLinkedList<T> reverseClone() {
        SinglyLinkedList<T> clone = deepClone();
        clone.reverse();
        return clone;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            arr[index++] = curr.data;
        }
        return arr;
    }

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

        // set first element immediately following the last element to null (useful for
        // determining size of the list since no null elements are allowed in the list)
        // if (a.length > size) {
        //     a[size] = null;
        // }
        return a;
    }

    // needs testing
    public boolean add(T e) {
        add(size, e);
        return true;
    }

    // needs testing
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type "
                    + head.data.getClass().getName());
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
        for (T e : c) {
            add(size + 1, e);
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
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (Object element : c) {
            removeAllInstances(element);
        }
        return size == initialSize;
    }

    // needs testing
    public boolean retainAll(Collection<?> c) {
        if (this == c) {
            return false;
        }
        int initialSize = 0;
        SinglyLinkedList<T> toRemove = new SinglyLinkedList<>();
        for (T element : this) {
            if (!c.contains(element)) {
                toRemove.add(element);
            }
        }
        for (T element : toRemove) {
            removeAllInstances(element);
        }
        return size == initialSize;
    }

    // needs testing
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // needs testing
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
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
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
                    someInd++;
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
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException("Unimplemented method 'remove'");
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

    // needs testing
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size) {
            throw new UnsupportedOperationException(
                    "At least one of 'fromtIndex' and 'toIndex' is out of bounds! Can't produce a sublist.");
        } else if (fromIndex == toIndex) {
            return new SinglyLinkedList<>();
        }
        SinglyLinkedList<T> ret = new SinglyLinkedList<>();
        for (int index = fromIndex; index < toIndex; index++) {
            ret.add(get(index));
        }
        return ret;
    }

    // needs testing
    public void addFirst(T e) {
        add(0, e);
    }

    // needs testing
    public boolean offerFirst(T e) {
        addFirst(e);
        return true;
    }


    // needs testing
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return remove(size - 1);
    }

    // needs testing
    public T pollLast() {
        return size == 0 ? null : removeLast();
    }

    // needs testing
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return tail.data;
    }

    // needs testing
    public T peekLast() {
        return size == 0 ? null : tail.data;
    }

    // needs testing
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type "
                    + head.data.getClass().getName());
        }
        if (size == 0) {
            return false;
        }
        int initialSize = size;
        remove(o);
        return size == initialSize;
    }

    // needs testing
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
        if (o.getClass() != head.data.getClass()) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type "
                    + head.data.getClass().getName());
        }
        if (size == 0) {
            return false;
        }
        int targIndex = 0, initialSize = size;
        for (Node curr = head; curr != null && !curr.data.equals(o); curr = curr.next) {
            targIndex++;
        }
        if (targIndex >= size) {
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
        return remove(0);
    }

    // needs testing
    public T poll() {
        return size == 0 ? null : remove(0);
    }

    // needs testing
    public T element() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty; cannot retrieve elements.");
        }
        return head.data;
    }

    // needs testing
    public T peek() {
        return size == 0 ? null : head.data;
    }

    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {

            private Node curr = reverseClone().head;

            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }

            public boolean hasNext() {
                return curr != null;
            }
        };
    }

    // needs testing
    @SuppressWarnings("unchecked")
    @Override
    public SinglyLinkedList<T> clone() throws CloneNotSupportedException {
        SinglyLinkedList<T> copy = (SinglyLinkedList<T>) super.clone();
        copy.head = head;
        copy.tail = tail;
        copy.size = size;
        return copy;
    }

    /**
     * Returns a deep copy of this SinglyLinkedList.
     */
    public SinglyLinkedList<T> deepClone() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        for (Node curr = head; curr != null; curr = curr.next) {
            newList.add(curr.data);
        }
        return newList;
    }
}