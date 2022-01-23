package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int rx, ry, dir;
    // 북, 동, 남, 서
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        // 로봇 초기 좌표와 방향
        st = new StringTokenizer(br.readLine());
        rx = Integer.parseInt(st.nextToken());
        ry = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        // 0 : 빈칸, 1 : 벽
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(rx, ry, dir);
        System.out.println(result);
    }

    static void solve(int x, int y, int dir){
        switch(map[x][y]){
            case 1:
                return;
            case 0:
                map[x][y] = 2;
                result++;
        }

        int nDir = dir;
        for(int i=0; i<4; i++){
            nDir = (nDir + 3) % 4; // 반시계방향
            int nx = x + dx[nDir];
            int ny = y + dy[nDir];

            if(map[nx][ny] == 0){ // 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면
                solve(nx, ny, nDir); // 그 방향으로 회전한 다음 한칸을 전진하고 1번부터 진행
                return;
            }
        }

        solve(x - dx[nDir], y - dy[nDir], dir);
        return;
    }
}
