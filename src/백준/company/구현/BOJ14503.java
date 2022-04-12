package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
    // 로봇청소기 / 골드 5 / 구현
    static int n, m;
    static int[][] map;
    static int result = 0;
    static int[] dx = {-1,0,1,0}; // 북동남서
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken()); // 방향

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        dfs(r,c,d);
    }

    /*
        로봇 작동과정
        1. 현재위치 청소
        2. 인접한 칸 탐색
          a. 현재위치의 바로 왼쪽에 아직 청소하지 않은 빈공간 존재한다면, 왼쪽으로 회전한 다음 한칸 전진하고 1번으로 돌아감
          그렇지 않다면, 왼쪽 방향으로 회전한다. 이때 왼쪽은 바라보는 방향을 기준으로
          b. 1번으로 돌아가거나 후진하지 않고 2번 a 단계가 연속으로 네 번 실행되었을 경우, 바로 뒤쪽이 벽이라면 작동을 멈춘다. 그렇지 않다면 한 칸 후진
     */
    static void dfs(int x, int y, int dir) {
        switch(map[x][y]){
            case 0 : // 빈칸
                map[x][y] = 2; // 청소한 칸은 2로 매핑
            case 1 : // 벽
                return;
        }

        // 인접한 칸 탐색
        int nDir = dir;
        for (int i = 0; i < 4; i++) {
            nDir = (nDir + 3) % 4; // 현재 바라보는 기준 왼쪽
            int nx = x + dx[nDir];
            int ny = y + dy[nDir];

            if(map[nx][ny] == 0){ // 왼쪽에 아직 청소하지 않은 빈공간 존재한다면
                dfs(nx, ny, nDir); // 그 방향으로 회전한 다음 한칸 전진하고 1번부터 진행
                return;
            }
        }
        // 1번으로 돌아가거나 후진하지 않고, a번 단계가 연속으로 네 번 실행되었을 경우(인접한 곳에 청소하지 않은 빈공간이 없다면), 바로 뒤쪽이 벽이라면 작동 멈춤
        // 인접한 곳에 청소하지 않은 빈공간이 없고, 뒤쪽이 벽이 아니라면 뒤로 한 칸 후진
        dfs(x - dx[nDir], y - dy[nDir], dir);
        return;
    }
}
