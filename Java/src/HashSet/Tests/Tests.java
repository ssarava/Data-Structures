package HashSet.Tests;

import static org.junit.Assert.*;

import org.junit.*;

import HashSet.impl.ClosedAddressing.HashSet;

public class Tests {
    
    @Test
    public void testConstructorsAndGetters() {
        HashSet<Integer> set1 = new HashSet<>();
        assertTrue(set1.size() == 0);
        for (int index = 0; index < 10; index ++) {
            set1.add(index);
        }
        assertTrue(set1.size() == 10);
        System.out.println(set1.toString());
    }
}
