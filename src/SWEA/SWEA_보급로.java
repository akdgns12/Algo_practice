package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_보급로 {
    static int N;
    static int[][] map, dp;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dp = new int[N][N];
            visited = new boolean[N][N];

            for(int i=0; i<N; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for(int i=0; i<N; i++){
                String str = br.readLine();
                for(int j=0; j<N; j++){
                    map[i][j] = str.charAt(i) - '0';
                }
            }

            ans = Integer.MAX_VALUE;
            bfs(0,0);
            sb.append("#").append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        dp[x][y] = 0;
        visited[x][y] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if(now.x == N-1 && now.y == N-1){
                ans = Math.min(ans, dp[N-1][N-1]);
                return;
            }

            if(ans <= dp[now.x][now.y]) continue;

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(!visited[nx][ny] && dp[nx][ny] > dp[now.x][now.y] + map[nx][ny]){
                    visited[nx][ny] = true;
                    dp[nx][ny] = dp[now.x][now.y] + map[nx][ny];
                    q.offer(new Node(nx, ny));
                }
            }
        }

    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
