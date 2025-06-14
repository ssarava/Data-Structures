package Trees.Tests;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import Trees.impl.BSTs.MyTreap;

public class MyTreapTests {

    private boolean widespreadConstructor(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap1 = new MyTreap<>();
        treap1.insert(keys, priorities);
        MyTreap<Integer> treap2 = new MyTreap<>(treap1);
        assertTrue(treap1.equals(treap2));
        return true;
    }

    @Test
    public void testConstructorAndIsEmpty() {
        MyTreap<Integer> treap = new MyTreap<>();
        assertTrue(treap.size() == 0);
        assertTrue(treap.size() == new MyTreap<>().size());
        assertTrue(treap.isEmpty());


        Integer[] arr = null, priorities = null;

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 90, 120};
        assertTrue(widespreadConstructor(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadConstructor(arr, priorities));
    }

    private boolean widespreadSize(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);
        Integer[] checkList = Arrays.copyOf(keys, keys.length);
        assertTrue(treap.size() == checkList.length);
        return true;
    }

    @Test
    public void testSize() {
        Integer[] arr = null, priorities = null;

        // left

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 90, 120};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55};
        priorities = new Integer[] {100, 90, 120, 80};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 65};
        priorities = new Integer[] {100, 90, 120, 70};
        assertTrue(widespreadSize(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        priorities = new Integer[] {100, 90, 80, 70, 95};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43};
        priorities = new Integer[] {100, 90, 70, 95, 60};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 48};
        priorities = new Integer[] {100, 90, 70, 95, 50};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        priorities = new Integer[] {100, 90, 70, 95, 60, 50};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70};
        priorities = new Integer[] {100, 90, 85, 95};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70, 72};
        assertTrue(widespreadSize(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        priorities = new Integer[] {100, 90, 80, 70, 60, 85};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        priorities = new Integer[] {100, 90, 70, 60, 85, 50};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                300};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                160};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                135};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 137};
        assertTrue(widespreadSize(arr, priorities));

        // right

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 110, 80};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 60, 45};
        priorities = new Integer[] {100, 110, 80, 90};
        assertTrue(widespreadSize(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        priorities = new Integer[] {100, 90, 95, 80};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35, 45};
        priorities = new Integer[] {100, 90, 95, 70, 80};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35};
        priorities = new Integer[] {100, 90, 95, 70};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70, 57};
        priorities = new Integer[] {100, 90, 95, 60, 50};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 95, 60};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 200, 60};
        assertTrue(widespreadSize(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 30, 10, 5};
        priorities = new Integer[] {100, 90, 80, 85};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 30, 10, 5, 7};
        priorities = new Integer[] {100, 90, 80, 85, 70};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 80, 85, 50};
        assertTrue(widespreadSize(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75, 67};
        priorities = new Integer[] {100, 90, 80, 85, 50, 40};
        assertTrue(widespreadSize(arr, priorities));
    }

    private boolean widespreadInsertRotation(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);
        Integer[] checkList = Arrays.copyOf(keys, keys.length);
        Arrays.sort(checkList);

        List<Integer> inorder = new LinkedList<>(treap.inorder());
        List<Integer> check = new LinkedList<>(Arrays.asList(checkList));
        assertTrue(inorder.equals(check));

        do {
            Collections.shuffle(check);
        } while (check.equals(inorder));
        assertFalse(inorder.equals(check));

        do {
            Collections.shuffle(check);
        } while (check.equals(inorder));
        assertFalse(inorder.equals(check));
        return true;
    }

    @Test
    public void testInsertLeftRotation() {
        Integer[] arr = null, priorities = null;

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 90, 120};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55};
        priorities = new Integer[] {100, 90, 120, 80};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 65};
        priorities = new Integer[] {100, 90, 120, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        priorities = new Integer[] {100, 90, 80, 70, 95};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43};
        priorities = new Integer[] {100, 90, 70, 95, 60};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 48};
        priorities = new Integer[] {100, 90, 70, 95, 50};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        priorities = new Integer[] {100, 90, 70, 95, 60, 50};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70};
        priorities = new Integer[] {100, 90, 85, 95};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70, 72};
        assertTrue(widespreadInsertRotation(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        priorities = new Integer[] {100, 90, 80, 70, 60, 85};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        priorities = new Integer[] {100, 90, 70, 60, 85, 50};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                300};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                160};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                135};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 137};
        assertTrue(widespreadInsertRotation(arr, priorities));
    }

    @Test
    public void testInsertRightRotation() {
        Integer[] arr = null, priorities = null;

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 110, 80};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 60, 45};
        priorities = new Integer[] {100, 110, 80, 90};
        assertTrue(widespreadInsertRotation(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        priorities = new Integer[] {100, 90, 95, 80};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35, 45};
        priorities = new Integer[] {100, 90, 95, 70, 80};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35};
        priorities = new Integer[] {100, 90, 95, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70, 57};
        priorities = new Integer[] {100, 90, 95, 60, 50};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 95, 60};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 200, 60};
        assertTrue(widespreadInsertRotation(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 30, 10, 5};
        priorities = new Integer[] {100, 90, 80, 85};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 30, 10, 5, 7};
        priorities = new Integer[] {100, 90, 80, 85, 70};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 80, 85, 50};
        assertTrue(widespreadInsertRotation(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75, 67};
        priorities = new Integer[] {100, 90, 80, 85, 50, 40};
        assertTrue(widespreadInsertRotation(arr, priorities));
    }

    // ensure that every inserted key exists in the tree (i.e no node was lost)
    private boolean widespreadContains(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);

        for (Integer i : keys) {
            assertTrue(treap.contains(i));
        }
        return true;
    }

    @Test
    public void testContains() {
        Integer[] arr = null, priorities = null;

        // left

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 90, 120};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55};
        priorities = new Integer[] {100, 90, 120, 80};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 65};
        priorities = new Integer[] {100, 90, 120, 70};
        assertTrue(widespreadContains(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        priorities = new Integer[] {100, 90, 80, 70, 95};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43};
        priorities = new Integer[] {100, 90, 70, 95, 60};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 48};
        priorities = new Integer[] {100, 90, 70, 95, 50};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        priorities = new Integer[] {100, 90, 70, 95, 60, 50};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70};
        priorities = new Integer[] {100, 90, 85, 95};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70, 72};
        assertTrue(widespreadContains(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        priorities = new Integer[] {100, 90, 80, 70, 60, 85};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        priorities = new Integer[] {100, 90, 70, 60, 85, 50};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                300};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                160};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                135};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 137};
        assertTrue(widespreadContains(arr, priorities));

        // right

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 110, 80};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 60, 45};
        priorities = new Integer[] {100, 110, 80, 90};
        assertTrue(widespreadContains(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        priorities = new Integer[] {100, 90, 95, 80};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35, 45};
        priorities = new Integer[] {100, 90, 95, 70, 80};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35};
        priorities = new Integer[] {100, 90, 95, 70};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70, 57};
        priorities = new Integer[] {100, 90, 95, 60, 50};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 95, 60};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 200, 60};
        assertTrue(widespreadContains(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 30, 10, 5};
        priorities = new Integer[] {100, 90, 80, 85};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 30, 10, 5, 7};
        priorities = new Integer[] {100, 90, 80, 85, 70};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 80, 85, 50};
        assertTrue(widespreadContains(arr, priorities));

        arr = new Integer[] {50, 60, 70, 65, 75, 67};
        priorities = new Integer[] {100, 90, 80, 85, 50, 40};
        assertTrue(widespreadContains(arr, priorities));
    }

    private boolean widespreadEquals(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap1 = new MyTreap<>(), treap2 = new MyTreap<>();
        treap1.insert(keys, priorities);
        treap2.insert(keys, priorities);

        assertTrue(treap1.equals(treap2));
        return true;
    }

    @Test
    public void testEquals() {
        Integer[] arr = null, priorities = null;

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 90, 120};
        assertTrue(widespreadEquals(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadEquals(arr, priorities));
    }

    private boolean widespreadDeleteRotation(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);
        MyTreap<Integer> referenceTreap = new MyTreap<>(treap);
        List<Integer> checkList = new LinkedList<>(Arrays.asList(keys));
        Collections.sort(checkList);
        int deletedSims = 0;

        while (deletedSims < keys.length) {
            for (Integer i : checkList) {
                treap.delete(i);

            }
            Collections.shuffle(checkList);
            treap = new MyTreap<>(referenceTreap);
            deletedSims++;
        }
        return true;
    }

    private boolean testPreorderDeletion(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);

        List<Integer> preorder = treap.preorder();
        Object[] initialPreorder = treap.preorder().toArray();
        int index = 0;

        for (Integer i : preorder) {
            treap.delete(i);
            assertFalse(treap.contains(initialPreorder[index++]));

        }
        assertTrue(treap.size() == 0);
        return true;
    }

    private boolean testPostorderDeletion(Integer[] keys, Integer[] priorities) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys, priorities);

        List<Integer> postorder = treap.postorder();
        Object[] initialPostorder = treap.postorder().toArray();
        int index = 0;

        for (Integer i : postorder) {
            treap.delete(i);
            assertFalse(treap.contains(initialPostorder[index++]));

        }
        assertTrue(treap.size() == 0);
        return true;
    }

    @Test
    public void testTraversalDeletions() {
        Integer[] arr = null, priorities = null;

        // left

        arr = new Integer[] {50, 40, 60, 55, 65};
        priorities = new Integer[] {100, 90, 120, 80, 70};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 55};
        priorities = new Integer[] {100, 90, 120, 80};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));


        arr = new Integer[] {50, 40, 60, 65};
        priorities = new Integer[] {100, 90, 120, 70};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45};
        priorities = new Integer[] {100, 90, 80, 70, 95};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43};
        priorities = new Integer[] {100, 90, 70, 95, 60};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 48};
        priorities = new Integer[] {100, 90, 70, 95, 50};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 43, 48};
        priorities = new Integer[] {100, 90, 70, 95, 60, 50};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70};
        priorities = new Integer[] {100, 90, 85, 95};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 70, 65, 75};
        priorities = new Integer[] {100, 90, 85, 95, 70, 72};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        // inserted.parent.parent.parent = root
        arr = new Integer[] {50, 40, 60, 30, 45, 47};
        priorities = new Integer[] {100, 90, 80, 70, 60, 85};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 45, 47, 46};
        priorities = new Integer[] {100, 90, 70, 60, 85, 50};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                300};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                160};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 20, 4,
                135};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        priorities = new Integer[] {200, 150, 140, 130, 120, 110, 103, 90, 101, 105, 107, 10, 137};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        // right

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        priorities = new Integer[] {100, 110, 80};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 60, 45};
        priorities = new Integer[] {100, 110, 80, 90};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        // inserted.parent.parent = root
        arr = new Integer[] {50, 40, 30, 45};
        priorities = new Integer[] {100, 90, 95, 80};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35, 45};
        priorities = new Integer[] {100, 90, 95, 70, 80};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 40, 30, 35};
        priorities = new Integer[] {100, 90, 95, 70};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70, 57};
        priorities = new Integer[] {100, 90, 95, 60, 50};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

        arr = new Integer[] {50, 60, 55, 70};
        priorities = new Integer[] {100, 90, 95, 60};
        assertTrue(widespreadDeleteRotation(arr, priorities));
        assertTrue(testPreorderDeletion(arr, priorities));
        assertTrue(testPostorderDeletion(arr, priorities));

    }

    private boolean widespreadDelete(Integer[] keys) {
        MyTreap<Integer> treap = new MyTreap<>();
        treap.insert(keys);
        // Integer[] checkList = Arrays.copyOf(keys, keys.length);
        // Arrays.sort(checkList);

        // List<Integer> inorder = new LinkedList<>(treap.inorder());
        // List<Integer> check = new LinkedList<>(Arrays.asList(checkList));
        // assertTrue(inorder.equals(check));

        // do {
        // Collections.shuffle(check);
        // } while (check.equals(inorder));
        // assertFalse(inorder.equals(check));

        // do {
        // Collections.shuffle(check);
        // } while (check.equals(inorder));
        // assertFalse(inorder.equals(check));
        return true;
    }

    @Test
    public void testManualDeletions() {
        MyTreap<Integer> treap = new MyTreap<>();
        System.out.println(treap);
        Integer[] arr = null;

        // left

        // inserted.parent = root
        arr = new Integer[] {50, 40, 60};
        treap.insert(arr);
        assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 55, 65};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 55};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 65};
        // assertTrue(widespreadDelete(arr));

        // // inserted.parent.parent = root
        // arr = new Integer[] {50, 40, 60, 30, 45};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 45, 43};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 45, 48};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 45, 43, 48};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 70};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 70, 65};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 70, 75};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 70, 65, 75};
        // assertTrue(widespreadDelete(arr));

        // // inserted.parent.parent.parent = root
        // arr = new Integer[] {50, 40, 60, 30, 45, 47};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 45, 47, 46};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65, 90, 150};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 20, 80, 5, 30, 60, 100, 1, 6, 25, 40, 55, 65};
        // assertTrue(widespreadDelete(arr));

        // // right

        // // inserted.parent = root
        // arr = new Integer[] {50, 40, 60};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 60, 45};
        // assertTrue(widespreadDelete(arr));

        // // inserted.parent.parent = root
        // arr = new Integer[] {50, 40, 30, 45};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 35, 45};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 40, 30, 35};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 60, 55, 70, 57};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 60, 55, 70};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 60, 55, 70};
        // assertTrue(widespreadDelete(arr));

        // // inserted.parent.parent.parent = root
        // arr = new Integer[] {50, 30, 10, 5};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 30, 10, 5, 7};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 60, 70, 65, 75};
        // assertTrue(widespreadDelete(arr));

        // arr = new Integer[] {50, 60, 70, 65, 75, 67};
        // assertTrue(widespreadDelete(arr));


    }
}
