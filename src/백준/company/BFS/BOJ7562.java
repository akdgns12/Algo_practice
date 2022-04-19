package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
    // 나이트의 이동 / 실버1 / BFS
    // 각 테케마다 최소 몇번만에 이동할 수 있는지
    static int t;
    static int l;
    static int[][] map;
    static int[] dx = {-2,-2,-1,1,2,2,1,-1}; // 8가지 방향
    static int[] dy = {-1,1,2,2,1,-1,-2,-2};
    static int sx,sy,ex,ey;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            l = Integer.parseInt(br.readLine()); // 체스판의 한 변 길이
            map = new int[l][l];
            visited = new boolean[l][l];
            // 출발지
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            // 목적지
            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            bfs(sx, sy);
        }
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == ex && node.y == ey){
                System.out.println(node.dist);
            }

            for (int i = 0; i < 8; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= l || ny >= l || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny, node.dist + 1));
            }
        }
    }

    static class Node{
        int x, y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
