package Stack.Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Stack.src.Driver;
import Stack.src.Driver.Person;
import Stack.src.LL_Stack;

public class StudentTests {

    Driver d = new Driver();
    Driver.Person p = d. new Person("", 3, 3.0);

    @Test
	public void testMyStackConstructorAndGettersAndEmpty() {
		LL_Stack<Driver.Person> stack1 = new LL_Stack<>();
		assertTrue(stack1.isEmpty());
        assertFalse(stack1 == null);
	}

    @Test
	public void testPeek() {
		LL_Stack<Driver.Person> stack1 = new LL_Stack<>();
        for (int index = 0; index < 8; index ++) {
            stack1.push(d. new Person("john" + index, (30 + 2*index / 3), (140.0 + 5*index)));
        }
        int size = stack1.getSize();
        assertTrue(stack1.peek().compareTo(d. new Person("Colombo", (30 + 2*7 / 3), (140.0 + 3.1415))) == 0);
        assertTrue(stack1.getSize() == size);
    }

    @Test
    public void testPop() {
        LL_Stack<Driver.Person> stack1 = new LL_Stack<>();
        for (int index = 0; index < 8; index ++) {
            stack1.push(d. new Person("john" + index, (30 + 2*index / 3), (140.0 + 5*index)));
        }
        int size = stack1.getSize();
        assertTrue(stack1.pop().compareTo(d. new Person("Colombo", (30 + 2*7 / 3), (140.0 + 3.1415))) == 0);
        assertTrue(stack1.getSize() == size - 1);
    }

    @Test
    public void testSearch() {
		LL_Stack<Driver.Person> stack1 = new LL_Stack<>();
        for (int index = 0; index < 5; index ++) {
            Person p = d.new Person("john" + index, (30 + 2*index / 3), (140.0 + 5*index));
            stack1.push(p);
        }
        System.out.println(stack1.toString());
        Person p1 = d.new Person("john" + 0, (30 + 2*0 / 3), (140.0 + 5*0));
        assertFalse(stack1.search(p1) == 1);
        assertFalse(stack1.search(p1) == 2);
        assertFalse(stack1.search(p1) == 3);
        System.out.println(stack1.search(p1));
        assertTrue(stack1.search(p1) == 4);
        assertFalse(stack1.search(p1) == 5);
    }
}
