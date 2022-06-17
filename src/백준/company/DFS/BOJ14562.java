package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14562 {
    // 태권왕 / 실버2 / DFS
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            int cnt = 0;
            System.out.println(dfs(s, t, cnt));
        }
    }

    static int dfs(int s, int t, int cnt) {
        if (s > t) {
            return 100;
        }
        if (s == t) {
            return cnt;
        } else {
            int min1 = dfs(s * 2, t + 3, cnt + 1);
            int min2 = dfs(s + 1, t, cnt + 1);
            return Math.min(min1, min2);
        }
    }
}
