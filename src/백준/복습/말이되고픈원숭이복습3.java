package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이복습3 {
    static class Node{
        int x,  y;
        int cnt;
        int k;

        public Node(int x, int y, int cnt, int k){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
    static int k,h,w;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] horseX = {-2,-1,1,2,2,1,-1,-2};
    static int[] horseY = {1,2,2,1,-1,-2,-2,-1};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        visited = new boolean[h][w][k+1];

        for(int i=0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0,k));
        visited[x][y][k] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == h-1 && node.y == w-1) {
                min = Math.min(min, node.cnt);
                return;
            }

            if(node.k > 0){
                for(int i=0; i<8; i++){
                    int nx = node.x + horseX[i];
                    int ny = node.y + horseY[i];

                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                    if(!visited[nx][ny][node.k-1] && map[nx][ny] == 0){
                        visited[nx][ny][node.k-1] = true;
                        q.offer(new Node(nx, ny, node.cnt+1, node.k-1));
                    }
                }
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if(!visited[nx][ny][node.k] && map[nx][ny] == 0){
                    visited[nx][ny][node.k] = true;
                    q.offer(new Node(nx, ny, node.cnt +1, node.k));
                }
            }
        }
    }
}
