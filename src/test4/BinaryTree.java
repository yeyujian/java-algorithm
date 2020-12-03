package test4;

import test3.Stack;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree<T extends Comparable<? super T>> {

    public Node<T> root;

    public BinaryTree() {
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

    //插入元素到指定父节点
    public Node<T> add(Node<T> node, T key) {
        if (node == null || key == null) return null;
        Node<T> p = new Node<>(key, null, null);
        if (node.left == null) {
            node.left = p;
            return p;
        }
        if (node.right == null) {
            node.right = p;
            return p;
        }
        return null;
    }

    //输入叶子结点 前序遍历
    public Node<T> add(T key) {
        Node<T> p = new Node<>(key, null, null);
        Node<T> fa, cur = this.root;
        if (cur == null) {
            this.root = p;
            return p;
        }
        do {
            fa = cur;
            cur = cur.data.compareTo(key) >= 0 ? cur.left : cur.right;
        } while (cur != null);  //遍历到没有孩子的节点位置   这里根据节点大小来插入   左节点《 当前节点 《 右节点
        if (fa.data.compareTo(key) >= 0) fa.left = p;
        else fa.right = p;
        return p;
    }

    private int getLeftCount(Node<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return getLeftCount(node.left) + getLeftCount(node.right);
    }


    //求二叉树中叶子结点个数  后序遍历
    public int LeftCount() {
        return getLeftCount(root);
    }


    public void reverseNode(Node<T> node) {
        Node<T> p = node.left;
        node.left = node.right;
        node.right = p;
        if (node.left != null) reverseNode(node.left);
        if (node.right != null) reverseNode(node.right);
    }

    //将节点左右子树调换 前序遍历
    public void reverse() {
        reverseNode(root);
    }

    //验证二叉树的性质3：n0=n2+1  求度为2的节点
    public int twoLeaf() {
        return twoLeaf(root);
    }

    // 前序遍历
    private int twoLeaf(Node<T> node) {
        if (node == null) return 0;
        return (node.left != null && node.right != null) ? 1 : 0 + twoLeaf(node.left) + twoLeaf(node.right);
    }

    //输出值大于k的结点 前序遍历
    public Node<T> getNodeUpToKey(Node<T> cur, T key) {
        if (cur == null) return null;
        if (cur.data.compareTo(key) > 0) return cur;
        Node<T> p;
        return (p = getNodeUpToKey(cur.left, key)) != null ? p : getNodeUpToKey(cur.right, key);
    }

    public Node<T> getNodeUpToKey(T key) {
        return getNodeUpToKey(root, key);
    }


    //已知先根和中根次序遍历序列构造二叉树
    public BinaryTree(T[] pre, T[] mid) {
        this();
        if (pre.length == 0 || mid.length == 0) {
            return;
        }
        this.root = buildTree(pre, mid, 0, 0, mid.length - 1);
    }

    public Node<T> buildTree(T[] pre, T[] mid, int pCur, int mStart, int mEnd) {  //通过递归求出树
        if (pCur >= pre.length || mStart > mEnd) return null;
        T element = pre[pCur];
        Node<T> node = new Node<>(element, null, null);  //获取先根遍历的当前节点   也就是树的当前节点
        int pos = mStart;
        while (pos <= mEnd && mid[pos].compareTo(element) != 0) pos++;
        node.left = buildTree(pre, mid, pCur + 1, mStart, pos - 1);  //当前节点在中跟遍历数组中  左边的集合是当前节点左子树
        node.right = buildTree(pre, mid, pCur + pos - mStart + 1, pos + 1, mEnd);//当前节点在中跟遍历数组中  右边的集合是当前节点右子树
        return node;
    }

    public void remove(Node<T> parent, boolean isleft) {
        if (isleft)
            parent.left = null; //若parent==null，Java抛出空对象异常
        else parent.right = null;
    }

    //删除二叉树的所有结点
    public void clear() {
        this.root = null;
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

    //判断两颗二叉树是否相等
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        return compareTree(root, ((BinaryTree) obj).root);
    }

    public boolean compareTree(Node<T> node1, Node<T> node2) {
        if(node1==null&&node2==null) return true;
        if(node1==null&&node2!=null) return false;
        if(node1!=null&&node2==null) return false;
        return node1.data.compareTo(node2.data) == 0 && compareTree(node1.left, node2.left) &&
                compareTree(node1.right, node2.right);  //判断当前节点  左右子节点是否相等 当都相等时 树相等
    }

    //求结点所在的层次  前序遍历
    public int getNodeDep(T key) {
        return getNodeDep(key, root, 1);
    }

    public int getNodeDep(T key, Node<T> node, int depth) {
        if (node == null) return 0;
        if (node.data.compareTo(key) == 0) return depth;
        int dep = 0;
        return (dep = getNodeDep(key, node.left, depth + 1)) != 0 ? dep : getNodeDep(key, node.right, depth + 1);
    }

    //求一颗二叉树在后根次序遍历下第一个访问的结点
    public Node<T> getFirstPostOrder() {
        Node<T> node = this.root;
        if (node == null) return null;
        while (node.left.left != null) node = node.left;  //一直求左孩子可得到结果
        return node.right != null ? node.right : node; //如果当前节点有右孩子，右孩子的后序遍历先于当前根节点，结果为右孩子，否则为当前节点
    }

    //复制一颗二叉树
    public BinaryTree<T> clone() throws CloneNotSupportedException {
        BinaryTree<T> tree = new BinaryTree<>();
        tree.root = this.root.clone();
        return tree;
    }

    //判断一颗二叉树是否为完全二叉树
    public boolean checkIsComplete() {
        return checkIsComplete(root);
    }

    public boolean checkIsComplete(Node<T> cur) {
        if (cur == null) return true;
        if (cur.right != null && cur.left == null) return false;
        return checkIsComplete(cur.left) && checkIsComplete(cur.right);
    }

    //实现二叉树后根次序遍历的非递归算法
    public void pastOrder() {
        Stack<Node<T>> stack = new Stack<>(); //使用栈来保存节点实现后根遍历
        Map<Node<T>, Integer> map = new HashMap<>(); //使用map保存节点是否访问过
        stack.push(this.root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.peek();
            if (map.get(node) == null) {  //当前节点是第一次访问时，记录次数并把左右孩子放入栈
                map.put(node, 1);
                if (node.right != null)
                    stack.push(node.right);
                if (node.left != null)
                    stack.push(node.left);
            } else { //当第二次访问时候输出节点
                System.out.println(node.data);
                stack.pop();
            }
        }
    }

    public static class Node<T extends Comparable<? super T>> implements Cloneable {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node() {
            data = null;
            left = right = null;
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node<T> clone() throws CloneNotSupportedException {
            return (Node<T>) super.clone();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        BinaryTree<String> tree = new BinaryTree<>();
        System.out.println("当前树tree为：");
        tree.getGeneralList();
        System.out.println("输入叶子结点A");
        tree.add("B");
        System.out.println("插入节点A,C");
        tree.add("A");tree.add("C");
        System.out.println("当前树tree为：");
        tree.getGeneralList();
        System.out.println("\n求二叉树中叶子结点个数:".concat(String.valueOf(tree.LeftCount())));
        System.out.println("\n验证二叉树的性质3：n0=n2+1");
        System.out.println("N0为:".concat(String.valueOf(tree.LeftCount())));
        System.out.println("N2为:".concat(String.valueOf(tree.twoLeaf())));
        System.out.println("\n输出值大于字面B的结点");
        System.out.println(tree.getNodeUpToKey("B"));
        System.out.println("\n已知先根和中根次序遍历序列构造二叉树tree2");
        System.out.println("\n先根遍历为（A B C D E F G），中根遍历为(C B D A F E G)");
        String[] pre={"A","B","C","D","E","F","G"},mid={"C","B","D" ,"A" ,"F" ,"E", "G"};
        BinaryTree<String> tree2=new BinaryTree<>(pre,mid);
        System.out.println("当前树tree2为：");
        tree2.getGeneralList();
        System.out.println("\n判断两颗二叉树是否相等:");
        System.out.println("tree==tree?".concat(tree.equals(tree)?"true":"false"));
        System.out.println("tree==tree2?".concat(tree.equals(tree2)?"true":"false"));
        System.out.println("\n求G结点所在tree2的层次");
        System.out.println(tree2.getNodeDep("G"));
        System.out.println("\n求tree2在后根次序遍历下第一个访问的结点");
        System.out.println(tree.getFirstPostOrder());
        System.out.println("\n复制一颗二叉树tree2");
        BinaryTree<String> tree3=tree2.clone();
        System.out.println("\n当前树tree3为：");
        tree3.getGeneralList();
        System.out.println("tree2==tree3?".concat(tree2.equals(tree3)?"true":"false"));
        System.out.println("\n判断tree3是否为完全二叉树");
        System.out.println(tree3.checkIsComplete());
        System.out.println("\n实现二叉树tree2后根次序遍历的非递归算法");
        tree2.pastOrder();
    }

}
