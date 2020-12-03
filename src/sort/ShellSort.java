package sort;

import java.util.Arrays;
import java.util.Scanner;

public class ShellSort {

    public static void sort(int[] arr, int n) {
        if (n <= 1) return;
        int len = n / 2, flag = 1;
        while (len >= 1) {
            for (int i = len; i < n; i++) {
                int tmp = arr[i];
                int j = i - len;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + len] = arr[j];
                    j -= len;
                }
                arr[j + len] = tmp;
            }
            len /= 2;
            System.out.println("第" + (flag++) + "轮排序后的数组为: " + Arrays.toString(arr));
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
