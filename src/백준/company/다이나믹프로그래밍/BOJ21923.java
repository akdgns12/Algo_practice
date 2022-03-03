package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21923 {
    // 곡예비행 / 골드 4 / DP
    // 상향, 하향 따로 계산
    static int n, m;
    static int[][] map;
    static int[][] up;
    static int[][] down;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        up = new int[n][m];
        down = new int[n][m];
        //각 칸의 점수정보
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //상승 비행
        up[n-1][0] = map[n-1][0];
        for (int i=n-1; i>=0; i--){
            // 첫번째 열의 점수들은
            if(i != n-1) up[i][0] = up[i+1][0] + map[i][0];
            //첫번째 열이 아닌 나머지 열들은 아래에서 올라오고 왼쪽에서 올라오고 2가지 케이스
            for (int j = 1; j < m; j++) {
                if(i != n-1) up[i][j] = Math.max(up[i+1][j], up[i][j-1]) + map[i][j];
                // 행이 마지막행에서 오른쪽으로 오는 케이스
                else up[i][j] = up[i][j-1] + map[i][j];
            }
        }

        // 하강 비행
        /*
            1. 하강, 상승 나눠 생각해야했다
            2. 하강비행을 상승비행처럼?
            -> 왜 이렇게 생각하고 풀어야 할까?
            상승은 출발점이 정해져 있고 도착점을 모른다
            -> 출발점부터 얻을 수 있는 최대 점수를 얻으면 됨
            하강은 도착점이 정해져 있고 출발점을 모른다
            -> 시작을 도착점으로 하고 얻을 수 있는 최대점수를 얻는다
         */
        down[n-1][m-1] = map[n-1][m-1];
        for (int i = n - 1; i >= 0; i--) {
            if(i != n-1) down[i][m-1] = down[i+1][m-1] + map[i][m-1];
            for (int j = m - 2; j >= 0; j--) {
                if(i != n-1) down[i][j] = Math.max(down[i+1][j], down[i][j+1]) + map[i][j];
                else down[i][j] = down[i][j+1] + map[i][j];
            }
        }

        //상승 점수 + 하강 점수
        //결국 같은 좌표에서 서로의 최대로 얻을 수 있는 점수를 얻은 상태로 만나게 된다
        //두 값을 더하면 중복된 부분까지 처리가능
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(down[i][j] + up[i][j], max);
            }
        }

        System.out.println(max);
    }
}
