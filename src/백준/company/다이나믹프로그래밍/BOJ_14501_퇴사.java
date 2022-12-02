package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][2];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = i + Integer.parseInt(st.nextToken()) - 1; // i번째 상담이 끝나는 일
            arr[i][1] = Integer.parseInt(st.nextToken()); // cost
        }

        int[] dp = new int[N+1]; // dp[i] = i번째 끝나는 상담들 중 최대수익
        dp[0] = 0;
        for(int i=1; i<=N; i++){
            dp[i] = dp[i-1];
            for(int j=1; j<=i; j++){
                // i번째 날 끝나는 상담이 있다면,
                if(arr[j][0] == i) dp[i] = Math.max(dp[i], dp[j-1] + arr[j][1]);
            }
        }

        System.out.println(dp[N]);
    }
}
