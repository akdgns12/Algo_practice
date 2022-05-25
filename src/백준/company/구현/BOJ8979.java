package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ8979 {
    // 올림픽 / 실버 5 / 구현
    static int n, k;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 국가의 수
        k = Integer.parseInt(st.nextToken()); // 등수를 알고싶은 국가
        Nation countries[] = new Nation[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int nation = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[nation] = new Nation(gold, silver, bronze);
        }

        int result = 1;
        Nation want = countries[k];
        for (int i = 1; i <= n; i++) {
            Nation c = countries[i];
            if(c.x > want.x || (c.x == want.x && c.y > want.y) || (c.x == want.x && c.y == want.y && c.z > want.z)) result++;
        }

        System.out.println(result);
    }

    static class Nation{
        int x, y, z;

        public Nation(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
