package LinkedList.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import LinkedList.impl.SinglyLinkedList;

public class SLLTests {
	
	@Test
	public void testMySinglyLinkedListConstructorAndGetters() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);
		assertTrue(list1.getFirst() == null);
		assertTrue(list1.getLast() == null);
	}
	
	@Test
	public void testaddFirst() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(5);
		assertTrue(list1.getFirst() == 5);
		assertTrue(list1.getFirst() == list1.getLast());

		list1.addFirst(10);
		assertTrue(list1.getFirst() == 10);
		assertTrue(list1.getLast() == 5);

		list1.addFirst(15);
		list1.addFirst(20);
		list1.addFirst(25);
		list1.addFirst(100);
		assertTrue(list1.getFirst() == 100);
		assertTrue(list1.getLast() == 5);
	}

	@Test
	public void testaddLast() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(100);
		assertTrue(list1.getFirst() == 100);
		assertTrue(list1.getFirst() == list1.getLast());

		list1.addFirst(999);
		assertTrue(list1.getFirst() == 999);
		assertTrue(list1.getLast() == 100);

		list1.addLast(38021);
		list1.addLast(-193);
		assertTrue(list1.getLast() == -193);
		
		list1.addLast(0);
		list1.addLast(-4920);
		assertTrue(list1.getLast() == -4920);
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		assertTrue(list2.size() == 0);

		list2.addLast(-8585);
		assertTrue(list2.getFirst() == -8585);
		assertTrue(list2.getFirst() == list2.getLast());
		assertTrue(list2.size() == 1);

		list2.addLast(493);
		assertTrue(list2.getLast() == 493);
		
		list2.addLast(-1039);
		list2.addLast(4820);
		assertTrue(list2.getLast() == 4820);
		assertTrue(list2.getFirst() == -8585);
		
		list2.addLast(809);
		list2.addFirst(38901);
		list2.addLast(353);
		assertTrue(list2.getFirst() == 38901);
		assertTrue(list2.getLast() == 353);
	}
	
	@Test
	public void testRetrieveFirstElement() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		assertTrue(list1.size() == 0);
		
		list1.addFirst(10000);
		assertTrue(list1.retrieveFirstElement() == (10000));
		assertTrue(list1.getFirst() == null && list1.size() == 0);

		list1.addLast(38021);
		list1.addFirst(-193);
		assertTrue(list1.getLast() == 38021 && list1.size() == 2);
		
		list1.addFirst(0);
		list1.addLast(-4920);
		assertTrue(list1.getFirst() == 0 && list1.getLast() == -4920);
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
		assertTrue(list1.getFirst() == -32810);
		assertTrue(list1.getFirst() == list1.getLast() && list1.size() == 1);
		
		list1.addLast(1103);
		list1.addFirst(-4920);
		assertTrue(list1.getLast() == 1103 && list1.size() == 3);
		
		list1.addFirst(88);
		list1.addLast(12345);
		assertTrue(list1.getFirst() == 88 && list1.getLast() == 12345);
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
		list1.addLast(0);
		list1.removeAllInstances(1);
		assertTrue(list1.size() == 1);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.addLast(0);
		list2.removeAllInstances(0);
		assertTrue(list2.size() == 0);
		
		SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
		list3.addLast(0);
		list3.addLast(0);
		list3.removeAllInstances(0);
		assertTrue(list3.size() == 0);
		
		SinglyLinkedList<Integer> list4 = new SinglyLinkedList<>();
		list4.addLast(0);
		list4.addLast(1);
		list4.removeAllInstances(1);
		assertTrue(list4.size() == 1);
		
		SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>();
		list5.addLast(1);
		list5.addLast(0);
		list5.removeAllInstances(1);
		assertTrue(list5.size() == 1);
		
		SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
		list6.addLast(0);
		list6.addLast(0);
		list6.addLast(0);
		list6.removeAllInstances(0);
		assertTrue(list6.size() == 0);
		
		SinglyLinkedList<Integer> list7 = new SinglyLinkedList<>();
		list7.addLast(0);
		list7.addLast(1);
		list7.addLast(0);
		list7.removeAllInstances(1);
		assertTrue(list7.size() == 2);
		
		SinglyLinkedList<Integer> list8 = new SinglyLinkedList<>();
		list8.addLast(0);
		list8.addLast(1);
		list8.addLast(1);
		list8.removeAllInstances(1);
		assertTrue(list8.size() == 1);
		
		SinglyLinkedList<Integer> list9 = new SinglyLinkedList<>();
		list9.addLast(0);
		list9.addLast(1);
		list9.addLast(1);
		list9.addLast(0);
		list9.removeAllInstances(1);
		assertTrue(list9.size() == 2);
		
		SinglyLinkedList<Integer> list10 = new SinglyLinkedList<>();
		list10.addLast(1);
		list10.addLast(0);
		list10.addLast(0);
		list10.addLast(1);
		list10.addLast(0);
		list10.addLast(1);
		list10.removeAllInstances(1);
		assertTrue(list10.size() == 3);
		
		SinglyLinkedList<Integer> list11 = new SinglyLinkedList<>();
		list11.addLast(0);
		list11.addLast(1);
		list11.addLast(0);
		list11.addLast(0);
		list11.addLast(1);
		list11.addLast(0);
		list11.addLast(1);
		list11.addLast(1);
		list11.addLast(1);
		list11.addLast(1);
		list11.addLast(0);
		list11.removeAllInstances(0);
		assertTrue(list11.size() == 6);
	}

	// -------------------------------------------------------------------------------------------------------------------------------- //

	@Test
	public void testContains() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.addLast(0);
		list1.addLast(1);
		list1.addLast(2);
		list1.addLast(3);
		list1.addLast(4);
		list1.addLast(5);
		list1.addLast(6);
		list1.addLast(7);
		list1.addLast(8);
		list1.addLast(8);
		list1.addLast(8);
		assertFalse(list1.contains(9));

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.addLast(0);
		list2.addFirst(1);
		list2.addFirst(1);
		list2.addLast(1);
		assertTrue(list1.contains(1));
	}

	@Test
	public void testIndexOf() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.addLast(0);
		list1.addLast(1);
		list1.addLast(2);
		list1.addLast(3);
		list1.addLast(4);
		list1.addLast(5);
		list1.addLast(6);
		assertTrue(list1.indexOf(8) == -1);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.addLast(92);
		list2.addLast(1);
		list2.addLast(2);
		list2.addLast(3);
		list2.addLast(4);
		list2.addLast(4);
		list2.addLast(1000);
		assertTrue(list2.indexOf(1000) == 6);
	}

	@Test
	public void testLastIndexOf() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addLast(0);
		list1.addLast(1);
		list1.addLast(1);
		list1.addLast(1);
		list1.addLast(0);
		list1.addLast(0);
		list1.addLast(0);
		assertTrue(list1.lastIndexOf(0) == 6);

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.addLast(1);
		list2.addLast(1);
		list2.addLast(1);
		list2.addLast(1);
        list2.addLast(1);
        list2.addLast(1);
		list2.addLast(0);
		assertTrue(list2.lastIndexOf(1) == 5);
	}

	@Test
	public void testSet() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addLast(0);
		list1.addLast(1);
		list1.addLast(2);
		list1.addLast(3);
        list1.addLast(4);
        list1.addLast(5);
		list1.addLast(6);
		Integer removed = list1.remove(4);
		assertTrue(list1.size() == 6 && removed == 4);
	}

	@Test
	public void testAdd() {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addLast(2);
        list1.addLast(2);
		list1.addLast(2);
        list1.addLast(2);
		list1.add(3, 16);
		assertTrue(list1.size() == 5 && list1.indexOf(16) == 3);
		assertTrue(list1.lastIndexOf(2) == 4);
	}
}
