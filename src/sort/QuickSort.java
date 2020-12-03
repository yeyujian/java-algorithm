package sort;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    public static void sort(int[] arr, int start, int end) {
        if (start >= end) return;
        int left = start, right = end;
        int tmp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > tmp) right--;
            if (left < right) arr[left++] = arr[right];
            while (left < right && arr[left] < tmp) left++;
            if (left < right) arr[right--] = arr[left];
        }
        arr[left] = tmp;
        sort(arr, start, left - 1);
        sort(arr, left + 1, end);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        sort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组结果: " + Arrays.toString(arr));
    }
}
