package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기 {
    static char[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j < m - 8; j++) {
                find(i, j);
            }
        }

        System.out.println(answer);
    }

    static void find(int x, int y) {
        int value = map[x][y];
        int cnt = 0;
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                if (map[i][j] != value) {
                    cnt++;
                }

                if(value == 'W') value = 'B';
                else value = 'W';
            }
            if(value == 'W') value = 'B';
            else value = 'W';
        }

        answer = Math.min(answer, Math.min(cnt, (64 - cnt)));
    }
}
