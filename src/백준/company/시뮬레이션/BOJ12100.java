package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    // 2048(Easy) / 시뮬레이션
    /*  최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값 찾기
        1.
     */
    static int[][] map;
    static int n;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, map); // (방향) 중복 상관없이 뽑아도됨
        System.out.println(max);
    }

    static void dfs(int depth, int[][] map){
        if(depth == 5){ // 5번 뽑으면
            // 가장 큰 값 찾는다
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }


            return;
        }

        for(int i=0; i<4; i++){
            boolean[][] sumCheck = new boolean[n][n];
            int[][] tempMap = new int[n][n];

            for(int j=0; j<n; j++)
                tempMap[j] = map[j].clone();

            if(i == 0 || i == 2) { // 상 좌로 움직일 때
                // 문제의 조건을 잘보면 이동하려고 하는 쪽의 칸이 먼저 합쳐진다.  상좌일 경우 0부터 시작해야함
                for(int j=0; j<n; j++){
                    for(int k=0; k<n; k++){
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }

            if(i == 1 || i == 3){
                // 문제의 조건을 잘보면 이동하려고 하는 쪽의 칸이 먼저 합쳐진다. 하우일 경우 map의 끝부터 시작해야함
                for(int j=n-1; j>=0; j--){
                    for(int k=n-1; k>=0; k--){
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }
            dfs(depth+1, tempMap);
        }
    }

    static void move(int[][] tempMap, boolean[][] sumCheck, int dir, int x, int y){
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= n || ny >= n) return;

        boolean end = false;

        while(!end){
            // 만약 이동할 곳이 빈칸이라면
            if(tempMap[nx][ny] == 0){
                tempMap[nx][ny] = tempMap[x][y];
                tempMap[x][y] = 0;
                x = nx;
                y = ny;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) end = true;
            }
            // 만약 이동할 곳이 빈칸이 아니고 현재의 값과 같을 때(합쳐질 때)
            else if(tempMap[nx][ny] == tempMap[x][y]){
                if(!sumCheck[nx][ny]){ // 합쳐진 적이 없다면
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
                end = true;
            }
            // 이동할 위치의 값이 현재의 값과 다를 때
            else{
                end = true;
            }
        }
    }
}
