package math;

/*
 * In = 25
 * Op = 5
 *
 * In = 99
 * Op = 9
 *
 * In = 1
 * Op = 1
 *
 */

public class LargestDigit {
    public static void main(String[] args) {
        System.out.println(largestDigit(25));
        System.out.println(largestDigit(123456789));
        System.out.println(largestDigit(1));
    }

    public static int largestDigit(int n) {
        if (n <= 9)
            return n;
        int largestDigit = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            if (lastDigit > largestDigit)
                largestDigit = lastDigit;
            n = n / 10;
        }
        return largestDigit;
    }
}
