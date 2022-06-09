package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16174 {
    // 점프왕 젤리(Large) / 실버1 / BFS
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,1};
    static int[] dy = {1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            int move = map[now.x][now.y];

            if (map[now.x][now.y] == -1) {
                System.out.println("HaruHaru");
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nx = now.x + dx[i] * move;
                int ny = now.y + dy[i] * move;

                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] ) continue;

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
        System.out.println("Hing");
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
