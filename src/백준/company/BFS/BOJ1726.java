package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {
    // 로봇 / 골드3 / BFS
    // 로봇의 현재 위치와 바라보는 방향이 주어질 때, 원하는 위치로 이동시키고, 원하는 방향으로 바라보도록 하는데 최소 몇 번의 명령이 필요한지 리턴
    static int m, n;
    static int[][] map;
    static boolean[][][] visited; // 위치와 방향까지 체크해야함, 특정 위치로 올 수 있는 방법은 4가지
    static int sx, sy, sDir, ex, ey, eDir;
    static Node start, end;
    static int[] dx = {0,0,0,1,-1}; // 동서남북
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

    /*
        1. 현재 향하고 있는 방향으로 k칸 만큼 이동 (1 ~ 3)
        2.
     */
    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        visited[sx][sy][sDir] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == ex && now.y == ey && now.dir == eDir){
                System.out.println(now.cnt);
                return;
            }

            // 현재 바라보는 방향에서 1, 2, 3칸 이동
            for (int i = 1; i <= 3; i++) {
                int nx = now.x + dx[now.dir] * i; // 현재 바라보는 방향으로 i칸만큼 이동
                int ny = now.y + dy[now.dir] * i;

                if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue; // 범위 벗어나면 pass
                if(visited[nx][ny][now.dir]) continue;
                if(map[nx][ny] == 0){ // 움직일 수 있는 궤도이면
                    q.offer(new Node(nx, ny, now.dir, now.cnt + 1));
                    visited[nx][ny][now.dir] = true;
                }else if(map[nx][ny] == 1){
                    break; // 벽을 만나면 더 건너뛸 수 없음
                }
            }

            // 방향만 움직이기(방향만 움직여도 cnt증가, 문제의 조건에 따르면 명령 횟수를 세는것이기 때문)
            for (int i = 1; i <= 4; i++) {
                // 방문하지 않았고 기존 방향이 아니라면
                if (!visited[now.x][now.y][i] || i != now.dir) {
                    // 180도 회전할 경우에는 cnt가 2증가하기 때문에
                    // 180도 회전할 경우는 동,서 와 남,북 이므로 둘을 더했을 땐 고유하게 3과 7의 값이 나오게 됨
                    if(i + now.dir == 3 || i + now.dir == 7){
                        q.offer(new Node(now.x, now.y, i, now.cnt + 2));
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
