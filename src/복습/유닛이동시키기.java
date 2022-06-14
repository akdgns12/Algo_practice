package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유닛이동시키기 {
    static int n, m, a, b, k;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];;
        visited = new boolean[n][m];

        for (int i = 0; i < k; i++) { // 장애물 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        bfs(sx, sy, ex, ey);
    }

    static void bfs(int sx, int sy, int ex, int ey) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.x == ex && now.y == ey) {
                System.out.println(now.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (canGo(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.dist + 1));
                }
            }
        }

        System.out.println(-1);
        return;
    }

    static boolean canGo(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx + a - 1 >= n || ny + b - 1 >= m) return false;
        for (int i = nx; i < nx + a; i++) {
            for (int j = ny; j < ny + b; j++) {
                if(map[i][j] == 1) return false;
            }
        }

        return true;
    }

    static class Node{
        int x, y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
