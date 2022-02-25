package 쩜튜브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2048Easy {
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
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

        dfs(0, map);
        System.out.println(max);
    }

    static void dfs(int depth, int[][] map) {
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
                boolean[][] sumCheck = new boolean[n][n];

                int[][] tempMap = new int[n][n];
                for(int j=0; j<n; j++)
                    tempMap[j] = map[j].clone();

                //상,좌로 각 칸의 값들을 움직일 경우
                if(i == 0 || i == 2){
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            move(tempMap, sumCheck, i, j, k);
                        }
                    }
                }

                //하,우로 각 칸의 값들을 움직일 경우
                if(i == 1 || i == 3){
                    for (int j = n - 1; j >= 0; j--) {
                        for (int k = n - 1; k >= 0; k--) {
                            move(tempMap, sumCheck, i, j, k);
                        }
                    }
                }
                dfs(depth+1, tempMap);
            }
        }
    }

    static void move(int[][] tempMap, boolean[][] sumCheck, int dir, int x, int y){
        //
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >=n || ny >= n) return;

        boolean end = false;

        while(!end){
            //다음 좌표가 빈칸이라면
            if(tempMap[nx][ny] == 0){
                tempMap[nx][ny] = tempMap[x][y];
                tempMap[x][y] = 0;

                x = nx;
                y = ny;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) end = true;
            }
            //다음 좌표의 값이 현재값과 같다면
            else if(tempMap[nx][ny] == tempMap[x][y]){
                if(!sumCheck[nx][ny]){
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
            }
            else{
                end = true;
            }
        }
    }
}
