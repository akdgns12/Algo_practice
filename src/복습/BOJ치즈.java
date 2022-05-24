package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ치즈 {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) sum += 1;
            }
        }

        int time = 0;
        while(true){
            if(sum == 0) break;
            Queue<Node> q = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];
            int[][] check = new int[n][m];

            q.offer(new Node(0,0));
            visited[0][0] = true;

            while(!q.isEmpty()){
                Node node = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                    if(map[nx][ny] == 1){
                        check[nx][ny]++;
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(check[i][j] > 1){
                        map[i][j] = 0;
                        sum--;
                    }
                }
            }

            time++;
        }

        System.out.println(time);
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
