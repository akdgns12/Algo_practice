package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[301]; // dp[i] : i번째 계단까지의 최대 점수
        int[] step = new int[301];

        for(int i=1; i<=n; i++)
            step[i] = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = step[1];
        dp[2] = step[1] + step[2];
        /*
            조건에서 마지막 계단을 무조건 밟아야 하므로
            마지막 계단인 n번째 계단을 밟는 경우의 수는 2가지
            1. n-3, n-1 번째 계단을 밟고 올라오는 경우
            2. n-2번째 계단을 밟고 올라오는 경우
            이렇게 2가지 경우로 도출되는 점화식은
            dp[i] = Math.max(dp[i-3] + step[i-1] + step[i], dp[i-2] + step[i])
         */

        for(int i=3; i<=n; i++)
            dp[i] = Math.max(dp[i-2] + step[i], dp[i-3] + step[i-1] + step[i]);

        System.out.println(dp[n]);
    }
}
