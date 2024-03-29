package 백준.company.구현;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ6087_레이저통신 {
    static int W, H;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        boolean first = false;
        int sx = 0, sy = 0, ex = 0, ey = 0;
        for(int i=0; i<H; i++){
            String str = br.readLine();
            for(int j=0; j<W; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C'){
                    if(!first){
                        sx = i;
                        sy = j;
                        first = true;
                    }else{
                        ex = i;
                        ey = j;
                    }
                }
            }
        }

        // C칸끼리 통신하기 위해 설치해야 하는 거울 개수의 최솟값
        // 레이저 통신 = 두칸을 연결할 수 있음을 의미
        // 방향이 바뀔떄마다 거울이 필요함!
        // 방향 바꾸는 최솟값
        visited = new int[H][W]; // 지금까지 거울설치 수 저장할 dp배열
        for(int i=0; i<H; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

        int ans = bfs(sx, sy, ex, ey);
        bw.write(String.valueOf(ans));
        bw.close();
    }

    static int bfs(int sx, int sy, int ex, int ey){
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 설치한 거울 수 오름차순
        pq.offer(new Node(sx, sy, -1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == ex && now.y == ey){
                return now.cnt;
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == '*') continue; // 범위 벗어나거나 벽이면 skip

                if(now.dir == i || now.dir == -1){ // 현재방향과 같거나 시작점이면 거울 설치 x
                    if(now.cnt <= visited[nx][ny]){
                        pq.offer(new Node(nx, ny, i, now.cnt));
                        visited[nx][ny] = now.cnt;
                    }
                }else{ // 방향이 다르다면, 거울 설치해야함
                    if(now.cnt + 1 <= visited[nx][ny]){
                        visited[nx][ny] = now.cnt + 1;
                        pq.offer(new Node(nx, ny, i, now.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= H || y >= W) return false;
        else return true;
    }

    static class Node implements Comparable<Node>{
        int x, y;
        int dir;
        int cnt;
        public Node(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o){
            return this.cnt - o.cnt;
        }
    }
}
