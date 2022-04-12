package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀복스으읍 {
    static int n, k, l;
    static int[][] map;
    static int[] dx = {0,1,0,-1}; // 동남서북
    static int[] dy = {1,0,-1,0};
    static ArrayList<Info> direction = new ArrayList<>();

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
            map[x][y] = 1; //사과의 위치는 1로매핑
        }

        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int timeInfo = Integer.parseInt(st.nextToken());
            char dirInfo = st.nextToken().charAt(0);

            direction.add(new Info(timeInfo, dirInfo));
        }

        System.out.println(Dummy());
    }

    static int Dummy(){
        int x = 1, y = 1; // 시작위치
        map[x][y] = 2; // 뱀이 있는곳 2로 매핑
        int now_dir = 0; // 뱀은 초기에 동쪽 바라봄
        int time = 0; // 뱀 이동 횟수
        int turn = 0; // 뱀 방향전환 횟수

        Queue<Snake> q = new LinkedList<>();
        q.offer(new Snake(x, y));

        while(!q.isEmpty()){
            // 뱀은 먼저 몸길이를 늘려 머리를 다음칸에 위치시킴
            int nx = x + dx[now_dir];
            int ny = y + dy[now_dir];
            // 범위 벗어나지 않고 뱀 자신의 몸을 만나지 않았다면 게임 진행
            if (nx >= 1 && ny >= 1 && nx <= n && ny <= n && map[nx][ny] != 2) {
                if(map[nx][ny] == 1){ // 이동한 칸에 사과가 있다면
                    map[nx][ny] = 2;
                    q.offer(new Snake(nx, ny));
                }
                if(map[nx][ny] == 0){ // 이동한 칸에 사과가 없다면, 몸길이 줄이고 꼬리가 위치한 칸 비워줌
                    Snake tail = q.poll();
                    map[tail.x][tail.y] = 0;
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2;
                }
            }else{ // 맵을 벗어나거나 자신의 몸통과 부딪히면 종료
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++;

            //다음 방향전환으로 넘어가야 하는 경우
            if (turn < l && direction.get(turn).time == time) {
                now_dir = rotate(now_dir, direction.get(turn).dir);
                turn++;
            }
        }

        return time;
    }

    static int rotate(int dir, char c) {
        if(c == 'D')
            dir = (dir + 1) % 4;
        else
            dir = (dir == 0) ? 3 : dir - 1;
        return dir;
    }


    static class Snake{
        int x, y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info{
        int time;
        char dir;

        public Info(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}
