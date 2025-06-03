package Queue.impl;

// This is my implementation of a integer queue using arrays
public class QueueViaArray {
    
    final static int STARTING_SIZE = 10;
    private static int[] queue;
    private int size;
    private int add_element_index;

    public QueueViaArray() {
        queue = new int[STARTING_SIZE];
        this.size = 0;
        this.add_element_index = 0;
    }
    public boolean isFull() {
        return this.size == queue.length;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    public void enqueue(int element) {
        if (isFull()) {
            System.out.println("MyQueue is full! Cannot add elements.");
        }
        queue[add_element_index] = element;
        add_element_index ++;
        this.size ++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("MyQueue is empty! Cannot remove elements.");
            return -1;
        }
        int dequeued_element = queue[0];
        for (int index = 1; index < this.size; index ++) {
            queue[index - 1] = queue[index];
        }
        add_element_index --;
        this.size --;
        return dequeued_element;
    }

    public int peek() {
        return queue[0];
    }

    public int getSize() {
        return this.size;
    }
    public int getLength() {
        return queue.length;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[ ]";
        }
        String s = "";
        for (int index = 0; index < this.size - 1; index ++) {
            s += "[ " + queue[index] + " ] -- " ;
        }
        s += "[ " + queue[this.size - 1] + " ]" ;
        return s;
    }

    public static void main(String[] args) {
        
    }
}