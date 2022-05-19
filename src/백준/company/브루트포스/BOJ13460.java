package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460 {
    // 구슬탈출2 / 골드 1 / 구현
    // 빨간구슬을 구멍을 통해 빼내야 한다. 몇 번만에 뺄 수 있는지 return
    // 생각생각..
    //
    static int n, m;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};
    static Marble blue, red;
    static int holeX, holeY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'B'){
                    blue = new Marble(0, 0, i, j, 0);
                }else if(map[i][j] == 'R'){
                    red = new Marble(i,j,0,0,0);
                }else if(map[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
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

            if(curCnt > 10){ // 이동횟수 10회 초과시 실패
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간구슬 이동 -> # 벽을 만날때까지 이동
                while(map[newRx + dx[i]][newRy + dy[i]] != '#'){
                    newRx += dx[i];
                    newRy += dy[i];

                    if (newRx == holeX && newRy == holeY) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란구슬 이동 -> # 벽을 만날때까지 이동
                while (map[newBx + dx[i]][newBy + dy[i]] != '#') {
                    newBx += dx[i];
                    newBy += dy[i];

                    if (newBx == holeX && newBy == holeY) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) { // 파란 구슬이 구멍에 빠지면 무조건 실패
                    continue; // 하지만 큐에 남은 다른 좌표도 봐야하니 스킵
                }

                if(isRedHole && !isBlueHole){
                    return curCnt;
                }

                // 둘 다 구멍에 빠지지 않았는데, 이동할 위치가 같은 경우 -> 위치 조정
                if(newRx == newBx && newRy == newBy){
                    if(i == 0){ // 위로 기울이기
                        // 더 큰 x값을 가진 구슬이 뒤로감
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 1){ // 아래로 기울
                        // 더 작은 x 값을 가진 구슬이 뒤로
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 2){ // 좌로 기울
                        // 더 큰 y값을 가진 구슬이 뒤로감
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }else if(i == 3){
                        if(curRy < curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                // 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우만 이동 -> 큐 추가
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
