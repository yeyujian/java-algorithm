package test3;

public class testddd {
    public static int gcd1(int a, int b)          //返回a与b的最大公因数

    {

        while (b != 0) {

            int temp = a % b;

            a = b;

            b = temp;

        }

        return a;

    }


    public static int gcd2(int a, int b)          //返回a,b的最大公因数，递归方法

    {

        if (b == 0)

            return a;

        if (a < 0)

            return gcd2(-a, b);

        if (b < 0)

            return gcd2(a, -b);

        return gcd2(b - a, a);

    }


    public static void main(String args[]) {

        int a = 12, b = 18, c = 24;

        System.out.println("gcd1(" + a + "," + b + "," + c + ")=" + gcd1(gcd2(a, b), c));

    }
}
