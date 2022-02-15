package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출4 {
    static class Node{
        int rx, ry, bx, by;
        int cnt;

        public Node(int rx, int ry, int bx, int by, int cnt){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
    static int N,M;
    static char[][] map;
    static boolean[][][][] visited;
    static Node red, blue;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};
    static int holeX, holeY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
                }else if(map[i][j] == 'R'){
                    red = new Node(i,j,0,0,0);
                }else if(map[i][j] == 'B'){
                    blue = new Node(0,0,i,j,0);
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            int curRx = node.rx;
            int curRy = node.ry;
            int curBx = node.bx;
            int curBy = node.by;
            int curCnt = node.cnt;

            for(int i=0; i<4; i++){
                int nextRx = curRx;
                int nextRy = curRy;
                int nextBx = curBx;
                int nextBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간구슬 이동
                while(map[nextRx + dx[i]][nextRy + dy[i]] != '#'){
                    nextRx += dx[i];
                    nextRy += dy[i];

                    if(nextRx == holeX && nextRy == holeY){
                        isRedHole = true;
                        break;
                    }
                }

                // 파란구슬 이동
                while(map[nextBx + dx[i]][nextBy + dy[i]] != '#'){
                    nextBx += dx[i];
                    nextBy += dy[i];

                    if(nextBx == holeX && nextBy == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;

                if(isRedHole && !isBlueHole) {
                    return curCnt;
                }

                if(nextRx == nextBx && nextRy == nextBy){
                    if(i == 0){ // 위쪽으로 기울이기
                        if(curRx > curBx) nextRx -= dx[i];
                        else nextBx -= dx[i];
                    }else if(i == 1){ // 아래로 기울이기
                        if(curRx < curBx) nextRx -= dx[i];
                        else nextBx -= dx[i];
                    }else if(i == 2){ // 왼쪽으로 기울이기
                        if(curRy > curBy) nextRy -= dy[i];
                        else nextBy -= dy[i];
                    }else if(i == 3){ // 오른쪽으로 기울이기
                        if(curRy < curBy) nextRy -= dy[i];
                        else nextBy -= dy[i];
                    }
                }

                if(!visited[nextRx][nextRy][nextBx][nextBy]){
                    visited[nextRx][nextRy][nextBx][nextBy] = true;
                    q.offer(new Node(nextRx, nextRy, nextBx, nextBy, curCnt+1));
                }
            }

        }
        return -1;
    }

}
