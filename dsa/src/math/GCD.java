package math;

/*
 * In = 4,6
 * Op = 2
 *
 * In = 9,8
 * Op = 1
 *
 * In = 6,12
 * Op = 6
 * */

public class GCD {
    public static void main(String[] args) {
        System.out.println(gcd(4, 6));
        System.out.println(gcd(9, 8));
        System.out.println(gcd(6, 12));
        System.out.println(gcd(90, 100));

        System.out.println(euclideanAlgorithm(4, 6));
        System.out.println(euclideanAlgorithm(9, 8));
        System.out.println(euclideanAlgorithm(6, 12));
        System.out.println(euclideanAlgorithm(90, 100));
    }

    public static int gcd(int n1, int n2) {
        if (Math.abs(n1 - n2) == 1)
            return 1;

        for (int i = Math.min(n1, n2); i >= 1; i--) {
            if ((n1 % i == 0) && (n2 % i == 0)) {
                return i;
            }
        }
        return 1;
    }

    public static int euclideanAlgorithm(int n1, int n2) {
        while (n1 != 0 && n2 != 0) {
            if (n1 > n2)
                n1 = n1 % n2;
            else
                n2 = n2 % n1;
        }
        return Math.max(n1, n2);
    }
}
