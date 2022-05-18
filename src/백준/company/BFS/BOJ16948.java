package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16948 {
    // 데스나이트 / 실버 1 / BFS
    /*
        체스의 나이트와 같은 움직임을가짐
        (x1, y1) -> (x2, y2)로 가는 최소 이동 횟수
     */

    static int n;
    static int x1,y1,x2,y2;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x1, y1, 0));
        visited[x1][y1] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == x2 && cur.y == y2) {
                System.out.println(cur.cnt);
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx > n || ny > n || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny, cur.cnt + 1));
            }
        }

        System.out.println(-1);
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
