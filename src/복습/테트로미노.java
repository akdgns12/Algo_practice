package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i,j,0,0);
                exception(i,j);
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth+1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static void exception(int x, int y) {
        int wing = 4;
        int sum = map[x][y];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                wing--;
                continue;
            }

            if(wing <= 2) return;
            min = Math.min(min, map[nx][ny]);
            sum = sum + map[nx][ny];
        }

        if(wing == 4){
            sum = sum - min;
        }
        max = Math.max(max, sum);
    }
}
