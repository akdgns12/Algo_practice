package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468 {
    // 안전영역
    /*
        물에 잠기지 않는 안전영역의 최대개수를 return
        높이에 따른 dfs 탐색을 통해 각 높이에 따른 안전영역의 개수 중 가장 큰 값을 return
     */
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > maxHeight){
                    maxHeight = map[i][j];
                }
            }
        }

        int max = 0;
        for (int height = 0; height < maxHeight; height++) {
            visited = new boolean[n][n];

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(!visited[i][j] && map[i][j] > height){
                        dfs(i,j, height);
                        cnt++;
                    }
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int height) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny] > height && !visited[nx][ny]){
                dfs(nx,ny,height);
            }
        }
    }
}
