package 코테대비.DP;

import java.util.Scanner;

public class 최대점수구하기_냅색알고리즘 {
    public static void main(String[] args) {
        최대점수구하기_냅색알고리즘 T = new 최대점수구하기_냅색알고리즘();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dp = new int[m+1];
        for (int i = 0; i < n; i++) {
            int ps = sc.nextInt(); //문제 풀면 얻는 점수
            int pt = sc.nextInt(); //문제 푸는데 걸리는 시간

            for (int j=m; j>=pt; j--){
                dp[j] = Math.max(dp[j], dp[j - pt] + ps);
            }
        }
        System.out.println(dp[m]);
    }
}
