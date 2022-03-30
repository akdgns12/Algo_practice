package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659 {
    // 구간 합 구하기 4 / 실버3 / DP
    static int n, m;
    static int[] a = new int[100001];
    static int[] dp = new int[100001];

    /**
     * dp[i] = a[1] + a[2] + ...+ a[i]
     * dp[i] = dp[i-1] + a[i]
     *
     * a[i] + a[i+1] + ... + a[j]
     * = (a[1] + a[2] + ...a[j]) - (a[1] + a[2] + ... + a[i-1])
     * = dp[j] - dp[i-1]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1] + a[i];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(dp[end] - dp[start-1]);
        }
    }
}
