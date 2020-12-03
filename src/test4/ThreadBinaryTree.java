package test4;

public class ThreadBinaryTree<T> {

    public Node<T> root;

    public ThreadBinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    //数组构建中序线索二叉树  在构造二叉树时进行线索化
    public ThreadBinaryTree(T[] prelist) {
        this.root = create(prelist, 0);
        inorderThread(this.root);
    }

    private Node<T> front = null;// 前驱节点

    //中序线索化二叉树
    private void inorderThread(Node<T> node) {
        if (node == null) return;
        inorderThread(node.left);
        if (node.left == null) {  //叶子节点
            node.ltag = true;
            node.left = front;
        }
        if (node.right == null) {
            node.rtag = true;
        }
        if (front != null && front.rtag) front.right = node;
        front = node;
        inorderThread(node.right);
    }

    //数组构建完全二叉树
    private Node<T> create(T[] prelist, int len) {
        Node<T> node = null;
        if (len < prelist.length) {
            T key = prelist[len];
            node = new Node<>(key, null, false, null, false);
            node.left = create(prelist, len * 2 + 1);
            node.right = create(prelist, len * 2 + 2);

        }
        return node;
    }

    //以广义表表示构造二叉树
    public void getGeneralList() {
        System.out.print("广义表：");
        System.out.println(getGeneralList(root));
    }

    public String getGeneralList(Node<T> node) {
        if (node == null) return null;
/*
        if(node.ltag){
            System.out.println(node.data.toString().concat("|左|").concat(node.left==null?"null":node.left.data.toString()));
        }
        if(node.rtag){
            System.out.println(node.data.toString().concat("|右|").concat(node.right==null?"null":node.right.data.toString()));
        }
*/

        return node.data.toString() + "(" + (node.ltag ? "null" : getGeneralList(node.left)) + "," + (node.rtag ? "null" : getGeneralList(node.right)) + ")";
    }

    //调用求结点的前驱结点算法，按中根次序遍历一颗中序线索二叉树
    public void inOrder() {
        System.out.println("中序遍历");
        Node<T> node = this.root;
        while (node.right != null && !node.rtag) node = node.right;
        inOrder(node);

    }

    //中序遍历当前节点
    public void inOrder(Node<T> node) {
        if (node == null) {
            return;
        }
//        System.out.println(node.data);
        inOrder(inPre(node));
        System.out.print(node.data.toString().concat("<-"));
    }


    //求结点的前驱结点
    public Node<T> inPre(Node<T> node) {
        if (node.ltag) return node.left;
        node = node.left;
        while (!node.rtag) node = node.right;
        return node;
    }

    //求节点的后续节点
    public Node<T> inPost(Node<T> node) {
        if (node.rtag) return node.right;
        node = node.right;
        while (!node.ltag) node = node.left;
        return node;
    }


    //按后根次序遍历中序线索二叉树
    public void postOrder(Node<T> node) {
        if (node == null) {
            return;
        }
//        System.out.println(node.data);
        if(!node.ltag)
            postOrder(node.left);
        if (!node.rtag)
            postOrder(node.right);
        System.out.print(node.data.toString().concat("<-"));
    }

    public void postOrder() {
        System.out.println("后根遍历");
        Node<T> node = this.root;
        postOrder(this.root);
    }

    //如果存在子节点就修改不存在就插入
    public void insert(Node<T> parent, T key, boolean isLeft) {
        if (isLeft) {   //为左节点
//            Node<T> preNode = inPre(parent.left);
            if (parent.ltag) {
                parent.left = new Node<>(key, parent.left, true, parent, true);
            } else {
                parent.left.data = key;
            }
            parent.ltag=false;
        } else {
//            Node<T> postNode = inPost(parent.right);
            if (parent.rtag) {
                parent.left = new Node<>(key, parent, true, parent.right, true);
            } else {
                parent.right.data = key;
            }
            parent.rtag=false;
        }
    }

    public Node<T> searchNode(Node<T> node, T key) {
        if (node.data.equals(key)) return node;
        Node<T> p = null;
        if (!node.ltag) p = searchNode(node.left, key);
        if (p == null && !node.rtag) p = searchNode(node.right, key);
        return p;
    }

    public void remove(Node<T> parent, boolean isleft) {
        if (isleft) {  //为左节点
            Node<T> node = parent;
            while (!node.ltag) node = node.left;
            node = inPre(node);
            parent.ltag = true;
            parent.left = node; //更换前驱  删除树后续节点
        } else {
            Node<T> node = parent;
            while (!node.rtag) node = node.right;
            node = inPost(node);
            parent.rtag = true;
            parent.right = node;
        }
    }

    //线索二叉树的二叉链表结点类
    public static class Node<T> {
        public T data;              //数据元素
        public Node<T> left, right;
        public boolean ltag, rtag;  //false表示孩子，true表示线索

        public Node(T data, Node<T> left, boolean ltag, Node<T> right, boolean rtag) {
            this.data = data;
            this.left = left;
            this.ltag = ltag;
            this.right = right;
            this.rtag = rtag;
        }

        public Node() {
            this.data = null;
            this.left = null;
            this.ltag = false;
            this.right = null;
            this.rtag = false;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", ltag=" + ltag +
                    ", rtag=" + rtag +
                    '}';
        }

        public boolean isLeaf() //判断是否叶子结点
        {
            return !this.ltag && !this.rtag;
        }
    }

    public static void main(String[] args) {
        String[] prelist = {"A", "B", "D", "E", "G", "C", "F", "H", "K"};
        // String[] prelist = {"A", "B", "D"};
        System.out.println("用数组遍历构建二叉树并且在构造二叉树时进行线索化{\"A\", \"B\", \"D\", \"E\", \"G\", \"C\", \"F\", \"H\", \"K\"}");
        ThreadBinaryTree<String> tree = new ThreadBinaryTree<>(prelist);
        tree.getGeneralList();
        System.out.println("调用求结点的前驱结点算法，按中根次序遍历一颗中序线索二叉树");
        tree.inOrder();
        System.out.println("\n按后根次序遍历中序线索二叉树");
        tree.postOrder();
        System.out.println("\n插入节点I到F节点左边");
        tree.insert(tree.searchNode(tree.root,"F"),"I",true);
        tree.getGeneralList();
        System.out.println("删除节点I");
        tree.remove(tree.searchNode(tree.root,"F"),true);
        tree.getGeneralList();

    }
}
