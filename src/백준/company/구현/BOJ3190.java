package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190 {
    // 뱀 / 골드5 / 구현
    static int n, k;
    static int l;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
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
            map[x][y] = 1; // 사과는 1로 매핑
        }

        l = Integer.parseInt(br.readLine()); // 뱀의 방향 전환 횟수
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int timeInfo = Integer.parseInt(st.nextToken());
            char dirInfo = st.nextToken().charAt(0);
            direction.add(new Info(timeInfo, dirInfo));
        }

        System.out.println(Dummy());
    }

    static int Dummy(){
        int x = 1;
        int y = 1;
        int now_dir = 0; // 처음은 동쪽을 바라보고 있음
        map[x][y] = 2; // 뱀이 있는 자리는 2로 변경
        int time = 0; // 이동 횟수
        int turn = 0; // 방향 전환 횟수

        Queue<Snake> q = new LinkedList<>();
        q.offer(new Snake(x, y));

        while(!q.isEmpty()){
            // 먼저 몸길이를 늘려 머리를 다음칸에 위치시킴
            int nx = x + dx[now_dir];
            int ny = y + dy[now_dir];

            // 범위를 벗어나지 않고 자신의 몸과 부딪히지 않았다면 게임 진행
            if (nx >= 1 && ny >= 1 && nx <= n && ny <= n && map[nx][ny] != 2) {
                if (map[nx][ny] == 1) { // 만약 이동한 칸에 사과가 있다면 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2; // 새로운 머리 추가
                }
                if(map[nx][ny] == 0){ // 이동한 칸에 사과가 없다면, 몸길이를 줄여 꼬리가 위치한 칸을 비워준다.
                    Snake tail = q.poll();
                    map[tail.x][tail.y] = 0; // 길이 줄이기
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2; // 새로운 머리 추가
                }
            }else{ // 맵을 벗어나거나 자신의 몸통과 부딪히면 종료
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++; // 이동시간 증가

            // 다음 방향 전환으로 넘어가야 하는 경우
            if(turn < l && direction.get(turn).time == time){
                now_dir = rotate(now_dir, direction.get(turn).dir);
                turn++;
            }
        }

        return time;
    }

    // 현재 방향, 변경할 방향
    static int rotate(int dir, char c) {
        if(c == 'D'){ // 오른쪽 90도
             dir = (dir + 1)  % 4;
        }else{ // 왼쪽 90도
             dir = (dir == 0) ? 3 : dir-1;
        }

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
