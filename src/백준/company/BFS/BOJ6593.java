package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6593 {
    // 상범빌딩 / 골드 5 / BFS
    // 상범빌딩은 정육면체 금은 못지나가고 #, 비어있는 곳만 지나갈 수 있음 . , 시작점은 S, 탈출점은 E
    // 인접한 동서남북상하 1분의 시간으로 이동가능, 출구를 통해서 나가는데 걸리는 시간은?
    static int l, r, c;
    static char[][][] map;
    static int[] dx = {0,0,-1,1,0,0}; // 동서남북상하
    static int[] dy = {1,-1,0,0,0,0};
    static int[] dz = {0,0,0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l == 0 && r == 0 && c == 0) break;

            map = new char[l][r][c];
            int sx = 0, sy = 0, sz = 0;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = str.charAt(k);
                        if(map[i][j][k] == 'S'){
                            sz = i; sx = j; sy = k;
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
        boolean[][][] visited = new boolean[l][r][c];
        q.offer(new Node(x, y, z, 0));
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

                if(nx < 0 || ny < 0 || nz < 0 || nx >= r || ny >= c || nz >= l) continue;
                if(visited[nz][nx][ny]) continue;
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
