package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class 곡예비행 {
    static int n, m;
    static int[][] map;
    static int[][] upMove;
    static int[][] downMove;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        upMove = new int[n][m];
        downMove = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //상승
        upMove[n-1][0] = map[n-1][0];
        for (int i = n - 1; i >= 0; i--) {
            // 마지막 행이 아니라면 첫번째 열의 값들은 아래에서만 올라오는 값
            if(i != n-1) upMove[i][0] = upMove[i+1][0] + map[i][0];
            // 첫번째 열이 아닌 나머지 열들은 아래에서 올라오고, 왼쪽에서 오는 경우 2가지
            for (int j = 1; j < m; j++) {
                if(i != n-1) upMove[i][j] = Math.max(upMove[i+1][j], upMove[i][j-1]) + map[i][j];
                // 마지막행에서는 왼쪽에서 오는 경우만 존재
                else upMove[i][j] = upMove[i][j-1] + map[i][j];
            }
        }

        //하강
        //도착점은 고정 -> 출발을 도착점부터로 상승처럼 처리
        downMove[n-1][m-1] = map[n-1][m-1];
        for (int i = n - 1; i >= 0; i--) {
            // 마지막 행이 아니라면 마지막 열은 아래에서 올라오는 경우만 존재
            if(i != n-1) downMove[i][m-1] = downMove[i+1][m-1] + map[i][m-1];
            // 마지막 열이 아닌 나머지 열들은 아래에서 올라오고, 오른쪽에서 오는 2가지 경우
            for (int j = m - 2; j >= 0; j--) {
                // 마지막 행이 아니라면 2가지 아래 -> 위, 오->왼 2가지 케이스
                if(i != n-1) downMove[i][j] = Math.max(downMove[i+1][j], downMove[i][j+1]) + map[i][j];
                // 마지막 행이라면 오->왼 케이스만 존재
                else downMove[i][j] = downMove[i][j+1] + map[i][j];
            }
        }


        // 상승 + 하강
        // 같은 좌표에서 더한값이 최대가 되는 좌표가 상승 마무리 및 하강 시작점
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(downMove[i][j] + upMove[i][j], max);
            }
        }

        System.out.println(max);
    }
}
