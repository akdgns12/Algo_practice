package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    // 테트로미노 / 골드 5 / 시뮬레이션
    /*
        ㅗ를 제외한 모양은 dfs로 한번에 다 그릴 수 있다.
        0,0 ~ n,m까지 시작점을 바꿔가며 depth4로 조건을 주어 구현
        ㅗ 모양은 ㅗ ㅓ ㅏ ㅜ 로 돌아 갈 수 있기 때문에 + 모양에서 날개 하나를 빼는식으로 구현
        날개가 2개 이하일때는 함수를 종료시켜 계산하지 않는다.
        계산값들 중 최고 값을 출력한다.
     */
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0);
                exception(i,j);
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth+1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    // ㅗ 모양 검사
    // + 모양에서 각 꼭짓점을 하나씩 뗀다.
    static void exception(int x, int y) {
        int wing = 4; // 상하좌우 날개
        int min = Integer.MAX_VALUE;
        int sum = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 날개가 맵 바깥에 있다면 날개가 아닌것이므로 하나 뗀다.
            if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                wing--;
                continue;
            }

            if(wing <= 2) return;
            min = Math.min(min, map[nx][ny]);
            sum = sum + map[nx][ny];
        }

        // 날개가 4개일때는 가장 작은 날개를 없애야 최대의 ㅗ ㅏ ㅓ ㅜ 모양이 나온다.
        if(wing == 4){
            sum = sum - min;
        }

        max =  Math.max(sum, max);
    }
}
