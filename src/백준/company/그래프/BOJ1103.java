package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1103 {
    // 게임 / 골드2 /
    /*
        동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
        위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
        동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
        만약 동전이 빠지거나,보드의 바깥으로 나간다면 게임은 종료
     */
    // DFS + DP로 풀어야 한다... 너무 어렵다 알고리즘
    static class Node{
        int x, y;
        int cnt;
        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int N,M;
    static char[][] map;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;
    static boolean isInfinite;
    static int cnt = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M]; // 방문확인 무한루프 체크용
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
            }
        }

        isInfinite = false;
        visited[0][0] = true;
        dfs(0,0,1);

        if(isInfinite)
            System.out.println(-1);
        else
            System.out.println(max);
    }

    static void dfs(int x, int y, int cnt){
        // 답 최신화
        if(cnt > max){
            max = cnt;
        }

        dp[x][y] = cnt; // 가지치기용 dp배열에 현재 cnt 마킹
        for(int i=0; i<4; i++){
            int nx = x + dx[i] * (map[x][y] - '0');
            int ny = y + dy[i] * (map[x][y] - '0');

            // 범위 벗어나거나 구멍이면 skip
            if(nx < 0 || ny < 0 || nx >= N || ny >= M
            || map[nx][ny] == 'H') continue;

            if(visited[nx][ny]){ // 방문한 지점에 돌아왔으므로 무한루프 가능
                isInfinite = true;
                return;
            }

            if(dp[nx][ny] > cnt) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, cnt+1);
            visited[nx][ny] = false;
        }
    }
}
