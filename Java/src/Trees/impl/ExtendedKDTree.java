package Trees.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtendedKDTree {

    protected static final Map<Integer, Character> dimensions = Map.of(
        0, 'x',
        1, 'y',
        2, 'z'
    );

    public static void main(String[] args) {
        
    }
    
    public class Datum {

        private int[] coords;
        private String code;

        private Datum(int[] coordsIn, String codeIn) {
            coords = coordsIn;
            code = codeIn;
        }

        @Override
        public String toString() {
            return "code:\t" + code + "\t\tcoords:\t" + coords;
        }
    }

    private class NodeInternal {
        private int splitIndex, splitValue;
        private Object leftChild, rightChild;

        private NodeInternal(int splitIndexIn, int splitValueIn, Object leftChildIn, Object rightChildIn) {
            this.splitIndex = splitIndexIn;
            this.splitValue = splitValueIn;
            this.leftChild = leftChildIn;
            this.rightChild = rightChildIn;
        }

        @Override
        public String toString() {
            return "splitIndex: " + splitIndex + "\tsplitValue: " + splitValue + "\t" + leftChild.toString() + "\t" + rightChild.toString();
        }
    }

    private class NodeLeaf {
        List<Datum> data;

        private NodeLeaf(List<Datum> data) {
            this.data = new ArrayList<>();
            for (Datum d: data) {
                this.data.add(d);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (Datum d: this.data) {
                StringBuilder temp = new StringBuilder("(");
                for (int index = 0; index < d.coords.length - 1; index ++) {
                    temp.append(d.coords[index] + ", ");
                }
                temp.append(d.coords[d.coords.length - 1] + ")");
                sb.append(temp);
            }
            return sb.toString();
        }
    }

    protected String splitMethod;
    protected int k, m;
    private Object root;

    public ExtendedKDTree(String splitMethodIn, int kIn, int mIn, Object rootIn) {
        splitMethod = splitMethodIn;
        k = kIn;
        m = mIn;
        root = rootIn;
    }

    public ExtendedKDTree(String splitMethodIn, int kIn, int mIn) {
        this(splitMethodIn, kIn, mIn, null);
    }

    public void insert(int[] point, String code) {
        Datum toAdd = new Datum(point, code);

        if (root == null) {
            List<Datum> newList = new ArrayList<>();
            newList.add(toAdd);
            root = new NodeLeaf(newList);
            return;
        }

        // Object curr = root, prev = null;
        // boolean isLeftChild = false;

        // while (curr instanceof NodeInternal) {
        //     prev = curr;
        //     NodeInternal tempInternal = (NodeInternal) curr;
        //     if (point[tempInternal.splitIndex] < tempInternal.splitValue) {
        //         curr = tempInternal.leftChild;
        //         isLeftChild = false;
        //     } else {
        //         curr = tempInternal.rightChild;
        //         isLeftChild = true;
        //     }
        // }

        
    }

    @Override
    public String toString() {
        return root == null ? "{}" : toStringHelper(root);
    }

    private String toStringHelper(Object node) {
        if (node instanceof NodeLeaf) {
            StringBuilder temp = new StringBuilder("p: ");
            NodeLeaf tempLeaf = (NodeLeaf) node;
            for (Datum d: tempLeaf.data) {
                temp.append("coords: " + arrToString(d.coords) + "\t,code: " + d.code);
            }
            return temp.toString();

        } else {
            StringBuilder temp = new StringBuilder("");
            NodeInternal tempLeaf = (NodeInternal) node;
            temp.append("splitindex: " + tempLeaf.splitIndex + "\nsplitvalue: " + tempLeaf.splitValue + "\nl: ");
            if (tempLeaf.leftChild == null) {
                temp.append("None");
            } else {
                temp.append("\t" + tempLeaf.leftChild.toString());
            }
            temp.append("\nr: ");
            if (tempLeaf.rightChild == null) {
                temp.append("None");
            } else {
                temp.append("\t" + tempLeaf.rightChild.toString());
            }
            return temp.toString();
        }
    }

    private static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int index = 0; index < arr.length - 1; index ++) {
            sb.append(arr[index] + ", ");
        }
        sb.append(arr[arr.length - 1] + "]");
        return sb.toString();
    }
    
}
