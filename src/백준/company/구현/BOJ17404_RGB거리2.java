package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404_RGB거리2 {
    static int[][] dp;
    static int[][] cost;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        cost = new int[N][3]; // N번째 집, 칠할 수 있는 색 수

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // 빨
            cost[i][1] = Integer.parseInt(st.nextToken()); // 초
            cost[i][2] = Integer.parseInt(st.nextToken()); // 파
        }

        // 모든 집을 칠하는 비용의 최솟값
        dp = new int[N][3];

        // DP의 첫번째 값(집)은 각 색상비용의 첫번째 값으로 초기화
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        // 3가지 색 중 최솟값으로
        System.out.println(Math.min(dfs(N-1, 0), Math.min(dfs(N-1, 1), dfs(N-1, 2))));
    }

    static int dfs(int N, int color) {
        // 만약 탐색하지 않은 배열이라면
        if(dp[N][color] == 0) {
            if(color == 0) {
                dp[N][0] = Math.min(dfs(N-1, 1), dfs(N-1, 2)) + cost[N][0];
            }
            else if(color == 1) {
                dp[N][1] = Math.min(dfs(N-1, 0), dfs(N-1, 2)) + cost[N][1];
            }
            else{
                dp[N][2] = Math.min(dfs(N-1, 0), dfs(N-1, 1)) + cost[N][2];
            }
        }

        return dp[N][color];
    }
}
