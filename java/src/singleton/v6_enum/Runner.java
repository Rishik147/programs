package singleton.v6_enum;

/**
 * Implementation of a Singleton using an Enum.
 * This approach is recommended as the best way
 * to implement singletons because it provides:
 * 1. Thread safety by default.
 * 2. Guaranteed single instance even during complex serialization or reflection attacks.
 */
enum Singleton {
    INSTANCE; // The unique instance of the singleton

    // Example method to demonstrate business logic within the singleton
    public void performAction() {
        System.out.println("Executing singleton logic...");
    }
}

public class Runner {
    public static void main(String[] args) {
        // Fetching the singleton instance twice
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;

        // Verifying that both variables point to the exact same instance
        System.out.println("Instance 1 HashCode: " + instance1.hashCode());
        System.out.println("Instance 2 HashCode: " + instance2.hashCode());

        // Demonstrating usage
        instance1.performAction();
    }
}
