package math;

/*
 * In = 4,6
 * Op = 12
 *
 * In = 3,5
 * Op = 15
 *
 * In = 4,12
 * Op = 12
 * */


// LCM * GCD = n1 * n2

public class LCM {
    public static void main(String[] args) {
        System.out.println(lcm(4, 6));
        System.out.println(lcm(3, 5));
        System.out.println(lcm(4, 12));
    }

    public static int lcm(int n1, int n2) {
        int gcd;
        int no1 = n1;
        int no2 = n2;
        while (n1 != 0 && n2 != 0) {
            if (n1 > n2)
                n1 = n1 % n2;
            else
                n2 = n2 % n1;
        }
        gcd = Math.max(n1, n2);
        return (no1 * no2) / gcd;
    }
}
