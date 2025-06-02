package BloomFilter.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BloomFilter.impl.MyBloomFilter;

public class BFTests {

    @Test
    public void testConstructorAndSize() {
        MyBloomFilter<String> bf1 = new MyBloomFilter<>(10);
        assertTrue(bf1.size() == 0);

        MyBloomFilter<String> bf2 = new MyBloomFilter<>();
        assertTrue(bf2.size() == 0);

        MyBloomFilter<String> bf3 = new MyBloomFilter<>(100, 100);
        assertTrue(bf3.size() == 0);
    }

    @Test
    public void testInsertAndSearch() {
        MyBloomFilter<String> bf = new MyBloomFilter<>();
        for (int index = 0; index < 3; index++) {
            bf.add("hello" + index);
        }
        System.out.println(bf);
        for (int index = 0; index < 3; index++) {
            String s = "hello" + index;
            assertTrue(bf.search(s));
        }
        assertFalse(bf.search("hello4"));
        assertFalse(bf.search("aafohadsf"));
    }

    @Test
    public void testProbability() {
        MyBloomFilter<String> bf1 = new MyBloomFilter<>(10);

        for (int index = 0; index < 50; index++) {
            bf1.add("hello" + index);
        }

        // needs fixing
        double probability = bf1.probFP();
        System.out.println(probability);
    }
}
