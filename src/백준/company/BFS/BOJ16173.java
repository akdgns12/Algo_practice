package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16173 {
    // 점프왕 쩰리 / 실버5 / BFS
    static int n;
    static int[][] map;
    static int[] dx = {0,1}; // 우, 하
    static int[] dy = {1,0};
    static boolean[][] visited;

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
        q.offer(new Node(0,0)); // 시작 정점
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int move = map[cur.x][cur.y];

            if(map[cur.x][cur.y] == -1){
                System.out.println("HaruHaru");
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nx = cur.x + dx[i] * move;
                int ny = cur.y + dy[i] * move;

                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

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



