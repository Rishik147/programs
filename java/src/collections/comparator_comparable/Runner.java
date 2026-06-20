package collections.comparator_comparable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Employee class implements Comparable to define its "Natural Ordering".
 * Natural ordering is the default sorting logic for the object (in this case, by age).
 */
class Employee implements Comparable<Employee> {
    private int age;
    private String name;

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.age, o.getAge());
    }
}

public class Runner {
    public static void main(String[] args) {
        // Initialize list with some data
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(55, "Alex"));
        list.add(new Employee(55, "Lando"));
        list.add(new Employee(45, "John"));
        System.out.println(list);

        // 1. Custom Sort: Using Comparator fluent API.
        // Sorts by age first, then by name for employees with the same age.
        list.sort(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName));
        System.out.println("Sorted by age then name: " + list);

        // 2. Custom Sort: Using a Lambda expression.
        // Sorts by name in descending order (Z-A).
        list.sort((Employee s1, Employee s2) -> s2.getName().compareTo(s1.getName()));
        System.out.println("Sorted by name descending: " + list);

        // 3. Natural Sort: Uses the compareTo method defined in the Employee class.
        // Passing 'null' to list.sort() tells Java to use the Comparable implementation.
        list.sort(null);
        System.out.println("Natural order (by age): " + list);
    }
}
