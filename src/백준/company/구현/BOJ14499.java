package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {
    // 주사위 굴리기 / 골드 4 / 구현, 시뮬레이션
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0,0,-1,1}; // 동서북남
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 지도 세로
        m = Integer.parseInt(st.nextToken()); // 지도 가로
        x = Integer.parseInt(st.nextToken()); // 좌표
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 명령의 개수

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 이동 명령
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            int nx = x + dx[dir-1];
            int ny = y + dy[dir-1];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                changeDice(dir);

                if(map[nx][ny] == 0){ // 이동한 칸이 0이라면
                    map[nx][ny] = dice[6]; // 해당 칸의 수를 주사위에 적힌 숫자로 변경
                }else{ // 이동한 칸이 0이 아니면
                    dice[6] = map[nx][ny]; // 주사위에 적힌 숫자를 해당 숫자로 변경

                    map[nx][ny] = 0; // 해당 칸은 0으로 변경
                }

                x = nx;
                y = ny;
                System.out.println(dice[1]); // 윗면에 적힌 숫자 출력
            }
        }
    }

    static void changeDice(int d){
        int[] temp = dice.clone();
        // 6 밑면, 1 윗면
        // 동 1, 서 2, 북 3, 남 4
        if(d == 1){ // 동으로 굴림
            dice[1] = temp[4];
            dice[4] = temp[6];
            dice[6] = temp[3];
            dice[3] = temp[1];
        }else if(d == 2){ // 서로 굴림
            dice[1] = temp[3];
            dice[4] = temp[1];
            dice[3] = temp[6];
            dice[6] = temp[4];
        }else if(d == 3){ // 북으로 굴림
            dice[1] = temp[5];
            dice[5] = temp[6];
            dice[2] = temp[1];
            dice[6] = temp[2];
        }else if(d == 4){ // 남으로 굴림
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }
    }
}
