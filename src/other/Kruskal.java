package other;

import java.util.Scanner;

public class Kruskal {
    static class Edge {
        private int begin;
        private int end;
        private int weight;
        private boolean isSeleted;
        char No;

        public boolean getIsSeleted() {
            return this.isSeleted;
        }

        public void setIsSeleted(boolean is) {
            this.isSeleted = is;
        }

        public Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }

        public Edge() {
            this.isSeleted = false;
        }
    }

    private static void sort(Edge[] edges) {
        Edge temp = null;
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[j].weight < edges[i].weight) {
                    temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("请输入图有几条边和几个点:");
        int n = scn.nextInt();  //保存边数
        int m = scn.nextInt(); //保存点数
        Edge[] edges = new Edge[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new Edge();  //创建出真实的边出来
            edges[i].weight = scn.nextInt();
            edges[i].begin = scn.nextInt();
            edges[i].end = scn.nextInt();
            edges[i].No = (char) ('a' + i);

        }

        sort(edges);
        int step = 0;
        int[] help = new int[m];//记录已经录用的点;

        for (int i = 0; i < n; i++) {
            if (step < m && (help[edges[i].begin] == 0 || help[edges[i].end] == 0)) {
                help[edges[i].begin] = help[edges[i].end] = 1;
                edges[i].setIsSeleted(true);
                step++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (edges[i].getIsSeleted())
                System.out.println(edges[i].No);
        }
    }
}
