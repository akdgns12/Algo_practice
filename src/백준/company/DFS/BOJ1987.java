package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
    // 알파벳 / 골드 4 / DFS
    static int r, c;
    static char[][] map;
    static boolean[] alpha;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        //좌측상단에서 시작해서 말이 최대한 몇칸을 지날 수 있는지
        alpha = new boolean[26];
        // 시작지점
        alpha[map[0][0] - 'A'] = true;
        dfs(0,0, 1);

        System.out.println(max);
    }

    static void dfs(int x, int y, int cnt){
        // 모든 알파벳을 다 모았을 경우 최대 칸이므로 더 볼 필요가 없음
        if(max == 26) return;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어날 경우 pass
            if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
            // 이미 체크되어 있다면 pass
            if(alpha[map[nx][ny] - 'A']) continue;

            // 현재 알파벳 체크
            alpha[map[nx][ny] - 'A'] = true;
            // 갈 수 있는 곳이라면 가보자
            dfs(nx, ny, cnt+1);
            // 현재 알파벳 체크 해제
            alpha[map[nx][ny] - 'A'] = false;
        }
        // 사방으로 갈 길이 없다면 최대 칸 수 갱신
        max = Math.max(max, cnt);
    }
}
