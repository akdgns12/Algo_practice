package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16985 {
    // maze / 골드 3 / 시뮬 / 3차원
    // 탈출하는 가장 적은 이동 횟수
    /*
        1. 2가지를 기준으로 모든 경우의 수를 탐색
            1) 판의 회전(어느 판을 얼마만큼 회전할지)
            2) 회전을 완료한 5개의 판 쌓기
         2. 위의 기준으로 만든 3차원 행렬을 bfs거리 탐색
     */
    static final int n = 5;
    static int min = Integer.MAX_VALUE;
    static boolean[] visitFloor = new boolean[n]; // 조합을 위한 visitFloor
    static ArrayList<Integer> floorList = new ArrayList<>();
    static int[][][] map = new int[n][n][n];
    static int[] dx = {-1,1,0,0,0,0}; // 상하좌우위아래
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {0,0,0,0,1,-1};

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

        if(min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    // 회전 정하기
    // 각 판마다 몇 도 회전하는지 모든 경우의 수를 탐색
    // -현재 판을 90도 회전시키고 다음 판에 대해 재귀 호출
    // 5번째(마지막)판에 대한 결정이 끝나면 판을 쌓는 함수를 호출
    static void searchMinPath(int cnt) {
        if(cnt == 5){
            // 쌓는 순서 정하기
            stackFloor(0);
            return;
        }

        for (int i=0; i<=3; i++){
            // 해당 판을 90도 회전하고 다음 판에 대해 재귀 호출
            rotate(cnt);
            searchMinPath(cnt+1);
        }
    }

    // 쌓는 순서 정하기
    // 각 판을 몇 번째에 쌓을지 모든 경우의 수를 탐색
    // 5번째(마지막)판에 대한 결정이 끝나면 bfs(거리 탐색) 호출
    static void stackFloor(int idx) {
        if(idx == n){
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visitFloor[i]){
                visitFloor[i] = true;
                floorList.add(i);
                stackFloor(idx+1);
                floorList.remove(floorList.size() - 1);
                visitFloor[i] = false;
            }
        }
    }

    // 도착점까지의 거리 구하기
    // (0,0,0)좌표와 (n-1,n-1,n-1)좌표가 둘 다 1(사람이 갈 수 있는 값)인지 확인
    // (0,0,0)에서 시작하여(n-1, n-1, n-1)까지 몇 번 이동해야하는지 bfs 탐색
    static void bfs(){
        int[][][] updateMap = new int[n][n][n];
        // 쌓는 순서에 따라 쌓고 bfs
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[floorList.get(i)], 0, updateMap[i], 0, map[floorList.get(i)].length);
        }

        if(updateMap[0][0][0] == 0 || updateMap[4][4][4] == 0) return;

        boolean[][][] visited = new boolean[n][n][n];
        visited[0][0][0] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 0));

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == 4 && node.y == 4 && node.z == 4){
                if(min > node.dist){
                    min = node.dist;
                }
                continue;
            }

            for (int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.z + dz[i];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= n || nz >= n) continue; // 범위 벗어나면 skip
                if(visited[nx][ny][nz] || updateMap[nx][ny][nz] == 0) continue; // 방문했거나 갈 수 없는 곳 skip

                visited[nx][ny][nz] = true;
                q.offer(new Node(nx, ny, nz, node.dist + 1));
            }
        }
    }

    // 해당판을 90도 회전
    static void rotate(int idx){
        int[][] temp = new int[n][n];
        for (int y=0; y<n; y++){
            for (int x=0; x<n; x++){
                temp[y][x] = map[idx][n - x - 1][y];
            }
        }
        System.arraycopy(temp, 0, map[idx], 0, temp.length);
    }

    static class Node{
        int x, y , z;
        int dist;

        public Node(int x, int y, int z, int dist) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dist = dist;
        }
    }
}
