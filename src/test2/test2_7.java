package test2;

import java.util.Random;
import java.util.Scanner;

/**
 * 约瑟夫环：
 * 在罗马人占领乔塔帕特后，39 个犹太人与Josephus及他的朋友躲到一个洞中，
 * 39个犹太人决定宁愿死也不要被敌人抓到，于是决定了一个自杀方式，41个人
 * 排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀，然后再
 * 由下一个重新报数，直到所有人都自杀身亡为止。然而Josephus 和他的朋友
 * 并不想遵从。首先从一个人开始，越过k-2个人（因为第一个人已经被越过），
 * 并杀掉第k个人。接着，再越过k-1个人，并杀掉第k个人。这个过程沿着圆圈
 * 一直进行，直到最终只剩下一个人留下，这个人就可以继续活着。
 * 题目：分别采用循环单链表、双链表或循环双链表求解约瑟夫环问题，并分析各操作效率。
 */
public class test2_7 {
    /**
     * 用循环单链表完成
     *
     * @param list
     * @param start 从第几个开始
     * @param dis   每次轮几个
     */
    public static void useCircSinglyLinkedList(CircSinglyLinkedList list, int start, int dis) {
        System.out.println("使用循环单链表");
        if (list.size() == 0) {
            System.out.println("请输入元素！！！");
            return;
        }
        System.out.println("从第 ".concat(String.valueOf(start)).concat("个节点开始计数;移动距离为： ").concat(String.valueOf(dis)));
        CircSinglyLinkedList.Node node = list.getNode(start);//获取位置i的节点
        while (list.size() > 1) {
            System.out.println("当前节点为：" + node.toString());
            CircSinglyLinkedList.Node temp = list.getNodeNext(node, dis - 1); //获取当前节点后第i-1的节点，因为是从当前节点数的所有后i-1个
            if (temp == null) {
                System.out.println("不存在此节点");
                return;
            }
            node = list.getNodeNext(temp, 1);//获取下一次计数的初始节点
            System.out.println("放出：" + list.remove(temp.data));
        }
        System.out.println("最后剩下：" + list.head.next.data);
    }

    /**
     * 使用循环双链表
     *
     * @param list
     * @param start
     * @param dis
     */
    public static void useCHDoublelyLinkedList(CHDoublelyLinkedList list, int start, int dis) {
        System.out.println("使用循环双链表");
        if (list.size() == 0) {
            System.out.println("请输入元素！！！");
            return;
        }
        System.out.println("从第 ".concat(String.valueOf(start)).concat("个节点开始计数;移动距离为： ").concat(String.valueOf(dis)));
        CHDoublelyLinkedList.Node node = list.getNode(start);
        while (list.size() > 1) {
            System.out.println("当前节点为：" + node.toString());
            CHDoublelyLinkedList.Node temp = list.getNodeNext(node, dis - 1);
            if (temp == null) {
                System.out.println("不存在此节点");
                return;
            }
            node = list.getNodeNext(temp, 1);
            System.out.println("放出：" + list.remove(temp.data));
        }
        System.out.println("最后剩下：" + list.head.next.data);
    }


    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            CircSinglyLinkedList<Integer> list1 = new CircSinglyLinkedList<>();
            CHDoublelyLinkedList<Integer> list2 = new CHDoublelyLinkedList<>();
            for (int i = 0; i < n; i++) {
                int temp = scan.nextInt();
                list1.insert(temp);
                list2.insert(temp);
            }

            Random random = new Random();
            System.out.println("用循环单链表:");
            System.out.println(list1.toString());
            useCircSinglyLinkedList(list1, random.nextInt(list1.size()) + 1, random.nextInt(list1.size()) + 1);
            //useCircSinglyLinkedList(list1,1,1);
            System.out.println("循环双链表:");
            System.out.println(list2.toString());
            //useCHDoublelyLinkedList(list2,1,1);
            useCHDoublelyLinkedList(list2, random.nextInt(list2.size()) + 1, random.nextInt(list2.size()) + 1);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
