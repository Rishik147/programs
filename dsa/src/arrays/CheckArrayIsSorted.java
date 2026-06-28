package arrays;

public class CheckArrayIsSorted {
    public static void main(String[] args) {
        System.out.println(isSorted(new int[]{1, -1, 3, 4, 5}));
        System.out.println(isSorted(new int[]{1, 51, 100, 101, 102}));
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i])
                return false;
        }
        return true;
    }
}
