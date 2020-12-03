package sort;

import java.util.Arrays;
import java.util.Scanner;

public class InsertSort {

    public static void sort(int[] arr, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; i++) {
            int val=arr[i];
            int j=i-1;
            while(j>=0&&arr[j]>val){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1] = val;
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
