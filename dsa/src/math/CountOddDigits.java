package math;

/*
 * In = 5
 * Op = 1
 *
 * In = 25
 * Op = 1
 *
 * In = 15
 * Op = 2
 * */

public class CountOddDigits {
    public static void main(String[] args) {
        System.out.println(countOddDigits(5));
        System.out.println(countOddDigits(25));
        System.out.println(countOddDigits(15));
        System.out.println(countOddDigits(13578));
    }

    public static int countOddDigits(int n) {
        int count = 0;
        while (n > 0) {
            int digit = n % 10;
            if (digit % 2 != 0)
                count++;
            n = n / 10;
        }
        return count;
    }
}
