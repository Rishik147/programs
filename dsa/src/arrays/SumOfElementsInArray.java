package arrays;

public class SumOfElementsInArray {
    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sum(new int[]{1, -1, 3, 4, 5}));
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }
}
