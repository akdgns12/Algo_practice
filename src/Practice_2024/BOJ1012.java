package Practice_2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int T, M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken()); // 세로
            K = Integer.parseInt(st.nextToken()); // 심어진 배추 개수

            map = new int[N][M];
            visited = new boolean[N][M];

            for(int i=0; i<K; i++){ // 배추의 위치(x,y)
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = 1; // 배추는 1
            }

            int answer = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        bfs(i,j);
                        answer++;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    static void bfs(int x, int y){
        Queue<Node> q =  new LinkedList<>();
        q.offer(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] != 1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
