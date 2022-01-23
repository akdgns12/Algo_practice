package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {
    /**
     * 판다가 최대한 많은 칸을 이동하려면 이동할 수 있는 칸의 수의 최댓값
     * 상하좌우 이동, 판다는 이동할 자리가 원래있던 자리보다 대나무가 많아야함
     */
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (x,y)에서 이동할 수 있는 최댓값
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                answer = Math.max(answer, dfs(i,j));
            }
        }
    }

    static int dfs(int x, int y){
        // 이미 갱신된 지역은 바로 그값을 리턴
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        // 1로 초기화
        dp[x][y] = 1;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            // 다음 지역은 이전 지역보다 커야한다
            if(map[nx][ny] > map[x][y]){
                // 기존의 값과 새로 탐색한 값 중에 최댓값으로 갱신
                dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
            }
        }

        return dp[x][y];
    }
}
