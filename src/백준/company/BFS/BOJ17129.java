package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17129 {
    // 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유 / 골드5 / BFS
    // 0 : 빈 복도, 1 : 장애물
    // 식구 셋인데 2, 청국장 3, 스시 4, 맥앤치즈 5
    // 시작점은 2의 위치, 갈 수 있는 가장 가까운 음식에 도달할때 최단거리
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int sx, sy;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs(sx, sy);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (map[now.x][now.y] == 3 || map[now.x][now.y] == 4
                    || map[now.x][now.y] == 5) {
                System.out.println("TAK");
                System.out.println(now.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if(map[nx][ny] != 1){
                    q.offer(new Node(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println("NIE");
        return;
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
