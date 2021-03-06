package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말원숭이 {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] horseX = {-2,-1,1,2,2,1,-1,-2};
    static int[] horseY = {1,2,2,1,-1,-2,-2,-1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 정수 개수
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken()); // 가로
        H = Integer.parseInt(st.nextToken()); // 세로
        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = bfs(0,0);

        if(min == Integer.MAX_VALUE) System.out.println();
        else System.out.println(min);
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,0,K));
        visited[0][0][K] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == H-1 && node.y == W-1){
                return node.cnt;
            }

            // 말 모드로 이동
            if(node.k > 0){ // 말 이동횟수가 남아있다면
                for(int i=0; i<9; i++){
                    int nx = node.x + horseX[i];
                    int ny = node.y + horseY[i];

                    if(nx < 0 || ny < 0 || ny >= H || ny >= W) continue;

                    if(!visited[nx][ny][node.k-1] && map[nx][ny] == 0){
                        visited[nx][ny][node.k-1] = true;
                        q.offer(new Node(nx, ny, node.cnt+1, node.k-1));
                    }
                }
            }

            // 원숭이 모드로 이동
            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if(!visited[nx][ny][node.k] && map[nx][ny] == 0){
                    visited[nx][ny][node.k] = true;
                    q.offer(new Node(nx, ny, node.cnt+1, node.k));
                }
            }
        }
        return min;
    }

    static class Node{
        int x, y;
        int cnt;
        int k;
        public Node(int x, int y, int cnt, int k){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
}
