package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2579 {
    // 계단오르기 / 실버 3 / DP
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];
        int[] step = new int[n+2]; // 각 계단 점수

        for(int i=1; i<=n; i++){
            step[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = step[1];
        dp[2] = Math.max(step[1] + step[2], step[2]); // 무조건 첫번째 계단을 밟는게 좋다

        for(int i=3; i<=n; i++)
            dp[i] = Math.max(dp[i-2] + step[i], dp[i-3] + step[i-1] + step[i]);

        System.out.println(dp[n]);
    }
}
