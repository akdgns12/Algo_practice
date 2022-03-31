package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12852 {
    // 1로 만들기 2 / 실버 1 / DP
    // n을 1로 만드는데 사용하는 횟수의 최솟값일때의 경로
    static int n;
    static int[] dp;
    static int[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1]; // i를 1로 만들기 위해 사용한 연산의 최솟값
        path = new int[n+1]; // 경로 저장

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + 1;
            path[i] = i-1;

            // 3으로 나누어 떨어지면서 1을 뺀 연산이 3으로 나눈 연산 횟수보다 크다면
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i/3] + 1;
                path[i] = i/3;
            }

            // 2로 나누어 떨어지면서 1을 뺀 연산이 2로 나눈 연산 횟수보다 크다면
            if(i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
                path[i] = i/2;
            }

        }

        System.out.println(dp[n]);
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n + " ");
            n = path[n];
        }
        System.out.println(sb);
    }
}
