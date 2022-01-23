package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 복습2_말이되고픈원숭이 {
    static int K;
    static int H,W;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] horseMoveX = {-2,-1,1,2,2,1,-1,-2};
    static int[] horseMoveY = {1,2,2,1,-1,-2,-2,-1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K];

        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = bfs(0,0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0,K));
        visited[x][y][K] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == H && node.y == W){
                return node.cnt;
            }

            if(node.k > 0){
                for(int i=0; i<8; i++){
                    int nx = node.x + horseMoveX[i];
                    int ny = node.y + horseMoveY[i];

                    if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if(!visited[nx][ny][node.k-1] && map[nx][ny] == 0){
                        visited[nx][ny][node.k-1] = true;
                        q.offer(new Node(nx, ny, node.cnt+1, node.k - 1));
                    }
                }
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(!visited[nx][ny][node.k] && map[nx][ny] == 0){
                    q.offer(new Node(nx, ny, node.cnt+1, node.k));
                    visited[nx][ny][node.k] = true;
                }
            }
        }

        return min;
    }

    static class Node{
        int x, y;
        int cnt, k;

        public Node(int x, int y, int cnt, int k){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
}
