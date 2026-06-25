package math;

/*
 * In = 2
 * Op = 2
 *
 * In = 0
 * Op = 1
 *
 * In = 3
 * Op = 6
 *
 * */

public class Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(2));
        System.out.println(factorial(0));
        System.out.println(factorial(3));
        System.out.println(factorial(5));

        System.out.println(factorialUsingRecursion(1));
        System.out.println(factorialUsingRecursion(6));
    }

    public static long factorial(int n) {
        if (n == 0 || n == 1)
            return n;
        long factorial = 1;
        for (int i = 2; i <= n; i++)
            factorial = factorial * i;
        return factorial;
    }

    public static long factorialUsingRecursion(int n) {
        if (n == 0 || n == 1)
            return n;
        return n * factorialUsingRecursion(n - 1);
    }
}
