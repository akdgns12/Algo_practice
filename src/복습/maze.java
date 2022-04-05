package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class maze {
    // 1. 각 판마다 몇도 회전하는지 모든 경우의 수 구하기
    // 2. 어떤 판부터 쌓는지 모든 경우의수 구하기
    static final int n = 5;
    static int[][][] map = new int[n][n][n];
    static boolean[] visitFloor = new boolean[n];
    static ArrayList<Integer> FloorList = new ArrayList<>();
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        searchMinPath(0);

        if(ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    // 각 판마다 회전하는 모든 경우의 수 구하기
    static void searchMinPath(int cnt){
        if(cnt == 5){
            // 쌓는 순서 정하기
            stackFloor(0);
            return;
        }

        for (int i = 0; i <= 3; i++) {
            rotate(cnt);
            searchMinPath(cnt+1);
        }
    }

    // 쌓는 순서 정하기
    // 각 판을 몇번째에 쌓을지 모든 경우의 수 구하기
    static void stackFloor(int idx) {
        if(idx == n){
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visitFloor[i]){
                visitFloor[i] = true;
                FloorList.add(i);
                stackFloor(idx + 1);
                FloorList.remove(FloorList.size() - 1);
                visitFloor[i] = false;
            }
        }
    }

    static void bfs(){
        int[][][] updateMap = new int[n][n][n];
        // 쌓는 순서에 따라 쌓고 bfs
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[FloorList.get(i)], 0, updateMap[i], 0, map[FloorList.get(i)].length);
        }

        if(updateMap[0][0][0] == 0 || updateMap[4][4][4] == 0) return;

        boolean[][][] visited = new boolean[n][n][n];
        visited[0][0][0] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,0,0));

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == 4 && node.y == 4 && node.z == 4){
                if(ans > node.dist){
                    ans = node.dist;
                }
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.z + dz[i];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= n || nz >= n) continue;
                if(visited[nx][ny][nz] || updateMap[nx][ny][nz] == 0) continue;

                visited[nx][ny][nz] = true;
                q.offer(new Node(nx, ny, nz, node.dist + 1));
            }
        }
    }

    static void rotate(int idx){
        int[][] temp = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                temp[y][x] = map[idx][n-1-x][y];
            }
        }
        System.arraycopy(temp, 0, map[idx], 0, temp.length);
    }

    static class Node{
        int x, y, z;
        int dist;
        public Node(int x, int y, int z, int dist){
            this.x = x;
            this.y = y;
            this.z = z;
            this.dist = dist;
        }
    }
}
