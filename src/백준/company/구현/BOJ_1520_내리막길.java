package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {
    static int N, M;
    static int[][] map, dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로
        map = new int[M][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N]; // dp[i][j] : 해당 경로에서 도착점까지 갈 수 있는 경로의 수 저장
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                dp[i][j] = -1;
            }
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    static int dfs(int x, int y){
        if(x == M-1 && y == N-1){
            return 1; // 도착점 올 수 있다면 1추가
        }

        if(dp[x][y] != -1){ // 이미 저장된 값이 있다면 그 값을 리턴
            return dp[x][y];
        }

        dp[x][y] = 0;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] < map[x][y]){ // 현재보다 낮은 경사라면
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= M || y >= N) return false;
        else return true;
    }
}
