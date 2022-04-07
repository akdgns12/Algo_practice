package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 영역구하기 {
    static int m, n, k;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // x,y 좌표를 바꿔서 표현
        map = new int[n+1][m+1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // 좌표를 알맞게 맞춰주기 위해 +1
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    map[j][k] = 1;
                }
            }
        }


        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(map[i][j] == 0){
                    size = 0;
                    dfs(i,j);
                    answer.add(size);
                }
            }
        }

        System.out.println(answer.size());
        Collections.sort(answer);
        for (int i : answer) {
            System.out.print(i + " ");
        }

    }

    static void dfs(int x, int y){
        map[x][y] = 1;
        size++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || ny < 1 || nx > n || ny > m) continue;
            if(map[nx][ny] == 1) continue;
            dfs(nx, ny);
        }
    }
}
