package BloomFilter.impl;

/**
 * My implementation of a Counting Bloom Filter. Does not support rebuilding.
 */
public class MyCBloomFilter<T> {

    private int k; // number of hash functions
    private int size; // size of the BF
    private short[] buckets; // 'true' means the bit is set

    private static final int DEFAULT_CAPACITY = 50, DEFAULT_NUM_HASH_FUNCTIONS = 5;

    public MyCBloomFilter(int capacityIn, int numHashFunctionsIn) {
        k = numHashFunctionsIn;
        buckets = new short[capacityIn];
        size = 0;
    }

    public MyCBloomFilter() {
        this(DEFAULT_CAPACITY, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public MyCBloomFilter(int capacityIn) {
        this(capacityIn, DEFAULT_NUM_HASH_FUNCTIONS);
    }

    public int size() {
        return size;
    }

    /**
     * Returns an estimation of the probability of a false positive
     */
    public double probFP() {
        return Math.pow(1 - Math.pow(1 - 1 / buckets.length, k * size), k);
    }

    /**
     * Very basic hash function generator.
     */
    private int hash(T data, int hashInd) {
        return Math.abs(hashInd * data.hashCode()) % buckets.length;
    }

    /**
     * Adds {@code data}
     */
    public void add(T data) {
        for (int hashInd = 0; hashInd < k; hashInd++) {
            // System.out.println("h(" + data + ")_" + index + " = " + hash(data, index));
            int bucket = hash(data, hashInd);
            buckets[bucket]++;
        }
        size++;
    }

    /**
     * Returns whether {@code data} previously existed and was successfully deleted.
     */
    public boolean delete(T data) {
        boolean deleted = true;
        for (int index = 0; index < k; index++) {
            int hashVal = hash(data, index);
            if (buckets[hashVal] == 0) {
                // System.out.println(data + " couldn't be deleted since it was never added.");
                deleted = false;
            } else {
                buckets[hashVal]--;
            }
        }
        // System.out.println(data + " was successfully deleted!");
        return deleted;
    }

    /**
     * Returns {@code true} if {@code data} was added, or {@code false} otherwise
     */
    public boolean search(T data) {
        for (int index = 0; index < k; index++) {
            if (buckets[hash(data, index)] == 0) {
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
        for (short s : buckets) {
            sb.append(s + " ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }
}
