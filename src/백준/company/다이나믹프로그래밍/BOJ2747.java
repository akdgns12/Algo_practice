package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ2747 {
    // 피보나치 수 구하기 / 브론즈 3 / 구현
    static int[] dp;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new int[46];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        // 피보나치 값 채우기
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);
    }
}
