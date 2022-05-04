package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        Solution T = new Solution();
        int A = 53;
        int B = 1953786;
        System.out.println(T.solution(A, B));
    }

    static int solution(int A, int B) {
        String a = String.valueOf(A);
        String b = String.valueOf(B);

        int len = a.length();

        if(b.contains(a)){
            for (int i = 0; i < b.length(); i++) {
                String str = b.substring(i, i+len);

                if(str.equals(a)){
                    return i;
                }
            }
        }


        return -1;
    }
}
