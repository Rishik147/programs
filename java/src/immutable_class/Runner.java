package immutable_class;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the implementation of an immutable object in Java.
 * Marking the class as 'final' prevents subclassing, which could otherwise lead to mutable behavior.
 */
final class MyClass {
    // Fields are private and final to ensure they cannot be modified after construction.
    private final String name;
    private final List<String> list;

    public MyClass(String name, List<String> list) {
        this.name = name;
        // Defensive Copy: We create a new ArrayList from the input list.
        // This prevents the caller from modifying the internal state of MyClass 
        // by changing the original list passed to the constructor.
        this.list = new ArrayList<>(list);
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the list.
     * Returning the original reference would allow the caller to modify the internal list content.
     */
    public List<String> getList() {
        // Defensive Copy: Return a new list so the internal state remains protected.
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "MyClass{name='" + name + "', list=" + list + "}";
    }
}

public class Runner {
    public static void main(String[] args) {
        // Initialize a mutable list
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("C");

        // Create an instance of the immutable class
        MyClass myClass = new MyClass("John", list);
        System.out.println("Initial object: " + myClass);

        // Attempting to modify the list returned by the getter.
        // Since getList() returns a copy, this modification will NOT affect the myClass instance.
        myClass.getList().add("DD");

        // Verification: The output should be identical to the first print statement.
        System.out.println("After attempting modification: " + myClass);
    }
}
