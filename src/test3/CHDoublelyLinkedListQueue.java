package test3;

import test2.CHDoublelyLinkedList;

import java.util.Scanner;

public class CHDoublelyLinkedListQueue<T> implements Queue<T> {

    private CHDoublelyLinkedList<T> queue;

    public CHDoublelyLinkedListQueue() {
        queue = new CHDoublelyLinkedList<T>();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    @Override
    public boolean add(T x) {
        if (x == null)
            return false;
        return queue.insert(x) > 0;
    }

    @Override
    public T peek() {
        if(isEmpty()) return null;
        return queue.head.next.data;
    }

    @Override
    public T poll() {
        if(isEmpty()) return null;
        T answer = queue.head.next.data;
        queue.remove(answer);
        return answer;
    }

    @Override
    public String toString() {

        return "CHDoublelyLinkedListQueue{" +
                "queue=" + queue +
                '}';
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            //选择输入的个数
            int n = scan.nextInt();
            Queue<Integer> q = new CHDoublelyLinkedListQueue<>();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                q.add(tmp);
            }
            System.out.println("输入队列顺序位：");
            System.out.println(q.toString());
            while (!q.isEmpty()) {
                System.out.println("队首元素：".concat(q.peek().toString()));
                System.out.println("队首元素出队列当前队列为：");
                q.poll();
                System.out.println(q.toString());
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
