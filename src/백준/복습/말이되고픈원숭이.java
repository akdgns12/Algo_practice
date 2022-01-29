package 백준.복습;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 말이되고픈원숭이 {
    static int k, w, h;
    static int[][] board;
    static int min = Integer.MAX_VALUE;
    static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2}; //말이 이동할 수 있는 8방향
    static int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] dx = {0, 1, 0 ,-1}; // 원숭이가 이동할 수 있는 4방향
    static int[] dy = {1, 0, -1, 0};
    /*
        visited 배열을 단순히 2차원으로 선언하면 말과 원숭이의 서로다른 이동경로를
        구분할 수 없기 때문에 3차원 배열로 선언하여 체크해주어야 한다.
        즉, 서로 다른 경로로 이동했을 때 방문처리르 따로 해주기 위해
        3차원 배열을 사용(visited[x][y][k번 이동한 횟수])
     */
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        k = scan.nextInt();
        w = scan.nextInt();
        h = scan.nextInt();

        board = new int[h][w];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                board[i][j] = scan.nextInt();
            }
        }

        visited = new boolean[h][w][k + 1];
        min = bfs(0, 0);

        if(min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }

    public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0, k));
        visited[x][y][k] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.x == h - 1 && current.y == w - 1) return current.count;

            if(current.k > 0) {
                for(int i = 0; i < 8; i++) {
                    int nx = current.x + hdx[i];
                    int ny = current.y + hdy[i];
                    if(nx >= 0 && ny >= 0 && nx < h && ny < w && !visited[nx][ny][current.k - 1] && board[nx][ny] == 0) {
                        visited[nx][ny][current.k - 1] = true;
                        q.offer(new Node(nx, ny, current.count + 1, current.k - 1));
                    }
                }
            }
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < h && ny < w && !visited[nx][ny][current.k] && board[nx][ny] == 0) {
                    visited[nx][ny][current.k] = true;
                    q.offer(new Node(nx, ny, current.count + 1, current.k));
                }
            }
        }
        return min;
    }

    public static class Node {
        int x;
        int y;
        int count;
        int k;

        public Node(int x, int y, int count, int k) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.k = k;
        }
    }

}
