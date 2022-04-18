package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    // 치즈 / 골드 4 / BFS
    // 공기와 맞닿은 치즈는 녹는데, 모두 녹아 없어지는데 걸리는 시간을 구하라
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int time, cnt, cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    cheese++;
                }
            }
        }

        // bfs탐색으로 0주위가 1인 부분들을 0으로 바꿔준다? -> 그럼 그안의 0은 어떻게?
        // 바깥인지 어떻게 판별하는가가 문제... 음
        // 가장자리에는 치즈가 놓여있지 않기 때문에 0,0에서 시작
        // 치즈 개수가 0이될때까지 반복
        while(cheese != 0){
            time++;
            cnt = cheese;
            bfs();
        }

        // 모두 녹을때까지 걸린 시간과 모두 녹기 한시간 전 치즈의 개수
        System.out.println(time);
        System.out.println(cnt);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0)); // 0,0에서 시작
        visited = new boolean[n][m];
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if(map[nx][ny] == 1){ // 옆칸이 치즈라면
                    cheese--;
                    map[nx][ny] = 0;
                }else if(map[nx][ny] == 0){
                    q.offer(new Node(nx,ny));
                }
                visited[nx][ny] = true;
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
