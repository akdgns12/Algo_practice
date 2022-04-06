package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀 {
    static int n, k, l;
    static int[][] map;
    static int[] dx = {0,1,0,-1}; // 동 남 서 북
    static int[] dy = {1,0,-1,0};
    static ArrayList<Move> Info = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for (int i = 0; i < k; i++) { // 사과정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 사과 있는 곳은 1
        }

        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int timeInfo = Integer.parseInt(st.nextToken());
            char directionInfo = st.nextToken().charAt(0);

            Info.add(new Move(timeInfo, directionInfo));
        }

        System.out.println(dummy());
    }

    static int dummy(){
        int x = 1, y = 1; // 뱀 초기 위치
        map[x][y] = 2; // 뱀이 있는 자리는 2
        int now_dir = 0; // 처음엔 동쪽바라봄
        int turn = 0; // 방향 전환 횟수
        int time = 0; // 이동시간~

        Queue<Snake> q = new LinkedList<>();
        q.offer(new Snake(x,y));

        while (!q.isEmpty()) {
            int nx = x + dx[now_dir];
            int ny = y + dy[now_dir];
            // 범위를 벗어나지 않고 자신의 몸에 부딪히지 않을 때
            if(nx >= 1 && ny >= 1 && nx <= n && ny <= n && map[nx][ny] != 2){
                if(map[nx][ny] == 1){ // 사과가 있는 칸에 도착했을 경우
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2;
                }

                if(map[nx][ny] == 0){ // 사과가 없는 칸에 도착했을 경우
                    Snake tail = q.poll();
                    map[tail.x][tail.y] = 0; // 길이 줄이기
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2;
                }
            }else{ // 맵을 벗어나거나 몸통에 부딪히면 종료
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++;

            // 다음 방향전환으로 넘어가야 하는 경우
            if(turn < l && Info.get(turn).time == time){
                now_dir = rotate(now_dir, Info.get(turn).direction);
                turn++;
            }
        }
        return time;
    }

    static int rotate(int dir, char c) {
        if(c == 'D') dir = (dir + 1) % 4;
        else dir = (dir == 0) ? 3 : dir-1;
        return dir;
    }


    static class Snake{
        int x, y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Move{
        int time;
        char direction;

        public Move(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }
}
