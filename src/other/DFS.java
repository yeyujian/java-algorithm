package other;

public class DFS {
    public static void dfs(int[][] graph, int[] help, int pos) {
        help[pos] = 1;
        System.out.println(pos);
        for (int i = 0; i < graph[pos].length; i++) {
            if (help[i] == 0 && i != pos && graph[pos][i] == 1) {
                dfs(graph, help, i);
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };

        int[] help = new int[maze.length];
        dfs(maze,help,0);

    }
}
