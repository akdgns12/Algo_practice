package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    // 조금 다른 치즈문제 / 구현 / 골드5
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int time, cnt, cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese += 1;
            }
        }

        // 치즈개수가 0이 될때까지 반복
        while(cheese != 0){
            time++; // 모두 녹아서 없어지는데 걸리는 시간
            cnt = cheese; // 모두 녹기 한시간 전에 남아있는 치즈조각이 놓여있는 칸의 개수
            bfs();
        }

        System.out.println(time);
        System.out.println(cnt);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        boolean[][] visited = new boolean[n][m];
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

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
