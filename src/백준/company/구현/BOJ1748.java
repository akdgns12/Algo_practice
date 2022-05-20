package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1748 {
    // 수 이어쓰기 1 / 실버 1 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        // 1~n까지 이어서 쓴 수의 총자릿수
        // 형변환 말고 최적화 시켜서 풀어야 함, 안그럼 시초남
        /*
            그러나 단순 브로트포스로 풀게되면 시간초과가 생기기 때문에 최적화를 시키고 풀어야한다. 다음과 같이 풀수 있다.
            N=120
            - 1 ~ 9 => (9 - 1 +1) X 1
            - 10 ~ 99 => (99 - 10 +1) X 2
            - 100 ~ 120 => (120 - 100 +1) X 3
         */
        long ans = 0;
        for (int start = 1, len = 1; start <= num; start *= 10, len++) {
            int end = start * 10 - 1;
            if (end > num) {
                end = num;
            }
            ans += (long)(end - start + 1) * len;
        }

        System.out.println(ans);
    }
}

