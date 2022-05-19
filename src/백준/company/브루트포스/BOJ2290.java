package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2290 {
    // LCD Test / 실버2 / 구현, 데이터 전처리 많이 필요한 문제 - 구현력 기르기 좋음
    // 길이가 s인 - 와 l를 이용해서 출력.
    static String[][] board;
    /*
        표시되는 숫자 모양의 공통점 찾고, case를 나눠 매핑될 수 있도록 전처리
        각 숫자 10개마다 표시되는 부분을 7개의 영역으로 나눠 처리
     */
    static int[][] map = {  { 0, 1, 1, 1, 0, 1, 1, 1 }, // 0 - 4번 부분이 빔
            { 0, 0, 0, 1, 0, 0, 1, 0 }, // 1 - 3,6을 제외한 나머지 부분 빔
            { 0, 1, 0, 1, 1, 1, 0, 1 }, // 2 - 2,6만 빔
            { 0, 1, 0, 1, 1, 0, 1, 1 }, // 3 - 2,5 빔
            { 0, 0, 1, 1, 1, 0, 1, 0 }, // 4 - 1,5,7 빔
            { 0, 1, 1, 0, 1, 0, 1, 1 }, // 5 - 3,5 빔
            { 0, 1, 1, 0, 1, 1, 1, 1 }, // 6 - 3빔
            { 0, 1, 0, 1, 0, 0, 1, 0 }, // 7 - 2,4,6,7 빔
            { 0, 1, 1, 1, 1, 1, 1, 1 }, // 8 - 안빔
            { 0, 1, 1, 1, 1, 0, 1, 1 } // 9 - 5빔
    };
    static int s, n;
    static int left;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        String[] num = st.nextToken().split("");
        n = num.length; // 표현해야 하는 입력받은 수의 길이
        board = new String[2 * s + 4][n * (s + 4)]; // 세로 : 2*s+3 + 1, 가로 : 각 숫자 사이에는 공백 있어야하니 수의 길이 * (s + 2 + 공백 + 1)

        for (int i = 0; i < 2 * s + 3; i++) {
            for (int j = 0; j < n * (s + 4); j++) {
                board[i][j] = " ";
            }
        }

        /*
            각 영역의 범위를 아래처럼 표현
               row --------------- col
            1   0       left + 1 <= left + s
            4   s+1
            7   2*(s+1)
            2   1 <= s          left
            5 s+2 <= 2*s+1
            3   1 <= s          left + s + 1
            6 s+2 <= 2*s+1
         */
        for (int i = 0; i < n; i++) {
            int n = Integer.parseInt(num[i]);
            if(map[n][1] == 1) fill(0, 0, left + 1, left + s, "-");
            if(map[n][4] == 1) fill(s+1, s+1, left + 1,left + s, "-");
            if(map[n][7] == 1) fill(2*(s+1), 2*(s+1), left + 1, left + s, "-");

            if(map[n][2] == 1) fill(1, s, left, left, "|");
            if(map[n][5] == 1) fill(1 + (s + 1), s + (s + 1), left, left, "|");

            if(map[n][3] == 1) fill(1, s, left + s + 1, left + s + 1, "|");
            if(map[n][6] == 1) fill(1 + (s + 1), s + (s + 1), left + s + 1, left + s + 1, "|");

            left += s + 3; // 하나의 숫자 그리고 나서, 공백을 포함헤 다음 수를 그릴 시작 좌표로 가야하니 s + 2 + 공백 (=left + s + 3)
        }

        result();
    }

    static void result(){
        for (int i = 0; i < 2 * s + 3; i++) {
            for (int j = 0; j < left; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static void fill(int sx, int ex, int sy, int ey, String str){
        for (int x = sx; x <= ex; x++) {
            for (int y = sy; y <= ey; y++) {
                board[x][y] = str;
            }
        }
    }
}
