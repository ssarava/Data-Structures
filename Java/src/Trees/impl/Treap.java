package Trees.impl;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import LinkedList.impl.SinglyLinkedList;
import Stack.src.MyStack;

/**
 * Supports unique priorities; will modify later to accommodate nodes with the
 * same priority
 */
public class Treap<T extends Comparable<T>> extends MyUniqueBST<T> {

    private static int debugCounter = 0;

    public static void main(String[] args) {
        Treap<Integer> treap = new Treap<>();
        // treap.insert(new Integer[]{50, 40, 60, 30, 45, 47}, new int[]{100, 90, 80,
        // 70, 85, 95});

        treap.insert(new Integer[] { 50, 40, 60, 30, 45 }, new int[] { 100, 90, 80, 70, 85 });
        System.out.println(treap.inorder());
    }

    protected class TreapNode extends MyUniqueBST<T>.BSTNode {

        protected Integer priority;
        protected static final int PRIORITY_UPPER_BOUND = 1000;
        protected TreapNode left, right, parent;

        protected TreapNode(T keyIn, int priorityIn) {
            super(keyIn);
            priority = priorityIn;
            priorities.add(priorityIn);
            parent = null;
        }

        protected TreapNode(T keyIn) {
            super(keyIn);
            do {
                priority = new Random().nextInt(PRIORITY_UPPER_BOUND - 1) + 1;
            } while (!priorities.contains(priority));
            priorities.add(priority);
            parent = null;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Treap.TreapNode)) {
                return false;
            }
            @SuppressWarnings("unchecked")
            TreapNode temp = (TreapNode) other;
            return key.compareTo(temp.key) == 0 && priority == temp.priority;
        }

        @Override
        public String toString() {
            return key.toString() + " | " + priority;
        }
    }

    protected Set<Integer> priorities = new HashSet<>();
    protected TreapNode root;
    protected int size;

    public Treap() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
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
    public boolean insert(Object[] keysIn, int[] prioritiesIn) {
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

    public boolean insert(Object keyIn, int priorityIn) {
        // handle unique priorities
        if (contains(keyIn)) {
            return false;
        }
        if (priorities.contains(priorityIn)) {
            throw new UnsupportedOperationException(
                    "A node with priority " + priorityIn + " already exists in the treap");
        }
        @SuppressWarnings("unchecked")
        TreapNode added = new TreapNode((T) keyIn, priorityIn);
        if (root == null) {
            root = added;
            return true;
        }
        if (!root.key.getClass().isAssignableFrom(keyIn.getClass())) {
            throw new ClassCastException(keyIn.getClass().getName() + " is not compatible with a tree of type "
                    + root.key.getClass().getName());
        }
        priorities.add(priorityIn);

        // bst insert
        if (root == null) {
            root = added;
            return true;
        }
        root = bstInsertHelper(added, root, root);

        if (debugCounter > 5) {
            System.out.println("exiting within 'left_rotate'");
            System.exit(0);
        }

        // handle rotations while the added node isn't the root and has a priority greater than its parent's
        while (added.parent != null && added.priority.compareTo(added.parent.priority) > 0) {
            boolean leftRotation = added.key.compareTo(added.parent.key) > 0;

            // left rotate
            if (leftRotation) {
                System.out.println(
                        "before " + (debugCounter + 1) + "st left rotation:\tadded.parent.key = " + added.parent.key
                                + "\tadded.parent.priority = " + added.parent.priority + "\t" + debugCounter++);
                leftRotateUp(added);
                System.out.println("after " + (debugCounter) + "st left rotation: ");

            // right rotate
            } else {
                break;
            }
        }
        if (debugCounter > 0) {
            System.out.println((debugCounter) + (debugCounter == 1 ? " rotation was " : " rotations were ") + "needed following the insertion of " + keyIn + "\n");
            System.out.println("---------------------------------------------------------------------------------------------");
        }

        return true;
    }

    // incomplete...
    private void leftRotateUp(TreapNode inserted) {
        if (debugCounter > 5) {
            System.out.println("exiting within 'left_rotate'");
            System.exit(0);
        }

        boolean isInsertedNodeChildOfRoot = inserted.parent == root;
        TreapNode tempLeftChild = inserted.left, tempParent = inserted.parent;

        // grandchild-specific
        TreapNode tempGrandparent = inserted.parent != root ? inserted.parent.parent : null;
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
    }

    @Override
    public boolean insert(T key) {
        if (contains(key)) {
            return false;
        }

        // bst insert
        TreapNode added = new TreapNode(key);
        if (root == null) {
            root = added;
            size++;
            return true;
        }
        root = bstInsertHelper(added, root, root);
        size++;
        return true;
    }

    public TreapNode bstInsertHelper(TreapNode toAdd, TreapNode root, TreapNode parent) {
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

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    private String toStringHelper(TreapNode node, String indent) {
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

    private List<T> preorderHelper(TreapNode root, List<T> acc) {
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

    private List<T> inorderHelper(TreapNode root, List<T> acc) {
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

    private List<T> postorderHelper(TreapNode root, List<T> acc) {
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
        MyStack<TreapNode> stack = new MyStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreapNode curr = stack.pop();
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
        Queue<TreapNode> queue = new SinglyLinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreapNode curr = queue.remove();
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

    public void levelorderTraversal() {
        System.out.println(bfs());
    }

    /**
     * @return a preorder iterator
     */
    // public Iterator<T> preorderIterator() {
    // return preorder().iterator();
    // }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        priorities.clear();
    }

    @Override
    public boolean delete(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}