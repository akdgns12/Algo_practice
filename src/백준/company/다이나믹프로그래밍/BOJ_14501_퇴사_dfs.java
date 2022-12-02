package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사_dfs {
    static int N;
    static int[][] arr;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // i번째 상담이 끝나는 일
            arr[i][1] = Integer.parseInt(st.nextToken()); // cost
        }

        dfs(1, 0);
        System.out.println(ans);
    }

    static void dfs(int cnt, int sum){
        if(cnt > N + 1) return;
        if(cnt == N + 1){
            ans = Math.max(ans, sum);
            return;
        }

        dfs(cnt + 1, sum);
        dfs(cnt + arr[cnt][0], sum + arr[cnt][1]);
    }
}
