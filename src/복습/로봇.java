package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇 {
    static int n, m;
    static int sx, sy, sDir, ex, ey, eDir;
    static Node start, end;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,0,1,-1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n][5];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        sDir = Integer.parseInt(st.nextToken());
        start = new Node(sx, sy, sDir, 0);

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        eDir = Integer.parseInt(st.nextToken());
        end = new Node(ex, ey, eDir, 0);

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        visited[start.x][start.y][start.dir] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end.x && now.y == end.y && now.dir == end.dir) {
                System.out.println(now.cnt);
                return;
            }

            // 1,2,3 전진
            for (int i = 1; i <= 3; i++) {
                int nx = now.x + dx[now.dir] * i;
                int ny = now.y + dy[now.dir] * i;

                if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if(visited[nx][ny][now.dir]) continue;
                if(map[nx][ny] == 1) break;

                q.offer(new Node(nx, ny, now.dir, now.cnt + 1));
                visited[nx][ny][now.dir] = true;
            }

            // 회전
            for (int i = 1; i <= 4; i++) {
                if(i != now.dir && !visited[now.x][now.y][i]){
                    // 180도 회전해서 명령 2번 필요한 경우
                    if((now.dir == 1 && i == 2) || (now.dir == 2 && i == 1) || (now.dir == 3 && i == 4) || (now.dir == 4 && i == 3)){
                        q.offer(new Node(now.x, now.y, i, now.cnt + 2));
                        visited[now.x][now.y][i] = true;
                    }
                    else{
                        q.offer(new Node(now.x, now.y, i, now.cnt + 1));
                        visited[now.x][now.y][i] = true;
                    }
                }
            }
        }

    }

    static class Node{
        int x, y;
        int dir;
        int cnt;

        public Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
