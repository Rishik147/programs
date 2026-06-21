package collections.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int q1 = list.stream()
                .filter(i -> i % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of even numbers: " + q1);

        System.out.println("----------");

        List<String> list1 = List.of("formula1", "racing", "battery");
        List<String> q2 = list1.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase list: " + q2);

        System.out.println("----------");

        List<String> list2 = List.of("hi", "java", "stream", "api", "lambda");
        List<String> q3 = list2.stream()
                .filter(s -> s.length() > 4)
                .toList();
        System.out.println("String having more than 4 characters: " + q3);

        System.out.println("----------");

        List<Integer> list3 = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
        Integer q4 = list3.stream()
                .filter(x -> Collections.frequency(list3, x) > 1)
                .findFirst().get();
        System.out.println("First duplicate element in list: " + q4);

        System.out.println("----------");

        List<List<Integer>> lists = List.of(List.of(1, 2, 3), List.of(4, 5), List.of(6, 7, 8));
        List<Integer> q5 = lists.stream()
                .flatMap((List<Integer> l) -> l.stream().map(x -> x * 2))
                .toList();
        System.out.println("Flattened list by multiplying every element with 2: " + q5);

        System.out.println("----------");

        String sentence = "to be or not to be that is the question";
        Map<String, Long> q6 = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Frequency: " + q6);

        System.out.println("----------");

        Map<Boolean, List<Integer>> q7 = list.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println("Partitioning even and odd: " + q7);

        System.out.println("----------");

        String q8 = list2.stream()
                .collect(Collectors.joining(":"));
        System.out.println(q8);

        System.out.println("----------");

        Stream<Integer> stream = Stream.iterate(10, (Integer i) -> i + 10).limit(10);
        stream.forEach(System.out::println);

    }
}
