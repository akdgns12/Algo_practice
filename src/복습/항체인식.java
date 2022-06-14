package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 항체인식 {
    static int n, m;
    static int[][] beforeMap, afterMap;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        beforeMap = new int[n][m];
        afterMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                beforeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                afterMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beforeMap[i][j] != afterMap[i][j]) {
                    bfs(i, j, afterMap[i][j]);
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        if (check()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beforeMap[i][j] != afterMap[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void bfs(int x, int y, int after) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited = new boolean[n][m];
        visited[x][y] = true;

        int before = beforeMap[x][y];

        beforeMap[x][y] = after;

        while (!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if (beforeMap[nx][ny] == before) {
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    beforeMap[nx][ny] = after;
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
