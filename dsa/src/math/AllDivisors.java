package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllDivisors {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(divisors(6)));
        System.out.println(Arrays.toString(divisors(7)));
    }

    public static int[] divisors(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}
