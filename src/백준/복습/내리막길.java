package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길 {
    static int N,M;
    static int[][] map,dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M]; // (x,y)에서 도착점으로 가는 경로의 수

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    static void dfs(int x, int y){
        if(x == N-1 && y == M-1){ // 종료조건
            dp[x][y] = 1;
            return;
        }

        dp[x][y] = 0 ; // 방문처리
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            if(map[x][y] > map[nx][ny]){
                if(dp[nx][ny] == -1){
                    dfs(nx, ny);
                }
                dp[x][y] += dp[nx][ny];
            }
        }
    }
}
