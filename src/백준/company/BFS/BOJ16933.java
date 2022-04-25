package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16933 {
    // 벽 부수고 이동하기 3 / 골드1 / BFS
    // 벽부수고 이동하기2에서 이동하지 않고 같은 칸에 머물러 있는 경우 생기고, 이 경우도 방문한 칸의 개수가 하나 늘어나는 걸로 처리해야 함
    // 한 번 이동할때마다 낮과 밤이 바뀌고, 이동하지 않고 머무르는 경우에도 낮과 밤은 바뀐다. 벽은 낮에만 부술 수 있다.
    static int n, m, k;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][k+1];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0, true)); // 0 : 낮, 1 : 밤
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                boolean day = now.day; // 낮과 밤 판별 변수

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0) { // 이동할 수 있는 경우
                    // 방문한 곳이면 pass
                    if(visited[nx][ny][now.breakWall]) continue;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall, !day));
                }
                else{ // 벽을 만난 경우
                    // 벽을 부술 횟수가 k를 초과하거나 방문했던 곳이면 pass
                    if(now.breakWall + 1 > k || visited[nx][ny][now.breakWall + 1]) continue;
                    // 낮일 경우
                    if(day){
                        visited[nx][ny][now.breakWall + 1] = true;
                        q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall + 1, !day));
                    }
                    else{ // 밤일 경우
                        // 낮이 되기를 기다렸다가 부심
                        q.offer(new Node(now.x, now.y, now.cnt + 1, now.breakWall, !day));
                    }
                }

            }
        }
        System.out.println(-1);
    }

    static class Node{
        int x, y;
        int cnt, breakWall;
        boolean day;

        public Node(int x, int y, int cnt, int breakWall, boolean day) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.breakWall = breakWall;
            this.day = day;
        }
    }
}
