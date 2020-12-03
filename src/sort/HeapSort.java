package sort;

import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {
    public static void exchange(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    public static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n &&a[j] < a[j + 1]) j++;
            if (a[j] < a[k]) break;
            exchange(a, k, j);
            k = j;
        }
    }

    public static void sort(int[] arr, int n) {
        if (n <= 1) return;
        for (int i = n / 2; i >= 1; i--) {
            sink(arr, i, n);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        //从下标1开始记录
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scan.nextInt();
        }
        sort(arr, arr.length-1);
        System.out.println("排序后的数组结果: " + Arrays.toString(arr));
    }
}
