package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class test1_2 {


    public static <E> E[] reversed(E[] list) {
        int start = 0, end = list.length - 1;
        while (start <= end) {
            E temp = list[start];
            list[start] = list[end];
            list[end] = temp;
            start++;
            end--;
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }
            System.out.println(Arrays.toString(arr));
            reversed(arr);
            System.out.println(Arrays.toString(arr));
        } catch (Exception e) {
            System.out.println("请输入整数!!!!");
            e.printStackTrace();
        }
    }
}
