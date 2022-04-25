package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442 {
    // 벽 부수고 이동하기 2 / 골드 3 / BFS
    // 벽 하나만 부수는게 아닌, K개까지 부술 수 있다
    static int n, m, k;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][][] visited;

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

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0));
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

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0) { // 다음 좌표가 빈 곳이면
                    // 방문했던 곳이면 pass
                    if(visited[nx][ny][now.breakWall]) continue;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall));
                    visited[nx][ny][now.breakWall] = true;
                }
                else{ // 다음 좌표가 벽이면
                    // 방문했거나, 벽을 뚫은 횟수가 k를 넘으면 pass
                    if(now.breakWall + 1 > k  || visited[nx][ny][now.breakWall+1]) continue;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWall + 1));
                    visited[nx][ny][now.breakWall + 1] = true;
                }
            }
        }
        System.out.println(-1);
    }

    static class Node{
        int x, y;
        int cnt;
        int breakWall;

        public Node(int x, int y, int cnt, int breakWall) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.breakWall = breakWall;
        }
    }
}
