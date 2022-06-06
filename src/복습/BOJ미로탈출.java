package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ미로탈출 {
    static int n, m;
    static int sx, sy, ex, ey;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2];

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        bfs();
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0, 0));
        visited[sx][sy][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (nx == ex && ny == ey) {
                    answer = Math.min(answer, now.dist + 1);
                    continue;
                }

                if(map[nx][ny] == 1 && now.isBreak == 0){
                    visited[nx][ny][1] = true;
                    q.offer(new Node(nx, ny, now.dist + 1, 1));
                } else if (map[nx][ny] == 0 && !visited[nx][ny][now.isBreak]) {
                    visited[nx][ny][now.isBreak] = true;
                    q.offer(new Node(nx, ny, now.dist + 1, now.isBreak));
                }
            }
        }
    }

    static class Node{
        int x ,y;
        int dist;
        int isBreak;

        public Node(int x, int y, int dist, int isBreak) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.isBreak = isBreak;
        }
    }
}
