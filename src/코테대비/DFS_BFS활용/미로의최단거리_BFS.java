package 코테대비.DFS_BFS활용;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
    public int x, y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class 미로의최단거리_BFS {
    static int[][] map, dist;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        map[x][y] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 &&
                map[nx][ny] == 0) {
                    map[nx][ny] = 1; // 방문 체크 1로 변경해서 체크
                    q.offer(new Node(nx, ny));
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        미로의최단거리_BFS T = new 미로의최단거리_BFS();
        Scanner sc = new Scanner(System.in);
        map = new int[8][8];
        dist = new int[8][8];
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        T.bfs(1,1);
        if(dist[7][7] == 0) System.out.println(-1);
        else System.out.println(dist[7][7]);
    }
}
