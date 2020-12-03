package other;

public class Dijkstra {
    private static int MAXNUM = 0x7fffffff;

    private static void dijkstra(int[][] g, int v, int n) {
        int[] dis = new int[n];
        int[] path = new int[n];
        boolean[] help = new boolean[n];
        for (int i = 0; i < n; i++) {
            dis[i] = g[v][i];
            help[i] = false;
            if (g[v][i] < MAXNUM) path[i] = g[v][i];
            else path[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int min = MAXNUM;
            int u = v;
            for (int j = 0; j < n; j++) {
                if (!help[j] && dis[j] < min) {
                    min = dis[j];
                    u = j;
                }
            }

            help[u]=true;
            for(int j=0;j<n;j++){
                if(!help[j]){
                    if(g[u][j]<MAXNUM&&dis[u]+g[u][j]<dis[j]){
                        dis[j]=dis[u]+g[u][j];
                        path[j]=u;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {


    }
}
