package arrays;

public class CountOddNumbersInArray {
    public static void main(String[] args) {
        System.out.println(countOdd(new int[]{1, 2, 3, 4, 5}));
        System.out.println(countOdd(new int[]{1, 11, 3, 4, 5}));
    }

    public static int countOdd(int[] arr) {
        int count = 0;
        for (int element : arr)
            if (element % 2 == 1)
                count++;
        return count;
    }
}
