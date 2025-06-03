package Trees.impl.BinarySearchTrees;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import Sets.impl.MyCAHashSet;
import Stacks.src.MyStack;
import impl.SinglyLinkedList;

/**
 * A BST with max heap properties. Keys and priorities are unique.
 */
public class MyUniqueTreap<T extends Comparable<T>> extends MyUniqueBST<T> {

    protected class Node extends MyUniqueBST<T>.Node {

        protected Integer priority;
        protected static final int PRIORITY_UPPER_BOUND = 1000;
        protected Node left, right, parent;

        protected Node(T keyIn, int priorityIn) {
            super(keyIn);
            priority = priorityIn;
            priorities.add(priorityIn);
            parent = null;
        }

        protected Node(T keyIn) {
            super(keyIn);
            do {
                priority = new Random().nextInt(PRIORITY_UPPER_BOUND - 1) + 1;
            } while (!priorities.contains(priority));
            priorities.add(priority);
            parent = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyUniqueTreap.Node)) {
                return false;
            }
            Node temp = (Node) other;
            return super.key.compareTo(temp.key) == 0 && priority == temp.priority;
        }

        public Node deepClone() {
            return new Node(key, priority);
        }

        @Override
        public String toString() {
            return super.toString() + " | " + priority;
        }
    }

    protected Node root;
    protected int size;
    public Set<Integer> priorities;

    public MyUniqueTreap() {
        root = null;
        size = 0;
        priorities = new MyCAHashSet<>();
    }

    public MyUniqueTreap(MyUniqueTreap<T> treapIn) {
        root = null;
        size = 0;
        priorities = new MyCAHashSet<>();
        for (Node node: treapIn.getNodes()) {
            insert(node.key, node.priority);
        }
    }

    private List<Node> getNodes() {
        return getNodesHelper(root, new SinglyLinkedList<>());
    }

    private List<Node> getNodesHelper(Node node, List<Node> acc) {
        if (node != null) {
            getNodesHelper(node.left, acc);
            acc.add(node);
            getNodesHelper(node.right, acc);
        }
        return acc;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
            throw new ClassCastException(o.getClass().getName() + " is not compatible with a tree of type "
                    + root.key.getClass().getName());
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

    /**
     * Inserts each element in {@code keysIn} with the same-index priority in
     * {@code prioritiesIn}, starting at index 0.
     * 
     * Returns {@code true} if for each element in {@code keysIn}, inserting
     * the element with the same-index priority in {@code priorities} changes
     * the structure of the tree. Returns {@code false} otherwise.
     * 
     * @throws UnsupportedOperationException if {@code keysIn} and
     *                                       {@code prioritiesIn} are of unequal
     *                                       length.
     */
    public boolean insert(Object[] keysIn, Integer[] prioritiesIn) {
        if (keysIn.length != prioritiesIn.length) {
            throw new UnsupportedOperationException(
                    "For insertion, keys array and priorities array must be the same length!");
        }
        for (int index = 0; index < keysIn.length; index++) {
            if (!insert(keysIn[index], prioritiesIn[index])) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean insert(Object keyIn, int priorityIn) {
        // handle unique priorities
        if (contains(keyIn)) {
            return false;
        }

        if (priorities.contains(priorityIn)) {
            throw new UnsupportedOperationException("A node with priority " + priorityIn + " already exists!");
        }

        Node added = new Node((T) keyIn, priorityIn);
        if (root == null) {
            root = added;
            size ++;
            return true;
        }
        if (!root.key.getClass().isAssignableFrom(keyIn.getClass())) {
            throw new ClassCastException(keyIn.getClass().getName() + " is not compatible with a tree of type "
                    + root.key.getClass().getName());
        }
        priorities.add(priorityIn);

        // bst insert
        root = bstInsertHelper(added, root, root);

        // handle rotations while the added node isn't the root and has a priority greater than its parent's
        while (added.parent != null && added.priority.compareTo(added.parent.priority) > 0) {
            boolean leftRotation = added.key.compareTo(added.parent.key) > 0;
            rotate(added, leftRotation);
        }
        size ++;
        return true;
    }

    private void rotate(Node inserted, boolean leftRotation) {
        // check depth of the inserted node relative to the root (applies to either rotation)
        boolean isInsertedNodeChildOfRoot = inserted.parent == root;
        Node tempParent = inserted.parent;

        // grandchild specific (applies to either rotation)
        Node tempGrandparent = inserted.parent != root ? inserted.parent.parent : null;

        // if left rotation needed:
        if (leftRotation) {
            Node tempLeftChild = inserted.left;

            // grandchild-specific
            boolean rightSubTree = inserted.parent != root ? inserted.key.compareTo(tempGrandparent.key) > 0: false;

            // isolate the inserted node
            inserted.parent = null;
            inserted.left = null;
            if (tempLeftChild != null) {
                tempLeftChild.parent = tempParent;
            }
            tempParent.right = tempLeftChild;

            // place inserted node correctly
            inserted.left = tempParent;
            tempParent.parent = inserted;

            // guaranteed to be the right child of the root
            if (isInsertedNodeChildOfRoot) {
                root = inserted;
            } else {
                if (rightSubTree) {
                    tempGrandparent.right = inserted;
                } else {
                    tempGrandparent.left = inserted;
                }
                inserted.parent = tempGrandparent;
            }
        } else {
            Node tempRightChild = inserted.right;

            // grandchild-specific
            boolean leftSubTree = inserted.parent != root ? inserted.key.compareTo(tempGrandparent.key) > 0: false;

            // isolate the inserted node
            inserted.parent = null;
            inserted.right = null;
            if (tempRightChild != null) {
                tempRightChild.parent = tempParent;
            }
            tempParent.left = tempRightChild;

            // place inserted node correctly
            inserted.right = tempParent;
            tempParent.parent = inserted;

            // guaranteed to be the right child of the root
            if (isInsertedNodeChildOfRoot) {
                root = inserted;
            } else {
                if (leftSubTree) {
                    tempGrandparent.right = inserted;
                } else {
                    tempGrandparent.left = inserted;
                }
                inserted.parent = tempGrandparent;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(Object keyIn) {
        System.out.println("insert line 259"); 
        return insert(keyIn, new Node((T) keyIn).priority);
    }

    public Node bstInsertHelper(Node toAdd, Node root, Node parent) {
        if (root == null) {
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

        return root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean delete(Object o) {
        if (!contains(o)) {
            return false;
        }
        Node toDelete = root;
        T temp = (T) o;
        while (toDelete.key.compareTo(temp) != 0) {
            int result = toDelete.key.compareTo(temp);
            if (result < 0) {
                // go right
                toDelete = toDelete.right;
            } else if (result > 0) {
                // go left
                toDelete = toDelete.left;
            } else {
                break;
            }
        }

        // set priority to "-infinity" (in this case, '-1' since priorities are non-negative)
        priorities.remove(toDelete.priority);
        toDelete.priority = -1;
        
        // handle rotations while the added node isn't a leaf
        while (toDelete.left != null && toDelete.right != null) {

            // must perform left rotation on right child since there's no left child
            if (toDelete.left == null && toDelete.right != null) {
                rotate(toDelete.right, true);
            }
            // must perform right rotation on left child since there's no right child
            else if (toDelete.left != null && toDelete.right == null) {
                rotate(toDelete.left, false);
            }
            // perform appropriate rotation based on which child has higher priority
            else {
                boolean rightRotation = toDelete.left.key.compareTo(toDelete.right.key) > 0;
                Node rotationTarget = rightRotation ? toDelete.left : toDelete.right;
                rotate(rotationTarget, !rightRotation);
            }
        }

        // chop off the leaf
        Node tempParent = toDelete.parent;
        toDelete.parent = null;
        if (tempParent != null) {
            if (tempParent.left == toDelete) {
                tempParent.left = null;
            } else {
                tempParent.right = null;
            }
        }

        size--;

        // if the tree is empty, root should be null
        if (size == 0) {
            root = null;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyUniqueTreap)) {
            return false;
        }
        MyUniqueTreap<T> other = (MyUniqueTreap<T>) o;
        List<Node> theseNodes = getNodes(), otherNodes = other.getNodes();
        return theseNodes.equals(otherNodes);
        
    }

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    private String toStringHelper(Node node, String indent) {
        if (node == null) {
            return "";
        }
        return indent + "k: " + node.key + "\n" + indent + "p: " + node.priority + "\n" + indent + "l:" +
                (node.left == null ? " null" : "\n") + toStringHelper(node.left, indent + "    ") + "\n" + indent +
                "r:" + (node.right == null ? " null" : "\n") + toStringHelper(node.right, indent + "    ");
    }

    /**
     * Returns a List containing this Tree's elements following a preorder
     * traversal.
     */
    @Override
    public List<T> preorder() {
        return preorderHelper(root, new SinglyLinkedList<>());
    }

    private List<T> preorderHelper(Node root, List<T> acc) {
        if (root != null) {
            acc.add(root.key);
            preorderHelper(root.left, acc);
            preorderHelper(root.right, acc);
        }
        return acc;
    }

    /**
     * Returns a List containing this Tree's elements in sorted order.
     */
    @Override
    public List<T> inorder() {
        return inorderHelper(root, new SinglyLinkedList<>());
    }

    private List<T> inorderHelper(Node root, List<T> acc) {
        if (root != null) {
            inorderHelper(root.left, acc);
            acc.add(root.key);
            inorderHelper(root.right, acc);
        }
        return acc;
    }

    /**
     * Returns a List containing this Tree's elements following a postorder
     * traversal.
     */
    @Override
    public List<T> postorder() {
        return postorderHelper(root, new SinglyLinkedList<>());
    }

    private List<T> postorderHelper(Node root, List<T> acc) {
        if (root != null) {
            postorderHelper(root.left, acc);
            postorderHelper(root.right, acc);
            acc.add(root.key);
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
    public List<T> levelorder() {
        return bfs();
    }

    @Override
    public Iterator<T> preorderIterator() {
        return preorder().iterator();
    }

    @Override
    public Iterator<T> iterator() {
        return inorder().iterator();
    }

    @Override
    public Iterator<T> postorderIterator() {
        return postorder().iterator();
    }

    @Override
    public boolean add(T element) {
        return insert(element);
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
            throw new ConcurrentModificationException("Removing elements to this list, from this list, isn't allowed.");
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
        for (Object o : c) {
            if (!contains(o)) {
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
        MyUniqueTreap<T> toRemove = new MyUniqueTreap<>();
        for (T element: this) {
            if (!c.contains(element)) {
                toRemove.add(element);
            }
        }
        for (T element: toRemove) {
            remove(element);
        }
        return size == initialSize;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        priorities.clear();
    }

    // for some reason a constructor given a pre-existing MyTreapSet wouldn't work, so this is a substitute
    public MyUniqueTreap<T> deepClone() {
        MyUniqueTreap<T> clone = new MyUniqueTreap<T>();
        for (Node node: getNodes()) {
            Node clonedNode = node.deepClone();
            clone.insert(clonedNode.key, clonedNode.priority);
        }
        return clone;
    }
}