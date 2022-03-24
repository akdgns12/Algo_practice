package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2048 {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n;
    static int answer = Integer.MIN_VALUE;

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

        dfs(0, map);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] map){
        if(depth == 5){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
            return;
        }

        for(int i=0; i<4; i++){
            boolean[][] sumCheck = new boolean[n][n];
            int[][] tempMap = new int[n][n];

            for(int j=0; j<n; j++)
                tempMap[j] = map[j].clone();

            if(i == 0 || i == 2){
                for(int j=0; j<n; j++){
                    for(int k=0; k<n; k++){
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }

            if(i == 1 || i == 3){
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
            // 다음 좌표가 빈칸일 때
            if(tempMap[nx][ny] == 0){
                tempMap[nx][ny] = tempMap[x][y];
                tempMap[x][y] = 0;
                x = nx;
                y = ny;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if(nx < 0 || ny < 0 || nx >= n || ny >=n) end = true;
            }
            // 빈칸이 아니고, 같은 값일 때 (합쳐질 때)
            else if(tempMap[nx][ny] == tempMap[x][y]){
                if(!sumCheck[nx][ny]){
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
                end = true;
            }
            // 다른 값일 때
            else{
                end = true;
            }

        }
    }

}
