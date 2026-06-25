package math;

/*
 * A perfect number is a number whose proper divisors add up to the number itself
 *
 * In n = 6
 * 1 + 2 + 3 = 6
 * Op = true
 *
 * In n = 4
 *  1 + 2 = 3
 * Op = false
 *
 * In n = 28
 * 1 + 2 + 4 + 7 + 14 = 28
 * Op = true
 *
 * */

public class PerfectNumber {
    public static void main(String[] args) {
        System.out.println(isPerfect(6));
        System.out.println(isPerfect(4));
        System.out.println(isPerfect(28));
        System.out.println(isPerfect(8128));
    }

    public static boolean isPerfect(int n) {
        int count = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                count = count + i;
            }
        }
        return n == count;
    }
}
