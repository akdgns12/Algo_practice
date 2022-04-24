package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {
    static int l, r, c;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,-1,1,0,0}; // 동서남북상하
    static int[] dy = {1,-1,0,0,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static int sx, sy, sz;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l == 0 && r == 0 && c == 0) break;

            map = new char[l][r][c];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = str.charAt(k);
                        if(map[i][j][k] == 'S'){
                            sx = j; sy = k; sz = i;
                            map[i][j][k] = '.';
                        }
                    }
                }
                br.readLine();
            }

            bfs(sx, sy, sz);
        }
    }

    static void bfs(int x, int y, int z) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, z, 0));
        visited = new boolean[l][r][c];
        visited[z][x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(map[now.z][now.x][now.y] == 'E'){
                System.out.println("Escaped in " + now.time + " minute(s).");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nz = now.z + dz[i];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= r || ny >= c || nz >= l || visited[nz][nx][ny]) continue;

                if(map[nz][nx][ny] == '.' || map[nz][nx][ny] == 'E'){
                    q.offer(new Node(nx, ny, nz, now.time + 1));
                    visited[nz][nx][ny] = true;
                }
            }
        }
        System.out.println("Trapped!");
    }

    static class Node{
        int x, y, z;
        int time;

        public Node(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
