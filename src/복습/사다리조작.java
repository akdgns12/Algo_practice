package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작 {
    static int n,m,h;
    static int[][] map;
    static boolean isFinish = false;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 1 : 왼쪽으로 이동
            map[x][y+1] = 2; // 2 : 오른쪽으로 이동
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 1, 0);
            if(isFinish) break;
        }

        System.out.println(isFinish ? answer : -1);
    }

    static void dfs(int x, int y, int count) {
        if(isFinish) return;
        if (answer == count) {
            if(check()) isFinish = true;
            return;
        }

        for (int i = x; i <= h; i++) {
            for (int j = y; j < n; j++) {
                if(map[i][j] == 0 && map[i][j+1] == 0){
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    dfs(1, 1, count+1);
                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int nx = 1;
            int ny = i;

            while(nx <= h){
                if(map[nx][ny] == 1) ny++;
                else if(map[nx][ny] == 2) ny--;
                nx++;
            }
            if(ny != i) return false;
        }
        return true;
    }
}
