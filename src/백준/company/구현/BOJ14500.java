package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    // 테트로미노 / 골드5 / 구현
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result = Integer.MIN_VALUE;

    // 격자판에 테트로미노 하나를 놓아 최대한의 점수를 얻도록
    // 생각 생각..
    /*
        아이디어 - ㅓ ㅜ ㅏ ㅓ 모양만 제외하고 나머지 모양은 dfs로 만들 수 있음
        4칸을 고르는 경우의수 마다 얻을 수 있는 점수
        위의 따로 구해야 하는 모형에서 얻을 수 있는 점수
        비교해서 최댓값
     */
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
                exception(i,j); // 예외 모양
            }
        }

        System.out.println(result);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if(depth == 4){ // 하나의 모양을 완성시켰다면
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    // ㅓ ㅜ ㅏ ㅗ 모양 +모양에서 최솟값인 날개 하나 떼기
    static void exception(int x, int y) {
        int wing = 4;
        int min = Integer.MAX_VALUE;
        int sum = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나면 해당 좌표는 wing이 될 수 없음
            if(nx < 0 || ny < 0 || nx >= n || ny >=m){
                wing--;
                continue;
            }

            if(wing <= 2) return; // 날개가 떨어져 나가 2개 이하가 된다면 조건에 맞지 않는 모양
            min = Math.min(min, map[nx][ny]); // 제외할 날개를 찾기 위해 날개 중 최솟값 찾기
            sum = sum + map[nx][ny];
        }

        if(wing == 4){
            sum = sum - min; // 제외할 날개 총합에서 제외
        }

        result = Math.max(sum, result); // 다른 모양들에서 구한 값들과 비교해 더 큰 값이 격자판에 놓을 모양
    }
}
