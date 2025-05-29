package LinkedList.impl;

import java.util.Iterator;
// This class represents an implementation of a Singly Linked List, as was done in CMSC132 with Dr. Emad. There will NOT be a 'tail' reference
public class SinglyLinkedList<T> implements Iterable<T> {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		
		for (int index = 1; index < 6; index ++) {
            list1.addLast(index);
        }
        System.out.println(list1);
        list1.reverse();
        System.out.println(list1);
    }

    private class Node {
        
        private T data;         // the data contained wtihin a Node
        private Node next;   // the "next" Node
    
        private Node(T data) {  // constructor for  
            this.data = data;
            this.next = null;
        }
    }

    private Node head;	//starting node in LinkedList
	private int size;
	final static int EMPTY_LIST = 0, SINGLE_NODE = 1;

    public SinglyLinkedList() {
        this.head = null;
        this.size = EMPTY_LIST;
    }

    public int size() {
        return this.size;
    }

    public T peek() {
        return this.head.data;
    }

    public T peekFirst() {
        return this.head == null ? null : this.head.data;
    }

    /*The following method adds a new node after the BasicLinkedList's tail. This method
	 * has one type parameter of generic type 'T' that represents the data to be present in the
	 * added node. The only side effect is increasing the BasicLinkedList's size by one.
	 * There are no conditions needed for this method to be invoked.
	 */
    public void addLast(T data) {
        Node newNode = new Node(data);
        Node curr = this.head;
        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        this.size ++;

    }

    public void push(T element) {
        this.addFirst(element);
    }

    public T pop() {
        return this.removeFirst();
    }

    /*The following method adds a new node before the BasicLinkedList's head. This method
	 * has one type parameter of generic type 'T' that represents the data to be present in the
	 * added node. The only side effect is increasing the BasicLinkedList's size by one.
	 * There are no conditions needed for this method to be invoked.
	 */
    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size ++;
    }

    public T removeFirst() {
        if (this.head == null) {
            return null;
        }
        T removed = this.head.data;
        this.head = this.head.next;
        this.size --;
        return removed;
    }

    /*The following method is a getter for the BasicLinkedList's head. If the BasicLinkedList is not
	 * empty, the method returns the data (of generic type 'T') contained within the head; otherwise,
	 * 'null' is returned. This method has no parameters, side effects, nor conditions needed for 
	 * this method to be invoked.
	 */
    public T getFirst() {
        return this.isEmpty() ? null : this.head.data;
    }

    /*The following method is a getter for the BasicLinkedList's tail. If the BasicLinkedList is not
	 * empty, the method returns the data (of generic type 'T') contained within the tail; otherwise,
	 * 'null' is returned. This method has no parameters, side effects, nor conditions needed for 
	 * this method to be invoked.
	 */
    public T getLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node curr = this.head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.data;
    }

    /*The following method removes the BasicLinkedList's head and returns its data. 
	 * The only side effect is decreasing the BasicLinkedList's size by one.
	 * This method has no parameters nor conditions needed to be invoked.
	 */
    public T retrieveFirstElement() {
        T data = this.head.data;
        this.head = this.head.next;
        this.size --;
        return data;
    }

    /*The following method removes the BasicLinkedList's tail and returns its data. 
	 * The only side effect is decreasing the BasicLinkedList's size by one.
	 * This method has no parameters nor conditions needed to be invoked.
	 */
    public T retrieveLastElement() {
        Node curr = this.head;
        if (this.isEmpty()) {
            return null;
        } else if (this.head.next == null) {
            this.size --;
            return this.head.data;
        }
        while (curr.next.next != null) {
            curr = curr.next;
        }
        T tailData = curr.next.data;
        curr.next = null;
        this.size --;
        return tailData;
    }

    /*The following method removes every node that contains the type parameter (of generic type 'T')
	 * called "targetData". This method requires no conditions to be invoked. The only side effect is 
	 * decreasing the BasicLinkedList's size by the number of nodes removed. There are no conditions 
	 * needed for this method to be invoked.
	 */
    public SinglyLinkedList<T> removeAllInstances(T targetData) {
        Node curr = this.head, tempHead = this.head;
        boolean nonTargetNode = false;
        
        while (curr != null) {
            if (curr.data.equals(targetData)) {
                if (!nonTargetNode) {
                    if (this.head == curr) {
                        this.head = curr.next;
                        curr = this.head;
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
                this.size --;
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
        return this;
    }

    /*The following overridden method returns an generic type 'T' iterator for the BasicLinkedList. 
	 * This method has no parameters, side effects, nor conditions needed for this method to be invoked.
     * The iterator is returned as an anonymous inner class.
	 */
    @Override
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

    // returns a string representation of a MySinglyLinkedList object for debugging purposes
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return null;
        }
        String s = "";

        for (Node curr = this.head; curr != null; curr = curr.next) {
            s += "[" + curr.data + "] --> ";
        }
        return s + " null";
    }

    // Helper method that determines if the SinglyLinkedList is empty
    private boolean isEmpty() {
        return this.size == EMPTY_LIST;
    }



    // -------------------------------------------------------------------------------------------------------------------------------- //
    //                         Additional LinkedList methods that Java LinkedLists have - more so for practice                          //
    // -------------------------------------------------------------------------------------------------------------------------------- //

    public boolean contains(T data) {
        Node curr = this.head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    public int indexOf(T data) {
        int index = 0;
        Node curr = this.head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return index;
            }
            curr = curr.next;
            index ++;
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
    public int lastIndexOf(T data) {
        int index = 0, lastIndex = -1;
        Node curr = this.head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                lastIndex = index;
            }
            curr = curr.next;
            index ++;
        }
        return lastIndex;
    }

    // Replaces the element at the specified position in this list with the specified element.
    public T set(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        int currIndex = 0;
        Node curr = this.head;
        while (curr != null) {
            curr = curr.next;
            if (currIndex + 1 == index) {
                break;
            }
            currIndex ++;
        }
        T prevElement = curr.data;
        curr.data = element;
        return prevElement;
    }
    // Removes the element at the specified position in this list. Returns the element that was removed from the list.
    public T remove(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        T data;
        if (index == 0) {
            data = this.head.data;
            this.head = this.head.next;
        } else {
            Node curr = this.head;
            int currIndex = 0;
            while (curr != null && currIndex < index - 1) {
                curr = curr.next;
                currIndex ++;
            }
            data = curr.next.data;
            curr.next = curr.next.next;
        }
        this.size --;
        return data;
    }

    /* Inserts the specified element at the specified position in this list. Shifts the element currently at that position 
     * (if any) and any subsequent elements to the right (adds one to their indices).
    */

    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node(element);
        if (index == 0) {
            node.next = this.head;
            this.head = node;
        } else {
            Node curr = this.head;
            int currIndex = 0;
            while (curr != null && currIndex < index - 1) {
                curr = curr.next;
                currIndex ++;
            }
            node.next = curr.next;
            curr.next = node;
        }
        this.size ++;
    }

    public void reverse() {
        Node node = null;

        while (this.head != null) {
            Node temp = this.head.next;
            this.head.next = node;
            node = this.head;
            this.head = temp;
        }
        this.head = node;
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        int index = 0;
        for (Node curr = this.head; curr != null; curr = curr.next) {
            arr[index] = curr.data;
            index ++;
        }
        return arr;
    }

    public static void printArray(Object[] arr) {
        StringBuffer s = new StringBuffer("[");
        for (int index = 0; index < arr.length - 1; index ++) {
            s.append(arr[index] + " ");
        }
        System.out.println(s.append(arr[arr.length - 1]) + "]");
    }
}