package singleton.v4_synchronized_method;

import java.util.Objects;

/**
 * Singleton implementation using Double-Checked Locking (DCL).
 * This pattern aims to reduce synchronization overhead by only locking
 * when the instance is null.
 */
class Singleton {
    /**
     * Without volatile, another thread might see a partially initialized INSTANCE
     * due to instruction reordering by the JVM compiler or CPU.
     * Volatile ensures that the write to INSTANCE happens-before any subsequent reads.
     */
    private static volatile Singleton INSTANCE;

    // Private constructor prevents external instantiation.
    private Singleton() {
    }

    /**
     * Returns the singleton instance using the Double-Checked Locking idiom.
     * DRAWBACKS:
     * 1. Complexity: It is much harder to implement correctly than Eager or Enum versions.
     * 2. Volatile Requirement: It relies on the 'volatile' keyword (Java 5+), which
     * developers often forget, leading to extremely hard-to-debug concurrency bugs.
     * 3. Reflection/Serialization: Like other class-based singletons, it can still be
     * broken by reflection or multiple class loaders.
     */
    public static Singleton getInstance() {
        // First check (no locking): If instance exists, return it immediately for performance.
        if (Objects.isNull(INSTANCE)) {
            // Synchronize on the class object to ensure only one thread creates the instance.
            synchronized (Singleton.class) {
                // Second check (with locking): A thread might have initialized 
                // the instance while this thread was waiting for the lock.
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

public class Runner {
    public static void main(String[] args) {
        // Fetching the singleton instances
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        // Verify both references point to the same memory address
        System.out.println("Instance 1 HashCode: " + instance1.hashCode());
        System.out.println("Instance 2 HashCode: " + instance2.hashCode());
        System.out.println("Are they the same? " + (instance1 == instance2));
    }
}
