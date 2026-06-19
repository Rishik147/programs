package lamdba_expression;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface Task {
    void todo(String taskName);
}

public class Runner {
    public static void main(String[] args) {
        Task task = (taskName) -> System.out.println("Performing task: " + taskName);
        task.todo("study");
        task.todo("swim");
        System.out.println(task.hashCode());

        Consumer<String> consumer = (s) -> System.out.println(s); // System.out::println
        consumer.accept("Test consumer");

        Supplier<String> supplier = () -> "hello";
        System.out.println(supplier.get());

        Function<Integer, Boolean> isEven = (x) -> x % 2 == 0;
        System.out.println(isEven.apply(2));
        System.out.println(isEven.apply(3));

        Predicate<String> isEvenLength = (s) -> s.length() % 2 == 0;
        System.out.println(isEvenLength.test("hello"));
        System.out.println(isEvenLength.test("race"));
    }
}
