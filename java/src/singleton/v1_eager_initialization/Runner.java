package singleton.v1_eager_initialization;

/**
 * Singleton pattern using Eager Initialization.
 * The instance is created as soon as the class is loaded into memory.
 */
class Singleton {
    // The JVM guarantees that the static instance is created in a thread-safe manner during class loading.
    // Making it 'final' ensures the reference cannot be changed once initialized.
    // DRAWBACK: Resource Waste. This instance is created even if the application never calls getInstance().
    // If this object is resource-heavy, it impacts the application's startup time and memory footprint.
    // DRAWBACK: Exception Handling. If instantiation fails (e.g., DB connection fails), 
    // it throws an ExceptionInInitializerError, which can be difficult to recover from gracefully 
    // compared to handling it within a method.
    private static final Singleton INSTANCE = new Singleton();

    // Private constructor prevents instantiation from outside the class.
    private Singleton() {
    }

    /**
     * Global access point for the unique instance of the Singleton class.
     * @return The pre-initialized Singleton instance.
     * 
     * DRAWBACK: No Parameterization. You cannot pass arguments to this method to configure the singleton 
     * on its first creation, as it is already instantiated.
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }
}

public class Runner {
    public static void main(String[] args) {
        // Retrieve the singleton instance twice to verify identity
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        // If both hash codes are the same, it confirms that both variables point to the same object in memory.
        System.out.println("Instance 1 HashCode: " + instance1.hashCode());
        System.out.println("Instance 2 HashCode: " + instance2.hashCode());

        if (instance1 == instance2) {
            System.out.println("Success: Both variables point to the same instance.");
        }
    }
}
