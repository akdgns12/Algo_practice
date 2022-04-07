package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2667 {
    // 단지번호 붙이기 / 실버 1 / DFS
    // 총 단지수, 각 단지내의 집의 수 출력
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited; // 방문 여부 체크
    static int cnt = 1; // 총 단지 수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1 && !visited[i][j]){
                    dfs(i,j);
                    cnt++;
                }
            }
        }
        // 총 단지수
        System.out.println(cnt-1);

        // 각 단지내 집의 수
        int[] result = new int[cnt];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] != 0)
                    result[map[i][j]]++;
            }
        }

        Arrays.sort(result);
        for (int i = 1; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    static void dfs(int x, int y){
        map[x][y] = cnt;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(!visited[nx][ny] && map[nx][ny] == 1){
                    dfs(nx,ny);
                }
            }
        }
    }
}

