package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17391 {
    // 무한부스터 / 실버1 / BFS
    static int n, m;
    static int[][] map, cnt;
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cnt = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt[i][j] = Integer.MAX_VALUE;
            }
        }

        q.offer(new Node(0, 0));
        cnt[0][0] = 0;
        bfs();

        System.out.println(cnt[n-1][m-1]);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));

        while (!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 2; i++) {
                int nx = now.x;
                int ny = now.y;
                for (int j = 0; j < map[now.x][now.y]; j++) {
                    nx = nx + dx[i];
                    ny = ny + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (cnt[nx][ny] > cnt[now.x][now.y] + 1) {
                        cnt[nx][ny] = cnt[now.x][now.y] + 1;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
