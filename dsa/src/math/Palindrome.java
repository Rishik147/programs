package math;

/*
 * In = 121
 * Op = true
 *
 * In = 101
 * Op = true
 *
 * In = 123
 * Op = false
 * */

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(101));
        System.out.println(isPalindrome(123));
    }

    public static boolean isPalindrome(int n) {
        int reversed = 0;
        int original = n;
        while (n > 0) {
            int lastDigit = n % 10;
            reversed = reversed * 10 + lastDigit;
            n = n / 10;
        }
        return original == reversed;
    }
}
