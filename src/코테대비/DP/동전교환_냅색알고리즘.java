package 코테대비.DP;

import java.util.Arrays;
import java.util.Scanner;

public class 동전교환_냅색알고리즘 { // 거슬러 줄 동전의 최소개수
    static int n, m;
    static int[] dp;

    static int solution(int[] coin){
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) { // i for문은 동전의 종류
            for (int j = coin[i]; j <= m; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        return dp[m];
    }

    public static void main(String[] args) {
        동전교환_냅색알고리즘 T = new 동전교환_냅색알고리즘();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        m = sc.nextInt();
        dp = new int[m+1];
        System.out.println(T.solution(arr));
    }
}
