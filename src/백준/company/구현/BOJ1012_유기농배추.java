package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추 {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<Node> q;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken()); // 세로
            K = Integer.parseInt(st.nextToken()); // 배추 개수

            map = new int[N][M];
            for(int i=0; i<K; i++){ // 배추 위치
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            ans = Integer.MAX_VALUE;
            int result = 0;
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        bfs(i,j);
                        result += 1;
                    }
                }
            }
            System.out.println(result);
        }
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
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
        if(x < 0 || y < 0 || x >= N || y >= M) return  false;
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
