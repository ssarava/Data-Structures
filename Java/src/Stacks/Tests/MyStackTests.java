package Stacks.Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Stacks.src.MyStack;

public class MyStackTests {

    @Test
	public void testMyStackConstructorAndGettersAndEmpty() {
		MyStack<String> stack1 = new MyStack<>();
		assertTrue(stack1.isEmpty());
        assertFalse(stack1 == null);
	}

    @Test
	public void testPeek() {
		
    }

    @Test
    public void testPop() {
       
    }

    @Test
    public void testSearch() {
		
    }
}
