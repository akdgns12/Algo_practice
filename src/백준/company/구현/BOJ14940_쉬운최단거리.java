package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940_쉬운최단거리 {
    static int n, m, ex, ey;
    static int[][] map, answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    ex = i;
                    ey = j;
                }
            }
        }

        visited = new int[n][m];
        bfs(ex,ey);

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                if(map[i][j] == 2 || map[i][j] == 0){
                    System.out.print(0 + " ");
                    continue;
                }

                if(map[i][j] == 1 && visited[i][j] == 0){
                    System.out.print(-1 + " ");
                    continue;
                }

                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == 0) continue;
                if(visited[nx][ny] != 0) continue;

                if(map[nx][ny] == 1){
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                }
            }
        }
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= m) return false;
        else return true;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
