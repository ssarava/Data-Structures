package LinkedList.Tests;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import LinkedList.impl.DoublyLinkedList;

public class DLLTests {

	public static final Random rand = new Random();

	@Test
	public void testConstructorsAndGetters() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		assertTrue(list1.size() == 0);
		assertTrue(list1.getFirst() == null);
		assertTrue(list1.getLast() == null);
		System.out.println(list1.toString());
	}

	@Test
	public void testaddFirst() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
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
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addLast(100);
		assertTrue(list1.getFirst() == 100);
		assertTrue(list1.getFirst() == list1.getLast());

		list1.addLast(999);
		assertTrue(list1.getFirst() == 100);
		assertTrue(list1.getLast() == 999);

		list1.addLast(rand.nextInt(Integer.MAX_VALUE));
		list1.addLast(-193);
		assertTrue(list1.getLast() == -193);

		list1.addLast(0);
		list1.addLast(-4920);
		assertTrue(list1.getLast() == -4920);

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
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
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		assertTrue(list1.size() == 0);

		list1.addFirst(10000);
		assertTrue(list1.removeFirst() == (10000));
		assertTrue(list1.getFirst() == null && list1.size() == 0);

		list1.addLast(38021);
		list1.addFirst(-193);
		assertTrue(list1.getLast() == 38021 && list1.size() == 2);

		list1.addFirst(0);
		list1.addLast(-4920);
		assertTrue(list1.getFirst() == 0 && list1.getLast() == -4920);
		assertTrue(list1.size() == 4);
		assertTrue(list1.removeFirst() == 0);
		assertTrue(list1.size() == 3);
		assertTrue(list1.removeFirst() == -193);
		assertTrue(list1.size() == 2);
		assertTrue(list1.removeFirst() == 38021);
		assertTrue(list1.size() == 1);
	}

	@Test
	public void testRemoveLast() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		assertTrue(list1.size() == 0);

		// list1.addFirst(-32810);
		list1.addLast(-32810);
		System.out.println(list1);
		assertTrue(list1.getFirst() == -32810);
		assertTrue(list1.getFirst() == list1.getLast() && list1.size() == 1);

		list1.addLast(1103);
		list1.addFirst(-4920);
		assertTrue(list1.getLast() == 1103 && list1.size() == 3);

		list1.addFirst(88);
		list1.addLast(12345);
		assertTrue(list1.getFirst() == 88 && list1.getLast() == 12345);
		assertTrue(list1.size() == 5);
		assertTrue(list1.removeFirst() == 88);
		assertTrue(list1.size() == 4);
		assertTrue(list1.removeFirst() == -4920);
		assertTrue(list1.size() == 3);
		assertTrue(list1.removeLast() == 12345);
		assertTrue(list1.size() == 2);
		assertTrue(list1.removeLast() == 1103);
		assertTrue(list1.size() == 1);
		assertTrue(list1.removeLast() == -32810);
		assertTrue(list1.size() == 0);
	}

	@Test
	public void testRemoveAllInstances() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		list1.addLast(0);
		list1.removeAllInstances(1);
		assertTrue(list1.size() == 1);

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
		list2.addLast(0);
		list2.removeAllInstances(0);
		assertTrue(list2.size() == 0);

		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
		list3.addLast(0);
		list3.addLast(0);
		list3.removeAllInstances(0);
		assertTrue(list3.size() == 0);

		DoublyLinkedList<Integer> list4 = new DoublyLinkedList<>();
		list4.addLast(0);
		list4.addLast(1);
		list4.removeAllInstances(1);
		assertTrue(list4.size() == 1);

		DoublyLinkedList<Integer> list5 = new DoublyLinkedList<>();
		list5.addLast(1);
		list5.addLast(0);
		list5.removeAllInstances(1);
		assertTrue(list5.size() == 1);

		DoublyLinkedList<Integer> list6 = new DoublyLinkedList<>();
		list6.addLast(0);
		list6.addLast(0);
		list6.addLast(0);
		list6.removeAllInstances(0);
		assertTrue(list6.size() == 0);

		DoublyLinkedList<Integer> list7 = new DoublyLinkedList<>();
		list7.addLast(0);
		list7.addLast(1);
		list7.addLast(0);
		list7.removeAllInstances(1);
		assertTrue(list7.size() == 2);

		DoublyLinkedList<Integer> list8 = new DoublyLinkedList<>();
		list8.addLast(0);
		list8.addLast(1);
		list8.addLast(1);
		list8.removeAllInstances(1);
		assertTrue(list8.size() == 1);

		DoublyLinkedList<Integer> list9 = new DoublyLinkedList<>();
		list9.addLast(0);
		list9.addLast(1);
		list9.addLast(1);
		list9.addLast(0);
		list9.removeAllInstances(1);
		assertTrue(list9.size() == 2);

		DoublyLinkedList<Integer> list10 = new DoublyLinkedList<>();
		list10.addLast(1);
		list10.addLast(0);
		list10.addLast(0);
		list10.addLast(1);
		list10.addLast(0);
		list10.addLast(1);
		list10.removeAllInstances(1);
		assertTrue(list10.size() == 3);

		DoublyLinkedList<Integer> list11 = new DoublyLinkedList<>();
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

	// --------------------------------------------------------------------------------------------------------------------------------
	// //

	@Test
	public void testContains() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
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

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
		list2.addLast(0);
		list2.addFirst(1);
		list2.addFirst(1);
		list2.addLast(1);
		assertTrue(list1.contains(1));
	}

	@Test
	public void testIndexOf() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		list1.addLast(0);
		list1.addLast(1);
		list1.addLast(2);
		list1.addLast(3);
		list1.addLast(4);
		list1.addLast(5);
		list1.addLast(6);
		assertTrue(list1.indexOf(8) == -1);

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
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
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		list1.addLast(0);
		list1.addLast(1);
		list1.addLast(1);
		list1.addLast(1);
		list1.addLast(0);
		list1.addLast(0);
		list1.addLast(0);
		assertTrue(list1.lastIndexOf(0) == 6);

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
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
	public void testRemove() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		for (int index = 0; index < 7; index++) {
			list1.addLast(index);
		}
		int initialSize1 = list1.size();

		for (int index = 0; index < initialSize1; index++) {
			int first = list1.get(0);
			Integer removed = list1.removeFirst();
			System.out.println(list1);
			assertTrue(removed == first);
		}
		System.out.println("-----------------------------------------------------------");

		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
		for (int index = 0; index < 7; index++) {
			list2.addLast(index);
		}
		int initialSize2 = list2.size();

		for (int index = initialSize2 - 1; index >= 0; index--) {
			int last2 = list2.get(list2.size() - 1);
			Integer removed2 = list2.remove(list2.size() - 1);
			System.out.println(list2);
			assertTrue(removed2 == last2);
		}
	}

	@Test
	public void testAdd() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		for (int index = 0; index < 10; index++) {
			list1.addLast(index);
		}
		System.out.println(list1);
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------");

		list1.add(3, 16);
		assertTrue(list1.size() == 11 && list1.indexOf(16) == 3);
		assertTrue(list1.lastIndexOf(2) == 4);
	}
}