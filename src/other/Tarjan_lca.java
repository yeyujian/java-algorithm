package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tarjan_lca {
    static class Node {
        public int c = 0, next = -1;
    }

    int cnt, n, m;
    private final static int MAXN = 10005;
    public List<ArrayList<Integer>> list;
    public boolean[] vis;
    int[] per, head, in_num;
    Node[] edge;

    Tarjan_lca() {
        per = new int[MAXN];
        head = new int[MAXN];
        in_num = new int[MAXN];
        edge = new Node[MAXN];
        vis = new boolean[MAXN];
        cnt = 0;
    }

    void init() {

        list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<Integer>());
        }
        Arrays.fill(head, -1);
        Arrays.fill(vis, false);
        for (int i = 1; i <= n; i++) {
            per[i] = i;
        }
    }

    void add(int x, int y) {
        edge[++cnt] = new Node();
        edge[cnt].next = head[x];
        edge[cnt].c = y;
        head[x] = cnt;
    }

    int find(int x) {
        if (per[x] != x) per[x] = find(per[x]);
        return per[x];
    }

    void union(int x, int y) {

        x = find(x);
        y = find(y);
        if (x == y)
            return;
        per[x] = y;
    }

    void tarjan(int x) {
        System.out.println("tarjan: " + new Integer(x).toString());
        for (int i = head[x]; i != -1; i = edge[i].next) {
            int v = edge[i].c;
            System.out.println(v);
            tarjan(v);
            union(v, x);
        }
        vis[x] = true;
        for (int i = 0; i < list.get(x).size(); i++) {
            if (vis[list.get(x).get(i)]) {
                System.out.printf("%d 和 %d 的LAC是 %d\n", x, list.get(x).get(i), find(list.get(x).get(i)));
            }
        }
    }

    public static void main(String[] args) {
        Tarjan_lca lca = new Tarjan_lca();
        Scanner scan = new Scanner(System.in);
        lca.n = scan.nextInt();
        lca.m = scan.nextInt();
        int x, y;
        lca.init();
        for (int i = 1; i < lca.n; i++) {

            x = scan.nextInt();
            y = scan.nextInt();
            lca.add(x, y);
            lca.in_num[y]++;
        }


        for (int i = 0; i < lca.m; i++) {
            x = scan.nextInt();
            y = scan.nextInt();
            lca.list.get(x).add(y);
            lca.list.get(y).add(x);
        }
        int root = 0;
        for (int i = 1; i <= lca.n; i++) {
            if (lca.in_num[i] == 0) root = i;
        }

        lca.tarjan(root);
    }
}
