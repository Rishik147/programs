package collections.queue;

import java.util.*;

/**
 * Queue - A collection designed for holding elements prior to processing.
 * Typically, queues order elements in a FIFO (first-in-first-out) manner,
 * though PriorityQueues order elements according to a supplied comparator.
 * <p>
 * Summary of Queue methods:
 * <p>
 * Category    | Throws exception | Returns special value
 * ------------|------------------|----------------------
 * Insert      | add(e)           | offer(e)
 * Remove      | remove()         | poll()
 * Examine     | element()        | peek()
 * <p>
 * add(e):    Inserts element; returns true on success, throws IllegalStateException if no space.
 * remove():  Retrieves and removes head; throws NoSuchElementException if empty.
 * element(): Retrieves head without removing; throws NoSuchElementException if empty.
 * <p>
 * offer(e):  Inserts element; returns true on success, false if no space.
 * poll():    Retrieves and removes head; returns null if empty.
 * peek():    Retrieves head without removing; returns null if empty.
 */
public class Runner {
    public static void main(String[] args) {
        // ArrayDeque is a highly efficient Resizable-array implementation of the Deque interface.
        // It is generally faster than LinkedList when used as a Queue.
        // Note: ArrayDeque does not permit null elements.
        Queue<Integer> queue = new ArrayDeque<>();

        // Adding elements (FIFO behavior)
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));

        System.out.println("Polled: " + queue.poll()); // Returns 1
        System.out.println("Peeked: " + queue.peek()); // Returns 2
        System.out.println("Polled: " + queue.poll()); // Returns 2
        System.out.println("Polled: " + queue.poll()); // Returns 3
        System.out.println(queue.size());

//        System.out.println(queue.element()); // Throws exception as there are no elements in queue
//        System.out.println(queue.peek()); // Returns null as there are no elements in queue

        System.out.println("------------------");

        // PriorityQueue orders elements based on their natural ordering (Default: Min-Heap).
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        minQueue.offer(5);
        minQueue.offer(10);
        minQueue.offer(2);
        minQueue.offer(7);

        // Note: minQueue.toString() does NOT show elements in sorted order. 
        // It shows the internal heap array representation.
        System.out.println("Internal Heap Array: " + minQueue);

        // To get elements in sorted order, you must poll them.
        while (!minQueue.isEmpty()) {
            System.out.println("Removed from head: " + minQueue.poll());
        }

        System.out.println("---------------");

        // Providing a custom comparator to create a Max-Heap.
        // Using Comparator.reverseOrder() is a cleaner alternative to (a, b) -> Integer.compare(b, a).
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        maxQueue.offer(5);
        maxQueue.offer(10);
        maxQueue.offer(2);
        maxQueue.offer(7);
        System.out.println(maxQueue);

        while (!maxQueue.isEmpty()) {
            System.out.println("Removed from head: " + maxQueue.poll());
        }

        System.out.println("----------");

        // Deque (Double Ended Queue) can be used as a Stack (LIFO) or a Queue (FIFO).
        // ArrayDeque is generally preferred over the legacy Stack class.
        Deque<String> deque = new ArrayDeque<>();

        // --- Using Deque as a STACK (Last-In-First-Out) ---
        System.out.println("Using Deque as a Stack:");
        deque.push("Element 1 (Bottom)");
        deque.push("Element 2 (Middle)");
        deque.push("Element 3 (Top)");

        // peek() or peekFirst() looks at the top element without removing it
        System.out.println("Stack Top (peek): " + deque.peek());

        // pop() or removeFirst() removes from the top
        while (!deque.isEmpty()) {
            System.out.println("Popped: " + deque.pop());
        }

        System.out.println("----------");

        // --- Using Deque as a QUEUE (First-In-First-Out) ---
        // While you can use Queue<String> q = new ArrayDeque<>(), 
        // using the Deque reference allows you to be explicit about which end you are manipulating.
        System.out.println("Using Deque as a Queue:");

        deque.offerLast("First");
        deque.offerLast("Second");
        deque.offerLast("Third");

        // In FIFO, we poll from the front (head)
        System.out.println("Queue Head (peekFirst): " + deque.peekFirst());

        while (!deque.isEmpty()) {
            // pollFirst() is the same as poll() in a standard Queue
            System.out.println("Removed from Queue: " + deque.pollFirst());
        }

        /**
         * Thread safe versions
         * Queue - ConcurrentLinkedDeque
         * PriorityQueue - PriorityBlockingQueue
         * */

    }
}
