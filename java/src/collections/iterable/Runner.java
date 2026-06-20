package collections.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println(list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);

            if (next == 3)
                iterator.remove();
        }
        System.out.println("----------");
        list.forEach(System.out::println);
    }
}
