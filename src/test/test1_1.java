package test;

import java.util.Arrays;
import java.util.Scanner;

public class test1_1 {
    public static boolean isSorted(int[] table) {
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] > table[i + 1]) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] table) {
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i].compareTo(table[i + 1]) > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            int[] arr = new int[n];
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
                a[i] = arr[i];
            }
            System.out.println("输入的数组: " + Arrays.toString(arr));
            System.out.println(isSorted(arr) ? "是按升序排序" : "不是按升序排序");
            System.out.println(isSorted(a) ? "是按升序排序" : "不是按升序排序");
        } catch (Exception e) {
            System.out.println("请输入整数!!!!");
            e.printStackTrace();
        }
    }
}
