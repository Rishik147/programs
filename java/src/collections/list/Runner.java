package collections.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class demonstrates the basic and advanced operations of the ArrayList.
 * ArrayList is backed by a dynamic array, providing O(1) access time but
 * O(n) time for insertions or removals in the middle due to element shifting.
 */
public class Runner {
    public static void main(String[] args) {
        // Initialize a generic list of Integers
        List<Integer> list1 = new ArrayList<>();
        list1.add(100);       // Appends to the end: O(1)
        list1.add(1, 200);    // Inserts at index 1: O(n) as elements to the right must shift
        list1.add(2, 300);    // Inserts at index 2: O(n)

        List<Integer> list2 = new ArrayList<>();
        list2.add(500);

        System.out.println(list1);
        System.out.println(list2);

        // addAll(index, collection): Inserts all elements of the specified collection into this list, 
        // starting at the specified position. Subsequent elements are shifted to the right.
        list1.addAll(2, list2);
        System.out.println(list1);

        // replaceAll(operator): Performs a bulk update by applying a UnaryOperator to every element.
        // This is more efficient than manually iterating and calling set().
        list1.replaceAll(x -> x * 2);
        System.out.println(list1);

        // Sorting using the modern List.sort API (replaces Collections.sort)
        // reverseOrder() sorts in descending order
        list1.sort(Comparator.reverseOrder());
        System.out.println(list1);

        // Positional access: O(1)
        System.out.println(list1.get(1));

        // set(index, element): Replaces the element at the index (does NOT shift)
        list1.set(1, 50);
        System.out.println(list1);

        // remove(index): Removes the element and shifts subsequent elements to the left
        // This is O(n) in the worst case (removing from the beginning)
        list1.remove(0);
        System.out.println(list1);

        // ListIterator provides bidirectional traversal and allows for element modification
        // (add, set, remove) without risking a ConcurrentModificationException.

        // We initialize the iterator at the end of the list by passing the current size.
        // The cursor is now positioned after the last element.
        ListIterator<Integer> listIterator = list1.listIterator(list1.size());
        System.out.println("Size before iteration: " + list1.size());

        while (listIterator.hasPrevious()) {
            // previous() returns the element behind the cursor and moves the cursor back one position.
            Integer element = listIterator.previous();
            System.out.println("Iterating backward, element: " + element);

            // remove() deletes the last element returned by the iterator.
            // This is the standard, thread-safe way to prune a list while traversing.
            listIterator.remove();
        }
        System.out.println("Size after clearing via ListIterator: " + list1.size());
    }
}
