package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987_알파벳 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] visitedColor;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        // 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
        // 0,0에서 시작해 최대한 갈 수 있는 칸 수 -> dfs?
        visited = new boolean[R][C];
        visitedColor = new boolean[26];
        visited[0][0] = true;
        visitedColor[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(ans);
    }

    static void dfs(int x, int y, int cnt){
        ans = Math.max(ans, cnt);

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx,ny)|| visited[nx][ny]) continue;
            if(visitedColor[map[nx][ny] - 'A']) continue;

            visitedColor[map[nx][ny] - 'A'] = true;
            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1);
            visited[nx][ny] = false;
            visitedColor[map[nx][ny] - 'A'] = false;
        }
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= R || y >= C) return false;
        else return true;
    }
}
