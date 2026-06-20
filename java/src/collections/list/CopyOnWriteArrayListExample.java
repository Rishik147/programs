package collections.list;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class demonstrates the behavior of CopyOnWriteArrayList.
 * Notice that we can modify the list while iterating over it without
 * triggering a ConcurrentModificationException.
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        // Thread-safe list implementation
        List<String> list = new CopyOnWriteArrayList<>();

        list.add("Java");
        list.add("Python");
        list.add("C++");

        System.out.println("Initial List: " + list);

        // Obtain an iterator (this captures a 'snapshot' of the array)
        Iterator<String> iterator = list.iterator();

        // We can modify the list AFTER the iterator is created
        list.add("Rust");
        list.remove("Python");

        System.out.println("--- Iterating through Snapshot ---");
        while (iterator.hasNext()) {
            String language = iterator.next();
            // The iterator will NOT show "Rust" and WILL show "Python"
            // because it is iterating over the snapshot taken at line 24.
            System.out.println("Iterator sees: " + language);
        }

        System.out.println("Final List state: " + list);

        /*
         * IMPORTANT NOTE:
         * Iterators of CopyOnWriteArrayList do NOT support the remove() method.
         * Attempting to call iterator.remove() will throw UnsupportedOperationException.
         */
        try {
            iterator = list.iterator();
            if (iterator.hasNext()) {
                iterator.remove();
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected Exception: Iterators cannot modify CopyOnWriteArrayList.");
        }
    }
}