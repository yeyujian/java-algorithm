package test2;


import java.util.Scanner;

public class SinglyLinkedList1<E extends Comparable> {
    public Node root;
    public Node end;

    static class Node<E extends Comparable> implements Comparable, Cloneable {
        public Node next;
        E t;

        Node(E t) {
            this.t = t;
            next = null;
        }

        Node() {
            next = null;
        }

        @Override
        public Object clone() {
            Node node = null;
            try {
                node = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return node;
        }

        @Override
        public String toString() {
            return t.toString();
        }


        @Override
        public int compareTo(Object o) {
            return t.compareTo((E) o);
        }
    }

    public SinglyLinkedList1() {
        root = end = null;
    }

    /**
     * 以单链表list构造新的单链表，复制单链表
     *
     * @param list
     */
    public SinglyLinkedList1(SinglyLinkedList1 list) {
        Node temp = list.root;
        if (temp != null) {
            this.root = (Node) temp.clone();
            temp = temp.next;
            this.end = this.root;
        } else {
            return;
        }
        while (temp != null) {
            end.next = (Node) temp.clone();
            temp = temp.next;
            end = end.next;

        }
    }

    /**
     * 插入
     *
     * @param node
     */
    public void insert(Node node) {
        if (root == null) {
            root = node;
            end = node;
            return;

        }
        end.next = node;
        end = node;
    }

    /**
     * 将指定单链表list链接在当前单链表之后
     *
     * @param list
     */
    public void concat(SinglyLinkedList1 list) {
        this.end.next = list.root;
        this.end = list.end;
    }


    @Override
    public String toString() {
        Node tmp = this.root;
        String str = "[";
        while (tmp != null) {
            str += tmp.toString() + ",";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    /**
     * 若查找到指定对象，则返回结点，否则返回null
     *
     * @param element
     * @return
     */
    public Node<E> search(E element) {
        Node temp = this.root;
        while (temp != null) {
            if (temp.compareTo(element) == 0) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    /**
     * 以查找结果判断单链表是否包含指定对象
     *
     * @param element
     * @return
     */
    public boolean contain(E element) {
        return search(element) == null ? false : true;
    }

    /**
     * 比较两条单链表是否相等
     *
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof SinglyLinkedList1) {
            SinglyLinkedList1 list = (SinglyLinkedList1) obj;
            Node temp1 = this.root, temp2 = list.root;
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

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            SinglyLinkedList1<Integer> list = new SinglyLinkedList1<>();
            for (int i = 0; i < n; i++) {
                int temp = scan.nextInt();
                Node<Integer> node = new Node<>(temp);
                list.insert(node);
            }
            System.out.println("输入完成!!!表为:");
            System.out.println(list.toString());
            SinglyLinkedList1<Integer> list1 = new SinglyLinkedList1<>(list);
            list1.concat(list);
            System.out.println("复制完成!!!表为:");
            System.out.println(list1.toString());
            System.out.println(list1.search(3));
            //? "两条链不相等" : "两条链相等"
            System.out.println(!list1.equals(list) ? "两条链不相等" : "两条链相等");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
