package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1103 {
    // 게임 / 골드 2 / DFS, DP
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max = Integer.MIN_VALUE;
    static boolean isCycle = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = str.charAt(j);

                if(num == 'H') map[i][j] = 10;
                else map[i][j] = num - '0';
            }
        }

        visited[0][0] = true;
        dfs(0, 0, 1); // x, y, count
        if(isCycle) System.out.println(-1);
        else System.out.println(max);
    }

    static void dfs(int x, int y, int count){
        if(count > max) max = count;

        dp[x][y] = count;
        for (int i=0; i<4; i++){
            int num = map[x][y];
            int nx = x + dx[i] * num;
            int ny = y + dy[i] * num;

            if(nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 10) continue;

            /*
                이미 방문한 곳을 재방문 한 경우는 싸이클이 생긴 경우
                싸이클이 생긴다면 무한루프에 빠지게 되기 때문에 처리를 해주어야 한다.
             */
            if(visited[nx][ny]){
                isCycle = true;
                return;
            }

            /*
                같은 좌표라도 더 많은 count로 기록을 남겨
                더 적은 count로 해당 좌표를 방문하면 가지치기를 하지 않도록 넘어간다
                이렇게 dp배열로 경우의 수를 줄여야 시간초과 없이 정답 처리
             */
            if(dp[nx][ny] > count) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, count+1);
            visited[nx][ny] = false;
        }
    }
}
