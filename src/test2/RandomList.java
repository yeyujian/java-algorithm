package test2;

import java.util.Random;
import java.util.Scanner;

public class RandomList<T> {
    public Node<T> head;
    public int length;

    /**
     * 根据n和数组生成随机序列
     *
     * @param n
     * @param array
     */
    RandomList(int n, T[] array) {
        head = new Node<T>();
        int count = 0;
        Random random = new Random();
        while (count < n) {
            int num = random.nextInt(n) + 1;
            if (hasIndex(num)) continue;
            insert(num, array[count++]);
        }
    }

    RandomList() {
        head = new Node<T>();
        length = 0;
    }

    /**
     * 查看是否已存在此序列号
     *
     * @param e
     * @return
     */
    public boolean hasIndex(int e) {
        Node<T> temp = this.head.next;
        while (temp != null) {
            if (temp.index == e) return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * 插入一个节点
     *
     * @param element
     * @return
     */
    public boolean insertOne(T element) {
        if (contain(element)) {
            System.out.println("此元素已存在！！！");
            return false;
        }
        //随机化插入节点的序列号
        Random random = new Random();
        int num = random.nextInt(length) + 1;
        Node<T> temp = this.head.next;
        System.out.println(insert(num, element));
        int flag = 0;
        /**
         * 去重 原本>= 当前节点的序列号+1
         */
        while (temp != null) {
            if (temp.index > num) temp.index++;
            if (temp.index == num) {
                if (flag > 0) {
                    temp.index++;
                }
                flag++;
            }
            temp = temp.next;
        }
        return true;
    }

    /**
     * 查看是否包含元素
     *
     * @param element
     * @return
     */
    public boolean contain(T element) {
        Node<T> temp = this.head.next;
        while (temp != null) {
            if (temp.data.equals(element)) return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * 插入元素并且标记序列号
     *
     * @param index
     * @param element
     * @return
     */
    public Node<T> insert(int index, T element) {
        Node<T> temp = this.head, node = new Node(index, element, null);
        while (temp.next != null) temp = temp.next;
        temp.next = node;
        length++;
        return node;
    }

    /**
     * 对于序列号大于index的元素序列号-1操作
     *
     * @param index
     */
    public void reduce(int index) {
        Node<T> temp = this.head.next;
        while (temp != null) {
            if (temp.index > index) temp.index--;
            temp = temp.next;
        }
    }

    /**
     * 删除序列号对应节点
     *
     * @param index
     * @return
     */
    public Node<T> delete(int index) {
        Node<T> temp = this.head, node;
        //因为删除了序列号，之后的序列号需要-1
        while (temp.next != null) {
            if (temp.next.index == index) {
                node = temp.next;
                temp.next = temp.next.next;
                reduce(node.index);
                length--;
                return node;
            }
            temp = temp.next;
        }
        return null;

    }

    @Override
    public String toString() {
        Node temp = this.head.next;
        String str = "[ ";
        while (temp != null) {
            str += temp.toString() + ", ";
            temp = temp.next;
        }
        return str + " ]";
    }

    /**
     * 声明node节点
     */
    public static class Node<T> {
        public int index; //序列号
        public Node next; //下一个节点
        public T data; //元素值

        public Node(int index, T data, Node next) {
            this.index = index;
            this.next = next;
            this.data = data;
        }

        public Node() {
            this.index = 0;
            this.next = null;
            this.data = null;
        }

        @Override
        public String toString() {
            return String.valueOf(index).concat(":" + data.toString());
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }
            RandomList<Integer> list = new RandomList(n, a);
            System.out.println("随机产生序列为(序列号:元素值)：");
            System.out.println(list.toString());
            System.out.println("插入元素 5 序列变成：");
            list.insertOne(5);
            System.out.println(list.toString());
            System.out.println("插入元素 19 序列变成：");
            list.insertOne(19);
            System.out.println(list.toString());
            System.out.println("删除序列号为5的元素：");
            System.out.println("此删除元素为：" + list.delete(5));
            System.out.println(list.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
