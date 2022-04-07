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
    static boolean[][] visited;
    static int count = 1; // 총 단지 수
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
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
                if(!visited[i][j] && map[i][j] == 1){ // 집이 있으면서 방문하지 않은 곳
                    dfs(i,j); // 연결 되어있는 단지 탐색
                    count++; // 다음 단지 수로 이동
                }
            }
        }

        // 총 단지 수
        System.out.println(count-1);
        // 단지 내 집의 수
        int result[] = new int[count];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] != 0)
                    result[map[i][j]]++;
            }
        }

        // 단지 수 오름차순으로 출력
        Arrays.sort(result);
        for (int i = 1; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    static void dfs(int x, int y) {
        map[x][y] = count; // 방문한 집 표시
        visited[x][y] = true;

        // 상하좌우로 이동하며 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위를 벗어나지 않고
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(map[nx][ny] == 1 && !visited[nx][ny]){ // 집이있으면서 방문하지 않은 곳이라면
                    dfs(nx,ny);
                }
            }
        }
    }
}

