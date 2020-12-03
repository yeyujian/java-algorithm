package test2;


import java.util.Scanner;

public class CircSinglyLinkedList<T> implements LList<T> {

    public Node<T> head;
    private int length;

    /**
     * 初始化循环单链表
     */
    public CircSinglyLinkedList() {
        this.head = new Node<>(null, null);
        this.head.next = this.head;
        length = 0;
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.head.next == this.head;
    }

    /**
     * 返回长度
     * @return
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * 获取第i个元素
     * @param i
     * @return
     */
    @Override
    public T get(int i) {
        Node<T> data = getNode(i);
        return data == null ? null : data.data;
    }

    public Node<T> getNodeNext(Node<T> node, int i) {
        if (!contains(node.data)) {
            System.out.println("在链表中无此节点");
            return null;
        }
        if (size() == 0) {
            System.out.println("链表长度为0");
            return null;
        }
        i = (i) % length;
        for (int j = 0; j < i; j++) {
            if (node.next == head) node = node.next;
            node = node.next;
        }
        return node;
    }

    /**
     * 获取当前元素后第i个元素
     * @param node
     * @param i
     * @return
     */
    public T getNext(Node<T> node, int i) {
        return (node = (Node<T>) getNodeNext(node, i)) == null ? null : node.data;
    }

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
     * 设置位置i的元素值
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
     * 删除位置i的元素
     * @param i
     * @return
     */
    @Override
    public T remove(int i) {
        if (size() < i) {
            System.out.println("超出边界！！！");
            return null;
        }
        Node<T> past = getNode(i - 1);
        T data = past.next.data;
        past.next = past.next.next;
        length--;
        return data;
    }
//清空
    @Override
    public void clear() {
        this.head.next = this.head;
        this.length = 0;
    }

    /**
     * 查找key的首个位置
     * @param key
     * @return
     */
    @Override
    public int search(T key) {

        Node<T> temp = this.head.next;

        for (int i = 0; i < size(); i++) {
            if (temp.data.equals(key)) return i + 1;
            temp = temp.next;
        }
        return -1;
    }

    /**
     * 查看是否包含key
     * @param key
     * @return
     */
    @Override
    public boolean contains(T key) {
        return search(key) != -1;
    }

    /**
     * 插入一个链表中不存在的元素
     * @param x
     * @return
     */
    public T inserDifferent(T x) {
        Node<T> temp = this.head;
        for (int i = 0; i < size(); i++) {
            temp = temp.next;
            if (temp.data.equals(x)) return null;

        }
        temp.next = new Node(x, this.head);
        length++;
        return x;
    }

    /**
     * 在链表尾部插入元素
     * @param key
     * @return
     */
    @Override
    public int insert(T key) {
        Node<T> temp = this.head;
        for (int i = 0; i < size(); i++) {
            temp = temp.next;
        }
        temp.next = new Node(key, this.head);
        length++;
        return length;
    }

    /**
     * 删除元素key
     * @param key
     * @return
     */
    @Override
    public T remove(T key) {
        Node<T> temp = this.head;
        for (int i = 0; i < size(); i++) {
            if (temp.next.data.equals(key)) {
                temp.next = temp.next.next;
                length--;
                return key;
            }
            temp = temp.next;
        }
        return null;
    }


    /**
     * 定义Node 类
     *
     * @param <T>
     */
    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
            this.data = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public String toString() {
        Node<T> tmp = this.head.next;
        String str = "[";
        while (tmp != this.head) {
            str += tmp.toString() + "->";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof CircSinglyLinkedList) {
            CircSinglyLinkedList list = (CircSinglyLinkedList) obj;
            Node<T> temp1 = this.head.next, temp2 = list.head.next;
            while (temp1.equals(temp2)) {
                temp1 = temp1.next;
                temp2 = temp2.next;
                if (temp1 == this.head) {
                    if (temp2 == list.head) return true;
                    break;
                }
                if (temp2 == list.head) break;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            CircSinglyLinkedList<Integer> list = new CircSinglyLinkedList<>();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                System.out.println("输入了:".concat(String.valueOf(tmp)));
                list.insert(new Integer(tmp));
                System.out.println("调用insert（）插入链表，当前链表：");
                System.out.println(list.toString());
            }
            System.out.println("输入完成!!!表为:");
            System.out.println(list.toString());
            System.out.println("调用remove（）删除表中1和9的元素");
            list.remove(9);
            list.remove(1);
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
