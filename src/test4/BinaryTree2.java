package test4;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree2<T extends Comparable<? super T>> {
    public Node<T> root;

    public BinaryTree2() {
        this.root = null;
    }

    //判断树是否为空
    public boolean isEmpty() {
        return this.root == null;
    }

    //根据key查找节点
    public Node<T> searchNode(Node<T> cur, T key) {
        if (cur == null) return null;
        if (cur.data.equals(key)) return cur;
        Node<T> p = searchNode(cur.left, key);
        if (p == null) return searchNode(cur.right, key);
        return p;
    }

    //查找某个值是否存在存在返回此值，不存在返回null
    public T search(T key) {
        Node<T> p = searchNode(root, key);
        if (p == null) return null;
        return p.data;
    }

    //以广义表表示构造二叉树
    public void getGeneralList() {
        System.out.print("广义表：");
        System.out.println(getGeneralList(root));
    }

    public String getGeneralList(Node<T> node) {
        if (node == null) return null;
        return node.data.toString() + "(" + getGeneralList(node.left) + "," + getGeneralList(node.right) + ")";
    }

    //插入元素到指定父节点
    public Node<T> add(Node<T> node, T key) {
        if (node == null || key == null) return null;
        Node<T> p = new Node<>(key, null, null, null);
        if (node.left == null) {
            node.left = p;
            p.parent = node;
            return p;
        }
        if (node.right == null) {
            node.right = p;
            p.parent = node;
            return p;
        }
        return null;
    }

    //输入叶子结点 前序遍历
    public Node<T> add(T key) {
        Node<T> p = new Node<>(key, null, null, null);
        Node<T> fa, cur = this.root;
        if (cur == null) {
            this.root = p;
            return p;
        }
        do {
            fa = cur;
            cur = cur.data.compareTo(key) >= 0 ? cur.left : cur.right;
        } while (cur != null);
        if (fa.data.compareTo(key) >= 0) fa.left = p;
        else fa.right = p;
        p.parent = fa;
        return p;
    }

    public void remove(Node<T> parent, boolean isleft) {
        if (isleft) {
            parent.left.parent = null;
            parent.left = null; //若parent==null，Java抛出空对象异常
        } else {
            parent.right.parent = null;
            parent.right = null;
        }
    }

    //删除二叉树的所有结点
    public void clear() {
        this.root = null;
    }

    //判断两颗二叉树是否相等
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        return compareTree(root, ((BinaryTree2) obj).root);
    }

    public boolean compareTree(Node<T> node1, Node<T> node2) {
        return node1.data.compareTo(node2.data) == 0 && compareTree(node1.left, node2.left) &&
                compareTree(node1.right, node2.right);
    }

    //返回指定结点的父母结点
    public Node<T> getNodeParent(T key) {
        return searchNode(root, key).parent;
    }

    //返回指定结点的所有祖先结点
    public List<Node<T>> getAncestors(T key) {
        List<Node<T>> list = new LinkedList<>();
        Node node = searchNode(root, key);
        while (node.parent != null) {
            list.add(node.parent);
            node = node.parent;
        }
        return list;
    }

    //返回两结点最近的共同祖先结点
    public Node<T> LCA(Node<T> node1, Node<T> node2) {
        List<Node<T>> list = getAncestors(node1.data); //先获取node1的所有祖先
        while (node2.parent != null) {  //根据node2的链慢慢向上走 直到 parent在node1的祖先列表中
            if (list.indexOf(node2.parent) != -1) return node2.parent;
            node2 = node2.parent;
        }
        return null;
    }

    public static class Node<T extends Comparable<? super T>> {
        public T data;
        public Node<T> parent, left, right;

        public Node() {
            this.data = null;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", parent=" + (parent == null ? "null" : parent.data) +
                    ", left=" + (left == null ? "null" : left.data) +
                    ", right=" +( right == null ? "null" : right.data) +
                    "}";
        }
    }

    public static void main(String[] args) {
        BinaryTree2<String> tree = new BinaryTree2<>();
        System.out.println("构造一颗三叉链表表示的二叉树,插入节点B A C");
        tree.add("B");
        tree.add("A");
        tree.add("C");
        tree.getGeneralList();
        System.out.println("返回指定结点A的父母结点");
        System.out.println(tree.getNodeParent("A"));
        System.out.println("插入节点D");
        tree.add("D");
        tree.getGeneralList();
        System.out.println("返回指定结点D的所有祖先结点");
        System.out.println(tree.getAncestors("D"));
        System.out.println("返回两结点A,C最近的共同祖先结点");
        System.out.println(tree.LCA(tree.searchNode(tree.root,"A"),tree.searchNode(tree.root,"C")));


    }
}
