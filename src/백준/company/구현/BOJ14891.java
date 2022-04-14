package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
    // 톱니바퀴 / 골드5 / 구현
    // 생각생각
    /*
        8개의 톱니, 톱니를 k번 회전시키려 한다. 톱니바퀴의 회전은 한 칸을 기준으로, 회전은 시계, 반시계 방향,
        4개의 톱니바퀴중 회전시킬 톱니와 회전방향 결정.
        맞닿은 톱니의 극이 다르다면, 반대방향으로 회전, 맞닿은 극이 같다면 회전 x

     */
    static int k;
    static int[][] gear = new int[5][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j) - '0'; // 0 : N, 1 : S극
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()); // 1 : 시계, -1 : 반시계

            solution(num, dir);
        }

        int cnt = 0;
        if(gear[0][0] == 1){
            cnt += 1;
        }
        if (gear[1][0] == 1) {
            cnt += 2;
        }
        if (gear[2][0] == 1) {
            cnt += 4;
        }
        if (gear[3][0] == 1) {
            cnt += 8;
        }

        System.out.println(cnt);
    }

    // 해당 톱니바퀴의 9시 방향은 6, 3시 방향은 2이지만 해당 톱니바퀴에 맞닿아
    // 있는 양쪽 톱니바퀴 기준으로는 9시 방향은 2, 3시 방향은 6
    static void solution(int idx, int dir) {
        left(idx - 1, -dir);
        right(idx + 1, -dir);
        rotate(idx, dir);
    }

    // 입력받은 톱니의 왼쪽에 해당하는 톱니
    static void left(int idx, int dir) {
        if(idx < 0) return; // 범위 벗어나면 종료
        // 왼쪽 3시방향 톱니와 오른쪽 9시방향 톱니가 같지 않다면
        if(gear[idx][2] != gear[idx+1][6]){
            left(idx - 1, -dir);
            rotate(idx, dir); // 왼쪽 톱니 회전
        }
    }

    static void right(int idx, int dir) {
        if(idx > 3) return;

        if (gear[idx][6] != gear[idx - 1][2]) {
            right(idx + 1, -dir);
            rotate(idx, dir);
        }
    }

    // dir = 1 시계, dir = -1 반시계
    static void rotate(int idx, int dir) {
        if(dir == 1){
            int temp = gear[idx][7];

            for (int i = 7; i > 0; i--) {
                gear[idx][i] = gear[idx][i-1];
            }

            gear[idx][0] = temp;
        }else {
            int temp = gear[idx][0];

            for (int i=0; i<7; i++){
                gear[idx][i] = gear[idx][i+1];
            }

            gear[idx][7] = temp;
        }
    }
}
