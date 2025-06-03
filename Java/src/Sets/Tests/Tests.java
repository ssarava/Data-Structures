package Sets.Tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.*;
import Sets.impl.MyCAHashSet;

public class Tests {

    @Test
    public void testConstructorsAndGetters() {
        MyCAHashSet<Integer> set1 = new MyCAHashSet<>();
        assertTrue(set1.size() == 0);
        for (int index = 0; index < 10; index++) {
            set1.add(index);
        }
        assertTrue(set1.size() == 10);
        System.out.println(set1.toString());
    }

    @Test
    public void misc() {
        MyCAHashSet<String> set1 = new MyCAHashSet<>(4);

        for (int ind = 0; ind < 12; ind++) {
            String s = "hello" + String.valueOf(ind);
            set1.add(s);
        }
        // System.out.println(set1);

        Iterator<String> iter1 = set1.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
    }
}
