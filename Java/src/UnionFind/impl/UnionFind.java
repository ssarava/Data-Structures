package UnionFind.impl;

public class UnionFind {

    // updating sizes needs modifying
    
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(8);
        uf.union(5, 4);
        uf.union(4, 2);
        uf.union(2, 3);
        uf.union(7, 6);
        uf.union(6, 3);
        uf.union(3, 1);
        uf.union(1, 0);
    
        uf.findrep(4);
        System.out.println(uf);
        System.out.println();
        
    }

    public static final int DEFAULT_SIZE = 10;
    int initialSize;
    int[] parents, sizes;

    public UnionFind(int initialSizeIn) {
        parents = new int[initialSizeIn];
        sizes = new int[initialSizeIn];
        for (int index = 0; index < initialSizeIn; index ++) {
            parents[index] = index;     // each node is its own parent
            sizes[index] = 1;
        }
    }

    public UnionFind() {
        this(DEFAULT_SIZE);
    }

    /**
     * Adds a new element to the set
     */
    public void add() {
        int[] tempPar = new int[parents.length + 1];
        for (int index = 0; index < parents.length; index ++) {
            tempPar[index] = parents[index];
        }
        tempPar[tempPar.length - 1] = parents.length;

        int[] tempSizes = new int[sizes.length + 1];
        for (int index = 0; index < sizes.length; index ++) {
            tempSizes[index] = sizes[index];
        }
        tempSizes[tempSizes.length - 1] = sizes.length;

        parents = tempPar;
        sizes = tempSizes;
    }

    public int getSize(Integer x) {
        return sizes[x];
    }

    /**
     * Uses path compression by making the
     * parent of every node along the path to the root, the root
     */
    public int findrep(Integer x) {
        if (x == parents[x]) {
            return x;
        }
        sizes[parents[x]] -= 1;
        parents[x] = findrep(parents[x]);
        System.out.println("parents[" + x + "] = " + parents[x] + "\tsizes[" + x + "] = " + sizes[x]);
        // update_sizes();
        return parents[x];
    }

    private void update_sizes() {
        for (int x = 0; x < sizes.length; x ++) {
            // if (x == parents[x]) {
            //     sizes[x] = 1;
            // } else {
            //     sizes[parents[x]] ++;
            // }
        }
    }

    /**
     * Weighted union. Sets x's root to y's root.
     */
    public void union(Integer x, Integer new_parent) {
        int x_par = findrep(x);
        int root = findrep(new_parent);
        parents[x_par] = root;
        // System.out.println("parents[" + x_par + "]" + " = " + root);
        // System.out.println("size[" + x_par + "] = " + sizes[x_par]);
        sizes[parents[x]] += sizes[x_par];
    }

    /**
     * Returns whether 'x' and 'y' are elements of the same set.
     */
    public boolean inSameSet(Integer x, Integer y) {
        return findrep(x) == findrep(y);
    }

    @Override
    public String toString() {
        StringBuilder sbparents = new StringBuilder("["), sbsizes = new StringBuilder("[");
        for (int i: parents) {
            sbparents.append(i + " ");
        }
        sbparents.replace(sbparents.length() - 1, sbparents.length(), "]");
        for (int i: sizes) {
            sbsizes.append(i + " ");
        }
        sbsizes.replace(sbsizes.length() - 1, sbsizes.length(), "]");
        return "Parents:\t" + sbparents.toString() + "\nSizes:\t\t" + sbsizes.toString();
    }

}