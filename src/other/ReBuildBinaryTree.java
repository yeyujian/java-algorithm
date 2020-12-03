package other;

import java.util.HashMap;
import java.util.Map;

public class ReBuildBinaryTree {

    static private class Node {
        int num;
        public Node left, right;

        public Node(int num) {
            this.num = num;
        }
    }

    public Node reBulidBinaryTree(int[] pre, int[] in) {
        Map<Integer, Integer> preIndex = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            preIndex.put(pre[i], i);
        }

        return buildTree(preIndex, in, 0, in.length - 1);
    }

    private Node buildTree(Map<Integer, Integer> preIndex, int[] in, int start, int end) {
        if (start == end) {
            return new Node(in[start]);
        }

        int indexOfRoot = start;
        for (int i = start; i <= end; i++) {
            if (preIndex.get(in[i]) < preIndex.get(in[indexOfRoot])) {
                indexOfRoot = i;
            }
        }
        Node root = new Node(in[indexOfRoot]);
        if (start <= indexOfRoot - 1) root.left = buildTree(preIndex, in, start, indexOfRoot - 1);
        if (indexOfRoot + 1 <= end) root.right = buildTree(preIndex, in, indexOfRoot + 1, end);
        return root;
    }
}
