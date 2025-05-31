package LinkedList.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import LinkedList.impl.SinglyLinkedList;

public class SLLTests {
	
	@Test
	public void testMySinglyLinkedListConstructorAndGetters() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);
		assertTrue(list1.peek() == null);
		assertTrue(list1.getLast() == null);
	}
	
	@Test
	public void testaddFirst() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(5);
		assertTrue(list1.peek() == 5);
		assertTrue(list1.peek() == list1.getLast());

		list1.addFirst(10);
		assertTrue(list1.peek() == 10);
		assertTrue(list1.getLast() == 5);

		list1.addFirst(15);
		list1.addFirst(20);
		list1.addFirst(25);
		list1.addFirst(100);
		assertTrue(list1.peek() == 100);
		assertTrue(list1.getLast() == 5);
	}

	@Test
	public void testoffer() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(100);
		assertTrue(list1.peek() == 100);
		assertTrue(list1.peek() == list1.getLast());

		list1.addFirst(999);
		assertTrue(list1.peek() == 999);
		assertTrue(list1.getLast() == 100);

		list1.offer(38021);
		list1.offer(-193);
		assertTrue(list1.getLast() == -193);
		
		list1.offer(0);
		list1.offer(-4920);
		assertTrue(list1.getLast() == -4920);
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		assertTrue(list2.size() == 0);

		list2.offer(-8585);
		assertTrue(list2.peek() == -8585);
		assertTrue(list2.peek() == list2.getLast());
		assertTrue(list2.size() == 1);

		list2.offer(493);
		assertTrue(list2.getLast() == 493);
		
		list2.offer(-1039);
		list2.offer(4820);
		assertTrue(list2.getLast() == 4820);
		assertTrue(list2.peek() == -8585);
		
		list2.offer(809);
		list2.addFirst(38901);
		list2.offer(353);
		assertTrue(list2.peek() == 38901);
		assertTrue(list2.getLast() == 353);
	}
	
	@Test
	public void testRetrieveFirstElement() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);
		
		list1.addFirst(10000);
		assertTrue(list1.retrieveFirstElement() == (10000));
		assertTrue(list1.peek() == null && list1.size() == 0);

		list1.offer(38021);
		list1.addFirst(-193);
		assertTrue(list1.getLast() == 38021 && list1.size() == 2);
		
		list1.addFirst(0);
		list1.offer(-4920);
		assertTrue(list1.peek() == 0 && list1.getLast() == -4920);
		assertTrue(list1.size() == 4);
		assertTrue(list1.retrieveFirstElement() == 0);
		assertTrue(list1.size() == 3);
		assertTrue(list1.retrieveFirstElement() == -193);
		assertTrue(list1.size() == 2);
		assertTrue(list1.retrieveFirstElement() == 38021);
		assertTrue(list1.size() == 1);
	}
	
	@Test
	public void testRetrieveLastElement() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(-32810);
		assertTrue(list1.peek() == -32810);
		assertTrue(list1.peek() == list1.getLast() && list1.size() == 1);
		
		list1.offer(1103);
		list1.addFirst(-4920);
		assertTrue(list1.getLast() == 1103 && list1.size() == 3);
		
		list1.addFirst(88);
		list1.offer(12345);
		assertTrue(list1.peek() == 88 && list1.getLast() == 12345);
		assertTrue(list1.size() == 5);
		assertTrue(list1.retrieveFirstElement() == 88);
		assertTrue(list1.size() == 4);
		assertTrue(list1.retrieveFirstElement() == -4920);
		assertTrue(list1.size() == 3);
		assertTrue(list1.retrieveLastElement() == 12345);
		assertTrue(list1.size() == 2);
		assertTrue(list1.retrieveLastElement() == 1103);
		assertTrue(list1.size() == 1);
		assertTrue(list1.retrieveLastElement() == -32810);
		assertTrue(list1.size() == 0);
	}
	
	@Test
	public void testRemoveAllInstances() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.offer(0);
		list1.removeAllInstances(1);
		assertTrue(list1.size() == 1);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.offer(0);
		list2.removeAllInstances(0);
		assertTrue(list2.size() == 0);
		
		SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
		list3.offer(0);
		list3.offer(0);
		list3.removeAllInstances(0);
		assertTrue(list3.size() == 0);
		
		SinglyLinkedList<Integer> list4 = new SinglyLinkedList<>();
		list4.offer(0);
		list4.offer(1);
		list4.removeAllInstances(1);
		assertTrue(list4.size() == 1);
		
		SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>();
		list5.offer(1);
		list5.offer(0);
		list5.removeAllInstances(1);
		assertTrue(list5.size() == 1);
		
		SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
		list6.offer(0);
		list6.offer(0);
		list6.offer(0);
		list6.removeAllInstances(0);
		assertTrue(list6.size() == 0);
		
		SinglyLinkedList<Integer> list7 = new SinglyLinkedList<>();
		list7.offer(0);
		list7.offer(1);
		list7.offer(0);
		list7.removeAllInstances(1);
		assertTrue(list7.size() == 2);
		
		SinglyLinkedList<Integer> list8 = new SinglyLinkedList<>();
		list8.offer(0);
		list8.offer(1);
		list8.offer(1);
		list8.removeAllInstances(1);
		assertTrue(list8.size() == 1);
		
		SinglyLinkedList<Integer> list9 = new SinglyLinkedList<>();
		list9.offer(0);
		list9.offer(1);
		list9.offer(1);
		list9.offer(0);
		list9.removeAllInstances(1);
		assertTrue(list9.size() == 2);
		
		SinglyLinkedList<Integer> list10 = new SinglyLinkedList<>();
		list10.offer(1);
		list10.offer(0);
		list10.offer(0);
		list10.offer(1);
		list10.offer(0);
		list10.offer(1);
		list10.removeAllInstances(1);
		assertTrue(list10.size() == 3);
		
		SinglyLinkedList<Integer> list11 = new SinglyLinkedList<>();
		list11.offer(0);
		list11.offer(1);
		list11.offer(0);
		list11.offer(0);
		list11.offer(1);
		list11.offer(0);
		list11.offer(1);
		list11.offer(1);
		list11.offer(1);
		list11.offer(1);
		list11.offer(0);
		list11.removeAllInstances(0);
		assertTrue(list11.size() == 6);
	}

	// -------------------------------------------------------------------------------------------------------------------------------- //

	@Test
	public void testContains() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.offer(0);
		list1.offer(1);
		list1.offer(2);
		list1.offer(3);
		list1.offer(4);
		list1.offer(5);
		list1.offer(6);
		list1.offer(7);
		list1.offer(8);
		list1.offer(8);
		list1.offer(8);
		assertFalse(list1.contains(9));

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.offer(0);
		list2.addFirst(1);
		list2.addFirst(1);
		list2.offer(1);
		assertTrue(list1.contains(1));
	}

	@Test
	public void testIndexOf() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.offer(0);
		list1.offer(1);
		list1.offer(2);
		list1.offer(3);
		list1.offer(4);
		list1.offer(5);
		list1.offer(6);
		assertTrue(list1.indexOf(8) == -1);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.offer(92);
		list2.offer(1);
		list2.offer(2);
		list2.offer(3);
		list2.offer(4);
		list2.offer(4);
		list2.offer(1000);
		assertTrue(list2.indexOf(1000) == 6);
	}

	@Test
	public void testLastIndexOf() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.offer(0);
		list1.offer(1);
		list1.offer(1);
		list1.offer(1);
		list1.offer(0);
		list1.offer(0);
		list1.offer(0);
		assertTrue(list1.lastIndexOf(0) == 6);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.offer(1);
		list2.offer(1);
		list2.offer(1);
		list2.offer(1);
        list2.offer(1);
        list2.offer(1);
		list2.offer(0);
		assertTrue(list2.lastIndexOf(1) == 5);
	}

	@Test
	public void testSet() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.offer(0);
		list1.offer(1);
		list1.offer(2);
		list1.offer(3);
        list1.offer(4);
        list1.offer(5);
		list1.offer(6);
		Integer removed = list1.remove(4);
		assertTrue(list1.size() == 6 && removed == 4);
	}

	@Test
	public void testAdd() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.offer(2);
        list1.offer(2);
		list1.offer(2);
        list1.offer(2);
		list1.add(3, 16);
		assertTrue(list1.size() == 5 && list1.indexOf(16) == 3);
		assertTrue(list1.lastIndexOf(2) == 4);
	}
}
