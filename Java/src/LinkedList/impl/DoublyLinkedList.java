package LinkedList.impl;

import java.util.Iterator;

// this class represents an implementation of a doubly linked list WITH a tail reference
public class DoublyLinkedList<T> implements Iterable<T>, Cloneable {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        list1.addToEnd(0);
        list1.addToEnd(1);
        list1.addToEnd(2);
        list1.addToEnd(3);

        // System.out.println(list1);

        Iterator<Integer> iter = list1.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        
        Iterator<Integer> revIter = list1.reverseIterator();
        while (revIter.hasNext()) {
            System.out.println(revIter.next());
        }

    }

    public class Node {

        private Node next, prev;
        private T data;

        private Node(T dataIn) {
            data = dataIn;
            next = null;
            prev = null;
        }

        @Override
        public String toString() {
            return "NODE! data = " + data.toString();
        }
    }

    private Node head, tail;
    private int size;
    final static int EMPTY_LIST = 0, SINGLE_NODE = 1;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = EMPTY_LIST;
    }

    /**
     * Initialize a linked list of nulls
     * 
     * @param size
     */
    public DoublyLinkedList(int sizeIn) {
        for (int index = 0; index < sizeIn; index ++) {
            addToFront(null);
        }
        size = sizeIn;
    }

    public int size() {
        return size;
    }

    /*
     * The following method adds a new node after the BasicLinkedList's tail. This
     * method
     * has one type parameter of generic type 'T' that represents the data to be
     * present in the
     * added node. The only side effect is increasing the BasicLinkedList's size by
     * one.
     * There are no conditions needed for this method to be invoked.
     */
    public void addToEnd(T element) {
        add(size + 1, element);
    }

    /**
     * Adds element to the front of the list
     * 
     * @param data
     */
    public void addToFront(T element) {
        add(0, element);
    }

    /*
     * The following method is a getter for the BasicLinkedList's head's data. If
     * the BasicLinkedList is not
     * empty, the method returns the data (of generic type 'T') contained within the
     * head; otherwise,
     * 'null' is returned. This method has no parameters, side effects, nor
     * conditions needed for
     * this method to be invoked.
     */
    public T getFirst() {
        if (size == 0) {
            throw new UnsupportedOperationException("list is empty! Add elements before retrieving them.");
        }
        return head.data;
    }

    /**
     * The following method is a getter for the BasicLinkedList's tail. If the
     * BasicLinkedList is not empty, the method returns the data (of generic type
     * 'T')
     * contained within the tail; otherwise, 'null' is returned. This method has no
     * parameters, side effects, nor conditions needed for this method to be
     * invoked.
     */
    public T getLast() {
        return tail == null ? null : tail.data;
    }

    /**
     * The following method removes the BasicLinkedList's head and returns its data.
     * The only side effect is decreasing the BasicLinkedList's size by one.
     * This method has no parameters nor conditions needed to be invoked.
     */
    public T removeFirst() {
        // if (isEmpty()) {
        // return null;
        // }
        // T data = head.data;
        // if (size == SINGLE_NODE) {
        // head = null;
        // tail = null;
        // } else {
        // head.next.prev = null;
        // head = head.next;
        // }
        // size--;
        // return data;
        return remove(0);
    }

    /**
     * The following method removes the BasicLinkedList's tail and returns its data.
     * The only side effect is decreasing the BasicLinkedList's size by one.
     * This method has no parameters nor conditions needed to be invoked.
     */
    public T removeLast() {
        // if (isEmpty()) {
        //     return null;
        // }
        // T data = tail.data;
        // if (size == SINGLE_NODE) {
        //     head = null;
        //     tail = null;
        // } else {
        //     tail.prev.next = null;
        //     tail = tail.prev;
        // }
        // size--;
        // return data;
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

    /**
     * Returns an iterator for this list
     */
    @Override
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

    public Iterator<T> reverseIterator() {
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

    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");

        for (Node curr = head; curr != null; curr = curr.next) {
            sb.append("[" + curr.data + "] <--> ");
        }
        return sb.append(" null").toString();
    }

    private boolean isEmpty() {
        return size == EMPTY_LIST;
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

    //--------------------------------------------------------------------------------------------------------------------------------
    // //
    // Additional LinkedList methods that Java LinkedLists have - more so for
    // practice //
    //--------------------------------------------------------------------------------------------------------------------------------

    /**
     * Determines whether this list contains the argument.
     * 
     * @param data
     * @return
     */
    public boolean contains(T data) {
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Returns the index of the first occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    public int indexOf(T data) {
        int index = 0;
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return index;
            }
            index ++;
            curr = curr.next;
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    public int lastIndexOf(T data) {
        int index = size - 1;
        Node curr = tail;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return index;
            }
            index--;
            curr = curr.prev;
        }
        return -1;
    }

    // Replaces the element at the specified position in this list with the
    // specified element.
    public T set(int index, T element) {
        Node curr;
        int currIndex;
        T data;
        if (index < size / 2) {
            currIndex = 0;
            curr = head;
            while (curr != null && currIndex < index) {
                curr = curr.next;
                currIndex ++;
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
            counter ++;
        }
        return curr.data;
    }

    // helper method for cloning
    private Node cloneGet(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot retrieve at index " + index);
        }
        if (index == 0) {
            return head;
        }
        int counter = 0;
        Node curr = head;
        while (counter < index) {
            curr = curr.next;
            counter ++;
        }
        return curr;
    }

    // helper method for cloning
    private void cloneSet(int index, Node replacement) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("cannot retrieve at index " + index);
        }
        if (index == 0) {
            head.next.prev = replacement;
            replacement.next = head.next;
            head = replacement;
        } else if (index == size - 1) {
            replacement.prev = tail.prev;
            tail.prev.next = replacement;
            tail = replacement;
        } else {
            int counter = 0;
            Node curr = head;
            while (counter < index) {
                curr = curr.next;
                counter ++;
            }
            curr.next.prev = replacement;
            replacement.next = curr.next;
            curr.prev.next = replacement;
            replacement.prev = curr.prev;
        }

    }

    // Removes the element at the specified position in this list. Returns the
    // element that was removed from the list.
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
                    currIndex ++;
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

    /**
     * Inserts the specified element at the specified position in this 
     * list. Shifts the element currently at that position (if any) 
     * and any subsequent elements to the right.
     */

    public void add(int index, T element) {
        if (index < 0 || index > size + 1) {
            throw new IndexOutOfBoundsException("can't insert at index " + index);
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
        }
        else {
            Node curr = index < size / 2 ? head : tail;
            int currIndex = index < size / 2 ? 0 : size - 1;

            if (index < size / 2) {
                System.out.println("before halfway");
                while (curr != null && currIndex < index - 1) {
                    curr = curr.next;
                    currIndex ++;
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
        size ++;
    }

    // incomplete... returns a shallow copy of this linked list; nodes nor their
    // data aren't copied
    @Override
    public DoublyLinkedList<T> clone() {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>(size);
        for (int index = 0; index < size; index ++) {
            newList.cloneSet(index, cloneGet(index));
        }
        return newList;
    }
}