package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086_아기상어2 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1,-1,-1,0,1,1,1,0}; // 좌상,상,우상,우,우하,하,좌하,좌
    static int[] dy = {-1,0,1,1,1,0,-1,-1};
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0){
                   int result = bfs(i,j);
                   ans = Math.max(result, ans);
                }
            }
        }

        System.out.println(ans);
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<8; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isRange(nx, ny) || visited[nx][ny]) continue;

                if(map[nx][ny] == 1){
                    return now.dist + 1;
                }

                q.offer(new Node(nx, ny, now.dist + 1));
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
        else return true;
    }

    static class Node{
        int x, y;
        int dist;
        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
