package Trees.impl.BinarySearchTrees;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import impl.DoublyLinkedList;
import impl.SinglyLinkedList;
import Stacks.src.MyStack;
import Trees.MyTree;

/**
 * My implementation of a binary search tree that does not support duplicates.
 * Some flattening/unflattening methods need to be implemented
 */
public class MyBST<T extends Comparable<T>> implements MyTree<T> {

    protected class Node {

        protected T key;
        protected Node left, right;
        protected int selfCount;

        protected Node(T keyIn) {
            key = keyIn;
            left = null;
            right = null;
            selfCount = 1;
        }

        protected Node(Node other) {
            this(other.key);
            left = other.left;
            right = other.right;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            @SuppressWarnings("unchecked")
            Node temp = (Node) other;
            return key.compareTo(temp.key) == 0 && selfCount == temp.selfCount;
        }

        @Override
        public String toString() {
            return toStringHelper(this, "");
        }

        private String toStringHelper(Node node, String indent) {
            if (node == null) {
                return "";
            }
            return indent + "k: " + node.key + "\n" + indent + "c: " + node.selfCount + "\n" + indent + "l:" + (node.left == null ? " null" : "\n") +
                    toStringHelper(node.left, indent + "    ") + "\n" + indent + "r:" +
                    (node.right == null ? " null" : "\n") + toStringHelper(node.right, indent + "    ");
        }
    }

    public Node root;
    private int size;

    public MyBST() {
        root = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(Object key) {
        T temp = (T) key;
        if (contains(key)) {
            Node curr = root;
            while (!curr.key.equals(key)) {
                int result = curr.key.compareTo(temp);
                if (result < 0) {
                    // go right
                    curr = curr.right;
                } else if (result > 0) {
                    // go left
                    curr = curr.left;
                } else {
                    break;
                }
            }
            curr.selfCount++;
        } else {
            root = insertHelper(root, temp);
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (root == null) {
            return false;
        }
        if (!root.key.getClass().isAssignableFrom(o.getClass())) {
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a deque of type "
                    + root.key.getClass().getName());
        }
        Node curr = root;
        while (curr != null) {
            @SuppressWarnings("unchecked")
            int result = curr.key.compareTo((T) o);
            if (result < 0) {
                // go right
                curr = curr.right;
            } else if (result > 0) {
                // go left
                curr = curr.left;
            } else {
                return true;
            }
        }
        return false;
    }

    private Node insertHelper(Node root, T toAdd) {
        if (root == null) {
            return new Node(toAdd);
        }
        int result = root.key.compareTo(toAdd);
        if (result < 0) {
            root.right = insertHelper(root.right, toAdd);
        } else if (result > 0) {
            root.left = insertHelper(root.left, toAdd);
        } else {
            return root;
        }
        return root;
    }

    @Override
    public boolean delete(Object o) {
        if (!contains(o)) {
            return false;
        } else {
            Node curr = root;
            while (!curr.key.equals(o)) {
                @SuppressWarnings("unchecked")
                int result = curr.key.compareTo((T) o);
                if (result < 0) {
                    // go right
                    curr = curr.right;
                } else if (result > 0) {
                    // go left
                    curr = curr.left;
                } else {
                    break;
                }
            }
            if (curr.selfCount > 1) {
                curr.selfCount--;
            } else {
                root = delete_helper(root, o);
            }
            size--;
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    private Node delete_helper(Node root, Object element) {
        if (root == null) {
            return root;
        }
        T temp = (T) element;
        if (root.key.compareTo(temp) < 0) {
            // go right
            root.right = delete_helper(root.right, element);
        } else if (root.key.compareTo(temp) > 0) {
            // go left
            root.left = delete_helper(root.left, element);

        // if root is found
        } else {

            // only right child is present OR no children are present
            if (root.left == null) {
                return root.right;
            }

            // only left child is present
            if (root.right == null) {
                return root.left;
            }

            // both children are present
            T ios = inorderSuccessor(root);
            root.key = ios;
            root.right = delete_helper(root.right, ios);
        }
        return root;
    }

    private T inorderSuccessor(Node node) {
        if (size == 0) {
            return null;
        }
        Node curr = node.right;
        // go right once, then left as far as possible
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr.key;
    }

    public List<T> getLeaves() {
        List<T> acc = new SinglyLinkedList<>();
        getLeavesHelper(root, acc);
        return acc;
    }

    public void getLeavesHelper(Node root, List<T> acc) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                acc.add(root.key);
            }
            getLeavesHelper(root.left, acc);
            getLeavesHelper(root.right, acc);
        }
    }

    protected boolean isLeaf(T key) {
        return isLeafHelper(root, key);
    }

    private boolean isLeafHelper(Node root, T key) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && key.compareTo(root.key) == 0) {
            return true;
        }
        return isLeafHelper(root.left, key) || isLeafHelper(root.right, key);
    }

    /**
     * Flattens the tree into a LinkedList, in place, following a pre-order
     * traversal.
     */
    public void preorder_flatten() {
        Node curr = root;
        while (curr != null) {
            if (curr.left != null) {
                Node predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    /**
     * Unflattens the LinkedList-like tree, in place, following an pre-order
     * traversal.
     */
    public void preorder_unflatten() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented!");
    }

    /**
     * Flattens the tree into a LinkedList, in place, following an in-order
     * traversal. incomplete
     */
    public void inorder_flatten() {
        // TODO
        // throw new UnsupportedOperationException("Unimplemented!");
        Node curr = root;
        while (curr != null) {
            if (curr.right != null) {
                Node successor = curr.right;
                while (successor.left != null) {
                    successor = successor.right;
                }
                curr.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    /**
     * Flattens the tree into a LinkedList, in place, following a post-order
     * traversal.
     */
    public void postorder_flatten() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented!");
    }

    /**
     * Flattens the tree into a LinkedList, in place, following a pre-order
     * traversal.
     */
    // private void initial_flatten() {
    // List<Node> inorder = new SinglyLinkedList<>();
    // flatten_helper(root, inorder);
    // for (Node node: inorder) {
    // node.left = null;
    // node.right = null;
    // }
    // for (int index = 0; index < inorder.size() - 1; index ++) {
    // inorder.get(index).right = inorder.get(index + 1);
    // }
    // root = inorder.get(0);
    // }

    // private void flatten_helper(Node root, List<Node> acc) {
    // if (root != null) {
    // acc.add(root);
    // flatten_helper(root.left, acc);
    // flatten_helper(root.right, acc);
    // }
    // }

    /**
     * Returns a List containing this Tree's elements in sorted order.
     */
    public List<T> inorder() {
        List<T> inorder = new SinglyLinkedList<>();
        inorder_helper(root, inorder);
        return inorder;
    }

    private void inorder_helper(Node root, List<T> acc) {
        if (root != null) {
            inorder_helper(root.left, acc);
            acc.add(root.key);
            inorder_helper(root.right, acc);
        }
    }

    public List<T> preorder() {
        List<T> preorder = new SinglyLinkedList<>();
        preorder_helper(root, preorder);
        return preorder;
    }

    private void preorder_helper(Node root, List<T> acc) {
        if (root != null) {
            acc.add(root.key);
            preorder_helper(root.left, acc);
            preorder_helper(root.right, acc);
        }
    }

    public List<T> postorder() {
        List<T> postorder = new SinglyLinkedList<>();
        postorder_helper(root, postorder);
        return postorder;
    }

    private void postorder_helper(Node root, List<T> acc) {
        if (root != null) {
            postorder_helper(root.left, acc);
            postorder_helper(root.right, acc);
            acc.add(root.key);
        }
    }

    public List<T> dfs() {
        List<T> acc = new SinglyLinkedList<>();
        MyStack<Node> stack = new MyStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            acc.add(curr.key);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return acc;
    }

    public List<T> bfs() {
        List<T> acc = new SinglyLinkedList<>();
        Queue<Node> queue = new SinglyLinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            acc.add(curr.key);
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
        return acc;
    }

    public List<T> levelorder() {
        return bfs();
    }

    /**
     * @return a preorder iterator
     */
    public Iterator<T> preorderIterator() {
        List<T> list = preorder();
        return list.iterator();
    }

    /**
     * @return an inorder iterator
     */
    @Override
    public Iterator<T> iterator() {
        return inorder().iterator();
    }

    /**
     * @return a postorder iterator
     */
    public Iterator<T> postorderIterator() {
        List<T> list = postorder();
        return list.iterator();
    }

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    private String toStringHelper(Node node, String indent) {
        if (node == null) {
            return "";
        }
        return indent + "key:   " + node.key + "\n" + indent + "count: " + node.selfCount + "\n" + indent + "l:" + (node.left == null ? " null" : "\n") +
                toStringHelper(node.left, indent + "    ") + "\n" + indent + "r:" +
                (node.right == null ? " null" : "\n") + toStringHelper(node.right, indent + "    ");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Iterator<T> iter = iterator();
        int index = 0;
        Object[] arr = new Object[size];
        while (iter.hasNext()) {
            arr[index++] = iter.next();
        }
        return arr;
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length != size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Object[] pointer = a; // needed to resolve compiler type conversion conflict
        Iterator<T> iter = (Iterator<T>) iterator();
        int index = 0;
        while (iter.hasNext()) {
            pointer[index++] = iter.next();
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        insert(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return delete(o);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (T element : c) {
            insert(element);
        }
        return size == initialSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (Object element : c) {
            remove(element);
        }
        return size == initialSize;
    }

    @Override
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

    @Override
    public boolean retainAll(Collection<?> c) {
        if (this == c) {
            return false;
        }
        int initialSize = 0;
        List<T> toRemove = new DoublyLinkedList<>();
        for (T element : this) {
            if (!c.contains(element)) {
                toRemove.add(element);
            }
        }
        for (T element : toRemove) {
            remove(element);
        }
        return size == initialSize;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}