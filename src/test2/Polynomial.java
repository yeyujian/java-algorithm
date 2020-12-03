package test2;

import java.util.Scanner;

/**
 * 多项式相加
 */
public class Polynomial {


    public Node head;

    public Polynomial() {
        this.head = new Node();
    }

    /**
     * 插入项
     *
     * @param node
     */
    public void insert(Node node) {
        Node t = this.head;
        //查找是否有相等指数的项有就相加
        while (t.next != null) {
            if (t.next.compareTo(node) == 0) {
                t.next.data += node.data;
                return;
            }
            t = t.next;
        }
        t.next = node;
    }

    //删除项
    public Node delete(Node node) {
        Node t = this.head, temp;
        while (t.next != null) {
            if (t.next.equals(node)) {
                temp = t.next;
                t.next = t.next.next;
                return temp;
            }
        }
        return null;
    }

    /**
     * 两个同指数项相加，系数相加
     * @param list
     */
    public void add(Polynomial list) {
        Node temp = list.head.next;
        while (temp != null) {
            this.insert(temp);
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        Node temp = this.head.next;
        String str = "[ ";
        while (temp != null) {
            str += (temp.toString().charAt(0) == '-' ? " " : " + ") + temp.toString();
            temp = temp.next;
        }
        return str + " ]";
    }

    /**
     * 声明node节点
     */
    public static class Node implements Comparable {
        public int index;//指数
        public Integer data;//系数
        public Node next;

        public Node(Integer data, int index, Node next) {
            this.data = data;
            this.next = next;
            this.index = index;
        }

        public Node() {
            this.data = null;
            this.next = null;
            this.index = 0;
        }

        @Override
        public String toString() {
            return data.toString().concat("*X^" + (index < 0 ? "(" + String.valueOf(index) + ")" : String.valueOf(index)));
        }

        @Override
        public int compareTo(Object o) {
            if (this.index > ((Node) o).index) {
                return 1;
            }
            if (this.index == ((Node) o).index) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            Polynomial list1 = new Polynomial();
            for (int i = 0; i < n; i++) {
                int data = scan.nextInt();
                int index = scan.nextInt();

                list1.insert(new Node(data, index, null));
                System.out.println("输入节点为：");
                System.out.println(new Node(data, index, null));
            }
            System.out.println("输入多项式list为：");
            System.out.println(list1.toString());
            System.out.println("多项式list 与 多项式list（自身）相加结果 为：");
            list1.add(list1);
            System.out.println(list1.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
