package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018 {
    // 체스판 다시 칠하기 / 실버 5 / 완탐
    // 체스판으로 만들기 위해 칠해야하는 최소 개수
    /*
        생각
        - 체스판을 만들기 위해서는 한 칸이 상하좌우의 색과 달라야 함
        -
     */
    static char[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        // B : 검은색, W : 흰색
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 완탐
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                checkChess(i, j);
            }
        }

        System.out.println(answer);
    }

    static void checkChess(int x, int y) {
        char value = map[x][y]; // 첫 번째 칸의 색
        int cnt = 0;
        // 규칙에 맞는 칸의 개수를 세고
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                if (map[i][j] != value) {
                    cnt++;
                }
                // 하나 검사하고 value 변경
                if(value == 'W') value = 'B';
                else value = 'W';
            }
            // 한줄 검사하고 다음줄에 다시 value 변경
            if(value == 'W') value = 'B';
            else value = 'W';
        }

        // (64 - cnt) = 전체 개수에서 맞는 개수를 빼준값이 구해야할 다시 칠해야 하는 개수
        // 64 - cnt보다 cnt가 더 적을 수도 있으니 둘 중 최솟값이 다시 칠해야 하는 개수
        // answer에 모든 좌표에서 시작해 나올 수 있는 칠해야 하는 경우의 수 중 최솟값 저장
        answer = Math.min(answer, Math.min(cnt, 64 - cnt));
    }
}
