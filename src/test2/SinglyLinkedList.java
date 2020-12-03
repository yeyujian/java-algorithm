package test2;

import java.util.Scanner;

public class SinglyLinkedList<E extends Comparable> {

    public Node<E> head;
    public int length;

    public SinglyLinkedList() {
        length = 0;
        head = new Node<E>(null, null);
    }

    /**
     * 由指定数组中的多个对象构造单链表
     */
    public SinglyLinkedList(E[] element) {
        length = 0;
        head = new Node<E>(null, null);
        Node<E> temp = head;
        for (int i = 0; i < element.length; i++) {
            temp.next = new Node(element[i], null);
            temp = temp.next;
            length++;
        }
    }

    /**
     * 以单链表list构造新的单链表，复制单链表
     *
     * @param list
     */
    public SinglyLinkedList(SinglyLinkedList<E> list) {
        length = 0;
        head = new Node<E>(null, null);
        Node<E> temp = list.head.next, cur = null;
        if (temp == null) {
            return;
        } else {
            this.head.next = cur = new Node<>(temp.data, null);
            temp = temp.next;
            length++;
        }

        while (temp != null) {
            cur.next = new Node<>(temp.data, null);
            temp = temp.next;
            cur = cur.next;
            length++;
        }

    }

    /**
     * 将指定单链表list链接在当前单链表之后
     *
     * @param list
     */
    public void concat(SinglyLinkedList<E> list) {
        Node<E> t = this.head;
        while (t.next != null) t = t.next;
        t.next = list.head.next;
    }

    public Node<E> search(E element) {
        for (Node<E> t = head.next; t != null; t = t.next) {
            if (t.data.compareTo(element) == 0)
                return t;
        }
        return null;
    }

    public boolean contain(E element) {
        return this.search(element) != null;
    }

    @Override
    public String toString() {
        Node<E> tmp = this.head.next;
        String str = "[";
        while (tmp != null) {
            str += tmp.toString() + ",";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof SinglyLinkedList) {
            SinglyLinkedList list = (SinglyLinkedList) obj;
            Node<E> temp1 = this.head.next, temp2 = list.head.next;
            while (temp1.equals(temp2)) {
                temp1 = temp1.next;
                temp2 = temp2.next;
                if (temp1 == null) {
                    if (temp2 == null) return true;
                    break;
                }
                if (temp2 == null) break;
            }

        }
        return false;
    }

    /**
     * 插入
     *
     * @param node
     */
    public void insert(Node node) {
        Node<E> t = this.head;
        while (t.next != null) t = t.next;
        t.next = node;
        length++;
    }

    public void insert(E data) {
        Node<E> t = this.head;
        while (t.next != null) t = t.next;
        t.next = new Node<E>(data, null);
    }


    public E remove(E key) {
        Node<E> temp = this.head;
        while (temp.next != null) {
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
     * 声明node节点
     *
     * @param <T>
     */
    public static class Node<T extends Comparable> {
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

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            for (int i = 0; i < n; i++) {
                int temp = scan.nextInt();
                Node<Integer> node = new Node<>(temp, null);
                list.insert(node);
            }
            System.out.println("输入完成!!! list表为:");
            System.out.println(list.toString());
            System.out.println("public SinglyLinkedList(SinglyLinkedList list)以单链表list构造新的单链表list1，复制单链表");
            SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(list);
            System.out.println("public void concat(SinglyLinkedList list)将指定单链表list链接在当前单链表list1之后");
            list1.concat(list);
            System.out.println("复制完成!!! list1表为:");
            System.out.println(list1.toString());
            System.out.println("在list1中查找元素3并输出：（不存在则返回null）");
            System.out.println(list1.search(3));
            System.out.println("判断两条链表是否相等");
            System.out.println(!list1.equals(list) ? "list:list1两条链不相等" : "list:list1两条链相等");
            System.out.println(!list.equals(list) ? "list:list两条链不相等" : "list:list两条链相等");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
