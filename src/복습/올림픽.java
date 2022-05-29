package 복습;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 올림픽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Nation[] countries = new Nation[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int nation = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[nation] = new Nation(gold, silver, bronze);
        }

        int result = 1;
        Nation ans = countries[k];
        for (int i = 1; i <= n; i++) {
            Nation c = countries[i];
            if(c.x > ans.x || (c.x == ans.x && c.y > ans.y) || (c.x == ans.x && c.y == ans.y && c.z > ans.z)) result++;
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
