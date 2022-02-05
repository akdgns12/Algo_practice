package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * n=1일때 1개
 * n=2일때 00, 11 2개
 * n=3일떼 001, 100, 111 3개
 * n=4일때 0000, 0011, 1100, 1001, 1111 5개
 * n=5일때 00001, 00100, 10000, 00111, 10011, 11001, 11100, 11111 8개
 * => 피보나치 수열처럼 늘어나는 것은 확인할 수 있다
 *
 */
public class BOJ1904 {
    // 01타일 / 실버 3 / DP
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i=4; i<=N; i++)
            dp[i] = (dp[i-1] + dp[i-2]) % 15746;

        System.out.println(dp[N]);
    }
}
