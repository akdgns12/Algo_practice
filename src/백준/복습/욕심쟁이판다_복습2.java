package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다_복습2 {
    static int n;
    static int[][] map, dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        // dp[i][j]에는 해당 칸에서 출발하여 판다가 방문할 수 있는 최대 칸 수 저장
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 지점을 시작점으로 바꿔가며 최대 방문칸수 구하기
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                answer = Math.max(answer, dfs(i,j));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y){
        if(dp[x][y] != 0){ // dp에 저장된 값이 있다면 반환
            return dp[x][y];
        }

        dp[x][y] = 1; // 현재 위치를 포함해야 하므로 1로 초기화

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나면 skip
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

            // 다음 위치의 대나무 양이 현재 위치의 대나무 양보다 많으면
             if(map[nx][ny] > map[x][y]){
                 /*
                    dp 값은 현재칸의 값과
                    칸 수가 가장 많은 값 + 1(x,y 칸으로 이동) 중 큰 값을 선택한다.
                  */
                 dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
             }
        }
        return dp[x][y];
    }
}
