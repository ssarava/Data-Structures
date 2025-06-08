package Trees.Tests;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import Trees.impl.BSTs.MyBST;

public class MyBSTTests {
    
    private boolean widespreadConstructor(Integer[] keys) {
        MyBST<Integer> bst1 = new MyBST<>();
        bst1.insert(keys);
        MyBST<Integer> bst2 = new MyBST<>(bst1);
        assertTrue(bst1.equals(bst2));
        return true;
    }

    @Test
    public void testConstructorAndIsEmpty() {
        MyBST<Integer> treap = new MyBST<>();
        assertTrue(treap.size() == 0);
        assertTrue(treap.size() == new MyBST<>().size());
        assertTrue(treap.isEmpty());

        Integer[] arr = null;

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        assertTrue(widespreadConstructor(arr));

        arr = new Integer[] {50, 40, 60, 55, 65};
        assertTrue(widespreadConstructor(arr));
    }

    private boolean widespreadSize(Integer[] keys) {
        MyBST<Integer> treap = new MyBST<>();
        treap.insert(keys);
        Integer[] checkList = Arrays.copyOf(keys, keys.length);
        assertTrue(treap.size() == checkList.length);
        return true;
    }

    @Test
    public void testSize() {
        Integer[] arr = null;

        // left

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 55, 65};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 55};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 65};
        assertTrue(widespreadSize(arr));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 45, 43};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 45, 48};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 70};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 70, 65};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 70, 75};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        assertTrue(widespreadSize(arr));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        assertTrue(widespreadSize(arr));

        // right

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 60, 45};
        assertTrue(widespreadSize(arr));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 35, 45};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 40, 30, 35};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 60, 55, 70, 57};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 60, 55, 70};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 60, 55, 70};
        assertTrue(widespreadSize(arr));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 30, 10, 5};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 30, 10, 5, 7};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 60, 70, 65, 75};
        assertTrue(widespreadSize(arr));

        arr = new Integer[] {50, 60, 70, 65, 75, 67};
        assertTrue(widespreadSize(arr));
    }

    // ensure that every inserted key exists in the tree (i.e no node was lost)
    private boolean widespreadContains(Integer[] keys) {
        MyBST<Integer> treap = new MyBST<>();
        treap.insert(keys);

        for (Integer i : keys) {
            assertTrue(treap.contains(i));
        }
        return true;
    }

    @Test
    public void testContains() {
        Integer[] arr = null;

        // left

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 55, 65};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 55};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 65};
        assertTrue(widespreadContains(arr));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 45, 43};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 45, 48};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 70};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 70, 65};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 70, 75};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        assertTrue(widespreadContains(arr));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        assertTrue(widespreadContains(arr));

        // right

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 60, 45};
        assertTrue(widespreadContains(arr));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 35, 45};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 40, 30, 35};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 60, 55, 70, 57};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 60, 55, 70};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 60, 55, 70};
        assertTrue(widespreadContains(arr));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 30, 10, 5};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 30, 10, 5, 7};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 60, 70, 65, 75};
        assertTrue(widespreadContains(arr));

        arr = new Integer[] {50, 60, 70, 65, 75, 67};
        assertTrue(widespreadContains(arr));
    }

}
