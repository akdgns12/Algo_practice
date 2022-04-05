package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3190 {
    // 뱀 / 골드 5 / 시뮬레이션
    /*
        1. 초기 뱀, (0,0)
     */
    static int n, k, l;
    static int[][] map;
    static ArrayList<Move> Info = new ArrayList<>(); // 뱀 머리 방향 정보
    static int[] dx = {0,1,0,-1}; // 동남서북
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine()); // 사과 개수
        map = new int[n+1][n+1];

        for (int i = 0; i < k; i++) { // 사과 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 사과 위치 1로 설정
        }

        l = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int timeInfo = Integer.parseInt(st.nextToken()); // 게임 시작시간으로부터 X초가 끝난 뒤에 C의 방향에 맞게 90도 방향 회전
            char directionInfo = st.nextToken().charAt(0); // c가 L이면 왼쪽, D면 오른쪽

            Info.add(new Move(timeInfo, directionInfo));
        }

        System.out.println(Dummy());
    }

    static int Dummy(){
        int x = 1, y = 1; // 뱀의 초기 위치
        int now_dir = 0; // 처음은 동쪽을 바라보고 있음
        map[x][y] = 2; // 뱀이 있는 자리는 2로 변경
        int time = 0; // 이동 횟수
        int turn = 0; // 방향 전환 횟수

        Queue<Snake> q = new LinkedList<>();
        q.offer(new Snake(x,y)); // 초기 방향 오른쪽

        while(!q.isEmpty()){
            int nx = x + dx[now_dir];
            int ny = y + dy[now_dir];

            // 범위를 벗어나지 않고 자신의 몸과 부딪히지 않았다면 게임 진행
            if(nx >= 1 && ny >= 1 && nx <= n && ny <= n && map[nx][ny] != 2){
                if(map[nx][ny] == 1){ // 사과가 있는 칸에 도착했을 경우
                    q.offer(new Snake(nx, ny));
                    map[nx][ny] = 2; // 새로운 머리 추가
                }

                if(map[nx][ny] == 0){ // 사과가 없는 칸에 도착했을 경우
                    Snake tail = q.poll();
                    map[tail.x][tail.y] = 0; // 길이 줄이기
                    q.offer(new Snake(nx,ny));
                    map[nx][ny] = 2; // 새로운 머리 추가
                }
            }
            else{ // 맵을 벗어낫거나 자신의 몸통과 부딪히면 종료
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++; // 이동시간 증가

            // 다음 방향 전환으로 넘어가야 하는 경우
            if (turn < l && Info.get(turn).time == time) {
                now_dir = rotate(now_dir, Info.get(turn).direction);
                turn++;
            }
        }

        return time;
    }

    static int rotate(int dir, char c) {
        if(c == 'D') dir = (dir + 1) % 4;
        else dir = (dir == 0) ? 3 : dir - 1;

        return dir;
    }

    static class Snake{
        int x, y;
        public Snake(int x, int y){
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
