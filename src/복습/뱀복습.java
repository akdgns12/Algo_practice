package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀복습 {
    static int n, k;
    static int l;
    static ArrayList<Move> Info = new ArrayList<>();
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 사과 1로 설정
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

    static int dummy() {
        int x = 1, y = 1; // 뱀 시작위치
        map[x][y] = 2; // 뱀이 있는 자리는 2
        int now_dir = 0; // 처음 뱀이 바라보는 방향은 동쪽
        int turn = 0;
        int time = 0;

        Queue<Snake> q = new LinkedList<>();
        q.offer(new Snake(x,y));

        while(!q.isEmpty()){
            int nx = x + dx[now_dir];
            int ny = y + dy[now_dir];

            if (nx >= 1 && ny >= 1 && nx <= n && ny <= n && map[nx][ny] != 2) {
                if(map[nx][ny] == 1){
                    map[nx][ny] = 2;
                    q.offer(new Snake(nx, ny));
                }

                if(map[nx][ny] == 0){
                    Snake tail = q.poll();
                    map[tail.x][tail.y] = 0; // 길이 줄이기
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2;
                }
            }else{
                time++;
                break;
            }

            time++;
            x = nx;
            y = ny;

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
