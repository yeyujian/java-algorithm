package other;

import java.util.Arrays;

public class KMP {

    public static int kmp(String str, String dest, int[] next) {
        int x=0;
        for(int i=0;i<str.length();i++){
            while (x > 0 && str.charAt(i) != dest.charAt(x)) {
                x = next[x-1];
            }
            if(str.charAt(i)==dest.charAt(x)){
                x++;
            }
            if(x==dest.length()){
                return i-x+1;

            }
        }
        return -1;
    }

    public static int[] next(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        int x = 0;
        for (int i = 1; i < dest.length(); i++) {
            while (x > 0 && dest.charAt(i) != dest.charAt(x)) {
                x = next[x - 1];
            }
            if (dest.charAt(i) == dest.charAt(x)) {
                x++;
            }
            next[i] = x;
        }

        return next;
    }

    public static void main(String[] args) {
        System.out.println("??????");
        String a = "ababa";
        String b = "ssdfgasdbababa";
        int[] next = next(a);
        System.out.println(Arrays.toString(next));
        int res = kmp(b, a,next);
        System.out.println(res);


    }
}
