package math;

/*
 * Armstrong number is a number which is equal to the sum of the digits of the number, raised to the power
 * of the number of digits
 *
 * In = 153
 * 1 ^ 3 + 5 ^ 3 + 3 ^ 3 = 153
 * Op = true
 *
 * In = 12
 * 1 ^ 2 + 2 ^ 2 = 5
 * Op = false
 *
 * */

public class ArmstrongNumber {
    public static void main(String[] args) {
        System.out.println(isArmstrongNumber(153));
        System.out.println(isArmstrongNumber(12));
        System.out.println(isArmstrongNumber(370));
        System.out.println(Math.log10(100));
    }

    private static boolean isArmstrongNumber(int n) {
        int armstrong = 0;

        int length = (int) Math.log10(n) + 1;

        int original = n;
        while (original > 0) {
            int lastDigit = original % 10;
            armstrong = armstrong + (int) Math.pow(lastDigit, length);
            original = original / 10;
        }

        return n == armstrong;
    }

}
