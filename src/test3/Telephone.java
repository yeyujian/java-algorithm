package test3;

import java.util.Random;
import java.util.Scanner;

/**
 * 使用3个队列分别保留手机最近10个“未接来电”、“已接来电”、“以拨电话”。
 */
public class Telephone {

    public enum TelephoneEnum {
        NOLISTEN, //未接
        LISTENED, //以接
        CALLED //已拨
    }

    public static class TType {
        public TelephoneEnum type;
        public String number;

        TType(TelephoneEnum type, String number) {
            this.type = type;
            this.number = number;
        }

        @Override
        public String toString() {
            return "TType{" +
                    "type=" + type +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        Queue<TType> NoListenQueue = new CircSinglyLinkedListQueue<>();
        Queue<TType> ListenedQueue = new CircSinglyLinkedListQueue<>();
        Queue<TType> CalledQueue = new CircSinglyLinkedListQueue<>();
        Random random = new Random();
        System.out.println("输入随机产生的电话数");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
//            int index = scan.nextInt();//表示电话类型 是以接电话还是其他
//            String number = scan.next();
            int index = random.nextInt(3);
            String number = String.valueOf(random.nextInt(999999))+String.valueOf(random.nextInt(99999));
            switch (index) {
                case 0: //未接
                    NoListenQueue.add(new TType(TelephoneEnum.NOLISTEN, number));
                    if (NoListenQueue.size() > 10) NoListenQueue.poll();
                    break;
                case 1:
                    ListenedQueue.add(new TType(TelephoneEnum.LISTENED, number));
                    if (ListenedQueue.size() > 10) ListenedQueue.poll();
                    break; //以接
                case 2:
                    CalledQueue.add(new TType(TelephoneEnum.CALLED, number));
                    if (CalledQueue.size() > 10) CalledQueue.poll();
                    break; //已拨
            }
        }

        System.out.println("未接电话列表长度：" + String.valueOf(NoListenQueue.size()));
        System.out.println("未接电话列表：" + NoListenQueue.toString());
        System.out.println("以接电话列表长度：" + String.valueOf(ListenedQueue.size()));
        System.out.println("以接电话列表：" + ListenedQueue.toString());
        System.out.println("已拨电话列表长度：" + String.valueOf(CalledQueue.size()));
        System.out.println("已拨电话列表：" + CalledQueue.toString());

    }
}
