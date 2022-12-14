package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589_보물섬 {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int sx, sy, ex, ey; // 보물 위치 두곳 좌표
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
            }
        }

        // 보물위치는 서로 간에 최단거리로 이동하는데 가장 긴 시간이 걸리는 육지 두 곳
        // 1. 보물 위치 찾기
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 'L'){
                    int result = bfs(i,j);
                    ans = Math.max(ans, result);
                }
            }
        }

        System.out.println(ans);
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        int time = 0;
        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 'W') continue;

                if(map[nx][ny] == 'L'){ // 육지일때만 이동가능
                    q.offer(new Node(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                    time = Math.max(time, now.dist + 1);
                }
            }
        }

        return time;
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
