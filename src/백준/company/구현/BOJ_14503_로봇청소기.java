package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1,0,1,0}; // 북동남서
    static int[] dy = {0,1,0,-1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // robot initial info
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(x,y,dir);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int dir){
        switch(map[x][y]){
            case 0: // 1. 현재위치 청소
                map[x][y] = 2; // 청소한 곳은 2로
                ans++;
                print();
                break;
            case 1:
                return;
        }

        /*
            북 동 남 서
            북 -> 서
            동 -> 북
            남 -> 동
         */
        int nDir = dir;
        for(int i=0; i<4; i++){ // 2. 현재 위치를 기준 왼쪽 방향부터 탐색
            nDir = (nDir + 3) % 4; // 왼쪽
            int nx = x + dx[nDir];
            int ny = y + dy[nDir];

           if(map[nx][ny] == 0){ // 왼쪽이 청소하지 않은 공간이면
                dfs(nx, ny, nDir);
               System.out.println("이때가 여기구나");
                return;
           }
           // 청소할 공간 없다면, 그 방향으로 회전하고 2번부터
        }

        // 네 방향 모두 청소가 됐거나 벽인 경우, 바라보는 방향 유지한 채 한칸 후진
        System.out.println("후진할 때");
        dfs(x - dx[nDir], y - dy[nDir], dir);
    }

    static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Node{
        int x, y;
        int dir;
        public Node(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
