package math;

public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(6));
        System.out.println(countPrimes(10));
        System.out.println(countPrimes(20));

    }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i))
                count++;
        }
        return count;
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
