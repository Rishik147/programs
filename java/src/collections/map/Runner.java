package collections.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Hashmap - Array of Node<K,V>
 * Each node will have hash, key, value and next(Node<K,V>)
 * Default capacity = 16
 */

public class Runner {
    /**
     * Main method demonstrating common HashMap operations and behaviors.
     * Shows insertion, lookup, conditional updates, removal, and iteration.
     */
    public static void main(String[] args) {
        // Create an empty HashMap: keys are Integers, values are Strings
        Map<Integer, String> map = new HashMap<>();
        // size(): returns number of key-value mappings
        System.out.println(map.size()); // prints 0
        // isEmpty(): true when the map has no mappings
        System.out.println(map.isEmpty()); // prints true

        // put(key, value): inserts or replaces the value for a key
        map.put(1, "John"); // adds mapping 1->John
        map.put(2, "Max");  // adds mapping 2->Max
        map.put(21, "Payne"); // adds mapping 21->Payne
        System.out.println(map.size()); // prints 3
        System.out.println(map.isEmpty()); // prints false

        // containsKey(key): checks if a key is present
        System.out.println(map.containsKey(1)); // true
        // containsValue(value): checks if a value is present (scans values)
        System.out.println(map.containsValue("Max")); // true
        // get(key): returns the value for the key, or null if absent
        System.out.println(map.get(1)); // "John"
        // toString(): prints the map entries (implementation-dependent order)
        System.out.println(map);
        // remove(key): removes the mapping for the specified key
        map.remove(2); // removes key 2 (Max)
        System.out.println(map);

        // putIfAbsent(key, value): inserts value only if key is not already present
        map.putIfAbsent(21, "Payne"); // key 21 exists so no change
        map.putIfAbsent(4, "Sam"); // key 4 absent, so 4->Sam is inserted
        System.out.println("After putIfAbsent: " + map);

        // Examples for computeIfAbsent and computeIfPresent
        // computeIfAbsent: only computes and inserts when the key is absent or mapped to null
        // Here key 21 is already present, so the mapping is NOT changed.
        map.computeIfAbsent(21, k -> "NewPayne"); // no change for existing key
        // Key 3 is absent, so the mapping function is called and the result stored.
        map.computeIfAbsent(3, k -> "Anna");
        System.out.println("After computeIfAbsent: " + map);

        // computeIfPresent: only applies the remapping function when the key is present and non-null
        // Updates value for key 1 by appending " Doe"
        map.computeIfPresent(1, (k, v) -> v + " Doe");
        // Key 5 is absent, so nothing happens
        map.computeIfPresent(5, (k, v) -> "ShouldNotAppear");
        System.out.println("After computeIfPresent: " + map);

        // If the remapping function returns null, the entry is removed from the map
        map.computeIfPresent(21, (k, v) -> null); // removes key 21
        System.out.println("After computeIfPresent that returns null: " + map);

        // Iterate entries: preferred when both key and value are needed
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " | Value: " + entry.getValue());
        }

        // Iterate keys only
        for (Integer k : map.keySet()) {
            System.out.println("Keys: " + k);
        }

        // Iterate values only
        for (String s : map.values()) {
            System.out.println("Values: " + s);
        }

        System.out.println("--------");


        /**
         *
         * LinkedHashMap - Doubly-linked list + hash table implementation.
         * Each entry stores: hash, key, value, next (bucket chain), before, after (linked list pointers).
         *
         * Maintains predictable iteration order:
         *  - Insertion-order (default): entries are iterated in the order they were inserted.
         *  - Access-order (optional): when constructed with accessOrder=true, accesses (get/put)
         *    move the entry to the end. This is useful for implementing LRU caches.
         *
         * Common constructor for access-order behavior:
         *   new LinkedHashMap<>(initialCapacity, loadFactor, true);
         *
         * To implement an LRU cache: override removeEldestEntry(Map.Entry<K,V>) and return true
         * when the map size exceeds a desired capacity.
         *
         * Note: LinkedHashMap is not synchronized. For concurrent access, use
         * Collections.synchronizedMap(...) or a concurrent map implementation.
         */
//      Insertion-order example: iteration preserves insertion order
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(100, "Anne");
        linkedHashMap.put(101, "Joe");
        linkedHashMap.put(102, "Carl");
        linkedHashMap.put(103, "Putin");
        System.out.println(linkedHashMap);

        System.out.println("----------");

        /**
         * What "access order" means -
         * In access order mode, every time you read or write an entry (get, put, putIfAbsent, compute, etc.),
         * that entry is moved to the tail of the internal doubly-linked list. The head always holds
         * the least recently used entry.
         *
         * */
        LinkedHashMap<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put(1, "A");
        accessOrderMap.put(2, "B");
        accessOrderMap.put(3, "C");
        System.out.println(accessOrderMap);
        accessOrderMap.get(1); // access moves key 1 to the end in access-order map
        System.out.println(accessOrderMap);
        accessOrderMap.get(2); // access moves key 2 to the end in access-order map
        System.out.println(accessOrderMap);
        accessOrderMap.get(1); // access moves key 1 to the end in access-order map
        System.out.println(accessOrderMap);

        System.out.println("----------");

        /**
         * LinkedHashMap as LRU cache
         * */

        int capacity = 3;
        // LRU cache implemented by extending LinkedHashMap and overriding removeEldestEntry.
        // accessOrder=true makes the map maintain entries from least-recently-used (head)
        // to most-recently-used (tail). When size exceeds capacity, the eldest (LRU) entry
        // is removed automatically.
        LinkedHashMap<Integer, String> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > capacity;
            }
        };

        // Populate cache up to capacity
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println("Initial LRU cache: " + lruCache);

        // Access key 1 to make it most recently used (moves it to the tail)
        System.out.println("Accessing key 1 -> " + lruCache.get(1));
        System.out.println("After accessing 1: " + lruCache);

        // Insert a new entry; this will cause eviction of the least-recently-used entry (key 2)
        lruCache.put(4, "Four");
        System.out.println("After inserting 4 (should evict LRU): " + lruCache);

        // Demonstrate cache hit and miss
        System.out.println("Contains key 2? " + lruCache.containsKey(2)); // expected false
        System.out.println("Contains key 3? " + lruCache.containsKey(3)); // expected true

        // Access remaining entries to show order changes
        lruCache.get(3); // access moves 3 to most-recently-used
        System.out.println("After accessing 3: " + lruCache);

        // Insert another entry to trigger eviction
        lruCache.put(5, "Five");
        System.out.println("After inserting 5 (evict LRU): " + lruCache);

        /**
         * Tree map - It is sorted either by natural sorting order of key or by provided comparator
         * Based on red-black tree(self-balancing binary search tree)
         * */

//        Natural sorting order tree map
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "five");
        treeMap.put(4, "four");
        treeMap.put(1, "one");
        treeMap.put(2, "two");
        treeMap.put(0, "zero");
        System.out.println(treeMap);

//        Using custom comparator
        TreeMap<Integer, String> customTreeMap = new TreeMap<>((Integer k1, Integer k2) -> k2.compareTo(k1));
        customTreeMap.put(5, "five");
        customTreeMap.put(4, "four");
        customTreeMap.put(1, "one");
        customTreeMap.put(2, "two");
        customTreeMap.put(0, "zero");
        System.out.println(customTreeMap);


    }
}
