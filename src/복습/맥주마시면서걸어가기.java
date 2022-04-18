package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 맥주마시면서걸어가기 {
    static int t,n;
    static int sx, sy, ex, ey;
    static boolean[] visited;
    static ArrayList<Node> store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
            }

            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            System.out.println(bfs(sx, sy) ? "happy\n" : "sad\n");
        }
    }

    static boolean bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy));
        visited = new boolean[n];

        while(!q.isEmpty()){
            Node now = q.poll();

            if (Math.abs(now.x - ex) + Math.abs(now.y - ey) <= 1000) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int nx = store.get(i).x;
                    int ny = store.get(i).y;
                    int dist = Math.abs(nx - now.x) + Math.abs(ny - now.y);
                    if (dist <= 1000) {
                        visited[i] = true;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
        return false;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
