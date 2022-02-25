package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    // 2048(Easy) / 골드 2 / 구현 및 백트래킹
    // 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0}; //상하좌우
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

        dfs(0,map);
        System.out.println(max);
    }

    static void dfs(int depth, int[][] map){
        if(depth == 5){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }
        else{

            for (int i = 0; i < 4; i++) {
                //합쳐진 여부 체크할 배열
                boolean[][] sumCheck = new boolean[n][n];

                int[][] tempMap = new int[n][n];
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        tempMap[j][k] = map[j][k];
                    }
                }
                //상 좌
                if(i == 0 || i == 2){
                    // 보드판의 좌측부터 시작
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            // 정해진 방향으로 보드판의 값들 움직이기
                            move(tempMap, sumCheck, i, j, k);
                        }
                    }
                }

                //하 우
                if(i == 1 || i == 3){
                    //보드판의 우측 하단부터 시작
                    for (int j=n-1; j>=0; j--){
                        for (int k = n - 1; k >= 0; k--) {
                            move(tempMap, sumCheck, i, j, k);
                        }
                    }
                }
                //한번의 이동이 끝나고 재귀호출
                dfs(depth + 1, tempMap);
            }
        }
    }

    static void move(int[][] tempMap, boolean[][] sumCheck, int i, int j, int k){
        int x = j;
        int y = k;

        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx < 0 || ny < 0 || nx >= n || ny >= n) return;

        boolean end = false;

        while (!end) {
            //이동할 빈칸이 0이면
            if(tempMap[nx][ny] == 0){
                tempMap[nx][ny] = tempMap[x][y];
                tempMap[x][y] = 0;
                x = nx;
                y = ny;

                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) end = true;
            }
            //이동할 위치의 값이 현재의 값과 같을 때(합쳐질 떄)
            else if(tempMap[nx][ny] == tempMap[x][y]){
                if(!sumCheck[nx][ny]){ //합쳐진 적이 없다면
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
                end = true;
            }
            //이동할 위치의 값이 현재의 값과 다를 때
            else{
                end = true;
            }
        }
    }
}
