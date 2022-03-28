package 백준.company.정렬2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ5648 {
    // 역원소 정렬 / 실버 4 / 정렬
    static int n;
    static long[] result;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        result = new long[n];

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            StringBuilder sb = new StringBuilder();
            sb.append(str);

            result[i] = Long.parseLong(sb.reverse().toString());
        }

        Arrays.sort(result);
        for (long val : result) System.out.println(val);
    }
}
