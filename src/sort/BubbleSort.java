package sort;


import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {


    public static void sort(int[] arr, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.println("第" + (i + 1) + "轮排序后的数组为: " + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        sort(arr, arr.length);
        System.out.println("排序后的数组结果: " + Arrays.toString(arr));
    }
}
