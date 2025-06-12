package Trees.impl.BSTs;

import java.util.List;
import java.util.Queue;
import Lists.impl.SinglyLinkedList;
import Stacks.src.MyStack;

public class MyAVLTree<T extends Comparable<T>> extends MyBST<T> {

    public static void main(String[] args) {
        MyAVLTree<Integer> tree1 = new MyAVLTree<>();
        Integer[] keys;

        // LL heavy
        // keys = new Integer[] {10, 5, 20, 3, 7, 2};
        // keys = new Integer[] {10, 8, 6};
        // keys = new Integer[] {10, 8, 20, 6, 4};
        // keys = new Integer[] {10, 8, 20, 17, 13};
        // keys = new Integer[] {10, 8, 20, 30, 6, 9, 4, 2};

        // RR heavy
        // keys = new Integer[] {10, 5, 20, 15, 25, 30};
        // keys = new Integer[] {10, 15, 20};
        // keys = new Integer[] {10, 5, 15, 20, 25};
        // keys = new Integer[] {10, 5, 15, 13, 20, 25};
        // keys = new Integer[] {10, 5, 15, 7, 9};
        // keys = new Integer[] {10, 5, 15, 2, 12, 20, 25, 30};

        // LR heavy
        // keys = new Integer[] {50, 10, 30};
        // keys = new Integer[] {50, 10, 60, 5, 7};
        // keys = new Integer[] {10, 5, 20, 3, 7, 21, 1, 6, 8, 9};

        keys = new Integer[] {40, 20, 60, 10, 30, 50};
        tree1.insert(keys);
        tree1.delete(20);
        System.out.println(tree1);
    }

    protected class Node extends MyBST<T>.Node {
        protected Node left, right, parent;

        protected Node(T keyIn, int priorityIn) {
            super(keyIn);
            parent = null;
        }

        protected Node(T keyIn) {
            super(keyIn);
            parent = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyTreap.Node)) {
                return false;
            }
            Node temp = (Node) other;
            return super.key.compareTo(temp.key) == 0;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private int size;
    public Node root;

    public MyAVLTree() {
        size = 0;
        root = null;
    }

    public static void printLine() {
        System.out.println("----------------------------------------------------");
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

    public boolean insert(Object[] keysIn) {
        for (int index = 0; index < keysIn.length; index++) {
            // weird logic...
            if (!insert(keysIn[index])) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(Object keyIn) {
        if (contains(keyIn)) {
            return false;
        }
        Node added = new Node((T) keyIn);
        root = insertHelper(added, root, null);

        // chek the balance of the tree, starting at the inserted node
        Node unbalanced = added;
        boolean[] lefts = new boolean[2];

        // move up the tree until a node such that Math.abs(balance) > 1 is found
        while (unbalanced != null && Math.abs(getBalance(unbalanced)) <= 1) {
            unbalanced = unbalanced.parent;
        }
        if (unbalanced != null) {
            // System.out.println("node " + unbalanced.key + " has a balance of "
            //         + getBalance(unbalanced) + "\tadded = " + added.key);
        } else {
            // System.out.println("all nodes in the tree have good balance");
            return true;
        }
        Node temp = unbalanced;

        // determine whether an LL, RR, LR, or RL rotation is needed
        for (int index = 0; index < 2; index++) {
            int result = added.key.compareTo(temp.key);
            lefts[index] = result < 0;
            temp = result < 0 ? temp.left : temp.right;
        }

        if (lefts[0] && lefts[1]) {
            System.out.println("LL heavy");
            rotate(unbalanced.left, false);
        } else if (!lefts[0] && !lefts[1]) {
            System.out.println("RR heavy");
            rotate(unbalanced.right, true);
        } else if (lefts[0] && !lefts[1]) {
            System.out.println("inserted node is in the right st of the left st of "
                    + unbalanced.key + "\nLR heavy");
            rotate(unbalanced.left.right, true);
            rotate(unbalanced.left, false);
            // printLine();
            // System.out.println("heheheha");
            // rotate(unbalanced, false);
        } else {
            System.out.println("inserted node is in the left st of the right st of "
                    + unbalanced.key + "\nRL heavy");
            rotate(unbalanced.right.left, false);
            rotate(unbalanced.right, true);
        }

        printLine();
        size++;
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
            boolean rightSubTree =
                    inserted.parent != root ? inserted.key.compareTo(tempGrandparent.key) > 0
                            : false;

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
            boolean leftSubTree =
                    inserted.parent != root ? inserted.key.compareTo(tempGrandparent.key) > 0
                            : false;

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

    private Node insertHelper(Node toAdd, Node root, Node parent) {
        if (root == null) {
            toAdd.parent = parent;
            return toAdd;
        }
        int result = root.key.compareTo(toAdd.key);
        if (result < 0) {
            // go right
            root.right = insertHelper(toAdd, root.right, root);

        } else if (result > 0) {
            // go left;
            root.left = insertHelper(toAdd, root.left, root);
        }
        return root;
    }

    @Override
    public boolean delete(Object element) {
        if (!contains(element)) {
            return false;
        }

        // find the parent of the soon-to-be deleted node
        // Node parent = null, ahead = root;
        // T temp = (T) element;
        // while (ahead.key.compareTo(temp) != 0) {
        //     int result = ahead.key.compareTo(temp);
        //     if (result < 0) {
        //         // go right
        //         parent = ahead;
        //         ahead = ahead.right;
        //     } else if (result > 0) {
        //         // go left
        //         parent = ahead;
        //         ahead = ahead.left;
        //     } else {
        //         break;
        //     }
        // }
        // System.out.println("parent = " + parent.key + "\tahead = " + ahead.key);

        root = deleteHelper(root, element);
        size--;
        return true;
    }

    @SuppressWarnings("unchecked")
    private Node deleteHelper(Node root, Object element) {
        if (root == null) {
            return root;
        }
        T temp = (T) element;
        if (root.key.compareTo(temp) < 0) {
            // go right
            root.right = deleteHelper(root.right, element);
        } else if (root.key.compareTo(temp) > 0) {
            // go left
            root.left = deleteHelper(root.left, element);

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
            root.right = deleteHelper(root.right, ios);
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

    private int getBalance(Node node) {
        return height(node.right) - height(node.left);
    }

    private int height(Node node) {
        return node == null ? -1 : Math.max(1 + height(node.left), 1 + height(node.right));
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyAVLTree)) {
            return false;
        }
        MyAVLTree<T> other = (MyAVLTree<T>) obj;
        List<T> theseNodes = inorder(), otherNodes = other.inorder();
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
        return indent + "k: " + node.key + "\n" + indent + "l:"
                + (node.left == null ? " null" : "\n") + toStringHelper(node.left, indent + "    ")
                + "\n" + indent + "r:" + (node.right == null ? " null" : "\n")
                + toStringHelper(node.right, indent + "    ");
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
    public void clear() {
        size = 0;
        root = null;
    }

}
