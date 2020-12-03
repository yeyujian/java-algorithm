package test3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * （4）走迷宫。
 * 一个迷宫如图所示，他有一个入口和一个出口，
 * 其中白色单元表示通路，黑色单元表示不通路。
 * 试寻找一条从入口到出口的路径，每一部只能从
 * 一个白色单元走到相邻的白色单元，直至出口。
 * 分别用站和队列求解问题。
 * 这里规定下标0：0为入口
 * 样例：
 6 6
0 1 0 0 0 1
0 0 0 1 0 1
1 0 1 0 0 1
0 0 0 1 1 1
0 1 1 0 0 0
0 0 0 0 1 1
29
 */
public class Maze {

    public static void dfs(int n, int m, int[][] map, int target) {
        Map<Integer, Integer> path = new HashMap<>(); //标记走过路径   key 为后续   value 为前驱
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int targetN = target / m;
        int targetM = target % m;
        int curN = 0, curM = 0;
        do {
            if (stack.isEmpty()) {
                System.out.println("无解");
                return;
            }
            int cur = stack.pop();
            curN = cur / m;
            curM = cur % m;
            //System.out.println("当前：" + String.valueOf(curN) + ":" + String.valueOf(curM));
            for (int i = -1; i < 2; i += 1) { //上下走
                if (i == 0) {
                    for (int j = -1; j < 2; j += 2) {//左右走
                        if (i + curN >= 0 && i + curN < n && j + curM >= 0 && j + curM < m) { //判断是否出界
                            if (map[i + curN][j + curM] == 0 && path.get((i + curN) * m + j + curM) == null) { //判断下一个位置是否可走并且没走过
                                stack.push((i + curN) * m + j + curM);
                                path.put((i + curN) * m + j + curM, curN * m + curM);
                                //System.out.println(String.valueOf(i + curN) + ":" + String.valueOf(j + curM));
                            }
                        }

                    } // for j
                } else {
                    if (i + curN >= 0 && i + curN < n) { //判断是否出界
                        if (map[i + curN][curM] == 0 && path.get((i + curN) * m + curM) == null) { //判断下一个位置是否可走并且没走过
                            stack.push((i + curN) * m + curM);
                            path.put((i + curN) * m + curM, curN * m + curM);
                            //System.out.println(String.valueOf(i + curN) + ":" + String.valueOf(curM));
                        }
                    }
                }
            }// for i
        } while (curN != targetN || curM != targetM); //如果比较结果为false 继续往下走
        while (!stack.isEmpty()) stack.pop();
        stack.push(target);
        int cur = target;
        do {
            cur = path.get(cur);
            stack.push(cur);
        } while (cur != 0);
        System.out.println("输出从起点到终点路径：");
        String str = "";
        while (!stack.isEmpty()) {
            cur = stack.pop();
            curN = cur / m;
            curM = cur % m;
            str += String.valueOf(curN) + ":" + String.valueOf(curM) + " --> ";
        }
        System.out.println(str);
    }

    public static void bfs(int n, int m, int[][] map, int target) {
        Map<Integer, Integer> path = new HashMap<>(); //标记走过路径   key 为后续   value 为前驱
        Queue<Integer> queue = new CircSinglyLinkedListQueue<>();
        queue.add(0);
        int targetN = target / m;
        int targetM = target % m;
        int curN = 0, curM = 0;
        do {
            if (queue.isEmpty()) {
                System.out.println("无解");
                return;
            }
            int cur = queue.poll();
            curN = cur / m;
            curM = cur % m;
            for (int i = -1; i < 2; i += 1) { //上下走
                if (i == 0) {
                    for (int j = -1; j < 2; j += 2) {//左右走
                        if (i + curN >= 0 && i + curN < n && j + curM >= 0 && j + curM < m) { //判断是否出界
                            if (map[i + curN][j + curM] == 0 && path.get((i + curN) * m + j + curM) == null) { //判断下一个位置是否可走并且没走过
                                queue.add((i + curN) * m + j + curM);
                                path.put((i + curN) * m + j + curM, curN * m + curM);
                            }
                        }

                    } // for j
                } else { //i !=0
                    if (i + curN >= 0 && i + curN < n) { //判断是否出界
                        if (map[i + curN][curM] == 0 && path.get((i + curN) * m + curM) == null) { //判断下一个位置是否可走并且没走过
                            queue.add((i + curN) * m + curM);
                            path.put((i + curN) * m + curM, curN * m + curM);
                        }
                    }
                }
            }// for i
        } while (curN != targetN || curM != targetM); //如果比较结果为false 继续往下走
        Stack<Integer> stack = new Stack<>(); //用于从终点找回到起点的路径
        stack.push(target);
        int cur = target;
        do {
            cur = path.get(cur);
            stack.push(cur);
        } while (cur != 0);
        System.out.println("输出从起点到终点路径：");
        String str = "";
        while (!stack.isEmpty()) {
            cur = stack.pop();
            curN = cur / m;
            curM = cur % m;
            str += String.valueOf(curN) + ":" + String.valueOf(curM) + " --> ";
        }
        System.out.println(str);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//迷宫长度
        int m = scan.nextInt();//迷宫宽度
        int[][] map = new int[n][m]; //迷宫地图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scan.nextInt();
            }
        }
        int target = scan.nextInt();//输入出口位置
        System.out.println("使用栈走迷宫结果：");
        dfs(n, m, map, target);
        System.out.println("使用队列走迷宫结果：");
        bfs(n, m, map, target);
    }
}
