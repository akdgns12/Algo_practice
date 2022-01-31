package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다복습2 {
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());;
        map = new int[n][n];
        // (x,y)에서 이동할 수 있는 최대 칸의 수를 저장하는 dp배열
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                max = Math.max(max, dfs(i,j));
            }
        }

        System.out.println(max);
    }

    /*
        지금 있는 지역보다 더 많은 대나무가 있어야 이동한다
     */
    static int dfs(int x, int y){
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        dp[x][y] = 1; // 해당 칸도 포함시켜야 하므로 1로 초기화

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

            if(map[x][y] < map[nx][ny]){
                dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
            }
        }
        return dp[x][y];
    }
}
