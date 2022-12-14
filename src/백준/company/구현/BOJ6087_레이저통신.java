package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6087_레이저통신 {
    static int W, H;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] visited;
    static int sx, sy, ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        boolean first = false;
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
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(solve());
    }

    static int solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 설치한 거울 수 오름차순
        pq.offer(new Node(sx, sy, -1, 0));
        visited[sx][sy] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == ex && now.y == ey){
                return visited[now.x][now.y];
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nDir = i;

                if(!isRange(nx, ny) || map[nx][ny] == '*') continue; // 범위 벗어나거나 벽이면 skip

                if(now.dir == nDir || now.dir == -1){ // 현재방향과 같거나 시작점이면 거울 설치 x
                    if(visited[nx][ny] < visited[now.x][now.y]) continue; // 현재까지 사용한 거울 수보다 더 크면 skip
                    pq.offer(new Node(nx, ny, nDir, now.cnt));
                    visited[nx][ny] = now.cnt;
                }else{ // 방향이 다르다면, 거울 설치해야함
                    if(visited[nx][ny] < visited[now.x][now.y] + 1) continue; // 현재거울수 + 설치할 거울(1)한 값이 다음 좌표에 저장된 거울 수보다 크다면 skip
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                    pq.offer(new Node(nx, ny, i, now.cnt + 1));
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
