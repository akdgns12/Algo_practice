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
        visited = new boolean[n][m][k+1]; // 0:낮, 1:밤

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
        q.offer(new Node(0, 0, 1, 0, true)); // true : 낮, false : 밤
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

                // 범위 넘어가면 pass
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 빈공간이고 방문한적 없는 곳인 경우
                if(map[nx][ny] == 0 && !visited[nx][ny][now.breakWall]){
                    visited[nx][ny][now.breakWall] = true;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall, !day));
                }

                // 벽이지만 부실 수 있는 경우
                if(map[nx][ny] == 1 && now.breakWall < k) {
                    if (day && !visited[nx][ny][now.breakWall + 1]) { // 낮이고 방문한적이 없다면
                        // 벽을 부순걸로 처리해준다(낮에만 벽을 부실 수 있음)
                        visited[nx][ny][now.breakWall+1]= true;
                        q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall + 1, !day));
                    } else if (!day && !visited[nx][ny][now.breakWall + 1]) { // 밤이고 방문한적이 없는 곳이라면
                        q.offer(new Node(now.x, now.y, now.cnt + 1, now.breakWall, !day)); // 이동하지 않고 같은 칸에 머물러있기.(낮에만 벽을 부실 수 있으므로 날이 바뀌기를 기다린다)
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
