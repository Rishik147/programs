package math;

/*
 * In = 25
 * Op = 52
 *
 * In = 123
 * Op = 321
 *
 * In = 54
 * Op = 45
 * */
public class ReverseNumber {
    public static void main(String[] args) {
        System.out.println(reverseNumber(25));
        System.out.println(reverseNumber(123));
        System.out.println(reverseNumber(54));
    }

    public static int reverseNumber(int n) {
        int reversed = 0;
        while (n > 0) {
            int temp = n % 10;
            reversed = reversed * 10 + temp;
            n = n / 10;
        }
        return reversed;
    }

}
