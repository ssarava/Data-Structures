package Heaps.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Supports unique priorities; will modify later to accommodate nodes with the same priority
 */
public class Treap<T extends Comparable<T>> {

    private static int debugCounter = 0;

    public static void main(String[] args) {
        Treap<Integer> treap1 = new Treap<>();
        treap1.insert(50, 100);
        treap1.insert(40, 90);
        treap1.insert(60, 80);
        treap1.insert(30, 70);
        treap1.insert(45, 85);
        // System.out.println(treap1);
        treap1.insert(47, 95);
        // System.out.println(treap1);

        // treap1.view_inorder();
        // treap1.view_preorder();
        // treap1.view_postorder();
    }

    private class Node {

        private T key;
        private Integer priority;
        private static final int PRIORITY_UPPER_BOUND = 1000;
        public Node left, right, parent;

        private Node(T keyIn, int priorityIn) {
            key = keyIn;
            priority = priorityIn;
            priorities.add(priorityIn);
            left = null;
            right = null;
            parent = null;
        }

        private Node(T keyIn) {
            key = keyIn;
            do {
                priority = new Random().nextInt(PRIORITY_UPPER_BOUND - 1) + 1;
            } while (!priorities.contains(priority));
            priorities.add(priority);
            left = null;
            right = null;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Treap.Node)) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Node temp = (Node) other;
            return key.compareTo(temp.key) == 0 && priority == temp.priority;
        }

        @Override
        public String toString() {
            return key.toString() + " | " + priority;
        }
    }

    private Set<Integer> priorities = new HashSet<>();
    public Node root;
    private int size;
    

    public Treap() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(T keyIn, int priorityIn) {
        // handle unique priorities
        if (priorities.contains(priorityIn)) {
            throw new UnsupportedOperationException("a node with priority " + priorityIn + " already exists in the treap");
        }
        priorities.add(priorityIn);

        // bst insert
        Node toAdd = new Node(keyIn, priorityIn);
        root = root == null ? toAdd : bstInsertHelper(toAdd, root, root);

        // find the newly-inserted node
        Node added = root;
        System.out.println(toString());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        while (!added.key.equals(keyIn)) {
            int result = added.key.compareTo(keyIn);
            if (result < 0) {
                // go right
                added = added.right;
            } else if (result > 0) {
                // go left
                added = added.left;
            } else {
                break;
            }
        }

        System.out.println("KEY = " + added.key);
        System.out.println("parent of added key = " + added.parent);
        System.out.println("--------------------------------");
        // handle rotations

        // if the added key is the root, no rotations needed
        if (added.parent == null) {
            return;
        }

        int result = added.key.compareTo(added.parent.key);
        while (result > 0) {
            result = added.key.compareTo(added.parent.key);
            if (result > 0) {
                // left rotate
                System.out.println("added.key = " + added.key + "\tadded.parent.key = " + added.parent.key + "\t\t\t" + debugCounter);
                left_rotate(added);
    
            } else if (result < 0) {
                // right rotate
                break;
            } 

            // 'added' is its own parent, meaning no more rotations are needed
            // else {
            //     break;
            // }
        }
    }   

    // incomplete...
    private void left_rotate(Node inserted) {
        if (debugCounter > 5) {
            System.exit(0);
        }
        
        // if node is the root, don't do anything
        if (inserted == root) {
            System.out.println("node " + inserted.key + " = root" + "\t\t\t\t\t\t" + debugCounter);
            debugCounter ++;
            return;
        }
        
        // if node is child of the root
        if (inserted.parent == root) {
            System.out.println("node.parent = root" + "\t\t\t\t\t" + debugCounter);
            debugCounter ++;
            if (inserted.left != null) {
                inserted.left.parent = root;
            }
            root.right = inserted.left;
            inserted.left = root;
            root.parent = inserted;
            root = inserted;
        }
        // if node is a grand child of the root
        else if (inserted.parent.parent == root) {
            System.out.println("node.parent.parent = root");
            debugCounter ++;
            Node tempParent = inserted.parent;
            root.left = inserted;
            inserted.parent = root;

            if (inserted.left != null) {
                inserted.left.parent = tempParent;
            }
            tempParent.right = inserted.right;
            
            inserted.left = tempParent;
            tempParent.parent = inserted;
        }
    }

    public void insert(T keyIn) {
        Node toAdd = new Node(keyIn);
        root = root == null ? toAdd : bstInsertHelper(toAdd, root, root);
        size ++;
    }

    public Node bstInsertHelper(Node toAdd, Node root, Node parent) {
        if (root == null) {
            // root = toAdd;
            toAdd.parent = parent;
            return toAdd;
        }
        int result = root.key.compareTo(toAdd.key);
        if (result < 0) {
            // go right
            root.right = bstInsertHelper(toAdd, root.right, root);
        } else if (result > 0) {
            // go left
            root.left = bstInsertHelper(toAdd, root.left, root);
        } 
        // uncomment this if duplicates are allowed
        // else {
        //     root.parent = root;
        //     return root;
        // }
        
        return root;
    }

    public boolean contains(T element) {
        Node curr = root;
        while (curr != null) {
            int result = curr.key.compareTo(element);
            if (result > 0) {
                // go left
                curr = curr.left;
            } else if (result < 0) {
                // go right
                curr = curr.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void view_preorder() {
        List<T> list = preorder();
        StringBuilder sb = new StringBuilder("[");
        for (T data: list) {
            sb.append(data.toString() + " ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public List<T> preorder() {
        List<T> preorder = new LinkedList<T>();
        preorderHelper(root, preorder);
        return preorder;
    }

    private void preorderHelper(Node root, List<T> acc) {
        if (root != null) {
            acc.add(root.key);
            preorderHelper(root.left, acc);
            preorderHelper(root.right, acc);
        }
    }

    public void view_inorder() {
        List<T> list = inorder();
        StringBuilder sb = new StringBuilder("[");
        for (T data: list) {
            sb.append(data.toString() + " ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public List<T> inorder() {
        List<T> inorder = new LinkedList<T>();
        inorderHelper(root, inorder);
        return inorder;
    }

    private void inorderHelper(Node root, List<T> acc) {
        if (root != null) {
            inorderHelper(root.left, acc);
            acc.add(root.key);
            inorderHelper(root.right, acc);
        }
    }

    public void view_postorder() {
        List<T> list = postorder();
        StringBuilder sb = new StringBuilder("[");
        for (T data: list) {
            sb.append(data.toString() + " ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public List<T> postorder() {
        List<T> postorder = new LinkedList<T>();
        postorderHelper(root, postorder);
        return postorder;
    }

    private void postorderHelper(Node root, List<T> acc) {
        if (root != null) {
            postorderHelper(root.left, acc);
            postorderHelper(root.right, acc);
            acc.add(root.key);
        }
    }

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    public String toStringHelper(Node node, String indent) {
        if (node == null) {
            return "";
        }
        return indent + "k: " + node.key + "\n" + indent + "p: " + node.priority + "\n" + indent + "l:" + 
        (node.left == null ? " null" : "\n") + toStringHelper(node.left, indent + "    ") + "\n" + indent + 
        "r:" + (node.right == null ? " null" : "\n") + toStringHelper(node.right, indent + "    ");
    }
}