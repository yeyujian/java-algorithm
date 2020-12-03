package test2;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 设计一个有序顺序表（元素已排序，递增或递减），实现插入、删除等操作，元素插入位置由其值决定
 *
 * @param <E>
 */
public class ArrayList<E extends Comparable> {

    private Object[] array = null;
    private int flag;

    //根据长度来初始化顺序表
    public ArrayList(int length) {
        array = new Object[length];
        flag = 0;
    }
    //根据数组来初始化顺序表
    public ArrayList(Object[] arr) {
        this.array = arr;
    }

    public ArrayList() {
        this(5);
    }

    //进行元素插入操作
    public void insert(E e) {
        if (flag >= array.length - 1) {
            array = Arrays.copyOf(array, array.length + 5);//扩展数组长度
        }
        int start = 0, end = flag-1;
        int mid;
        //使用二分查找法找到插入的位置
        while (start <= end) {
            mid = (start + end) >>> 1;
            if (((E) array[mid]).compareTo(e) == 0) {
                start = mid;
                break;
            }
            if (((E) array[mid]).compareTo(e) > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        for (int i = flag - 1; i >= start; i--) {
            array[i + 1] = array[i];
        }
        array[start] = e;
        flag++;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "array=" + Arrays.toString(array) +
                ", flag=" + flag +
                '}';
    }

    /**
     * 删除首个匹配元素的节点
     * @param e
     */
    public void delete(E e) {
        int start = 0, end = flag-1;
        int mid = 0;
        //使用二分查找法
        while (start <= end) {
            mid = (start + end) >>> 1;
            if (((E) array[mid]).compareTo(e) == 0) {
                System.out.println(array[mid]);
                start = mid;
                mid = -3;
                break;
            } else if (((E) array[mid]).compareTo(e) > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (mid == -3) {
            for (int i = start; i < flag - 1; i++) {
                array[i] = array[i + 1];
            }

            array[flag - 1] = null;
            flag--;
        }

    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                System.out.println("输入了:".concat(String.valueOf(tmp)));
                list.insert(new Integer(tmp));
                System.out.println(list.toString());
            }
            System.out.println("输入完成!!!表为:");
            System.out.println(list.toString());
            System.out.println("删除表中1和9的元素");
            list.delete(9);
            list.delete(1);
            System.out.println("修改完成!!!表为:");
            System.out.println(list.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
