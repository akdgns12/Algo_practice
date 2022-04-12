package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0, 1, 0, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r,c,d);
        System.out.println(result);
    }

    static void dfs(int x, int y, int dir) {
        switch (map[x][y]){
            case 0 : // 빈칸일때
                map[x][y] = 2;
                result++;
            case 1 : // 벽일때
                return;
        }

        // 인접한 곳 탐색
        int nDir = dir;
        for (int i = 0; i < 4; i++) {
            nDir = (nDir + 3) % 4; // 왼쪽 방향 바라봄
            int nx = x + dx[nDir];
            int ny = y + dy[nDir];

            if(map[nx][ny] == 0){ // 빈공간이라면
                dfs(nx, ny, nDir);
                return;
            }
        }

        dfs(x - dx[nDir], y - dy[nDir], dir);
        return;
    }
}
