package sort;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public static void merge(int[] a, int start, int mid, int end) {
        int[] temp=new int[a.length];
        int left=start,right=mid+1;
        int k=start;
        while(left<=mid&&right<=end){
            if(a[left]<a[right]) temp[k++]=a[left++];
            else temp[k++]=a[right++];
        }
        while(left<=mid) temp[k++]=a[left++];
        while(right<=end) temp[k++]=a[right++];
        while(start<=end) a[start]=temp[start++];
    }

    public static void sort(int[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) >> 1;
            sort(a, start, mid);
            sort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        sort(arr, 0, arr.length-1);
        System.out.println("排序后的数组结果: " + Arrays.toString(arr));
    }
}
