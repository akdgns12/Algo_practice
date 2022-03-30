package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9095 {
    // 1,2,3 더하기 / 실버 3 / DP
    static int[] dp = new int[20]; // dp[i] = i를 1,2,3의 합으로 나타내는 방법의 수

    /**
     * dp[4] = dp[3] + dp[2] + dp[1]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
