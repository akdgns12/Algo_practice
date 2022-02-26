package 코테대비.DFS_BFS활용;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 섬나라아일랜드_BFS {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int[][] map;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int answer = 0;
    static Queue<Node> q = new LinkedList<>();

    static void bfs(int x, int y, int[][] map){
        q.offer(new Node(x,y));
        while(!q.isEmpty()){
            Node cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n
                && map[nx][ny] == 1){
                    map[nx][ny] = 0;
                    q.offer(new Node(nx,ny));
                }
            }
        }
    }

    static void solution(int[][] map){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    answer++;
                    map[i][j] = 0;
                    bfs(i,j,map);
                }
            }
        }
    }

    public static void main(String[] args) {
        섬나라아일랜드_BFS T = new 섬나라아일랜드_BFS();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        T.solution(map);
        System.out.println(answer);
    }
}
