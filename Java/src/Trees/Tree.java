package Trees;

import java.util.Collection;

public interface Tree<T> extends Collection<T> {
    
    void insert(T element);

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

    @Override
    default boolean containsAll(Collection<?> c) {
        System.out.println("tree 'containsAll()' run!");
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
}
