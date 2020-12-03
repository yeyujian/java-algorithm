package test4;

import com.yyj.nettychat.entity.MsgActionEnum;

public class test {
    public static class Node implements Cloneable {
        public Node next = null;
        public String str;

        Node() {
            str = new String("hello");
        }

        @Override
        public Node clone() {
            Node obj = null;
            try {
                obj = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            } finally {
                return obj;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", str='" + str + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
//        Node n1 = new Node();
//        Node n2 = new Node();
//        n2.str = "mmp";
//        n1.next = n2;
//        Node n3 = n1.clone();
//        System.out.println(n1);
//        n3.str="ddd";
//        n3.next.str="???";
//        System.out.println(n3);
//        System.out.println(n1);
        switch (MsgActionEnum.fromType(2)) {
            case CONNECT:// 处理刚连接信息
                System.out.println("hello 0");
                break;
            case CHATFRIEND://发送好友信息
                System.out.println("hello 1");
                break;
            case CHATGROUP://发送群聊消息
                System.out.println("hello 2");
                break;

        }
    }
}
