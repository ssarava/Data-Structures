package HashSet.impl.ClosedAddressing;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import LinkedList.impl.SinglyLinkedList;

/**
 * This immutable class is an implementation of Java's HashSet class.
 * It follows open hashing (closed addressing).
 * A singly-linked-list-like data structure supports separate chaining.
 */
public class HashSet<T extends Comparable<T>> implements Cloneable, Iterable<T> {

	public static int debug = 0;
	public static void main(String[] args) {
	

	}

	/**
	 * Separate chaining via singly linked lists is used for this hashtable
	 * implementation
	 */
	private class Node {
		private T value;
		private Node next;

		private Node(T valueIn) {
			value = valueIn;
		}
	}

	/**
	 * The size of the hashtable upon initialization.
	 */
	public static final int DEFAULT_TABLE_SIZE = 4;

	/**
	 * Specifies when table expansion and rehashing of internal nodes is needed.
	 * LOAD_FACTOR = n / m where 'n' is the number of entries and 'm' is an
	 * arbitrarily-chosen value
	 */
	public static final double LOAD_FACTOR = 0.75;

	/**
	 * The number of entries (nodes) in the hashtable; includes chained nodes.
	 */
	private int size;

	private Node[] hashtable;

	/**
	 * Allows for the hashtable to be a chosen size upon initialization
	 * 
	 * @param customCapacity
	 */
	@SuppressWarnings("unchecked")
	public HashSet(int customCapacity) {
		if (customCapacity <= 0) {
			throw new IllegalArgumentException("Can't initialize a list of length " + customCapacity);
		}
		size = 0;
		hashtable = new HashSet.Node[customCapacity];
	}

	/**
	 * The hashtable is set to the default size of 4 upon initialization
	 */
	public HashSet() {
		this(DEFAULT_TABLE_SIZE);
	}

	public int size() {
		return size;
	}

	public boolean contains(T obj) {
		int targInd = Math.abs(obj.hashCode() % hashtable.length); // hashcode mod capacity to find appropriate insertion index

		// search through chain
		for (Node curr = hashtable[targInd]; curr != null; curr = curr.next) {
			if (curr.value.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds to the set if it doesn't already exist. Prior to adding the argument,
	 * this function rebuilds the underlying hashtable if it's overfull
	 * 
	 * @param obj is added only if it doesn't already exist in the set
	 */
	public void add(T obj) {
		if (!contains(obj)) {
			int insertIndex = Math.abs(obj.hashCode() % hashtable.length);
			Node node = new Node(obj);
			if (hashtable[insertIndex] != null) {
				node.next = hashtable[insertIndex];
			}
			hashtable[insertIndex] = node;
			size++;
		}
		// rehash if overfull
		double ratio = ((double) size) / ((double) hashtable.length);
		if (ratio > LOAD_FACTOR) {
			rehash();
		}

	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean remove(T obj) {
		if (contains(obj)) {
			// remove the object from its chain
			int targIndex = Math.abs(obj.hashCode() % hashtable.length);
			Node curr = hashtable[targIndex];
			if (curr.value.equals(obj)) {
				hashtable[targIndex] = null;
			} else {
				Node prev = null;
				while (!curr.value.equals(obj)) {
					prev = curr;
					curr = curr.next;
				}
				prev.next = prev.next.next;
			}

			size--;
			return true;
		}
		return false;
	}

	public void clear() {
		for (int index = 0; index < hashtable.length; index++) {
			hashtable[index] = null;
		}
		size = 0;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HashSet)) {
			return false;
		}

		HashSet<T> otherHashSet = (HashSet<T>) other;

		// check for equal capacities
		if (hashtable.length != otherHashSet.hashtable.length) {
			return false;
		}
		for (int index = 0; index < hashtable.length; index ++) {
			Node curr1 = hashtable[index], curr2 = otherHashSet.hashtable[index];
			while (curr1 != null && curr2 != null) {
				
				// check if the nodes are shared
				if (curr1 != curr2) {
					return false;
				}

				// two different nodes in memory; check if values are the same
				if (!curr1.value.equals(curr2.value)) {
					return false;
				}
				curr1 = curr1.next;
				curr2 = curr2.next;

				// check if one of 'curr1' and 'curr2' is null (indicates the chains aren't of equal length)
				if ((curr1 == null && curr2 != null) || (curr1 != null && curr2 == null)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns a shallow copy of this HashSet instance; the elements aren't cloned.
	 */
	@Override
	public HashSet<T> clone() {
		HashSet<T> clonedSet = new HashSet<>(hashtable.length);
		clonedSet.size = size;
		for (int index = 0; index < hashtable.length; index ++) {
			clonedSet.hashtable[index] = hashtable[index];
		}
		return clonedSet;
	}
	

	@Override
	public Iterator<T> iterator() {
		return toList().iterator();
	}

	// Technically, sets are unordered, so there's no point in reversing one.
	private Iterator<T> reverse_iterator() {
		return reverse(toList()).iterator();
	}

	private List<T> reverse(List<T> list) {
		int beg = 0, end = list.size() - 1;
		while (beg < end) {
			T temp = list.get(beg);
			list.set(beg, list.get(end));
			list.set(end, temp);
			beg ++;
			end --;
		}
		return list;
	}

	private void rehash() {
		// Step 1: Choose new 'm' (AKA capacity); we'll double the length of the table
		int newCap = hashtable.length * 2;

		// Step 2: Allocate space for the new table
		HashSet<T>.Node[] newTable = new HashSet.Node[newCap];

		// Step 3: Use a new hash function given the new capacity to rehash/reinsert all keys
		for (int index = 0; index < hashtable.length; index++) {
			Node curr = hashtable[index];

			// rehash the entire chain
			while (curr != null) {
				Node temp = curr.next;
				int insertIndex = Math.abs(curr.value.hashCode() % newCap);
				curr.next = null;
				if (newTable[insertIndex] != null) {
					curr.next = newTable[insertIndex];
				}
				newTable[insertIndex] = curr;

				curr = temp;
				if (temp != null) {
					temp = temp.next;
				}
			}

		}
		hashtable = newTable;
	}

	/**
	 * Creates a new list consisting of the set's elements
	 * @param set
	 * @return
	 */
	public List<T> toList() {
		List<T> ret = new LinkedList<>();
		for (int index = 0; index < hashtable.length; index++) {

			for (Node curr = hashtable[index]; curr != null; curr = curr.next) {
				ret.add(curr.value);
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[Empty HashSet]";
		}
		StringBuilder sb = new StringBuilder("");
		for (int index = 0; index < hashtable.length; index++) {
			sb.append(index + ":\t");

			for (Node curr = hashtable[index]; curr != null; curr = curr.next) {
				sb.append(curr.value + " -> ");
			}
			sb.append("null\n");
		}
		return sb.toString();
	}
}
