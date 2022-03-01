package 코테대비.DP;

import java.util.Scanner;

public class 돌다리건너기 {
    static int[] dp;
    static int n;

    static int solution(int n){
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n+1; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        return dp[n+1]; // 개울을 건너는 방법은 n개의 돌다리를 모두 건넌 후!! dp[n+1]이다
    }

    public static void main(String[] args) {
        돌다리건너기 T = new 돌다리건너기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+2];
        System.out.println(T.solution(n));
    }
}
