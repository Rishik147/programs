package collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Demonstrates HashSet, LinkedHashSet and TreeSet with short examples.
 * - HashSet: no guaranteed order, fast O(1) add/contains/remove on average.
 * - LinkedHashSet: preserves insertion order (uses doubly-linked list internally).
 * - TreeSet: sorted order (implements NavigableSet), operations are O(log n).
 */
public class Runner {
    public static void main(String[] args) {
        // HashSet example: uniqueness, no ordering guarantee
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(2);
        hashSet.add(2); // duplicate ignored
        hashSet.add(1);
        System.out.println("HashSet size: " + hashSet.size()); // 2
        System.out.println("HashSet contents (unordered): " + hashSet);
        System.out.println("HashSet contains 2? " + hashSet.contains(2));

        System.out.println("----------");

        // LinkedHashSet example: preserves insertion order
        Set<Integer> linked = new LinkedHashSet<>();
        linked.add(3);
        linked.add(1);
        linked.add(2);
        linked.add(1); // duplicate ignored
        // Iteration will follow insertion order: 3,1,2
        System.out.println("LinkedHashSet (insertion order): " + linked);

        System.out.println("----------");

        // TreeSet example: sorted order (natural ordering for Integer)
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(5);
        tree.add(1);
        tree.add(4);
        tree.add(2);
        // TreeSet stores elements sorted: 1,2,4,5
        System.out.println("TreeSet (sorted): " + tree);
        // Useful TreeSet operations
        System.out.println("First (smallest): " + tree.first());
        System.out.println("Last (largest): " + tree.last());
        System.out.println("Lower than 4: " + tree.lower(4)); // greatest < 4 -> 2
        System.out.println("Higher than 4: " + tree.higher(4)); // least > 4 -> 5

        // Note: TreeSet (without custom Comparator) does not allow null elements because
        // comparisons would throw NullPointerException when ordering operations are performed.
    }
}
