package singleton.v2_lazy_initialization;

import java.util.Objects;

/**
 * Singleton implementation using Lazy Initialization.
 * The instance is created only when getInstance() is called for the first time.
 */
class Singleton {
    // The instance is initialized to null by default.
    private static Singleton INSTANCE;

    // Private constructor ensures no other class can instantiate this.
    private Singleton() {
    }

    /**
     * Returns the singleton instance.
     * 
     * DRAWBACK: This implementation is NOT thread-safe.
     * In a multi-threaded environment, two threads could check 'isNull' at the 
     * same time, both find it true, and create two different instances.
     */
    public static Singleton getInstance() {
        if (Objects.isNull(INSTANCE)) {
            // Thread A and Thread B could both reach this line simultaneously.
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}

public class Runner {
    public static void main(String[] args) {
        // Accessing the singleton instance
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        // Printing hash codes to verify if they are the same object
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        
        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}
