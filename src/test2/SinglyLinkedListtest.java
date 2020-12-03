package test2;

import java.util.Scanner;


public class SinglyLinkedListtest<T extends Comparable> {
    /**
     * 节点
     *
     * @param <T>
     */
    public static class Node<T extends Comparable> implements Comparable<Node<T>> {
        public Node<T> next = null;
        public T value;

        Node(T value) {
            this.value = value;
        }

        public Node() {

        }

        public int compareTo(Node<T> node) {
            return value.compareTo(node.value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private Node<T> root;
    private int size = 0;

    public SinglyLinkedListtest() {
        this.root = null;
    }

    /**
     * 在cur的末尾插入node
     *
     * @param cur
     * @param node
     */
    private void insert(Node<T> cur, Node<T> node) {
        if (cur == null) {
            node.next = root;
            root = node;
        } else {
            Node tmp = cur.next;
            cur.next = node;
            node.next = tmp;
        }
        size++;
    }

    /**
     * 插入节点
     *
     * @param node
     */
    public void insertOne(Node<T> node) {
        System.out.println("插入元素:");
        if (root == null) {
            insert(null, node);
            return;
        }
        Node<T> past = null;
        Node<T> cur = root;
        int flag = 0;
        while (flag < size) {
            if (cur.compareTo(node) > 0) {
                insert(past, node);
                break;
            } else {
                if (cur.next != null) {
                    past = cur;
                    cur = cur.next;

                } else {
                    cur.next = node;
                    size++;
                    break;
                }
                flag++;
            }
        }
        System.out.println(size);
    }

    /**
     * 删除cur后面的node节点
     *
     * @param cur
     * @param node
     */
    private void delete(Node<T> cur, Node<T> node) {
        if (cur == null) {
            root = node.next;
        } else {
            cur.next = node.next;
        }
    }

    /**
     * 删除对应节点
     *
     * @param value
     */
    public void deleteOne(T value) {
        SinglyLinkedListtest.Node<T> node = new SinglyLinkedListtest.Node<>(value);
        Node<T> past = null, cur = root;
        while (cur != null) {
            if (cur.compareTo(node) == 0) {
                delete(past, cur);
                break;
            }
            past = cur;
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        Node<T> tmp = root;
        String str = "[";
        while (tmp != null) {
            str += tmp.toString() + ",";
            tmp = tmp.next;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            SinglyLinkedListtest<Integer> list = new SinglyLinkedListtest<>();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                System.out.println("输入了:".concat(String.valueOf(tmp)));
                SinglyLinkedListtest.Node<Integer> node = new SinglyLinkedListtest.Node<>(tmp);

                list.insertOne(node);
            }
            System.out.println("输入完成!!!表为:");
            System.out.println(list.toString());
            list.deleteOne(9);
            System.out.println("修改完成!!!表为:");
            System.out.println(list.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
