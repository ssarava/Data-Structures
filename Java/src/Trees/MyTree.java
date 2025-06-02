package Trees;

import java.util.Collection;

public interface MyTree<T extends Comparable<T>> extends Collection<T> {
    
    /**
     * Returns {@code true} if this tree's structure changes as a result of
     * the call. Returns {@code false} if the specified element is a null
     * reference.<p>
     *
     * If this tree permits duplicates, {@code true} is always returned. If 
     * duplicates are not permited, {@code false} is returned if this tree already
     * contains the specified element, and {@code true} otherwise.<p>
     *
     * @param e element whose presence in this collection is to be ensured
     * @return {@code true} if this collection changed as a result of the
     *         call
     */
    boolean insert(T element);

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present.  More formally,
     * removes an element {@code e} such that
     * {@code Objects.equals(o, e)}, if
     * this collection contains one or more such elements.  Returns
     * {@code true} if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be deleted from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     * @throws UnsupportedOperationException if the {@code delete} operation
     *         is not supported by this collection
     */
    boolean delete(Object o);
}
