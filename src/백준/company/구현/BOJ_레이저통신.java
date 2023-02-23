package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_레이저통신 {
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int W, H;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        boolean isFirst = false;
        int sx = 0, sy = 0, ex = 0, ey = 0;
        for(int i=0; i<H; i++){
            String str = br.readLine();
            for(int j=0; j<W; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C'){
                    if(!isFirst){
                        sx = i;
                        sy = j;
                        isFirst = true;
                    }else{
                        ex = i;
                        ey = j;
                    }
                }
            }
        }

        bfs(sx, sy, ex, ey);
        System.out.println(ans);
    }

    static void bfs(int sx, int sy, int ex, int ey){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(sx, sy, 0, -1));
        int[][] visited = new int[H][W];
        for(int i=0; i<H; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[sx][sy] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == ex  && now.y == ey){
                ans = Math.min(ans, now.cnt);
                return;
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isOutOfRange(nx, ny) || map[nx][ny] == '*') continue;

                if(now.dir != i || now.dir != -1){
                    now.cnt += 1;
                }


            }
        }
    }

    static boolean isOutOfRange(int x, int y){
        if(x < 0 || y < 0 || x >= H || y >= W) return false;
        else return true;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cnt;
        int dir;

        public Node(int x, int y, int cnt, int dir){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o){
            return this.cnt - o.cnt;
        }
    }
}