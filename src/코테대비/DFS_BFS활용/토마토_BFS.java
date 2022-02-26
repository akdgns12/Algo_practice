package 코테대비.DFS_BFS활용;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토_BFS {
    static class Node{
        public int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int m, n;
    static int[][] map, day;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> q = new LinkedList<>();

    public static void bfs(){
        while(!q.isEmpty()){
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m
                        && map[nx][ny] == 0){
                    map[nx][ny] = 1;
                    q.offer(new Node(nx, ny));
                    day[nx][ny] = day[cur.x][cur.y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        토마토_BFS T = new 토마토_BFS();
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        map = new int[n][m];
        day = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                //익은 토마토의 위치 큐에 넣기 0 - level
                if(map[i][j] == 1) q.offer(new Node(i,j));
            }
        }
        T.bfs();
        boolean flag = true;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0) flag = false; // 모두 익지 않을 때 flag = false;
            }
        }
        if(flag){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j  ++) {
                    answer = Math.max(answer, day[i][j]);
                }
            }
            System.out.println(answer);
        }
        else
            System.out.println(-1);
    }
}
