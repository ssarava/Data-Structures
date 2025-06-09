package Trees.impl.BSTs;

public class MySplayTree<T extends Comparable<T>> extends MyBST<T> {

    private class Node extends MyBST<T>.Node {

        protected Node left, right, parent;

        private Node(T keyIn) {
            super(keyIn);
            parent = null;
        }

        private Node(Node other) {
            super(other);
            parent = null;
        }
    }

    private Node root;
    private int size;

    public MySplayTree() {
        root = null;
        size = 0;
    }

    private void splay(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot add a null reference to the tree.");
        }
        if (root == null) {
            return;
        }
        if (!root.key.getClass().isAssignableFrom(o.getClass())) {
            throw new ClassCastException(o.getClass().getName()
                    + " is not compatible with a tree of type " + root.key.getClass().getName());
        }
        T temp = (T) o;
        Node nodeFound = bstSearch(o);

        // if the key is in the tree
        if (nodeFound.key.compareTo((T) o) == 0) {
            System.out.println("key " + nodeFound.key + " was found in the tree!");
            return;
        }
        
        // 'nodeFound' is the key's IOP

        // if the IOP is at the root:
        if (nodeFound == root) {
            return;
        }
        // if the IOP is a child of the root, do a rotation (zig)
        else if (nodeFound == root.left || nodeFound == root.right) {

            // if the IOP is the left child of the root
            zig(nodeFound, true);
        }
        
        return;
    }

    private void zig(Node iop, boolean leftRotation) {
        
        if (leftRotation) {
            Node tempRightChild = iop.right, tempParent = iop.parent;
            // isolate the IOP

        }

    }

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
        Node nodeFound = bstSearch(o);

        // if the key is in the tree
        if (nodeFound.key.compareTo((T) o) == 0) {

            // if the key is at the root:
        }
        return true;
    }

    private Node bstSearch(Object keyIn) {
        Node curr = root, parentBeforeFallingOut = null;
        while (curr != null) {
            int result = curr.key.compareTo((T) keyIn);
            if (result < 0) {
                // go right
                parentBeforeFallingOut = curr;
                curr = curr.right;
            } else if (result > 0) {
                // go left
                parentBeforeFallingOut = curr;
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return parentBeforeFallingOut;
    }

    @Override
    public boolean insert(Object keyIn) {
        if (contains(keyIn)) {
            return false;
        }
        Node added = new Node((T) keyIn);

        // bst insert
        root = bstInsertHelper(added, root, null);
        // incomplete (splay)
        return true;
    }

    private Node bstInsertHelper(Node toAdd, Node root, Node parent) {
        if (root == null) {
            toAdd.parent = parent;
            return root;
        }
        int result = toAdd.key.compareTo(root.key);
        if (result < 0) {
            // go left
            root.left = bstInsertHelper(toAdd, root.left, root);

        } else if (result > 0) {
            // go right
            root.right = bstInsertHelper(toAdd, root.right, root);
        }
        return root;
    }
    
}