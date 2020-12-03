package test2;

import java.util.Scanner;

public class CHDoublelyLinkedList<E> implements LList<E> {

    public Node<E> head;
    private int length;

    public CHDoublelyLinkedList() {

        this.head = new Node<>(null, null, null);
        this.head.next = this.head.prev = this.head;
        length = 0;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回链表的长度
     * @return
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * 获取第i个位置节点的元素值
     * @param i
     * @return
     */
    @Override
    public E get(int i) {
        Node<E> data = getNode(i);
        return data == null ? null : data.data;
    }

    /**
     * 获取第i个节点
     * @param i
     * @return
     */
    public Node<E> getNode(int i) {
        if (size() < i) {
            System.out.println("超出边界！！！");
            return null;
        }
        int flag = 0;
        Node<E> temp = this.head;
        while (flag < i) {
            flag++;
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 修改第i个节点的元素值
     * @param i
     * @param x
     */
    @Override
    public void set(int i, E x) {
        Node<E> node = getNode(i);
        if (node == null) return;
        node.data = x;
    }

    /**
     *  插入元素都链表尾部
     * @param x
     * @return
     */
    @Override
    public int insert(E x) {
        if (x == null)
            throw new NullPointerException("输入x为空");     //抛出空对象异常

        Node<E> temp = new Node<E>(x, head.prev, head);
        head.prev.next = temp;
        head.prev = temp;
        length++;
        return length;
    }

    /**
     * 删除位置i的节点
     * @param i
     * @return
     */
    @Override
    public E remove(int i) {

        Node<E> past = getNode(i);
        if (past == null) return null;
        past.prev.next = past.next;
        past.next.prev = past.prev;
        length--;
        return past.data;
    }

    /**
     * 删除所有节点
     */
    @Override
    public void clear() {
        this.head.prev = this.head.next = this.head;
        this.length = 0;
    }

    /**
     * 查找对应元素所在位置
     * @param key
     * @return
     */
    @Override
    public int search(E key) {
        Node<E> temp = this.head.next;
        for (int i = 0; i < size(); i++) {
            if (temp.data.equals(key)) return i + 1;
            temp = temp.next;
        }
        return -1;
    }

    /**
     * 查看是否包含此元素
     * @param key
     * @return
     */
    @Override
    public boolean contains(E key) {
        return search(key) != -1;
    }

    /**
     * 获取当前元素node的后面i位置的元素
     * @param node
     * @param i
     * @return
     */
    public Node<E> getNodeNext(Node<E> node, int i) {
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
     * 删除与key相等的首个节点
     * @param key
     * @return
     */
    @Override
    public E remove(E key) {

        Node<E> temp = this.head.next;
        for (int i = 0; i < size(); i++) {
            if (temp.data.equals(key)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                length--;
                return key;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public String toString() {
        Node<E> tmp = this.head.next;
        String str = "[";
        while (tmp != this.head) {
            //System.out.println(tmp);
            str += tmp.toString() + " ,";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    /**
     * 声明node节点
     *
     * @param <E>
     */
    public static class Node<E> {
        public E data;
        public Node<E> next;
        public Node<E> prev;

        public Node(E data, Node<E> prev, Node<E> next) {
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
            return (prev.data == null ? prev.prev.data.toString() : prev.data.toString()) + "<--" + data.toString() + "-->"
                    + (next.data == null ? next.next.data.toString() : next.data.toString());
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            CHDoublelyLinkedList<Integer> list = new CHDoublelyLinkedList<>();
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
