package singleton.v5_static_inner_class;

/**
 * Singleton implementation using the Initialization-on-demand holder idiom.
 * This provides lazy initialization and is thread-safe without explicit synchronization.
 */
class Singleton {
    // Private constructor prevents instantiation from other classes.
    private Singleton() {
    }

    /**
     * Returns the singleton instance.
     * The INSTANCE_HOLDER class is not loaded into memory until this method is called.
     * 
     * DRAWBACKS:
     * 1. Reflection: A caller can use reflection to change the constructor's 
     *    accessibility and create a second instance.
     * 2. Serialization: If this class implements Serializable, deserialization 
     *    will create a new instance unless readResolve() is implemented.
     * 3. No Parameters: You cannot pass arguments to the constructor to configure 
     *    the singleton during its initial creation.
     */
    public static Singleton getInstance() {
        return INSTANCE_HOLDER.INSTANCE;
    }

    /**
     * The JVM delays loading this class until it is actually referenced in getInstance().
     * Class loading is guaranteed by the JVM to be thread-safe, so no 'synchronized' 
     * keyword is needed.
     */
    private static class INSTANCE_HOLDER {
        /*
         * The 'final' keyword ensures that the instance is fully initialized 
         * before any thread can access it, preventing "partially initialized 
         * object" issues.
         */
        private static final Singleton INSTANCE = new Singleton();
    }
}

public class Runner {
    public static void main(String[] args) {
        // Accessing the singleton instance
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        // Printing hash codes to verify it's the same object
        System.out.println("Instance 1 HashCode: " + instance1.hashCode());
        System.out.println("Instance 2 HashCode: " + instance2.hashCode());

        if (instance1 == instance2) {
            System.out.println("Success: Both references point to the same instance.");
        }
    }
}
