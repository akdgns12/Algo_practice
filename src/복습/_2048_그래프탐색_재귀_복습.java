package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2048_그래프탐색_재귀_복습 {
    static int N;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, map);
        System.out.println(result);
    }

    static void dfs(int depth, int[][] map){
        if(depth == 5){

            int tempResult = 0;

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    tempResult = Math.max(tempResult, map[i][j]);
                }
            }

            result = Math.max(result, tempResult);
            return;
        }

        for(int i=0; i<4; i++){
            boolean[][] sumCheck = new boolean[N][N];

            int[][] tempMap = new int[N][N];
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    tempMap[j][k] = map[j][k];
                }
            }

            // 상 좌
            if(i == 0 || i == 2){
                for(int j=0; j<N; j++){
                    for(int k=0; k<N; k++){
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }

            if(i == 1 || i == 3){
                for(int j=0; j<N; j++){
                    for(int k=0; k<N; k++){
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }

            dfs(depth+1, tempMap);
        }
    }

    static void move(int[][] tempMap, boolean[][] sumCheck, int i, int j, int k){
        int x = j;
        int y = k;
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx < 0 || ny < 0 || nx >= N || ny >= N){
            return;
        }

        boolean end = false;
        // 이동시작
        while(!end){

            if(tempMap[nx][ny] == 0){ // 다음 위치가 0일경우
                tempMap[nx][ny] = tempMap[x][y];
                tempMap[x][y] = 0;
                x = nx;
                y = ny;
                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                    end = true;
                }
            }

            else if(tempMap[nx][ny] == tempMap[x][y]){ // 다음 위치의 값이 현재위치의 값과 같다면 합친다
                if(!sumCheck[nx][ny]){
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
                else
                    end = true;
            }
            else{
                end = true;
            }
        }
    }
}
