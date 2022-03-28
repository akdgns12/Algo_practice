package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2750_카운팅정렬 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        /*
            range : -1000 ~ 1000
            0은 index[1000]을 의미
         */
        boolean[] arr = new boolean[2001];

        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000] = true;
        }

        // 정렬 과정이 따로 없음

        for (int i = 0; i < 2002; i++) {
            if (arr[i]) {
                System.out.println(i - 1000);
            }
        }
    }
}
