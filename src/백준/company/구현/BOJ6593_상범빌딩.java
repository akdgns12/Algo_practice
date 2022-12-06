package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593_상범빌딩 {
    static int L, R, C;
    static char[][][] map;
    static int[] dx = {0, 0, -1, 1, 0, 0}; // 동서남북상하
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken()); // 층 수
            R = Integer.parseInt(st.nextToken()); // 행
            C = Integer.parseInt(st.nextToken()); // 열

            if(L == 0 && R == 0 && C == 0){
                System.out.println(sb);
                return;
            }

            map = new char[L][R][C];
            int sx = 0, sy = 0, sz = 0;

            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    String str = br.readLine();
                    for(int k=0; k<C; k++){
                        map[i][j][k] = str.charAt(k);
                        if(map[i][j][k] == 'S'){
                            sz = i;
                            sx = j;
                            sy = k;
                        }
                    }
                }
                br.readLine();
            }
            bfs(sx, sy, sz);
        }
    }

    static void bfs(int x, int y, int z){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, z, 0));
        boolean[][][] visited = new boolean[L][R][C];
        visited[z][x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(map[now.z][now.x][now.y] == 'E'){
                sb.append("Escaped in " + now.time + " minute(s).\n");
                return;
            }

            for(int i=0; i<6; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nz = now.z + dz[i];

                if(!isRange(nx, ny, nz) || visited[nz][nx][ny]) continue;

                if(map[nz][nx][ny] == '.' || map[nz][nx][ny] == 'E'){
                    q.offer(new Node(nx, ny, nz, now.time + 1));
                    visited[nz][nx][ny] = true;
                }
            }
        }

        sb.append("Trapped!\n");
    }

    static boolean isRange(int x, int y, int z){
        if(x < 0 || y < 0 || z < 0 || x >= R || y >= C || z >= L) return false;
        else return true;
    }

    static class Node{
        int x, y, z;
        int time;
        public Node(int x, int y, int z, int time){
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
