package Queue.impl;
import java.util.Iterator;
import impl.DoublyLinkedList;

public class QueueViaDLL<T> {
    final static int CAPACITY = 10;
    private DoublyLinkedList<T> queue;
    private int size;

    public QueueViaDLL() {
        this.queue = new DoublyLinkedList<>();
        this.size = 0;
    }

    public void enqueue(T element) {
        if (this.size == CAPACITY) {
            System.out.println("Queue is full! Can't add elements.");
        } else {
            this.queue.addLast(element);
            this.size ++;
        }
    }

    public T dequeue() {
        if (this.size == 0) {
            System.out.println("Queue is empty! Can't remove elements.");
            return null;
        }
        this.size --;
        return this.queue.removeFirst();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == CAPACITY;
    }

    public T peek() {
        if (this.isEmpty()) {
            System.out.println("The queue is empty!");
            return null;
        }
        return this.queue.getFirst();
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[ ]";
        }
        Iterator<T> iterator = this.queue.iterator();
        String s = "";
        while (iterator.hasNext()) {
            s += "[ " + iterator.next() + " ]" ;
        }
        return s;
    }
}