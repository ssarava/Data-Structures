package BloomFilter.impl;

/**
 * My implementation of a Bloom Filter. Does not support rebuilding.
 */
public class MyBloomFilter<T> {

    public static void main(String[] args) {
       MyBloomFilter<String> bf = new MyBloomFilter<>();
       bf.add("sad;lfihaif");
       System.out.println(bf);

    }

    private int k;                      // number of hash functions
    private int size;                   // size of the BF
    private boolean[] bits;             // 'true' means the bit is set

    private static final int DEFAULT_CAPACITY = 100;
    private static final int DEFAULT_NUM_HASH_FUNCTIONS = 5;

    public MyBloomFilter(int capacityIn, int numHashFunctionsIn) {
        k = numHashFunctionsIn;
        bits = new boolean[capacityIn];
        size = 0;
    }

    public MyBloomFilter() {
        this(DEFAULT_CAPACITY, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public MyBloomFilter(int capacityIn) {
        this(capacityIn, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public int size() {
        return size;
    }

    /**
     * Returns an estimation of the probability of a false positive
     */
    public double probFP() {
        return Math.pow(1 - Math.pow(1 - 1 / bits.length, k * size), k);
    }

    /**
     * Very basic hash function generator.
     */
    private int hash(T data, int hashInd) {
        return Math.abs(hashInd * data.hashCode()) % bits.length;
    }

    public void add(T data) {
        for (int hashInd = 0; hashInd < k; hashInd ++) {
            // System.out.println("h(" + data + ")_" + index + " = " + hash(data, index));
            int bucket = hash(data, hashInd);
            bits[bucket] = true;
        }
        size ++;
    }

    public boolean search(T data) {
        for (int index = 0; index < k; index ++) {

            if (!bits[hash(data, index)]) {
                System.out.println("Search for [" + data + "] yields 'NO'");
                return false;
            }
        }
        System.out.println("Search for [" + data + "] yields 'YES'");
        return true;
    }

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
