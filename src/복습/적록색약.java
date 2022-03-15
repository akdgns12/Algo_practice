package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 적록색약 {
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 적록색약이 아닌 사람
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]){
                    bfs(i,j,map[i][j]);
                    answer[0]++;
                }
            }
        }

        // 적록색약인 사람을 구하기 위해 R을 G로 바꿔줌
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 'R') map[i][j] = 'G';
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]){
                    bfs(i, j, map[i][j]);
                    answer[1]++;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

    static void bfs(int x, int y, char color){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny] && map[nx][ny] == color){
                    visited[nx][ny] = true;
                    q.offer(new Node(nx,ny));
                }
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
