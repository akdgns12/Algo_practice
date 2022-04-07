package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468 {
    // 안전 영역 / 실버 1 / DFS
    // 어떤 지역의 높이 정보를 파악하고, 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는지 조사
    // 모든 비의 양에 따른 안전 영역의 최대 갯수 return
    /*
        각 높이에 따라 브루트포스로 맵을 전체탐색 한다
        탐색 도중 안전지역이 있으면 해당 지역을 시작으로 DFS탐색을 시작한다
        안전지역 DFS 탐색이 끝났으면 카운팅
        높이에 따라 나온 cnt 중 최댓값을 출력
     */
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > maxHeight){
                    maxHeight = map[i][j];
                }
            }
        }

        int max = 0;
        // 1. 모든 지역 탐색
        for (int height = 0; height < maxHeight; height++) {
            visited = new boolean[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(!visited[i][j] && map[i][j] > height){
                        dfs(i,j,height);
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

            if(nx < 0 || ny < 0 || nx >=n || ny >= n || visited[nx][ny]) continue;
            if(map[nx][ny] > height){
                dfs(nx,ny,height);
            }
        }
    }
}
