package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520_복습필요 {
    static int M, N;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 내리막 길 / 골드4 / 최단거리 탐색이 아닌 특정지점으로의 경우의 수를 구하는 유형이므로 DFS탐색이 유용할 듯
        /*
        DFS와 다이나믹 프로그래밍으로 풀 수 있는 문제였습니다.
        시작점에서 끝점까지 도달할 수 있는 경로의 개수를 세는 문제로, DFS만 사용하면 시간초과가 발생합니다.
        따라서, 시작점에서 끝점까지 DFS를 이용하여 탐색은 하되, 이미 탐색해서 경로의 개수가 파악된 지점을 또 탐색하게 되면, 저장된 경로의 개수를 반환하면 됩니다.
        예를 들어, (1, 1)에서 (10, 10)으로 갈 수 있는 경로의 개수를 구한다고 해 봅시다.
        만약, (3, 3)에서 (10, 10)으로 갈 수 있는 경로가 3개라고 가정합니다.
        그리고 (1, 1)부터 탐색을 시작해서 (2, 2)에 도달하고, (2, 2)에서는 어쩌다보니 (3, 3) 지점에 도착하였다고 합시다.
        만약, (3, 3) -> (10, 10) 의 경로의 개수를 저장해 두지 않았다면, (2, 2)를 시작점으로 하고, (10, 10)을 끝점으로 하는 경로의 개수를 구하기 위해서 (3, 3) -> (10, 10) 의 경로의 개수를 중복해서 세야합니다.
        따라서, 한 번 끝점까지 탐색이 완료된 점은 dp를 사용하여 경로의 개수를 저장해 놓고, 다른 점에서 또 탐색이 되었을 때는 이 경로를 더해주는 방향으로 로직을 설계하시면 되겠습니다.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N]; // (x,y)에서 도착점으로 가는 경로의 개수
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        dfs(0,0);

        System.out.println(dp[0][0]);
    }

    static void dfs(int x, int y){
        if(x == M-1 && y == N-1){
            dp[x][y] = 1;
            return;
        }

        dp[x][y] = 0; // 방문처리
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            if(map[x][y] > map[nx][ny]){
                if(dp[nx][ny]  == -1){
                    dfs(nx,ny);
                }
                dp[x][y] += dp[nx][ny];
            }
        }


    }
}

