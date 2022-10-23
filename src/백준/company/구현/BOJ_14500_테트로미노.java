package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
                Exception(i, j);
            }
        }


        sb.append(ans);
        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int x, int y, int cnt, int sum){
        if(cnt >= 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny <0 || nx >= N || ny >= M || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    // 'ㅗ' 모양 구현
    // '+' 모양에서 하나를 빼는식으로 구현해보자
    static void Exception(int x, int y){
        int wing = 4;
        int min = Integer.MAX_VALUE;
        int sum = map[x][y];
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 날개 3개미만이면 ㅗ 모양 아님
            if(wing <= 2){
                return;
            }

            if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                wing--;
                continue;
            }

            min = Math.min(min, map[nx][ny]); // 날개 4개일때 하나 빼주기 위해 가장 작은 값의 날개 저장
            sum = sum + map[nx][ny];
        }

        if(wing == 4){
            sum -= min;
        }

        ans = Math.max(ans, sum);
    }
}
