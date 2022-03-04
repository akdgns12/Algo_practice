package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기 {
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0,0,-1,1}; // 동서북남
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            int nx = x + dx[dir-1];
            int ny = y + dy[dir-1];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                changeDice(dir);

                if(map[nx][ny] == 0){ //이동한 칸에 적힌 수가 0이면
                    map[nx][ny] = dice[6];
                }else{
                    dice[6] = map[nx][ny];

                    map[nx][ny] = 0;
                }
                x = nx;
                y = ny;
                System.out.println(dice[1]);
            }

        }
    }

    static void changeDice(int d){
        int[] temp = dice.clone();

        if(d == 1){
            dice[1] = temp[4];
            dice[4] = temp[6];
            dice[3] = temp[1];
            dice[6] = temp[3];
        }else if(d == 2){
            dice[1] = temp[3];
            dice[4] = temp[1];
            dice[3] = temp[6];
            dice[6] = temp[4];
        }else if(d == 3){
            dice[1] = temp[5];
            dice[5] = temp[6];
            dice[6] = temp[2];
            dice[2] = temp[1];
        }else if(d == 4){
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }


    }
}
