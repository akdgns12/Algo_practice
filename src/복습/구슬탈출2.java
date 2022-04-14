package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출2 {
    static int n, m;
    static int[][] map;
    static boolean[][][][] visited;
    static int holeX, holeY;
    static int[] dx = {-1,1,0,0}; // 상 하 좌 우
    static int[] dy = {0,0,-1,1};
    static Marble blue, red;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
                }else if(map[i][j] == 'B'){
                    blue = new Marble(0,0,i,j,0);
                }else if(map[i][j] == 'R'){
                    red = new Marble(i,j,0,0,0);
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Marble> q = new LinkedList<>();
        q.offer(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!q.isEmpty()){
            Marble marble = q.poll();

            int curRx = marble.rx;
            int curRy = marble.ry;
            int curBx = marble.bx;
            int curBy = marble.by;
            int curCnt = marble.cnt;

            for (int i = 0; i < 4; i++) {
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;
                // 빨간구슬 이동 -> 벽을 만날때까지
                while(map[newRx + dx[i]][newRy + dy[i]] != '#'){
                    newRx += dx[i];
                    newRy += dy[i];


                    if (newRx == holeX && newRy == holeY) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란구슬 이동 -> 벽을 만날때까지
                while(map[newBx + dx[i]][newBy + dy[i]] != '#'){
                    newBx += dx[i];
                    newBy += dy[i];

                    if(newBx == holeX && newBy == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole){ // 파란구슬이 구멍에 빠지면 무조건 실패
                    continue;
                }

                // 빨간구슬만 구멍에 빠지면 성공
                if (isRedHole && !isBlueHole) {
                    return curCnt;
                }

                // 둘 다 구멍에 빠지지 않았는데, 이동할 위치가 같은 경우 -> 위치 조정
                if (newRx == newBx && newRy == newBy) {
                    if(i == 0){
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 1){
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 2){
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }else if(i == 3){
                        if(curRy < curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                if(!visited[newRx][newRy][newBx][newBy]){
                    visited[newRx][newRy][newBx][newBy] = true;
                    q.offer(new Marble(newRx, newRy, newBx, newBy, curCnt + 1));
                }
            }
        }
        return -1;
    }

    static class Marble{
        int rx, ry;
        int bx, by;
        int cnt;

        public Marble(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
