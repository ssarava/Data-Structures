package HashSet.impl.ClosedAddressing;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import LinkedList.impl.DoublyLinkedList;

/**
 * This immutable class is an implementation of Java's HashSet class.
 * It follows open hashing (closed addressing).
 * A singly-linked-list-like data structure supports separate chaining.
 */
public class HashSet<T extends Comparable<T>> implements Cloneable, Set<T> {

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

	/**
	 * This set is backed by an array rather than an ArrayList
	 */
	private Node[] hashtable;

	@SuppressWarnings("unchecked")
	public HashSet(int customCapacity) {
		if (customCapacity <= 0) {
			throw new IllegalArgumentException("Can't initialize a list of length " + customCapacity);
		}
		size = 0;
		hashtable = new HashSet.Node[customCapacity];
	}

	public HashSet() {
		this(DEFAULT_TABLE_SIZE);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
		int targInd = Math.abs(o.hashCode() % hashtable.length); // hashcode mod capacity to find appropriate insertion index

		// search through chain
		for (Node curr = hashtable[targInd]; curr != null; curr = curr.next) {
			if (curr.value.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean add(T o) {
		if (o == null) {
            throw new NullPointerException("Cannot remove a null reference from the list.");
        }
		if (!contains(o)) {
			int insertIndex = Math.abs(o.hashCode() % hashtable.length);
			Node node = new Node(o);
			if (hashtable[insertIndex] != null) {
				node.next = hashtable[insertIndex];
			}
			hashtable[insertIndex] = node;
			size++;

			// rehash if overfull
			double ratio = ((double) size) / ((double) hashtable.length);
			if (ratio > LOAD_FACTOR) {
				rehash();
			}
			return true;
		}
		return false;

	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(Object o) {
		if (contains(o)) {
			// remove the object from its chain
			int targIndex = Math.abs(o.hashCode() % hashtable.length);
			Node curr = hashtable[targIndex];
			if (curr.value.equals(o)) {
				hashtable[targIndex] = null;
			} else {
				Node prev = null;
				while (!curr.value.equals(o)) {
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

		@SuppressWarnings("unchecked")
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

	// Technically, sets are unordered, so it doesn't make sense to reverse one.
	protected Iterator<T> reverse_iterator() {
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
		@SuppressWarnings("unchecked")
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

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
        int index = 0;
		for (T element: toList()) {
			arr[index ++]  = element;
		} 
        return arr;
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length != size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Object[] pointer = a; // needed to resolve compiler type conversion conflict
        int index = 0;
        for (Object element: toList()) {
			pointer[index ++]  = element;
		} 
        return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (this == c) {
            return true;
        }
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		 if (c == null) {
            throw new NullPointerException("Cannot add a null reference to the list.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Adding elements to this list, from this list, isn't allowed.");
        }
        int initialSize = size;
        for (T element : c) {
            add(element);
        }
        return size == initialSize;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
		if (this == c) {
            return false;
        }
        int initialSize = 0;
        DoublyLinkedList<T> toRemove = new DoublyLinkedList<>();
        for (T element: this) {
            if (!c.contains(element)) {
                toRemove.add(element);
            }
        }
        for (T element: toRemove) {
            remove(element);
        }
        return size == initialSize;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) {
            throw new NullPointerException("Cannot access elements given a null reference.");
        }
        if (this == c) {
            throw new ConcurrentModificationException("Removing elements to this list, from this list, isn't allowed. Try 'clear()'" );
        }
        int initialSize = size;
        for (Object element : c) {
            remove(element);
        }
        return size == initialSize;
	}
}
