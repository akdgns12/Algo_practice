package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14923 {
    // 미로탈출 / 골드4 / BFS

    static int n, m;
    static int sx, sy, ex ,ey;
    static int[][] map;
    static boolean[][][] visited; // 벽 부순 여부도 같이 들고다녀야함, 0:벽 안부숨, 1:벽부숨
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
        // 시작 위치
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        // 탈출 위치
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽을 허물 수 있는 지팡이 사용은 1회만 가능
        // 가장 빠른 경로의 길이 구하라
        bfs();
        int ans = answer == Integer.MAX_VALUE ? - 1 : answer;
        System.out.println(ans);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        visited[sx][sy][0] = true;
        q.offer(new Node(sx, sy, 0, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if(nx == ex && ny == ey) { // 탈출구면
                    answer = Math.min(now.dist + 1, answer);
                    continue;
                }

                if(map[nx][ny] == 1 && now.isBreak == 0){ // 벽이고, 아직 벽을 부수지 않았다면
                    q.offer(new Node(nx, ny, now.dist + 1, 1));
                    visited[nx][ny][1] = true;
                } else if (map[nx][ny] == 0 && !visited[nx][ny][now.isBreak]) { // 그냥 길일때는 벽을 부순적이 있던, 없던 지나갈 수 있음
                    q.offer(new Node(nx, ny, now.dist + 1, now.isBreak));
                    visited[nx][ny][now.isBreak] = true;
                }
            }
        }
    }

    static class Node{
        int x, y;
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
