package train;

import java.util.ArrayList;

//输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
public class test1 {

//利用堆排序原理，计算出最小的 k 个数

    public ArrayList<Integer> GetLestNumbers(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length || k == 0) {
            return res;
        }

        for (int i = input.length - 1; i >= 0; i--) {
            minHeap(input, 0, i);
            swap(input, 0, i);
            res.add(input[i]);
            if (res.size() == k) break;
        }
        return res;
    }


    private void minHeap(int[] heap, int start, int end) {
        if (start == end) return;

        int left = start * 2 + 1;
        int right = left + 1;
        if (left <= end) {
            minHeap(heap, left, end);

            if (heap[left] < heap[start]) swap(heap, start, left);
        }

        if (right <= end) {
            minHeap(heap, right, end);

            if (heap[right] < heap[start]) swap(heap, start, right);
        }

    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
