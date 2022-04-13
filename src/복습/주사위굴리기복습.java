package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기복습 {
    static int n, m;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0, 0, -1, 1}; // 동서북남
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                changeDir(dir);

                if(map[nx][ny] == 0){ // 이동한 칸이 0이라면
                    map[nx][ny] = dice[6]; // 해당 칸의 숫자를 주사위 밑면의 수로 변경
                }else{ // 이동한 칸이 0이 아니라면
                    dice[6] = map[nx][ny];

                    map[nx][ny] = 0;
                }
            }

            x = nx;
            y = ny;
            System.out.println(dice[1]); // 주사위 상단에 적힌 수 출력
        }
    }

    static void changeDir(int dir) {
        int[] temp = dice.clone();
        if(dir == 0){ // 동으로 굴림
            dice[1] = temp[4];
            dice[3] = temp[1];
            dice[4] = temp[6];
            dice[6] = temp[3];
        }else if(dir == 1){ // 서로 굴림
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[4] = temp[1];
            dice[6] = temp[4];
        }else if(dir == 2){ // 북으로 굴림
            dice[1] = temp[5];
            dice[2] = temp[1];
            dice[5] = temp[6];
            dice[6] = temp[2];
        }else if(dir == 3){ // 남으로 굴림
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }
    }
}
