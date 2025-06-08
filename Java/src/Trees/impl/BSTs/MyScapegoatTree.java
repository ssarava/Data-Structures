package Trees.impl.BSTs;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Queue;
import Lists.impl.DoublyLinkedList;
import Lists.impl.SinglyLinkedList;
import Stacks.src.MyStack;

public class MyScapegoatTree<T extends Comparable<T>> extends MyBST<T> {

    public static void main(String[] args) {
        MyScapegoatTree<Integer> tree1 = new MyScapegoatTree<>();
        // Integer[] keys = new Integer[] {20, 10, 30, 40, 50, 60, 70};
        Integer[] keys = new Integer[] {3, 2, 8, 5, 10, 11, 12, 13};
        tree1.insert(keys);
    }

    protected class Node extends MyBST<T>.Node {

        protected Node left, right, parent;
        private int depth;
        private int size; // size of the subtree rooted at this node

        protected Node(T keyIn) {
            super(keyIn);
            depth = 0;
            size = 1;
            parent = null;
        }

        protected Node(Node other) {
            super(other);
            depth = 0;
            size = 1;
            parent = null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyScapegoatTree.Node)) {
                return false;
            }
            Node temp = (Node) other;
            return key.compareTo(temp.key) == 0;
        }
    }

    public static final double ALPHA = 2.0 / 3.0;
    public Node root;
    private int m; // max # of nodes that have been in the tree since creation OR since the last
                   // total rebuild due to deletion

    public MyScapegoatTree() {
        super();
        m = 0;
    }

    public MyScapegoatTree(MyScapegoatTree<T> other) {
        this();
        for (T element: other.inorder()) {
            insert(element);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot add a null reference to the tree.");
        }
        if (root == null) {
            return false;
        }
        if (!root.key.getClass().isAssignableFrom(o.getClass())) {
            throw new ClassCastException(o.getClass().getName()
                    + " is not compatible with a tree of type " + root.key.getClass().getName());
        }
        Node curr = root;
        while (curr != null) {
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

    @Override
    public boolean insert(Object[] keysIn) {
        int initialSize = size;
        for (Object key : keysIn) {
            insert(key);
        }
        return size == initialSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(Object keyIn) {
        if (contains(keyIn)) {
            return false;
        }
        Node added = new Node((T) keyIn);

        // bst insert
        root = bstInsertHelper(added, root, root);
        size++;
        m++;

        if (depthViolation(added)) {
            // find scapegoat
            Node scapegoat = findScapegoat(added), parent = scapegoat.parent;

            // isolate the scapegoat (and its subtree) from its parent
            if (parent.right == scapegoat) {
                parent.right = null;
            } else {
                parent.left = null;
            }

            boolean isRightChild = scapegoat.parent.right == scapegoat;

            // List<Node> inorder = getSubtreeInorderNodes(scapegoat, new SinglyLinkedList<>());
            List<T> inorder = inorderHelper(scapegoat, new SinglyLinkedList<>());
            if (isRightChild) {
                Node rightSubtree = rebuildSubtree(inorder);
                rightSubtree.parent = parent;
                parent.right = rightSubtree;
            } else {
                Node leftSubtree = rebuildSubtree(inorder);
                leftSubtree.parent = parent;
                parent.left = leftSubtree;
            }

            // update sizes and parents
            updateNodeParams(root, null, 0);

        }
        return true;
    }

    private Node rebuildSubtree(List<T> inorder) {
        int k = inorder.size();
        if (k == 0) {
            return null;
        }
        int splitIndex = k / 2;
        Node parent = new Node(inorder.get(splitIndex));

        // build left list
        List<T> leftList = new SinglyLinkedList<>();
        for (int index = 0; index <= splitIndex - 1; index++) {
            leftList.add(inorder.get(index));
        }

        // build right list
        List<T> rightList = new SinglyLinkedList<>();
        for (int index = splitIndex + 1; index <= k - 1; index++) {
            rightList.add(inorder.get(index));
        }

        parent.left = rebuildSubtree(leftList);
        parent.right = rebuildSubtree(rightList);
        return parent;
    }

    private Node bstInsertHelper(Node toAdd, Node root, Node parent) {
        if (root == null) {
            toAdd.parent = parent;
            return toAdd;
        }

        toAdd.depth++;
        int result = root.key.compareTo(toAdd.key);
        if (result < 0) {
            // go right
            root.size++;
            root.right = bstInsertHelper(toAdd, root.right, root);
        } else if (result > 0) {
            // go left
            root.size++;
            root.left = bstInsertHelper(toAdd, root.left, root);
        }
        return root;
    }

    private boolean depthViolation(Node added) {
        double logs = Math.log(size) / Math.log(1 / ALPHA);
        return added.depth > logs;
    }

    private Node findScapegoat(Node added) {
        Node parent = added.parent, child = added;
        while (((double) child.size / (double) parent.size) <= ALPHA) {
            child = parent;
            parent = parent.parent;
        }
        return parent;
    }

    // update node sizes, parents, and depths following insertions and deletions
    private void updateNodeParams(Node node, Node parent, int depth) {
        if (node != null) {
            node.size = sizeOf(node);
            node.parent = parent;
            node.depth = depth;
            updateNodeParams(node.left, node, depth + 1);
            updateNodeParams(node.right, node, depth + 1);
        }
    }

    private int sizeOf(Node node) {
        return node == null ? 0 : 1 + sizeOf(node.left) + sizeOf(node.right);
    }

    @Override
    public boolean delete(Object element) {
        if (!contains(element)) {
            return false;
        }
        root = delete_helper(root, element);
        size--;

        // check if rebuilding the entire tree is needed
        if (size < ALPHA * m) {
            // rebuild
            List<T> inorder = inorderHelper(root, new SinglyLinkedList<>());
            root = rebuildSubtree(inorder);
            m = size;
        }
        updateNodeParams(root, null, 0);
        return true;
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

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    private String toStringHelper(Node node, String indent) {
        return node == null ? ""
                : indent + "k: " + node.key + "\n" + indent + "s: " + node.size + "\n" + indent
                        + "d: " + node.depth + "\n" + indent + "l:"
                        + (node.left == null ? " null" : "\n")
                        + toStringHelper(node.left, indent + "    ") + "\n" + indent + "r:"
                        + (node.right == null ? " null" : "\n")
                        + toStringHelper(node.right, indent + "    ");
    }

    @Override
    public boolean add(T e) {
        return insert(e);
    }

    @Override
    public boolean remove(Object o) {
        return delete(o);
    }

    @Override
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

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException(
                    "Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (T element : c) {
            add(element);
        }
        return size == initialSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
        if (this == c) {
            throw new ConcurrentModificationException(
                    "Removing elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (Object element : c) {
            remove(element);
        }
        return size == initialSize;
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

    @Override
    public List<T> levelorder() {
        return bfs();
    }

    @Override
    public List<T> inorder() {
        return inorderHelper(root, new SinglyLinkedList<>());
    }

    private List<T> inorderHelper(Node node, List<T> acc) {
        if (node != null) {
            inorderHelper(node.left, acc);
            acc.add(node.key);
            inorderHelper(node.right, acc);
        }
        return acc;
    }

    @Override
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

    @Override
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

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MyScapegoatTree)) {
            return false;
        }
        MyScapegoatTree<T> temp = (MyScapegoatTree<T>) other;
        List<T> inorder1 = inorder(), inorder2 = temp.inorder();
        return inorder1.equals(inorder2);
    }
}
