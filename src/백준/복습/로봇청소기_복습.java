package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_복습 {
    static int N,M;
    static int[][] map;
    static int result = 0;
    static int[] dx = {-1,0,1,0}; // 북 동 남 서
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int rx = Integer.parseInt(st.nextToken());
        int ry = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

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
            case 1: // 벽이라면 종료
                return;
            case 0: // 빈 칸이라면 해당 칸 2로 바꿔주고 답 카운팅
                map[x][y] = 2;
                result++;

        }

        int nDir = dir;
        for(int i=0; i<4; i++){
            nDir = (nDir + 3) % 4;
            int nx = x + dx[nDir];
            int ny = y + dy[nDir];

            if(map[nx][ny] == 0){ // 탐색한 칸이 빈칸이라면
                solve(nx, ny, nDir);
                return;
            }
        }

        // 방향 유지한채 한칸 후진
        solve(x - dx[nDir], y - dy[nDir], dir);
        return;
    }
}
