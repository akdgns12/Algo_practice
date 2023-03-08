package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13456_구슬탈출 {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][][][] visited;
    static Marble red, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    red = new Marble(i, j, 0, 0, 0);
                }else if(map[i][j] == 'B'){
                    blue = new Marble(0, 0, i, j, 0);
                }
            }
        }

        visited = new boolean[N][M][N][M];
        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Marble> q = new LinkedList<>();
        q.offer(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!q.isEmpty()){
            Marble now = q.poll();
            int curRx = now.rx;
            int curRy = now.ry;
            int curBx = now.bx;
            int curBy = now.by;

            if(now.cnt > 10) return 0;

            for(int i=0; i<4; i++){
                int nRx = now.rx;
                int nRy = now.ry;
                int nBx = now.bx;
                int nBy = now.by;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동
                while(map[nRx + dx[i]][nRy + dy[i]] != '#'){
                    nRx += dx[i];
                    nRy += dy[i];

                    if(map[nRx][nRy] == 'O'){
                        isRedHole = true;
                        break;
                    }
                }

                while(map[nBx + dx[i]][nBy + dy[i]] != '#'){
                    nBx += dx[i];
                    nBy += dy[i];

                    if(map[nBx][nBy] == 'O'){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;
                if(isRedHole && !isBlueHole) return 1;

                if(!isRedHole && !isBlueHole){
                    if(nRx == nBx && nRy == nBy){ // 같은 위치일 경우
                        switch(i){
                            case 0: // 상으로 이동 -> 더 큰 x값 가졌던 구슬이 한 칸 뒤로
                                if(curRx > curBx) nRx -= dx[i];
                                else nBx -= dx[i];
                                break;
                            case 1: // 하 -> 더 작은 x 값 가졌던 구슬이 한 칸 뒤로
                                if(curRx < curBx) nRx -= dx[i];
                                else nBx -= dx[i];
                                break;
                            case 2: // 좌 -> 더 큰 y 값 가졌던 구슬이 한 칸 뒤로
                                if(curRy > curBy) nRy -= dy[i];
                                else nBy -= dy[i];
                                break;
                            case 3: // 우 -> 더 작은 y 값 가졌던 구슬이 한 칸 뒤로
                                if(curRy < curBy) nRy -= dy[i];
                                else nBy -= dy[i];
                                break;
                        }
                    }
                }

                if(!visited[nRx][nRy][nBx][nBy]){
                    q.offer(new Marble(nRx, nRy, nBx, nBy, now.cnt + 1));
                    visited[nRx][nRy][nBx][nBy] = true;
                }
            }
        }

        return 0;
    }

    static class Marble{
        int rx, ry, bx, by;
        int cnt;
        public Marble(int rx, int ry, int bx, int by, int cnt){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
