package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460 {
    // 구슬 탈출 2 / 골드 1 / 시뮬레이션
    /*
        빨간 구슬을 구멍을 통해서 빼내야 한다.
        각각의 동작에서 공은 동시에 움직임. 파란 구슬이 구멍에 빠지면 실패. 두 구슬이 동시에 구멍이 빠져도 실패.
        두 구슬은 동시에 같은 칸에 있을 수 없다.
        최소 몇 번만에 빼낼 수 있는지 return
     */
    static int n, m;
    static int holeX, holeY;
    static char[][] map;
    static boolean[][][][] visited;
    static Ball blue, red;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};
    static int min = Integer.MAX_VALUE;

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
                if(map[i][j] == 'O'){ // 구멍위치
                    holeX = i;
                    holeY = j;
                }else if(map[i][j] == 'B'){
                    blue = new Ball(0,0,i,j,0);
                }else if(map[i][j] == 'R'){
                    red = new Ball(i,j,0,0,0);
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Ball> q = new LinkedList<>();
        q.offer(new Ball(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!q.isEmpty()){
            Ball b = q.poll();

            int curRx = b.rx;
            int curRy = b.ry;
            int curBx = b.bx;
            int curBy = b.by;
            int curCnt = b.cnt;

            for (int i = 0; i < 4; i++) {
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간구슬 벽을 만날때까지 이동
                while(map[newRx + dx[i]][newRy + dy[i]] != '#'){
                    newRx += dx[i];
                    newRy += dy[i];

                    if(newRx == holeX && newRy == holeY){
                        isRedHole = true;
                        break;
                    }
                }

                // 파란구슬 벽을 만날 때가지 이동
                while(map[newBx + dx[i]][newBy + dy[i]] != '#'){
                    newBx += dx[i];
                    newBy += dy[i];

                    if(newBx == holeX && newBy == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) { // 파란 구슬이 구멍에 빠지면 무조건 실패
                    continue; // 하지만 큐에 남은 다른 좌표도 봐야하니 스킵
                }

                if (isRedHole && !isBlueHole) { // 빨간 구슬만 구멍에 빠지면 성공
                    return curCnt;
                }

                // 둘 다 구멍에 빠지지 않았는데, 이동할 위치가 같은 경우 -> 위치 조정
                if(newRx == newBx && newRy == newBy){
                    if(i == 0) { // 위쪽으로 기울이기
                        // 더 큰 x값을 가진 구슬이 뒤로감
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 1){ // 아래쪽으로 기울이기
                        // 더 작은 x값을 가진 구슬이 뒤로 감
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 2){ // 왼쪽으로 기울이기
                        // 더 큰 y값을 가진 구슬이 뒤로감
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }else if(i == 3){ // 아래쪽으로 기울이기
                        // 더 작은 y값을 가진 구슬이 뒤로감
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                // 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우만 이동 -> 큐 추가
                if(!visited[newRx][newRy][newBx][newBy]){
                    visited[newRx][newRy][newBx][newBy] = true;
                    q.offer(new Ball(newRx, newRy, newBx, newBy, curCnt + 1));
                }
            }
        }

        return -1;
    }

    static class Ball{
        int rx, ry;
        int bx, by;
        int cnt;

        public Ball(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
