package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1388 {
    // 바닥장식 / 실버4 / BFS
    /*
        -나 |를 만날때마다 탐색할 수 있도록 함.
        -를 만날경우 좌우로만, |를 만날경우 위아래로만 탐색하면 됨.
     */
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == '-' || map[i][j] == '|'){
                    if(!visited[i][j]){
                        cnt++;
                        bfs(i, j);
                    }
                }
            }
        }

        System.out.println(cnt);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        char check =  map[x][y];

        // -일 경우 좌우로만, |일경우 위아래로만 탐색하기 위해 idx 설정
        int idx = map[x][y] == '-' ? 2 : 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for (int i = idx; i < idx + 2; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;

                if(map[nx][ny] == check){
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
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
