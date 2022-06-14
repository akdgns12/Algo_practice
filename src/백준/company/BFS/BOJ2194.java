package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2194 {
    // 유닛 이동시키기 / 골드 5 / BFS
    // 유닛을 목적지까지 움직이기 위해 필요한 최소의 이동 횟수
    static int n, m;
    static int a, b, k;
    static int[][] map;
    static boolean[][] visited;
    static int ex, ey;
    static ArrayList<Node> list; // 장애물 좌표 담을 list
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자 크기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 유닛의 크기
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        // 장애물의 수
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        list = new ArrayList<>();

        for (int i = 0; i < k; i++) { // 장애물 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            list.add(new Node(x, y, 0));
            map[x][y] = 1; // 장애물 위치는 1로 마킹
        }

        // 시작점과 도착점 정보
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        bfs(sx, sy);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if(now.x == ex && now.y == ey){
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(canGo(nx ,ny) && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.cnt + 1));
                }
            }
        }

        System.out.println(-1);
        return;
    }

    static boolean canGo(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= n - a + 1 || ny >= m - b + 1) return false;
        for (int i = nx; i < nx + a; i++) {
            for (int j = ny; j < ny + b; j++) {
                if(map[i][j] == 1) return false;
            }
        }

        return true;
    }

    static class Node{
        int x, y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
