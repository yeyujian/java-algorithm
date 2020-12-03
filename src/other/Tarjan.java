package other;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class Tarjan {
    private int numOfNode;
    private List<ArrayList<Integer>> graph;//图
    private List<ArrayList<Integer>> result;//保存极大强连通图
    private boolean[] inStack;
    private Stack<Integer> stack;
    private int[] dfn;
    private int[] low;
    private int time;

    public Tarjan(List<ArrayList<Integer>> graph, int numOfNode) {
        this.graph = graph;
        this.numOfNode = numOfNode;
        this.inStack = new boolean[numOfNode];
        this.stack = new Stack<Integer>();
        dfn = new int[numOfNode];
        low = new int[numOfNode];
        Arrays.fill(dfn, -1);
        Arrays.fill(low, -1);
        result = new ArrayList<ArrayList<Integer>>();
    }

    public List<ArrayList<Integer>> run() {
        for (int i = 0; i < numOfNode; i++) {
            if (dfn[i] == -1) {
                tarjan(i);
            }
        }
        return result;
    }

    public void tarjan(int current) {
        dfn[current] = low[current] = time++;
        inStack[current] = true;
        stack.push(current);

        for (int i = 0; i < graph.get(current).size(); i++) {
            int next = graph.get(current).get(i);
            if (dfn[next] == -1) {//-1代表没有被访问
                tarjan(next);
                low[current] = Math.min(low[current], low[next]);
            } else if (inStack[next]) {
                low[current] = Math.min(low[current], dfn[next]);
            }
        }

        if (low[current] == dfn[current]) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int j = -1;
            while (current != j) {
                j = stack.pop();
                inStack[j] = false;
                temp.add(j);
            }
            result.add(temp);
        }
    }

    public static void main(String[] args) {
        //创建图
        int numOfNode = 6;
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numOfNode; i++) {
            graph.add(new ArrayList<Integer>());
        }
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(3);
        graph.get(2).add(4);
        graph.get(3).add(0);
        graph.get(3).add(5);
        graph.get(4).add(5);
        //调用Tarjan算法求极大连通子图
        Tarjan t = new Tarjan(graph, numOfNode);
        List<ArrayList<Integer>> result = t.run();
        //打印结果
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }

}



