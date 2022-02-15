package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일01_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i=4; i<=n; i++)
            dp[i] = (dp[i-2] + dp[i-1]) & 15746;

        System.out.println(dp[n]);
    }
}
