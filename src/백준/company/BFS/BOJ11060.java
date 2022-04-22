package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11060 {
    // 점프 점프 / 실버2 / BFS, DP?
    /*
        BFS와 DP사용. 현 위치에서 가장 멀리뛴다고해서 최적해가 되지 않는다. 따라서 현 위치에서 뛸 수 있는 위치들을 모두
        큐에 넣어줘서 최소값을 구한다.
     */
    static int n;
    static int[] arr;
    static int[] dp; // dp[i] = i번째 칸에 도달할 수 있는 최소 점프 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        if(dp[n-1] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[n-1]);
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        dp[0] = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int i = 1; i <= arr[now]; i++) {
                if(now + i >= n) break; // 범위를 벗어나면 멈춘다.
                if(dp[now+i] > dp[now] + 1) {
                    dp[now + i] = dp[now] +1;
                    q.offer(now+i);
                }
            }
        }
    }
}


