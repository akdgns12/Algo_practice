package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {
    // RGB 거리 / 실버 1 / DP
    static int n;
    static int[][] dp;
    static int[] r,g,b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        r = new int[1001];
        g = new int[1001];
        b = new int[1001];
        dp = new int[1001][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        /*
            점화식
            dp[n][0] = Math.min(dp[n-1][1], dp[n-1][2]) + r[n];
         */
        dp[1][0] = r[1];
        dp[1][1] = g[1];
        dp[1][2] = b[1];
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + r[i];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + g[i];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + b[i];
        }

        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));

    }
}
