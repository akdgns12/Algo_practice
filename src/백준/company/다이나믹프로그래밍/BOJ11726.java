package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726 {
    // 2xn 타일링 / 실버 3 / DP
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[100001]; // dp[i] : 2xi크기의 직사각형을 채우는 방법의 수

        dp[1] = 1;
        dp[2] = 2;
        /**
         * mod 연산을 한 결과값을 출력해야 할 때는 반드시 연산할 때마다 mod연산을 해줘야함.
         * 계속 숫자를 더하고 마지막 출력시에만 mod연산을 해줄 경우 Integer.MAX_VALUE를 넘어
         * Overflow가 발생하기 때문에 잘못된 값 출력될 수도 있다.
         */
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 10007;
        }

        System.out.println(dp[n]);
    }
}
