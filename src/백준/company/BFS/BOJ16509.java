package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16509 {
    // 장군 / 골드5 / BFS
    /*
        상이 이동하는 경로에 다른 기물이 있다면 이동x
        범위 벗어나도 안됨
        상과 왕의 위치가 주어질 때, 상이 왕에게 도달할 수 있는 최소 이동횟수
     */
    static int[][] map = new int[10][9];
    static boolean[][] visited;
    static int dx[] = { 2,-2,3,-3,2,-2,3,-3 };
    static int dy[] = { 3,3,2,2,-3,-3,-2,-2 };
    static int r1,c1,r2,c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[10][9];
        // 상 위치
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        map[r1][c1] = 1;
        // 왕 위치
        st = new StringTokenizer(br.readLine());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        map[r2][c2] = 1;

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r1, c1, 0));
        visited[r1][c1] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.x == r2 && now.y == c2) {
                System.out.println(now.dist);
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx< 0 || ny < 0 || nx >= 10 || ny >= 9 || visited[nx][ny]) continue;
                // 이동경로상에 기물이 있는지 확인해야함
                if(check(nx, ny, i)){
                    q.offer(new Node(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println(-1);
        return;
    }

    // 이동경로 상에 기물이 있는지 체크
    static boolean check(int x, int y, int i) {
        boolean flag = true;

        if (i == 0 || i == 2) { // 우하 방향일때 2개의 케이스
            if(map[x-1][y-1] == 1){
                flag = false;
            } else if (map[x - 2][y - 2] == 1) {
                flag = false;
            }
        } else if (i == 1 || i == 3) { // 우상 방향일때 2개의 케이스
            if (map[x + 1][y - 1] == 1) {
                flag = false;
            } else if (map[x + 2][y - 1] == 1) {
                flag = false;
            }
        }
        else if (i == 4 || i == 6) { // 좌하 방향일때 2개의 케이스
            if (map[x - 1][y + 1] == 1) {
                flag = false;
            } else if (map[x - 2][y + 2] == 1) {
                flag = false;
            }
        } else if (i == 5 || i == 7) {
            if (map[x + 1][y + 1] == 1) {
                flag = false;
            } else if (map[x + 2][y + 2] == 1) {
                flag = false;
            }
        }

        return flag;
    }

    static class Node{
        int x, y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
