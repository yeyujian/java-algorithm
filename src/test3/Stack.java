package test3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 使用一个栈，将十进制转换成二进制
 */
public class Stack<T> {

    private Object[] stack;//栈数组存储数据

    private int size;//当前栈大小

    public Stack() {
        stack = new Object[20];//初始栈容量为20
    }

    //判断是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }

    //元素x入栈
    public void push(T x) {
        if (size >= stack.length) { //扩展容量
            int len = stack.length;
            stack = Arrays.copyOf(stack, size + 5);
        }
        stack[size] = x;
        size++;
    }

    //出栈，返回栈顶元素
    public T pop() {
        if (size <= 0) {
            throw new NullPointerException("当前栈已空！！");
        }
        size--;
        return (T) stack[size];
    }

    //返回栈顶元素，未出栈
    public T peek() {
        if (size <= 0) {
            throw new NullPointerException("当前栈已空！！");
        }
        return (T) stack[size - 1];
    }

    /**
     * 对用字符表示的数字/2
     *
     * @param num
     * @param flag 表示数字非零最高位开始位置
     * @return
     */
    public static int cutDown(char[] num, int flag) {
        int n = 0;
        for (int i = flag; i < num.length; i++) {
            int cur = n * 10 + num[i] - '0';
            num[i] = (char) ((cur >> 1)+'0');
            n = cur& 1;
        }
        return n;
    }

    public static void main(String[] args) throws Exception {
        Stack<String> st = new Stack<>();
        System.out.println("输入十进制：");
        Scanner scan = new Scanner(System.in);
        char[] num = scan.nextLine().toCharArray();
        for (int i = 0; i < num.length; i++) {
            if (!Character.isDigit(num[i])) {
                throw new Exception("输入包含非数字字符");
            }
        }
        int flag = 0;
        while (flag < num.length) {
            st.push(String.valueOf(cutDown(num, flag)));
            if (num[flag] == '0') flag++;
        }
        String str = "";
        while (!st.isEmpty()) {
            str += st.pop();
        }
        System.out.println("输出二进制：");
        System.out.println(str);
    }
}
