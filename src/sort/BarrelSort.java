package sort;

import java.util.Arrays;
import java.util.Scanner;

public class BarrelSort {
    public static int getFigure(int i, int k) {
        int[] b = {1, 10, 100};
        return (i / b[k - 1]) % 10;
    }

    public static void sort(int[] a, int key) {
        int[] count = new int[10];
        int[] barrel = new int[a.length];
        for (int k = 1; k <= key; k++) {
            for (int i = 0; i < 10; i++) {
                count[i] = 0;//清空
            }
            for (int i = 0; i < a.length; i++) {

                count[getFigure(a[i], k)]++;
            }
            for (int i = 1; i < a.length; i++) {
                count[i] += count[i - 1];
            }
            for (int i = a.length - 1; i >= 0; i--) {
                int j = getFigure(a[i], k);
                barrel[count[j]-1] = a[i];
                count[j]--;
            }
            for (int i = 0, j = 0; i < a.length; i++, j++) {
                a[i] = barrel[j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        int key = scan.nextInt();//最大位数
        sort(arr, key);
        System.out.println("排序后的数组结果: " + Arrays.toString(arr));
    }
}
