package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    // 토마토(이문제는 3차원) / 골드 5 / BFS
    static int m, n, h;
    static int[][][] map;
    static int[] dx = {0,0,0,0,1,-1}; // 위 아래 왼 오 앞 뒤
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {1,-1,0,0,0,0};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로
        h = Integer.parseInt(st.nextToken());

        map = new int[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1) q.offer(new Node(i, j, k));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.h + dz[i];

                if(nx >=0 && ny >= 0 && nz >= 0 && nx < n && ny < m && nz < h){
                    if(map[nz][nx][ny] == 0){
                        q.offer(new Node(nz, nx, ny));
                        map[nz][nx][ny] = map[node.h][node.x][node.y] + 1;
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=0; i<h; i++){
            for (int j = 0; j < n; j++) {
                for(int k=0; k<m; k++){
                    if(map[i][j][k] == 0) // 모두 익지 않았다면
                        return -1;

                    result = Math.max(result, map[i][j][k]);
                }
            }
        }

        if(result == 1) // 만약 모두 익어있었다면
            return 0;
        else
            return result - 1;
    }

    static class Node{
        int x, y, h;
        public Node(int h, int x, int y){
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }
}
