package sort;

import java.util.Arrays;
import java.util.Scanner;

public class ChooseSort {
    public static void sort(int[] arr, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
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
