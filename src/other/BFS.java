package other;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void bfs(int[][] graph) {
        int[] help=new int[graph.length];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        help[0]=1;
        System.out.println(0);

        while(!queue.isEmpty()){
            System.out.println("========");
            int pos=queue.poll();
            for(int i=0;i<graph[pos].length;i++){
                if(help[i]==0&&i!=pos&&graph[pos][i]==1){

                    queue.add(i);
                    System.out.println(i);
                    help[i]=1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };


        bfs(maze);

    }
}
