package test;

import java.util.Arrays;
import java.util.Scanner;

public class test1_3 {
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            if (a == 0 || b == 0) System.out.println("结果为 0");
            int bt = gcd(a, b);
            System.out.println("a,b,c最大公约数为: " + Integer.toString(gcd(bt,c)));
            System.out.println("a,b最小公倍数为: " + Integer.toString(a * b / bt));

        } catch (Exception e) {
            System.out.println("请输入整数!!!!");
            e.printStackTrace();
        }
    }
}
