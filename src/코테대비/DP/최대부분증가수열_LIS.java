package 코테대비.DP;

import java.util.Scanner;

public class 최대부분증가수열_LIS {
    static int n;
    static int[] dp;
    static int[] arr;

    static int solution(int[] arr){
        int answer = 0;
        dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i] && dp[j] > max) max = dp[j];
            }
            dp[i] = max + 1;
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        최대부분증가수열_LIS T = new 최대부분증가수열_LIS();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(T.solution(arr));
    }
}
