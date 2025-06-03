package Queue.impl;

import java.util.Stack;
import java.util.ListIterator;

public class QueueViaStack<T> {
    
    private Stack<T> stack;

    public QueueViaStack() {
        this.stack = new Stack<>();
    }

    public static void main(String[] args) {
        QueueViaStack<Integer> queue1 = new QueueViaStack<>();
        for (int i = 1; i <= 256; i *= 2) {
            queue1.push(i);
            // System.out.println(queue1.toString());
            // System.out.println();
        }
        System.out.println(queue1.toString());
        queue1.poll();
        System.out.println(queue1.toString());
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void push(T x) { 
        Stack<T> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        temp.push(x);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }        
    }

    public T poll() {
        return !this.stack.isEmpty() ? null : this.stack.pop();
    }

    public T peek() {
        return this.stack.peek();
    }

    @Override
    public String toString() {
        if (this.stack.isEmpty()) {
            return "top [] end";
        }
        StringBuilder sb = new StringBuilder("");
        ListIterator<T> iter = this.stack.listIterator();
        while (iter.hasNext()) {
            sb.append(iter.next() + "\n");
        }
        return sb.toString();
    }
}