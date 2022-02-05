package 백준.복습;

import java.io.BufferedReader;
import java.util.Scanner;

public class 피보나치수구하기 {
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        dp = new int[46];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);
    }
}
