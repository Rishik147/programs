package math;

/*
 * In = 4
 * Op = 1
 *
 *
 * In = 234
 * Op = 3
 * */

public class CountDigit {
    public static void main(String[] args) {
        System.out.println(countDigits(31));
        System.out.println(countDigits(14));
        System.out.println(countDigits(349098765));
        System.out.println(countDigits(1));
    }

    public static int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        return count;
    }
}
