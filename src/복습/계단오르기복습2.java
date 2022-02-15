package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기복습2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[301];
        int[] step = new int[301];

        for(int i=1; i<=n; i++)
            step[i] = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = step[1];
        dp[2] = step[1] + step[2];

        for(int i=3; i<=n; i++)
            dp[i] = Math.max(dp[i-2] + step[i], dp[i-3] + step[i-1] + step[i]);

        System.out.println(dp[n]);
    }
}
