package BloomFilter.impl;

public class BloomFilter<T> {

    public static void main(String[] args) {
       
    }
    
    private int m;                      // capacity
    private int k;                      // number of hash functions
    private int size;                   // size of the BF
    private boolean[] bits;             // 'true' means the bit is set

    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_NUM_HASH_FUNCTIONS = 3;
    private static final double LOAD_FACTOR = 0.75;

    public BloomFilter(int capacityIn, int numHashFunctionsIn) {
        m = capacityIn;
        k = numHashFunctionsIn;
        bits = new boolean[m];
        size = 0;
    }

    public BloomFilter() {
        this(DEFAULT_SIZE, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public BloomFilter(int capacityIn) {
        this(capacityIn, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public int size() {
        return size;
    }

    /**
     * Calculates the probability of a false positive
     */
    public double probFP() {
        return Math.pow(1 - Math.pow(1 - 1 / m, k * size), k);
    }

    /**
     * Random hash function
     */
    private int hash(T data, int index) {
        return Math.abs(index * data.hashCode()) % m;
    }

    public void insert(T data) {
        for (int index = 0; index < k; index ++) {
            // System.out.println("h(" + data + ")_" + index + " = " + hash(data, index));
            int bucket = hash(data, index);
            bits[bucket] = true;
        }
        size ++;
    }

    public boolean search(T data) {
        for (int index = 0; index < k; index ++) {

            if (!bits[hash(data, index)]) {
                System.out.println("Search for " + data + " yields 'NO'");
                return false;
            }
        }
        System.out.println("Search for " + data + " yields 'YES'");
        return true;
    }
    // incomplete...
    // private void rebuild() {
    //     int newLength = bits.length * 3;
    //     boolean[] newBits = new boolean[newLength];
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (boolean b: bits) {
            sb.append((b ? '1' : '0') + " ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }

}
