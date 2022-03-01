package 코테대비.DP;

import java.util.Scanner;

public class 계단오르기 {
    static int[] dp;
    static int n;

    public int solution(int n){
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        계단오르기 T = new 계단오르기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1];
        System.out.println(T.solution(n));
    }
}
