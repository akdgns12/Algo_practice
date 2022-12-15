package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963_섬의개수 {
    static int w,h;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,-1,-1,0,1,1,1,0}; // 좌상,상,우상,우,우하,하,좌하,좌
    static int[] dy = {-1,0,1,1,1,0,-1,-1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0){
                break;
            }

            map = new int[w][h];
            for(int i=0; i<w; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<h; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            visited = new boolean[w][h];
            for(int i=0; i<w; i++){
                for(int j=0; j<h; j++){
                    if(!visited[i][j] && map[i][j] == 1){
                        visited[i][j] = true;
                        ans += 1;
                        bfs(i,j);
                    }
                }
            }
            System.out.println(ans);
        }
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<8; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isRange(nx, ny) || visited[nx][ny]) continue;

                if(map[nx][ny] == 1){
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= w || y >= h) return false;
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
