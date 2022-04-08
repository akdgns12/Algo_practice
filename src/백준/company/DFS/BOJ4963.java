package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963 {
    // 섬의 개수 / 실버 2 / DFS
    static int w, h;
    static int[][] map;
    static int[] dx = {-1,-1,-1,0,1,1,1,0}; // 좌상,상,우상,우,우하,하,좌하,좌
    static int[] dy = {-1,0,1,1,1,0,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            // 0 0 이 입력되면 종료
            if(w == 0 & h == 0)
                break;

            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int landCnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1) { // 땅이면
                        dfs(i, j);
                        landCnt++;
                    }
                }
            }
            System.out.println(landCnt);
        }
    }

    static void dfs(int x, int y){
        // 방문했으니까 방문처리
        map[x][y] = 0;
        // 주변을 탐색
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나거나 물이면 pass
            if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
            if(map[nx][ny] == 0) continue;
            dfs(nx, ny); // 방문해볼 땅을 방문
        }
    }
}
