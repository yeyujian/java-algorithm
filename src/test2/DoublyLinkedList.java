package test2;

import java.util.Scanner;

public class DoublyLinkedList<T> implements LList<T> {

    public Node<T> head;
    private int length;

    DoublyLinkedList() {
        this.head = new Node<>(null, null, null);
        length = 0;
    }

    /**
     * 查看链表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回链表长度
     *
     * @return
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * 获取第i个元素
     *
     * @param i
     * @return
     */
    @Override
    public T get(int i) {
        Node<T> data = getNode(i);
        return data == null ? null : data.data;
    }

    /**
     * 获取第i个节点
     *
     * @param i
     * @return
     */
    public Node<T> getNode(int i) {
        if (size() < i) {
            System.out.println("超出边界！！！");
            return null;
        }
        int flag = 0;
        Node<T> temp = this.head;
        while (flag < i) {
            flag++;
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 设置第i个节点的元素值
     *
     * @param i
     * @param x
     */
    @Override
    public void set(int i, T x) {
        Node<T> node = getNode(i);
        if (node == null) return;
        node.data = x;
    }

    /**
     * 插入元素都链表尾部
     *
     * @param x
     * @return
     */
    @Override
    public int insert(T x) {
        if (x == null)
            throw new NullPointerException("输入x为空");     //抛出空对象异常

        Node<T> temp = head;
        for (int i = 0; i < length; i++) {
            temp = temp.next;
        }
        temp.next = new Node<T>(x, temp, null);
        length++;
        return length;

    }

    /**
     * 删除位置i的节点
     *
     * @param i
     * @return
     */
    @Override
    public T remove(int i) {

        Node<T> past = getNode(i - 1);
        if (past == null) return null;
        T data = past.next.data;
        past.next = past.next.next;
        past.next.prev = past;
        length--;
        return data;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        this.head.next = null;
        this.length = 0;
    }

    /**
     * 查找与key相等元素的位置
     *
     * @param key
     * @return
     */
    @Override
    public int search(T key) {
        Node<T> temp = this.head.next;
        for (int i = 0; i < size(); i++) {
            if (temp.data.equals(key)) return i + 1;
        }
        return -1;
    }

    /**
     * 查看是否包含元素key
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(T key) {
        return search(key) != -1;
    }

    /**
     * 删除首个指定元素值
     *
     * @param key
     * @return
     */
    @Override
    public T remove(T key) {

        Node<T> temp = this.head;
        for (int i = 0; i < size(); i++) {
            if (temp.next.data.equals(key)) {
                temp.next = temp.next.next;
                if (temp.next != null)
                    temp.next.prev = temp;
                length--;
                return key;
            }
            temp = temp.next;
        }
        return null;
    }


    @Override
    public String toString() {
        Node<T> tmp = this.head.next;
        String str = "[";
        while (tmp != null) {
            str += tmp.toString() + ",";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    /**
     * 声明node节点
     *
     * @param <T>
     */
    public static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node() {
            this.data = null;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return (prev.data == null ? "null" : prev.data.toString()) + "<--" + data.toString() + "-->" + (next == null ? "null" : next.data.toString());
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                System.out.println("输入了:".concat(String.valueOf(tmp)));
                list.insert(new Integer(tmp));
                System.out.println("调用insert（）插入链表，当前链表：");
                System.out.println(list.toString());
            }
            System.out.println("输入完成!!!表为:");
            System.out.println(list.toString());
            System.out.println("调用remove（）删除表中元素1和9");
            list.remove((Integer) 9);
            list.remove((Integer) 1);
            System.out.println("删除完成!!!表为:");
            System.out.println(list.toString());
            System.out.println("调用contains（）方法判断是否包含元素3");
            System.out.println(list.contains(3));
            System.out.println("调用search（）方法查找元素3 没有则返回 -1");
            System.out.println(list.search(3));
            System.out.println("调用isEmpty（）判断是否为空");
            System.out.println(list.isEmpty());
            System.out.println("调用size（）查看长度");
            System.out.println(list.size());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
