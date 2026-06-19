package singleton.v3_synchronized_method;

import java.util.Objects;

/**
 * Singleton implementation using a Synchronized Method.
 * This approach achieves thread safety by ensuring only one thread can execute 
 * getInstance() at a time.
 */
class Singleton {
    private static Singleton INSTANCE;

    // Private constructor prevents instantiation from other classes.
    private Singleton() {
    }

    /**
     * Returns the singleton instance.
     * 
     * DRAWBACK: Performance Overhead.
     * Using 'synchronized' on the method header means that EVERY call to this method
     * requires acquiring a monitor lock. While this is necessary the first time to 
     * prevent multiple instances, it becomes a bottleneck once the instance is created.
     * Even for simple read operations, threads will have to wait in line, which can 
     * reduce performance in high-concurrency systems.
     */
    synchronized public static Singleton getInstance() {
        // Check if the instance is null before creating it.
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}

public class Runner {
    public static void main(String[] args) {
        // Accessing the singleton instance from the global access point.
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        // Printing hash codes to verify that both references point to the same object.
        System.out.println("Instance 1 HashCode: " + instance1.hashCode());
        System.out.println("Instance 2 HashCode: " + instance2.hashCode());

        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}
