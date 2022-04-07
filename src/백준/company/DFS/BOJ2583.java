package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583 {
    // 영역 구하기 / 실버 1 / DFS
    /*
        모눈종이 위에 k개의 직사각형을 그릴 때 이들 K개의 직사각형의 내부를 제외한
        나머지 부분이 몇 개의 분리된 영역으로 나누어짐.
        각 분리된 영역의 넓이가 몇인지 return
     */
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
        k = Integer.parseInt(st.nextToken()); // 직사각형의 개수

        map = new int[m+1][n+1];
        // 왼쪽 아래 꼭짓점과 오른쪽 위 꼭짓점이 주어진다
        // 마킹된 곳을 제외한 각각이 빈 구역들의 넓이를 구해야 하는 문제
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int k = x1; k < x2; k++) {
                for (int j = y1; j < y2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    size = 0;
                    dfs(i,j);
                    answer.add(size);
                }
            }
        }

        System.out.println(answer.size());
        Collections.sort(answer);
        for (int i : answer){
            System.out.print(i + " ");
        }

    }

    static void dfs(int x, int y) {
        map[x][y] = 1;
        size++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || ny < 1 || nx > m || ny > n) continue;
            if(map[nx][ny] == 1) continue;
            dfs(nx, ny);
        }
    }
}
