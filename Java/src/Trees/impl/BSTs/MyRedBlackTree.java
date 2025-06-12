package Trees.impl.BSTs;

import java.util.List;
import java.util.Queue;
import Lists.impl.SinglyLinkedList;
import Stacks.src.MyStack;

public class MyRedBlackTree<T extends Comparable<T>> extends MyBST<T> {

    private static int debug = 0;

    public static void main(String[] args) {
        MyRedBlackTree<Integer> tree = new MyRedBlackTree<>();
        tree.insert(15);
        tree.insert(5);
        tree.insert(1);
        System.out.println(tree);
    }

    protected class Node extends MyBST<T>.Node {

        protected static final boolean BLACK = true, RED = false;
        protected Node left, right, parent;
        protected boolean color;

        protected Node(T keyIn) {
            super(keyIn);
            left = LEAF;
            right = LEAF;
            parent = null;
            color = RED;
        }

        protected Node(T keyIn, boolean colorIn) {
            this(keyIn);
            color = colorIn;
        }

        @Override
        public String toString() {
            return super.toString() + " | color: " + (color ? "black" : "red");
        }
    }

    private Node root;
    private final Node LEAF = new Node(null, Node.BLACK);
    private int size;

    public MyRedBlackTree() {
        root = null;
        size = 0;
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
            throw new ClassCastException(o.getClass().getName()
                    + " is not compatible with a tree of type " + root.key.getClass().getName());
        }
        Node curr = root;
        while (curr != LEAF) {
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
        int initialSize = size;
        for (int index = 0; index < keysIn.length; index++) {
            insert(keysIn[index]);
        }
        return size != initialSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(Object keyIn) {
        if (contains(keyIn)) {
            return false;
        }
        Node toAdd = new Node((T) keyIn);
        root = insertHelper(toAdd, root, null);
        
        // redblack-specific
        handleRotationsAndColoring(toAdd);

        size++;
        return true;
    }

    private Node insertHelper(Node toAdd, Node root, Node parent) {
        if (root == null || root == LEAF) {
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


    protected void bstInsertIter(Node toAdd) {
        // iterative insert because why not
        Node pred = null, curr = root;
        while (pred != null && curr != LEAF) {
            pred = curr;
            if (curr.key.compareTo(toAdd.key) < 0) {
                // go right
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        toAdd.parent = pred;

        // if the root is null
        if (pred == null) {
            root = toAdd;

        // make 'toAdd' the appropriate child
        } else if (toAdd.key.compareTo(toAdd.parent.key) < 0) {
            pred.left = toAdd;
        } else {
            pred.right = toAdd;
        }
    }

    private void handleRotationsAndColoring(Node added) {
        while (added.parent != null && added.parent.color == Node.RED) {
            debug ++;
            if (debug > 5) {
                System.out.println("exiting from insert");
                System.exit(1);
            }

            // if "toAdd's" parent is a left child, it's uncle is a right child
            if (added.parent == added.parent.parent.left) {
                Node uncle = added.parent.parent.right;

                // case 1: if uncle's color is RED, switch the colors of toAdd's parent, uncle, and
                // grandparent
                if (uncle.color == Node.RED) {
                    added.parent.color = Node.BLACK;
                    uncle.color = Node.BLACK;
                    added.parent.parent.color = Node.RED;

                    // re-assign colors moving up the tree
                    added = added.parent.parent;
                } else {
                    if (added == added.parent.right) {
                        added = added.parent;
                        left_rotate(added);
                    }
                    added.parent.color = Node.BLACK;
                    added.parent.parent.color = Node.RED;
                    right_rotate(added.parent.parent);
                }

            // if "toAdd's" parent is a right child, it's uncle is a left child
            } else {
                Node uncle = added.parent.parent.left;

                // case 1: if uncle's color is RED, switch the colors of toAdd's parent, uncle, and
                // grandparent
                if (uncle.color == Node.RED) {
                    added.parent.color = Node.BLACK;
                    uncle.color = Node.BLACK;
                    added.parent.parent.color = Node.RED;

                    // re-assign colors moving up the tree
                    added = added.parent.parent;
                } else {
                    if (added == added.parent.left) {
                        added = added.parent;
                        right_rotate(added);
                    }
                    added.parent.color = Node.BLACK;
                    added.parent.parent.color = Node.RED;
                    left_rotate(added.parent.parent);
                }
            }

            if (added == root) {
                break;
            }
        }

        // root must be black
        root.color = Node.BLACK;
    }

    private void left_rotate(Node node) {
        Node tempRightChild = node.right;
        node.right = tempRightChild.left;

        if (tempRightChild.left != LEAF) {
            tempRightChild.left.parent = node;
        }

        tempRightChild.parent = node.parent;

        if (node.parent == null) {
            root = tempRightChild;
        } else if (node == node.parent.left) {
            node.parent.left = tempRightChild;
        } else {
            node.parent.right = tempRightChild;
        }

        tempRightChild.left = node;
        node.parent = tempRightChild;
    }

    private void right_rotate(Node node) {
        Node tempLeftChild = node.left;
        node.left = tempLeftChild.right;

        if (tempLeftChild.right != LEAF) {
            tempLeftChild.right.parent = node;
        }

        tempLeftChild.parent = node.parent;

        if (node.parent == null) {
            root = tempLeftChild;
        } else if (node == node.parent.right) {
            node.parent.right = tempLeftChild;
        } else {
            node.parent.left = tempLeftChild;
        }

        tempLeftChild.right = node;
        node.parent = tempLeftChild;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyRedBlackTree)) {
            return false;
        }
        MyRedBlackTree<T> other = (MyRedBlackTree<T>) obj;
        List<T> theseNodes = inorder(), otherNodes = other.inorder();
        return theseNodes.equals(otherNodes);
    }

    @Override
    public String toString() {
        return toStringHelper(root, "");
    }

    private String toStringHelper(Node node, String indent) {
        return node == null ? "" : indent + (node == LEAF ? "LEAF" : "k: " + (node.key == null ? "LEAF" : node.key) + "\n" + indent + "c: " + (node.color == Node.BLACK ? "Black" : "Red") + "\n" + indent + 
                "l: " + (node.left == null ? "null" : "\n") + toStringHelper(node.left, indent + "    ") + "\n" + indent + 
                "r: " + (node.right == null ? "null" : "\n") + toStringHelper(node.right, indent + "    "))
                ;
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
