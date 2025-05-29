package BloomFilter.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BloomFilter.impl.BloomFilter;

public class Tests {
    
    @Test
    public void testConstructorAndSize() {
        BloomFilter<String> bf1 = new BloomFilter<>(10);
        assertTrue(bf1.size() == 0);
        
        BloomFilter<String> bf2 = new BloomFilter<>();
        assertTrue(bf2.size() == 0);

        BloomFilter<String> bf3 = new BloomFilter<>(100, 100);
        assertTrue(bf3.size() == 0);
    }

    @Test
    public void testInsertAndSearch() {
        BloomFilter<String> bf1 = new BloomFilter<>(10);
        for (int index = 0; index < 3; index ++) {
            bf1.insert("hello" + String.valueOf(index));
        }
        System.out.println(bf1);
        for (int index = 0; index < 3; index ++) {
            String s = "hello" + String.valueOf(index);
            assertTrue(bf1.search(s));
        }
    }

    @Test
    public void testProbability() {
        BloomFilter<String> bf1 = new BloomFilter<>(10);
        double probability = bf1.probFP();
        System.out.println(probability);

        for (int index = 0; index < 3; index ++) {
            bf1.insert("hello" + String.valueOf(index));
            // incomplete... needs work
        }
    }
}
